package br.laramara.ti.sislaraserver.dominio.agenda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemana;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAgendamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;
import br.laramara.ti.sislaraserver.utilitarios.EntidadeUtils;

@Component
public class Agendador {

	@Inject
	private RepositorioAgendamento repositorioAgendamento;
	
	@Inject
	private RepositorioSislara repositorioSislara;

	public Agendamento liberarAgendamento(Agendamento agendamento,
			StatusAgendamento statusAgendamentoAposLiberacao,
			ContaAcesso contaAcessoReponsavelPorOperacao)
			throws AgendamentoNaoPodeSerLiberado {
		agendamento.aplicarStatus(statusAgendamentoAposLiberacao,
				contaAcessoReponsavelPorOperacao);
		agendamento.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (agendamento.estaCancelado() || agendamento.estaReagendado()) {
			if (agendamento.validado()) {
				repositorioAgendamento.salvar(agendamento);

				agendamento
						.preparaParaLiberar(contaAcessoReponsavelPorOperacao);
				repositorioAgendamento.salvar(agendamento);
			} else {
				throw new AgendamentoNaoPodeSerLiberado(
						" por ausência de motivo.");
			}
			return agendamento;
		} else {
			throw new AgendamentoNaoPodeSerLiberado(".");
		}
	}
	
	public List<Agendamento> gerarAgendamentos(
			EspecificacaoGeracaoAgendamento especificacaoGeracaoAgendamento,
			ContaAcesso contaAcessoResponsavelPorOperacao)
			throws AgendamentoEmConflitoException,
			AgendamentoComDataAnteriorADataAtual {

		List<Agendamento> agendamentos = filtrarFeriadosEPontes(
				gerarAgendamento(especificacaoGeracaoAgendamento, contaAcessoResponsavelPorOperacao));

		verificarErroDataAnterior(agendamentos);
		lancarConflito(obterAgendamentosEmConflito(
				especificacaoGeracaoAgendamento.getProfissionais(),
				agendamentos));

		return repositorioAgendamento.salvar(agendamentos);
	}
	
	private List<Agendamento> filtrarFeriadosEPontes(List<Agendamento> agendamentos) {
		return agendamentos.stream()
				.filter(agendamento -> !repositorioSislara.existeFeriadoOuPonteEmConflito(agendamento))
				.collect(Collectors.toList());
	}
	
	public Agendamento copiarAgendamento(
			EspecificacaoGeracaoCopiaAgendamento especificacaoGeracaoCopiaAgendamento,
			Agendamento agendamento,
			ContaAcesso contaAcessoResponsavelPorOperacao)
			throws AgendamentoNaoPodeSerCopiadoException,
			AgendamentoEmConflitoException,
			AgendamentoComDataAnteriorADataAtual {
		if (agendamento.estaCancelado()) {
			agendamento.prepararParaCopia(
					especificacaoGeracaoCopiaAgendamento.getData(),
					especificacaoGeracaoCopiaAgendamento.getHorario(),
					contaAcessoResponsavelPorOperacao);

			verificarErroDataAnterior(agendamento);
			lancarConflito(obterAgendamentosEmConflito(agendamento));
		} else {
			throw new AgendamentoNaoPodeSerCopiadoException();
		}

		return repositorioAgendamento.salvar(agendamento);
	}
	
	private void verificarErroDataAnterior(List<Agendamento> agendamentos)
			throws AgendamentoComDataAnteriorADataAtual {
		for (Agendamento agendamento : agendamentos) {
			verificarErroDataAnterior(agendamento);
		}
	}
	
	private void verificarErroDataAnterior(Agendamento agendamento)
			throws AgendamentoComDataAnteriorADataAtual {
		if (agendamento.possuiDataAnteriorDataAtual())
				throw new AgendamentoComDataAnteriorADataAtual();
	}
	
	private void lancarConflito(List<Agendamento> agendamentosEmConflito)
			throws AgendamentoEmConflitoException,
			AgendamentoComDataAnteriorADataAtual {
		if (agendamentosEmConflito.size() != 0) {
			throw new AgendamentoEmConflitoException(
					obterDescricaoDeAgendamentosEmConflito(agendamentosEmConflito));
		}
	}
	
	private List<Agendamento> gerarAgendamento(
			EspecificacaoGeracaoAgendamento especificacaoGeracaoAgendamento, ContaAcesso contaAcessoReponsavelPorOperacao) {
		Calendar dataInicio = especificacaoGeracaoAgendamento
				.getDataInicioCalendar();
		Calendar dataTermino = especificacaoGeracaoAgendamento
				.getDataTerminoCalendar();
		DiaSemana diaSemana = especificacaoGeracaoAgendamento.getDiaSemana();

		List<Agendamento> agendamentos = new ArrayList<>();
		if (especificacaoGeracaoAgendamento.solicitacaoAgendamentoUnico()) {
			agendamentos = criarAgendamentosParaDataUnica(especificacaoGeracaoAgendamento, contaAcessoReponsavelPorOperacao);
		} else if (especificacaoGeracaoAgendamento
				.solicitacaoAgendamentoMultiplo()) {
			List<Calendar> datasParaAgendamento = gerarDatasNoDiaDaSemanaNoPeriodo(
					dataInicio, dataTermino, diaSemana);
			agendamentos = criarAgendamentoParaDatasVariadas(
					especificacaoGeracaoAgendamento, datasParaAgendamento, contaAcessoReponsavelPorOperacao);
		}

		return agendamentos;
	}
	
	private List<Agendamento> criarAgendamentosParaDataUnica(
			EspecificacaoGeracaoAgendamento especificacaoGeracaoAgendamento, ContaAcesso contaAcessoPorOperacao) {
		List<Agendamento> agendamentosGerado = new ArrayList<>();

		List<Profissional> profissionais = especificacaoGeracaoAgendamento
				.getProfissionais();
		for (Profissional profissional : profissionais) {
			Usuario usuario = especificacaoGeracaoAgendamento.getUsuario();
			PreCadastro preCadastro = especificacaoGeracaoAgendamento.getPreCadastro();
			List<Grupo> grupos = especificacaoGeracaoAgendamento.getGrupos();
			Status reservaPara = especificacaoGeracaoAgendamento
					.getReservaStatus();
			DescricaoTipoAtendimento descricaoTipoAtendimento = especificacaoGeracaoAgendamento
					.getDescricaoTipoAtendimento();
			Modulo modulo = especificacaoGeracaoAgendamento.getModulo();
			Setor setor = especificacaoGeracaoAgendamento.getSetor();
			LocalAtendimento localAtendimento = especificacaoGeracaoAgendamento
					.getLocalAtendimento();
			Calendar dataInicio = especificacaoGeracaoAgendamento
					.getDataInicioCalendar();
			Horario horario = especificacaoGeracaoAgendamento.getHorario();
			String obs = especificacaoGeracaoAgendamento.getObs();

			Agendamento agendamentoGerado = new Agendamento(contaAcessoPorOperacao);
			agendamentoGerado.setUsuario(usuario);
			agendamentoGerado.setPreCadastro(preCadastro);
			agendamentoGerado.setGrupos(grupos);
			agendamentoGerado.setProfissional(profissional);
			agendamentoGerado
					.setDescricaoTipoAtendimento(descricaoTipoAtendimento);
			agendamentoGerado.setModulo(modulo);
			agendamentoGerado.setReservaPara(reservaPara);
			agendamentoGerado.setSetor(setor);
			agendamentoGerado.setLocalAtendimento(localAtendimento);
			agendamentoGerado.setData(dataInicio);
			agendamentoGerado.setHorario(horario);
			agendamentoGerado.setObs(obs);

			agendamentosGerado.add(agendamentoGerado);
		}

		return agendamentosGerado;
	}
	
	private List<Agendamento> criarAgendamentoParaDatasVariadas(
			EspecificacaoGeracaoAgendamento especificacaoGeracaoAgendamento,
			List<Calendar> datas, ContaAcesso contaAcessoReponsavelPorOperacao) {
		List<Agendamento> agendamentosGerados = new ArrayList<>();
		for (Calendar data : datas) {
			List<Agendamento> agendamentos = criarAgendamentosParaDataUnica(especificacaoGeracaoAgendamento, contaAcessoReponsavelPorOperacao);
			for (Agendamento agendamento : agendamentos) {
				agendamento.setData(data);
				agendamentosGerados.add(agendamento);
			}
		}
		return agendamentosGerados;
	}

	public static synchronized List<Calendar> gerarDatasNoDiaDaSemanaNoPeriodo(Calendar dataInicio,
			Calendar dataTermino, DiaSemana diaSemana) {
		List<Calendar> datasParaAgendamento = new ArrayList<>();
		Calendar ultimaData = (Calendar) dataInicio.clone();
		while (ultimaData.equals(dataInicio) || ultimaData.equals(dataTermino)
				|| ultimaData.before(dataTermino)) {
			if ((ultimaData.before(dataTermino) || ultimaData
					.equals(dataTermino))
					&& EntidadeUtils.diaSemanaCompativelComData(diaSemana, ultimaData)) {
				datasParaAgendamento.add((Calendar) ultimaData.clone());
			}
			ultimaData.add(Calendar.DAY_OF_MONTH, 1);
		}
		return datasParaAgendamento;
	}

	private String obterDescricaoDeAgendamentosEmConflito(
			List<Agendamento> agendamentos) {
		String retorno = "";
		for (Agendamento agendamento : agendamentos) {
			retorno += agendamento.getProfissional().getNome() + ", "
					+ agendamento.getData() + ", "
					+ agendamento.getHorario().getHoraInicio() + " às "
					+ agendamento.getHorario().getHoraTermino() + ". ";
		}
		return retorno.substring(0, retorno.length() - 2);
	}
	
	private List<Agendamento> obterAgendamentosEmConflito(
			Agendamento agendamento) {
		List<Profissional> profissionais = new ArrayList<>();
		profissionais.add(agendamento.getProfissional());
		List<Agendamento> agendamentos = new ArrayList<>();
		agendamentos.add(agendamento);

		return obterAgendamentosEmConflito(profissionais, agendamentos);
	}
	
	private List<Agendamento> obterAgendamentosEmConflito(
			List<Profissional> profissionais,
			List<Agendamento> agendamentosGerados) {
		List<Agendamento> agendamentosEmConflito = new ArrayList<>();
		for (Profissional profissional : profissionais) {
			for (Agendamento agendamentoGerado : agendamentosGerados) {
				List<Agendamento> agendamentosExistentes = repositorioAgendamento
						.obterTodosPor(profissional,
								agendamentoGerado.getDataCalendario());

				for (Agendamento agendamento : agendamentosExistentes) {
					if (agendamento.conflito(agendamentoGerado)) {
						agendamentosEmConflito.add(agendamento);
					}
				}
			}
		}
		return agendamentosEmConflito;
	}

}

package br.laramara.ti.sislaraserver.fachadas.operacoes;

import java.util.Arrays;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.agenda.RestricaoAssociacaoPreCadastro;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.pendencia.Pendencia;
import br.laramara.ti.sislaraserver.dominio.pendencia.PendenciaAtendimentoIndividualPreCadastro;
import br.laramara.ti.sislaraserver.dominio.pendencia.PendenciaAtendimentoIndividualUsuario;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaAgendamento;
import br.laramara.ti.sislaraserver.fachadas.Fachada;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAgendamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPendencia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;

public class OperacaoAgendamentoEdicao implements OperacaoFachada  {

	private static final Logger logger = Logger
			.getLogger(OperacaoAgendamentoEdicao.class);

	private FabricaAgendamento fabricaAgendamento;
	private RepositorioAgendamento repositorioAgendamento;
	private RepositorioEspera repositorioEspera;
	private RepositorioTipoAtendimento repositorioTipoAtendimento;
	private RepositorioPendencia repositorioPendencia;
	private AgendamentoDTO agendamentoDto;
	private RestricaoAssociacaoPreCadastro restricaoAssociacaoPreCadastro;
	private boolean originadoPorExcessoDeFaltas;

	public OperacaoAgendamentoEdicao(boolean originadoPorExcessoDeFaltas, FabricaAgendamento fabricaAgendamento,
			RepositorioAgendamento repositorioAgendamento, RepositorioEspera repositorioEspera,
			RepositorioTipoAtendimento repositorioTipoAtendimento, RepositorioPendencia repositorioPendencia,
			AgendamentoDTO agendamentoDto, RestricaoAssociacaoPreCadastro restrito) {
		this.fabricaAgendamento = fabricaAgendamento;
		this.repositorioAgendamento = repositorioAgendamento;
		this.repositorioEspera = repositorioEspera;
		this.repositorioTipoAtendimento = repositorioTipoAtendimento;
		this.repositorioPendencia = repositorioPendencia;
		this.agendamentoDto = agendamentoDto;
		this.restricaoAssociacaoPreCadastro = restrito;
		this.originadoPorExcessoDeFaltas = originadoPorExcessoDeFaltas;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultado) {
		Agendamento agendamento = fabricaAgendamento.converterParaDominio(
				agendamentoDto,
				repositorioAgendamento.obterPorId(agendamentoDto.getId()));
		agendamento.setOriginadoPorExcessoDeFaltas(originadoPorExcessoDeFaltas);
		agendamento
				.validarRestricaoAssociacaoPreCadastro(restricaoAssociacaoPreCadastro);
		agendamento.inicializarStatus(contaAcesso);
		agendamento.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		if (agendamento.validado()) {
			if (!AutomatizadorEspera.verificarSePossuiEsperaPorExcessoDeFaltasNaoJustificadasENaoEServicoSocialAvaliacaoETriagem(
					agendamento, repositorioEspera, repositorioTipoAtendimento)) {
				logger.info(contaAcesso
						+ " efetuou Solicitação de Edição do "
						+ agendamento);
				Agendamento agendamentoSalvo = repositorioAgendamento
						.salvar(agendamento);
				salvarPendenciaAtendimentoIndividual(agendamentoSalvo);
				resultado.efetuadoComSucesso(fabricaAgendamento
						.converterParaDTO(agendamentoSalvo));
			} else {
				resultado
						.adicionarErro(Fachada.MENSAGEM_BLOQUEIO_POR_FALTAS_EXCESSIVAS);
			}
		} else {
			resultado.adicionarErro(agendamento.obterDescricaoErros());
		}
		return resultado;
	}

	private void salvarPendenciaAtendimentoIndividual(Agendamento agendamentoSalvo) {
		Pendencia pendencia = null;
		if (agendamentoSalvo.possuiUsuario()) {
			pendencia = new PendenciaAtendimentoIndividualUsuario(agendamentoSalvo.getUsuario().getId(),
					agendamentoSalvo, Arrays.asList(agendamentoSalvo.getProfissional()));
		} else if (agendamentoSalvo.possuiPreCadastro()) {
			pendencia = new PendenciaAtendimentoIndividualPreCadastro(agendamentoSalvo,
					Arrays.asList(agendamentoSalvo.getProfissional()));
		}
		repositorioPendencia.salvar(pendencia);
	}
}

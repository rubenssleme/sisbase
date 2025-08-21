package br.laramara.ti.sislaraserver.fachadas.operacoes;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendador;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemanaEHorario;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.pendencia.Pendencia;
import br.laramara.ti.sislaraserver.dominio.pendencia.PendenciaAtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaGrupo;
import br.laramara.ti.sislaraserver.fachadas.Fachada;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioGrupo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPendencia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;

public class OperacaoGrupoEdicao implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoGrupoEdicao.class);

	private FabricaGrupo fabricaGrupo;
	private RepositorioGrupo repositorioGrupo;
	private RepositorioPendencia repositorioPendencia;
	private RepositorioSislara repositorioSislara;
	private RepositorioEspera repositorioEspera;
	private RepositorioTipoAtendimento repositorioTipoAtendimento;
	private GrupoDTO grupoDto;
	
	public OperacaoGrupoEdicao(FabricaGrupo fabricaGrupo, RepositorioGrupo repositorioGrupo,
			RepositorioPendencia repositorioPendencia, RepositorioSislara repositorioSislara,
			RepositorioEspera repositorioEspera, RepositorioTipoAtendimento repositorioTipoAtendimento,
			GrupoDTO grupoDto) {
		super();
		this.fabricaGrupo = fabricaGrupo;
		this.repositorioGrupo = repositorioGrupo;
		this.repositorioPendencia = repositorioPendencia;
		this.repositorioEspera = repositorioEspera;
		this.repositorioTipoAtendimento = repositorioTipoAtendimento;
		this.repositorioSislara = repositorioSislara;
		this.grupoDto = grupoDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso, ResultadoDTO resultado) {
		Grupo grupo = fabricaGrupo.converterParaDominio(grupoDto, repositorioGrupo.obterGrupoPorId(grupoDto.getId()));
		grupo.adicionarListaUsuariosEmEsperaPorExcessoDeFaltas(
				AutomatizadorEspera.obterUsuariosNaEsperaDoServicoSocialPorExcessoDeFaltasAguardando(
						grupo.obterTodosUsuariosIntegrados(), repositorioEspera, repositorioTipoAtendimento));
		grupo.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (grupo.validado()) {
			if (repositorioGrupo.confirmaVersaoAtualPorIdGrupo(grupoDto.getId(), grupoDto.getVersao())) {
				if (!repositorioGrupo.possuiGrupoAtivo(grupo)) {
					logger.info(contaAcesso + " efetuou Solicitação de Edição do " + grupo);
					Grupo grupoSalvo = repositorioGrupo.salvar(grupo);
					resolverPendenciasAnterioresDoGrupo(grupoSalvo);
					salvarPendenciaAtendimentoGrupo(grupoSalvo);
					resultado.efetuadoComSucesso(fabricaGrupo.converterParaDTO(
							repositorioGrupo.obterGrupoSemAtendimentosEIntegrantesPorId(grupoSalvo.getId())));
				} else {
					logger.error(Fachada.GRUPO_ATIVO_JA_EXISTENTE + contaAcesso.toString());
					resultado.adicionarErro(Fachada.GRUPO_ATIVO_JA_EXISTENTE);
				}
			} else {
				logger.error(Fachada.MENSAGEM_DADOS_DESATUALIZADOS + contaAcesso.toString());
				resultado.adicionarErro(Fachada.MENSAGEM_DADOS_DESATUALIZADOS);
			}
		} else {
			resultado.adicionarErro(grupo.obterDescricaoErros());
		}
		return resultado;
	}

	private void resolverPendenciasAnterioresDoGrupo(Grupo grupo) {
		repositorioSislara.resolverPendencia(grupo);
	}

	private void salvarPendenciaAtendimentoGrupo(Grupo grupo) {
		if (grupo.isAtivo() && (grupo.podeCriarPendenciaDeAtendimento())) {
			for (ModuloPeriodo moduloPeriodo : grupo.getModulosPeriodos()) {
				if (!moduloPeriodo
						.ePsicossocialParaAsFamiliasOuPsicossocialParaOsJovensOuReuniaoComAsInstituicoesDeEnsino()
						&& !moduloPeriodo.eModuloSerAdolescente() && !moduloPeriodo.eModuloHistoriasEscola()) {
					for (DiaSemanaEHorario diaSemanaEHorario : moduloPeriodo.getDiasSemanaEHorariosDaAtividade()) {
						List<Calendar> datasDoDiaDaSemanaNoPeriodo = Agendador.gerarDatasNoDiaDaSemanaNoPeriodo(grupo.getDataInicioCalendario(),
								grupo.getDataTerminoCalendario(), diaSemanaEHorario.getDiaSemana());
						for (Calendar dataDoDiaDaSemanaNoPeriodo : datasDoDiaDaSemanaNoPeriodo) {
							Pendencia pendencia = new PendenciaAtendimentoGrupo(dataDoDiaDaSemanaNoPeriodo,
									diaSemanaEHorario.getHorario(), moduloPeriodo.obterSomenteProfissionais(), grupo,
									moduloPeriodo);
							repositorioPendencia.salvar(pendencia);
						}
					}
				}
			}
		}
	}
}

package br.laramara.ti.sislaraserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspera;
import br.laramara.ti.sislaraserver.fachadas.Fachada;
import br.laramara.ti.sislaraserver.repositorios.RepositorioBloqueio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;

public class OperacaoEsperaEdicao implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoEsperaEdicao.class);

	private FabricaEspera fabricaEspera;
	private RepositorioEspera repositorioEspera;
	private RepositorioBloqueio repositorioBloqueio;
	private RepositorioTipoAtendimento repositorioTipoAtendimento;
	private EsperaDTO esperaDto;

	public OperacaoEsperaEdicao(FabricaEspera fabricaEspera,
			RepositorioEspera repositorioEspera, RepositorioBloqueio repositorioBloqueio, RepositorioTipoAtendimento repositorioTipoAtendimento, EsperaDTO esperaDto) {
		this.fabricaEspera = fabricaEspera;
		this.repositorioEspera = repositorioEspera;
		this.repositorioBloqueio = repositorioBloqueio;
		this.repositorioTipoAtendimento = repositorioTipoAtendimento;
		this.esperaDto = esperaDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultadoDto) {
		Espera espera = fabricaEspera.converterParaDominio(esperaDto,
				repositorioEspera.obterPorId(esperaDto.getId()));
		espera.setContaAcessoOperacao(contaAcesso);
		espera.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (espera.validado()) {
			if (!repositorioBloqueio.existeBloqueioListaEspera(espera)) {
				if (!AutomatizadorEspera.verificarSePossuiEsperaAguardandoOuJaExistiuEsperaComMesmaChaveDeAtendimento(espera,
						repositorioEspera)) {
					if (!AutomatizadorEspera.verificarSePossuiEsperaPorExcessoDeFaltasNaoJustificadasENaoEDescricaoServicoSocialEModuloAvaliacaoETriagem(
							espera, repositorioEspera,
							repositorioTipoAtendimento)) {
						if (!AutomatizadorEspera
								.verificarSeExisteEsperaSimultaneamenteEmAtendimentoEspecificoEspecializadoETriagemOftalmologicaDaAvaliacaoDeServicoEmOftalmologia(
										espera, repositorioEspera, repositorioTipoAtendimento)) {
							logger.info(contaAcesso
									+ " efetuou Solicitação de Edição do "
									+ espera);
							Espera esperaSalvo = repositorioEspera
									.salvar(espera);
							resultadoDto.efetuadoComSucesso(fabricaEspera
									.converterParaDTO(esperaSalvo));
						} else {
							resultadoDto
									.adicionarErro(Fachada.MENSAGEM_BLOQUEIO_POR_EXISTENCIA_SIMULTANEA_TRIAGEM_E_AVALIACAO_OFTALMOLOGICA);
						}
					} else {
						resultadoDto
								.adicionarErro(Fachada.MENSAGEM_BLOQUEIO_POR_FALTAS_EXCESSIVAS);
					}
				} else {
					resultadoDto
							.adicionarErro("O Usuário ou Pré-cadastro já está incluído na lista de espera do tipo selecionado.");
				}
			} else {
				resultadoDto
						.adicionarErro("Módulo/Atividade não permite a inclusão.");
			}
		} else {
			resultadoDto.adicionarErro(espera.obterDescricaoErros());
		}
		return resultadoDto;
	}
}

package br.laramara.ti.sislaraserver.fachadas.operacoes;

import java.util.List;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.usuario.AutomatizadorStatusUsuario;
import br.laramara.ti.sislaraserver.fabricas.FabricaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAtendimentoIndividual;
import br.laramara.ti.sislaraserver.repositorios.RepositorioBloqueio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPreCadastro;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioUsuario;

public class OperacaoAtendimentoIndividualEdicao extends OperacaoAtendimentoIndividual implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoAtendimentoIndividualEdicao.class);

	private FabricaAtendimentoIndividual fabricaAtendimentoIndividual;
	private AtendimentoIndividualDTO atendimentoIndividualDto;
	private AutomatizadorStatusUsuario automatizadorStatusUsuario;
	private RepositorioUsuario repositorioUsuario;

	public OperacaoAtendimentoIndividualEdicao(
			FabricaAtendimentoIndividual fabricaAtendimentoIndividual,
			RepositorioAtendimentoIndividual repositorioAtendimentoIndividual,
			RepositorioBloqueio repositorioBloqueio,
			RepositorioEspera repositorioEspera,
			RepositorioTipoAtendimento repositorioTipoAtendimento,
			RepositorioPreCadastro repositorioPreCadastro,
			RepositorioUsuario repositorioUsuario,
			AutomatizadorEspera automatizadorEspera, 
			AutomatizadorStatusUsuario automatizadorStatusUsuario,
			AtendimentoIndividualDTO atendimentoIndividualDto) {
		super(repositorioAtendimentoIndividual, repositorioBloqueio,
				repositorioEspera, repositorioTipoAtendimento, repositorioPreCadastro,  
				automatizadorEspera, fabricaAtendimentoIndividual);
		this.fabricaAtendimentoIndividual = fabricaAtendimentoIndividual;
		this.atendimentoIndividualDto = atendimentoIndividualDto;
		this.automatizadorEspera = automatizadorEspera;
		this.automatizadorStatusUsuario = automatizadorStatusUsuario;
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultadoDto) {
		
		AtendimentoIndividual atendimentoIndividual = fabricaAtendimentoIndividual.converterParaDominio(
				atendimentoIndividualDto,
				repositorioAtendimentoIndividual.obterPorId(atendimentoIndividualDto.getId()));
		atendimentoIndividual.atualizarUsuarioCadastroPosteriormente(repositorioPreCadastro);
		atendimentoIndividual.atualizarAcoesCondutasNovas();
		atendimentoIndividual.vincularContaAcessoResponsavelPorOperacao(contaAcesso);
		atendimentoIndividual
				.validarRetornoDeAvaliacaoFuncionalComFrequenciaATSemAtendimentoDeAvaliacaoFuncionalNosUltimos6Meses(
						repositorioAtendimentoIndividual);
		atendimentoIndividual.validarObrigatoriedadeDeIntegracaoEDiscussaoDeCaso(repositorioUsuario);
		atendimentoIndividual.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (atendimentoIndividual.validado()) {
			List<AtendimentoIndividual> atendimentosIndividuaisEmConflito = obtemAtendimentosIndividuaisEmConflito(atendimentoIndividual);
			if (!existeAtendimentoIndividualEmConflito(atendimentosIndividuaisEmConflito)) {
				if (atendimentoIndividual.eRetorno() || !repositorioAtendimentoIndividual
						.existeAtendimentoIndividualComUsuarioPrimeiraVez(atendimentoIndividual)) {
					logger.info(contaAcesso + " efetuou Solicitação de Edição do " + atendimentoIndividual);
					try {
						processar(atendimentoIndividual, contaAcesso, resultadoDto);
						automatizadorStatusUsuario.atualizarStatusParaRetornoSeNecessario(atendimentoIndividual);
						automatizadorEspera.retornarEsperaPorExcessoDeFaltasSeNecessario(atendimentoIndividual,
								contaAcesso);
						automatizadorEspera.incluirNaListaDeEsperaDeAvaliacaoFuncionalSeNecessario(atendimentoIndividual);
					} catch (Exception e) {
						logger.error("Ocorreu algum erro durante o armazenamento do " + atendimentoIndividual
								+ ". \nDetalhes: " + e);
					}
				} else {
					resultadoDto.adicionarErro("Atendimento individual da primeira vez já foi efetuado.");
				}
			} else {
				resultadoDto
						.adicionarErro(obterDescricaoDeAtendimentosEmConflito(atendimentosIndividuaisEmConflito));
			}
		} else {
			resultadoDto.adicionarErro(atendimentoIndividual
					.obterDescricaoErros());
		}
		return resultadoDto;
	}
}

package br.laramara.ti.sislaraserver.fachadas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.EspecificacaoPesquisaContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoEdicaoContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoListagemContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoListagemMotivoDesativacaoDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoListagemStatusContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaraserver.dominio.contribuicao.AutomatizadorContribuicao;
import br.laramara.ti.sislaraserver.dominio.contribuicao.Contribuinte;
import br.laramara.ti.sislaraserver.dominio.contribuicao.StatusContribuinte;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.fabricas.FabricaContribuinte;
import br.laramara.ti.sislaraserver.fabricas.FabricaMotivoDesativacao;
import br.laramara.ti.sislaraserver.fabricas.FabricaStatusContribuinte;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoContribuinteEdicao;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.repositorios.RepositorioContribuinte;
import br.laramara.ti.sislaraserver.repositorios.RepositorioMotivoDesativacao;

@Component
public class FachadaContribuinte extends Fachada {

	@Inject
	private RepositorioContribuinte repositorioContribuinte;
	@Inject
	private RepositorioMotivoDesativacao repositorioMotivoDesativacao;
	@Inject
	private AutomatizadorContribuicao automatizarContribuicao;

	public synchronized ResultadoEdicaoContribuinteDTO editarContribuinte(
			ContribuinteDTO contribuinteDTO, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoContribuinteEdicao(
				new FabricaContribuinte(), repositorioContribuinte,
				contribuinteDTO);
		return (ResultadoEdicaoContribuinteDTO) verificarPermissaoEProcessar(
				operacao, Permissao.CONTRIBUINTE_EDICAO,
				new ResultadoEdicaoContribuinteDTO(), tokenDto);
	}

	public ResultadoListagemContribuinteDTO obterListagemContribuinte(
			EspecificacaoPesquisaContribuinteDTO especificacao) {
		ResultadoListagemContribuinteDTO resultadoDto = new ResultadoListagemContribuinteDTO();
		List<Contribuinte> contribuintes = new ArrayList<>();
		try {
			if (especificacao.existeChave(ChavePesquisaDTO.TODOS_CONTRIBUINTES)) {
				contribuintes = repositorioContribuinte.obterTodos();
			} else if (especificacao.existeChave(ChavePesquisaDTO.NOME_CONTRIBUINTE)) {
				String parametro = (String) especificacao
						.obterParametro(ChavePesquisaDTO.NOME_CONTRIBUINTE);
				contribuintes = repositorioContribuinte.obterPorNome(parametro);
			} else {
				resultadoDto.adicionarErro(MENSAGEM_INSIRA_TIPO_DADO_PESQUISA);
			}
			gravarResultadoSeNaoForVazio(contribuintes,
					new FabricaContribuinte(), resultadoDto);
		} catch (NumberFormatException e) {
			String erro = "Insira um parâmetro de pesquisa válido.";
			resultadoDto.adicionarErro(erro);
			logger.error(erro + "\nDetalhes: " + e);
		} catch (Exception e) {
			String erro = "Erro durante a pesquisa por Contribuinte.";
			resultadoDto.adicionarErro(erro);
			logger.fatal(erro + "\nDetalhes: " + e);
		}
		return resultadoDto;
	}

	public ResultadoListagemStatusContribuinteDTO obterListagemStatusContribuinte() {
		return (ResultadoListagemStatusContribuinteDTO) obterListagem(Arrays.asList(StatusContribuinte.values()),
				new FabricaStatusContribuinte(), "Status de Contribuinte",
				new ResultadoListagemStatusContribuinteDTO());
	}
	
	public boolean solicitarGeracaoArquivoCobranca(TokenDTO tokenDto){
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (contaAcesso.possuiPermissao(Permissao.GERACAO_ARQUIVO_COBRANCA_CONTRIBUICAO)){
			return automatizarContribuicao.gerarArquivoEEnviarParaEmail();
		}
		return false;
	}

	public ResultadoListagemMotivoDesativacaoDTO obterListagemMotivoDesativacao() {
			return (ResultadoListagemMotivoDesativacaoDTO) obterListagem(
					repositorioMotivoDesativacao.obterTodos(), new FabricaMotivoDesativacao(),
					"Motivo de desativacao", new ResultadoListagemMotivoDesativacaoDTO());
	}
}

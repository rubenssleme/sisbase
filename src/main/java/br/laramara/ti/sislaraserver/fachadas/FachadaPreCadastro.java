package br.laramara.ti.sislaraserver.fachadas;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.EspecificacaoPesquisaPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.ResultadoEdicaoPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.ResultadoListagemPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.fabricas.FabricaPreCadastro;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoPreCadastroEdicao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPreCadastro;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;

@Component
public class FachadaPreCadastro extends Fachada {

	@Inject
	private RepositorioPreCadastro repositorioPreCadastro;
	
	@Inject
	private RepositorioSislara repositorioSislara;
	
	public ResultadoListagemPreCadastroDTO pesquisarPreCadastroPor(
			EspecificacaoPesquisaPreCadastroDTO especificacao) {
		ResultadoListagemPreCadastroDTO resultado = new ResultadoListagemPreCadastroDTO();
		try {
			List<PreCadastro> preCadastros = new ArrayList<>();
			if (especificacao
					.existeChave(ChavePesquisaDTO.RG)) {
				String parametro = (String) especificacao
								.obterParametro(ChavePesquisaDTO.RG);
				
				preCadastros = repositorioPreCadastro
						.pesquisarPorRG(parametro);
			} else if (especificacao
					.existeChave(ChavePesquisaDTO.PRE_CADASTRO)) {
				String parametro = (String) especificacao
						.obterParametro(ChavePesquisaDTO.PRE_CADASTRO);

				preCadastros = repositorioPreCadastro
						.pesquisarPorNome(parametro);
				
			} else if (especificacao.existeChave(ChavePesquisaDTO.CPF)){
				String parametro = (String) especificacao.obterParametro(ChavePesquisaDTO.CPF);

				preCadastros = repositorioPreCadastro.pesquisarPorCpf(parametro);
			} else {
				resultado.adicionarErro(MENSAGEM_INSIRA_TIPO_DADO_PESQUISA);
			}
			gravarResultadoSeNaoForVazio(preCadastros, new FabricaPreCadastro(), resultado);
		} catch (NumberFormatException e) {
			String erro = "Insira um parâmetro de pesquisa válido.";
			resultado.adicionarErro(erro);
			logger.error(erro + "\nDetalhes: " + e);
		} catch (Exception e) {
			String erro = "Erro durante a pesquisa por Pré-Cadastro.";
			resultado.adicionarErro(erro);
			logger.fatal(erro + "\nDetalhes: " + e);
		}
		return resultado;
	}

	public ResultadoEdicaoPreCadastroDTO editarPreCadastro(
			PreCadastroDTO preCadastroDto, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoPreCadastroEdicao(
				new FabricaPreCadastro(), repositorioPreCadastro, repositorioSislara, preCadastroDto);
		return (ResultadoEdicaoPreCadastroDTO) verificarPermissaoEProcessar(
				operacao, Permissao.PRE_CADASTRO_EDICAO,
				new ResultadoEdicaoPreCadastroDTO(), tokenDto);
	}
}

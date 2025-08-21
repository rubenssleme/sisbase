package br.laramara.ti.sislaraserver.fachadas;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemDiretoriaEnsinoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemDreCefaiDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemTipoApoioDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemTipoEspecialidadeDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.EspecificacaoPesquisaInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoEdicaoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemClassificacaoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemTipoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaraserver.dominio.escola.TipoApoio;
import br.laramara.ti.sislaraserver.dominio.escola.TipoEspecialidade;
import br.laramara.ti.sislaraserver.dominio.instituicao.ClassificacaoInstituicao;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;
import br.laramara.ti.sislaraserver.dominio.instituicao.TipoInstituicao;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.fabricas.FabricaClassificacaoInstituicao;
import br.laramara.ti.sislaraserver.fabricas.FabricaDiretoriaEnsino;
import br.laramara.ti.sislaraserver.fabricas.FabricaDreCefai;
import br.laramara.ti.sislaraserver.fabricas.FabricaInstituicao;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipoApoio;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipoEspecialidade;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipoInstituicao;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoInstituicaoEdicao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioDiretoriaEnsino;
import br.laramara.ti.sislaraserver.repositorios.RepositorioDreCefai;
import br.laramara.ti.sislaraserver.repositorios.RepositorioInstituicao;

@Component
public class FachadaInstituicao extends Fachada {

	@Inject
	private RepositorioInstituicao repositorioInstituicao;
	@Inject
	private RepositorioDreCefai repositorioDreCefai;
	@Inject
	private RepositorioDiretoriaEnsino repositorioDiretoriaEnsino;

	public ResultadoListagemClassificacaoInstituicaoDTO obterListagemClassificacaoInstituicao() {
		return (ResultadoListagemClassificacaoInstituicaoDTO) obterListagem(
				Arrays.asList(ClassificacaoInstituicao.values()),
				new FabricaClassificacaoInstituicao(),
				"Classificação de Instituição",
				new ResultadoListagemClassificacaoInstituicaoDTO());
	}

	public ResultadoListagemTipoInstituicaoDTO obterListagemTipoInstituicao() {
		return (ResultadoListagemTipoInstituicaoDTO) obterListagem(
				Arrays.asList(TipoInstituicao.values()),
				new FabricaTipoInstituicao(), "Tipo de Instituiçao",
				new ResultadoListagemTipoInstituicaoDTO());
	}

	public ResultadoEdicaoInstituicaoDTO editarInstituicao(
			InstituicaoDTO instituicaoDto, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoInstituicaoEdicao(
				repositorioInstituicao, instituicaoDto);
		return (ResultadoEdicaoInstituicaoDTO) verificarPermissaoEProcessar(
				operacao, Permissao.INSTITUICAO_EDICAO,
				new ResultadoEdicaoInstituicaoDTO(), tokenDto);
	}

	public ResultadoListagemInstituicaoDTO pesquisarInstituicaoPor(
			EspecificacaoPesquisaInstituicaoDTO especificacao) {
		ResultadoListagemInstituicaoDTO resultado = new ResultadoListagemInstituicaoDTO();
		try {
			if (especificacao
					.existeChave(ChavePesquisaDTO.NOME_INSTITUICAO)) {
				String parametro = (String) especificacao
						.obterParametro(ChavePesquisaDTO.NOME_INSTITUICAO);

				List<Instituicao> instituicoes = repositorioInstituicao
						.pesquisarPorNome(parametro);
				gravarResultadoSeNaoForVazio(instituicoes, new FabricaInstituicao(), resultado);
			} else {
				resultado.adicionarErro(MENSAGEM_INSIRA_TIPO_DADO_PESQUISA);
			}
		} catch (Exception e) {
			String erro = "Erro durante a pesquisa por Instituição.";
			resultado.adicionarErro(erro);
			logger.fatal(erro + "\nDetalhes: " + e);
		}
		return resultado;
	}
	
	public ResultadoListagemInstituicaoDTO obterListagemInstituicoes() {
		return (ResultadoListagemInstituicaoDTO) obterListagem(
				repositorioInstituicao.obterTodos(), new FabricaInstituicao(),
				"Instituição de Ensino", new ResultadoListagemInstituicaoDTO());
	}

	public ResultadoListagemTipoEspecialidadeDTO obterListagemTipoEspecialidade() {
		return (ResultadoListagemTipoEspecialidadeDTO) obterListagem(
				Arrays.asList(TipoEspecialidade.values()), new FabricaTipoEspecialidade(),
				"Tipo de Especialidade", new ResultadoListagemTipoEspecialidadeDTO());
	}
	
	public ResultadoListagemTipoApoioDTO obterListagemTipoApoio() {
		return (ResultadoListagemTipoApoioDTO) obterListagem(
				Arrays.asList(TipoApoio.values()),
				new FabricaTipoApoio(), "Tipo de Apoio",
				new ResultadoListagemTipoApoioDTO());
	}

	public ResultadoListagemDreCefaiDTO obterListagemDreCefai() {
		return (ResultadoListagemDreCefaiDTO) obterListagem(
				repositorioDreCefai.obterTodos(), new FabricaDreCefai(),
				"DreCefai", new ResultadoListagemDreCefaiDTO());
	}

	public ResultadoListagemDiretoriaEnsinoDTO obterLitagemDiretoriaEnsino() {
		return (ResultadoListagemDiretoriaEnsinoDTO) obterListagem(
				repositorioDiretoriaEnsino.obterTodos(),
				new FabricaDiretoriaEnsino(), "Diretoria de Ensino",
				new ResultadoListagemDiretoriaEnsinoDTO());
	}

	public ResultadoListagemInstituicaoDTO obterListagemInstituicoesComSRMs() {
		return (ResultadoListagemInstituicaoDTO) obterListagem(
				repositorioInstituicao.obterTodosComSRMs(),
				new FabricaInstituicao(), "Instituição de Ensino com SRMs",
				new ResultadoListagemInstituicaoDTO());
	}

	public ResultadoListagemInstituicaoDTO obterListagemInstituicoesComSalaRecurso() {
		return (ResultadoListagemInstituicaoDTO) obterListagem(
				repositorioInstituicao.obterTodosComSalaRecurso(),
				new FabricaInstituicao(),
				"Instituição de Ensino com Sala de Ensino",
				new ResultadoListagemInstituicaoDTO());
	}
	
	public ResultadoListagemInstituicaoDTO obterListagemInstituicoesComOutrosAEE() {
		return (ResultadoListagemInstituicaoDTO) obterListagem(
				repositorioInstituicao.obterTodosComOutrosAEE(),
				new FabricaInstituicao(),
				"Instituição de Ensino com Outros AEE",
				new ResultadoListagemInstituicaoDTO());
	}
}

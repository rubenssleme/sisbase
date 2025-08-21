package br.laramara.ti.sislaraserver.fachadas;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.PatrocinioDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.RecursoDisponivelDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoEdicaoProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemEmpresaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemPrestacaoContaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoValidacaoRecursoDisponivelDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.ResultadoValidacaoPatrocinioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaraserver.dominio.doacao.PrestacaoConta;
import br.laramara.ti.sislaraserver.dominio.doacao.Projeto;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.fabricas.FabricaArquivo;
import br.laramara.ti.sislaraserver.fabricas.FabricaEmpresa;
import br.laramara.ti.sislaraserver.fabricas.FabricaPatrocinio;
import br.laramara.ti.sislaraserver.fabricas.FabricaPrestacaoConta;
import br.laramara.ti.sislaraserver.fabricas.FabricaProjeto;
import br.laramara.ti.sislaraserver.fabricas.FabricaRecursoDisponivel;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoProjetoEdicao;
import br.laramara.ti.sislaraserver.fachadas.validadores.Validador;
import br.laramara.ti.sislaraserver.repositorios.RepositorioArquivo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEmpresa;
import br.laramara.ti.sislaraserver.repositorios.RepositorioProjeto;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRecurso;

@Component
public class FachadaProjeto extends Fachada{
	
	@Inject
	private RepositorioProjeto repositorioProjeto;
	@Inject
	private RepositorioRecurso repositorioRecurso;
	@Inject 
	private RepositorioArquivo repositorioArquivo;
	@Inject
	private RepositorioEmpresa repositorioEmpresa;
	
	public ResultadoListagemProjetoDTO pesquisarProjetoPor(
			EspecificacaoPesquisaProjetoDTO especificacao) {
		ResultadoListagemProjetoDTO resultado = new ResultadoListagemProjetoDTO();
		try {
			if (especificacao
					.existeChave(ChavePesquisaDTO.PROJETO)) {
				String parametro = (String) especificacao
						.obterParametro(ChavePesquisaDTO.PROJETO);

				List<Projeto> projetos = repositorioProjeto.
						pesquisarPorNome(parametro);
						
				gravarResultadoSeNaoForVazio(projetos, new FabricaProjeto(), resultado);
			} else {
				resultado.adicionarErro(MENSAGEM_INSIRA_TIPO_DADO_PESQUISA);
			}
		} catch (Exception e) {
			String erro = "Erro durante a pesquisa por Projeto.";
			resultado.adicionarErro(erro);
			logger.fatal(erro + "\nDetalhes: " + e);
		}
		return resultado;
	}
	
	public ResultadoEdicaoProjetoDTO editarProjeto(ProjetoDTO projetoDto,
			TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoProjetoEdicao(new FabricaProjeto(),
				repositorioProjeto, projetoDto);
		return (ResultadoEdicaoProjetoDTO) verificarPermissaoEProcessar(operacao,
				Permissao.PROJETO_EDICAO, new ResultadoEdicaoProjetoDTO(), tokenDto);
	}

	public String obterValorTotalPorRecurso(RecursoDTO recursoDto, String quantidade) {
		Recurso recurso = repositorioRecurso.obterPorId(recursoDto.getId());
		return DinheiroUtils.obterDinheiro(recurso.getValor().multiply(BigDecimal.valueOf(Long.valueOf(quantidade.trim()))));
	}

	public ResultadoEdicaoProjetoDTO calcularTotais(ProjetoDTO projetoDTO) {
		Projeto projeto = new FabricaProjeto().converterParaDominio(projetoDTO);
		ResultadoEdicaoProjetoDTO resultadoEdicaoProjetoDTO = new ResultadoEdicaoProjetoDTO();
		resultadoEdicaoProjetoDTO.efetuadoComSucesso(new FabricaProjeto().converterParaDTO(projeto));
		return resultadoEdicaoProjetoDTO;
	}

	public ResultadoListagemPrestacaoContaDTO obterListagemPrestacaoConta() {
		return (ResultadoListagemPrestacaoContaDTO) obterListagem(Arrays.asList(PrestacaoConta.values()),
				new FabricaPrestacaoConta(), "Prestação de conta", new ResultadoListagemPrestacaoContaDTO());
	}

	public ArquivoDTO obterArquivoProjeto(ProjetoDTO projetoDto, ArquivoDTO arquivoDTO) {
		return new FabricaArquivo().converterParaDTO(repositorioArquivo.obterArquivoProjeto(
				new FabricaProjeto().converterParaDominio(projetoDto),
				new FabricaArquivo().converterParaDominio(arquivoDTO)));
	}

	public ResultadoListagemEmpresaDTO obterListagemEmpresa() {
		return (ResultadoListagemEmpresaDTO) obterListagem(repositorioEmpresa.obterTodos(), new FabricaEmpresa(),
				"Empresas", new ResultadoListagemEmpresaDTO());
	}

	public ResultadoValidacaoPatrocinioDTO validarPatrocinio(PatrocinioDTO patrocinioDto) {
		return (ResultadoValidacaoPatrocinioDTO) Validador.efetuarValidacao(patrocinioDto, new FabricaPatrocinio(),
				"Patrocinio", new ResultadoValidacaoPatrocinioDTO());
	}

	public ResultadoValidacaoRecursoDisponivelDTO validarRecursoDisponivel(RecursoDisponivelDTO recursoDisponivelDto) {
		return (ResultadoValidacaoRecursoDisponivelDTO) Validador.efetuarValidacao(
				recursoDisponivelDto, new FabricaRecursoDisponivel(), "Recurso Disponível",
				new ResultadoValidacaoRecursoDisponivelDTO());
	}
}
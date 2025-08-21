package br.laramara.ti.sislaraserver.fachadas;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoEdicaoReciboDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaReciboDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ReciboDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemFilialDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemReciboDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoReciboDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Recibo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.fabricas.FabricaFilial;
import br.laramara.ti.sislaraserver.fabricas.FabricaRecibo;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoReciboEdicao;
import br.laramara.ti.sislaraserver.relatorios.ModeloRelatorio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioFilial;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRecibo;
import br.laramara.ti.sislaraserver.utilitarios.ExtensoUtils;

@Component
public class FachadaRecibo extends Fachada{
	
	private final Logger logger = Logger.getLogger(FachadaRecibo.class);
	
	@Inject
	private RepositorioRecibo repositorioRecibo;
	@Inject
	private RepositorioFilial repositorioFilial;
	
	public synchronized ResultadoEdicaoReciboDTO editarRecibo(
			ReciboDTO reciboDTO, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoReciboEdicao(
				new FabricaRecibo(), repositorioRecibo,
				reciboDTO);
		return (ResultadoEdicaoReciboDTO) verificarPermissaoEProcessar(
				operacao, Permissao.RECIBO_EDICAO,
				new ResultadoEdicaoReciboDTO(), tokenDto);
	}

	public ResultadoListagemFilialDTO obterListagemFilial() {
		return (ResultadoListagemFilialDTO) obterListagem(repositorioFilial.obterTodos(), new FabricaFilial(), "Filial",
				new ResultadoListagemFilialDTO());
	}
	
	public ResultadoListagemReciboDTO obterListagemRecibo(
			EspecificacaoPesquisaReciboDTO especificacao) {
		ResultadoListagemReciboDTO resultadoDto = new ResultadoListagemReciboDTO();
		List<Recibo> recibos = new ArrayList<>();
		try {
			if (especificacao.existeChave(ChavePesquisaDTO.TODOS_RECIBOS)) {
				recibos = repositorioRecibo.obterTodos();
			} else if (especificacao.existeChave(ChavePesquisaDTO.CPF_CNPJ)){
				recibos = repositorioRecibo
						.obterTodosPorCpfCnpj((String) especificacao.obterParametro(ChavePesquisaDTO.CPF_CNPJ));
			} else if (especificacao.existeChave(ChavePesquisaDTO.FILIAL)){
				recibos = repositorioRecibo.obterPorFilial((String)especificacao.obterParametro(ChavePesquisaDTO.FILIAL));
			} else {
				resultadoDto.adicionarErro(MENSAGEM_INSIRA_TIPO_DADO_PESQUISA);
			}
			gravarResultadoSeNaoForVazio(recibos, new FabricaRecibo(), resultadoDto);
		} catch (NumberFormatException e) {
			String erro = "Insira um parâmetro de pesquisa válido.";
			resultadoDto.adicionarErro(erro);
			logger.error(erro + "\nDetalhes: " + e);
		} catch (Exception e) {
			String erro = "Erro durante a pesquisa por Recibo.";
			resultadoDto.adicionarErro(erro);
			logger.fatal(erro + "\nDetalhes: " + e);
		}
		return resultadoDto;
	}
	
	public ArquivoDTO obterRelatorioRecibo(Long numeroRecibo, TokenDTO tokenDTO) {
		Recibo recibo = repositorioRecibo.obterPorId(numeroRecibo);
		return gerarRelatorio(Permissao.RELATORIO_RECIBO, ModeloRelatorio.RECIBO,
				gerarParametros(numeroRecibo, new ExtensoUtils(recibo.getValorTotalRecibo()).obterExtenso()), tokenDTO);
	}

	public ResultadoEdicaoReciboDTO cancelar(Long numeroRecibo, String motivoDoCancelamento, TokenDTO tokenDto) {
		ResultadoEdicaoReciboDTO resultadoEdicaoReciboDTO = new ResultadoEdicaoReciboDTO();
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);

		if (contaAcesso.possuiPermissao(Permissao.RECIBO_EDICAO)) {
			Recibo reciboACancelar = repositorioRecibo.obterPorId(numeroRecibo);
			reciboACancelar.cancelarSePossivel(motivoDoCancelamento, contaAcesso);
			if (reciboACancelar.validado()) {
				resultadoEdicaoReciboDTO.efetuadoComSucesso(
						new FabricaRecibo().converterParaDTO(repositorioRecibo.salvar(reciboACancelar)));
			} else {
				resultadoEdicaoReciboDTO.adicionarErro(reciboACancelar.obterDescricaoErros());
			}
		} else {
			resultadoEdicaoReciboDTO.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultadoEdicaoReciboDTO;
	}

	public ResultadoReciboDTO obterReciboMaisRecentePorCpfCnpf(String cpfCnpj) {
		ResultadoReciboDTO resultadoReciboDTO = new ResultadoReciboDTO();
		resultadoReciboDTO
				.efetuadoComSucesso(new FabricaRecibo().converterParaDTO(repositorioRecibo.obterMaisRecentePorCpfCnpj(cpfCnpj)));
		return resultadoReciboDTO;
	}
	
	public ReciboDTO obterValorTotalDoRecibo(String numeroRecibo) {
		ReciboDTO reciboDto = null;
		try {
			reciboDto = new FabricaRecibo().converterParaDTO(repositorioRecibo.obterPorId(Long.valueOf(numeroRecibo)));
		} catch (Exception e) {
			logger.warn("Não foi possível localizar o recibo para a captação. Detalhes: " + e);
		}
		return reciboDto;
	}
}

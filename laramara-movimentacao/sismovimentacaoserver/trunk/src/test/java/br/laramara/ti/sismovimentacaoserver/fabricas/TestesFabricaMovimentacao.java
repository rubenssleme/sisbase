package br.laramara.ti.sismovimentacaoserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Movimentacao;

public class TestesFabricaMovimentacao {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_movimentacao_converte_objeto_de_dto_para_dominio() {
		MovimentacaoDTO movimentacaoDto = ContextoMovimentacao
				.construirMovimentacaoDTO();

		Movimentacao movimentacao = new FabricaMovimentacao()
				.converterParaDominio(movimentacaoDto);

		Assert.assertEquals(movimentacao.getId(), movimentacaoDto.getId());
		Assert.assertEquals(movimentacao.getGl(), movimentacaoDto.getGl());
		Assert.assertEquals(movimentacao.getCliente(),
				movimentacaoDto.getCliente());
		Assert.assertEquals(movimentacao.getCodigoProduto(),
				movimentacaoDto.getCodigoProduto());
		Assert.assertEquals(movimentacao.getDescricao(),
				movimentacaoDto.getDescricao());
		Assert.assertEquals(movimentacao.getDescricaoProduto(),
				movimentacaoDto.getDescricaoProduto());
		Assert.assertEquals(movimentacao.getQuantidadeCor(),
				movimentacaoDto.getQuantidadeCor());
		Assert.assertEquals(movimentacao.getCor(), movimentacaoDto.getCor());
		Assert.assertEquals(movimentacao.getDirecaoFibra(),
				movimentacaoDto.getDirecaoFibra());
		Assert.assertEquals(movimentacao.getFormato(),
				movimentacaoDto.getFormato());
		Assert.assertEquals(movimentacao.getCodigoAnterior(),
				movimentacaoDto.getCodigoAnterior());
		Assert.assertEquals(movimentacao.getGramatura(),
				movimentacaoDto.getGramatura());
		Assert.assertEquals(movimentacao.getLaetus(),
				movimentacaoDto.getLaetus());
		Assert.assertEquals(movimentacao.getObsEspecificacao(),
				movimentacaoDto.getObsEspecificacao());
		Assert.assertEquals(movimentacao.getObsArte(),
				movimentacaoDto.getObsArte());
		Assert.assertEquals(movimentacao.getFibra().toString(), movimentacaoDto
				.getFibraDto().toString());
		Assert.assertEquals(movimentacao.getPapel().toString(), movimentacaoDto
				.getPapelDto().toString());
		Assert.assertEquals(movimentacao.getSangria().toString(),
				movimentacaoDto.getSangriaSimNaoDto().toString());
		Assert.assertEquals(movimentacao.getAbdb().toString(), movimentacaoDto
				.getAbdbDto().toString());
		Assert.assertEquals(movimentacao.getEspecificacao().toString(),
				movimentacaoDto.getEspecificacaoSimNaoDto().toString());
		Assert.assertEquals(movimentacao.getGr(), movimentacaoDto.getGr());
		Assert.assertEquals(movimentacao.getPasta(), movimentacaoDto.getPasta());
		Assert.assertEquals(movimentacao.getBobina(),
				movimentacaoDto.getBobina());
		Assert.assertEquals(movimentacao.getPlanaPapel(),
				movimentacaoDto.getPlanaPapel());
		Assert.assertEquals(movimentacao.getTipoProva(),
				movimentacaoDto.getTipoProva());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_movimentacao_converte_objeto_de_dominio_para_dto() {
		Movimentacao movimentacao = ContextoMovimentacao
				.fabricarComTodosOsDados();

		MovimentacaoDTO movimentacaoDto = new FabricaMovimentacao()
				.converterParaDTO(movimentacao);

		Assert.assertEquals(movimentacaoDto.getId(), movimentacao.getId());
		Assert.assertEquals(movimentacaoDto.getGl(), movimentacao.getGl());
		Assert.assertEquals(movimentacaoDto.getCliente(),
				movimentacao.getCliente());
		Assert.assertEquals(movimentacaoDto.getCodigoProduto(),
				movimentacao.getCodigoProduto());
		Assert.assertEquals(movimentacaoDto.getDescricao(),
				movimentacao.getDescricao());
		Assert.assertEquals(movimentacaoDto.getDescricaoProduto(),
				movimentacao.getDescricaoProduto());
		Assert.assertEquals(movimentacaoDto.getQuantidadeCor(),
				movimentacao.getQuantidadeCor());
		Assert.assertEquals(movimentacaoDto.getCor(), movimentacao.getCor());
		Assert.assertEquals(movimentacaoDto.getDirecaoFibra(),
				movimentacao.getDirecaoFibra());
		Assert.assertEquals(movimentacaoDto.getFormato(),
				movimentacao.getFormato());
		Assert.assertEquals(movimentacaoDto.getCodigoAnterior(),
				movimentacao.getCodigoAnterior());
		Assert.assertEquals(movimentacaoDto.getGramatura(),
				movimentacao.getGramatura());
		Assert.assertEquals(movimentacaoDto.getLaetus(),
				movimentacao.getLaetus());
		Assert.assertEquals(movimentacaoDto.getObsEspecificacao(),
				movimentacao.getObsEspecificacao());
		Assert.assertEquals(movimentacaoDto.getObsArte(),
				movimentacao.getObsArte());
		Assert.assertEquals(movimentacaoDto.getFibraDto().toString(),
				movimentacao.getFibra().toString());
		Assert.assertEquals(movimentacaoDto.getPapelDto().toString(),
				movimentacao.getPapel().toString());
		Assert.assertEquals(movimentacaoDto.getSangriaSimNaoDto().toString(),
				movimentacao.getSangria().toString());
		Assert.assertEquals(movimentacaoDto.getAbdbDto().toString(),
				movimentacao.getAbdb().toString());
		Assert.assertEquals(movimentacaoDto.getEspecificacaoSimNaoDto()
				.toString(), movimentacao.getEspecificacao().toString());
		Assert.assertEquals(movimentacaoDto.getGr(), movimentacao.getGr());
		Assert.assertEquals(movimentacaoDto.getPasta(), movimentacao.getPasta());
		Assert.assertEquals(movimentacaoDto.getBobina(),
				movimentacao.getBobina());
		Assert.assertEquals(movimentacaoDto.getPlanaPapel(),
				movimentacao.getPlanaPapel());
		Assert.assertEquals(movimentacaoDto.getTipoProva(),
				movimentacao.getTipoProva());
	}
}

package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesMovimentacaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void movimentacaodto_foi_construida_com_sucesso() {

		Long id = new Long(12222);
		StatusDTO status = new StatusDTO("ENTRADA_DO_ARQUIVO", new ClassificacaoDTO("BRANCO"));
		String gl = "GL473847";
		String cliente = "JOSEP";
		String codigoProduto = "894734";
		String descricao = "MEXICLAN";
		String descricaoProduto = "BULA ANDOLBA";
		String quantidadeCor = "1x1";
		String cor = "PRETO";
		String direcaoFibra = "VERTICAL";
		String formato = "200x300";
		String codigoAnterior = "34833";
		String gramatura = "43";
		String laetus = "4343";
		String obsEspecificacao = "Obs de esp";
		String obsArte = "Obs de arte";
		String historico = "TEXTE TE TE TE TETE";
		FibraDTO fibraDto = new FibraDTO("HORIZONTAL");
		PapelDTO papelDto = new PapelDTO("MONO");
		SimNaoDTO sangriaSimNaoDto = new SimNaoDTO("SIM");
		AbdbDTO abdbDto = new AbdbDTO("AB");
		SimNaoDTO especificacaoSimNaoDto = new SimNaoDTO("SIM");
		String gr = "GR";
		String pasta = "PASTA";
		String bobinas = "BOBISNA";
		String planaPapel = "PLANA";
		String tipoProva = "PROvA";
		
		MovimentacaoDTO movimentacaoDto = new MovimentacaoDTO();
		movimentacaoDto.setId(id);
		movimentacaoDto.setStatus(status);
		movimentacaoDto.setGl(gl);
		movimentacaoDto.setCliente(cliente);
		movimentacaoDto.setCodigoProduto(codigoProduto);
		movimentacaoDto.setDescricao(descricao);
		movimentacaoDto.setDescricaoProduto(descricaoProduto);
		movimentacaoDto.setQuantidadeCor(quantidadeCor);
		movimentacaoDto.setCor(cor);
		movimentacaoDto.setDirecaoFibra(direcaoFibra);
		movimentacaoDto.setFormato(formato);
		movimentacaoDto.setCodigoAnterior(codigoAnterior);
		movimentacaoDto.setGramatura(gramatura);
		movimentacaoDto.setFibraDto(fibraDto);
		movimentacaoDto.setPapelDto(papelDto);
		movimentacaoDto.setSangriaSimNaoDto(sangriaSimNaoDto);
		movimentacaoDto.setAbdbDto(abdbDto);
		movimentacaoDto.setEspecificacaoSimNaoDto(especificacaoSimNaoDto);
		movimentacaoDto.setLaetus(laetus);
		movimentacaoDto.setObsEspecificacao(obsEspecificacao);
		movimentacaoDto.setObsArte(obsArte);
		movimentacaoDto.setHistoricoOperacoes(historico);
		movimentacaoDto.setGr(gr);
		movimentacaoDto.setPasta(pasta);
		movimentacaoDto.setBobina(bobinas);
		movimentacaoDto.setPlanaPapel(planaPapel);
		movimentacaoDto.setTipoProva(tipoProva);

		Assert.assertEquals(movimentacaoDto.getId(), id);
		Assert.assertEquals(movimentacaoDto.getStatus().toString(),
				status.toString());
		Assert.assertEquals(movimentacaoDto.getGl(), gl);
		Assert.assertEquals(movimentacaoDto.getCliente(), cliente);
		Assert.assertEquals(movimentacaoDto.getCodigoProduto(), codigoProduto);
		Assert.assertEquals(movimentacaoDto.getDescricao(), descricao);
		Assert.assertEquals(movimentacaoDto.getDescricaoProduto(),
				descricaoProduto);
		Assert.assertEquals(movimentacaoDto.getQuantidadeCor(), quantidadeCor);
		Assert.assertEquals(movimentacaoDto.getCor(), cor);
		Assert.assertEquals(movimentacaoDto.getDirecaoFibra(), direcaoFibra);
		Assert.assertEquals(movimentacaoDto.getFormato(), formato);
		Assert.assertEquals(movimentacaoDto.getCodigoAnterior(), codigoAnterior);
		Assert.assertEquals(movimentacaoDto.getGramatura(), gramatura);
		Assert.assertEquals(movimentacaoDto.getLaetus(), laetus);
		Assert.assertEquals(movimentacaoDto.getObsEspecificacao(),
				obsEspecificacao);
		Assert.assertEquals(movimentacaoDto.getObsArte(), obsArte);
		Assert.assertEquals(movimentacaoDto.getFibraDto(), fibraDto);
		Assert.assertEquals(movimentacaoDto.getPapelDto(), papelDto);
		Assert.assertEquals(movimentacaoDto.getSangriaSimNaoDto(),
				sangriaSimNaoDto);
		Assert.assertEquals(movimentacaoDto.getAbdbDto(), abdbDto);
		Assert.assertEquals(movimentacaoDto.getEspecificacaoSimNaoDto(),
				especificacaoSimNaoDto);
		Assert.assertEquals(movimentacaoDto.getHistoricoOperacoes(), historico);
		Assert.assertEquals(movimentacaoDto.getGr(), gr);
		Assert.assertEquals(movimentacaoDto.getPasta(), pasta);
		Assert.assertEquals(movimentacaoDto.getBobina(), bobinas);
		Assert.assertEquals(movimentacaoDto.getPlanaPapel(), planaPapel);
		Assert.assertEquals(movimentacaoDto.getTipoProva(), tipoProva);
	}
}

package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Movimentacao;

public class FabricaMovimentacao extends
		FabricaRecursiva<MovimentacaoDTO, Movimentacao> {

	@Override
	public Movimentacao converterParaDominio(MovimentacaoDTO movimentacaoDto,
			Movimentacao movimentacao) {
		if (movimentacaoDto != null) {
			if (movimentacao == null) {
				movimentacao = new Movimentacao();
			}
			movimentacao.setId(movimentacaoDto.getId());
			movimentacao.setGl(movimentacaoDto.getGl());
			movimentacao.setCliente(movimentacaoDto.getCliente());
			movimentacao.setCodigoProduto(movimentacaoDto.getCodigoProduto());
			movimentacao.setDescricao(movimentacaoDto.getDescricao());
			movimentacao.setDescricaoProduto(movimentacaoDto
					.getDescricaoProduto());
			movimentacao.setQuantidadeCor(movimentacaoDto.getQuantidadeCor());
			movimentacao.setCor(movimentacaoDto.getCor());
			movimentacao.setDirecaoFibra(movimentacaoDto.getDirecaoFibra());
			movimentacao.setFormato(movimentacaoDto.getFormato());
			movimentacao.setCodigoAnterior(movimentacaoDto.getCodigoAnterior());
			movimentacao.setGramatura(movimentacaoDto.getGramatura());
			movimentacao.setLaetus(movimentacaoDto.getLaetus());
			movimentacao.setObsEspecificacao(movimentacaoDto
					.getObsEspecificacao());
			movimentacao.setObsArte(movimentacaoDto.getObsArte());
			movimentacao.setFibra(new FabricaFibra()
					.converterParaDominio(movimentacaoDto.getFibraDto()));
			movimentacao.setPapel(new FabricaPapel()
					.converterParaDominio(movimentacaoDto.getPapelDto()));
			movimentacao
					.setSangria(new FabricaSimNao()
							.converterParaDominio(movimentacaoDto
									.getSangriaSimNaoDto()));
			movimentacao.setAbdb(new FabricaAbdb()
					.converterParaDominio(movimentacaoDto.getAbdbDto()));
			movimentacao.setEspecificacao(new FabricaSimNao()
					.converterParaDominio(movimentacaoDto
							.getEspecificacaoSimNaoDto()));
			movimentacao.setGr(movimentacaoDto.getGr());
			movimentacao.setPasta(movimentacaoDto.getPasta());
			movimentacao.setBobina(movimentacaoDto.getBobina());
			movimentacao.setPlanaPapel(movimentacaoDto.getPlanaPapel());
			movimentacao.setTipoProva(movimentacaoDto.getTipoProva());
		}
		return movimentacao;
	}

	@Override
	public Movimentacao obterNovo() {
		return new Movimentacao();
	}

	@Override
	public MovimentacaoDTO converterParaDTO(Movimentacao movimentacao) {
		MovimentacaoDTO movimentacaoDto = null;
		if (movimentacao != null) {
			movimentacaoDto = new MovimentacaoDTO();
			movimentacaoDto.setId(movimentacao.getId());
		}
		movimentacaoDto.setStatus(new FabricaStatus()
				.converterParaDTO(movimentacao.obterStatusAtual()));
		movimentacaoDto.setGl(movimentacao.getGl());
		movimentacaoDto.setCliente(movimentacao.getCliente());
		movimentacaoDto.setCodigoProduto(movimentacao.getCodigoProduto());
		movimentacaoDto.setDescricao(movimentacao.getDescricao());
		movimentacaoDto.setDescricaoProduto(movimentacao.getDescricaoProduto());
		movimentacaoDto.setQuantidadeCor(movimentacao.getQuantidadeCor());
		movimentacaoDto.setCor(movimentacao.getCor());
		movimentacaoDto.setDirecaoFibra(movimentacao.getDirecaoFibra());
		movimentacaoDto.setFormato(movimentacao.getFormato());
		movimentacaoDto.setCodigoAnterior(movimentacao.getCodigoAnterior());
		movimentacaoDto.setGramatura(movimentacao.getGramatura());
		movimentacaoDto.setLaetus(movimentacao.getLaetus());
		movimentacaoDto.setObsEspecificacao(movimentacao.getObsEspecificacao());
		movimentacaoDto.setObsArte(movimentacao.getObsArte());
		movimentacaoDto.setFibraDto(new FabricaFibra()
				.converterParaDTO(movimentacao.getFibra()));
		movimentacaoDto.setPapelDto(new FabricaPapel()
				.converterParaDTO(movimentacao.getPapel()));
		movimentacaoDto.setSangriaSimNaoDto(new FabricaSimNao()
				.converterParaDTO(movimentacao.getSangria()));
		movimentacaoDto.setAbdbDto(new FabricaAbdb()
				.converterParaDTO(movimentacao.getAbdb()));
		movimentacaoDto.setEspecificacaoSimNaoDto(new FabricaSimNao()
				.converterParaDTO(movimentacao.getEspecificacao()));
		movimentacaoDto.setHistoricoOperacoes(movimentacao.getHistoricoOperacoes());
		movimentacaoDto.setGr(movimentacao.getGr());
		movimentacaoDto.setPasta(movimentacao.getPasta());
		movimentacaoDto.setBobina(movimentacao.getBobina());
		movimentacaoDto.setPlanaPapel(movimentacao.getPlanaPapel());
		movimentacaoDto.setTipoProva(movimentacao.getTipoProva());
		return movimentacaoDto;
	}

}

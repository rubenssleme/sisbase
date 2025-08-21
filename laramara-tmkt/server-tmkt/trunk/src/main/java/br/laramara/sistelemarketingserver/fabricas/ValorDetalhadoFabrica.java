package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.doacao.ValorDetalhadoDTO;
import br.laramara.sistelemarketingserver.dominio.doacao.ValorDetalhado;

public class ValorDetalhadoFabrica extends FabricaBase<ValorDetalhadoDTO, ValorDetalhado> {
	public final ValorDetalhadoDTO converterParaDTO(ValorDetalhado valorDetalhado) {
		ValorDetalhadoDTO valorDetalhadoDto = new ValorDetalhadoDTO();
		if (valorDetalhado != null) {
			valorDetalhadoDto.setId(valorDetalhado.getId());
			valorDetalhadoDto
					.setTipoRetiradaDto(new TipoRetiradaFabrica().converterParaDTO(valorDetalhado.getTipoRetirada()));
			valorDetalhadoDto.setMetodoDto(new MetodoFabrica().converterParaDTO(valorDetalhado.getMetodo()));
			valorDetalhadoDto.setDataEfetuacao(valorDetalhado.getDataEfetuacao());
			valorDetalhadoDto.setValor(valorDetalhado.getValor());
		}
		return valorDetalhadoDto;
	}

	public final ValorDetalhado converterParaDominio(ValorDetalhadoDTO valorDetalhadoDto) {
		ValorDetalhado valorDetalhado = new ValorDetalhado();
		if (valorDetalhadoDto != null) {
			valorDetalhado.setId(valorDetalhadoDto.getId());
			valorDetalhado.setTipoRetirada(
					new TipoRetiradaFabrica().converterParaDominio(valorDetalhadoDto.getTipoRetiradaDto()));
			valorDetalhado.setMetodo(new MetodoFabrica().converterParaDominio(valorDetalhadoDto.getMetodoDto()));
			valorDetalhado.setDataEfetuacao(valorDetalhadoDto.getDataEfetuacao());
			valorDetalhado.setValor(valorDetalhadoDto.getValor());
		}
		return valorDetalhado;
	}

}

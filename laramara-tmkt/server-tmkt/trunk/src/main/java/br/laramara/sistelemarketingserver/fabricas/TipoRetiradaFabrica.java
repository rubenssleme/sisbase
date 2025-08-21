package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.doacao.TipoRetiradaDTO;
import br.laramara.sistelemarketingserver.dominio.doacao.TipoRetirada;

public class TipoRetiradaFabrica extends FabricaBase<TipoRetiradaDTO, TipoRetirada> {
	public final TipoRetiradaDTO converterParaDTO(TipoRetirada tipoRetirada) {
		return tipoRetirada != null ? new TipoRetiradaDTO(tipoRetirada.toString()) : null;
	}

	public final TipoRetirada converterParaDominio(TipoRetiradaDTO tipoRetiradaDto) {
		return tipoRetiradaDto != null ? TipoRetirada.valueOf(TipoRetirada.class, tipoRetiradaDto.toString()) : null;
	}
}

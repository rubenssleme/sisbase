package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.doacao.MetodoDTO;
import br.laramara.sistelemarketingserver.dominio.doacao.Metodo;

public class MetodoFabrica extends FabricaBase<MetodoDTO, Metodo> {
	public final MetodoDTO converterParaDTO(Metodo metodo) {
		return metodo != null
				? new MetodoDTO(metodo.toString(),
						new TipoRetiradaFabrica().converterParaDTO(metodo.getTiposRetiradas()))
				: null;
	}

	public final Metodo converterParaDominio(MetodoDTO metodoDto) {
		return metodoDto != null ? Metodo.valueOf(Metodo.class, metodoDto.toString()) : null;
	}
}

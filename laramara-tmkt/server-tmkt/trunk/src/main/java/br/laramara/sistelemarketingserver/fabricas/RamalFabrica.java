package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.telefonia.RamalDTO;
import br.laramara.sistelemarketingserver.dominio.telefonia.Ramal;

public class RamalFabrica extends FabricaBase<RamalDTO, Ramal> {

	@Override
	public Ramal converterParaDominio(RamalDTO objetoDto) {
		return objetoDto != null ? Ramal.obter(objetoDto.getNumero()) : null;
	}

	@Override
	public RamalDTO converterParaDTO(Ramal objetoDominio) {
		return objetoDominio != null ? new RamalDTO(objetoDominio.getNumero()) : null;
	}
}

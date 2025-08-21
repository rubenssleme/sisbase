package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.BeneficioDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Beneficio;

public class FabricaBeneficio extends FabricaBase<BeneficioDTO, Beneficio> {

	public final BeneficioDTO converterParaDTO(Beneficio beneficio) {
		return beneficio != null ? new BeneficioDTO(beneficio.getId(),
				beneficio.getDescricao()) : null;
	}

	public final Beneficio converterParaDominio(
			BeneficioDTO beneficioDto) {
		return beneficioDto != null ? new Beneficio(beneficioDto.getId(),
				beneficioDto.toString()) : null;
	}
}
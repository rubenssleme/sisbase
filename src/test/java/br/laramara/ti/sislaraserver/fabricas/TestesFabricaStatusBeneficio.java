package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.StatusBeneficioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.StatusBeneficio;

public class TestesFabricaStatusBeneficio {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_beneficio_converte_objeto_de_dominio_para_dto() {
		StatusBeneficio status = StatusBeneficio.ENCAMINHADO;

		StatusBeneficioDTO statusDTO = new FabricaStatusBeneficio().converterParaDTO(status);

		Assert.assertEquals(statusDTO.toString(), status.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_beneficio_converte_objeto_de_dto_para_dominio() {
		StatusBeneficio statusEsperado = StatusBeneficio.ENCAMINHADO;
		StatusBeneficioDTO statusDto = new StatusBeneficioDTO(statusEsperado.toString());

		StatusBeneficio statusObtido = new FabricaStatusBeneficio().converterParaDominio(statusDto);

		Assert.assertEquals(statusObtido, statusEsperado);
	}
}

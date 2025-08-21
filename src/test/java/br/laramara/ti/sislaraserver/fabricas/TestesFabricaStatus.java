package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;

public class TestesFabricaStatus {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_converte_objeto_de_dominio_para_dto() {
		Status status = Status.CASO_NOVO;
		
		StatusDTO statusDTO = new FabricaStatus().converterParaDTO(status);
		
		Assert.assertEquals(statusDTO.toString(), status.toString());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_converte_objeto_de_dto_para_dominio() {
		Status statusEsperado = Status.DESLIGADO;
		StatusDTO statusDto = new StatusDTO(statusEsperado.toString());
		
		Status statusObtido = new FabricaStatus().converterParaDominio(statusDto);
		
		Assert.assertEquals(statusObtido, statusEsperado);
	}
}

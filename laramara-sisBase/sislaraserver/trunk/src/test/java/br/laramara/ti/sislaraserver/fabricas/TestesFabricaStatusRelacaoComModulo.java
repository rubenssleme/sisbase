package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.StatusRelacaoComModuloDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.StatusRelacaoComModulo;

public class TestesFabricaStatusRelacaoComModulo {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_relacao_com_modulo_converte_objeto_de_dominio_para_dto() {
		StatusRelacaoComModulo status = StatusRelacaoComModulo.INTEGRADO;

		StatusRelacaoComModuloDTO statusDTO = new FabricaStatusRelacaoComModulo()
				.converterParaDTO(status);

		Assert.assertEquals(statusDTO.toString(), status.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_relacao_com_modulo_converte_objeto_de_dto_para_dominio() {
		StatusRelacaoComModulo statusEsperado = StatusRelacaoComModulo.INTEGRADO;
		StatusRelacaoComModuloDTO statusDto = new StatusRelacaoComModuloDTO(
				statusEsperado.toString());

		StatusRelacaoComModulo statusObtido = new FabricaStatusRelacaoComModulo()
				.converterParaDominio(statusDto);

		Assert.assertEquals(statusObtido, statusEsperado);
	}
}

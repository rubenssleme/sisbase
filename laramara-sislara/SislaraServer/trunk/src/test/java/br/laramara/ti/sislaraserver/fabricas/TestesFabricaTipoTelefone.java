package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.TipoTelefoneDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.TipoTelefone;

public class TestesFabricaTipoTelefone {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipotelefone_converte_objeto_de_dominio_para_dto() {
		TipoTelefone tipoTelefone = TipoTelefone.RESIDENCIAL;

		TipoTelefoneDTO tipoTelefoneDTO = new FabricaTipoTelefone()
				.converterParaDTO(tipoTelefone);

		Assert.assertEquals(tipoTelefoneDTO.toString(), tipoTelefone.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipotelefone_converte_objeto_de_dto_para_dominio() {
		TipoTelefone tipoTelefoneEsperado = TipoTelefone.COMERCIAL;
		TipoTelefoneDTO tipoTelefoneDto = new TipoTelefoneDTO(
				tipoTelefoneEsperado.toString());

		TipoTelefone tipoTelefoneObtido = new FabricaTipoTelefone()
				.converterParaDominio(tipoTelefoneDto);

		Assert.assertEquals(tipoTelefoneObtido, tipoTelefoneEsperado);
	}
}

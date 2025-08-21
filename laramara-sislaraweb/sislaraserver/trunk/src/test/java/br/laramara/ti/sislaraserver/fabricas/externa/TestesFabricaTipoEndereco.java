package br.laramara.ti.sislaraserver.fabricas.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.TipoEnderecoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.endereco.TipoEndereco;

public class TestesFabricaTipoEndereco {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipo_endereco_converte_objeto_de_dominio_para_dto() {
		TipoEndereco comercial = TipoEndereco.COMERCIAL;

		TipoEnderecoDTO tipoEnderecoDto = new FabricaTipoEndereco().converterParaDTO(comercial);

		Assert.assertEquals(tipoEnderecoDto.toString(), comercial.toString());
	}
}

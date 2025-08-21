package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.PaisDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.endereco.Pais;

public class TestesFabricaPais {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_pais_converte_objeto_de_dominio_para_dto() {
		Pais pais = new Pais(new Long(1), "Brasil");

		PaisDTO paisDTO = new FabricaPais().converterParaDTO(pais);

		Assert.assertEquals(pais.getId(), paisDTO.getId());
		Assert.assertEquals(pais.getNome(), paisDTO.toString());
	}
}

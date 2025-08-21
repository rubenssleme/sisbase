package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;

public class TestesFabricaUf {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_uf_converte_objeto_de_dominio_para_dto() {
		UF uf = UF.PA;
		
		UfDTO ufDTO = new FabricaUf().converterParaDTO(uf);
		
		Assert.assertEquals(ufDTO.toString(), uf.toString());
	}
}

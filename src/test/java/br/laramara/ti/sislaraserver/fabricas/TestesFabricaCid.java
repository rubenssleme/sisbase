package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.CidDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Cid;

public class TestesFabricaCid {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_cid_converte_objeto_de_dominio_para_dto() {
		Cid cid = new Cid("CID12", "Qualquer descrilção");

		CidDTO cidDTO = new FabricaCid().converterParaDTO(cid);

		Assert.assertEquals(cidDTO.getId(), cid.getId());
		Assert.assertEquals(cidDTO.toString(), cid.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_cid_converte_objeto_de_dto_para_dominio() {
		String id = "CID12";
		String descricao = "Descrição do Cid";

		CidDTO cidDto = new CidDTO(id, descricao);

		Cid cidObtido = new FabricaCid().converterParaDominio(cidDto);

		Assert.assertEquals(cidObtido.getDescricao(), descricao);
		Assert.assertEquals(cidObtido.getId(), id);
	}
}

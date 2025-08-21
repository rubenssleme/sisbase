package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ConvenioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Convenio;

public class TestesFabricaConvenio {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_convenio_converte_objeto_de_dominio_para_dto() {
		Convenio convenio = new Convenio(new Long(12222), "SESC");

		ConvenioDTO convenioDTO = new FabricaConvenio()
				.converterParaDTO(convenio);

		Assert.assertEquals(convenioDTO.getId(), convenio.getId());
		Assert.assertEquals(convenioDTO.toString(), convenio.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_convenio_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(122222);
		String descricao = "SESC";

		ConvenioDTO convenioDto = new ConvenioDTO(id, descricao);

		Convenio convenioObtido = new FabricaConvenio()
				.converterParaDominio(convenioDto);

		Assert.assertEquals(convenioObtido.getDescricao(), descricao);
		Assert.assertEquals(convenioObtido.getId(), id);
	}
}

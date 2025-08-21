package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.DoencaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Doenca;

public class TestesFabricaDoenca {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_doenca_converte_objeto_de_dominio_para_dto() {
		Doenca doenca = new Doenca(new Long(12222), "AUDITIVA");

		DoencaDTO doencaDTO = new FabricaDoenca().converterParaDTO(doenca);

		Assert.assertEquals(doencaDTO.getId(), doenca.getId());
		Assert.assertEquals(doencaDTO.toString(), doenca.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_doenca_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(122222);
		String descricao = "AUDITIVA";

		DoencaDTO doencaDto = new DoencaDTO(id, descricao);

		Doenca doencaObtido = new FabricaDoenca()
				.converterParaDominio(doencaDto);

		Assert.assertEquals(doencaObtido.getDescricao(), descricao);
		Assert.assertEquals(doencaObtido.getId(), id);
	}
}

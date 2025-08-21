package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.familiar.ParentescoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.familiar.Parentesco;

public class TestesFabricaParentesco {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_parentesco_converte_objeto_de_dominio_para_dto() {
		Parentesco parentesco = new Parentesco(new Long(1), "PAI");

		ParentescoDTO parentescoDTO = new FabricaParentesco()
				.converterParaDTO(parentesco);

		Assert.assertEquals(parentescoDTO.toString(), parentesco.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_parentesco_converte_objeto_de_dto_para_dominio() {
		Parentesco parentescoEsperada = new Parentesco(new Long(1), "PAI");
		ParentescoDTO parentescoDto = new ParentescoDTO(new Long(1),
				parentescoEsperada.toString());

		Parentesco parentescoObtido = new FabricaParentesco()
				.converterParaDominio(parentescoDto);

		Assert.assertEquals(parentescoObtido, parentescoEsperada);
	}
}

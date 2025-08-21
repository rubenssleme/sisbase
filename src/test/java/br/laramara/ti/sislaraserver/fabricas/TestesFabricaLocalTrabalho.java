package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.trabalho.LocalTrabalhoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.trabalho.LocalTrabalho;

public class TestesFabricaLocalTrabalho {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_local_trabalho_converte_objeto_de_dominio_para_dto() {
		LocalTrabalho localTrabalho = new LocalTrabalho(new Long(1), "CTIS");

		LocalTrabalhoDTO localTrabalhoDTO = new FabricaLocalTrabalho().converterParaDTO(localTrabalho);

		Assert.assertEquals(localTrabalho.getId(), localTrabalhoDTO.getId());
		Assert.assertEquals(localTrabalho.getNome(), localTrabalhoDTO.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_cargo_converte_objeto_de_dto_para_dominio() {
		LocalTrabalhoDTO localTrabalhoDto = new LocalTrabalhoDTO(new Long(1), "CTIS");

		LocalTrabalho localTrabalho = new FabricaLocalTrabalho().converterParaDominio(localTrabalhoDto);

		Assert.assertEquals(localTrabalho.getId(), localTrabalhoDto.getId());
		Assert.assertEquals(localTrabalho.getNome(), localTrabalhoDto.toString());
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;

public class TestesFabricaSetor {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_setor_converte_objeto_de_dominio_para_dto() {
		Setor setor = Setor.CTO;

		SetorDTO setorDTO = new FabricaSetor().converterParaDTO(setor);

		Assert.assertEquals(setorDTO.toString(), setor.toString());
	}
}

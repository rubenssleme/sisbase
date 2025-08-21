package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.EstadoCivilDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.EstadoCivil;

public class TestesFabricaEstadoCivil {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_estadocivil_converte_objeto_de_dominio_para_dto() {
		EstadoCivil estadoCivil = new EstadoCivil(new Long(1), "SOLTEIRO");

		EstadoCivilDTO estadoCivilDTO = new FabricaEstadoCivil()
				.converterParaDTO(estadoCivil);

		Assert.assertEquals(estadoCivilDTO.getId(), estadoCivil.getId());
		Assert.assertEquals(estadoCivilDTO.toString(), estadoCivil.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_estadocivil_converte_objeto_de_dto_para_dominio() {
		EstadoCivil estadoCivilEsperada = new EstadoCivil(new Long(1), "CASADO");
		EstadoCivilDTO estadoCivilDto = new EstadoCivilDTO(new Long(1),
				estadoCivilEsperada.toString());

		EstadoCivil estadoCivilObtido = new FabricaEstadoCivil()
				.converterParaDominio(estadoCivilDto);

		Assert.assertEquals(estadoCivilObtido, estadoCivilEsperada);
	}
}

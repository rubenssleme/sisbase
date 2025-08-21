package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.EtiologiaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Etiologia;

public class TestesFabricaEtiologia {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_etiologia_converte_objeto_de_dominio_para_dto() {
		Etiologia etiologia = ContextoEtiologia.construirEtiologia();

		EtiologiaDTO etiologiaDTO = new FabricaEtiologia()
				.converterParaDTO(etiologia);

		Assert.assertEquals(etiologiaDTO.getId(), etiologia.getId());
		Assert.assertEquals(etiologiaDTO.getCidDto().getId(), etiologia
				.getCid().getId());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_etiologia_converte_objeto_de_dto_para_dominio() {
		EtiologiaDTO etiologiaDto = ContextoEtiologia.construirEtiologiaDTO();

		Etiologia etiologia = new FabricaEtiologia()
				.converterParaDominio(etiologiaDto);

		Assert.assertEquals(etiologia.getId(), etiologiaDto.getId());
		Assert.assertEquals(etiologia.getCid().getId(), etiologiaDto
				.getCidDto().getId());
	}
}

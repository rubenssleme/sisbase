package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.GrupoEtnicoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.GrupoEtnico;

public class TestesFabricaGrupoEtnico {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_grupoetnico_converte_objeto_de_dominio_para_dto() {
		GrupoEtnico gruupoEtnico = GrupoEtnico.BRANCA;

		GrupoEtnicoDTO grupoEtnicoDTO = new FabricaGrupoEtnico()
				.converterParaDTO(gruupoEtnico);

		Assert.assertEquals(grupoEtnicoDTO.toString(), gruupoEtnico.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_grupoetnico_converte_objeto_de_dto_para_dominio() {
		GrupoEtnico grupoEtnicoEsperada = GrupoEtnico.BRANCA;
		GrupoEtnicoDTO grupoEtnicoDto = new GrupoEtnicoDTO(
				grupoEtnicoEsperada.toString());

		GrupoEtnico grupoEtnicoObtido = new FabricaGrupoEtnico()
				.converterParaDominio(grupoEtnicoDto);

		Assert.assertEquals(grupoEtnicoObtido, grupoEtnicoEsperada);
	}
}

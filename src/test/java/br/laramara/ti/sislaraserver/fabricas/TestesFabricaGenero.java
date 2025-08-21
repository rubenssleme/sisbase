package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.GeneroDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Genero;

public class TestesFabricaGenero {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_genero_converte_objeto_de_dominio_para_dto() {
		Genero genero = Genero.MASCULINO;

		GeneroDTO generoDTO = new FabricaGenero().converterParaDTO(genero);

		Assert.assertEquals(generoDTO.toString(), genero.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_genero_converte_objeto_de_dto_para_dominio() {
		Genero generoEsperado = Genero.MASCULINO;
		GeneroDTO generoDto = new GeneroDTO(generoEsperado.toString());

		Genero generoObtido = new FabricaGenero().converterParaDominio(generoDto);

		Assert.assertEquals(generoObtido, generoEsperado);
	}
}

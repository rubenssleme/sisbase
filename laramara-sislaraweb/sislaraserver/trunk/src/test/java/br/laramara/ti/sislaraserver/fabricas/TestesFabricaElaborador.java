package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.solicitacao.ElaboradorDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.solicitacao.Elaborador;

public class TestesFabricaElaborador {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_elaborador_converte_objeto_de_dominio_para_dto() {
		Elaborador elaborador = Elaborador.OFTALMOLOGIA;

		ElaboradorDTO elaboradorDTO = new FabricaElaborador()
				.converterParaDTO(elaborador);

		Assert.assertEquals(elaboradorDTO.toString(), elaborador.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_elaborador_converte_objeto_de_dto_para_dominio() {
		Elaborador elaboradorEsperado = Elaborador.ORTOPTICA;
		ElaboradorDTO elaboradorDto = new ElaboradorDTO(
				elaboradorEsperado.toString());

		Elaborador elaboradorObtido = new FabricaElaborador()
				.converterParaDominio(elaboradorDto);

		Assert.assertEquals(elaboradorObtido, elaboradorEsperado);
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;

public class TestesFabricaModulo {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_modulo_converte_objeto_de_dto_para_dominio() {
		ModuloDTO moduloDto = ContextoModulo.construirModuloDTO();

		Modulo modulo = new FabricaModulo().converterParaDominio(moduloDto);

		Assert.assertEquals(modulo.getId(), moduloDto.getId());
		Assert.assertEquals(modulo.getNome(), moduloDto.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_modulo_converte_objeto_de_dominio_para_dto() {
		Modulo modulo = ContextoModulo.fabricarComTodosOsDados();

		ModuloDTO moduloDto = new FabricaModulo().converterParaDTO(modulo);

		Assert.assertEquals(modulo.getId(), moduloDto.getId());
		Assert.assertEquals(modulo.getNome(), moduloDto.toString());
	}
}
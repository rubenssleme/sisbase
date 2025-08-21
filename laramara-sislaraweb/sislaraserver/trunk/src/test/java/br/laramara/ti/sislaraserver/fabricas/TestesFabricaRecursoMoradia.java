package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.RecursoMoradiaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.RecursoMoradia;

public class TestesFabricaRecursoMoradia {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_recurso_moradia_converte_objeto_de_dto_para_dominio() {
		RecursoMoradiaDTO recursoMoradiaDto = ContextoRecursoMoradia.construirRecursoMoradiaDTO();

		RecursoMoradia recursoMoradia = new FabricaRecursoMoradia().converterParaDominio(recursoMoradiaDto);

		Assert.assertEquals(recursoMoradia.getId(), recursoMoradiaDto.getId());
		Assert.assertEquals(recursoMoradia.getDescricao(), recursoMoradiaDto.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_recurso_moradia_converte_objeto_de_dominio_para_dto() {
		RecursoMoradia recursoMoradia = ContextoRecursoMoradia.fabricarRecursoMoradiaComTodosOsDados();

		RecursoMoradiaDTO recursoMoradiaDto = new FabricaRecursoMoradia().converterParaDTO(recursoMoradia);

		Assert.assertEquals(recursoMoradiaDto.getId(), recursoMoradia.getId());
		Assert.assertEquals(recursoMoradiaDto.toString(), recursoMoradia.getDescricao());
	}
}

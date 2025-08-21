package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.RecursoDisponivelDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.RecursoDisponivel;

public class TestesFabricaRecursoDisponivel {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_recurso_disponivel_converte_objeto_de_dominio_para_dto() {

		RecursoDisponivel recursoDisponivel = ContextoRecursoDisponivel.fabricarComTodosOsDados();

		RecursoDisponivelDTO recursoDisponivelDTO = new FabricaRecursoDisponivel().converterParaDTO(recursoDisponivel);

		Assert.assertEquals(recursoDisponivelDTO.getId(), recursoDisponivel.getId());
		Assert.assertEquals(recursoDisponivelDTO.getRecursoDto().getId(), recursoDisponivel.getRecurso().getId());
		Assert.assertEquals(recursoDisponivelDTO.getQuantidade(), recursoDisponivel.getQuantidade());
		Assert.assertEquals(recursoDisponivelDTO.getValorUnitario(), recursoDisponivel.getValorUnitario());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_recurso_disponivel_converte_objeto_de_dto_para_dominio() {
		RecursoDisponivelDTO recursoDisponivelDTO = ContextoRecursoDisponivel.fabricarComTodosOsDadosDTO();

		RecursoDisponivel recursoDisponivel = new FabricaRecursoDisponivel().converterParaDominio(recursoDisponivelDTO);

		Assert.assertEquals(recursoDisponivel.getId(), recursoDisponivelDTO.getId());
		Assert.assertEquals(recursoDisponivel.getRecurso().getId(), recursoDisponivelDTO.getRecursoDto().getId());
		Assert.assertEquals(recursoDisponivel.getQuantidade(), recursoDisponivelDTO.getQuantidade());
		Assert.assertEquals(recursoDisponivel.getValorUnitario(), recursoDisponivelDTO.getValorUnitario());
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;

public class TestesFabricaRecurso {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_recurso_converte_objeto_de_dto_para_dominio() {
		RecursoDTO recursoDto = ContextoRecurso
				.construirRecursoDTO();

		Recurso recurso = new FabricaRecurso()
				.converterParaDominio(recursoDto);

		Assert.assertEquals(recurso.getId(),
				recursoDto.getId());
		Assert.assertEquals(recurso.getDescricao(),
				recursoDto.toString());
		Assert.assertEquals(recurso.obterValor(), recursoDto.getValor());
		Assert.assertEquals(recurso.eCartelaDeSelos(), recursoDto.isCartelaDeSelos());
		Assert.assertEquals(recurso.isDisponivelParaDemanda(), recursoDto.isDisponivelParaDemanda());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_recurso_converte_objeto_de_dominio_para_dto() {
		Recurso recurso = ContextoRecurso
				.fabricarRecursoComTodosOsDados();

		RecursoDTO recursoDto = new FabricaRecurso()
				.converterParaDTO(recurso);

		Assert.assertEquals(recursoDto.getId(),
				recurso.getId());
		Assert.assertEquals(recursoDto.toString(),
				recurso.getDescricao());
		Assert.assertEquals(recursoDto.isCartelaDeSelos(), recurso.eCartelaDeSelos());
		Assert.assertEquals(recursoDto.getValor(), recurso.obterValor());
		Assert.assertEquals(recursoDto.isDisponivelParaDemanda(), recurso.isDisponivelParaDemanda());
	}

}

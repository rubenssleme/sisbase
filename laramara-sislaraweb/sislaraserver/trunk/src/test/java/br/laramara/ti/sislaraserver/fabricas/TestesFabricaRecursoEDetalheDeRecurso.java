package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.RecursoEDescricaoRecursoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.RecursoEDescricaoRecurso;

public class TestesFabricaRecursoEDetalheDeRecurso {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_recurso_e_descricao_de_recurso_converte_objeto_de_dto_para_dominio() {
		RecursoEDescricaoRecursoDTO recursoEDescricaoRecuroDto = ContextoRecursoDescricaoRecurso.criarAlternativoDto();

		RecursoEDescricaoRecurso recursoEDescricaoRecurso = new FabricaRecursoEDescricaoDeRecurso()
				.converterParaDominio(recursoEDescricaoRecuroDto);

		Assert.assertEquals(recursoEDescricaoRecurso.getRecurso().getId(),
				recursoEDescricaoRecuroDto.getRecursoDTO().getId());
		Assert.assertEquals(recursoEDescricaoRecurso.getDescricaoRecurso().getId(),
				recursoEDescricaoRecuroDto.getDescricaoRecursoDTO().getId());
	}
}

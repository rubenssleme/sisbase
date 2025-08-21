package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.DescricaoRecursoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.DescricaoRecurso;

public class TestesFabricaDescricaoRecurso {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_descricao_recurso_converte_objeto_de_dominio_para_dto() {

		DescricaoRecurso descricaoRecurso = ContextoDescricaoRecurso.construirDescricaoRecurso();

		DescricaoRecursoDTO descricaoRecursoDtoCovertido = new FabricaDescricaoRecurso()
				.converterParaDTO(descricaoRecurso);

		Assert.assertEquals(descricaoRecursoDtoCovertido.getId(), descricaoRecurso.getId());
		Assert.assertEquals(descricaoRecursoDtoCovertido.toString(), descricaoRecurso.getDescricao());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_descricao_recurso_converte_objeto_dto_para_dominio() {

		DescricaoRecursoDTO descricaoRecursoDto = ContextoDescricaoRecurso.construirDescricaoRecursoDTO();

		DescricaoRecurso descricaoRecursoCovertido = new FabricaDescricaoRecurso()
				.converterParaDominio(descricaoRecursoDto);

		Assert.assertEquals(descricaoRecursoCovertido.getId(), descricaoRecursoDto.getId());
		Assert.assertEquals(descricaoRecursoCovertido.getDescricao(), descricaoRecursoDto.toString());
	}
}

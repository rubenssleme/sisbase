package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.PossuiNecessitaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.PossuiNecessita;

public class TestesFabricaRelacaoRecurso {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_relacao_recurso_converte_objeto_de_dto_para_dominio() {
		PossuiNecessitaDTO relacaoRecursoDto = new PossuiNecessitaDTO(PossuiNecessita.NECESSITA.toString());

		PossuiNecessita relacaoRecurso = new FabricaPossuiNecessita().converterParaDominio(relacaoRecursoDto);

		Assert.assertEquals(relacaoRecurso.toString(), relacaoRecursoDto.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_relacao_recurso_converte_objeto_de_dominio_para_dto() {
		PossuiNecessita relacaoRecurso = PossuiNecessita.POSSUI;

		PossuiNecessitaDTO relacaoRecursoDto = new FabricaPossuiNecessita().converterParaDTO(relacaoRecurso);

		Assert.assertEquals(relacaoRecursoDto.toString(), relacaoRecurso.toString());
	}
}

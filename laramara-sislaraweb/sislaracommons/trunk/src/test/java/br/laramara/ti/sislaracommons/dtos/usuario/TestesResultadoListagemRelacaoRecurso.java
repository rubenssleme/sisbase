package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemRelacaoRecurso {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_listagem_relacao_recurso_dto_sem_erro_foi_construido() {
		List<PossuiNecessitaDTO> relacaoRecurso = Arrays.asList(new PossuiNecessitaDTO("TESTE"));
		ResultadoListagemRelacaoRecursoDTO resultadoListagemRelacaoRecursoDto = new ResultadoListagemRelacaoRecursoDTO();
		resultadoListagemRelacaoRecursoDto.efetuadoComSucesso(relacaoRecurso);

		Assert.assertTrue(resultadoListagemRelacaoRecursoDto.sucesso());
		Assert.assertFalse(resultadoListagemRelacaoRecursoDto.getObjetosDto().isEmpty());
	}
}

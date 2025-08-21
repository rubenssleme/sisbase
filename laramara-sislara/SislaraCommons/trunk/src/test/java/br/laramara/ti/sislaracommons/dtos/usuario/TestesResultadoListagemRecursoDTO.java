package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemRecursoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemRecursoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemrecursodto_sem_erro_foi_construido() {
		List<RecursoDTO> recursoDto = new ArrayList<>();
		recursoDto.add(new RecursoDTO(new Long(3333), "Bengala", false, "100,00"));
		ResultadoListagemRecursoDTO resultadoListagemRecursoDto = new ResultadoListagemRecursoDTO();
		resultadoListagemRecursoDto.efetuadoComSucesso(recursoDto);

		Assert.assertTrue(resultadoListagemRecursoDto.sucesso());
		Assert.assertFalse(resultadoListagemRecursoDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemrecursodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemRecursoDTO resultadoListagemRecursoDto = new ResultadoListagemRecursoDTO();
		resultadoListagemRecursoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemRecursoDto.sucesso());
		Assert.assertNotNull(resultadoListagemRecursoDto.obterMensagens());
	}

}

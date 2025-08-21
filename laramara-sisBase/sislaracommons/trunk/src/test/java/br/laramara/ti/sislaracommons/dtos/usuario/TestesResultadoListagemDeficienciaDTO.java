package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemDeficienciaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemdeficienciadto_sem_erro_foi_construido() {
		List<DeficienciaDTO> deficienciaDto = new ArrayList<>();
		deficienciaDto.add(new DeficienciaDTO(new Long(12222), "AUDITIVA", true));
		ResultadoListagemDeficienciaDTO resultadoListagemDeficienciaDto = new ResultadoListagemDeficienciaDTO();
		resultadoListagemDeficienciaDto.efetuadoComSucesso(deficienciaDto);

		Assert.assertTrue(resultadoListagemDeficienciaDto.sucesso());
		Assert.assertFalse(resultadoListagemDeficienciaDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemdeficienciadto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemDeficienciaDTO resultadoListagemDeficienciaDto = new ResultadoListagemDeficienciaDTO();
		resultadoListagemDeficienciaDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemDeficienciaDto.sucesso());
		Assert.assertNotNull(resultadoListagemDeficienciaDto.obterMensagens());
	}

}

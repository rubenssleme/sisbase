package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemSetorDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemsetordto_sem_erro_foi_construido() {
		List<SetorDTO> setoresDto = new ArrayList<>();
		setoresDto.add(new SetorDTO("CTO"));
		ResultadoListagemSetorDTO resultadoListagemSetorDto = new ResultadoListagemSetorDTO();
		resultadoListagemSetorDto.efetuadoComSucesso(setoresDto);

		Assert.assertTrue(resultadoListagemSetorDto.sucesso());
		Assert.assertFalse(resultadoListagemSetorDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemsetordto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemSetorDTO resultadoListagemSetorDto = new ResultadoListagemSetorDTO();
		resultadoListagemSetorDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemSetorDto.sucesso());
		Assert.assertNotNull(resultadoListagemSetorDto.obterMensagens());
	}
}

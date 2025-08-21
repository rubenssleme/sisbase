package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemEstadoCivilDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemestadocivildto_sem_erro_foi_construido() {
		List<EstadoCivilDTO> estadosCivisDto = new ArrayList<>();
		estadosCivisDto.add(new EstadoCivilDTO(new Long(1), "SOLTEIRO"));

		ResultadoListagemEstadoCivilDTO resultadoListagemEstadoCivilDto = new ResultadoListagemEstadoCivilDTO();
		resultadoListagemEstadoCivilDto.efetuadoComSucesso(estadosCivisDto);

		Assert.assertTrue(resultadoListagemEstadoCivilDto.sucesso());
		Assert.assertFalse(resultadoListagemEstadoCivilDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemestadocivildto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemEstadoCivilDTO resultadoListagemEstadoCivilDto = new ResultadoListagemEstadoCivilDTO();
		resultadoListagemEstadoCivilDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemEstadoCivilDto.sucesso());
		Assert.assertNotNull(resultadoListagemEstadoCivilDto.obterMensagens());
	}
}

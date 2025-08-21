package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.ZonaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemZonaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemzonadto_sem_erro_foi_construido() {
		List<ZonaDTO> zonas = new ArrayList<>();
		zonas.add(new ZonaDTO("NORTE"));
		ResultadoListagemZonaDTO resultadoListagemZonaDto = new ResultadoListagemZonaDTO();
		resultadoListagemZonaDto.efetuadoComSucesso(zonas);

		Assert.assertTrue(resultadoListagemZonaDto.sucesso());
		Assert.assertFalse(resultadoListagemZonaDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemzonadto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemZonaDTO resultadoListagemZonaDto = new ResultadoListagemZonaDTO();
		resultadoListagemZonaDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemZonaDto.sucesso());
		Assert.assertNotNull(resultadoListagemZonaDto.obterMensagens());
	}
}

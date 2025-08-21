package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemUfDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemufdto_sem_erro_foi_construido() {
		List<UfDTO> ufsDto = new ArrayList<>();
		ufsDto.add(new UfDTO("PA"));
		ResultadoListagemUfDTO resultadoListagemUfDto = new ResultadoListagemUfDTO();
		resultadoListagemUfDto.efetuadoComSucesso(ufsDto);

		Assert.assertTrue(resultadoListagemUfDto.sucesso());
		Assert.assertFalse(resultadoListagemUfDto.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemufdto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemUfDTO resultadoListagemUfDto = new ResultadoListagemUfDTO();
		resultadoListagemUfDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemUfDto.sucesso());
		Assert.assertNotNull(resultadoListagemUfDto.obterMensagens());
	}
}

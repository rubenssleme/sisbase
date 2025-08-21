package br.laramara.ti.sislaracommons.dtos.endereco;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemMunicipioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemMunicipioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemmunicipiodto_sem_erro_foi_construido() {
		List<MunicipioDTO> municipiosDto = new ArrayList<>();
		municipiosDto.add(new MunicipioDTO(new Long(4850), "São Paulo", new UfDTO(
				"SP")));
		ResultadoListagemMunicipioDTO resultadoListagemMunicipioDto = new ResultadoListagemMunicipioDTO();
		resultadoListagemMunicipioDto.efetuadoComSucesso(municipiosDto);

		Assert.assertTrue(resultadoListagemMunicipioDto.sucesso());
		Assert.assertFalse(resultadoListagemMunicipioDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemmunicipiodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemMunicipioDTO resultadoListagemMunicipioDto = new ResultadoListagemMunicipioDTO();
		resultadoListagemMunicipioDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemMunicipioDto.sucesso());
		Assert.assertNotNull(resultadoListagemMunicipioDto.obterMensagens());
	}

}

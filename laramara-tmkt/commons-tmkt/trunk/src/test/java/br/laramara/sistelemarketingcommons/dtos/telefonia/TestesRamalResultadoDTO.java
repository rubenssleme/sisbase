package br.laramara.sistelemarketingcommons.dtos.telefonia;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesRamalResultadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void ramal_resultado_dto_foi_construida_com_sucesso() {

		List<RamalDTO> ramaisDTO = Arrays.asList(new RamalDTO("6489"), new RamalDTO("6459"));

		RamalResultadoDTO ramaisResultadoDTO = new RamalResultadoDTO();
		ramaisResultadoDTO.efetuadoComSucesso(ramaisDTO);

		Assert.assertEquals(ramaisResultadoDTO.getRamaisDto().size(), ramaisResultadoDTO.getRamaisDto().size());
	}
}

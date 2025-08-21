package br.laramara.sistelemarketingcommons.dtos;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesBairroResultadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void bairro_resultado_listagem_dto_foi_construida_com_sucesso() {

		List<BairroDTO> bairrosDTO = Arrays.asList(ContextoBairroDTO.construir());

		BairroResultadoDTO bairroResultadoDTO = new BairroResultadoDTO();
		bairroResultadoDTO.efetuadoComSucesso(bairrosDTO);

		Assert.assertEquals(bairroResultadoDTO.getBairrosDto().size(), bairroResultadoDTO.getBairrosDto().size());
	}
}

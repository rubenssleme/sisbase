package br.laramara.sistelemarketingcommons.dtos;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesMunicipioResultadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void municipio_resultado_listagem_dto_foi_construida_com_sucesso() {
	
		List<MunicipioDTO> municipiosDTO  = Arrays.asList(ContextoMunicipioDTO.construir());
		
		MunicipioResultadoDTO estadoResultadoDTO = new MunicipioResultadoDTO();
		estadoResultadoDTO.efetuadoComSucesso(municipiosDTO);

		Assert.assertEquals(estadoResultadoDTO.getMunicipiosDto().size(),
				estadoResultadoDTO.getMunicipiosDto().size());
	}
}

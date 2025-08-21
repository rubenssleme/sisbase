package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoConsultaUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.externo.UsuarioExternoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoConsultaUsuarioExternoDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoconsultausuarioexternodto_foi_construido_com_sucesso() {
		String nomeCompleto = "1234";

		UsuarioExternoDTO usuarioExternoDto = new UsuarioExternoDTO();

		usuarioExternoDto.setNomeCompleto(nomeCompleto);

		ResultadoConsultaUsuarioExternoDTO resultadoConsultaUsuarioExternoDTO = new ResultadoConsultaUsuarioExternoDTO();

		resultadoConsultaUsuarioExternoDTO.efetuadoComSucesso(usuarioExternoDto);

		Assert.assertTrue(resultadoConsultaUsuarioExternoDTO.sucesso());
		Assert.assertNotNull(resultadoConsultaUsuarioExternoDTO.getUsuarioExternoDto());
		Assert.assertNotNull(resultadoConsultaUsuarioExternoDTO.getUsuarioExternoDto().getNomeCompleto(), nomeCompleto);
	}

}

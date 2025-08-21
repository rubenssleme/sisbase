package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.externo.UsuarioExternoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesSolicitacaoEdicaoUsuarioExternoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacaoedicaousuarioexternodto_foi_construido_com_sucesso() {		
		SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDto = new SolicitacaoEdicaoUsuarioExternoDTO();
		
		solicitacaoEdicaoUsuarioExternoDto.setUsuarioExternoDto(new UsuarioExternoDTO());
		solicitacaoEdicaoUsuarioExternoDto.setToken("");		

		Assert.assertFalse(solicitacaoEdicaoUsuarioExternoDto.toString().isEmpty());
		Assert.assertNotNull(solicitacaoEdicaoUsuarioExternoDto.getUsuarioExternoDto());
		Assert.assertTrue(solicitacaoEdicaoUsuarioExternoDto.getToken().isEmpty());
	}

}

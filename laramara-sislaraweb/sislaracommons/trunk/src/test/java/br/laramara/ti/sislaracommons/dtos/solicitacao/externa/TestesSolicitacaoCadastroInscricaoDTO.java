package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.inscricao.InscricaoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesSolicitacaoCadastroInscricaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacaocadastroinscricaodto_foi_construido_com_sucesso() {
		SolicitacaoCadastroInscricaoDTO solicitacaoCadastroInscricaoDto = new SolicitacaoCadastroInscricaoDTO();

		solicitacaoCadastroInscricaoDto.setInscricaoDto(new InscricaoDTO());
		solicitacaoCadastroInscricaoDto.setToken(new TokenDTO(UUID.randomUUID().toString()).getToken());

		Assert.assertFalse(solicitacaoCadastroInscricaoDto.toString().isEmpty());
		Assert.assertNotNull(solicitacaoCadastroInscricaoDto.getInscricaoDto());
		Assert.assertNotNull(solicitacaoCadastroInscricaoDto.getToken());
	}
}

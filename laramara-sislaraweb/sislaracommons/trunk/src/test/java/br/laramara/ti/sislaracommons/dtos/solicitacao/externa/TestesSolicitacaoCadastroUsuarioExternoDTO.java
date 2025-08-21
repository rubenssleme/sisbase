package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesSolicitacaoCadastroUsuarioExternoDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacaocadastrousuarioexternodto_foi_construido_com_sucesso() {
		String nomeCompleto = "Carlos Henrique da Silva Kafka";
		String telefoneCelular = "(11)981847597";
		String email = "carloskafka7@gmail.com";
		boolean autorizoRecebimentoInformativo = true;
		
		SolicitacaoCadastroUsuarioExternoDTO solicitacaoCadastroUsuarioExternoDTO = new SolicitacaoCadastroUsuarioExternoDTO(
				nomeCompleto, telefoneCelular, email, autorizoRecebimentoInformativo);

		Assert.assertFalse(solicitacaoCadastroUsuarioExternoDTO.toString().isEmpty());
		Assert.assertEquals(nomeCompleto, solicitacaoCadastroUsuarioExternoDTO.getNomeCompleto());
		Assert.assertEquals(telefoneCelular, solicitacaoCadastroUsuarioExternoDTO.getDddETelefone());
		Assert.assertEquals(email, solicitacaoCadastroUsuarioExternoDTO.getEmail());
		Assert.assertEquals(autorizoRecebimentoInformativo, solicitacaoCadastroUsuarioExternoDTO.isAutorizoRecebimentoInformativo());
	}

}

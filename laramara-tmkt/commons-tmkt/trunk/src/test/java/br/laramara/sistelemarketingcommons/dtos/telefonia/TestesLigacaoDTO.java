package br.laramara.sistelemarketingcommons.dtos.telefonia;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.ContextoTelefoneDTO;
import br.laramara.sistelemarketingcommons.dtos.contato.TelefoneDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesLigacaoDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void ligacao_dto_foi_construida_com_sucesso() {
		TelefoneDTO telefoneDto = ContextoTelefoneDTO.criar();
		String token = "4398792478234";

		LigacaoDTO ligacaoDto = new LigacaoDTO();
		ligacaoDto.setTelefoneDto(telefoneDto);
		ligacaoDto.setToken(token);

		Assert.assertEquals(ligacaoDto.getTelefoneDto().getTelefone(), telefoneDto.getTelefone());
		Assert.assertEquals(ligacaoDto.getToken(), token);
	}
}

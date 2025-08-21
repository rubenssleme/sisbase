package br.laramara.sistelemarketingcommons.dtos.contato;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesDistribuicaoContatoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void distribuicao_contato_dto_foi_construida_com_sucesso() {
		ContatoDTO contatoDto = ContextoContatoDTO.criar();

		DistribuicaoContatoDTO distribuicaoContatoDto = new DistribuicaoContatoDTO();
		distribuicaoContatoDto.setContatoDto(contatoDto);

		Assert.assertEquals(distribuicaoContatoDto.getContatoDto().getId(), contatoDto.getId());
	}
}

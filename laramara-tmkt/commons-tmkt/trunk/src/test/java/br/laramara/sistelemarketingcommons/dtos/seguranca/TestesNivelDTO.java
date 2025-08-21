package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesNivelDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void nivel_dto_foi_construida_com_sucesso() {
		Long id = new Long(2);
		String descricao = "Gerente";
		List<PermissaoDTO> permissaoDtos = Arrays.asList(ContextoPermissaoDTO.construir());
		
		NivelDTO nivelDto = new NivelDTO();
		nivelDto.setId(id);
		nivelDto.setDescricao(descricao);
		nivelDto.setPermissoesDto(permissaoDtos);
	
		Assert.assertEquals(nivelDto.getId(), id);
		Assert.assertEquals(nivelDto.getDescricao(), descricao);
		Assert.assertEquals(nivelDto.getPermissoesDto(), permissaoDtos);
	}
}

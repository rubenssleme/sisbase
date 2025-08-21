package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoNivel;
import br.laramara.sistelemarketingserver.dominio.seguranca.Nivel;
import br.laramara.sistelemarketingserver.fabricas.NivelFabrica;

public class TestesNivelFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_nivel_converte_objeto_de_dominio_para_dto() {
		Nivel nivel = ContextoNivel.fabricarNivel();

		NivelDTO nivelDtoCovertido = new NivelFabrica().converterParaDTO(nivel);

		Assert.assertEquals(nivelDtoCovertido.getId(), nivel.getId());
		Assert.assertEquals(nivelDtoCovertido.getDescricao(), nivel.getDescricao());
		Assert.assertEquals(nivelDtoCovertido.getPermissoesDto().size(), nivel.getPermissoes().size());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_nivel_converte_objeto_dto_para_dominio() {
		NivelDTO nivelDto = ContextoNivel.fabricarNivelDto();

		Nivel nivelCovertido = new NivelFabrica().converterParaDominio(nivelDto);

		Assert.assertEquals(nivelCovertido.getId(), nivelDto.getId());
		Assert.assertEquals(nivelCovertido.getDescricao(), nivelDto.getDescricao());
		Assert.assertEquals(nivelCovertido.getPermissoes().size(), nivelDto.getPermissoesDto().size());
	}
}

package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.BairroDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.Bairro;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoBairro;
import br.laramara.sistelemarketingserver.fabricas.BairroFabrica;

public class TestesBairroFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_bairro_converte_objeto_de_dominio_para_dto() {
		Bairro bairro = ContextoBairro.fabricarBairro();

		BairroDTO bairroDtoCovertido = new BairroFabrica().converterParaDTO(bairro);

		Assert.assertEquals(bairroDtoCovertido.getNome(), bairro.getNome());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_bairro_converte_objeto_dto_para_dominio() {
		BairroDTO bairroDto = ContextoBairro.fabricarBairroDto();

		Bairro bairroCovertido = new BairroFabrica().converterParaDominio(bairroDto);

		Assert.assertEquals(bairroCovertido.getNome(), bairroDto.getNome());
	}
}

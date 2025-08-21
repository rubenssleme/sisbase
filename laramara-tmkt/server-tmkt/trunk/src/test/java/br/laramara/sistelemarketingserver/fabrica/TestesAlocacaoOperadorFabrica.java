package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.campanha.AlocacaoOperadorDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.campanha.AlocacaoOperador;
import br.laramara.sistelemarketingserver.dominio.campanha.ContextoAlocacaoOperador;
import br.laramara.sistelemarketingserver.fabricas.AlocacaoOperadorFabrica;

public class TestesAlocacaoOperadorFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_alocacao_operador_converte_objeto_de_dominio_para_dto() {
		AlocacaoOperador alocacaoOperador = ContextoAlocacaoOperador.fabricar();

		AlocacaoOperadorDTO alocacaoOperadorDtoCovertido = new AlocacaoOperadorFabrica()
				.converterParaDTO(alocacaoOperador);

		Assert.assertEquals(alocacaoOperadorDtoCovertido.getId(), alocacaoOperador.getId());
		Assert.assertEquals(alocacaoOperadorDtoCovertido.getContaAcessoDto().getId(),
				alocacaoOperador.getContaAcesso().getId());
		Assert.assertEquals(alocacaoOperadorDtoCovertido.getMetaFinanceira(), alocacaoOperador.getMetaFinanceira());
		Assert.assertEquals(alocacaoOperadorDtoCovertido.getMetaQuantidadeLigacoes(),
				alocacaoOperador.getMetaQuantidadeLigacoes());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_alocacao_operador_converte_objeto_dto_para_dominio() {
		AlocacaoOperadorDTO alocacaoOperadorDto = ContextoAlocacaoOperador.criarDto();

		AlocacaoOperador alocacaoOperadorCovertido = new AlocacaoOperadorFabrica()
				.converterParaDominio(alocacaoOperadorDto);

		Assert.assertEquals(alocacaoOperadorCovertido.getId(), alocacaoOperadorDto.getId());
		Assert.assertEquals(alocacaoOperadorCovertido.getContaAcesso().getId(),
				alocacaoOperadorDto.getContaAcessoDto().getId());
		Assert.assertEquals(alocacaoOperadorCovertido.getMetaFinanceira(), alocacaoOperadorDto.getMetaFinanceira());
		Assert.assertEquals(alocacaoOperadorCovertido.getMetaQuantidadeLigacoes(),
				alocacaoOperadorDto.getMetaQuantidadeLigacoes());
	}
}

package br.laramara.sistelemarketingcommons.dtos.campanha;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContextoContaAcessoDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesAlocacaoOperadorDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void alocacao_operador_dto_foi_construida_com_sucesso() {
		Long id = new Long(2);
		ContaAcessoDTO contaAcessoDto = ContextoContaAcessoDTO.criar();
		BigDecimal metaFinanceira = new BigDecimal("1000.00");
		Integer metaQuantidadeLigacoes = new Integer(1000);

		AlocacaoOperadorDTO alocacaoOperedorDto = new AlocacaoOperadorDTO();
		alocacaoOperedorDto.setId(id);
		alocacaoOperedorDto.setContaAcessoDto(contaAcessoDto);
		alocacaoOperedorDto.setMetaFinanceira(metaFinanceira);
		alocacaoOperedorDto.setMetaQuantidadeLigacoes(metaQuantidadeLigacoes);

		Assert.assertEquals(alocacaoOperedorDto.getId(), id);
		Assert.assertEquals(alocacaoOperedorDto.getContaAcessoDto(), contaAcessoDto);
		Assert.assertEquals(alocacaoOperedorDto.getMetaFinanceira(), metaFinanceira);
		Assert.assertEquals(alocacaoOperedorDto.getMetaQuantidadeLigacoes(), metaQuantidadeLigacoes);
	}
}

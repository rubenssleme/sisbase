package br.laramara.sistelemarketingcommons.dtos.campanha;

import java.math.BigDecimal;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContextoContaAcessoDTO;

public class ContextoAlocacaoOperadorDTO {

	public static AlocacaoOperadorDTO criar() {
		Long id = new Long(2);
		ContaAcessoDTO contaAcessoDto = ContextoContaAcessoDTO.criar();
		BigDecimal metaFinanceira = new BigDecimal("1000.00");
		Integer metaQuantidadeLigacoes = new Integer(1000);

		AlocacaoOperadorDTO alocacaoOperadorDto = new AlocacaoOperadorDTO();
		alocacaoOperadorDto.setId(id);
		alocacaoOperadorDto.setContaAcessoDto(contaAcessoDto);
		alocacaoOperadorDto.setMetaFinanceira(metaFinanceira);
		alocacaoOperadorDto.setMetaQuantidadeLigacoes(metaQuantidadeLigacoes);
		return alocacaoOperadorDto;
	}
}

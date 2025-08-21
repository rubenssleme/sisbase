package br.laramara.sistelemarketingserver.dominio.campanha;

import java.math.BigDecimal;

import br.laramara.sistelemarketingcommons.dtos.campanha.AlocacaoOperadorDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoContaAcesso;

public class ContextoAlocacaoOperador {

	public static AlocacaoOperadorDTO criarDto() {
		Long id = new Long(2);
		ContaAcessoDTO contaAcessoDto = ContextoContaAcesso.fabricar();
		BigDecimal metaFinanceira = new BigDecimal("1000.00");
		Integer metaQuantidadeLigacoes = new Integer(1000);

		AlocacaoOperadorDTO alocacaoOperadorDto = new AlocacaoOperadorDTO();
		alocacaoOperadorDto.setId(id);
		alocacaoOperadorDto.setContaAcessoDto(contaAcessoDto);
		alocacaoOperadorDto.setMetaFinanceira(metaFinanceira);
		alocacaoOperadorDto.setMetaQuantidadeLigacoes(metaQuantidadeLigacoes);
		return alocacaoOperadorDto;
	}

	public static AlocacaoOperador fabricar() {
		Long id = new Long(12);
		ContaAcesso contaAcesso = ContextoContaAcesso.fabricarContaAcessoCarlos();
		BigDecimal metaFinanceira = new BigDecimal("1000.00");
		Integer metaQuantidadeLigacoes = new Integer(10000);

		AlocacaoOperador alocacaoOperador = new AlocacaoOperador();
		alocacaoOperador.setId(id);
		alocacaoOperador.setContaAcesso(contaAcesso);
		alocacaoOperador.setMetaFinanceira(metaFinanceira);
		alocacaoOperador.setMetaQuantidadeLigacoes(metaQuantidadeLigacoes);
		return alocacaoOperador;
	}
}

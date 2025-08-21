package br.laramara.sistelemarketingserver.dominio.doacao;

import java.math.BigDecimal;

import br.laramara.sistelemarketingcommons.dtos.doacao.TipoRetiradaDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.ValorDetalhadoDTO;
import br.laramara.sistelemarketingserver.ContextoGenerico;

public class ContextoValorDetalhado {

	public static ValorDetalhadoDTO fabricarDto() {
		ValorDetalhadoDTO valorDetalhadoDTO = new ValorDetalhadoDTO();
		valorDetalhadoDTO.setId(new Long(1000));
		valorDetalhadoDTO.setDataEfetuacao(ContextoGenerico.obterData("31/12/2018 23:59:59.999"));
		valorDetalhadoDTO.setValor(new BigDecimal("100.00"));
		valorDetalhadoDTO.setMetodoDto(ContextoMetodo.fabricarDto());
		valorDetalhadoDTO.setTipoRetiradaDto(new TipoRetiradaDTO("MENSAGEIRO"));
		return valorDetalhadoDTO;
	}

}

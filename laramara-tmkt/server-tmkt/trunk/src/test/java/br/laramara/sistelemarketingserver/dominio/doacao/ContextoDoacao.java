package br.laramara.sistelemarketingserver.dominio.doacao;

import java.util.Arrays;

import br.laramara.sistelemarketingcommons.dtos.doacao.DoacaoDTO;
import br.laramara.sistelemarketingserver.fabrica.ContextoDistriucaoContato;

public class ContextoDoacao {

	public static DoacaoDTO fabricarDto() {
		DoacaoDTO doacaoDTO = new DoacaoDTO();
		doacaoDTO.setId(new Long(1000));
		doacaoDTO.setValoresDetalhadosDto(Arrays.asList(ContextoValorDetalhado.fabricarDto()));
		doacaoDTO.setDistribuicaoContatoDto(ContextoDistriucaoContato.fabricarDistribuicaoContatoDto());
		return doacaoDTO;
	}
}

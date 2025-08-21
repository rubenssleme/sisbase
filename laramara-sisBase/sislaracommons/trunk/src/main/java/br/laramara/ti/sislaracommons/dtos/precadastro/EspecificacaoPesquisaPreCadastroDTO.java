package br.laramara.ti.sislaracommons.dtos.precadastro;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;

public class EspecificacaoPesquisaPreCadastroDTO extends EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = -1464304022424189539L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.PRE_CADASTRO);	
		adicionar(ChavePesquisaDTO.RG);	
		adicionar(ChavePesquisaDTO.CPF);
	}
}

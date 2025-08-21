package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;

public class EspecificacaoPesquisaReciboDTO extends EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = 7172551095128832294L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.TODOS_RECIBOS);
		adicionar(ChavePesquisaDTO.CPF_CNPJ);
		adicionar(ChavePesquisaDTO.FILIAL);
	}
}

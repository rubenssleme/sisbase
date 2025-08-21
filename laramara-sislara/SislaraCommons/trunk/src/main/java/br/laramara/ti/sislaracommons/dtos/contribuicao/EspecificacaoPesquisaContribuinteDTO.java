package br.laramara.ti.sislaracommons.dtos.contribuicao;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;

public class EspecificacaoPesquisaContribuinteDTO extends
		EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = 7172551095128832294L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.NOME_CONTRIBUINTE);
		adicionar(ChavePesquisaDTO.TODOS_CONTRIBUINTES);
	}
}

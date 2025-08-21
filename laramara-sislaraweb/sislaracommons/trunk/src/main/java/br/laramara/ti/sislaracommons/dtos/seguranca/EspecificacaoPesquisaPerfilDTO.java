package br.laramara.ti.sislaracommons.dtos.seguranca;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;

public class EspecificacaoPesquisaPerfilDTO extends EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = 697291943184587425L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.TODOS_PERFIS);
	}
}

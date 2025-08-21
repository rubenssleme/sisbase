package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;

public class EspecificacaoPesquisaGrupoDTO extends EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = -3886428793039712003L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.GRUPOS_ATIVOS);
		adicionar(ChavePesquisaDTO.GRUPOS_INATIVOS);
	}
}

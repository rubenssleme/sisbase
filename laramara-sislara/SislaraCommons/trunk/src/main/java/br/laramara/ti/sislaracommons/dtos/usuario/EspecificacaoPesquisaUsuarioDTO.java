package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;

public class EspecificacaoPesquisaUsuarioDTO extends
		EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = 7172551095128832294L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.PRONTUARIO);
		adicionar(ChavePesquisaDTO.NOME);
		adicionar(ChavePesquisaDTO.RG);
		adicionar(ChavePesquisaDTO.FAMILIAR);
	}
}

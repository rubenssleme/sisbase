package br.laramara.ti.sislaraserver.dominio.seguranca;

import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;

public class ChaveBloqueioGrupo extends ChaveBloqueio{

	private static final long serialVersionUID = 3134600585965672334L;

	public ChaveBloqueioGrupo(String identificacao) {
		super(identificacao);
	}

	public ChaveBloqueioGrupo(GrupoDTO grupoDto) {
		super(grupoDto.getNomeGrupoDto().toString() + "-"
				+ grupoDto.getTurma().toString() + "-"
				+ grupoDto.getDataInicio());
	}
}

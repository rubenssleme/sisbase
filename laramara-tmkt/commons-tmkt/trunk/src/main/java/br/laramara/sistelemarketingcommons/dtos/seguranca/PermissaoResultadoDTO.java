package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.List;

public class PermissaoResultadoDTO extends ResultadoDTO<PermissaoDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public List<PermissaoDTO> getPermissoesDto() {
		return objetos;
	}

	public void setPermissoesDto(List<PermissaoDTO> permissoesDto) {
		this.objetos = permissoesDto;
	}
}

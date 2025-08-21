package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;

public class PermissaoDTO extends ModeloDTO {

	private static final long serialVersionUID = 2004430618894014352L;

	private String permissao;

	public PermissaoDTO(String permissao) {
		this.permissao = permissao;
	}

	@Override
	public String toString() {
		return permissao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermissaoDTO other = (PermissaoDTO) obj;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		return true;
	}
}

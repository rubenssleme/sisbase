package br.laramara.ti.sislaracommons.dtos.endereco;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class TipoEnderecoDTO extends ModeloDTO {

	private static final long serialVersionUID = 637083394564755267L;

	private String tipoEndereco;

	public TipoEnderecoDTO() {
	}

	public TipoEnderecoDTO(String tipoEndereco) {
		setTipoEndereco(tipoEndereco);
	}

	public String getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipoEndereco == null) ? 0 : tipoEndereco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoEnderecoDTO other = (TipoEnderecoDTO) obj;
		if (tipoEndereco == null) {
			if (other.tipoEndereco != null)
				return false;
		} else if (!tipoEndereco.equals(other.tipoEndereco))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return tipoEndereco;
	}

}

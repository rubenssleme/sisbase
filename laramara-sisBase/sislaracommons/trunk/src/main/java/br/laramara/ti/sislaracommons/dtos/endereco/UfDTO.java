package br.laramara.ti.sislaracommons.dtos.endereco;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class UfDTO extends ModeloDTO{

	private static final long serialVersionUID = -7476109849888821440L;

	private String uf;

	public UfDTO(String uf) {
		this.uf = uf;
	}

	public String toString() {
		return uf;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UfDTO other = (UfDTO) obj;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}
}

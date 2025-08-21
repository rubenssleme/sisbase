package br.laramara.ti.sislaracommons.dtos;


public class TipoTelefoneDTO extends ModeloDTO {

	private static final long serialVersionUID = 5699596010585052345L;

	private String tipo;

	public TipoTelefoneDTO(String tipo) {
		this.tipo = tipo;
	}

	public String toString() {
		return tipo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoTelefoneDTO other = (TipoTelefoneDTO) obj;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
}

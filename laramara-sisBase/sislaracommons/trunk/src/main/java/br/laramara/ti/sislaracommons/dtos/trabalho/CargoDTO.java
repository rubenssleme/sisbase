package br.laramara.ti.sislaracommons.dtos.trabalho;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class CargoDTO extends ModeloDTO {

	private static final long serialVersionUID = -6619194507291271553L;

	private Long id;
	private String nome;

	public CargoDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String toString() {
		return this.nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CargoDTO other = (CargoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

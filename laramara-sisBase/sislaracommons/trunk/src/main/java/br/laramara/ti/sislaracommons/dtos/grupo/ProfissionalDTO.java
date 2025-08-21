package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ProfissionalDTO extends ModeloDTO {

	private static final long serialVersionUID = -7567411008242564525L;

	private Long id;
	private String nome;
	private String chapa;

	public ProfissionalDTO(Long id, String nome, String chapa) {
		this.id = id;
		this.nome = nome;
		this.chapa = chapa;
	}

	public Long getId() {
		return id;
	}
	
	public String getChapa() {
		return chapa;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfissionalDTO other = (ProfissionalDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
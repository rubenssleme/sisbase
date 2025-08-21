package br.laramara.ti.sislaracommons.dtos.escola;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class DiretoriaEnsinoDTO extends ModeloDTO {

	private static final long serialVersionUID = 4260017623239865770L;

	private Long id;
	private String nome;

	public DiretoriaEnsinoDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

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
		DiretoriaEnsinoDTO other = (DiretoriaEnsinoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

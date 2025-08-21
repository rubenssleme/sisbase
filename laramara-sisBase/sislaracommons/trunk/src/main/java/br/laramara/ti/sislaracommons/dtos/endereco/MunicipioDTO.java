package br.laramara.ti.sislaracommons.dtos.endereco;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;


public class MunicipioDTO extends ModeloDTO{

	private static final long serialVersionUID = -1362826547485639788L;

	private Long id;
	private String nome;
	private UfDTO uf;

	public MunicipioDTO(Long id, String nome, UfDTO uf) {
		this.id = id;
		this.nome = nome;
		this.uf = uf;
	}

	public Long getId() {
		return id;
	}

	public UfDTO getUf() {
		return uf;
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
		MunicipioDTO other = (MunicipioDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

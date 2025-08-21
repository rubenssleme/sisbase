package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class RecursoMoradiaDTO extends ModeloDTO{
	
	private static final long serialVersionUID = 3792175400643789122L;
	
	private Long id;
	private String descricao;
	
	public RecursoMoradiaDTO(Long id, String descricao){
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecursoMoradiaDTO other = (RecursoMoradiaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

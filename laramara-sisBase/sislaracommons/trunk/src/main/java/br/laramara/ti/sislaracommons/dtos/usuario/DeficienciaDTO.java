package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class DeficienciaDTO extends ModeloDTO{
	
	private static final long serialVersionUID = 3792175400643789122L;
	
	private Long id;
	private String descricao;
	private boolean etiologiaObrigatorio;
		
	public DeficienciaDTO(Long id, String descricao, boolean etiologiaObrigatorio){
		super();
		this.id = id;
		this.descricao = descricao;
		this.etiologiaObrigatorio = etiologiaObrigatorio;
	}
	
	public Long getId() {
		return id;
	}
	
	public boolean isEtiologiaObrigatorio() {
		return etiologiaObrigatorio;
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
		DeficienciaDTO other = (DeficienciaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

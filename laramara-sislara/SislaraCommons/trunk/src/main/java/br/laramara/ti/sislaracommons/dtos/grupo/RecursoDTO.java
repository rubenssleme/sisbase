package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class RecursoDTO extends ModeloDTO{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	private boolean cartelaDeSelos;
	private String valor;

	public RecursoDTO(Long id, String descricao, boolean cartelaDeSelos, String valor) {
		this.id = id;
		this.descricao = descricao;
		this.cartelaDeSelos = cartelaDeSelos;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}
	
	public String getValor() {
		return valor;
	}
	
	public boolean isCartelaDeSelos() {
		return cartelaDeSelos;
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
		RecursoDTO other = (RecursoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

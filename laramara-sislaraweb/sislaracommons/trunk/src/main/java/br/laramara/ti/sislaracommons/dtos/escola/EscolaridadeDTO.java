package br.laramara.ti.sislaracommons.dtos.escola;

import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class EscolaridadeDTO extends ModeloDTO {

	private static final long serialVersionUID = 5062166785634842332L;

	private Long id;
	private String descricao;
	private List<SerieDTO> series;

	public EscolaridadeDTO(Long id, String descricao, List<SerieDTO> series) {
		this.id = id;
		this.descricao = descricao;
		this.series = series;
	}

	public Long getId() {
		return id;
	}

	public String toString() {
		return this.descricao;
	}

	public List<SerieDTO> getSeries() {
		return series;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EscolaridadeDTO other = (EscolaridadeDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

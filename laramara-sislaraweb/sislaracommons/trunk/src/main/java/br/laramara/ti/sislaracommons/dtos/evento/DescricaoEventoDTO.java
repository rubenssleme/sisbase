package br.laramara.ti.sislaracommons.dtos.evento;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class DescricaoEventoDTO extends ModeloDTO {
	private static final long serialVersionUID = -5089049210994646608L;

	private Long id;
	private TipoDescricaoEventoDTO tipoDescricaoEventoDto;
	private String conteudo;

	public DescricaoEventoDTO() {
		this.conteudo = "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoDescricaoEventoDTO getTipoDescricaoEvento() {
		return tipoDescricaoEventoDto;
	}

	public void setTipoDescricaoEvento(TipoDescricaoEventoDTO tipoDescricaoEvento) {
		this.tipoDescricaoEventoDto = tipoDescricaoEvento;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipoDescricaoEventoDto == null) ? 0 : tipoDescricaoEventoDto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DescricaoEventoDTO other = (DescricaoEventoDTO) obj;
		if (tipoDescricaoEventoDto == null) {
			if (other.tipoDescricaoEventoDto != null)
				return false;
		} else if (!tipoDescricaoEventoDto.equals(other.tipoDescricaoEventoDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return tipoDescricaoEventoDto.toString();
	}

}

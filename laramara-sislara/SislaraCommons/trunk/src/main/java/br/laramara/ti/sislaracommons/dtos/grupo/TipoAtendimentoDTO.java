package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class TipoAtendimentoDTO extends ModeloDTO {

	private static final long serialVersionUID = -4979806406812030347L;

	private Long id;
	private String nome;
	private List<DescricaoTipoAtendimentoDTO> descricoesTipoAtendimentoDto;

	public TipoAtendimentoDTO() {
		descricoesTipoAtendimentoDto = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<DescricaoTipoAtendimentoDTO> getDescricoesTipoAtendimentoDto() {
		return descricoesTipoAtendimentoDto;
	}

	public void setDescricoesTipoAtendimentoDto(
			List<DescricaoTipoAtendimentoDTO> descricoesTipoAtendimentoDto) {
		this.descricoesTipoAtendimentoDto = descricoesTipoAtendimentoDto;
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
		TipoAtendimentoDTO other = (TipoAtendimentoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

package br.laramara.ti.sislaracommons.dtos.contribuicao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class MotivoDesativacaoDTO extends ModeloDTO {
	
	private static final long serialVersionUID = 6438856195011725784L;
	
	private Long id;
	private String descricao;

	public MotivoDesativacaoDTO(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}

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
		MotivoDesativacaoDTO other = (MotivoDesativacaoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

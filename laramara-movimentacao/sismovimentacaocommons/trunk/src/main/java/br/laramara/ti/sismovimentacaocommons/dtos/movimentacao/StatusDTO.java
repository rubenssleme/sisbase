package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;

public class StatusDTO extends ModeloDTO {

	private static final long serialVersionUID = -2437803202291845337L;

	private String status;
	private ClassificacaoDTO classificacaoDto;
	
	public StatusDTO(String status) {
		this.status = status;
	}

	public StatusDTO(String status, ClassificacaoDTO classificacaoDto) {
		this(status);
		this.classificacaoDto = classificacaoDto;
	}

	public String toString() {
		return status;
	}
	
	public ClassificacaoDTO getClassificacaoDto(){
		return classificacaoDto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusDTO other = (StatusDTO) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}

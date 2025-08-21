package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class EtiologiaDTO extends ModeloDTO {

	private static final long serialVersionUID = 3601434085370605479L;

	private Long id;
	private CidDTO cidDto;

	public EtiologiaDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CidDTO getCidDto() {
		return cidDto;
	}

	public void setCidDto(CidDTO cidDto) {
		this.cidDto = cidDto;
	}

	@Override
	public String toString() {
		return cidDto.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EtiologiaDTO other = (EtiologiaDTO) obj;
		if (cidDto == null) {
			if (other.cidDto != null)
				return false;
		} else if (!cidDto.equals(other.cidDto))
			return false;
		return true;
	}
	
	
}

package br.laramara.ti.sislaracommons.dtos.atendimento;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ParticipacaoDetalhadaDTO extends ModeloDTO {

	private static final long serialVersionUID = -7567411008242564525L;

	private Long id;
	private ParticipacaoDTO participacaoDto;
	private String quantidade;

	public ParticipacaoDetalhadaDTO() {
	}

	public Long getId() {
		return id;
	}
	
	public ParticipacaoDTO getParticipacaoDto() {
		return participacaoDto;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setParticipacaoDto(ParticipacaoDTO participacaoDto) {
		this.participacaoDto = participacaoDto;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return participacaoDto.toString() + " - " + quantidade;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipacaoDetalhadaDTO other = (ParticipacaoDetalhadaDTO) obj;
		if (participacaoDto == null) {
			if (other.participacaoDto != null)
				return false;
		} else if (!participacaoDto.equals(other.participacaoDto))
			return false;
		return true;
	}
}

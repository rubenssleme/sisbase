package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDTO;

public class InformacaoAtendimentoDTO extends ModeloDTO{

	private static final long serialVersionUID = -3837700847671449152L;

	private Long id;
	private FrequenciaDTO frequenciaDto;
	private String descricao;
	private String justificativa;
	private ParticipacaoDTO participacaoDto;

	public InformacaoAtendimentoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FrequenciaDTO getFrequenciaDto() {
		return frequenciaDto;
	}

	public void setFrequenciaDto(FrequenciaDTO frequenciaDto) {
		this.frequenciaDto = frequenciaDto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	public ParticipacaoDTO getParticipacaoDto() {
		return participacaoDto;
	}

	public void setParticipacaoDto(ParticipacaoDTO participacaoDto) {
		this.participacaoDto = participacaoDto;
	}
}

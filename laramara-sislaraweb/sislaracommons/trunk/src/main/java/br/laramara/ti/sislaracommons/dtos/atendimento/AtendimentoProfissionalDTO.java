package br.laramara.ti.sislaracommons.dtos.atendimento;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;

public class AtendimentoProfissionalDTO extends AtendimentoBaseDTO {

	private static final long serialVersionUID = 2683394681963275569L;

	private ProfissionalDTO profissionalDto;
	
	private HorarioDTO horarioDto;
	
	private boolean apenasParticipante;

	public AtendimentoProfissionalDTO() {
		super();
	}

	public ProfissionalDTO getProfissionalDto() {
		return profissionalDto;
	}

	public void setProfissionalDto(ProfissionalDTO profissionalDto) {
		this.profissionalDto = profissionalDto;
	}
	
	public boolean isApenasParticipante() {
		return apenasParticipante;
	}

	public void setApenasParticipante(boolean apenasParticipante) {
		this.apenasParticipante = apenasParticipante;
	}
	
	public HorarioDTO getHorarioDto() {
		return horarioDto;
	}

	public void setHorarioDto(HorarioDTO horarioDto) {
		this.horarioDto = horarioDto;
	}

	@Override
	protected String obterNome() {
		return profissionalDto.toString();
	}
}

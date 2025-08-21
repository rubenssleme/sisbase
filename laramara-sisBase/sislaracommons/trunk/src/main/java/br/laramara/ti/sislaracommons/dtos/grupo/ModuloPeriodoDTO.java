package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;

public class ModuloPeriodoDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = 2377935481186121951L;

	private Long id;
	private ModuloDTO moduloDto;
	private List<DiaSemanaEHorarioDTO> diasSemanaEHorariosDaAtividadeDto;
	private List<ProfissionalVinculoDTO> profissionaisVinculoDto;
	private List<ProfissionalDTO> profissionaisDto;
	private LocalAtendimentoDTO localAtendimentoDTO;
	private String vagas;
	private String cargaHoraria;
	private String cargaHorariaMinima;
	private List<ProgramacaoDTO> programacaoDto;
	private List<AtendimentoGrupoDTO> atendimentosGrupoDto;
	private List<ModuloUsuarioDTO> modulosUsuariosDto;
	private List<ModuloPreCadastroDTO> modulosPreCadastroDto;
	
	public ModuloPeriodoDTO() {
		diasSemanaEHorariosDaAtividadeDto = new ArrayList<>();
		profissionaisVinculoDto = new ArrayList<>();
		programacaoDto = new ArrayList<>();
		atendimentosGrupoDto = new ArrayList<>();
		modulosUsuariosDto = new ArrayList<>();
		modulosPreCadastroDto = new ArrayList<>();
		cargaHoraria = "000:00";
		cargaHorariaMinima = "000:00";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ModuloDTO getModuloDto() {
		return moduloDto;
	}

	public void setModuloDto(ModuloDTO moduloDto) {
		this.moduloDto = moduloDto;
	}

	public List<ProfissionalDTO> getProfissionaisDto() {
		return profissionaisDto;
	}

	public void setProfissionaisDto(List<ProfissionalDTO> profissionaisDto) {
		this.profissionaisDto = profissionaisDto;
	}

	public List<ProfissionalVinculoDTO> getProfissionaisVinculoDto() {
		return profissionaisVinculoDto;
	}

	public void setProfissionaisVinculoDto(List<ProfissionalVinculoDTO> profissionaisVinculoDto) {
		this.profissionaisVinculoDto = profissionaisVinculoDto;
	}

	public LocalAtendimentoDTO getLocalAtendimentoDTO() {
		return localAtendimentoDTO;
	}

	public void setLocalAtendimentoDTO(LocalAtendimentoDTO localAtendimentoDTO) {
		this.localAtendimentoDTO = localAtendimentoDTO;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}
	
	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public String getCargaHorariaMinima(){
		return cargaHorariaMinima;
	}
	
	public void setCargaHorariaMinima(String cargaHorariaMinima){
		this.cargaHorariaMinima = cargaHorariaMinima;
	}

	public List<ProgramacaoDTO> getProgramacaoDto() {
		return programacaoDto;
	}

	public void setProgramacaoDto(List<ProgramacaoDTO> programacaoDto) {
		this.programacaoDto = programacaoDto;
	}
	
	public AtendimentoGrupoDTO obterAtendimentoGrupo(AtendimentoGrupoDTO atendimentoDto) {
		return atendimentosGrupoDto.contains(atendimentoDto) ? atendimentosGrupoDto
				.get(atendimentosGrupoDto.indexOf(atendimentoDto)) : null;
	}
	
	public List<AtendimentoGrupoDTO> getAtendimentosGrupoDto() {
		return atendimentosGrupoDto;
	}

	public void setAtendimentosGrupoDto(List<AtendimentoGrupoDTO> atendimentosDto) {
		this.atendimentosGrupoDto = atendimentosDto;
	}

	public List<ModuloUsuarioDTO> getModulosUsuariosDto() {
		return modulosUsuariosDto;
	}

	public void setModulosUsuariosDto(List<ModuloUsuarioDTO> modulosUsuariosDto) {
		this.modulosUsuariosDto = modulosUsuariosDto;
	}
	
	public List<ModuloPreCadastroDTO> getModulosPreCadastroDto() {
		return modulosPreCadastroDto;
	}

	public void setModulosPreCadastroDto(
			List<ModuloPreCadastroDTO> modulosPreCadastroDto) {
		this.modulosPreCadastroDto = modulosPreCadastroDto;
	}

	public List<DiaSemanaEHorarioDTO> getDiasSemanaEHorariosDaAtividadeDto() {
		return diasSemanaEHorariosDaAtividadeDto;
	}
	
	public String obterHorarios() {
		String resultado = "";
		for (DiaSemanaEHorarioDTO diaSemanaEHorarioDTO : diasSemanaEHorariosDaAtividadeDto) {
			resultado += diaSemanaEHorarioDTO.getHorarioDto().toString() +" ";
		}
		return resultado;
	}

	public void setDiasSemanaEHorariosDaAtividadeDto(List<DiaSemanaEHorarioDTO> diasSemanaEHorariosDaAtividadeDto) {
		this.diasSemanaEHorariosDaAtividadeDto = diasSemanaEHorariosDaAtividadeDto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuloPeriodoDTO other = (ModuloPeriodoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return moduloDto + " - " + TextoUtils.removerChaves(profissionaisDto.toString() + " - " + obterHorarios());
	}
}

package br.laramara.ti.sislaracommons.dtos.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public class EspecificacaoGeracaoAgendamentoDTO implements Serializable {

	private static final long serialVersionUID = 6127343287988660867L;

	private UsuarioDTO usuarioDto;
	private PreCadastroDTO preCadastroDTO;
	private List<GrupoDTO> gruposDTO;
	private List<ProfissionalDTO> profissionaisDto;
	private StatusDTO reservaStatusDto;
	private DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto;
	private ModuloDTO moduloDto;
	private SetorDTO setorDto;
	private LocalAtendimentoDTO localAtendimentoDto;
	private String dataInicio;
	private String dataTermino;
	private DiaSemanaDTO diaSemanaDto;
	private HorarioDTO horarioDto;
	private String obs;

	public EspecificacaoGeracaoAgendamentoDTO() {
		horarioDto = new HorarioDTO();
		gruposDTO = new ArrayList<>();
		profissionaisDto = new ArrayList<>();
	}

	public UsuarioDTO getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDTO usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

	public PreCadastroDTO getPreCadastroDTO() {
		return preCadastroDTO;
	}

	public void setPreCadastroDTO(PreCadastroDTO preCadastroDTO) {
		this.preCadastroDTO = preCadastroDTO;
	}

	public List<GrupoDTO> getGruposDTO() {
		return gruposDTO;
	}

	public void setGruposDTO(List<GrupoDTO> gruposDTO) {
		this.gruposDTO = gruposDTO;
	}

	public List<ProfissionalDTO> getProfissionaisDto() {
		return profissionaisDto;
	}

	public void setProfissionaisDto(List<ProfissionalDTO> profissionaisDto) {
		this.profissionaisDto = profissionaisDto;
	}

	public StatusDTO getReservaStatusDto() {
		return reservaStatusDto;
	}

	public void setReservaStatusDto(StatusDTO reservaStatusDto) {
		this.reservaStatusDto = reservaStatusDto;
	}

	public DescricaoTipoAtendimentoDTO getDescricaoTipoAtendimentoDto() {
		return descricaoTipoAtendimentoDto;
	}

	public void setDescricaoTipoAtendimentoDto(
			DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto) {
		this.descricaoTipoAtendimentoDto = descricaoTipoAtendimentoDto;
	}
	
	public ModuloDTO getModuloDto() {
		return moduloDto;
	}

	public void setModuloDto(ModuloDTO moduloDto) {
		this.moduloDto = moduloDto;
	}

	public SetorDTO getSetorDto() {
		return setorDto;
	}

	public void setSetorDto(SetorDTO setorDto) {
		this.setorDto = setorDto;
	}

	public LocalAtendimentoDTO getLocalAtendimentoDto() {
		return localAtendimentoDto;
	}

	public void setLocalAtendimentoDto(LocalAtendimentoDTO localAtendimentoDto) {
		this.localAtendimentoDto = localAtendimentoDto;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}

	public DiaSemanaDTO getDiaSemanaDto() {
		return diaSemanaDto;
	}

	public void setDiaSemanaDto(DiaSemanaDTO diaSemanaDto) {
		this.diaSemanaDto = diaSemanaDto;
	}

	public HorarioDTO getHorarioDto() {
		return horarioDto;
	}

	public void setHorarioDto(HorarioDTO horarioDto) {
		this.horarioDto = horarioDto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
}

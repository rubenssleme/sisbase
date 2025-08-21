package br.laramara.ti.sislaracommons.dtos.agenda;

import java.util.List;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
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

public class AgendamentoDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = 6813031134400178L;
	
	private Long id;
	private UsuarioDTO usuarioDto;
	private PreCadastroDTO preCadastroDto;
	private List<GrupoDTO> gruposDto;
	private ProfissionalDTO profissionalDto;
	private DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto;
	private ModuloDTO moduloDto;
	private String data;
	private DiaSemanaDTO diaSemanaDto;
	private HorarioDTO horarioDto; 
	private SetorDTO setorDto;
	private StatusDTO reservaParaDto;
	private LocalAtendimentoDTO localAtendimentoDto;
	private String obs;
	private StatusAgendamentoDTO statusDto;
	private MotivoCancelamentoDTO motivoCancelamentoDTO;
	private String justificativaCancelamento;
	private String informacaoAdicional;
	private boolean estaAgendado;
	private boolean estaReagendado;
	private boolean estaDisponivel;
	private boolean estaCancelado;
	private UsuarioDTO usuarioCriadoPeloPreCadastro;
	private String responsaveisOperacoes;
	private String idadeDoUsuarioOuPreCadastro;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioDTO getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDTO usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

	public PreCadastroDTO getPreCadastroDto() {
		return preCadastroDto;
	}

	public void setPreCadastroDto(PreCadastroDTO preCadastroDto) {
		this.preCadastroDto = preCadastroDto;
	}

	public List<GrupoDTO> getGruposDto() {
		return gruposDto;
	}

	public void setGruposDto(List<GrupoDTO> gruposDto) {
		this.gruposDto = gruposDto;
	}

	public ProfissionalDTO getProfissionalDto() {
		return profissionalDto;
	}

	public void setProfissionalDto(ProfissionalDTO profissionalDto) {
		this.profissionalDto = profissionalDto;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public StatusAgendamentoDTO getStatusDto() {
		return statusDto;
	}

	public void setStatusDto(StatusAgendamentoDTO status) {
		this.statusDto = status;
	}

	public SetorDTO getSetorDto() {
		return setorDto;
	}

	public void setSetorDto(SetorDTO setor) {
		this.setorDto = setor;
	}

	public StatusDTO getReservaParaDto() {
		return reservaParaDto;
	}

	public void setReservaParaDto(StatusDTO reservaParaDto) {
		this.reservaParaDto = reservaParaDto;
	}

	public LocalAtendimentoDTO getLocalAtendimentoDto() {
		return localAtendimentoDto;
	}

	public void setLocalAtendimentoDto(LocalAtendimentoDTO localAtendimentoDto) {
		this.localAtendimentoDto = localAtendimentoDto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public MotivoCancelamentoDTO getMotivoCancelamentoDTO() {
		return motivoCancelamentoDTO;
	}

	public void setMotivoCancelamentoDTO(MotivoCancelamentoDTO motivoCancelamentoDTO) {
		this.motivoCancelamentoDTO = motivoCancelamentoDTO;
	}

	public String getJustificativaCancelamento() {
		return justificativaCancelamento;
	}

	public void setJustificativaCancelamento(String justificativaCancelamento) {
		this.justificativaCancelamento = justificativaCancelamento;
	}
	
	public String getInformacaoAdicional() {
		return informacaoAdicional;
	}

	public void setInformacaoAdicional(String informacaoAdicional) {
		this.informacaoAdicional = informacaoAdicional;
	}

	public boolean isEstaDisponivel() {
		return estaDisponivel;
	}

	public void setEstaDisponivel(boolean estaDisponivel) {
		this.estaDisponivel = estaDisponivel;
	}
	
	public boolean isEstaAgendado() {
		return estaAgendado;
	}

	public void setEstaAgendado(boolean estaAgendado) {
		this.estaAgendado = estaAgendado;
	}
	
	public boolean isEstaReagendado() {
		return estaReagendado;
	}

	public void setEstaReagendado(boolean estaReagendado) {
		this.estaReagendado = estaReagendado;
	}

	public boolean isEstaCancelado() {
		return estaCancelado;
	}

	public void setEstaCancelado(boolean estaCancelado) {
		this.estaCancelado = estaCancelado;
	}

	public String getResponsaveisOperacoes() {
		return responsaveisOperacoes;
	}

	public void setResponsaveisOperacoes(String responsaveisOperacoes) {
		this.responsaveisOperacoes = responsaveisOperacoes;
	}

	public UsuarioDTO getUsuarioCriadoPeloPreCadastro() {
		return usuarioCriadoPeloPreCadastro;
	}

	public void setUsuarioCriadoPeloPreCadastro(UsuarioDTO usuarioCriadoPeloPreCadastro) {
		this.usuarioCriadoPeloPreCadastro = usuarioCriadoPeloPreCadastro;
	}

	public String getIdadeDoUsuarioOuPreCadastro() {
		return idadeDoUsuarioOuPreCadastro;
	}

	public void setIdadeDoUsuarioOuPreCadastro(String idade) {
		this.idadeDoUsuarioOuPreCadastro = idade;
	}
}

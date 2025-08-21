package br.laramara.ti.sislaracommons.dtos.espera;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public class EsperaDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = 5195553374632706693L;
	
	private Long id;
	private UsuarioDTO usuarioDto;
	private PreCadastroDTO preCadastroDto;
	private String dataCadastro;
	private String dataExpectativa;
	private DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto;
	private ModuloDTO moduloDto;
	private SetorDTO setorDto;
	private NomeGrupoDTO nomeGrupoDto;
	private ProfissionalDTO profissionalSolicitouDto;
	private TipoEsperaDTO tipoEsperaDto;
	private StatusEsperaDTO statusDto;
	private boolean estaCancelado;
	private boolean estaAguardando;
	private boolean estaTriando;
	private String obs;
	private String justificativaCancelamento;
	private String historioOperacoes;
	private List<SetorDTO> setoresUsuario;
	private UsuarioDTO usuarioCriadoPeloPreCadastro;
	private String dataUltimaLigacaoInteresse;
	private boolean interesse;
	private boolean lmLigou;
	private boolean pendencias;
	private String idadeDoUsuarioOuPreCadastro;

	public EsperaDTO() {
		setoresUsuario = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public InformacaoEssencialDTO getInformacaoEssencialDTO() {
		if (usuarioDto != null) {
			return usuarioDto.getInformacaoEssencialDto();
		} else if (preCadastroDto != null) {
			return preCadastroDto.getInformacaoEssencialDto();
		} else {
			return null;
		}
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
	
	public List<SetorDTO> getSetoresUsuario() {
		return setoresUsuario;
	}
	
	public boolean isPendencias() {
		return pendencias;
	}

	public void setPendencias(boolean pendencias) {
		this.pendencias = pendencias;
	}

	public void setSetoresUsuario(List<SetorDTO> setoresUsuario) {
		this.setoresUsuario = setoresUsuario;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDataExpectativa() {
		return dataExpectativa;
	}

	public void setDataExpectativa(String data) {
		this.dataExpectativa = data;
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

	public NomeGrupoDTO getNomeGrupoDto() {
		return nomeGrupoDto;
	}

	public void setNomeGrupoDto(NomeGrupoDTO nomeGrupoDto) {
		this.nomeGrupoDto = nomeGrupoDto;
	}
	
	public ProfissionalDTO getProfissionalSolicitioDto() {
		return profissionalSolicitouDto;
	}

	public void setProfissionalSolicitouDto(ProfissionalDTO profissionalSolicitouDto) {
		this.profissionalSolicitouDto = profissionalSolicitouDto;
	}

	public TipoEsperaDTO getTipoEsperaDto() {
		return tipoEsperaDto;
	}

	public void setTipoEsperaDto(TipoEsperaDTO tipoEsperaDto) {
		this.tipoEsperaDto = tipoEsperaDto;
	}

	public StatusEsperaDTO getStatusDto() {
		return statusDto;
	}

	public void setStatusDto(StatusEsperaDTO statusDto) {
		this.statusDto = statusDto;
	}

	public boolean estaCancelado() {
		return estaCancelado;
	}

	public void setEstaCancelado(boolean estaCancelado) {
		this.estaCancelado = estaCancelado;
	}
		
	public boolean estaAguardando() {
		return estaAguardando;
	}
	
	public void setEstaAguardando(boolean estaAguardando) {
		this.estaAguardando = estaAguardando;
	}

	public boolean estaTriando() {
		return estaTriando;
	}
	
	public void setEstaTriando(boolean estaTriando) {
		this.estaTriando = estaTriando;
	}
	
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getJustificativaCancelamento() {
		return justificativaCancelamento;
	}

	public void setJustificativaCancelamento(String justificativaCancelamento) {
		this.justificativaCancelamento = justificativaCancelamento;
	}

	public String getHistorioOperacoes() {
		return historioOperacoes;
	}

	public void setHistorioOperacoes(String historioOperacoes) {
		this.historioOperacoes = historioOperacoes;
	}

	public UsuarioDTO getUsuarioCriadoPeloPreCadastro() {
		return usuarioCriadoPeloPreCadastro;
	}

	public void setUsuarioCriadoPeloPreCadastro(
			UsuarioDTO usuarioCriadoPeloPreCadastro) {
		this.usuarioCriadoPeloPreCadastro = usuarioCriadoPeloPreCadastro;
	}

	public boolean isInteresse() {
		return interesse;
	}

	public void setInteresse(boolean interesse) {
		this.interesse = interesse;
	}

	public boolean isLmLigou() {
		return lmLigou;
	}

	public void setLmLigou(boolean lmLigou) {
		this.lmLigou = lmLigou;
	}

	public String getDataUltimaLigacaoInteresse() {
		return dataUltimaLigacaoInteresse;
	}

	public void setDataUltimaLigacaoInteresse(String dataUltimaLigacaoInteresse) {
		this.dataUltimaLigacaoInteresse = dataUltimaLigacaoInteresse;
	}

	public String getIdadeDoUsuarioOuPreCadastro() {
		return idadeDoUsuarioOuPreCadastro;
	}

	public void setIdadeDoUsuarioOuPreCadastro(String idade) {
		this.idadeDoUsuarioOuPreCadastro = idade;
	}
}

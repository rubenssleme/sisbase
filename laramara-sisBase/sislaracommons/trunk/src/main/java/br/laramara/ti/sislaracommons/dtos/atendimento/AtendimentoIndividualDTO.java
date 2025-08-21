package br.laramara.ti.sislaracommons.dtos.atendimento;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.InformacaoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public class AtendimentoIndividualDTO extends ModeloDTO implements Identificavel{
	
	private static final long serialVersionUID = -3065689335458089364L;
	
	private Long id;
	private UsuarioDTO usuarioDto;
	private PreCadastroDTO preCadastroDto;
	private String data;
	private StatusAtendimentoDTO statusAtendimentoDto;
	private HorarioDTO horarioDto;
	private InformacaoAtendimentoDTO informacaoAtendimentoDto;
	private DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto;
	private ModuloDTO moduloDto;
	private SetorDTO setorDto;
	private String interdisciplinar;
	private List<AtendimentoProfissionalDTO> atendimentosProfissionalDto;
	private List<AtendimentoComunidadeDTO> atendimentosComunidadeDto;
	private LocalAtendimentoDTO localAtendimentoDto;
	private ParticipacaoDTO participacaoDto;
	private StatusDTO primeiraVezOuRetornoDto;
	private List<ArquivoDTO> arquivos;
	private String idadeDoUsuarioOuPreCadastro;
	private List<AcaoCondutaDTO> acoesDeCondutasDto;
	private OpcaoIntegracaoDTO integracaoDto;
	private String motivoNaoIntegracao;
	private SimNaoDTO discussaoCasoDto;
	private String resumoIntegracao;

	public AtendimentoIndividualDTO() {
		atendimentosProfissionalDto = new ArrayList<>();
		atendimentosComunidadeDto = new ArrayList<>();
		informacaoAtendimentoDto = new InformacaoAtendimentoDTO();
		arquivos = new ArrayList<>();
		acoesDeCondutasDto = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<ArquivoDTO> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<ArquivoDTO> arquivos) {
		this.arquivos = arquivos;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public StatusAtendimentoDTO getStatusAtendimentoDto() {
		return statusAtendimentoDto;
	}

	public void setStatusAtendimentoDto(
			StatusAtendimentoDTO statusAtendimentoDto) {
		this.statusAtendimentoDto = statusAtendimentoDto;
	}

	public HorarioDTO getHorarioDto() {
		return horarioDto;
	}

	public void setHorarioDto(HorarioDTO horarioDto) {
		this.horarioDto = horarioDto;
	}

	public InformacaoAtendimentoDTO getInformacaoAtendimentoDto() {
		return informacaoAtendimentoDto;
	}

	public void setInformacaoAtendimentoDto(
			InformacaoAtendimentoDTO informacaoAtendimentoDto) {
		this.informacaoAtendimentoDto = informacaoAtendimentoDto;
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

	public String getInterdisciplinar() {
		return interdisciplinar;
	}

	public void setInterdisciplinar(String interdisciplinar) {
		this.interdisciplinar = interdisciplinar;
	}

	public List<AtendimentoProfissionalDTO> getAtendimentosProfissionalDto() {
		return atendimentosProfissionalDto;
	}

	public void setAtendimentosProfissionalDto(
			List<AtendimentoProfissionalDTO> atendimentosProfissionalDto) {
		this.atendimentosProfissionalDto = atendimentosProfissionalDto;
	}

	public List<AtendimentoComunidadeDTO> getAtendimentosComunidadeDto() {
		return atendimentosComunidadeDto;
	}

	public void setAtendimentosComunidadeDto(
			List<AtendimentoComunidadeDTO> atendimentosComunidadeDto) {
		this.atendimentosComunidadeDto = atendimentosComunidadeDto;
	}

	public LocalAtendimentoDTO getLocalAtendimentoDto() {
		return localAtendimentoDto;
	}

	public void setLocalAtendimentoDto(LocalAtendimentoDTO localAtendimentoDto) {
		this.localAtendimentoDto = localAtendimentoDto;
	}

	public ParticipacaoDTO getParticipacaoDto() {
		return participacaoDto;
	}

	public void setParticipacaoDto(ParticipacaoDTO participacaoDto) {
		this.participacaoDto = participacaoDto;
	}

	public StatusDTO getPrimeiraVezOuRetornoDto() {
		return primeiraVezOuRetornoDto;
	}

	public void setPrimeiraVezOuRetornoDto(StatusDTO primeiraVezOuRetornoDto) {
		this.primeiraVezOuRetornoDto = primeiraVezOuRetornoDto;
	}
	
	public String getIdadeDoUsuarioOuPreCadastro() {
		return idadeDoUsuarioOuPreCadastro;
	}

	public void setIdadeDoUsuarioOuPreCadastro(String idade) {
		this.idadeDoUsuarioOuPreCadastro = idade;
	}

	public List<AcaoCondutaDTO> getAcoesDeCondutasDto() {
		return acoesDeCondutasDto;
	}

	public void setAcoesDeCondutasDto(List<AcaoCondutaDTO> acoesDeCondutasDto) {
		this.acoesDeCondutasDto = acoesDeCondutasDto;
	}

	public OpcaoIntegracaoDTO getOpcaoIntegracaoDto() {
		return integracaoDto;
	}

	public void setOpcaoIntegracaoDto(OpcaoIntegracaoDTO integracaoDto) {
		this.integracaoDto = integracaoDto;
	}

	public String getMotivoNaoIntegracao() {
		return motivoNaoIntegracao;
	}

	public void setMotivoNaoIntegracao(String motivoNaoIntegracao) {
		this.motivoNaoIntegracao = motivoNaoIntegracao;
	}

	public SimNaoDTO getDiscussaoCasoSimNaoDto() {
		return discussaoCasoDto;
	}

	public void setDiscussaoCasoSimNaoDto(SimNaoDTO discussaoCaso) {
		this.discussaoCasoDto = discussaoCaso;
	}

	public String getResumoIntegracao() {
		return resumoIntegracao;
	}

	public void setResumoIntegracao(String resumoIntegracao) {
		this.resumoIntegracao = resumoIntegracao;
	}
}

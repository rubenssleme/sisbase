package br.laramara.ti.sislaracommons.dtos.doacao;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.MotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public class DemandaDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = 1511532330040479798L;

	private Long id;
	private String sequenciaCriacao;
	private String dataPrazoCaptacao;
	private UsuarioDTO usuarioDto;
	private PreCadastroDTO preCadastroDto;
	private GrupoDTO grupoDto;
	private RecursoDTO recursoDto;
	private String obs;
	private String responsaveisOperacoes;
	private StatusDemandaDTO statusDemandaDto;
	private List<DocumentoSolicitacaoDoacaoDTO> documentosSolicitacaoDoacaoDTO;
	private String valorTotalCaptado;
	private String valorCartela;
	private String valorSaldo;
	private boolean estaNovo;
	private boolean cartelaDeSelos;
	private MotivoCancelamentoDTO motivoCancelamentoDTO;
	
	public DemandaDTO(){
		documentosSolicitacaoDoacaoDTO = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}
		
	public void setId(Long id) {
		this.id = id;
	}

	public boolean isCartelaDeSelos(){
		return cartelaDeSelos;
	}
	
	public void setCartelaDeSelos(boolean cartelaDeSelos) {
		this.cartelaDeSelos = cartelaDeSelos;
	}

	public String getSequenciaCriacao() {
		return sequenciaCriacao;
	}

	public void setSequenciaCriacao(String sequenciaCriacao) {
		this.sequenciaCriacao = sequenciaCriacao;
	}

	public String getDataPrazoCaptacao() {
		return dataPrazoCaptacao;
	}

	public void setDataPrazoDeCaptacao(String dataPrazoCaptacao) {
		this.dataPrazoCaptacao = dataPrazoCaptacao;
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

	public GrupoDTO getGrupoDto() {
		return grupoDto;
	}

	public void setGrupoDto(GrupoDTO grupoDto) {
		this.grupoDto = grupoDto;
	}

	public RecursoDTO getRecursoDto() {
		return recursoDto;
	}

	public void setRecursoDto(RecursoDTO recursoDto) {
		this.recursoDto = recursoDto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getResponsaveisOperacoes() {
		return responsaveisOperacoes;
	}

	public void setResponsaveisOperacoes(String responsaveisOperacoes) {
		this.responsaveisOperacoes = responsaveisOperacoes;
	}
	
	public StatusDemandaDTO getStatusDemandaDto() {
		return statusDemandaDto;
	}

	public void setStatusDemandaDto(StatusDemandaDTO statusDemandaDto) {
		this.statusDemandaDto = statusDemandaDto;
	}

	public boolean isEstaNovo() {
		return estaNovo;
	}

	public void setEstaNovo(boolean estaNovo) {
		this.estaNovo = estaNovo;
	}

	public List<DocumentoSolicitacaoDoacaoDTO> getDocumentosSolicitacaoDoacaoDto() {
		return documentosSolicitacaoDoacaoDTO;
	}

	public void setDocumentosSolicitacaoDoacao(List<DocumentoSolicitacaoDoacaoDTO> documentoSolicitacaoDoacaoDTO) {
		this.documentosSolicitacaoDoacaoDTO = documentoSolicitacaoDoacaoDTO;
	}

	public String getValorTotalCaptado() {
		return valorTotalCaptado;
	}

	public void setValorTotalCaptado(String valorTotalCaptado) {
		this.valorTotalCaptado = valorTotalCaptado;
	}

	public String getValorCartela() {
		return valorCartela;
	}

	public void setValorCartela(String valorCartela) {
		this.valorCartela = valorCartela;
	}

	public String getValorSaldo() {
		return valorSaldo;
	}

	public void setValorSaldo(String valorSaldo) {
		this.valorSaldo = valorSaldo;
	}

	public MotivoCancelamentoDTO getMotivoCancelamentoDTO() {
		return motivoCancelamentoDTO;
	}

	public void setMotivoCancelamentoDTO(MotivoCancelamentoDTO motivoCancelamentoDTO) {
		this.motivoCancelamentoDTO = motivoCancelamentoDTO;
	}
}
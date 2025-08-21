package br.laramara.ti.sislaracommons.dtos.solicitacao;

import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public class SolicitacaoRelatorioDTO extends ModeloDTO {

	private static final long serialVersionUID = -2693891162923070277L;

	private Long id;

	private String nomeSolicitante;

	private String rgSolicitante;

	private UsuarioDTO usuarioDto;

	private String quantidadeRelatoriosEmitidos;

	private String quantidadeRelatoriosEntregues;

	private String obs;

	private String responsavelPorOperacao;

	private boolean estaSolicitado;

	private boolean estaEncaminhaPelaRecepcao;

	private boolean estaEmitidoPorProfissional;
	
	private boolean estaEntregueParaRecepcao;
	
	private boolean estaCancelado;
	
	private StatusSolicitacaoRelatorioDTO statusDaEntrega;
	
	private StatusSolicitacaoRelatorioDTO statusAtual;
	
	private List<FinalidadeRelatorioDTO> finalidadesRelatoriosDto;
	
	private String finalidadesRelatorios;
	
	private ElaboradorDTO elaboradorDto;
	
	private boolean enviarPeloCorreio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getRgSolicitante() {
		return rgSolicitante;
	}

	public void setRgSolicitante(String rgSolicitante) {
		this.rgSolicitante = rgSolicitante;
	}

	public UsuarioDTO getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDTO usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

	public String getQuantidadeRelatoriosEmitidos() {
		return quantidadeRelatoriosEmitidos;
	}

	public void setQuantidadeRelatoriosEmitidos(
			String quantidadeRelatoriosEmitidos) {
		this.quantidadeRelatoriosEmitidos = quantidadeRelatoriosEmitidos;
	}

	public String getQuantidadeRelatoriosEntregues() {
		return quantidadeRelatoriosEntregues;
	}

	public void setQuantidadeRelatoriosEntregues(
			String quantidadeRelatoriosEntregues) {
		this.quantidadeRelatoriosEntregues = quantidadeRelatoriosEntregues;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getResponsavelPorOperacao() {
		return responsavelPorOperacao;
	}

	public void setResponsavelPorOperacao(String responsavelPorOperacao) {
		this.responsavelPorOperacao = responsavelPorOperacao;
	}

	public boolean estaSolicitado() {
		return estaSolicitado;
	}

	public boolean estaEncaminhadoPelaRecepcao() {
		return estaEncaminhaPelaRecepcao;
	}

	public boolean estaEmitidoPorProfissional() {
		return estaEmitidoPorProfissional;
	}

	public boolean estaEntregueParaRecepcao(){
		return estaEntregueParaRecepcao;
	}
	
	public boolean estaCancelado(){
		return estaCancelado;
	}
	
	public void setEstaSolicitado(boolean estaSolicitado) {
		this.estaSolicitado = estaSolicitado;
	}

	public void setEstaEncaminhadoPelaRecepcao(
			boolean estaEncaminhadoPelaRecepcao) {
		this.estaEncaminhaPelaRecepcao = estaEncaminhadoPelaRecepcao;
	}

	public void setEstaEmitidoPorProfissional(boolean estaEmitidoPorProfissional) {
		this.estaEmitidoPorProfissional = estaEmitidoPorProfissional;
	}

	public void setEstaEntregueParaRecepcao(
			boolean estaEntregueParaRecepcao) {
		this.estaEntregueParaRecepcao = estaEntregueParaRecepcao;
	}
	
	public void setEstaCancelado(boolean estaCancelado){
		this.estaCancelado = estaCancelado;
	}
	
	public StatusSolicitacaoRelatorioDTO getStatusDaEntrega() {
		return statusDaEntrega;
	}

	public void setStatusDaEntrega(
			StatusSolicitacaoRelatorioDTO statusDaEntrega) {
		this.statusDaEntrega = statusDaEntrega;
	}

	public StatusSolicitacaoRelatorioDTO getStatusAtual() {
		return statusAtual;
	}

	public void setStatusAtual(StatusSolicitacaoRelatorioDTO statusAtual) {
		this.statusAtual = statusAtual;
	}

	public List<FinalidadeRelatorioDTO> getFinalidadesRelatoriosDto() {
		return finalidadesRelatoriosDto;
	}

	public void setFinalidadesRelatoriosDto(List<FinalidadeRelatorioDTO> finalidadesRelatoriosDto) {
		this.finalidadesRelatoriosDto = finalidadesRelatoriosDto;
	}

	public String getFinalidadesRelatorios() {
		return finalidadesRelatorios;
	}

	public void setTiposRelatorios(String tiposRelatorios) {
		this.finalidadesRelatorios = tiposRelatorios;
	}

	public ElaboradorDTO getElaboradorDto() {
		return elaboradorDto;
	}

	public void setElaboradorDto(ElaboradorDTO elaboradorDto) {
		this.elaboradorDto = elaboradorDto;
	}

	public boolean isEnviarPeloCorreio() {
		return enviarPeloCorreio;
	}

	public void setEnviarPeloCorreio(boolean enviarPeloCorreio) {
		this.enviarPeloCorreio = enviarPeloCorreio;
	}
}

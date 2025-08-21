package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;

public class MovimentacaoDTO  extends ModeloDTO {

	private static final long serialVersionUID = -7567411008242564525L;
	
	private Long id;
	
	private String Gl;
	
	private String cliente;
	
	private String codigoProduto;
	
	private String descricao;
	
	private String descricaoProduto;
	
	private String quantidadeCor;
	
	private String cor;
	
	private String direcaoFibra;
	
	private String formato;
	
	private String codigoAnterior;
	
	private String gramatura;
	
	private FibraDTO fibraDto;
	
	private PapelDTO papelDto;
	
	private SimNaoDTO sangriaSimNaoDto;
	
	private AbdbDTO abdbDto;
	
	private SimNaoDTO especificacaoSimNaoDto;
	
	private String laetus;
	
	private String obsEspecificacao;
	
	private String obsArte;
	
	private StatusDTO status;
	
	private String historicoOperacoes;
	
	private String gr;

	private String pasta;

	private String bobina;
	
	private String planaPapel;
	
	private String tipoProva;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGl() {
		return Gl;
	}

	public void setGl(String gl) {
		Gl = gl;
	}
	
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	
	public String getQuantidadeCor() {
		return quantidadeCor;
	}

	public void setQuantidadeCor(String quantidadeCor) {
		this.quantidadeCor = quantidadeCor;
	}
	
	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getDirecaoFibra() {
		return direcaoFibra;
	}

	public void setDirecaoFibra(String direcaoFibra) {
		this.direcaoFibra = direcaoFibra;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getCodigoAnterior() {
		return codigoAnterior;
	}

	public void setCodigoAnterior(String codigoAnterior) {
		this.codigoAnterior = codigoAnterior;
	}

	public String getGramatura() {
		return gramatura;
	}

	public void setGramatura(String gramatura) {
		this.gramatura = gramatura;
	}

	public String getLaetus() {
		return laetus;
	}

	public void setLaetus(String laetus) {
		this.laetus = laetus;
	}

	public String getObsEspecificacao() {
		return obsEspecificacao;
	}

	public void setObsEspecificacao(String obsEspecificacao) {
		this.obsEspecificacao = obsEspecificacao;
	}

	public String getObsArte() {
		return obsArte;
	}

	public void setObsArte(String obsArte) {
		this.obsArte = obsArte;
	}

	public StatusDTO getStatus() {
		return status;
	}

	public void setStatus(StatusDTO status) {
		this.status = status;
	}

	public FibraDTO getFibraDto() {
		return fibraDto;
	}

	public void setFibraDto(FibraDTO fibraDto) {
		this.fibraDto = fibraDto;
	}

	public PapelDTO getPapelDto() {
		return papelDto;
	}

	public void setPapelDto(PapelDTO papelDto) {
		this.papelDto = papelDto;
	}

	public SimNaoDTO getSangriaSimNaoDto() {
		return sangriaSimNaoDto;
	}

	public void setSangriaSimNaoDto(SimNaoDTO sangriaSimNaoDto) {
		this.sangriaSimNaoDto = sangriaSimNaoDto;
	}

	public AbdbDTO getAbdbDto() {
		return abdbDto;
	}

	public void setAbdbDto(AbdbDTO abdbDto) {
		this.abdbDto = abdbDto;
	}

	public SimNaoDTO getEspecificacaoSimNaoDto() {
		return especificacaoSimNaoDto;
	}

	public void setEspecificacaoSimNaoDto(SimNaoDTO especificacaoSimNaoDto) {
		this.especificacaoSimNaoDto = especificacaoSimNaoDto;
	}

	public String getHistoricoOperacoes() {
		return historicoOperacoes;
	}

	public void setHistoricoOperacoes(String historicoOperacoes) {
		this.historicoOperacoes = historicoOperacoes;
	}

	public String getGr() {
		return gr;
	}

	public void setGr(String gr) {
		this.gr = gr;
	}

	public String getPasta() {
		return pasta;
	}

	public void setPasta(String pasta) {
		this.pasta = pasta;
	}

	public String getBobina() {
		return bobina;
	}

	public void setBobina(String bobina) {
		this.bobina = bobina;
	}

	public String getPlanaPapel() {
		return planaPapel;
	}

	public void setPlanaPapel(String planaPapel) {
		this.planaPapel = planaPapel;
	}

	public String getTipoProva() {
		return tipoProva;
	}

	public void setTipoProva(String tipoProva) {
		this.tipoProva = tipoProva;
	}
}

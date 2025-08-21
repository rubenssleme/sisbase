package br.laramara.ti.sislaracommons.dtos.doacao;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;


public class ProjetoDTO extends ModeloDTO implements Identificavel{
	
	private static final long serialVersionUID = 6526881775584192408L;
	
	private Long id;
	private String nome;
	private String editalInvestimento;
	private String orgaoParceiroFinanciador;
	private String lei;
	private SimNaoDTO incentivadoDto;
	private SetorDTO classificacaoDto;
	private List<ResponsavelTecnicoDTO> responsaveisTecnicosDto;
	private String objetivoGeral;
	private String publicoAlvo;
	private ProfissionalDTO profissionalAdministrativoResponsavelDto;
	private PrestacaoContaDTO prestacaoContaDto;
	private List<PatrocinioDTO> patrociniosDto;
	private String numeroTermoFomentoParceria;
	private List<ArquivoDTO> arquivos;
	private String duracao;
	private String aditamento;
	private String dataInicialVigencia;
	private String dataFinalVigencia;
	private String valorTotal;
	private boolean ativo;
	private String valorProdutos;
	private String valorOutros;
	private String somaTotalProdutos;
	private List<RecursoDisponivelDTO> recursoDisponivelDto;
	private String resumoReservas;
	private String idadeMinima;
	private String idadeMaxima;
		
	public ProjetoDTO() {
		this.recursoDisponivelDto = new ArrayList<>();
		this.arquivos = new ArrayList<>();
		this.responsaveisTecnicosDto = new ArrayList<>();
		this.patrociniosDto = new ArrayList<>();
	}
	 	
	public Long getId() {
		return id;
	}

	public List<RecursoDisponivelDTO> getRecursoDisponivelDto() {
		return recursoDisponivelDto;
	}

	public String getValorTotal(){
		return valorTotal;
	}
	
	public String getDataInicialVigencia() {
		return dataInicialVigencia;
	}

	public String getDataFinalVigencia() {
		return dataFinalVigencia;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEditalInvestimento() {
		return editalInvestimento;
	}

	public void setEditalInvestimento(String editalInvestimento) {
		this.editalInvestimento = editalInvestimento;
	}

	public String getOrgaoParceiroFinanciador() {
		return orgaoParceiroFinanciador;
	}

	public void setOrgaoParceiroFinanciador(String orgaoParceiroFinanciador) {
		this.orgaoParceiroFinanciador = orgaoParceiroFinanciador;
	}

	public String getLei() {
		return lei;
	}

	public void setLei(String lei) {
		this.lei = lei;
	}
	
	public SimNaoDTO getIncentivadoDto() {
		return incentivadoDto;
	}

	public void setIncentivadoDto(SimNaoDTO incentivadoDto) {
		this.incentivadoDto = incentivadoDto;
	}

	public SetorDTO getClassificacaoDto() {
		return classificacaoDto;
	}

	public void setClassificacaoDto(SetorDTO classificacaoDto) {
		this.classificacaoDto = classificacaoDto;
	}
	
	public List<ResponsavelTecnicoDTO> getResponsaveisTecnicosDto() {
		return responsaveisTecnicosDto;
	}

	public void setResponsaveisTecnicosDto(List<ResponsavelTecnicoDTO> responsaveisTecnicosDto) {
		this.responsaveisTecnicosDto = responsaveisTecnicosDto;
	}

	public String getObjetivoGeral() {
		return objetivoGeral;
	}

	public void setObjetivoGeral(String objetivoGeral) {
		this.objetivoGeral = objetivoGeral;
	}

	public String getPublicoAlvo() {
		return publicoAlvo;
	}

	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}
	
	public ProfissionalDTO getProfissionalAdministrativoResponsavelDto() {
		return profissionalAdministrativoResponsavelDto;
	}

	public void setProfissionalAdministrativoResponsavelDto(ProfissionalDTO profissionalAdministrativoResponsavelDto) {
		this.profissionalAdministrativoResponsavelDto = profissionalAdministrativoResponsavelDto;
	}

	public PrestacaoContaDTO getPrestacaoContaDto() {
		return prestacaoContaDto;
	}

	public void setPrestacaoContaDto(PrestacaoContaDTO prestacaoContaDto) {
		this.prestacaoContaDto = prestacaoContaDto;
	}
	
	public List<PatrocinioDTO> getPatrociniosDto() {
		return patrociniosDto;
	}

	public void setPatrociniosDto(List<PatrocinioDTO> patrociniosDto) {
		this.patrociniosDto = patrociniosDto;
	}

	public String getNumeroTermoFomentoParceria() {
		return numeroTermoFomentoParceria;
	}

	public void setNumeroTermoFomentoParceria(String numeroTermoFomentoParceria) {
		this.numeroTermoFomentoParceria = numeroTermoFomentoParceria;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public String getAditamento() {
		return aditamento;
	}

	public void setAditamento(String aditamento) {
		this.aditamento = aditamento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setRecursoDisponivelDto(List<RecursoDisponivelDTO> recursoDisponivelDto) {
		this.recursoDisponivelDto = recursoDisponivelDto;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public void setDataInicialVigencia(String dataInicial) {
		this.dataInicialVigencia = dataInicial;
	}

	public void setDataFinalVigencia(String dataFinal) {
		this.dataFinalVigencia = dataFinal;
	}
	
	public String getValorProdutos() {
		return valorProdutos;
	}

	public void setValorProdutos(String valorProdutos) {
		this.valorProdutos = valorProdutos;
	}

	public String getValorOutros() {
		return valorOutros;
	}

	public void setValorOutros(String valorOutros) {
		this.valorOutros = valorOutros;
	}
	
	public String getSomaTotalProdutos() {
		return somaTotalProdutos;
	}

	public void setSomaTotalProdutos(String somaTotalProdutos) {
		this.somaTotalProdutos = somaTotalProdutos;
	}
	
	public String getResumoReservas() {
		return resumoReservas;
	}

	public void setResumoReservas(String resumoReservas) {
		this.resumoReservas = resumoReservas;
	}
	
	public List<ArquivoDTO> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<ArquivoDTO> arquivos) {
		this.arquivos = arquivos;
	}
	
	public String getIdadeMinima() {
		return idadeMinima;
	}

	public void setIdadeMinima(String idadeMinima) {
		this.idadeMinima = idadeMinima;
	}

	public String getIdadeMaxima() {
		return idadeMaxima;
	}

	public void setIdadeMaxima(String idadeMaxima) {
		this.idadeMaxima = idadeMaxima;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjetoDTO other = (ProjetoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		return nome + " - " + dataInicialVigencia +  " a " + dataFinalVigencia;
	}
}
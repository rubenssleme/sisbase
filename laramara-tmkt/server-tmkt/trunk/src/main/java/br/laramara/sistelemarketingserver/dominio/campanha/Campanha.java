package br.laramara.sistelemarketingserver.dominio.campanha;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.laramara.sistelemarketingcommons.Identificavel;
import br.laramara.sistelemarketingserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.sistelemarketingserver.dominio.seguranca.TamanhoMaximoGenerico;
import br.laramara.sistelemarketingserver.dominio.seguranca.Validavel;
import br.laramara.sistelemarketingserver.utilitarios.DataHoraUtils;

@Entity
@Table(name = "campanha")
public class Campanha extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.DESCRICAO_SUPER_LONGA)
	private String descricao;
	
	@Column(name = "data_inicio", nullable = false)
	private LocalDate dataInicio;

	@Column(name = "data_termino")
	private LocalDate dataTermino;
	
	@Column(name = "meta_financeira")
	private BigDecimal metaFinanceira;
	
	@Column(name = "meta_quantidade_ligacoes")
	private Integer metaQuantidadeLigacoes;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "campanha_criterio", joinColumns = { @JoinColumn(name = "id_campanha", referencedColumnName = "id") }, 
		inverseJoinColumns = { @JoinColumn(name = "id_criterio", referencedColumnName = "id") })
	private List<Criterio> criterios;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "campanha_alocacao_operador", joinColumns = {
			@JoinColumn(name = "id_campanha", referencedColumnName = "id") }, 
		inverseJoinColumns = {@JoinColumn(name = "id_alocacao_operador", referencedColumnName = "id") })
	private List<AlocacaoOperador> alocacoesOperadores;

	public Long getId() {
		return id;
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
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataInicio() {
		return DataHoraUtils.formatarData(dataInicio);
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataInicio);
	}

	public String getDataTermino() {
		return DataHoraUtils.formatarData(dataTermino);
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataTermino);
	}
	
	public BigDecimal getMetaFinanceira() {
		return metaFinanceira;
	}

	public void setMetaFinanceira(BigDecimal metaFinanceira) {
		this.metaFinanceira = metaFinanceira;
	}
	
	public Integer getMetaQuantidadeLigacoes() {
		return metaQuantidadeLigacoes;
	}

	public void setMetaQuantidadeLigacoes(Integer metaQuantidadeLigacoes) {
		this.metaQuantidadeLigacoes = metaQuantidadeLigacoes;
	}
	
	public List<Criterio> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<Criterio> criterios) {
		this.criterios = criterios;
	}
	
	public List<AlocacaoOperador> getAlocacoesOperadores() {
		return alocacoesOperadores;
	}

	public void setAlocacoesOperadores(List<AlocacaoOperador> alocacoesOperadores) {
		this.alocacoesOperadores = alocacoesOperadores;
	}
	
	public boolean ativo() {
		return DataHoraUtils.estaNoPeriodo(dataInicio, dataTermino);
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (estaComCampoNuloOuVazio(nome)) {
			adicionarErro("Insira um nome válido.");
		}
		if (tamanhoMaximoViolado(nome, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um nome contendo até " + TamanhoMaximoGenerico.NOME + " caracteres.");
		}
		if (tamanhoMaximoViolado(descricao, TamanhoMaximoGenerico.DESCRICAO_SUPER_LONGA)) {
			adicionarErro("Insira uma descrição contendo até " + TamanhoMaximoGenerico.DESCRICAO_SUPER_LONGA + " caracteres.");
		}
		if (dataInicio == null) {
			adicionarErro("Insira uma data de início.");
		}
		if (DataHoraUtils.dataInvalida(dataInicio)) {
			adicionarErro("Insira uma data de início válida.");
		}
		if (DataHoraUtils.dataInvalida(dataTermino)) {
			adicionarErro("Insira uma data de término válida.");
		}
		if (DataHoraUtils.datasForaDeOrdem(dataInicio, dataTermino)) {
			adicionarErro("Insira uma data de início anterior a data de término.");
		}
	}

	@Override
	public String toString() {
		return "Campanha [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", dataInicio=" + getDataInicio()
				+ ", dataTermino=" + getDataTermino() + ", metaFinanceira=" + getMetaFinanceira()
				+ ", metaQuantidadeLigacoes=" + getMetaQuantidadeLigacoes() + ", criterios=" +  getCriterios() 
				+ ", alocacoesOperadores=" + getAlocacoesOperadores() + "]";
	}
}

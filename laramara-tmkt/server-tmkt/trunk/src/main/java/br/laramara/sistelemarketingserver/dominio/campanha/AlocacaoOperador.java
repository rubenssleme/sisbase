package br.laramara.sistelemarketingserver.dominio.campanha;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.sistelemarketingcommons.Identificavel;
import br.laramara.sistelemarketingserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;
import br.laramara.sistelemarketingserver.dominio.seguranca.Validavel;

@Entity
@Table(name = "alocacao_operador")
public class AlocacaoOperador extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_conta_acesso")
	private ContaAcesso contaAcesso;

	@Column(name = "meta_financeira")
	private BigDecimal metaFinanceira;

	@Column(name = "meta_quantidade_ligacoes")
	private Integer metaQuantidadeLigacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ContaAcesso getContaAcesso() {
		return contaAcesso;
	}

	public void setContaAcesso(ContaAcesso contaAcesso) {
		this.contaAcesso = contaAcesso;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (contaAcesso == null) {
			adicionarErro("Insira um operador.");
		}
	}

	@Override
	public String toString() {
		return "AlocacaoOperador [id=" + id + ", contaAcesso=" + contaAcesso + ", metaFinanceira=" + metaFinanceira
				+ ", metaQuantidadeLigacoes=" + metaQuantidadeLigacoes + "]";
	}
}

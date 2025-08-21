package br.laramara.ti.sislaraserver.dominio.doacao;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;

@Entity
@Table(name = "recurso_disponivel")
public class RecursoDisponivel extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_recurso", nullable = false)
	private Recurso recurso;

	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;

	@Column(name = "valor_unitario", nullable = false)
	private BigDecimal valorUnitario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
	
	public String getQuantidade() {
		return NumeroUtils.obterTexto(quantidade);
	}
	
	public Integer obterQuantidadeDisponiveParaDoacao() {
		return quantidade;
	}
	
	public void setQuantidade(String quantidade) {
		this.quantidade = NumeroUtils.retornaInteiroOuInvalido(quantidade);
	}

	public String getValorUnitario() {
		return DinheiroUtils.obterDinheiro(valorUnitario);
	}

	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = DinheiroUtils.obterDinheiroOuInvalido(valorUnitario);
	}

	public String obterValorTotal() {
		return DinheiroUtils.obterDinheiro(valorUnitario.multiply(BigDecimal.valueOf(quantidade)));
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (recurso == null) {
			adicionarErro("Insira um Recurso.");
		}
		
		if (quantidade == null || NumeroUtils.numeroInteiroInvalido(quantidade)) {
			adicionarErro("Insira uma Quantidade válida.");
		}
		
		if (valorUnitario == null || (valorUnitario != null && valorUnitario.equals(DinheiroUtils.obterDinheiroInvalido()))) {
			adicionarErro("Insira uma Valor válido.");
		}
	}
}

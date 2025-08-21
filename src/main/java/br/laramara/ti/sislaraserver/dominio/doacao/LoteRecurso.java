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
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;

@Entity
@Table(name = "lote_recurso")
public class LoteRecurso extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_recurso", nullable = false)
	private Recurso recurso;
	
	@Column(name = "quantidade", length = TamanhoMaximoGenerico.QUANTIDADE_RECURSO_MINIMO, nullable = false)
	private Integer quantidade;
	
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

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

	public void setQuantidade(String quantidade) {
		this.quantidade = NumeroUtils.retornaInteiroOuInvalido(quantidade);
	}
	
	public BigDecimal obterValor(){
		return valor;
	}
	
	public String getValor() {
		return DinheiroUtils.obterDinheiro(valor);
	}

	public void setValor(String valor) {
		this.valor = DinheiroUtils.obterDinheiroOuInvalido(valor);
	}
	
	private void validarObrigatoriedade() {
		if (recurso == null) {
			adicionarErro("Insira um Recurso.");
		}
		
		if (quantidade == null || NumeroUtils.numeroInteiroInvalido(quantidade)) {
			adicionarErro("Insira uma Quantidade válida.");
		}
		
		if (valor == null || (valor != null && valor.equals(DinheiroUtils.obterDinheiroInvalido()))) {
			adicionarErro("Insira uma Valor válido.");
		}
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
	}
	
	@Override
	public String toString() {
		return "LoteRecurso [id=" + id + ", recurso=" + recurso
				+ ", quantidade=" + quantidade + ", valor=" + DinheiroUtils.obterDinheiro(valor) + "]";
	}
}
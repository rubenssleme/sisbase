package br.laramara.ti.sislaraserver.dominio.usuario;

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
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "custo")
public class Custo extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_item_custo")
	private ItemCusto itemCusto;

	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public ItemCusto getItemCusto() {
		return itemCusto;
	}

	public void setItemCusto(ItemCusto itemCusto) {
		this.itemCusto = itemCusto;
	}

	public String getValor() {
		return DinheiroUtils.obterDinheiro(valor);
	}

	public void setValor(String valor) {
		this.valor = DinheiroUtils.obterDinheiroOuInvalido(valor);
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (itemCusto == null) {
			adicionarErro("Insira um Item de Custo.");
		}
		if (valor == null
				|| (valor != null && valor.equals(DinheiroUtils
						.obterDinheiroInvalido()))) {
			adicionarErro("Insira um Valor de Custo válido.");
		}
	}

	@Override
	public String toString() {
		return "Custo [id=" + id + ", itemCusto=" + itemCusto + ", valor="
				+ valor + "]";
	}
}

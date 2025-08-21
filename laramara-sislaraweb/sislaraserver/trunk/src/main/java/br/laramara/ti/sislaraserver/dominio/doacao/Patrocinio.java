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

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "patrocinio")
public class Patrocinio extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa;

	@Column(name = "valor")
	private BigDecimal valor;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getValor() {
		return DinheiroUtils.obterDinheiro(valor);
	}

	public void setValor(String valor) {
		this.valor = DinheiroUtils.obterDinheiroOuInvalido(valor);
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
	}
	
	private void validarObrigatoriedade(){
		if (empresa == null){
			adicionarErro("Insira uma Empresa.");
		}
		if (valor == null || (valor != null && valor.equals(DinheiroUtils.obterDinheiroInvalido()))) {
			adicionarErro("Insira um Valor de patrocínio válido.");
		}
	}

	@Override
	public String toString() {
		return "Patrocinio [id=" + id + ", empresa=" + empresa + ", valor=" + DinheiroUtils.obterDinheiro(valor) + "]";
	}

	
}

package br.laramara.sistelemarketingserver.dominio.doacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.sistelemarketingcommons.Identificavel;
import br.laramara.sistelemarketingserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.sistelemarketingserver.dominio.seguranca.TamanhoMaximoGenerico;
import br.laramara.sistelemarketingserver.dominio.seguranca.Validavel;
import br.laramara.sistelemarketingserver.utilitarios.DataHoraUtils;

@Entity
@Table(name = "valor_detalhado")
public class ValorDetalhado extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "metodo", length = TamanhoMaximoGenerico.METODO, nullable = false)
	private Metodo metodo;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_retirada", length = TamanhoMaximoGenerico.TIPO_RETIRADA, nullable = false)
	private TipoRetirada tipoRetirada;

	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	@Column(name = "data_hora_efetuacao", nullable = false)
	protected LocalDateTime dataEfetuacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Metodo getMetodo() {
		return metodo;
	}

	public void setMetodo(Metodo metodo) {
		this.metodo = metodo;
	}

	public TipoRetirada getTipoRetirada() {
		return tipoRetirada;
	}

	public void setTipoRetirada(TipoRetirada tipoRetirada) {
		this.tipoRetirada = tipoRetirada;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataEfetuacao() {
		return DataHoraUtils.converterParaDate(dataEfetuacao);
	}

	public void setDataEfetuacao(Date dataEfetuacao) {
		this.dataEfetuacao = DataHoraUtils.converterParaLocalDateTime(dataEfetuacao);
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (metodo == null) {
			adicionarErro("Insira um método.");
		}
		if (tipoRetirada == null) {
			adicionarErro("Insira um tipo de retirada.");
		}
		if (valor == null) {
			adicionarErro("Insira um valor de doação.");
		}
		if (dataEfetuacao == null) {
			adicionarErro("Insira uma data de efetuação.");
		}
	}
	
	@Override
	public String toString() {
		return "ValorDetalhado [id=" + id + ", metodo=" + metodo + ", tipoRetirada=" + tipoRetirada + ", valor="
				+ getValor() + ", dataEfetuacao=" + getDataEfetuacao() + "]";
	}
}

package br.laramara.ti.sislaraserver.dominio.doacao;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;

@Entity
@Table(name = "reserva")
public class Reserva extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_demanda", nullable = false)
	private Demanda demanda;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", nullable = false)
	private Calendar data;
	
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	public Reserva() {
	}

	public Reserva(Demanda demanda) {
		super();
		this.demanda = demanda;
		this.data = MaquinaTempo.obterInstancia().obterCalendarioAtual();
		this.valor = demanda.getRecurso().getValor();
	}

	public Recurso getRecurso(){
		return demanda.getRecurso();
	}
	
	public Demanda getDemanda() {
		return demanda;
	}

	public BigDecimal getValor() {
		return valor;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", demanda=" + demanda + ", data=" + data + ", valor=" + valor + "]";
	}
}

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

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

@Entity
@Table(name = "captacao")
public class Captacao extends Validavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", nullable = false)
	private Calendar data;
	
	@ManyToOne
	@JoinColumn(name = "id_conta_acesso")
	private ContaAcesso contaAcesso;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "cancelada")
	private boolean cancelada;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_recibo")
	private Recibo recibo;
	
	public Captacao(){
		
	}
	
	public Captacao(BigDecimal valor, Recibo recibo, ContaAcesso contaAcesso) {
		super();
		this.data = MaquinaTempo.obterInstancia().obterCalendarioAtual();
		this.recibo = recibo;
		this.contaAcesso = contaAcesso;
		this.valor = valor;
	}
	
	public BigDecimal obterValor(){
		return valor;
	}

	public boolean isCancelada() {
		return cancelada;
	}
	
	public boolean eReciboNumero(Long numeroRecibo){
		return recibo.getId().equals(numeroRecibo);
	}

	@Override
	public String toString() {
		return "Captacao [id=" + id + ", data=" + DataHoraUtils.formatarData(data) + ", contaAcesso="
				+ contaAcesso + ", valor=" + DinheiroUtils.obterDinheiro(valor) +  ", recibo=" + recibo + ", cancelada=" + cancelada + "]";
	}
}

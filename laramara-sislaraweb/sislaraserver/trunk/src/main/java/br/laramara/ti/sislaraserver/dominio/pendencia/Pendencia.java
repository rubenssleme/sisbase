package br.laramara.ti.sislaraserver.dominio.pendencia;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "pendencia")
@DiscriminatorColumn(name = "tipo", discriminatorType=DiscriminatorType.STRING)
public abstract class Pendencia implements Comparavel, Descritivel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", length = TamanhoMaximoGenerico.TIPO_PENDENCIA, nullable = false, insertable = false, updatable = false)
	private TipoPendencia tipoPendencia;

	@Column(name = "resolvida")
	private boolean resolvida;
		
	@Column(name = "obs", length = TamanhoMaximoGenerico.OBS)
	private String obs;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "pendencia_profissional", joinColumns = {
			@JoinColumn(name = "id_pendencia", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_profissional", referencedColumnName = "id") })
	protected List<Profissional> profissionaisAfetados;

	protected Horario horario;
	
	public Pendencia(){
		horario = new Horario();
	}
	
	public Long getId() {
		return id;
	}

	protected boolean igualOuAnteriorADataAtual(Calendar data) {
		Calendar dataAtual = MaquinaTempo.obterInstancia().obterCalendarioAtual();
		return data.equals(dataAtual) || data.before(dataAtual);
	}
	
	public TipoPendencia getTipoPendencia() {
		return tipoPendencia;
	}

	public void marcarComoResolvida() {
		resolvida = true;
		obs = "Removida automaticamente pelo sistema.";
	}

	public void marcarComoResolvida(String mensagem) {
		resolvida = true;
		obs = mensagem;
	}

	public boolean eExcessoDeFaltas() {
		return tipoPendencia.equals(TipoPendencia.ENVIO_LISTA_ESPERA_EXCESSO_DE_FALTAS);
	}
	
	public boolean eReuniaoDeIntegracao() {
		return tipoPendencia.equals(TipoPendencia.TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO)
				|| tipoPendencia.equals(TipoPendencia.INCLUSAO_DE_REUNIAO_DE_INTEGRACAO);
	}

	public boolean eAtendimentoIndividualUsuarioOuPreCadastro() {
		return eAtendimentoIndividualUsuario() || eAtendimentoIndividualPreCadastro();
	}
	
	public boolean eAtendimentoIndividualUsuario() {
		return tipoPendencia.equals(TipoPendencia.ATENDIMENTO_INDIVIDUAL_USUARIO);
	}
	
	public boolean eAtendimentoIndividualPreCadastro() {
		return tipoPendencia.equals(TipoPendencia.ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO);
	}

	public boolean eAtendimentoDeGrupo() {
		return tipoPendencia.equals(TipoPendencia.ATENDIMENTO_GRUPO);
	}

	public Horario getHorario() {
		return horario;
	}
	
	public boolean estaResolvida() {
		return resolvida;
	}
	
	public static final Comparator<Pendencia> obterComparador() {
		return new Comparator<Pendencia>() {
			public int compare(Pendencia o1, Pendencia o2) {
				return (o1.getDataParaComparacao().compareTo(o2
						.getDataParaComparacao()));
			}
		};
	}

	@Override
	public String toString() {
		return "id=" + id + ", tipoPendencia=" + tipoPendencia + ", resolvida=" + resolvida + ", obs=" + obs
				+ ", profissionaisAfetados=" + profissionaisAfetados + ", horario=" + horario;
	}
}

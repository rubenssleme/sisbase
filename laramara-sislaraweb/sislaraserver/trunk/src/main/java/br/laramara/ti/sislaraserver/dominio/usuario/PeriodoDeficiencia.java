package br.laramara.ti.sislaraserver.dominio.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;

@Entity
@Table(name = "periodo_deficiencia")
public class PeriodoDeficiencia extends Historico implements
ValidavelObrigatoriedadeETamanhoMaximo {
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_deficiencia")
	private Deficiencia deficiencia;
	
	@ManyToOne()
	@JoinColumn(name = "cid")
	private Cid cid;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "epoca_incidencia", length = TamanhoMaximoUsuario.EPOCA_INCIDENCIA)
	private EpocaIncidencia epocaIncidencia;
	
	@Column(name= "excluido")
	private boolean excluido;
	
	@OneToMany(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "periodo_deficiencia_etiologia", joinColumns = { @JoinColumn(name = "id_periodo_deficiencia", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_etiologia", referencedColumnName = "id") })
	private List<Etiologia> etiologias;
	
	public PeriodoDeficiencia(){
		etiologias = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Deficiencia getDeficiencia() {
		return deficiencia;
	}

	public void setDeficiencia(Deficiencia deficiencia) {
		this.deficiencia = deficiencia;
	}
	
	public Cid getCid() {
		return cid;
	}

	public void setCid(Cid cid) {
		this.cid = cid;
	}

	public EpocaIncidencia getEpocaIncidencia() {
		return epocaIncidencia;
	}

	public void setEpocaIncidencia(EpocaIncidencia epocaIncidencia) {
		this.epocaIncidencia = epocaIncidencia;
	}

	public List<Etiologia> getEtiologias() {
		return etiologias;
	}

	public void setEtiologias(List<Etiologia> etiologias) {
		this.etiologias = etiologias;
	}

	public boolean excluido() {
		return excluido;
	}

	public void marcarExcluido() {
		this.excluido = true;
	}
	
	public boolean novo(){
		return id == null;
	}
	
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
	}
	
	private void validarObrigatoriedade(){
		if (deficiencia == null){
			adicionarErro("Insira a Deficiência.");
		}
		if (deficiencia != null && deficiencia.isEtiologiaObrigatorio() && etiologias.isEmpty()){
			adicionarErro("Insira a Etiologia.");
		}
		super.validarObrigatoriedadeEObrigatoriedadeDeDados();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodoDeficiencia other = (PeriodoDeficiencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public static void adicionarPeriodoDeficiencia(
			List<PeriodoDeficiencia> periodoDeficienciasExistentes,
			List<PeriodoDeficiencia> periodosDeficienciasAAdicionar) {
		for(PeriodoDeficiencia periodoDeficienciaAAdicionar : periodosDeficienciasAAdicionar){
			if (periodoDeficienciaAAdicionar.novo() || !periodoDeficienciasExistentes.contains(periodoDeficienciaAAdicionar)){
				periodoDeficienciasExistentes.add(periodoDeficienciaAAdicionar);
			}else{
				periodoDeficienciasExistentes.set(periodoDeficienciasExistentes.lastIndexOf(periodoDeficienciaAAdicionar), periodoDeficienciaAAdicionar);
			}
		}
		
		for(PeriodoDeficiencia periodoDeficiencia : periodoDeficienciasExistentes){
			if (!periodosDeficienciasAAdicionar.contains(periodoDeficiencia)){
				periodoDeficiencia.marcarExcluido();
			}
		}
	}

	public static List<PeriodoDeficiencia> obterPeriodoDeficiencia(
			List<PeriodoDeficiencia> periodoDeficiencias) {
		List<PeriodoDeficiencia> retorno = new ArrayList<PeriodoDeficiencia>();
		for (PeriodoDeficiencia periodoDeficiencia : periodoDeficiencias) {
			if (!periodoDeficiencia.excluido()) {
				retorno.add(periodoDeficiencia);
			}
		}
		return retorno;
	}
	
	@Override
	public String toString() {
		return "PeriodoDeficiencia [id=" + id + ", deficiencia=" + deficiencia
				+ ", etiologias=" + etiologias + ", cid = " + cid + ", epocaIncidencia = " + epocaIncidencia
				+ ", dataInicial="
				+ DataHoraUtils.formatarDataHora(dataInicialVigencia)
				+ ", dataFinal="
				+ DataHoraUtils.formatarDataHora(dataFinalVigencia) + "]";
	}
}

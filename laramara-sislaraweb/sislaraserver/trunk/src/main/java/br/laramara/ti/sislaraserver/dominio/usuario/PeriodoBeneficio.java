package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;

@Entity
@Table(name = "periodo_beneficio")
public class PeriodoBeneficio extends Historico implements
		ValidavelObrigatoriedadeETamanhoMaximo {
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_beneficio")
	private Beneficio beneficio;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGenerico.STATUS, nullable = false)
	private StatusBeneficio status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Beneficio getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}
	
	public StatusBeneficio getStatus() {
		return status;
	}

	public void setStatus(StatusBeneficio status) {
		this.status = status;
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
	}
	
	private void validarObrigatoriedade(){
		if (beneficio == null){
			adicionarErro("Insira o Beneficio.");
		}
		if (status == null){
			adicionarErro("Insira um Status.");
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
		PeriodoBeneficio other = (PeriodoBeneficio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PeriodoBeneficio [id=" + id + ", beneficio=" + beneficio + ", status=" + status 
				+ ", dataInicial=" + DataHoraUtils.formatarDataHora(dataInicialVigencia)
				+ ", dataFinal=" + DataHoraUtils.formatarDataHora(dataFinalVigencia) + "]";
	}
}

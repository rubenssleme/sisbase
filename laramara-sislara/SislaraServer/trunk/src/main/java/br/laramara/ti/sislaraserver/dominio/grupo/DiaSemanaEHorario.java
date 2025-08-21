package br.laramara.ti.sislaraserver.dominio.grupo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "dia_semana_e_horario")
public class DiaSemanaEHorario extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "dia_semana", length = TamanhoMaximoGrupo.DIA_SEMANA, nullable = false)
	private DiaSemana diaSemana;

	private Horario horario;

	public DiaSemanaEHorario(){
		this.horario = new Horario();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (diaSemana == null) {
			adicionarErro("Insira um dia da semana.");
		}
		
		horario.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (!horario.validado()) {
			adicionarErro(horario.obterErros());
		}
	}

	@Override
	public String toString() {
		return "DiaSemanaEHorario [id=" + id + ", diaSemana=" + diaSemana + ", horario=" + horario + "]";
	}
}

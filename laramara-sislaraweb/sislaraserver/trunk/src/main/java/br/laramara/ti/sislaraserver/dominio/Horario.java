package br.laramara.ti.sislaraserver.dominio;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Embeddable
public class Horario extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	@Column(name = "hora_inicio")
	private Time horaInicio;

	@Column(name = "hora_termino")
	private Time horaTermino;

	public Horario() {
	}

	public Horario(String horaInicio, String horaTermino) {
		this.horaInicio = DataHoraUtils.obterTempo(horaInicio);
		this.horaTermino = DataHoraUtils.obterTempo(horaTermino);
	}

	public String getHoraInicio() {
		return DataHoraUtils.obterHora(horaInicio);
	}

	public Time getHoraInicioTime() {
		return horaInicio;
	}
	
	public String getHoraTermino() {
		return DataHoraUtils.obterHora(horaTermino);
	}

	public Time getHoraTerminoTime() {
		return horaTermino;
	}
	
	public boolean estaAlgumHorarioPersonalizadoPreenchido() {
		return horaInicio != null || horaTermino != null;
	}
	
	public boolean estaPreenchido() {
		return horaInicio != null && horaTermino != null;
	}
	
	public boolean possuiMesmoHorario(Horario horarioGrupo) {
		return horarioGrupo.getHoraInicioTime().equals(horaInicio)
				&& horarioGrupo.getHoraTerminoTime().equals(horaTermino);
	}
	
	public boolean violaHorario(Horario horarioGrupo) {
		return (horaInicio.before(horarioGrupo.getHoraInicioTime())
				|| horaTermino.after(horarioGrupo.getHoraTerminoTime()));
	}

	public Time obterTempoDecorrido() {
		return horaInicio != null && horaTermino != null ? DataHoraUtils
				.obterTempoDecorrido(horaInicio, horaTermino) : new Time(0);
	}

	public PeriodoHorario periodo() {
		return DataHoraUtils.aposMeioPeriodo(horaInicio) ? PeriodoHorario.VESPERTINO : PeriodoHorario.MATUTINO;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (DataHoraUtils.horaInvalida((horaInicio))) {
			adicionarErro("Insira uma Hora de Início válida.");
		}
		if (DataHoraUtils.horaInvalida((horaTermino))) {
			adicionarErro("Insira uma Hora de Término válida.");
		}
		if (DataHoraUtils
				.horaTerminoAnteriorHoraInicio(horaInicio, horaTermino)) {
			adicionarErro("Insira uma Hora de Témino posterior a Hora de Início.");
		}
	}
	
	@Override
	public String toString() {
		return "Horario [horaInicio=" + horaInicio + ", horaTermino="
				+ horaTermino + "]";
	}
}

package br.laramara.sistelemarketingcommons.dtos.seguranca;

import br.laramara.sistelemarketingcommons.dtos.ItemComboboxComDescricao;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;

public class TurnoDTO extends ModeloDTO implements ItemComboboxComDescricao {

	private static final long serialVersionUID = -2437803202291845337L;

	private String turno;
	
	public TurnoDTO() {
	}

	public TurnoDTO(String turno) {
		setTurno(turno);
	}
	
	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return getTurno();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurnoDTO other = (TurnoDTO) obj;
		if (turno == null) {
			if (other.turno != null)
				return false;
		} else if (!turno.equals(other.turno))
			return false;
		return true;
	}

}

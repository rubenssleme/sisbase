package br.laramara.ti.sislaracommons.dtos;

public class HorarioDTO extends ModeloDTO {

	private static final long serialVersionUID = 1925524822812978748L;
	
	private String horaInicio;
	private String horaTermino;

	public HorarioDTO(){
	}
	
	public HorarioDTO(String horaInicio, String horaTermino) {
		super();
		this.horaInicio = horaInicio;
		this.horaTermino = horaTermino;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public String getHoraTermino() {
		return horaTermino;
	}

	@Override
	public String toString() {
		return horaInicio + " �s "+ horaTermino;
	}
}

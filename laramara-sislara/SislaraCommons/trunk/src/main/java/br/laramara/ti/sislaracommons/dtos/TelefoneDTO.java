package br.laramara.ti.sislaracommons.dtos;

public class TelefoneDTO extends ModeloDTO {

	private static final long serialVersionUID = -7738999307843365638L;

	private TipoTelefoneDTO tipoTelefoneDto;
	private String dddTelefone;

	public TelefoneDTO(TipoTelefoneDTO tipoTelefoneDto, String dddTelefone) {
		this.tipoTelefoneDto = tipoTelefoneDto;
		this.dddTelefone = dddTelefone;
	}

	public TipoTelefoneDTO getTipoTelefoneDto() {
		return tipoTelefoneDto;
	}

	public String getDDDTelefone() {
		return dddTelefone;
	}

	@Override
	public String toString() {
		return dddTelefone.length() > 2 ? tipoTelefoneDto + " (" + dddTelefone.subSequence(0, 2) + ")"
				+ dddTelefone.subSequence(2, dddTelefone.length()) : "";
	}
}

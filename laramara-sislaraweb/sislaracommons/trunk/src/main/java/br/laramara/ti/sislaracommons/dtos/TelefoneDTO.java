package br.laramara.ti.sislaracommons.dtos;

public class TelefoneDTO extends ModeloDTO {

	private static final long serialVersionUID = -7738999307843365638L;

	private TipoTelefoneDTO tipoTelefoneDto;
	private String dddTelefone;
	private String ramal;
	private String nomeContato;

	public TelefoneDTO() {
		super();
	}

	public TelefoneDTO(TipoTelefoneDTO tipoTelefoneDto, String dddTelefone, String ramal, String nomeContato) {
		this.tipoTelefoneDto = tipoTelefoneDto;
		this.dddTelefone = dddTelefone;
		this.ramal = ramal;
		this.nomeContato = nomeContato;
	}

	public TipoTelefoneDTO getTipoTelefoneDto() {
		return tipoTelefoneDto;
	}

	public String getDDDTelefone() {
		return dddTelefone;
	}

	public String getRamal() {
		return ramal;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	@Override
	public String toString() {
		return dddTelefone.length() > 2 ? tipoTelefoneDto + " (" + dddTelefone.subSequence(0, 2) + ")"
				+ dddTelefone.subSequence(2, dddTelefone.length()) 
				+ (ramal != null ? " " + ramal : "") 
				+ (nomeContato != null ? " " + nomeContato : "") : "";
	}
}

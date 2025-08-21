package br.laramara.ti.sislaracommons.dtos;

import java.util.ArrayList;
import java.util.List;

public class ContatoDTO extends ModeloDTO {

	private static final long serialVersionUID = -1081934251501276406L;
	
	private Long id;
	private List<TelefoneDTO> telefonesDto;
	private String ramal;
	private String nomeContato;
	private String email;
	
	public ContatoDTO(){
		telefonesDto = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TelefoneDTO> getTelefonesDto() {
		return telefonesDto;
	}

	public void setTelefonesDto(List<TelefoneDTO> telefonesDto) {
		if (telefonesDto != null){
			this.telefonesDto = telefonesDto;
		}
	}
	
	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return telefonesDto.toString().replace("[", "").replace("]", "");
	}
}

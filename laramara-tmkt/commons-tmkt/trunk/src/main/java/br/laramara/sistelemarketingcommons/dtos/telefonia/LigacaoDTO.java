package br.laramara.sistelemarketingcommons.dtos.telefonia;

import java.io.Serializable;

import br.laramara.sistelemarketingcommons.dtos.contato.TelefoneDTO;

public class LigacaoDTO implements Serializable {

	private static final long serialVersionUID = -1271799439094294577L;
	
	private String token;
	private TelefoneDTO telefoneDto;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TelefoneDTO getTelefoneDto() {
		return telefoneDto;
	}

	public void setTelefoneDto(TelefoneDTO telefoneDto) {
		this.telefoneDto = telefoneDto;
	}
}

package br.laramara.sistelemarketingcommons.dtos.seguranca;

import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.RamalDTO;

public class CredencialDTO extends ModeloDTO {
	private static final long serialVersionUID = -7707628886965980316L;

	private String usuario;
	private String senha;
	private RamalDTO ramalDto;

	public CredencialDTO() {
		super();
	}

	public CredencialDTO(String usuario, String senha, RamalDTO ramalDto) {
		this.usuario = usuario;
		this.senha = senha;
		this.ramalDto = ramalDto;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}

	public RamalDTO getRamalDto() {
		return ramalDto;
	}
}

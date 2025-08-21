package br.laramara.ti.sislaracommons.dtos.seguranca;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class CredencialExternaDTO extends ModeloDTO {
	private static final long serialVersionUID = 5060573219359232873L;
	
	private String email;
	private String senha;

	public CredencialExternaDTO() {
	}

	public CredencialExternaDTO(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "CredencialExternaDTO [email=" + email + ", senha=" + senha + "]";
	}

}

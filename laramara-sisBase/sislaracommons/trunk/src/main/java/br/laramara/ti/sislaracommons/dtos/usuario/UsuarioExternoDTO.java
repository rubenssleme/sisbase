package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class UsuarioExternoDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = -6890670949381185096L;

	private Long id;
	private String email;
	private String senha;
	private boolean bloqueado;

	@Override
	public Long getId() {
		return id;
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

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UsuarioExternoDTO [id=" + id + ", email=" + email + ", senha=" + senha + ", bloqueado=" + bloqueado
				+ "]";
	}

}
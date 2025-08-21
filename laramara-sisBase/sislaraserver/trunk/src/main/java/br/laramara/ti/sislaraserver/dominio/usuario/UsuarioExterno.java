package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "usuario_externo")
public class UsuarioExterno extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "email", length = TamanhoMaximoGenerico.EMAIL, nullable = false)
	private String email;
	@Column(name = "senha", nullable = false)
	private String senha;
	@Column(name = "bloqueado", nullable = false)
	private boolean bloqueado;
	@Column(name = "token", length = TamanhoMaximoGenerico.TOKEN)
	private String token;

	public UsuarioExterno() {
		bloqueado = false;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = TextoUtils.removerCaracteresInvalidosEAnularVazio(email);
	}

	public String getSenha() {
		return senha;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSenha(String senha) {
		configurarSenha(senha);
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private void configurarSenha(String senha) {
		if (senha != null && !TextoUtils.estaVazio(senha)) {
			this.senha = senha;
		}
	}

	public void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(email, TamanhoMaximoGenerico.EMAIL)) {
			adicionarErro("Insira um Email contendo até " + TamanhoMaximoGenerico.EMAIL + " caracteres.");
		}
	}

	public void validarObrigatoriedadeDeDados() {
		if (email == null || email.isEmpty()) {
			adicionarErro("Informe um e-mail.");
		}
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedadeDeDados();
		validarTamanhoMaximoDeDados();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioExterno other = (UsuarioExterno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioExterno [id=" + id + ", email=" + email + ", bloqueado=" + bloqueado + ", token=" + token + "]";
	}

}

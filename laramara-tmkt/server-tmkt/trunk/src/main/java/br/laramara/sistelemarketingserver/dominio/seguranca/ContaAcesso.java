package br.laramara.sistelemarketingserver.dominio.seguranca;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.sistelemarketingcommons.Identificavel;
import br.laramara.sistelemarketingcommons.utilitarios.Criptografia;
import br.laramara.sistelemarketingcommons.utilitarios.TextoUtils;
import br.laramara.sistelemarketingserver.dominio.telefonia.Ramal;


@Entity
@Table(name = "conta_acesso")
public class ContaAcesso extends Validavel implements Serializable, Identificavel {

	private static final long serialVersionUID = -99748161922449073L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;
	
	@Column(name = "login", length = TamanhoMaximoGenerico.DESCRICAO, nullable = false)
	private String login;

	@Column(name = "senha", length = TamanhoMaximoGenerico.DESCRICAO, nullable = false)
	private String senha;

	@Column(name = "ativo", nullable = false)
	private boolean ativo;
	
	@Column(name = "token", length = TamanhoMaximoGenerico.TOKEN)
	private String token;
	
	@ManyToOne
	@JoinColumn(name = "id_nivel")
	private Nivel nivel;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "turno", length = TamanhoMaximoGenerico.TURNO)
	private Turno turno;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ramal", length = TamanhoMaximoGenerico.RAMAL)
	private Ramal ramal;
	
	
	public ContaAcesso() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setSenha(String senha) {
		configurarSenha(senha);
	}

	public String getSenha() {
		return senha;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	
	public boolean possuiPermissao(Permissao permissaoNecessaria) {
		return nivel.possuiPermissao(permissaoNecessaria);
	}
	
	public List<Permissao> obterPermissoes() {
		return nivel.getPermissoes();
	}
	
	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Ramal getRamal() {
		return ramal;
	}

	public void setRamal(Ramal ramal) {
		this.ramal = ramal;
	}

	public boolean possuiNivelOperadorAtivo() {
		return ativo && nivel.eOperador();
	}

	public String obterRamal() {
		return ramal.getNumero();
	}
	
	private void configurarSenha(String senha) {
		if (senha != null && !TextoUtils.estaVazio(senha)) {
			this.senha = Criptografia.converterParaMD5(senha);
		}
	}
	
	public boolean eOperador() {
		return nivel.eOperador();
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (estaComCampoNuloOuVazio(nome)) {
			adicionarErro("Insira um nome válido.");
		}
		if (estaComCampoNuloOuVazio(login)) {
			adicionarErro("Insira um login válido.");
		}
		if (estaComCampoNuloOuVazio(senha)) {
			adicionarErro("Insira uma senha válida.");
		}
		if (tamanhoMaximoViolado(nome, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um nome contendo até " + TamanhoMaximoGenerico.NOME + " caracteres.");
		}
		if (tamanhoMaximoViolado(login, TamanhoMaximoGenerico.DESCRICAO)) {
			adicionarErro("Insira um login contendo até "
					+ TamanhoMaximoGenerico.DESCRICAO + " caracteres.");
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaAcesso other = (ContaAcesso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContaAcesso [id=" + id + ", login=" + login + ", nome=" + nome + ", ativo=" + ativo + ", token="
				+ token + ", nivel=" + nivel + ", turno=" + turno + ", ramal=" +  ramal + "]";
	}
}

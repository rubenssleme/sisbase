package br.laramara.ti.sislaraserver.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "contato")
public class Contato extends Validavel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "telefone", joinColumns = @JoinColumn(name = "id_contato"))
	private List<Telefone> telefones;

	@Column(name = "ramal", length = TamanhoMaximoGenerico.RAMAL)
	private String ramal;

	@Column(name = "nome_contato", length = TamanhoMaximoGenerico.NOME)
	private String nomeContato;

	@Column(name = "email", length = TamanhoMaximoGenerico.EMAIL)
	private String email;

	public Contato(){
		this.telefones = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		if (telefones != null){
			this.telefones = telefones;
		}
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = TextoUtils.removerCaracteresInvalidosEAnularVazio(ramal);
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = TextoUtils.anularVazio(nomeContato);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = TextoUtils.removerCaracteresInvalidosEAnularVazio(email);
	}

	public void validarTamanhoMaximoDeDados() {
		validarTamanhoMaximoDeDadosTelefones();
		
		if (tamanhoMaximoViolado(ramal, TamanhoMaximoGenerico.RAMAL)) {
			adicionarErro("Insira um Ramal contendo até "
					+ TamanhoMaximoGenerico.RAMAL + " caracteres.");
		}
		if (tamanhoMaximoViolado(nomeContato, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um Nome de Contato contendo até "
					+ TamanhoMaximoGenerico.NOME + " caracteres.");
		}
		if (tamanhoMaximoViolado(email, TamanhoMaximoGenerico.EMAIL)) {
			adicionarErro("Insira um Email contendo até "
					+ TamanhoMaximoGenerico.EMAIL + " caracteres.");
		}
	}

	private void validarTamanhoMaximoDeDadosTelefones() {
		for (Telefone telefone : telefones) {
			telefone.validarTamanhoMaximoDeDados();
			if (!telefone.validado()) {
				adicionarErro(telefone.obterErros());
			}
		}
	}
	
	public void validarObrigatoriedadeETamanhoMaximoDeDados(){
		validarObrigatoriedade();
		validarTamanhoMaximoDeDados();
	}

	private void validarObrigatoriedade() {
		if (telefones.size() <= 0) {
			adicionarErro("Insira pelo menos um Telefone.");
		}
		for (Telefone telefone : telefones) {
			telefone.validarObrigatoriedade();
			if (!telefone.validado()) {
				adicionarErro(telefone.obterErros());
			}
		}
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", telefones=" + telefones + ", ramal="
				+ ramal + ", nomeContato=" + nomeContato + ", email=" + email
				+ "]";
	}
}

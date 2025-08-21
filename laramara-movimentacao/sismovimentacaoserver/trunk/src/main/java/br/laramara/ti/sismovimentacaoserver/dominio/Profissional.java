package br.laramara.ti.sismovimentacaoserver.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "profissional")
public class Profissional implements Serializable{

	private static final long serialVersionUID = 6514113531987536607L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME)
	private String nome;
	
	@Column(name = "chapa", length = TamanhoMaximoGenerico.CHAPA)
	private String chapa;
	
	@Column(name = "habilitado")
	private boolean habilitado;

	@Column(name = "profissional")
	private boolean profissional;
		
	public Profissional() {
		marcarComoHabilitado();
	}

	public Profissional(Long id, String nome, String chapa) {
		super();
		this.id = id;
		this.nome = nome;
		this.chapa = chapa;
		marcarComoHabilitado();
	}
	
	private void marcarComoHabilitado(){
		this.habilitado = true;
		this.profissional = true;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getChapa() {
		return chapa;
	}

	public boolean possuiChapa(){
		return chapa != null;
	}
	
	public boolean possuiIdentificacaoIgual(Profissional profissional) {
		return this.getId().equals(profissional.getId());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profissional other = (Profissional) obj;
		if (chapa == null) {
			if (other.chapa != null)
				return false;
		} else if (!chapa.equals(other.chapa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profissional [id=" + id + ", nome=" + nome + ", chapa=" + chapa
				+ "]";
	}
}

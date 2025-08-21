package br.laramara.ti.sislaraserver.dominio.grupo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "local_atendimento")
public class LocalAtendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;

	public LocalAtendimento() {
	}

	public LocalAtendimento(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "LocalAtendimento [id=" + id + ", nome=" + nome + "]";
	}
}

package br.laramara.ti.sislaraserver.dominio.escola;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "diretoria_ensino")
public class DiretoriaEnsino {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;

	public DiretoriaEnsino() {
	
	}

	public DiretoriaEnsino(Long id, String nome) {
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
		return "DiretoriaEnsino [id=" + id + ", nome=" + nome + "]";
	}
}

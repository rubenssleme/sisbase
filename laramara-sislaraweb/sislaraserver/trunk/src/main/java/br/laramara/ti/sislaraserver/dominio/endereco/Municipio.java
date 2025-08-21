package br.laramara.ti.sislaraserver.dominio.endereco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "municipio")
public class Municipio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = TamanhoMaximoEndereco.MUNICIPIO, nullable = false)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "uf", length = TamanhoMaximoEndereco.UF, nullable = false)
	private UF uf;

	public Municipio() {
	}
	
	public Municipio(Long id, String nome, UF uf) {
		this();
		this.id = id;
		this.nome = nome;
		this.uf = uf;
	}
	
	public Long getId(){
		return id;
	}

	public UF getUf() {
		return uf;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Municipio other = (Municipio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Municipio [id=" + id + ", nome=" + nome + "]";
	}
}

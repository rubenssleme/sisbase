package br.laramara.ti.sislaraserver.dominio.escola;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "escolaridade")
public class Escolaridade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.DESCRICAO, nullable = false)
	private String descricao;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "escolaridade_serie", joinColumns = { @JoinColumn(name = "id_escolaridade", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_serie", referencedColumnName = "id") })
	private List<Serie> series;

	public Escolaridade() {
	}

	public Escolaridade(Long id, String descricao) {
		this();
		this.id = id;
		this.descricao = descricao;
		this.series = new ArrayList<>();
	}
	
	public Escolaridade(Long id, String descricao, List<Serie> series) {
		this(id, descricao);
		this.series = series;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public List<Serie> getSeries() {
		return series;
	}
	
	public void setSeries(List<Serie> series) {
		this.series = series ;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Escolaridade other = (Escolaridade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Escolaridade [id=" + id + ", descricao=" + descricao
				+ ", series = " + series + "]";
	}
}

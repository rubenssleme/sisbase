package br.laramara.ti.sislaraserver.dominio.doacao;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "descricao_recurso")
public class DescricaoRecurso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String descricao;

	public DescricaoRecurso() {
	
	}

	public DescricaoRecurso(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static final Comparator<DescricaoRecurso> obterComparador() {
		return new Comparator<DescricaoRecurso>() {
			public int compare(DescricaoRecurso o1, DescricaoRecurso o2) {
				return (o1.getDescricao().compareTo(o2.getDescricao()));
			}
		};
	}
	
	@Override
	public String toString() {
		return "DescricaoRecurso [id=" + id + ", descricao=" + descricao + "]";
	}
}

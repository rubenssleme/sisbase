package br.laramara.sistelemarketingserver.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.sistelemarketingserver.dominio.seguranca.TamanhoMaximoGenerico;

@Entity
@Table(name = "estado")
public class Estado {

	@Id
	private Long id;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.DESCRICAO, nullable = false)
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", descricao=" + descricao + "]";
	}
}

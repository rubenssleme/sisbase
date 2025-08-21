package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "CID10")
public class Cid {

	@Id
	@Column(name = "cid", nullable = false)
	private String id;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String descricao;

	public Cid() {
	}

	public Cid(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "CID [id=" + id + ", descricao=" + descricao + "]";
	}
}

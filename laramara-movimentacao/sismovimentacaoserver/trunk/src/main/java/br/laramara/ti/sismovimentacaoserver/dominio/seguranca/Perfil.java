package br.laramara.ti.sismovimentacaoserver.dominio.seguranca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {
	
	private static final long serialVersionUID = -5057261816013899020L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = 100)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfil_permissoes", joinColumns = @JoinColumn(name = "id_perfil"))
	@Column(name = "permissao", length = 100)
	private List<Permissao> permissoes;

	public Perfil() {
		this.permissoes = new ArrayList<>();
	}

	public Perfil(Long id, String descricao) {
		this();
		this.id = id;
		this.descricao = descricao;
	}
	
	public Perfil(Long id, String descricao, List<Permissao> permissoes) {
		this(id, descricao);
		this.permissoes = permissoes;
	}
	
	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void adicionarPermissao(Permissao permissao) {
		permissoes.add(permissao);
	}

	public boolean possuiPermissao(Permissao permissaoNecessaria) {
		return permissoes.contains(permissaoNecessaria) ? true : false;
	}

	public List<Permissao> getPermissoes() {
		Collections.sort(permissoes, Permissao.obterComparador());
		return permissoes;
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", descricao=" + descricao
				+ ", permissoes=" + permissoes + "]";
	}
}

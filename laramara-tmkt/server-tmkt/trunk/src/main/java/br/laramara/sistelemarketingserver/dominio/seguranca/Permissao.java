package br.laramara.sistelemarketingserver.dominio.seguranca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.sistelemarketingcommons.dtos.seguranca.IdentificacaoPermissao;

@Entity
@Table(name = "permissao")
public class Permissao{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao", length = TamanhoMaximoGenerico.DESCRICAO, nullable = false)
	private String descricao;
	
	public Permissao() {
	}
	
	public Permissao(Long id) {
		super();
		this.id = id;
	}

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
	
	public static Permissao obterPermissaoContaAcessoEditar() {
		return new Permissao(IdentificacaoPermissao.ID_PERMISSAO_CONTA_ACESSO_EDITAR);
	}
	
	public static Permissao obterPermissaoNivelEditar() {
		return new Permissao(IdentificacaoPermissao.ID_PERMISSAO_NIVEL_EDITAR);
	}
	
	public static Permissao obterPermissaoCampanhaEditar() {
		return new Permissao(IdentificacaoPermissao.ID_PERMISSAO_CAMPANHA_EDITAR);
	}

	public static Permissao obterPermissaoDoacaoEfetuar() {
		return new Permissao(IdentificacaoPermissao.ID_PERMISSAO_DOACAO_EFETUAR);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permissao other = (Permissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Permissao [id=" + id + ", descricao=" + descricao + "]";
	}
}

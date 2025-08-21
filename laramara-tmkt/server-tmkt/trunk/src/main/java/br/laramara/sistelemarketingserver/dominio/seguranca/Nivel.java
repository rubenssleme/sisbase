package br.laramara.sistelemarketingserver.dominio.seguranca;

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

import br.laramara.sistelemarketingcommons.dtos.seguranca.IdentificacaoNivel;
import br.laramara.sistelemarketingserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;

@Entity
@Table(name = "nivel")
public class Nivel extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao", length = TamanhoMaximoGenerico.DESCRICAO, nullable = false)
	private String descricao;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "nivel_permissao", joinColumns = { @JoinColumn(name = "id_nivel", referencedColumnName = "id") }, 
		inverseJoinColumns = { @JoinColumn(name = "id_permissao", referencedColumnName = "id") })
	private List<Permissao> permissoes;
	
	public Nivel() {
		permissoes = new ArrayList<>();
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
	
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public boolean possuiPermissao(Permissao permissaoNecessaria) {
		return permissoes.contains(permissaoNecessaria);
	}
	
	public boolean eOperador() {
		return id.equals(IdentificacaoNivel.ID_NIVEL_OPERADOR);
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (estaComCampoNuloOuVazio(descricao)) {
			adicionarErro("Insira um nível.");
		}
		if (tamanhoMaximoViolado(descricao, TamanhoMaximoGenerico.DESCRICAO)) {
			adicionarErro("Insira um nível contendo até "
					+ TamanhoMaximoGenerico.DESCRICAO + " caracteres.");
		}
	}

	@Override
	public String toString() {
		return "Nivel [id=" + id + ", descricao=" + descricao + "]";
	}
}

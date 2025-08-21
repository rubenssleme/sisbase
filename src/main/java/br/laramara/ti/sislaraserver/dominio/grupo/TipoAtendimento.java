package br.laramara.ti.sislaraserver.dominio.grupo;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "tipo_atendimento")
public class TipoAtendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tipo_atendimento_descricao_tipo_atendimento", joinColumns = { @JoinColumn(name = "id_tipo_atendimento", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_descricao_tipo_atendimento", referencedColumnName = "id") })
	@OrderBy("nome")
	private List<DescricaoTipoAtendimento> descricoesTipoAtendimento;
	
	@Column(name = "inativo")
	private boolean inativo;

	public TipoAtendimento() {
		this.descricoesTipoAtendimento = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<DescricaoTipoAtendimento> getDescricoesTipoAtendimentoAtivos() {
		List<DescricaoTipoAtendimento> atendimentosAtivos = new ArrayList<>();
		for(DescricaoTipoAtendimento descricaoTipoAtendimento : descricoesTipoAtendimento){
			if (descricaoTipoAtendimento.estaAtivo()){
				atendimentosAtivos.add(descricaoTipoAtendimento);
			}
		}
		return atendimentosAtivos;
	}

	public void setDescricoesTipoAtendimento(
			List<DescricaoTipoAtendimento> descricoesTipoAtendimento) {
		this.descricoesTipoAtendimento = descricoesTipoAtendimento;
	}

	@Override
	public String toString() {
		return "TipoAtendimento [id=" + id + ", nome=" + nome
				+ ", descricoesTipoAtendimento=" + descricoesTipoAtendimento
				+ ", inativo=" + inativo + "]";
	}
}

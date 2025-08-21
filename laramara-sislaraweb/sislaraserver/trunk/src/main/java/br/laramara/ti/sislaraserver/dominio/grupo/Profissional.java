package br.laramara.ti.sislaraserver.dominio.grupo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "profissional")
public class Profissional implements Serializable{

	private static final long serialVersionUID = 6514113531987536607L;

	public static final Long ID_VERA_APARECIDA_SALGUEIRO_PEREIRA = new Long(13);
	public static final Long ID_ANDERSON_ALMEIDA_BATISTA = new Long(6);
	public static final Long ID_MARA_LUCIA_DE_MELO_OLIVEIRA = new Long(89);
	public static final Long ID_ERICA_CRISTINA_TAKAHASHI_SILVA = new Long(29);
	public static final Long ID_ROSANA_SERAFIM_ABREU_SILVA_FURTADO = new Long(9);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME)
	private String nome;
	
	@Column(name = "chapa", length = TamanhoMaximoGenerico.CHAPA)
	private String chapa;
	
	@Column(name = "habilitado")
	private boolean habilitado;

	@Column(name = "profissional")
	private boolean profissional;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "especialidade", length = TamanhoMaximoGenerico.ESPECIALIDADE)
	private Especialidade especialidade; 
	
	public Profissional() {
		marcarComoHabilitado();
	}

	public Profissional(Long id, String nome, String chapa) {
		super();
		this.id = id;
		this.nome = nome;
		this.chapa = chapa;
		marcarComoHabilitado();
	}
	
	private void marcarComoHabilitado(){
		this.habilitado = true;
		this.profissional = true;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome.toUpperCase();
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getChapa() {
		return chapa;
	}

	public boolean possuiIdentificacaoIgual(Profissional profissional) {
		return this.getId().equals(profissional.getId());
	}
	
	public boolean oftalmologia() {
		return especialidade != null
				&& especialidade.equals(Especialidade.OFTALMOLOGIA);
	}
	
	public static List<Profissional> obterProfissionaisReponsaveisPeloServicoSocial() {
		return new ArrayList<>(Arrays.asList(new Profissional(ID_ANDERSON_ALMEIDA_BATISTA, null, null),
				new Profissional(ID_VERA_APARECIDA_SALGUEIRO_PEREIRA, null, null),
				new Profissional(ID_ERICA_CRISTINA_TAKAHASHI_SILVA, null, null)));
	}

	public static List<Profissional> obterProfissionaisAutorizadosAAlterarModuloReuniaoDeIntegracao() {
		List<Profissional> profissionais = obterProfissionaisReponsaveisPeloServicoSocial();
		profissionais.add(new Profissional(ID_MARA_LUCIA_DE_MELO_OLIVEIRA, null, null));
		profissionais.add(new Profissional(ID_ROSANA_SERAFIM_ABREU_SILVA_FURTADO, null, null));
		return profissionais;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profissional other = (Profissional) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profissional [id=" + id + ", nome=" + nome + ", chapa=" + chapa
				+", especialidade=" + especialidade + "]";
	}
}

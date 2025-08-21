package br.laramara.ti.sislaraserver.dominio.instituicao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.Contato;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.escola.DiretoriaEnsino;
import br.laramara.ti.sislaraserver.dominio.escola.DreCefai;
import br.laramara.ti.sislaraserver.dominio.escola.Escolaridade;
import br.laramara.ti.sislaraserver.dominio.escola.TipoApoio;
import br.laramara.ti.sislaraserver.dominio.escola.TipoEspecialidade;

@Entity
@Table(name = "instituicao")
public class Instituicao extends Validavel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", length = TamanhoMaximoInstituicao.TIPO, nullable = false)
	private TipoInstituicao tipoInstituicao;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
	@JoinColumn(name = "id_contato")
	private Contato contato;
	
	@Column(name = "nome_coordenador_responsavel", length = TamanhoMaximoGenerico.NOME)
	private String nomeCoordenadorResponsavel;

	@Enumerated(EnumType.STRING)
	@Column(name = "classificacao", length = TamanhoMaximoInstituicao.CLASSIFICACAO, nullable = false)
	private ClassificacaoInstituicao classificacao;

	@Column(name = "obs", length = TamanhoMaximoGenerico.OBS)
	private String obs;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_apoio", length = TamanhoMaximoGenerico.TIPO_APOIO, nullable = true)
	private TipoApoio tipoApoio;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "instituicao_tipo_especialidade", joinColumns = @JoinColumn(name = "id_instituicao"))
	@Column(name = "tipo_especialidade", length = TamanhoMaximoGenerico.TIPO_ESPECIALIDADE)
	private List<TipoEspecialidade> tiposEspecialidade;
	
	@OneToOne
	@JoinColumn(name = "id_dre_cefai")
	private DreCefai dreCefai;
	
	@OneToOne
	@JoinColumn(name = "id_diretoria_ensino")
	private DiretoriaEnsino diretoriaEnsino;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "instituicao_escolaridade", joinColumns = { @JoinColumn(name = "id_instituicao", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_escolaridade", referencedColumnName = "id") })
	private List<Escolaridade> escolaridades;
	
	public Instituicao() {
		endereco = new Endereco();
		contato = new Contato();
		tiposEspecialidade = new ArrayList<>();
		escolaridades = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public TipoInstituicao getTipoInstituicao() {
		return tipoInstituicao;
	}

	public void setTipoInstituicao(TipoInstituicao tipo) {
		this.tipoInstituicao = tipo;
	}

	public String getNome() {
		return nome;
	}
	
	public String getNomeSemEspaco(){
		return nome.trim();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getNomeCoordenadorResponsavel() {
		return nomeCoordenadorResponsavel;
	}

	public void setNomeCoordenadorResponsavel(String nomeCoordenadorResponsavel) {
		this.nomeCoordenadorResponsavel = nomeCoordenadorResponsavel;
	}

	public ClassificacaoInstituicao getClassificacaoInstituicao() {
		return classificacao;
	}

	public void setClassificacao(ClassificacaoInstituicao classificacao) {
		this.classificacao = classificacao;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public TipoApoio getTipoApoio() {
		return tipoApoio;
	}

	public void setTipoApoio(TipoApoio tipoApoio) {
		this.tipoApoio = tipoApoio;
	}

	public List<TipoEspecialidade> getTiposEspecialidade() {
		return tiposEspecialidade;
	}

	public void setTiposEspecialidade(List<TipoEspecialidade> tiposEspecialidade) {
		this.tiposEspecialidade = tiposEspecialidade;
	}
	
	public DreCefai getDreCefai() {
		return dreCefai;
	}

	public void setDreCefai(DreCefai dreCefai) {
		this.dreCefai = dreCefai;
	}
	
	public DiretoriaEnsino getDiretoriaEnsino() {
		return diretoriaEnsino;
	}

	public void setDiretoriaEnsino(DiretoriaEnsino diretoriaEnsino) {
		this.diretoriaEnsino = diretoriaEnsino;
	}

	public List<Escolaridade> getEscolaridades() {
		return escolaridades;
	}

	public void setEscolaridades(List<Escolaridade> escolaridades) {
		this.escolaridades = escolaridades;
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(nome, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um Nome contendo até "
					+ TamanhoMaximoGenerico.NOME + " caracteres.");
		}
		if (tamanhoMaximoViolado(nomeCoordenadorResponsavel, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um Nome de Coordenador ou Responsável contendo até "
					+ TamanhoMaximoGenerico.NOME + " caracteres.");
		}
		if (tamanhoMaximoViolado(obs, TamanhoMaximoGenerico.OBS)) {
			adicionarErro("Insira as Observações contendo até "
					+ TamanhoMaximoGenerico.OBS + " caracteres.");
		}
	}
	
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarTamanhoMaximoDeDados();
		validarObrigatoriedadeETamanhoMaximoDeDadosEndereco();
		validarObrigatoriedadeNivelEscolar();
		validarObrigatoriedadeETamanhoMaximoDeDadosContato();
	}
	
	private void validarObrigatoriedade(){
		if (tipoInstituicao == null){
			adicionarErro("Insira o Tipo da Instituiçãos.");
		}
		if (nome == null || TextoUtils.estaVazio(nome)) {
			adicionarErro("Insira um Nome.");
		}
		if (classificacao == null){
			adicionarErro("Insira uma Classificação.");
		}
	}

	private void validarObrigatoriedadeETamanhoMaximoDeDadosContato(){
		contato.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (!contato.validado()){
			adicionarErro(contato.obterErros());
		}	
	}
	
	private void validarObrigatoriedadeETamanhoMaximoDeDadosEndereco() {
		endereco.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (!endereco.validado()){
			adicionarErro(endereco.obterErros());
		}	
	}
	
	private void validarObrigatoriedadeNivelEscolar(){
		if (escolaridades.isEmpty()){
			adicionarErro("Insira pelo menos um Nível Escolar.");
		}
	}
	
	public static final Comparator<Instituicao> obterComparador() {
		return new Comparator<Instituicao>() {
			public int compare(Instituicao o1, Instituicao o2) {
				return (o1.getNome().compareTo(o2.getNome()));
			}
		};
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instituicao other = (Instituicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Instituicao [id=" + id + ", tipo=" + tipoInstituicao
				+ ", nome=" + nome + ", endereco=" + endereco + ", contato="
				+ contato + ", nomeCoordenadorResponsavel="
				+ nomeCoordenadorResponsavel + ", classificacao="
				+ classificacao + ", obs=" + obs + ", tipoApoio=" + tipoApoio
				+ ", tiposEspecialidade=" + tiposEspecialidade + ", dreCefai="
				+ dreCefai + ", diretoriaEnsino=" + diretoriaEnsino
				+ ", escolaridades=" + escolaridades + "]";
	}
}

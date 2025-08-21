package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.ArrayList;
import java.util.Arrays;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.precadastro.TamanhoMaximoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;

@Entity
@Table(name = "descricao_tipo_atendimento")
public class DescricaoTipoAtendimento {

	public static final Long ID_AVALIACAO_SERVICO_ATENCAO_ESPECIALIZADA_OFTALMOLOGIA = new Long(40);
	public static final Long ID_AVALIACAO_SERVICO_ATENCAO_ESPECIALIZADA_ORTOPTICA = new Long(42);
	public static final Long ID_ACOMPANHAMENTO_SERVICO_ATENCAO_ESPECIALIZADA_ORTOPTICA = new Long(43);
	public static final Long ID_ACOMPANHAMENTO_SERVICO_ATENCAO_ESPECIALIZADA_OFTALMOLOGIA = new Long(41);
	public static final Long ID_SERVICO_SOCIAL = new Long(37);
	public static final Long ID_ATENDIMENTO_ESPECIALIZADO_GLOBAL = new Long(1);
	public static final Long ID_AE_TRANSTORNO_GLOBAL_DO_DESENVOLVIMENTO = new Long(59);
	public static final Long ID_AE_COMUNICACAO = new Long(63);
	public static final Long ID_AE_ATIVIDADES_DE_VIDA_AUTONOMA = new Long(60);
	public static final Long ID_AVALIACAO_FUNCIONAL = new Long(17);
	public static final Long ID_PSICOLOGIA = new Long(39);
	public static final Long ID_PSICOSSOCIAL_DE_JOVENS_E_ADULTOS = new Long(38);
	public static final Long ID_ATIVIDADES_SOCIOPOLITICAS = new Long(62);
	public static final Long ID_ATIVIDADES_DE_CULTURA_E_LAZER = new Long(24);
	public static final Long ID_ORIENTACAO_SOCIOEDUCATIVA = new Long(16);
	public static final Long ID_AVALIACAO_OM = new Long(19);
	public static final Long ID_ORIENTACAO_E_MOBILIDADE = new Long(7);
	public static final Long ID_ACOMPANHAMENTO = new Long(38);
	public static final Long ID_CURSOS = new Long(50);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;

	@Column(name = "sigla", length = TamanhoMaximoTipoAtendimento.SIGLA, nullable = false)
	private String sigla;

	@Column(name = "descricao", length = TamanhoMaximoTipoAtendimento.DESCRICAO)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="descricao_tipo_atendimento_setor", 
			joinColumns=@JoinColumn(name="id_descricao_tipo_atendimento"))
	@Column(name="id_setor")
	private List<Setor> setor;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_tipo_atendimento", nullable = false)
	private TipoAtendimento tipoAtendimento;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "descricao_tipo_atendimento_nome_grupo", joinColumns = { @JoinColumn(name = "id_descricao_tipo_atendimento", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_nome_grupo", referencedColumnName = "id") })
	private List<NomeGrupo> nomesGrupos;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "descricao_tipo_atendimento_modulo", joinColumns = { @JoinColumn(name = "id_descricao_tipo_atendimento", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_modulo", referencedColumnName = "id") })
	@OrderBy("nome")
	private List<Modulo> modulos;
	
	@Column(name = "inativo")
	private boolean inativo;

	public DescricaoTipoAtendimento(){
		setor = new ArrayList<>();
		modulos = new ArrayList<>();
	}
	
	public DescricaoTipoAtendimento(Long id){
		this.id = id;
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Setor> getSetor() {
		return setor;
	}

	public void setSetor(List<Setor> setor) {
		this.setor = setor;
	}

	public TipoAtendimento getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}
	
	public List<NomeGrupo> getNomesGrupos() {
		return nomesGrupos;
	}

	public void setNomesGrupos(List<NomeGrupo> nomesGrupos) {
		this.nomesGrupos = nomesGrupos;
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	public boolean estaAtivo() {
		return !inativo;
	}

	public boolean possuiModulos() {
		return modulos != null && !modulos.isEmpty();
	}
	
	public static DescricaoTipoAtendimento fabricarDescricaoAcompanhamentoServicoAtencaoEspecializadaOrtoptica() {
		return new DescricaoTipoAtendimento(ID_ACOMPANHAMENTO_SERVICO_ATENCAO_ESPECIALIZADA_ORTOPTICA);
	}
	
	public static DescricaoTipoAtendimento fabricarDescricaoAvaiacaoServicoAtencaoEspecializadaOrtoptica() {
		return new DescricaoTipoAtendimento(ID_AVALIACAO_SERVICO_ATENCAO_ESPECIALIZADA_ORTOPTICA);
	}
	
	public static DescricaoTipoAtendimento fabricarAvaliacaoServicoAtencaoEspecializadaOftalmologia() {
		return new DescricaoTipoAtendimento(ID_AVALIACAO_SERVICO_ATENCAO_ESPECIALIZADA_OFTALMOLOGIA);
	}

	public static DescricaoTipoAtendimento fabricarAcompanhamentoServicoAtencaoEspecializadaOftalmologia() {
		return new DescricaoTipoAtendimento(ID_ACOMPANHAMENTO_SERVICO_ATENCAO_ESPECIALIZADA_OFTALMOLOGIA);
	}
	
	public static List<DescricaoTipoAtendimento> obterDescricoesEquivalenteParaRegraPrimeiraVez(
			DescricaoTipoAtendimento descricaoTipoAtendimento) {
		if (descricaoTipoAtendimento.eAcompanhamentoServicoAtencaoEspecializadaOftalmologia()
				|| descricaoTipoAtendimento.eAvaliacaoServicoAtencaoEspecializadaOftalmologia()) {
			return Arrays.asList(fabricarAvaliacaoServicoAtencaoEspecializadaOftalmologia(),
					fabricarAcompanhamentoServicoAtencaoEspecializadaOftalmologia());
		} else if (descricaoTipoAtendimento.eAcompanhamentoServicoAtencaoEspecializadaOrtoptica()
				|| descricaoTipoAtendimento.eAvaliacaoServicoAtencaoEspecializadaOrtoptica()) {
			return Arrays.asList(fabricarDescricaoAcompanhamentoServicoAtencaoEspecializadaOrtoptica(),
					fabricarDescricaoAvaiacaoServicoAtencaoEspecializadaOrtoptica());
		} else {
			return Arrays.asList(descricaoTipoAtendimento);
		}
	}
	
	public boolean ePsicologia() {
		return id.equals(ID_PSICOLOGIA);
	}

	public boolean eAtendimentoEspecializadoGlobal() {
		return id.equals(ID_ATENDIMENTO_ESPECIALIZADO_GLOBAL);
	}
	
	public boolean eAcompanhamentoServicoAtencaoEspecializadaOftalmologia() {
		return id.equals(ID_ACOMPANHAMENTO_SERVICO_ATENCAO_ESPECIALIZADA_OFTALMOLOGIA);
	}
	
	public boolean eAvaliacaoServicoAtencaoEspecializadaOftalmologia() {
		return id.equals(ID_AVALIACAO_SERVICO_ATENCAO_ESPECIALIZADA_OFTALMOLOGIA);
	}
	
	public boolean eAvaliacaoServicoAtencaoEspecializadaOrtoptica() {
		return id.equals(ID_AVALIACAO_SERVICO_ATENCAO_ESPECIALIZADA_ORTOPTICA);
	}
	
	public boolean eAcompanhamentoServicoAtencaoEspecializadaOrtoptica() {
		return id.equals(ID_ACOMPANHAMENTO_SERVICO_ATENCAO_ESPECIALIZADA_ORTOPTICA);
	}
	
	public boolean ePsicossocialDeJovensEAdultos() {
		return id.equals(ID_PSICOSSOCIAL_DE_JOVENS_E_ADULTOS);
	}

	public boolean eServicoSocial() {
		return id.equals(ID_SERVICO_SOCIAL);
	}
	
	public boolean eAvaliacaoOM() {
		return id.equals(ID_AVALIACAO_OM);
	}
	
	public boolean eAvaliacaoFuncional() {
		return id.equals(ID_AVALIACAO_FUNCIONAL);
	}
	
	public boolean eAtividadesSociopoliticas() {
		return id.equals(ID_ATIVIDADES_SOCIOPOLITICAS);
	}
	
	public boolean eTranstornoGlogalDoDesenvolvimento() {
		return id.equals(ID_AE_TRANSTORNO_GLOBAL_DO_DESENVOLVIMENTO);
	}

	public boolean eAtividadesDeCulturaELazer() {
		return id.equals(ID_ATIVIDADES_DE_CULTURA_E_LAZER);
	}
	
	public boolean eOrientacaoSocioeducativa() {
		return id.equals(ID_ORIENTACAO_SOCIOEDUCATIVA);
	}
	
	public boolean eOrientacaoEMobilidade() {
		return id.equals(ID_ORIENTACAO_E_MOBILIDADE);
	}
	
	public boolean eAcompanhamento() {
		return id.equals(ID_ACOMPANHAMENTO);
	}
	
	public boolean eCursos() {
		return id.equals(ID_CURSOS);
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DescricaoTipoAtendimento other = (DescricaoTipoAtendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DescricaoTipoAtendimento [id=" + id + ", nome=" + nome
				+ ", sigla=" + sigla + ", descricao=" + descricao + ", setor="
				+ setor + ", nomesGrupos=" + nomesGrupos + ", modulos="
				+ modulos + ", inativo=" + inativo + "]";
	}
}

package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaraserver.dominio.ModuloRelacaoBase;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.atendimento.TipoVinculo;
import br.laramara.ti.sislaraserver.dominio.escola.DiretoriaEnsino;
import br.laramara.ti.sislaraserver.dominio.escola.DreCefai;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Entity
@Table(name = "modulo_pre_cadastro")
public class ModuloPreCadastro extends ModuloRelacaoBase implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel {

	@ManyToOne
	@JoinColumn(name = "id_pre_cadastro", nullable = false)
	private PreCadastro preCadastro;

	@ManyToOne
	@JoinColumn(name = "id_tipo_vinculo", nullable = false)
	private TipoVinculo tipoVinculo;

	@ManyToOne
	@JoinColumn(name = "id_usuario_vinculado")
	private Usuario usuarioVinculado;
	
	@Column(name = "nome_origem_comunidade", length = TamanhoMaximoGenerico.NOME)
	private String nomeOrigemComunidade;
	
	@Column(name = "curso", length = TamanhoMaximoGenerico.CURSO)
	private String curso;
	
	@ManyToOne
	@JoinColumn(name = "id_instituicao")
	private Instituicao instituicao;
	
	@ManyToOne
	@JoinColumn(name = "id_dre_cefai")
	private DreCefai dreCefai;
	
	@ManyToOne
	@JoinColumn(name = "id_diretoria_ensino")
	private DiretoriaEnsino diretoriaEnsino;
	
	@ManyToOne
	@JoinColumn(name = "id_instituicao_com_srms")
	private Instituicao instituicaoComSrms;
	
	@ManyToOne
	@JoinColumn(name = "id_instituicao_com_sala_recurso")
	private Instituicao instituicaoComSalaRecurso;
	
	@ManyToOne
	@JoinColumn(name = "id_instituicao_com_outros_aee")
	private Instituicao instituicaoComOutrosAEE;
	
	@Column(name = "quantidade_criancas")
	private Integer quantidadeCriancas;
	
	@Column(name = "quantidade_adultos")
	private Integer quantidadeAdultos;
	
	public ModuloPreCadastro() {
		this.status = StatusRelacaoComModulo.INTEGRADO;
	}

	public PreCadastro getPreCadastro() {
		return preCadastro;
	}

	public void setPreCadastro(PreCadastro preCadastro) {
		this.preCadastro = preCadastro;
	}

	public Usuario getUsuarioVinculado() {
		return usuarioVinculado;
	}

	public void setUsuarioVinculado(Usuario usuarioVinculado) {
		this.usuarioVinculado = usuarioVinculado;
	}
	
	public TipoVinculo getTipoVinculo() {
		return tipoVinculo;
	}

	public void setTipoVinculo(TipoVinculo tipoVinculo) {
		this.tipoVinculo = tipoVinculo;
	}

	public String getNomeOrigemComunidade() {
		return nomeOrigemComunidade;
	}

	public void setNomeOrigemComunidade(String nomeOrigemComunidade) {
		this.nomeOrigemComunidade = nomeOrigemComunidade;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
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
	
	public Instituicao getInstituicaoComSrms() {
		return instituicaoComSrms;
	}

	public void setInstituicaoComSrms(Instituicao instituicaoComSrms) {
		this.instituicaoComSrms = instituicaoComSrms;
	}

	public Instituicao getInstituicaoComSalaRecurso() {
		return instituicaoComSalaRecurso;
	}

	public void setInstituicaoComSalaRecurso(Instituicao instituicaoComSalaRecurso) {
		this.instituicaoComSalaRecurso = instituicaoComSalaRecurso;
	}

	public Instituicao getInstituicaoComOutrosAEE() {
		return instituicaoComOutrosAEE;
	}

	public void setInstituicaoComOutrosAEE(Instituicao instituicaoComOutrosAEE) {
		this.instituicaoComOutrosAEE = instituicaoComOutrosAEE;
	}

	public String getQuantidadeCriancas() {
		return  NumeroUtils.obterTexto(quantidadeCriancas);
	}

	public void setQuantidadeCriancas(String quantidadeCriancas) {
		this.quantidadeCriancas = NumeroUtils.retornaInteiroOuInvalido(quantidadeCriancas);
	}

	public String getQuantidadeAdultos() {
		return  NumeroUtils.obterTexto(quantidadeAdultos);
	}

	public void setQuantidadeAdultos(String quantidadeAdultos) {
		this.quantidadeAdultos =  NumeroUtils.retornaInteiroOuInvalido(quantidadeAdultos);
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarDREouDiretorioDeEnsino();
		validarSRMsouSalaDeRecursoOuOutroAEE();
		validarTamanhoMaximoDeDados();
	}

	private void validarDREouDiretorioDeEnsino(){
		if (dreCefai != null && diretoriaEnsino != null){
			adicionarErro("Insira um DRE/CEFAI ou Diretoria de Ensino.");
		}
	}
	
	private void validarSRMsouSalaDeRecursoOuOutroAEE(){
		if (maisQueUmNaoNulo(instituicaoComSrms, instituicaoComSalaRecurso, instituicaoComOutrosAEE)){
			adicionarErro("Insira um SRMs ou Sala de Recurso ou Outro AEE.");
		}
	}
	
	private boolean maisQueUmNaoNulo(Instituicao... instituicoes){
		int contador = 0;
		for(Instituicao instituicao : instituicoes){
			if (instituicao!=null){
				contador++;
			}
		}
		return contador > 1;
	}
	
	protected void validarObrigatoriedade() {
		if (preCadastro == null) {
			adicionarErro("Insira o Pré-Cadastro.");
		}
		if (tipoVinculo == null) {
			adicionarErro("Insira a Formação.");
		}
		if (NumeroUtils.numeroInteiroInvalido(quantidadeCriancas)) {
			adicionarErro("Insira uma quantidade de crianças válida.");
		}
		if (NumeroUtils.numeroInteiroInvalido(quantidadeAdultos)) {
			adicionarErro("Insira uma quantidade de adultos válida.");
		}
		super.validarObrigatoriedade();
	}

	protected void validarTamanhoMaximoDeDados() {
		super.validarTamanhoMaximoDeDados();
		if (tamanhoMaximoViolado(nomeOrigemComunidade,
				TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira uma Nome da Origem contendo até "
					+ TamanhoMaximoGenerico.NOME + " caracteres.");
		}
		if (tamanhoMaximoViolado(curso,
				TamanhoMaximoGenerico.CURSO)) {
			adicionarErro("Insira um Curso contendo até "
					+ TamanhoMaximoGenerico.CURSO + " caracteres.");
		}
		if (tamanhoMaximoViolado(quantidadeCriancas, TamanhoMaximoGenerico.QUANTIDADE)) {
			adicionarErro("Insira uma Quantidade de Crianças contendo até " + TamanhoMaximoGenerico.QUANTIDADE
					+ " caracteres.");
		}
		if (tamanhoMaximoViolado(quantidadeAdultos, TamanhoMaximoGenerico.QUANTIDADE)) {
			adicionarErro("Insira uma Quantidade de Adultos contendo até " + TamanhoMaximoGenerico.QUANTIDADE
					+ " caracteres.");
		}
	}

	public static final Comparator<ModuloPreCadastro> obterComparador() {
		return new Comparator<ModuloPreCadastro>() {
			public int compare(ModuloPreCadastro o1, ModuloPreCadastro o2) {
				return o1
						.getPreCadastro()
						.getInformacaoEssencial()
						.getNome()
						.compareTo(
								o2.getPreCadastro().getInformacaoEssencial()
										.getNome());
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
		ModuloPreCadastro other = (ModuloPreCadastro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ModuloPreCadastro [id=" + id + ", preCadastro=" + preCadastro
				+ ", tipVinculo=" + tipoVinculo
				+ ", quantidadeCriancas=" + quantidadeCriancas 
				+ ", quantidadeAdultos=" + quantidadeAdultos
				+", usuarioVinculado="+usuarioVinculado+ ", nomeOrigemComunidade=" + nomeOrigemComunidade + ", curso="
				+ curso + ", dataInicio="
				+ DataHoraUtils.formatarData(dataInicio) + ", dataOcorrencia="
				+ DataHoraUtils.formatarData(dataOcorrencia) + ", status="
				+ status + ", aprovado=" + aprovado + ", obs=" + obs + "]";
	}


}
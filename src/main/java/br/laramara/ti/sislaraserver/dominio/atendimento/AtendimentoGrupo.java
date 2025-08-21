package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.Versionavel;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPreCadastro;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.grupo.ProfissionalVinculo;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Entity
@Table(name = "atendimento_grupo")
public class AtendimentoGrupo extends AtendimentoBase implements
		ValidavelObrigatoriedadeETamanhoMaximo, Versionavel, Identificavel {
	
	@Transient
	protected String versao;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "atendimento_grupo_atendimento_usuario", joinColumns = { @JoinColumn(name = "id_atendimento_grupo", referencedColumnName = "id") }, 
	inverseJoinColumns = { @JoinColumn(name = "id_atendimento_usuario", referencedColumnName = "id") })
	private List<AtendimentoUsuario> atendimentosUsuarios;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "atendimento_grupo_atendimento_pre_cadastro", joinColumns = { @JoinColumn(name = "id_atendimento_grupo", referencedColumnName = "id") }, 
	inverseJoinColumns = { @JoinColumn(name = "id_atendimento_pre_cadastro", referencedColumnName = "id") })
	private List<AtendimentoPreCadastro> atendimentosPreCadastros;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "atendimento_grupo_atendimento_profissional", joinColumns = { @JoinColumn(name = "id_atendimento_grupo", referencedColumnName = "id") }, 
	inverseJoinColumns = { @JoinColumn(name = "id_atendimento_profissional", referencedColumnName = "id") })
	private List<AtendimentoProfissional> atendimentosProfissionais;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String descricao;

	@Column(name = "interdisciplinar", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String interdisciplinar;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "atendimento_grupo_arquivo", joinColumns = { @JoinColumn(name = "id_atendimento_grupo", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_arquivo", referencedColumnName = "id") })
	private List<Arquivo> arquivos;

	public AtendimentoGrupo() {
		super(CategoriaAtendimento.GRUPO);
		inicializarStatusAtendimento();
		atendimentosUsuarios = new ArrayList<>();
		atendimentosPreCadastros = new ArrayList<>();
		atendimentosProfissionais = new ArrayList<>();
		arquivos = new ArrayList<>();
		versao = "";
	}
	
	public AtendimentoGrupo(Long id) {
		this.id = id;
	}
	
	public AtendimentoGrupo(List<Usuario> usuarios,
			List<PreCadastro> preCadastros, List<Profissional> profissionais) {
		this();
		inicializarAtendimentosUsuarios(usuarios);
		inicializarAtendimentosPreCadastros(preCadastros);
		inicializarAtendimentosProfissionais(profissionais);
		inicializarStatusAtendimento();
	}

	private void inicializarAtendimentosProfissionais(
			List<Profissional> profissionais) {
		for (Profissional profissional : profissionais) {
			AtendimentoProfissional atendimentoDoProfissional = new AtendimentoProfissional();
			atendimentoDoProfissional.setProfissional(profissional);

			atendimentosProfissionais.add(atendimentoDoProfissional);
		}
	}

	private void inicializarAtendimentosUsuarios(List<Usuario> usuarios) {
		if (!usuarios.isEmpty()) {
			for (Usuario usuario : usuarios) {
				AtendimentoUsuario atendimentoDoUsuario = new AtendimentoUsuario();
				atendimentoDoUsuario.setUsuario(usuario);

				atendimentosUsuarios.add(atendimentoDoUsuario);
			}
		}
	}
	
	private void inicializarAtendimentosPreCadastros(
			List<PreCadastro> preCadastros) {
		if (!preCadastros.isEmpty()) {
			for (PreCadastro preCadastro : preCadastros) {
				AtendimentoPreCadastro atendimentoDoPreCadastro = new AtendimentoPreCadastro();
				atendimentoDoPreCadastro.setPreCadastro(preCadastro);

				atendimentosPreCadastros.add(atendimentoDoPreCadastro);
			}
		}
	}
	
	public void preencherInformacoesAuxiliaresNoAtendimentoPreCadastro(
			List<ModuloPreCadastro> moduloPreCadastroReferencia) {
		for (AtendimentoPreCadastro atendimentoPreCadastro : atendimentosPreCadastros) {
			for (ModuloPreCadastro moduloPreCadastro : moduloPreCadastroReferencia) {
				if (atendimentoPreCadastro.getPreCadastro().equals(
						moduloPreCadastro.getPreCadastro())) {
					atendimentoPreCadastro.setInstituicao(moduloPreCadastro
							.getInstituicao());
					atendimentoPreCadastro.setDreCefai(moduloPreCadastro
							.getDreCefai());
					atendimentoPreCadastro.setDiretoriaEnsino(moduloPreCadastro
							.getDiretoriaEnsino());
					atendimentoPreCadastro.setNomeOrigem(moduloPreCadastro
							.getNomeOrigemComunidade());
					atendimentoPreCadastro.setTipoVinculo(moduloPreCadastro
							.getTipoVinculo());
				}
			}
		}
	}
	
	public void preencherInformacaoAuxiliaresNoAtendimentoProfissional(
			List<ProfissionalVinculo> profissionaisVinculos) {
		for (AtendimentoProfissional atendimentoProfissional : atendimentosProfissionais) {
			for (ProfissionalVinculo profissionalVinculo : profissionaisVinculos) {
				if (profissionalVinculo.getProfissional().equals(
						atendimentoProfissional.getProfissional())) {
					atendimentoProfissional
							.setApenasParticipante(profissionalVinculo
									.isParticipante());
				}
			}
		}
	}

	public String getVersao(){
		return versao;
	}
	
	public void setVersao(String versao){
		this.versao = versao;
	}
	
	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public List<Usuario> obterUsuariosComAtendimentoDeGrupoNormalEFrequenciaFUouFJ(){
		return getAtendimentosUsuarios().stream()
				.filter(atendimentosUsuario -> estaNormal() && atendimentosUsuario.possuiFrequenciaFUouFJ())
				.map(atendimentoUsuario -> atendimentoUsuario.getUsuario()).collect(Collectors.toList());
	}
	
	public List<AtendimentoUsuario> getAtendimentosUsuarios() {
		Collections.sort(atendimentosUsuarios,
				AtendimentoUsuario.obterComparador());
		return atendimentosUsuarios;
	}

	public void setAtendimentosUsuarios(
			List<AtendimentoUsuario> atendimentosUsuarios) {
		this.atendimentosUsuarios = atendimentosUsuarios;
	}
	
	public void setAtendimentosPreCadastro(
			List<AtendimentoPreCadastro> atendimentosPreCadastro) {
		this.atendimentosPreCadastros = atendimentosPreCadastro;
	}
	
	public List<AtendimentoPreCadastro> getAtendimentosPreCadastros() {
		Collections.sort(atendimentosPreCadastros,
				AtendimentoPreCadastro.obterComparador());
		return atendimentosPreCadastros;
	}

	public List<AtendimentoProfissional> getAtendimentosProfissionais() {
		Collections.sort(atendimentosProfissionais,
				AtendimentoProfissional.obterComparador());
		return atendimentosProfissionais;
	}

	public void setAtendimentosProfissionais(
			List<AtendimentoProfissional> atendimentosProfissionais) {
		this.atendimentosProfissionais = atendimentosProfissionais;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getInterdisciplinar() {
		return interdisciplinar;
	}

	public void setInterdisciplinar(String interdisciplinar) {
		this.interdisciplinar = interdisciplinar;
	}

	public boolean naoPossuiId() {
		return id == null;
	}
	
	public boolean possuiId() {
		return id != null;
	}
	
	public boolean possuiProfissionaisComFrequenciaATouFRouFPouOAouRCeStatusDeAtendimentoNormal() {
		return statusAtendimento.equals(StatusAtendimento.NORMAL)
				&& !obterProfissionaisComFrequenciaATouFRouFPouOAouRC(atendimentosProfissionais).isEmpty();
	}
	
	private void validarExistenciaDeArquivosDuplicados() {
		if (validarDuplicacaoArquivos(arquivos)){
			adicionarErro("Existem arquivos duplicados.");
		}
	}
	
	private void validarExistenciaDeProfissionaisDuplicados() {
		 if (possuiOcorrenciaDuplicada(atendimentosProfissionais.stream()
					.map(arquivo -> arquivo.getProfissional()).collect(Collectors.toList()))){
			 adicionarErro("Existem profissionais duplicados.");
		 }
	}
	
	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedadeDeData();
		validarObrigatoriedadeDeCategoriaAtendimento();
		validarObrigatoriedadeETamanhoMaximoDeDadosHorario();
		validarRestricaoDeAlteracao(atendimentosProfissionais);
		validarExistenciaDeArquivosDuplicados();
		validarExistenciaDeProfissionaisDuplicados();
		validarFaltaDeProfissionais(atendimentosProfissionais, todosOsUsuariosPossuemFrequenciaFP());
		validarTamanhoMaximoDeDados();
	}

	private boolean todosOsUsuariosPossuemFrequenciaFP() {
		return !atendimentosUsuarios.isEmpty() && atendimentosUsuarios.stream()
				.allMatch(atendimentosUsuarios -> atendimentosUsuarios.possuiFrequenciaFP());
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(descricao, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira uma Descrição contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(interdisciplinar,
				TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira um Interdisciplinar contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
	}

	public static final Comparator<AtendimentoGrupo> obterComparador() {
		return new Comparator<AtendimentoGrupo>() {
			public int compare(AtendimentoGrupo o1, AtendimentoGrupo o2) {
				return (o1.data.compareTo(o2.data));
			}
		};
	}

	@Override
	public String toString() {
		return "AtendimentoGrupo [id=" + id + ", categoria=" + categoriaAtendimento
				+ ", data=" + DataHoraUtils.formatarData(data) + ", status="
				+ statusAtendimento + ", horario=" + horario + ", totalHoras="
				+ totalHoras + ", atendimentosAosUsuários="
				+ atendimentosUsuarios + ", atendimentosDosProfissionais="
				+ atendimentosProfissionais + ", descrição=" + descricao
				+ ", interdisciplinar=" + interdisciplinar + "]";
	}
}

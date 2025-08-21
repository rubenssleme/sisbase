package br.laramara.ti.sislaraserver.dominio.grupo;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.Versionavel;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.doacao.LoteRecurso;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Entity
@Table(name = "grupo")
public class Grupo extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel, Versionavel{

	public static final String SEQUENCIA = "grupo_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Transient
	private String versao;
	
	@Column(name = "ativo")
	private boolean ativo;

	@Column(name = "excluido")
	private boolean excluido;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "id_setor", length = TamanhoMaximoGenerico.SETOR, nullable = false)
	private Setor setor;

	@ManyToOne
	@JoinColumn(name = "id_descricao_tipo_atendimento", nullable = false)
	private DescricaoTipoAtendimento descricaoTipoAtendimento;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio", nullable = false)
	private Calendar dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_termino", nullable = false)
	private Calendar dataTermino;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_nome_grupo")
	private NomeGrupo nomeGrupo;

	@Column(name = "turma", length = TamanhoMaximoGrupo.TURMA)
	private String turma;

	@Column(name = "nivel")
	private Integer nivel;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "grupo_modulo_periodo", joinColumns = { @JoinColumn(name = "id_grupo", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_modulo_periodo", referencedColumnName = "id") })
	private List<ModuloPeriodo> modulosPeriodos;
	
	@Column(name = "descricao", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String descricao;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "grupo_tipificacao_servico", joinColumns = { @JoinColumn(name = "id_grupo", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_tipificacao_servico", referencedColumnName = "id") })
	private List<TipificacaoServico> tipificacoesServico;
	
	@OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "grupo_lote_recurso", joinColumns = { @JoinColumn(name = "id_grupo", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_lote_recurso", referencedColumnName = "id") })
	private List<LoteRecurso> loteRecurso;
	
	@ManyToOne
	@JoinColumn(name = "id_instituicao")
	private Instituicao instituicaoDoacao;
	
	@Transient
	private List<Usuario> usuariosNaEsperaDoServicoSocialPorExcessoDeFaltasAguardando;
	
	public Grupo() {
		modulosPeriodos = new ArrayList<>();
		tipificacoesServico = new ArrayList<>();
		loteRecurso = new ArrayList<>();
		versao = "";
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isInicializado(){
		return id != null;
	}
	
	public void mudarVersao(){
		this.versao = UUID.randomUUID().toString();
		for(ModuloPeriodo moduloPeriodo : modulosPeriodos){
			moduloPeriodo.setVersao(versao);
		}
	}
	
	public String getVersao(){
		return versao;
	}
		
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public DescricaoTipoAtendimento getDescricaoTipoAtendimento() {
		return descricaoTipoAtendimento;
	}

	public void setDescricaoTipoAtendimento(DescricaoTipoAtendimento descricaoTipoAtendimento) {
		this.descricaoTipoAtendimento = descricaoTipoAtendimento;
	}

	public String getDataInicio() {
		return DataHoraUtils.formatarData(dataInicio);
	}

	public LocalDateTime obterDataInicioDaReuniaoDeIntegracao() {
		return DataHoraUtils.criar(dataInicio, Time.valueOf("00:00:00"));
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio =  DataHoraUtils.obterDataValidaInvalidaOuNulo(dataInicio);
	}

	public String getDataTermino() {
		return DataHoraUtils.formatarData(dataTermino);
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataTermino);
	}

	public NomeGrupo getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(NomeGrupo nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = preencheComZeroAEsquerda(turma);
	}

	public String getNivel() {
		return NumeroUtils.obterTexto(nivel);
	}

	public void setNivel(String nivel) {
		this.nivel = NumeroUtils.retornaInteiroOuInvalido(nivel);
	}
	
	public boolean eTranstornoGlobalDoDesenvolvimento() {
		return descricaoTipoAtendimento.eTranstornoGlogalDoDesenvolvimento();
	}

	public List<ModuloPeriodo> getModulosPeriodos() {
		Collections.sort(modulosPeriodos, ModuloPeriodo.obterComparador());
		return modulosPeriodos;
	}

	public void setModulosPeriodos(
			List<ModuloPeriodo> modulosPeriodos) {
		this.modulosPeriodos = modulosPeriodos;
	}
	
	public void atualizarModuloPeriodo(ModuloPeriodo moduloPeriodoAAtualizar) {
		int indice = this.modulosPeriodos.indexOf(moduloPeriodoAAtualizar);
		this.modulosPeriodos.set(indice, moduloPeriodoAAtualizar);
	}
	
	private boolean possuiModuloReuniaoDeIntegracao(){
		return modulosPeriodos.stream().anyMatch(moduloPeriodo -> moduloPeriodo.ePossuiModuloDeReuniaoIntegracao());
	}

	public void adicionarListaUsuariosEmEsperaPorExcessoDeFaltas(
			List<Usuario> usuariosNaEsperaDoServicoSocialPorExcessoDeFaltasAguardando) {
		this.usuariosNaEsperaDoServicoSocialPorExcessoDeFaltasAguardando = usuariosNaEsperaDoServicoSocialPorExcessoDeFaltasAguardando;
	}
	
	public void atualizarAtendimendoGrupo(
			AtendimentoGrupo atendimentoGrupoAAtualizar) {
		for (ModuloPeriodo moduloPeriodo : modulosPeriodos) {
			for (AtendimentoGrupo atendimentoGrupo : moduloPeriodo
					.getAtendimentosGrupo()) {
				if (atendimentoGrupo.getId().equals(
						atendimentoGrupoAAtualizar.getId())) {
					moduloPeriodo
							.atualizarAtendimentoGrupo(atendimentoGrupoAAtualizar);
					return;
				}
			}
		}
	}
	
	public List<Usuario> obterTodosUsuarios() {
		Set<Usuario> usuarios = new HashSet<>();
		for (ModuloPeriodo moduloPeriodo : modulosPeriodos) {
			usuarios.addAll(moduloPeriodo.obterUsuarios());
		}
		return new ArrayList<Usuario>(usuarios);
	}
	
	public List<Usuario> obterTodosUsuariosIntegrados() {
		return modulosPeriodos.stream()
				.flatMap(moduloPeriodo -> moduloPeriodo.obterUsuariosIntegradosPorModuloPeriodo().stream()).distinct()
				.collect(Collectors.toList());
	}
	
	public String getDescricao() {
		return descricao;
	}

	public Calendar getDataInicioCalendario() {
		return dataInicio;
	}

	public Calendar getDataTerminoCalendario() {
		return dataTermino;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<TipificacaoServico> getTipificacoesServico() {
		return tipificacoesServico;
	}

	public void setTipificacoesServico(
			List<TipificacaoServico> tipificacoesServico) {
		this.tipificacoesServico = tipificacoesServico;
	}

	public Instituicao getInstituicaoDoacao() {
		return instituicaoDoacao;
	}

	public void setInstituicaoDoacao(Instituicao instituicaoDoacao) {
		this.instituicaoDoacao = instituicaoDoacao;
	}
	
	public List<LoteRecurso> getDoacaoRecurso() {
		return loteRecurso;
	}

	public void setDoacaoRecurso(List<LoteRecurso> doacaoRecurso) {
		this.loteRecurso = doacaoRecurso;
	}
	
	public boolean dataInicioFutura() {
		return dataInicio.after(MaquinaTempo.obterInstancia().obterCalendarioAtual());
	}
	
	public boolean eDescricaoServicoSocialComModuloReuniaoDeIntegracao() {
		return descricaoTipoAtendimento.eServicoSocial()
				&& modulosPeriodos.stream().anyMatch(moduloPeriodo -> moduloPeriodo.ePossuiModuloDeReuniaoIntegracao());
	}

	public boolean eDescricaoPsicossocialDeJovensEAdultos() {
		return descricaoTipoAtendimento.ePsicossocialDeJovensEAdultos();
	}

	public boolean eDescricaoCursos(){
		return descricaoTipoAtendimento.eCursos();
	}
	
	public boolean eDescricaoAtividadesSocioPoliticas() {
		return descricaoTipoAtendimento.eAtividadesSociopoliticas();
	}
	
	public boolean eDescricaoAtividadeDeCulturaELazer(){
		return descricaoTipoAtendimento.eAtividadesDeCulturaELazer();
	}

	public boolean eDescricaoOrientacaoSocioeducativa(){
		return descricaoTipoAtendimento.eOrientacaoSocioeducativa();
	}
	
	public boolean eAtendimentoEspecializadoGlobal() {
		return descricaoTipoAtendimento.eAtendimentoEspecializadoGlobal();
	}

	public StatusRelacaoComModulo obterStatusRelacaoPadrao() {
		return (descricaoTipoAtendimento.eOrientacaoSocioeducativa()
				|| descricaoTipoAtendimento.eAtividadesDeCulturaELazer()) ? StatusRelacaoComModulo.EVENTUAL
						: StatusRelacaoComModulo.INTEGRADO;
	}

	public boolean podeCriarPendenciaDeAtendimento() {
		return !eDescricaoPsicossocialDeJovensEAdultos() && !eDescricaoAtividadesSocioPoliticas() && !eDescricaoCursos()
				&& !eDescricaoAtividadeDeCulturaELazer() && !eDescricaoOrientacaoSocioeducativa();
	}
	
	public boolean podeIncluirUsuariosEmReuniaoDeIntegracao() {
		return !eDescricaoServicoSocialComModuloReuniaoDeIntegracao() && !eDescricaoAtividadeDeCulturaELazer();
	}
	
	public boolean existeUsuarioIntegradoEmModuloDeReunicaoDeIntegracaoEmModuloPeriodoDistinto(Usuario usuarioIntegrado,
			ModuloPeriodo moduloPeriodoSolicitante) {
		return !moduloPeriodoSolicitante.equals(obterModuloPeriodoDeReuniaoDeIntegracao())
				&& existeUsuarioIntegradoEmModuloDeReunicaoDeIntegracao(usuarioIntegrado);
	}

	public boolean existeUsuarioIntegradoEmModuloDeReunicaoDeIntegracao(Usuario usuarioIntegrado) {
		return obterModuloPeriodoDeReuniaoDeIntegracao().existeUsuarioIntegrado(usuarioIntegrado);
	}
	
	public ModuloPeriodo obterModuloPeriodoDeReuniaoDeIntegracao() {
		return modulosPeriodos.stream()
				.filter(moduloPeriodoAvaliado -> moduloPeriodoAvaliado.ePossuiModuloDeReuniaoIntegracao()).findFirst()
				.orElse(null);
	}
	
	public boolean eDescricaoServicoSocialModuloReuniaoDeIntegracaoComDataFuturaEPeriodoCompativel(
			ModuloPeriodo moduloPeriodoSolicitante) {
		List<ModuloPeriodo> moduloPeriodoComReuniaoIntegracaoEHorarioCompativel = modulosPeriodos.stream()
				.filter(moduloPeriodo -> !moduloPeriodo.equals(moduloPeriodoSolicitante) 
						&& moduloPeriodo.ePossuiModuloDeReuniaoIntegracao() && dataInicioFutura()
						&& moduloPeriodo.contemPeloMenosUmPeriodoHorario(moduloPeriodoSolicitante)
						&& moduloPeriodo
								.possuiVagasDispoveisParaUsuariosIntegradoOuProvisoriosOuAcesso())
				.collect(Collectors.toList());
		return descricaoTipoAtendimento.eServicoSocial()
				&& !moduloPeriodoComReuniaoIntegracaoEHorarioCompativel.isEmpty();
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(turma, TamanhoMaximoGrupo.TURMA)) {
			adicionarErro("Insira uma Turma contendo até "
					+ TamanhoMaximoGrupo.TURMA + " caracteres.");
		}
		if (tamanhoMaximoViolado(nivel, TamanhoMaximoGrupo.NIVEL)) {
			adicionarErro("Insira um Nível contendo até "
					+ TamanhoMaximoGrupo.NIVEL + " caracteres.");
		}
		if (tamanhoMaximoViolado(descricao, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira a Descrição contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarTamanhoMaximoDeDados();
		validarModulosPeriodos();
		validarOcorrenciaUsuariosNaListaDeEsperaPorExcessoDeFaltasAguardando();
		validarDiaSemanaCompativelComDataUnicaDoGrupo();
	}

	private void validarDiaSemanaCompativelComDataUnicaDoGrupo() {
		if (dataInicio != null && dataTermino != null && dataInicio.equals(dataTermino)) {
			if (modulosPeriodos.stream().anyMatch(moduloPeriodo -> !moduloPeriodo.eDiaSemanaCompativel(dataInicio))) {
				adicionarErro("Dia da semana do módulo incompatível com a data do grupo.");
			}
		}
	}

	private void validarOcorrenciaUsuariosNaListaDeEsperaPorExcessoDeFaltasAguardando() {
		if (!ativo
				&& (usuariosNaEsperaDoServicoSocialPorExcessoDeFaltasAguardando != null
						&& !usuariosNaEsperaDoServicoSocialPorExcessoDeFaltasAguardando.isEmpty())
				&& !possuiModuloReuniaoDeIntegracao() && !eDescricaoAtividadeDeCulturaELazer()
				&& !eDescricaoOrientacaoSocioeducativa()) {
			adicionarErro("Usuário(s) "
					+ TextoUtils.removerChaves(
							Usuario.obterNomesDosUsuarios(usuariosNaEsperaDoServicoSocialPorExcessoDeFaltasAguardando))
					+ " está aguardando na espera por excesso de faltas. Por favor, analise o(s) caso(s) antes de desativar o grupo.");
		}
	}

	private boolean validarExistenciaModuloDuplicado(){
		boolean retorno = false;
		List<Modulo> modulosExistentes = new ArrayList<>();
		for(ModuloPeriodo moduloPeriodo : modulosPeriodos){
			Modulo modulo = moduloPeriodo.getModulo();
			if (!modulosExistentes.contains(modulo)){
				modulosExistentes.add(modulo);
			}else{
				retorno = true;
			}
		}
		return retorno;
	}
	
	private void validarModulosPeriodos() {
		for (ModuloPeriodo moduloPeriodo : modulosPeriodos) {
			moduloPeriodo.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (ativo == false) {
				moduloPeriodo.validarExistenciaUsuarioComStatusProvisorioOuAcesso();
			}
			if (!moduloPeriodo.validado()) {
				adicionarErro(moduloPeriodo.obterErros());
			}
		}
		if (validarExistenciaModuloDuplicado()) {
			adicionarErro("Existem Módulos duplicados nesse grupo.");
		}
	}
	
	private void validarObrigatoriedade() {
		if (setor == null) {
			adicionarErro("Insira o Setor.");
		}
		if (descricaoTipoAtendimento == null) {
			adicionarErro("Insira a Descrição do Tipo de Atendimento.");
		}
		if (dataInicio == null || DataHoraUtils.dataInvalida(dataInicio)) {
			adicionarErro("Insira uma Data de Início válida.");
		}
		if (dataTermino == null || DataHoraUtils.dataInvalida(dataTermino)) {
			adicionarErro("Insira uma Data de Término válida.");
		}
		if (DataHoraUtils.dataTerminoAnteriorDataInicio(dataInicio, dataTermino)){
			adicionarErro("Insira uma Data de Témino posterior a Data de Início.");
		}
		if (nomeGrupo == null) {
			adicionarErro("Insira um Grupo.");
		}
		if (turma == null
				|| (turma != null && turma.trim().isEmpty())
				|| NumeroUtils.numeroInteiroInvalido(NumeroUtils
						.retornaInteiroOuInvalido(turma))) {
			adicionarErro("Insira uma Turma válida.");
		}
		if (NumeroUtils.numeroInteiroInvalido(nivel)) {
			adicionarErro("Insira um Nível válido.");
		}
		if (modulosPeriodos.isEmpty()){
			adicionarErro("Insira pelo menos um Módulo/Ativade.");
		}
	}
	
	private String preencheComZeroAEsquerda(String texto) {
		if ((TextoUtils.removerCaracteresInvalidos(texto)).length()==1) {
			return "0" + TextoUtils.removerCaracteresInvalidos(texto);
		} else {
			return texto;
		}
	}
	
	public String obterNomeGrupoETurma() {
		return nomeGrupo.getNome() + "-" + turma.toString();
	}
	
	public static final Comparator<Grupo> obterComparador() {
		return new Comparator<Grupo>() {
			public int compare(Grupo o1, Grupo o2) {
				return (o1.obterNomeGrupoETurma().compareTo(o2
						.obterNomeGrupoETurma()));
			}
		};
	}

	public List<PreCadastro> obterTodosPreCadastro() {
		Set<PreCadastro> preCadastro = new HashSet<>();
		for (ModuloPeriodo moduloPeriodo : modulosPeriodos) {
			preCadastro.addAll(moduloPeriodo.obterPreCadastro());
		}
		return new ArrayList<PreCadastro>(preCadastro);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", setor=" + setor + ", versao=" + versao
				+ ", ativo=" + ativo + ", descricaoTipoAtendimento="
				+ descricaoTipoAtendimento + ", dataInicial="
				+ DataHoraUtils.formatarData(dataInicio) + ", dataTermino="
				+ DataHoraUtils.formatarData(dataTermino) + ", nomeGrupo="
				+ nomeGrupo + ", turma=" + turma + ", nivel=" + nivel
				+ ", modulosPeriodos=" + modulosPeriodos + ", descricao="
				+ descricao + ", tipificacaoServico=" + tipificacoesServico
				+ "]";
	}
}

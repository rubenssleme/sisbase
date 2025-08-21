package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaraserver.dominio.ModuloRelacaoBase;
import br.laramara.ti.sislaraserver.dominio.PeriodoHorario;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.Versionavel;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoBase;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.utilitarios.EntidadeUtils;

@Entity
@Table(name = "modulo_periodo")
public class ModuloPeriodo extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel, Versionavel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Transient
	protected String versao;
	
	@OneToOne
	@JoinColumn(name = "id_modulo", nullable = false)
	private Modulo modulo;

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "modulo_periodo_dia_semana_e_horario", joinColumns = { @JoinColumn(name = "id_modulo_periodo", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_dia_semana_e_horario", referencedColumnName = "id") })
	private List<DiaSemanaEHorario> diasSemanaEHorariosDaAtividade;
		
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "modulo_periodo_profissional_vinculo", joinColumns = { @JoinColumn(name = "id_modulo_periodo", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_profissional_vinculo", referencedColumnName = "id") })
	private List<ProfissionalVinculo> profissionaisVinculo;
	
	@OneToOne
	@JoinColumn(name = "id_local_atendimento")
	private LocalAtendimento localAtendimento;
	
	@Column(name = "vagas")
	private Integer vagas;
	
	@Column(name = "carga_horaria", length = TamanhoMaximoGrupo.CARGA_HORARIA, nullable = false)
	private String cargaHoraria;
	
	@Column(name = "carga_horaria_minima", length = TamanhoMaximoGrupo.CARGA_HORARIA_MINIMA, nullable = false)
	private String cargaHorariaMinima;

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "modulo_periodo_programacao", joinColumns = { @JoinColumn(name = "id_modulo_periodo", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_programacao", referencedColumnName = "id") })
	private List<Programacao> programacoes;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "modulo_periodo_modulo_usuario", joinColumns = { @JoinColumn(name = "id_modulo_periodo", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_modulo_usuario", referencedColumnName = "id") })
	private List<ModuloUsuario> modulosUsuarios;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "modulo_periodo_modulo_pre_cadastro", joinColumns = { @JoinColumn(name = "id_modulo_periodo", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_modulo_pre_cadastro", referencedColumnName = "id") })
	private List<ModuloPreCadastro> modulosPreCadastro;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name = "modulo_periodo_atendimento_grupo", joinColumns = { @JoinColumn(name = "id_modulo_periodo", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_atendimento_grupo", referencedColumnName = "id") })
	private List<AtendimentoGrupo> atendimentosGrupo;
	
	@Transient
	private boolean usuarioNovoAdicionado;
	
	@Transient
	private boolean sofreuMudancaDeStatusDeProvisorioParaAcesso;
	
	@Transient
	private ContaAcesso contaAcessoReponsavelPorOperacao;
	
	@Transient
	private List<Grupo> gruposComDescricaoServicoSocialModuloReunicaoDeIntegracaoAtivos;
	
	public ModuloPeriodo() {
		profissionaisVinculo = new ArrayList<>();
		programacoes = new ArrayList<>();
		atendimentosGrupo = new ArrayList<>();
		modulosUsuarios = new ArrayList<>();
		modulosPreCadastro = new ArrayList<>();
		diasSemanaEHorariosDaAtividade = new ArrayList<>();
		versao = "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVersao(){
		return versao;
	}
	
	public void setVersao(String versao){
		this.versao = versao;
		for(AtendimentoGrupo atendimentoGrupo : atendimentosGrupo){
			atendimentoGrupo.setVersao(versao);
		}
	}
	
	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public List<ProfissionalVinculo> getProfissionaisVinculo() {
		return profissionaisVinculo;
	}

	public void setProfissionaisVinculo(List<ProfissionalVinculo> profissionaisVinculo) {
		this.profissionaisVinculo = profissionaisVinculo;
	}

	public boolean possuiUsuarioComStatusProvisorioOuAcesso(){
		return !obterModuloUsuariosComStatusProvisorioOuAcesso().isEmpty();
	}
	
	public List<PeriodoHorario> obterPeriodosHorarios() {
		List<PeriodoHorario> periodosHorarios = diasSemanaEHorariosDaAtividade.stream()
				.map(diaSemanaEHorarioDeAtividade -> diaSemanaEHorarioDeAtividade.getHorario().periodo())
				.collect(Collectors.toList());
		return periodosHorarios;
	}
	
	public void adicionarUsuario(Usuario usuario, String obs, boolean aprovado){
		adicionarUsuario(usuario, obs, aprovado, StatusRelacaoComModulo.INTEGRADO);
	}
	
	public void provisionarUsuario(Usuario usuario, String obs, boolean aprovado){
		adicionarUsuario(usuario, obs, aprovado, StatusRelacaoComModulo.PROVISORIO);
	}
	
	private void adicionarUsuario(Usuario usuario, String obs, boolean aprovado,
			StatusRelacaoComModulo statusRelacaoComModulo) {
		if (!modulosUsuarios.stream().anyMatch(moduloUsuario -> moduloUsuario.getUsuario().equals(usuario))) {
			ModuloUsuario moduloUsuario = new ModuloUsuario();
			moduloUsuario.setUsuario(usuario);
			moduloUsuario.setStatus(statusRelacaoComModulo);
			moduloUsuario.setDataInicio(DataHoraUtils.formatarData(MaquinaTempo.obterInstancia().obterCalendarioAtual()));
			moduloUsuario.setObs(obs);
			moduloUsuario.setAprovado(aprovado);
			modulosUsuarios.add(moduloUsuario);
			usuarioNovoAdicionado = true;
		}
	}
	
	public boolean possuiUsuarioNovo() {
		return usuarioNovoAdicionado;
	}
	
	public List<Profissional> obterSomenteProfissionais() {
		List<Profissional> profissionais = new ArrayList<>();
		for (ProfissionalVinculo profissionalVinculo : profissionaisVinculo) {
			if (!profissionalVinculo.isParticipante()) {
				profissionais.add(profissionalVinculo.getProfissional());
			}
		}
		return profissionais;
	}
	
	public LocalAtendimento getLocalAtendimento() {
		return localAtendimento;
	}

	public void setLocalAtendimento(LocalAtendimento localAtendimento) {
		this.localAtendimento = localAtendimento;
	}
	
	public String getVagas() {
		return NumeroUtils.obterTexto(vagas);
	}

	public void setVagas(String vagas) {
		this.vagas =  NumeroUtils.retornaInteiroOuInvalido(vagas);
	}
	
	public String getCargaHoraria() {
		return DataHoraUtils.obterHora(cargaHoraria);
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = DataHoraUtils
				.retornaTotalHoraOuInvalido(cargaHoraria);
	}
	
	public String getCargaHorariaMinima(){
		return DataHoraUtils.obterHora(cargaHorariaMinima);
	}
	
	public void setCargaHorariaMinima(String cargaHorariaMinima){
		this.cargaHorariaMinima = DataHoraUtils.retornaTotalHoraOuInvalido(cargaHorariaMinima);
	}
	
	public List<Programacao> getProgramacoes() {
		return programacoes;
	}

	public void setProgramacoes(List<Programacao> programacoes) {
		this.programacoes = programacoes;
	}

	public List<DiaSemanaEHorario> getDiasSemanaEHorariosDaAtividade() {
		return diasSemanaEHorariosDaAtividade;
	}

	public void setDiasSemanaEHorariosDaAtividade(List<DiaSemanaEHorario> diasSemanaEHorariosDaAtividade) {
		this.diasSemanaEHorariosDaAtividade = diasSemanaEHorariosDaAtividade;
	}

	public List<AtendimentoGrupo> getAtendimentosGrupo() {
		Collections.sort(atendimentosGrupo, AtendimentoGrupo.obterComparador());
		preencherInformacoesAuxiliaresDeAtendimentos();
		return atendimentosGrupo;
	}

	public void setAtendimentosGrupo(List<AtendimentoGrupo> atendimentosGrupo) {
		this.atendimentosGrupo = atendimentosGrupo;
	}
	
	public void gerarAtendimentos(Grupo grupo, EspecificacaoGeracaoAtendimento especificacaoGeracaoAtendimento) {
		gerarAtendimentosDeGrupo(grupo, modulo, especificacaoGeracaoAtendimento);
		validarExistenciaDeUsuarioOuPreCadastro();
	}

	private void validarExistenciaDeMaisDeUmAtendimentoNormalEmReuniaoDeIntegracao() {
		if (ePossuiModuloDeReuniaoIntegracao()){
			if (possuiMaisDeUmAtendimentoComStatusNormal()){
				adicionarErro("Não é possível existir mais de um atendimento em Reunião de Integração com status NORMAL.");
			}
		}
	}

	private boolean possuiMaisDeUmAtendimentoComStatusNormal() {
		return atendimentosGrupo.stream().filter(atendimentoGrupo -> atendimentoGrupo.estaNormal()).count() > 1;
	}

	public boolean ePossuiModuloDeReuniaoIntegracao() {
		return modulo != null && modulo.eReuniaoIntegracao();
	}
	
	public boolean ePossuiModuloDeReuniaoComInstituicaoDeEnsino() {
		return modulo != null && modulo.eReuniaoComAsInstituicoesDeEnsino();
	}

	public List<ModuloUsuario> obterUsuariosComStatusDiferenteDeProvisorioEAcesso() {
		return modulosUsuarios.stream().filter(modulosUsuario -> !modulosUsuario.estaProvisorioOuAcesso())
				.collect(Collectors.toList());
	}

	public void atualizarAtendimentoGrupo(
			AtendimentoGrupo atendimentoGrupoASalvar) {
		int indice = this.atendimentosGrupo.indexOf(atendimentoGrupoASalvar);
		this.atendimentosGrupo.set(indice, atendimentoGrupoASalvar);
	}
	
	public List<ModuloUsuario> getModulosUsuarios() {
		Collections.sort(modulosUsuarios, ModuloUsuario.obterComparador());
		return modulosUsuarios;
	}
	
	public List<Usuario> obterUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		for (ModuloUsuario moduloUsuario : modulosUsuarios) {
			usuarios.add(moduloUsuario.getUsuario());
		}
		return usuarios;
	}

	public List<PreCadastro> obterPreCadastro(){
		List<PreCadastro> preCadastro = new ArrayList<>();
		for (ModuloPreCadastro moduloPreCadastro : modulosPreCadastro) {
			preCadastro.add(moduloPreCadastro.getPreCadastro());			
		}
		return preCadastro;
	}
	
	public void incluirOuAlterarStatusModuloUsuario(List<ModuloUsuario> moduloUsuariosComStatusIntegradoOuProvisorioOuAcesso) {
		for (ModuloUsuario moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso : moduloUsuariosComStatusIntegradoOuProvisorioOuAcesso) {
			ModuloUsuario moduloUsuarioExistente = new ModuloUsuario();
			if (!existeUsuario(moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso.getUsuario())) {
				moduloUsuarioExistente.setUsuario(moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso.getUsuario());
				moduloUsuarioExistente.setIgnorarRegraDeReuniaoDeIntegracao(
						moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso.isIgnorarRegraDeReuniaoDeIntegracao());
				modulosUsuarios.add(moduloUsuarioExistente);
			} else {
				moduloUsuarioExistente = obterModuloUsuario(moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso.getUsuario());
			}
			moduloUsuarioExistente
					.atualizarStatusApartirDeStatusIntegradoOuProvisorioOuAcesso(moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso);
		}
	}
	
	public ModuloUsuario obterModuloUsuario(Usuario usuario) {
		for(ModuloUsuario moduloUsuario : modulosUsuarios){
			if (moduloUsuario.getUsuario().equals(usuario)){
				return moduloUsuario;
			}
		}
		return null;
	}
	
	public boolean eAtendimentoEspecificoEspecializado() {
		return modulo.eAtendimentoEspecificoEspecializado();
	}

	public void validarAutorizacaoAlteracaoDeGruposDeReunisaoDeIntegracao(ContaAcesso contaAcesso,
			ModuloPeriodo moduloPeriodoReuniaoIntegracaoOriginal) {
		if (ePossuiModuloDeReuniaoIntegracao()) {
			if (!Profissional.obterProfissionaisAutorizadosAAlterarModuloReuniaoDeIntegracao()
					.contains(contaAcesso.getProfissional())) {
				validarAlteracaoRestritaDeReuniaoDeIntegracao(moduloPeriodoReuniaoIntegracaoOriginal);
			}
		}
	}
	
	public boolean ePossuiModuloPsicossocialParaAsFamilias(){
		return modulo != null && modulo.ePsicossocialParaAsFamilias();
	}
	
	public boolean ePsicossocialParaAsFamiliasOuPsicossocialParaOsJovensOuReuniaoComAsInstituicoesDeEnsino(){
		return ePossuiModuloPsicossocialParaAsFamilias() || modulo.eReuniaoComAsInstituicoesDeEnsino() || modulo.ePsicossocialParaOsJovens();
	}
	
	public boolean eModuloSerAdolescente() {
		return modulo != null && modulo.eSerAdolescente();
	}
	
	public boolean eModuloHistoriasEscola() {
		return modulo != null && modulo.eHistoriasDaEscola();
	}

	public boolean eModuloEducacaoTecnologica() {
		return modulo != null && modulo.eEducacaoTecnologica();
	}

	public boolean eModuloAtividadesFuncionais() {
		return modulo != null && modulo.eAtividadesFuncionais();
	}

	public boolean eModuloAutonomiaEIndependencia() {
		return modulo != null && modulo.eAutonomiaEIndependencia();
	}
	
	public void vincularContaAcessoResponsavelPorOperacao(ContaAcesso contaAcesso) {
		this.contaAcessoReponsavelPorOperacao = contaAcesso;
	}
	
	public boolean existeUsuarioComStatusRelacaoDiferente(ModuloUsuario moduloUsuario) {
		return existeUsuario(moduloUsuario.getUsuario()) && estaComStatusUsuarioDiferente(moduloUsuario);
	}

	private boolean estaComStatusUsuarioDiferente(ModuloUsuario moduloUsuarioAVerificar) {
		return modulosUsuarios.stream()
				.anyMatch(moduloUsuario -> moduloUsuario.getUsuario().equals(moduloUsuarioAVerificar.getUsuario())
						&& !moduloUsuario.getStatus().equals(moduloUsuarioAVerificar.getStatus()));
	}

	public boolean existeUsuario(Usuario usuario) {
		List<Usuario> usuarios = obterUsuarios();
		return usuarios.contains(usuario);
	}
	
	public boolean ePossuiModulo(Modulo modulo) {
		return this.modulo.equals(modulo);
	}
	
	public void removerAutomaticamenteParaOutroGrupoDeReuniaoIntegracao(Usuario usuario){
		removerAutomaticamente(usuario, "para outro grupo de Reunião de Integração por consequência de falta no grupo original.");
	}
	
	public void removerAutomaticamentePorExcessoDeFalta(Usuario usuario) {
		removerAutomaticamente(usuario, "por Excesso de Faltas.");
	}
	
	private void removerAutomaticamente(Usuario usuario, String descricaoTextual) {
		ModuloUsuario moduloUsuarioARemover = modulosUsuarios.stream()
				.filter(moduloUsuario -> moduloUsuario.getUsuario().equals(usuario)).findFirst().get();
		moduloUsuarioARemover.setStatus(StatusRelacaoComModulo.REMOVIDO);
		moduloUsuarioARemover.adicionarObs("---------------------------------------------------------------"
				+ "\nRemovido automaticamente " + descricaoTextual + " Data da remoção: "
				+ DataHoraUtils.formatarData(MaquinaTempo.obterInstancia().obterCalendarioAtual())
				+ "\n-------------------------------------------------------------");
	}
	
	private List<ModuloUsuario> filtrarModuloUsuario(Predicate<ModuloUsuario> condicional) {
		return modulosUsuarios.stream().filter(condicional).collect(Collectors.toList());
	}

	public List<ModuloUsuario> obterModuloUsuariosComStatusProvisorioOuAcesso() {
		return filtrarModuloUsuario(moduloUsuario -> moduloUsuario.estaProvisorioOuAcesso());
	}
	
	public List<ModuloUsuario> obterModuloUsuariosComStatusIntegradoOuProvisorioOuAcesso() {
		return filtrarModuloUsuario(moduloUsuario -> moduloUsuario.estaIntegradoOuProvisorioOuAcesso());
	}
	
	public boolean possuiAtendimentoDeGrupoNormalComFrequenciaAT(Usuario usuario) {
		return atendimentosGrupo.stream().anyMatch(atendimentoGrupo -> atendimentoGrupo.estaNormal()
				&& atendimentoGrupo.eAtendimentoNormalComFrequenciaAT(usuario));
	}
	
	public List<Usuario> obterUsuariosComStatusDesligado() {
		return modulosUsuarios.stream()
				.filter(moduloUsuarioComStatusDesligado -> moduloUsuarioComStatusDesligado.estaDesligado())
				.map(ModuloUsuario::getUsuario).collect(Collectors.toList());
	}

	public List<Usuario> obterUsuariosComStatusIntegradoOuProvisorioOuAcessoSujeitosARegraDaReuniaoDeIntegracao() {
		return filtrarUsuariosComStatusIntegradoOuProvisorioOuAcesso(
				moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso -> !moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso
						.isIgnorarRegraDeReuniaoDeIntegracao());
	}

	public List<Usuario> obterUsuariosComStatusAcessoOuIntegrado() {
		return filtrarModuloUsuario(moduloUsuario -> moduloUsuario.estaIntegradoOuAcesso()).stream()
				.map(moduloUsuario -> moduloUsuario.getUsuario()).collect(Collectors.toList());
	}
	
	public List<Usuario> obterUsuariosComStatusIntegradoOuProvisorioOuAcesso() {
		return filtrarUsuariosComStatusIntegradoOuProvisorioOuAcesso(moduloUsuario -> moduloUsuario.possuiUsuario());
	}
	
	private List<Usuario> filtrarUsuariosComStatusIntegradoOuProvisorioOuAcesso(Predicate<ModuloUsuario> condicional){
		return obterModuloUsuariosComStatusIntegradoOuProvisorioOuAcesso().stream().filter(condicional)
				.map(ModuloUsuario::getUsuario).collect(Collectors.toList());
	}

	public List<ModuloPreCadastro> getModulosPreCadastro() {
		Collections.sort(modulosPreCadastro, ModuloPreCadastro.obterComparador());
		return modulosPreCadastro;
	}
	
	public void propagarStatus(ModuloPeriodo moduloPeriodoResponsavelPropagacao, ModuloUsuario moduloUsuarioAPropagar) {
		if (existeUsuario(moduloUsuarioAPropagar.getUsuario())) {
			ModuloUsuario moduloUsuarioExistente = obterModuloUsuario(moduloUsuarioAPropagar.getUsuario());
			moduloUsuarioExistente.propagar(moduloPeriodoResponsavelPropagacao, moduloUsuarioAPropagar);
		}
	}
	
	public void setModulosUsuarios(List<ModuloUsuario> modulosUsuarios) {
		adicionarModulosRelacao(modulosUsuarios, this.modulosUsuarios);
	}
	
	public boolean eDiaSemanaCompativel(Calendar dataInicio) {
		if (diasSemanaEHorariosDaAtividade.size() == 1) {
			return EntidadeUtils.diaSemanaCompativelComData(diasSemanaEHorariosDaAtividade.get(0).getDiaSemana(),
					dataInicio);
		}
		return false;
	}
	
	public String obterRelacaoUsuariosComStatusProvisorioOuAcesso() {
		List<ModuloUsuario> moduloUsuariosComStatusProvisorioOuAcesso = obterModuloUsuariosComStatusProvisorioOuAcesso();
		String relacaoUsuarios = "";
		if (!moduloUsuariosComStatusProvisorioOuAcesso.isEmpty()) {
			relacaoUsuarios += "<br>Usuário(s) com status PROVISÓRIO ou ACESSO no módulo:<br>";
			for (ModuloUsuario moduloUsuarioComStatusProvisorioOuAcesso : moduloUsuariosComStatusProvisorioOuAcesso) {
				Usuario usuairoComStatusProvisorioOuAcesso = moduloUsuarioComStatusProvisorioOuAcesso.getUsuario();
				relacaoUsuarios += usuairoComStatusProvisorioOuAcesso.getId() + " - "
						+ usuairoComStatusProvisorioOuAcesso.getInformacaoEssencial().getNome() + "<br>";
			}
		}
		return relacaoUsuarios;
	}
	
	public void setModulosPreCadastro(
			List<ModuloPreCadastro> modulosPreCadastro) {
		adicionarModulosRelacao( modulosPreCadastro, this.modulosPreCadastro);
	}
	
	private void preencherInformacoesAuxiliaresDeAtendimentos() {
		for (AtendimentoGrupo atendimentoGrupo : atendimentosGrupo) {
			atendimentoGrupo
					.preencherInformacoesAuxiliaresNoAtendimentoPreCadastro(modulosPreCadastro);
			atendimentoGrupo
					.preencherInformacaoAuxiliaresNoAtendimentoProfissional(profissionaisVinculo);
		}
	}
	
	public boolean estaVazio() {
		return modulosUsuarios.isEmpty() && modulosPreCadastro.isEmpty();
	}

	@SuppressWarnings("unchecked")
	private void adicionarModulosRelacao(
			List<? extends ModuloRelacaoBase> modulosRelacoes,
			List<? extends ModuloRelacaoBase> modulosRelacoesExistentes) {
		for (ModuloRelacaoBase moduloRelacaoBase : modulosRelacoes) {
			int valorIndice = modulosRelacoesExistentes
					.indexOf(moduloRelacaoBase);
			if (valorIndice != -1 && moduloRelacaoBase.getId() != null) {
				((List<ModuloRelacaoBase>) modulosRelacoesExistentes).set(
						valorIndice, moduloRelacaoBase);
			} else {
				((List<ModuloRelacaoBase>) modulosRelacoesExistentes)
						.add(moduloRelacaoBase);
			}
		}
	}
	
	public List<Usuario> obterUsuariosIntegradosPorModuloPeriodo() {
		List<Usuario> usuarios = new ArrayList<>();
		List<ModuloRelacaoBase> moduloRelacaoBases = obterIntegrados(modulosUsuarios);
		for (ModuloRelacaoBase moduloRelacaoBase : moduloRelacaoBases) {
			usuarios.add(((ModuloUsuario) moduloRelacaoBase).getUsuario());
		}
		return usuarios;
	}
	
	private List<PreCadastro> obterPreCadastroIntegradosPorModuloPeriodo() {
		List<PreCadastro> preCadastros = new ArrayList<>();
		List<ModuloRelacaoBase> moduloRelacaoBases = obterIntegrados(modulosPreCadastro);
		for (ModuloRelacaoBase moduloRelacaoBase : moduloRelacaoBases) {
			preCadastros.add(((ModuloPreCadastro) moduloRelacaoBase)
					.getPreCadastro());
		}
		return preCadastros;
	}
	
	private List<ModuloRelacaoBase> obterIntegrados(
			List<? extends ModuloRelacaoBase> modulosRelacaoBase) {
		List<ModuloRelacaoBase> retorno = new ArrayList<>();
		for (ModuloRelacaoBase moduloUsuario : modulosRelacaoBase) {
			if (moduloUsuario.estaIntegrado()) {
				retorno.add(moduloUsuario);
			}
		}
		return retorno;
	}
	
	public String periodoHorario() {
		return obterPeriodosHorarios().toString();
	}
	
	public void gerarAtendimentosDeGrupo(Grupo grupo, Modulo modulo, EspecificacaoGeracaoAtendimento especificacaoGeracaoAtendimento) {
		AtendimentoGrupo atendimento = new AtendimentoGrupo(grupo, modulo, 
				obterUsuariosIntegradosOuEventuais(),
				obterPreCadastroIntegradosPorModuloPeriodo(), obterProfissionais());
		atendimento.setData(especificacaoGeracaoAtendimento.getData());
		atendimento.setHorario(especificacaoGeracaoAtendimento.getHorario());
		atendimentosGrupo.add(atendimento);
	}
	
	private List<Usuario> obterUsuariosIntegradosOuEventuais() {
		List<Usuario> usuariosIntegradosOuEventuais = obterUsuariosIntegradosPorModuloPeriodo();
		usuariosIntegradosOuEventuais.addAll(obterUsuariosEventuais());
		return usuariosIntegradosOuEventuais;
	}
	
	private List<Usuario> obterUsuariosEventuais() {
		return modulosUsuarios.stream().filter(moduloUsuario -> moduloUsuario.estaEventual())
				.map(moduloUsuario -> moduloUsuario.getUsuario()).collect(Collectors.toList());
	}	
	
	private List<Profissional> obterProfissionais(){
		List<Profissional> profissionais = new ArrayList<>();
		for(ProfissionalVinculo profissionalVinculo : profissionaisVinculo){
			profissionais.add(profissionalVinculo.getProfissional());
		}
		return profissionais;
	}
	
	private void validarAtendimentosGrupo(){
		for(AtendimentoGrupo atendimento : atendimentosGrupo){
			atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (!atendimento.validado()){
				adicionarErro(atendimento.obterErros());
			}
		}
	}
	
	private void validarDiasSemanaEHorarioAtividade(){
		for(DiaSemanaEHorario diaSemanaEHorario : diasSemanaEHorariosDaAtividade){
			diaSemanaEHorario.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (!diaSemanaEHorario.validado()){
				adicionarErro(diaSemanaEHorario.obterErros());
			}
		}
	}

	public void desligarTodosOsModulosComUsuario(Usuario usuarioADesligar, Grupo grupoOrigemDesligamento,
			ModuloPeriodo moduloPeriodoOrigemDesligamento, String dataOcorrenciaOrigem, String obs) {
		for (ModuloUsuario moduloUsuario : modulosUsuarios) {
			if (moduloUsuario.getUsuario() != null && moduloUsuario.getUsuario().equals(usuarioADesligar)) {
				moduloUsuario.desligar(grupoOrigemDesligamento, moduloPeriodoOrigemDesligamento, dataOcorrenciaOrigem, obs);
			}
		}
	}
	
	private int quantidadeUsuariosIntegrados() {
		List<ModuloRelacaoBase> moduloRelacaoBases = obterIntegrados(modulosUsuarios);
		return moduloRelacaoBases.size();
	}

	public static boolean possuiUsuarioDuplicado(List<ModuloUsuario> modulosUsuario,
			ModuloUsuario moduloUsuarioAVerificar) {
		boolean existe = false;
		for (ModuloUsuario moduloUsuario : modulosUsuario) {
			if (moduloUsuario.getUsuario() != null
					&& moduloUsuario.getUsuario().getId().equals(moduloUsuarioAVerificar.getUsuario().getId())) {
				existe = true;
			}
		}
		return existe;
	}

	public AtendimentoGrupo obterAtendimentoGrupoCriadoMaisRecentemente() {
		return atendimentosGrupo.stream().max(Comparator.comparing((AtendimentoBase i) -> {
			return i.getId();
		})).get();
	}
	
	public AtendimentoGrupo obterModeloAtendimentoGrupoCriadoMaisRecentemente() {
		return atendimentosGrupo.stream().filter(atendimentoGrupo -> !atendimentoGrupo.possuiId()).findFirst()
				.orElse(null);
	}
	
	public static boolean possuiPreCadastroDuplicado(
			List<ModuloPreCadastro> modulosPreCadastro,
			ModuloPreCadastro moduloPreCadastroAVerificar) {
		boolean existe = false;
		for (ModuloPreCadastro moduloPreCadastro : modulosPreCadastro) {
			if (moduloPreCadastroAVerificar.getPreCadastro() != null && moduloPreCadastro.getPreCadastro().getId()
					.equals(moduloPreCadastroAVerificar.getPreCadastro().getId())) {
				existe = true;
			}
		}
		return existe;
	}
	
	public void atualizarStatusDeProvisorioParaAcesso(Usuario usuario, Grupo grupo, String dataAtendimentoDeRI) {
		ModuloUsuario moduloUsuario = obterModuloUsuario(usuario);
		if (moduloUsuario.estaProvisorio()) {
			moduloUsuario.setStatus(StatusRelacaoComModulo.ACESSO);
			moduloUsuario.adicionarObs(
					"Status atualizado de PROVISORIO para ACESSO devido frequencia AT em Reunião de Integração no "
							+ grupo.obterNomeGrupoETurma() + ", data " + dataAtendimentoDeRI +".");
			sofreuMudancaDeStatusDeProvisorioParaAcesso = true;
		}
	}
	
	public boolean sofreuMudancaDeStatusDeProvisorioParaAcesso() {
		return sofreuMudancaDeStatusDeProvisorioParaAcesso;
	}

	public void adicionarNovoAtendimentoGrupo(AtendimentoGrupo atendimentoGrupo) {
		if (!atendimentosGrupo.contains(atendimentoGrupo)) {
			atendimentosGrupo.add(atendimentoGrupo);
		} else {
			atendimentosGrupo.set(atendimentosGrupo.indexOf(atendimentoGrupo), atendimentoGrupo);
		}
	}
	
	private boolean possueUsuarioIntegrado() {
		return !obterIntegrados(this.modulosUsuarios).isEmpty();
	}
	
	public List<ModuloUsuario> getModulosUsuariosDesligados() {
		return modulosUsuarios.stream()
				.filter(moduloUsuario -> moduloUsuario.estaDesligado())
				.collect(Collectors.toList());
	}

	public boolean possuiVagasDispoveisParaUsuariosIntegradoOuProvisoriosOuAcesso() {
		long quantidadeVagasAOcupar = vagas - quantidadeUsuariosIntegrados();
		return quantidadeVagasAOcupar > 0;
	}

	private boolean possuiMaisUsuaroisIntegradosQueVagas() {
		return vagas != null && quantidadeUsuariosIntegrados() > vagas;
	}
	
	public boolean existeUsuarioIntegrado(Usuario usuarioIntegrado) {
		return obterUsuariosIntegradosPorModuloPeriodo().stream()
				.anyMatch(usuario -> usuario.equals(usuarioIntegrado));
	}
	
	private void validarNumeroDeVagasExcedidas() {
		if (possuiMaisUsuaroisIntegradosQueVagas()) {
			adicionarErro("Número de vagas excedidas.");
		}
	}

	private void validarAlteracaoRestritaDeReuniaoDeIntegracao(ModuloPeriodo moduloPeriodoReuniaoIntegracaoOriginal) {
		for (ModuloUsuario moduloUsuario : modulosUsuarios) {
			ModuloUsuario moduloUsuarioOriginal = moduloPeriodoReuniaoIntegracaoOriginal
					.obterModuloUsuario(moduloUsuario.getUsuario());
			if (!moduloUsuario.possuiId()) {
				adicionarErro("Somente profissionais do Serviço Social podem incluir um usuário manualmente.");
			}
			if (moduloUsuarioOriginal != null
					&& moduloUsuario.possuiStatusOuDataOcorrenciaDiferente(moduloUsuarioOriginal)) {
				adicionarErro("Somente profissionais do Serviço Social podem alterar o status e a data de ocorrencia.");
			}
		}
	}

	private void validarExistenciaDeUsuarioOuPreCadastro() {
		if (estaVazio()) {
			adicionarErro("Não é possível gerar atendimentos para um grupo sem integrantes.");
		}
	}
	
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (modulo == null){
			adicionarErro("Insira um Módulo/Atividade.");
		}
		if (diasSemanaEHorariosDaAtividade.isEmpty()){
			adicionarErro("Insira um conjunto de Dia da Semana e Horario.");
		}
		if (vagas == null || NumeroUtils.numeroInteiroInvalido(vagas)) {
			adicionarErro("Insira a quantidade de Vagas.");
		}
		if (cargaHoraria == null) {
			adicionarErro("Insira uma Carga Horária.");
		}
		if (cargaHorariaMinima == null) {
			adicionarErro("Insira uma Carga Horária Minima.");
		}
		if (obterProfissionais().isEmpty()){
			adicionarErro("Insira pelo menos um Profissional.");
		}
		if (localAtendimento == null){
			adicionarErro("Insira um Local de Atendimento.");
		}
		validarDiasSemanaEHorarioAtividade();
		validarAtendimentosGrupo();
		validarNumeroDeVagasExcedidas();
		validarExistenciaAtendimentoDuplicado();
		validarStatusInvalidoEmModuloDeReuniaoDeIntegracao();
		validarExistenciaDeAcessoNoPsicossocial();
		validarExistenciaDeMaisDeUmAtendimentoNormalEmReuniaoDeIntegracao();
		validarRestricaoDeAlteracao();
	}
	
	protected void validarRestricaoDeAlteracao() {
		if (!ePossuiModuloDeReuniaoIntegracao() && contaAcessoReponsavelPorOperacao != null
				&& !contaAcessoReponsavelPorOperacao.eRoot()
				&& !contaAcessoReponsavelPorOperacao.contemProfissional(obterProfissionais())) {
			adicionarErro("Somente o profissional vinculado ao módulo pode efetuar alterações.");
		}
	}

	private void validarExistenciaDeAcessoNoPsicossocial() {
		if (ePossuiModuloPsicossocialParaAsFamilias() && possuiUsuarioComStatusAcesso()) {
			adicionarErro(
					"Não é possível existir um usuário com status ACESSO no Psicossocial para as famílias.");
		}
	}

	private boolean possuiUsuarioComStatusAcesso() {
		return modulosUsuarios.stream().anyMatch(moduloUsuario -> moduloUsuario.estaAcesso());
	}

	private void validarStatusInvalidoEmModuloDeReuniaoDeIntegracao() {
		if (ePossuiModuloDeReuniaoIntegracao() && possuiUsuarioComStatusDiferenteDeIntegradoERemovido()) {
			adicionarErro(
					"Não é possível existir um usuário com status diferente de INTEGRADO, REMOVIDO ou DESLIGADO na Reunião de Integração.");
		}
	}

	public void validarExistenciaDeIntegradosNoOSEeCL(Grupo grupo){
		if (grupo.eDescricaoOrientacaoSocioeducativa() || grupo.eDescricaoAtividadeDeCulturaELazer()){
			if (possueUsuarioIntegrado()){
				adicionarErro("Não é possível existir um usuário INTEGRADO em grupos OSE ou CL.");
			}
		}
	}
	
	private boolean possuiUsuarioComStatusDiferenteDeIntegradoERemovido() {
		return modulosUsuarios.stream().anyMatch(moduloUsuario -> !moduloUsuario.estaIntegradoOuRemovidoOuDesligado());
	}
	
	private void validarExistenciaAtendimentoDuplicado() {
		List<AtendimentoGrupo> atendimentosGruposGerados = atendimentosGrupo.stream()
				.filter(atendimentosGrupo -> !atendimentosGrupo.possuiId()).collect(Collectors.toList());
		if (atendimentosGruposGerados.stream().anyMatch(atendimentoGrupoGerado -> atendimentosGrupo.stream()
				.anyMatch(atendimentoGrupo -> !atendimentoGrupo.equals(atendimentoGrupoGerado)
						&& !atendimentoGrupo.estaCancelado()
						&& atendimentoGrupo.obterData().equals(atendimentoGrupoGerado.obterData()) && Validavel
								.conflitoHora(atendimentoGrupo.getHorario(), atendimentoGrupoGerado.getHorario())))) {
			adicionarErro(
					"Não é possível gerar um novo atendimento de grupo em horário conflitante com um atendimento já existente.");
		}
	}
	
	public void validarExistenciaUsuarioComStatusProvisorioOuAcesso() {
		if (possuiUsuarioComStatusProvisorioOuAcesso()) {
			adicionarErro("Existe pelo menos um usuário com status Provisório ou Acesso.");
		}
	}
	
	public static final Comparator<ModuloPeriodo> obterComparador() {
		return new Comparator<ModuloPeriodo>() {
			public int compare(ModuloPeriodo o1, ModuloPeriodo o2) {
				return o1.getModulo().getNome()
						.compareTo(o2.getModulo().getNome());
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
		ModuloPeriodo other = (ModuloPeriodo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ModuloPeriodo [id=" + id + ", modulo=" + modulo + ", diasSemanaEHorarios=" + diasSemanaEHorariosDaAtividade
				+ ", profissionaisVinculo=" + profissionaisVinculo + ", localAtendimento=" + localAtendimento
				+ ", vagas=" + vagas + ", cargaHoraria=" + cargaHoraria
				+ ", carga_horaria_minima=" + cargaHorariaMinima
				+ ", programação=" + programacoes + ", atendimentosGrupo = "
				+ atendimentosGrupo + ", modulosUsuarios=" + modulosUsuarios + "]";
	}
}

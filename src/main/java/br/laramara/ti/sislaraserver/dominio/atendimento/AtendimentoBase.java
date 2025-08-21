package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.sql.Time;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.grupo.TamanhoMaximoGrupo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

@MappedSuperclass
public abstract class AtendimentoBase extends Validavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "categoria", length = TamanhoMaximoGrupo.CATEGORIA_ATENDIMENTO, nullable = false)
	protected CategoriaAtendimento categoriaAtendimento;

	public static final String COLUNA_DATA = "data";
	@Temporal(TemporalType.DATE)
	@Column(name = COLUNA_DATA, nullable = false)
	protected Calendar data;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGrupo.STATUS_ATENDIMENTO)
	protected StatusAtendimento statusAtendimento;

	protected Horario horario;

	@Column(name = "total_horas", nullable = false)
	protected Time totalHoras;
	
	@Transient
	private ContaAcesso contaAcessoReponsavelPorOperacao;
	
	public AtendimentoBase(){
	}

	public AtendimentoBase(CategoriaAtendimento categoriaAtendimento) {
		this.horario = new Horario();
		this.categoriaAtendimento = categoriaAtendimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	protected List<Profissional> obterProfissionaisComFrequencia(
			List<AtendimentoProfissional> atendimentosProfissionais, Predicate<AtendimentoProfissional> predicado) {
		return atendimentosProfissionais.stream().filter(predicado).map(AtendimentoProfissional::getProfissional)
				.collect(Collectors.toList());
	}

	protected List<Profissional> obterProfissionaisComQualquerFrequencia(List<AtendimentoProfissional> atendimentosProfissionais) {
		return obterProfissionaisComFrequencia(atendimentosProfissionais,
				atendimentoProfissional -> atendimentoProfissional.getInformacaoAtendimento()
						.estaComQualquerFrequencia());
	}
	
	protected List<Profissional> obterProfissionaisComFrequenciaATouFP(
			List<AtendimentoProfissional> atendimentosProfissionais) {
		return obterProfissionaisComFrequencia(atendimentosProfissionais,
				atendimentoProfissional -> atendimentoProfissional.getInformacaoAtendimento().estaComFrequenciaATouFP());
	}

	protected List<Profissional> obterProfissionaisComFrequenciaATouFRouFPouOAouRC(
			List<AtendimentoProfissional> atendimentosProfissionais) {
		return obterProfissionaisComFrequencia(atendimentosProfissionais,
				atendimentoProfissional -> atendimentoProfissional.getInformacaoAtendimento()
						.estaComFrenquenciaATouFRouFPouOAouRC());
	}
	
	public CategoriaAtendimento getCategoriaAtendimento() {
		return categoriaAtendimento;
	}

	public Calendar obterData(){
		return data;
	}
	
	public String getData() {
		return DataHoraUtils.formatarData(data);
	}

	public void setData(String data) {
		this.data = DataHoraUtils.obterDataValidaInvalidaOuNulo(data);
	}

	public StatusAtendimento getStatusAtendimento() {
		return statusAtendimento;
	}

	public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
		if (statusAtendimento != null) {
			this.statusAtendimento = statusAtendimento;
		}
	}

	public void cancelar() {
		setStatusAtendimento(StatusAtendimento.CANCELADO);
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
		calcularHoraTotal();
	}

	private void calcularHoraTotal() {
		this.totalHoras = horario.obterTempoDecorrido();
	}

	public boolean estaCancelado() {
		return statusAtendimento.equals(StatusAtendimento.CANCELADO);
	}
	
	public boolean estaNormal() {
		return statusAtendimento.equals(StatusAtendimento.NORMAL);
	}
	
	public void vincularContaAcessoResponsavelPorOperacao(ContaAcesso contaAcesso) {
		this.contaAcessoReponsavelPorOperacao = contaAcesso;
	}

	public static boolean validarDuplicacaoArquivos(List<Arquivo> arquivos) {
		List<String> nomesArquivos = arquivos.stream().map(arquivo -> arquivo.getNome()).collect(Collectors.toList());
		return possuiOcorrenciaDuplicada(nomesArquivos);
	}
	
	protected static boolean possuiOcorrenciaDuplicada(List<?> a) {
		return a.stream().anyMatch(o -> comparar(a, o) > 1);
	}

	private static int comparar(List<?> a, Object o) {
		return Collections.frequency(a, o);
	}
	
	protected void inicializarStatusAtendimento() {
		if (statusAtendimento == null) {
			statusAtendimento = StatusAtendimento.NORMAL;
		}
	}

	protected void validarObrigatoriedadeETamanhoMaximoDeDadosHorario() {
		horario.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (!horario.validado()) {
			adicionarErro(horario.obterErros());
		}
	}

	protected void validarObrigatoriedadeDeData() {
		if (data == null || DataHoraUtils.dataInvalida(data)) {
			adicionarErro("Insira uma Data de Atendimento válida.");
		}
		if (data != null && DataHoraUtils.dataPosteriorDataAtual(data)
				&& !estaCancelado()) {
			adicionarErro("Insira uma data igual ou anterior ao dia de hoje.");
		}
	}

	protected void validarObrigatoriedadeDeCategoriaAtendimento() {
		if (categoriaAtendimento == null) {
			adicionarErro("O atendimento deve possuir uma Categoria.");
		}
	}
	
	protected void validarRestricaoDeAlteracao(List<AtendimentoProfissional> atendimentosProfissionais) {
		if (contaAcessoReponsavelPorOperacao != null && !contaAcessoReponsavelPorOperacao
				.contemProfissional(obterProfissionaisComQualquerFrequencia(atendimentosProfissionais))) {
			adicionarErro("Somente os profissionais vinculados ao atendimento podem alterar o registro.");
		}
	}

	protected void validarFaltaDeProfissionais(List<AtendimentoProfissional> atendimentosProfissionais,
			boolean todosOsUsuariosPossuemFrequenciaFP) {
		if (estaNormal() && todosOsProfissionaisPossuemFrequenciaFP(atendimentosProfissionais)) {
			if (!todosOsUsuariosPossuemFrequenciaFP) {
				adicionarErro("A frequencia FP é obrigatória aos usuários.");
			}
		}
	}
	
	protected boolean todosOsProfissionaisPossuemFrequenciaFP(List<AtendimentoProfissional> atendimentosProfissionais) {
		return !atendimentosProfissionais.isEmpty() && atendimentosProfissionais.stream()
				.allMatch(atendimentoProfissional -> atendimentoProfissional.possuiFrequenciaFP());
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
		AtendimentoBase other = (AtendimentoBase) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

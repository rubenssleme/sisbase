package br.laramara.ti.sislaraserver.dominio.agenda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemana;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

public class EspecificacaoGeracaoAgendamento extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	private Usuario usuario;
	private PreCadastro preCadastro;
	private List<Grupo> grupos;
	private List<Profissional> profissionais;
	private DescricaoTipoAtendimento descricaoTipoAtendimento;
	private Modulo modulo;
	private Setor setor;
	private LocalAtendimento localAtendimento;
	private Calendar dataInicio;
	private Calendar dataTermino;
	private DiaSemana diaSemana;
	private Horario horario;
	private Status reservaStatus;
	private String obs;
	private int itemAgendado;
	
	public EspecificacaoGeracaoAgendamento() {
		super();
		horario = new Horario();
		grupos = new ArrayList<>();
		profissionais = new ArrayList<>();
	}
	
	private boolean possuiMaisDeUmItemAgendado() {
		return itemAgendado > 1;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		if (usuario != null) {
			this.usuario = usuario;
			this.itemAgendado++;
		}
	}

	public PreCadastro getPreCadastro() {
		return preCadastro;
	}

	public void setPreCadastro(PreCadastro preCadastro) {
		if (preCadastro != null) {
			this.preCadastro = preCadastro;
			this.itemAgendado++;
		}
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		if (!grupos.isEmpty()) {
			this.grupos = grupos;
			this.itemAgendado++;
		}
	}

	public List<Profissional> getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(List<Profissional> profissionais) {
		this.profissionais = profissionais;
	}

	public DescricaoTipoAtendimento getDescricaoTipoAtendimento() {
		return descricaoTipoAtendimento;
	}

	public void setDescricaoTipoAtendimento(
			DescricaoTipoAtendimento descricaoTipoAtendimento) {
		this.descricaoTipoAtendimento = descricaoTipoAtendimento;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public LocalAtendimento getLocalAtendimento() {
		return localAtendimento;
	}

	public void setLocalAtendimento(LocalAtendimento localAtendimento) {
		this.localAtendimento = localAtendimento;
	}

	public String getDataInicio() {
		return DataHoraUtils.formatarData(dataInicio);
	}
	
	public Calendar getDataInicioCalendar(){
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = DataHoraUtils
				.obterDataValidaInvalidaOuNulo(dataInicio);
	}

	public String getDataTermino() {
		return DataHoraUtils.formatarData(dataTermino);
	}
	
	public Calendar getDataTerminoCalendar() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = DataHoraUtils
				.obterDataValidaInvalidaOuNulo(dataTermino);
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public Status getReservaStatus() {
		return reservaStatus;
	}

	public void setReservaStatus(Status reservaStatus) {
		this.reservaStatus = reservaStatus;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (profissionais.isEmpty()) {
			adicionarErro("Insira um Profissional.");
		}
		if (reservaStatus == null){
			adicionarErro("Insira um Status para Reserva.");
		}
		if (descricaoTipoAtendimento == null) {
			adicionarErro("Insira uma Descrição de Tipo de Atendimento.");
		}
		if (modulo == null){
			adicionarErro("Insira um Modulo.");
		}
		if (setor == null) {
			adicionarErro("Insira o Setor.");
		}
		if (localAtendimento == null) {
			adicionarErro("Insira o Local de Atendimento.");
		}
		if (dataInicio == null || DataHoraUtils.dataInvalida(dataInicio)) {
			adicionarErro("Insira uma Data de Início válida.");
		}
		if (DataHoraUtils.dataTerminoAnteriorDataInicio(dataInicio, dataTermino)){
			adicionarErro("Insira uma Data de Témino posterior a Data de Início.");
		}
		if (dataInicio != null && dataTermino != null
				&& dataInicio.equals(dataTermino)) {
			adicionarErro("Insira uma Data de Início diferente da Data de Témino.");
		}
		if (dataInicio != null && dataTermino != null
				&& !DataHoraUtils.dataInvalida(dataInicio)
				&& !DataHoraUtils.dataInvalida(dataTermino)
				&& possuiVariacaoSuperiorA6Meses(dataInicio, dataTermino)) {
			adicionarErro("Insira um período com diferença máxima de 6 meses.");
		}
		validarObrigatoriedadeETamanhoMaximoDeDadosHorario();
		if (possuiMaisDeUmItemAgendado()) {
			adicionarErro("Insira somente um Usuário, Pré-Cadastro ou Grupo.");
		}
		validarTamanhoMaximoDeDados();
		validarObrigatoriedadeMultiploAgendamento();
	}
	
	private void validarObrigatoriedadeETamanhoMaximoDeDadosHorario() {
		horario.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (!horario.validado()) {
			adicionarErro(horario.obterErros());
		}
	}
	
	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(obs, TamanhoMaximoGenerico.OBS)) {
			adicionarErro("Insira uma Observação contendo até "
					+ TamanhoMaximoGenerico.OBS + " caracteres.");
		}
	}

	public boolean solicitacaoAgendamentoUnico(){
		return !solicitacaoAgendamentoMultiplo();
	}
	
	private boolean possuiVariacaoSuperiorA6Meses(Calendar dataInicio,
			Calendar dataTermino) {
		Calendar dataInicioMais6Meses = (Calendar) dataInicio.clone();
		dataInicioMais6Meses.add(Calendar.MONTH, 6);

		return dataTermino.after(dataInicioMais6Meses);
	}
	
	public boolean solicitacaoAgendamentoMultiplo(){
		return diaSemana != null || dataTermino != null;
	}
	
	private void validarObrigatoriedadeMultiploAgendamento() {
		if (solicitacaoAgendamentoMultiplo()) {
			if (dataTermino == null || DataHoraUtils.dataInvalida(dataTermino)) {
				adicionarErro("Insira uma Data de Término válida.");
			}
			if (diaSemana == null) {
				adicionarErro("Insira um Dia da Semana.");
			}
		}
	}

	@Override
	public String toString() {
		return "EspecificacaoGeracaoAgendamento [usuario=" + usuario
				+ ", preCadastro=" + preCadastro + ", grupos=" + grupos
				+ ", profissionais=" + profissionais
				+ ", descricaoTipoAtendimento=" + descricaoTipoAtendimento
				+ ", modulo=" + modulo + ", setor=" + setor
				+ ", localAtendimento=" + localAtendimento + ", dataInicio="
				+ DataHoraUtils.formatarData(dataInicio) + ", dataTermino="
				+ DataHoraUtils.formatarData(dataTermino) + ", diaSemana="
				+ diaSemana + ", horario=" + horario + ", reservaStatus="
				+ reservaStatus + ", obs=" + obs + "]";
	}
}

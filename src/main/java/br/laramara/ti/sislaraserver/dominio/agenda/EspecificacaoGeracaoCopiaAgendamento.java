package br.laramara.ti.sislaraserver.dominio.agenda;

import java.util.Calendar;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

public class EspecificacaoGeracaoCopiaAgendamento extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	private Calendar data;
	private Horario horario;
	
	public EspecificacaoGeracaoCopiaAgendamento() {
		this.horario = new Horario();
	}

	public Calendar getData() {
		return data;
	}

	public void setData(String data) {
		this.data = DataHoraUtils.obterDataValidaInvalidaOuNulo(data);
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (data == null || DataHoraUtils.dataInvalida(data)) {
			adicionarErro("Insira uma Data válida.");
		}
		validarObrigatoriedadeETamanhoMaximoDeDadosHorario();
	}
	
	private void validarObrigatoriedadeETamanhoMaximoDeDadosHorario() {
		horario.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (!horario.validado()) {
			adicionarErro(horario.obterErros());
		}
	}

	@Override
	public String toString() {
		return "EspecificacaoGeracaoCopiaAgendamento [data=" + DataHoraUtils.formatarData(data)
				+ ", horario=" + horario + "]";
	}
}

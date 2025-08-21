package br.laramara.ti.sislaraserver.dominio.pendencia;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

@Entity
@DiscriminatorValue(value = TipoPendencia.DescricaoTipo.ENVIO_LISTA_ESPERA_EXCESSO_DE_FALTAS)
public class PendenciaEnvioListaEsperaPorExcessoDeFaltas extends Pendencia {
	
	@Column(name = "prontuario")
	private Long prontuario;
	
	public PendenciaEnvioListaEsperaPorExcessoDeFaltas() {
	}

	public PendenciaEnvioListaEsperaPorExcessoDeFaltas(Long prontuario, List<Profissional> profissionaisAfetados) {
		super();
		this.prontuario = prontuario;
		this.profissionaisAfetados = profissionaisAfetados;
	}

	public Long getProntuario() {
		return prontuario;
	}

	@Override
	public boolean igualOuAnteriorADataAtual() {
		return false;
	}

	@Override
	public Calendar getDataParaComparacao() {
		return DataHoraUtils.obterDataInvalida();
	}

	@Override
	public String obterDescricaoPendencia() {
		return "(IMPORTANTE)Prontuário " + prontuario + " foi enviado para a lista de espera do SS por excesso de faltas.";
	}

	@Override
	public String toString() {
		return "PendenciaEnvioListaEsperaPorExcessoDeFaltas [" + super.toString() + ", prontuario=" + prontuario + "]";
	}
}

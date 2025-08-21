package br.laramara.ti.sislaraserver.dominio.pendencia;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

@MappedSuperclass
public abstract class PendenciaReuniaoDeIntegracao extends Pendencia implements Comparavel{

	@Column(name = "prontuario")
	protected Long prontuario;
	
	@ManyToOne
	@JoinColumn(name = "id_modulo_periodo")
	protected ModuloPeriodo moduloPeriodo;
	
	public PendenciaReuniaoDeIntegracao() {
	}

	public PendenciaReuniaoDeIntegracao(Long prontuario, ModuloPeriodo moduloPeriodo,
			List<Profissional> profissionaisAfetados) {
		this.prontuario = prontuario;
		this.moduloPeriodo = moduloPeriodo;
		this.profissionaisAfetados = profissionaisAfetados;
	}

	public Long getProntuario() {
		return prontuario;
	}

	public ModuloPeriodo getModuloPeriodo() {
		return moduloPeriodo;
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
	public String toString() {
		return prontuario + ", moduloPeriodo=" + moduloPeriodo;
	}
}

package br.laramara.ti.sislaraserver.dominio.pendencia;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

@Entity
@DiscriminatorValue(value = TipoPendencia.DescricaoTipo.ATENDIMENTO_GRUPO)
public class PendenciaAtendimentoGrupo extends Pendencia implements Comparavel{

	@ManyToOne()
	@JoinColumn(name = "id_grupo")
	private Grupo grupo;

	@Temporal(TemporalType.DATE)
	@Column(name = "data")
	private Calendar data;
	
	@ManyToOne
	@JoinColumn(name = "id_modulo_periodo")
	private ModuloPeriodo moduloPeriodo;
	
	public PendenciaAtendimentoGrupo() {
	}

	public PendenciaAtendimentoGrupo(Calendar data, Horario horario, List<Profissional> profissionaisAfetados,
			Grupo grupo, ModuloPeriodo moduloPeriodo) {
		this.data = data;
		this.horario = horario;
		this.profissionaisAfetados = profissionaisAfetados;
		this.grupo = grupo;
		this.moduloPeriodo = moduloPeriodo;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public boolean possuiDataAnterior(Calendar data) {
		return this.data.before(data);
	}

	public String obterData() {
		return DataHoraUtils.formatarData(data);
	}

	public ModuloPeriodo getModuloPeriodo() {
		return moduloPeriodo;
	}

	@Override
	public boolean igualOuAnteriorADataAtual() {
		return igualOuAnteriorADataAtual(data);
	}

	@Override
	public Calendar getDataParaComparacao() {
		return data;
	}

	@Override
	public String obterDescricaoPendencia() {
		return "Atendimento do grupo no " + grupo.obterNomeGrupoETurma() + ", Data: " + DataHoraUtils.formatarData(data)
				+ ", Módulo: " + moduloPeriodo.getModulo().getNome();
	}

	@Override
	public String toString() {
		return "PendenciaAtendimentoGrupo [" + super.toString() + ", grupo=" + grupo + ", data=" + DataHoraUtils.formatarData(data)
				+ ", moduloPeriodo=" + moduloPeriodo + "]";
	}
}

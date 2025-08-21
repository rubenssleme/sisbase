package br.laramara.ti.sislaraserver.dominio.pendencia;

import java.util.Calendar;

public interface Comparavel {
	public boolean igualOuAnteriorADataAtual();
	public Calendar getDataParaComparacao();
}

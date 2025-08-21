package br.laramara.ti.sislaraserver.dominio.pendencia;

import br.laramara.ti.sislaraserver.dominio.EspecificacaoPesquisaBase;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.espera.TipoEspera;

public class EspecificacaoPesquisaPendencia extends EspecificacaoPesquisaBase
		implements ValidavelObrigatoriedadeETamanhoMaximo {

	private TipoEspera tipoEspera;

	public TipoEspera getTipoEspera() {
		return tipoEspera;
	}

	public void setTipoEspera(TipoEspera tipoEspera) {
		this.tipoEspera = tipoEspera;
	}

	@Override
	public String toString() {
		return "EspecificacaoPesquisaPendencia [tipoEspera=" + tipoEspera + "]";
	}
}

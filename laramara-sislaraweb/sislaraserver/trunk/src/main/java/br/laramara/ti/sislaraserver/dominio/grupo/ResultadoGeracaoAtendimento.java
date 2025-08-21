package br.laramara.ti.sislaraserver.dominio.grupo;

import br.laramara.ti.sislaraserver.dominio.Resultado;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;

public class ResultadoGeracaoAtendimento extends Resultado {

	private Grupo grupo;
	private ModuloPeriodo moduloPeriodo;
	private AtendimentoGrupo atendimentoGrupo;
	
	public void efetuadoComSucesso(Grupo grupo, ModuloPeriodo moduloPeriodo,
			AtendimentoGrupo atendimentoGrupo) {
		this.grupo = grupo;
		this.moduloPeriodo = moduloPeriodo;
		this.atendimentoGrupo = atendimentoGrupo;
		efetuadoComSucesso();
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public ModuloPeriodo getModuloPeriodo() {
		return moduloPeriodo;
	}

	public AtendimentoGrupo getAtendimentoGrupo() {
		return atendimentoGrupo;
	}
}

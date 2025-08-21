package br.laramara.ti.sislaraserver.dominio.grupo;

import br.laramara.ti.sislaraserver.dominio.Resultado;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;

public class ResultadoEdicaoAtendimentoGrupo extends Resultado{
	
	private AtendimentoGrupo atendimentoGrupo;
	
	public void efetuadoComSucesso(AtendimentoGrupo atendimentoGrupo) {
		this.atendimentoGrupo = atendimentoGrupo;
		efetuadoComSucesso();
	}
	
	public void efetuadoComSucesso(AtendimentoGrupo atendimentoGrupo,
			String mensagemAdicional) {
		efetuadoComSucesso(atendimentoGrupo);
		setMensagem(mensagemAdicional);
	}

	public AtendimentoGrupo getAtendimentoGrupo() {
		return atendimentoGrupo;
	}
}

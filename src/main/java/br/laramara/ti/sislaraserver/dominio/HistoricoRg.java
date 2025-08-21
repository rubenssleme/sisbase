package br.laramara.ti.sislaraserver.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.usuario.TamanhoMaximoUsuario;

@Entity
@Table(name = "historico_rg")
public class HistoricoRg extends Historico {

	@Column(name = "rg", length = TamanhoMaximoUsuario.RG)
	private String rg;

	public HistoricoRg() {
	}
	
	public HistoricoRg(String rg) {
		this.rg = TextoUtils.removerCaracteresInvalidosRG(rg);
	}

	public String getRg() {
		return rg;
	}
	
	public boolean estaVazio(){
		return rg.isEmpty();
	}

	public void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(rg, TamanhoMaximoUsuario.RG)) {
			adicionarErro("Insira um RG contendo até "
					+ TamanhoMaximoUsuario.RG + " caracteres.");
		}
	}
}

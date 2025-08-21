package br.laramara.sistelemarketingserver.dominio.seguranca;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Transient;

import br.laramara.sistelemarketingcommons.utilitarios.TextoUtils;

public abstract class Validavel {
	@Transient
	protected boolean validado;
	@Transient
	protected Set<String> erros;

	protected Validavel() {
		validado = true;
		erros = new LinkedHashSet<>();
	}

	public boolean validado() {
		return validado;
	}

	public String obterDescricaoErros() {
		String textoErros = "";
		for (String erro : erros) {
			textoErros += erro + "\n";
		}
		return textoErros;
	}

	protected void adicionarErro(String erro) {
		erros.add(erro);
		marcarComoInvalido();
	}

	private void marcarComoInvalido() {
		validado = false;
	}
	
	protected boolean estaComCampoNuloOuVazio(String texto) {
		return texto == null || texto != null && TextoUtils.estaVazio(texto);
	}
	
	protected boolean tamanhoMaximoViolado(String propriedade, int tamanhoMaximo) {
		return (propriedade != null && propriedade.length() > tamanhoMaximo);
	}
}

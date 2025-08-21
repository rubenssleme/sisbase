package br.laramara.ti.sismovimentacaoserver.dominio;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

import br.laramara.ti.sismovimentacaocommons.utilitarios.NumeroUtils;

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

	public List<String> obterErros() {
		return new ArrayList<>(erros);
	}

	public String obterDescricaoErros() {
		String textoErros = "";
		for (String erro : erros) {
			textoErros += erro + "\n";
		}
		return textoErros;
	}

	public int obterNumeroErros() {
		return erros.size();
	}

	protected void adicionarErro(String erro) {
		erros.add(erro);
		marcarComoInvalido();
	}

	protected void adicionarErro(List<String> erros) {
		this.erros.addAll(erros);
		marcarComoInvalido();
	}

	private void marcarComoInvalido() {
		validado = false;
	}

	protected boolean tamanhoMaximoViolado(String propriedade, int tamanhoMaximo) {
		return (propriedade != null && propriedade.length() > tamanhoMaximo);
	}
	
	protected boolean tamanhoMaximoViolado(Integer numero, int tamanhoMaximo) {
		return (numero != null && !NumeroUtils.numeroInteiroInvalido(numero) && numero
				.toString().length() > tamanhoMaximo);
	}
	
	protected boolean tamanhoMinimoEMaximoViolado(String propriedade,
			int tamanhoMimino, int tamanhoMaximo) {
		return ((tamanhoMinimoViolado(propriedade, tamanhoMimino) && !tamanhoMaximoViolado(
				propriedade, tamanhoMaximo)) || (!tamanhoMinimoViolado(
				propriedade, tamanhoMimino) && tamanhoMaximoViolado(
				propriedade, tamanhoMaximo)));
	}
	
	protected boolean tamanhoMinimoViolado(String propriedade, int tamanhoMinino) {
		return (propriedade != null && propriedade.length() < tamanhoMinino);
	}
}

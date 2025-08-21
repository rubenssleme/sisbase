package br.laramara.ti.sislaraserver.repositorios;

import java.util.HashMap;
import java.util.Map;

public class ComandoSQLEParametros {

	private boolean iniciado;
	private String comandoBase;
	private String comandoEspecifico;
	private String comandoOrdem;
	private Map<String, Object> parametros;

	public ComandoSQLEParametros() {
		this.iniciado = false;
		this.comandoEspecifico = "";
		this.comandoOrdem = "";
		this.parametros = new HashMap<>();
	}

	public String getComando() {
		return comandoBase + comandoEspecifico + comandoOrdem;
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public void setComandoBase(String comandoBase) {
		this.comandoBase = comandoBase;
	}

	public void adicionarComando(String comando) {
		this.comandoEspecifico += obterComando(iniciado, comando);
		this.iniciado = true;
	}
	
	public void setComandoOrdem(String comandoOrdem) {
		this.comandoOrdem = comandoOrdem;
	}

	public void adicionarParametro(String chave, Object valor) {
		parametros.put(chave, valor);
	}

	private String obterComando(boolean iniciado, String comando) {
		return iniciado ? " AND" + comando : comando;
	}
}

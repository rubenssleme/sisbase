package br.laramara.ti.sismovimentacaocommons.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EspecificacaoPesquisaDTO implements Serializable {

	private static final long serialVersionUID = 7053861186709516431L;

	private Map<ChavePesquisaDTO, Object> parametrosParaPesquisa;
	private List<ChavePesquisaDTO> listaChaves;

	public EspecificacaoPesquisaDTO() {
		parametrosParaPesquisa = new HashMap<>();
		listaChaves = new ArrayList<>();
		configurar();
	}

	public void adicionarParametro(ChavePesquisaDTO chave, Object parametro) {
		parametrosParaPesquisa.put(chave, parametro);
	}

	public boolean existeChave(ChavePesquisaDTO chave) {
		return parametrosParaPesquisa.containsKey(chave);
	}

	public Object obterParametro(ChavePesquisaDTO chave) {
		return parametrosParaPesquisa.get(chave);
	}

	public List<ChavePesquisaDTO> obterOpcoes() {
		return listaChaves;
	}

	protected void adicionar(ChavePesquisaDTO chavePesquisa) {
		listaChaves.add(chavePesquisa);
	}

	protected abstract void configurar();
}

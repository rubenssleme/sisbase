package br.laramara.ti.sismovimentacaocommons.utilitarios;

import java.util.HashMap;
import java.util.Map;

import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.TokenDTO;

public class Sessao {

	private static final String CHAVE_TOKEN = "Token";
	public static final String CHAVE_CONTA_ACESSO = "Conta Acesso";
	public static final String CHAVE_MOVIMENTACAO = "Movimentaca";
	public static final String CHAVE_PERFIL = "Perfil";
	
	private Map<String, ModeloDTO> dtosArmazenados;
	private static final Sessao instancia = new Sessao();

	private Sessao() {
		dtosArmazenados = new HashMap<>();
	}

	public static Sessao obterInstancia() {
		return instancia;
	}

	public boolean possuiDto(String chave) {
		return dtosArmazenados.containsKey(chave);
	}

	public TokenDTO obterToken(){
		return (TokenDTO) dtosArmazenados.get(CHAVE_TOKEN);
	}
	
	public void armazenarTokenDTO(ModeloDTO objetoDto) {
		dtosArmazenados.put(CHAVE_TOKEN, objetoDto);
	}
	
	public void armazenarDTO(String chave, ModeloDTO objetoDto) {
		dtosArmazenados.put(chave, objetoDto);
	}

	public final Object obterDto(String chave) {
		Object retorno = dtosArmazenados.get(chave);
		removerDto(chave);
		return retorno;
	}

	public void removerDto(String chave) {
		dtosArmazenados.remove(chave);
	}
}

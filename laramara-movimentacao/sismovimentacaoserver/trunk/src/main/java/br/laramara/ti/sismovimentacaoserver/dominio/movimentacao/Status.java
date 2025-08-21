package br.laramara.ti.sismovimentacaoserver.dominio.movimentacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Status {
	
	ENTRADA_DO_ARQUIVO(Classificacao.BRANCO), /* BRANCO Data de Entrada PADRAO */
	ARTE_FINALIZADA(Classificacao.BRANCO), 
	ARTE_EM_APROVACAO(Classificacao.AZUL), /* AZUL Data de envio para o cliente */
	ARTE_APROVADA(Classificacao.LILAS), /* LILAS Data da aprovação */
	REQUISICAO_DO_CTP(Classificacao.VERDE), 
	LIBERADO_PARA_CTP(Classificacao.VERDE), /* VERDE Saida de filme(muda de FILME para CTP) */
	
	ARQUIVO_COM_PROBLEMA(Classificacao.VERMELHO), /* pode voltar para ENTREDA_DO_ARQUIVO*/
	INATIVO(Classificacao.CINZA), 
	NOVA_ARTE_APROVADA_COM_MESMO_GL_GR(Classificacao.LARANJA), /*Nao vai ser USADO. Somente historico.*/
	APROVADO_AGUARDANDO_ESPECIFICACAO(Classificacao.AMARELO); /*Avançar para ARTE_APROVADA*/
	
	private final Classificacao classificacao;
	
	private static Map<Status, Status> proximos = new HashMap<Status, Status>();
	static {
		proximos.put(ENTRADA_DO_ARQUIVO, ARTE_FINALIZADA);
		proximos.put(ARTE_FINALIZADA, ARTE_EM_APROVACAO);
		proximos.put(ARTE_EM_APROVACAO, ARTE_APROVADA);
		proximos.put(APROVADO_AGUARDANDO_ESPECIFICACAO, ARTE_APROVADA);
		proximos.put(ARTE_APROVADA, REQUISICAO_DO_CTP);
		proximos.put(REQUISICAO_DO_CTP, LIBERADO_PARA_CTP);
	}
	
	private static Map<Status, Status> anterior = new HashMap<Status, Status>();
	static {
		anterior.put(APROVADO_AGUARDANDO_ESPECIFICACAO, ARTE_EM_APROVACAO);
	}
		
	Status(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public Classificacao getClassificacao() {
		return this.classificacao;
	}
	
	public Status obterProximo() {
		return proximos.get(this);
	}
	
	public Status obterAnterior() {
		return anterior.get(this);
	}
	
	public boolean possuiAnterior() {
		return anterior.containsKey(this);
	}
	
	public static List<Status> obterStatusDisponiveis() {
		List<Status> resultado = new ArrayList<>();
		resultado.add(ARQUIVO_COM_PROBLEMA);
		resultado.add(INATIVO);
		resultado.add(APROVADO_AGUARDANDO_ESPECIFICACAO);
		return resultado;
	}
	
	public static List<Status> obterStatusFinais() {
		List<Status> resultado = new ArrayList<>();
		resultado.add(LIBERADO_PARA_CTP);
		resultado.add(ARQUIVO_COM_PROBLEMA);
		resultado.add(INATIVO);
		return resultado;
	}
}

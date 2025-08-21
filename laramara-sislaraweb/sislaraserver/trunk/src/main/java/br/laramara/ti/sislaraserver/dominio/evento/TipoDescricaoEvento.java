package br.laramara.ti.sislaraserver.dominio.evento;

import java.util.Arrays;
import java.util.Comparator;

public enum TipoDescricaoEvento {
	DESCRICAO_EMENTA(1, "Descrição / Ementa"), 
	PUBLICO_ALVO(2, "Público Alvo"), 
	OBJETIVOS(3, "Objetivos"), 
	CONTEUDO_PROGRAMACAO(4, "Conteúdo / Programação"), 
	PRE_REQUISITOS(5, "Pré-Requisitos"), 
	LOCAL_E_DATA_DO_CURSO(6, "Local e Data do Curso"),
	INVESTIMENTO(7, "Investimento"), 
	REALIZACAO(8, "Realização"), 
	INFORMACOES_ADICIONAIS(9, "Informações Adicionais"), 
	COORDENACAO(10, "Coordenação"), 
	TERMO_DE_USO_DE_IMAGEM(11, "Termo de Uso de Imagem");

	private int sequencia;
	private String nome;

	private TipoDescricaoEvento(int sequencia, String nome) {
		this.sequencia = sequencia;
		this.nome = nome;
	}

	public static TipoDescricaoEvento obterPorNome(String tipoDescricaoEvento) {
		return Arrays.asList(values()).stream()
				.filter(tipoDescricaoEventoObtido -> tipoDescricaoEventoObtido.toString().equals(tipoDescricaoEvento))
				.findFirst().orElse(null);
	}

	public int getSequencia() {
		return sequencia;
	}
	
	public static final Comparator<TipoDescricaoEvento> obterComparador() {
		return new Comparator<TipoDescricaoEvento>() {

			@Override
			public int compare(TipoDescricaoEvento o1, TipoDescricaoEvento o2) {
				return o1.getSequencia() - o2.getSequencia();
			}
		};
	}

	@Override
	public String toString() {
		return nome;
	}
}

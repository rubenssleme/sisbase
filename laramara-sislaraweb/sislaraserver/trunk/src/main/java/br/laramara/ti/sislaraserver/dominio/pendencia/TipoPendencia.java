package br.laramara.ti.sislaraserver.dominio.pendencia;

public enum TipoPendencia {
	ENVIO_LISTA_ESPERA_EXCESSO_DE_FALTAS(TipoPendencia.DescricaoTipo.ENVIO_LISTA_ESPERA_EXCESSO_DE_FALTAS), 
	ATENDIMENTO_INDIVIDUAL_USUARIO(TipoPendencia.DescricaoTipo.ATENDIMENTO_INDIVIDUAL_USUARIO), 
	ATENDIMENTO_GRUPO(TipoPendencia.DescricaoTipo.ATENDIMENTO_GRUPO), 
	ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO(TipoPendencia.DescricaoTipo.ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO),
	TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO(TipoPendencia.DescricaoTipo.TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO),
	INCLUSAO_DE_REUNIAO_DE_INTEGRACAO(TipoPendencia.DescricaoTipo.INCLUSAO_DE_REUNIAO_DE_INTEGRACAO);

	private TipoPendencia(String tipo) {
		if (!this.name().equals(tipo))
			throw new IllegalArgumentException(
					"Não é possível utilizar tipo de pendencia diferente da descrição do tipo.");
		this.tipo = tipo;
	}

	@SuppressWarnings("unused")
	private String tipo;

	public static class DescricaoTipo {
		public static final String ENVIO_LISTA_ESPERA_EXCESSO_DE_FALTAS = "ENVIO_LISTA_ESPERA_EXCESSO_DE_FALTAS";
		public static final String ATENDIMENTO_INDIVIDUAL_USUARIO = "ATENDIMENTO_INDIVIDUAL_USUARIO";
		public static final String ATENDIMENTO_GRUPO = "ATENDIMENTO_GRUPO";
		public static final String ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO = "ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO";
		public static final String TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO = "TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO";
		public static final String INCLUSAO_DE_REUNIAO_DE_INTEGRACAO = "INCLUSAO_DE_REUNIAO_DE_INTEGRACAO";
	}
}

package br.laramara.ti.sislaraserver.dominio.contribuicao;

import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;

public abstract class Cobranca {

	protected static final int TAMANHO_NOME_EMPRESA = 30;
	protected static final int TAMANHO_CODIGO_BANCO = 3;
	protected static final int TAMANHO_NOME_BANCO = 15;
	protected static final int TAMANHO_NUMERO_AGENCIA = 4;
	protected static final int TAMANHO_CONTA_CORRENTE = 5;
	protected static final int TAMANHO_DAC_CONTA_CORRENTE = 1;
	protected static final int TAMANHO_NOSSO_NUMERO = 8;
	protected static final int TAMANHO_SEU_NUMERO = 10;
	protected static final int TAMANHO_VENCIMENTO_TITULO = 6;
	protected static final int TAMANHO_DATA_EMISSAO = 6;
	protected static final int TAMANHO_DATA_GERACAO = 6;
	protected static final int TAMANHO_NUMERO_INSCRICAO = 15;
	protected static final int TAMANHO_NOME_SACADO = 30;
	protected static final int TAMANHO_COMPLEMENTO_REGISTRO = 9;
	protected static final int TAMANHO_LOGRADOURO = 40;
	protected static final int TAMANHO_BAIRRO= 12;
	protected static final int TAMANHO_CEP= 8;
	protected static final int TAMANHO_CIDADE = 15;
	protected static final int TAMANHO_ESTADO = 2;
	protected static final int TAMANHO_ESPACO_DIREITO_INICIO = 144;
	protected static final int TAMANHO_ESPACO_DIREITO_FIM = 31;
	protected static final int TAMANHO_CODIGO_INSCRICAO = 17;
	protected static final int TAMANHO_CODIGO_MOEDA = 1;
	protected static final int TAMANHO_LITERAL_MOEDA = 4;
	protected static final int TAMANHO_VALOR_TITULO = 13;
	protected static final int TAMANHO_FIM_LINHA = 6;
	protected static final int TAMANHO_INSTRUCOES = 69;
	protected static final int TAMANHO_INSTRUCOES_FIM = 116;
	protected static final int TAMANHO_ESPACO_FIM_DO_ARQUIVO = 393;
	protected static final String DOIS_ZEROS = "00";
	protected static final String FINAL_ARQUIVO = "63";
	protected static final String BRANCOS = " ";
	protected int LINHA = 2;

	private String texto = "";

	protected void adicionarQuebra(){
		adicionar("\n");
		LINHA++;
	}
	
	protected String adicionarTextoComZeroAEsquerda(String texto, int tamanhoMaximo) {
		return adicionarTexto(texto, tamanhoMaximo, "0", Preenchimento.ESQUERDA);
	}
	
	protected String adicionarTextoComZeroAEsquerda(int texto, int tamanhoMaximo) {
		return adicionarTexto(String.valueOf(texto), tamanhoMaximo, "0", Preenchimento.ESQUERDA);
	}

	protected String adicionarTextoComEspacoADireita(String texto, int tamanhoMaximo) {
		return adicionarTexto(texto, tamanhoMaximo, " ", Preenchimento.DIREITA);
	}
	
	protected String adicionarTextoComEspacoAEsquerda(String texto, int tamanhoMaximo) {
		return adicionarTexto(texto, tamanhoMaximo, " ", Preenchimento.ESQUERDA);
	}

	protected String adicionarTexto(String texto, int tamanhoMaximo, String textoDePreenchimento,
			Preenchimento direcaoPreenchimento) {
		if (texto.length() > tamanhoMaximo) {
			return upperCaseSemAcento(texto.substring(0, tamanhoMaximo));
		} else {
			String preenchimento = "";
			for (int i = 0; i < tamanhoMaximo - texto.length(); i++) {
				preenchimento += textoDePreenchimento;
			}
			if (direcaoPreenchimento.equals(Preenchimento.DIREITA)) {
				return upperCaseSemAcento(texto.concat(preenchimento));
			} else if (direcaoPreenchimento.equals(Preenchimento.ESQUERDA)) {
				return upperCaseSemAcento(preenchimento.concat(texto));
			}else{
				return null;
			}
		}
	}
	
	private String upperCaseSemAcento(String texto){
		return TextoUtils.removerAcentos(texto.toUpperCase());
	}

	protected void adicionar(String texto) {
		this.texto += upperCaseSemAcento(texto);
	}

	public String obterTexto() {
		return texto;
	}
}

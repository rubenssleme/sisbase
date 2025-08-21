package br.laramara.ti.sislaraserver.dominio.contribuicao;

public class ArquivoCobrancaCabecalho extends Cobranca {

	private static final String TIPO_DE_REGISTRO = "01REMESSA01COBRANCA       ";

	public ArquivoCobrancaCabecalho(String numeroAgencia, String numeroContaCorrente, String dacContaCorrente,
			String nomeDaEmpresa, String codigoBanco, String nomeBanco, String dataGeracao) {
		adicionar(TIPO_DE_REGISTRO);
		adicionar(adicionarTextoComEspacoADireita(numeroAgencia, TAMANHO_NUMERO_AGENCIA));
		adicionar(DOIS_ZEROS);
		adicionar(adicionarTextoComEspacoADireita(numeroContaCorrente, TAMANHO_CONTA_CORRENTE));
		adicionar(adicionarTextoComEspacoADireita(dacContaCorrente, TAMANHO_DAC_CONTA_CORRENTE));
		adicionar(adicionarTextoComEspacoADireita(BRANCOS, 8));
		adicionar(adicionarTextoComEspacoADireita(nomeDaEmpresa, TAMANHO_NOME_EMPRESA));
		adicionar(adicionarTextoComEspacoADireita(codigoBanco, TAMANHO_CODIGO_BANCO));
		adicionar(adicionarTextoComEspacoADireita(nomeBanco, TAMANHO_NOME_BANCO));
		adicionar(adicionarTextoComEspacoADireita(dataGeracao, TAMANHO_DATA_GERACAO));
		adicionar(adicionarTextoComEspacoAEsquerda(BRANCOS, 294));
		adicionar(adicionarTextoComZeroAEsquerda("1", TAMANHO_FIM_LINHA));
		adicionarQuebra();
	}
}

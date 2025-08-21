package br.laramara.ti.sislaraserver.dominio.contribuicao;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;

public class ArquivoCobrancaCorpo extends Cobranca {
	
	private static final String PRIMEIRA_LINHA_INICIO = "61";
	//Texto tem estar em conformidade com o layout CNAB400 disponível no arquivo layout_cobranca_400bytes_cnab_itau_mensagem.pdf 
	private static final String SEGUNDA_LINHA_DADOS_INSTITUICAO = "62LARAMARA ASSOC.BRAS.DE ASSIST.AO DEF.VISUAL                          CNPJ.67.640.441/0001-29                                              PABX 3660-6400                 SITE: WWW.LARAMARA.ORG.BR             TEL: 3660-6447                                                                                                                                                                           ";
	private static final String TERCEIRA_LINHA_INICIO = "63";
	private static final String ESPECIE_TITULO = "99";
	private static final String ACEITE = "A";
	private static final String FIM_ARQUIVO = "9";
	
	public ArquivoCobrancaCorpo(List<Contribuinte> contribuintes, RepositorioSislara repositorioSislara, String numeroAgencia,
			String numeroContaCorrente, String dacContaCorrente, String numeroCarteira, String codigoMoeda,
			String literalMoeda, String dataEmissao) {
		for (Contribuinte contribuinte : contribuintes) {
			Long nossoNumero = repositorioSislara.obterProximoNossoNumero();
			Endereco endereco = contribuinte.getEndereco();
			adicionar(PRIMEIRA_LINHA_INICIO);
			adicionar(adicionarTextoComEspacoADireita(numeroAgencia, TAMANHO_NUMERO_AGENCIA));
			adicionar(DOIS_ZEROS);
			adicionar(adicionarTextoComEspacoADireita(numeroContaCorrente, TAMANHO_CONTA_CORRENTE));
			adicionar(adicionarTextoComEspacoADireita(dacContaCorrente, TAMANHO_DAC_CONTA_CORRENTE));
			adicionar(adicionarTextoComEspacoADireita(numeroCarteira, 3));
			String nossoNumeroAposPrenchimento = adicionarTextoComZeroAEsquerda(nossoNumero.toString(),
					TAMANHO_NOSSO_NUMERO);
			adicionar(nossoNumeroAposPrenchimento);
			adicionar(carcularDacNossoNumero(numeroAgencia, numeroContaCorrente, numeroCarteira,
					nossoNumeroAposPrenchimento));
			adicionar(adicionarTextoComEspacoADireita(codigoMoeda, TAMANHO_CODIGO_MOEDA));
			adicionar(adicionarTextoComEspacoADireita(literalMoeda, TAMANHO_LITERAL_MOEDA));
			adicionar(adicionarTextoComZeroAEsquerda(removerVirguraEPonto(contribuinte.getValorContribuicao()), TAMANHO_VALOR_TITULO));
			adicionar(adicionarTextoComEspacoADireita(nossoNumero.toString(), TAMANHO_SEU_NUMERO));
			adicionar(adicionarTextoComEspacoADireita(BRANCOS, TAMANHO_VENCIMENTO_TITULO));
			adicionar(ESPECIE_TITULO);
			adicionar(ACEITE);
			adicionar(adicionarTextoComEspacoADireita(removerSeparador(dataEmissao), TAMANHO_DATA_EMISSAO));
			adicionar(DOIS_ZEROS);
			adicionar(adicionarTextoComZeroAEsquerda(DOIS_ZEROS, TAMANHO_NUMERO_INSCRICAO));
			adicionar(adicionarTextoComEspacoADireita(contribuinte.getNomeEmpresa(), TAMANHO_NOME_SACADO));
			adicionar(adicionarTextoComEspacoADireita(BRANCOS, TAMANHO_COMPLEMENTO_REGISTRO));
			adicionar(adicionarTextoComEspacoADireita(endereco.obterEnderecoComNumeroEComplemento(), TAMANHO_LOGRADOURO));
			adicionar(adicionarTextoComEspacoADireita(BRANCOS, TAMANHO_BAIRRO));
			adicionar(adicionarTextoComEspacoADireita(contribuinte.getEndereco().getCep(), TAMANHO_CEP));
			adicionar(adicionarTextoComEspacoADireita(endereco.getMunicipio().getNome(), TAMANHO_CIDADE));
			adicionar(adicionarTextoComEspacoADireita(endereco.getUf().toString(), TAMANHO_ESTADO));
			adicionar(adicionarTextoComEspacoADireita(BRANCOS, TAMANHO_ESPACO_DIREITO_INICIO));
			adicionar(adicionarTextoComEspacoADireita("01000000000000000", TAMANHO_CODIGO_INSCRICAO));
			adicionar(adicionarTextoComEspacoADireita(BRANCOS, TAMANHO_ESPACO_DIREITO_FIM));
			adicionar(adicionarTextoComZeroAEsquerda(LINHA, TAMANHO_FIM_LINHA));
			adicionarQuebra();
			adicionar(SEGUNDA_LINHA_DADOS_INSTITUICAO);
			adicionar(adicionarTextoComZeroAEsquerda(LINHA, TAMANHO_FIM_LINHA));
			adicionarQuebra();
			adicionar(TERCEIRA_LINHA_INICIO);
			adicionar(adicionarTextoComEspacoADireita(BRANCOS, TAMANHO_INSTRUCOES));
			adicionar(adicionarTextoComEspacoADireita("LARAMARA - AJUDANDO A TRANSFORMAR VIDAS", TAMANHO_INSTRUCOES));
			adicionar(adicionarTextoComEspacoADireita("", TAMANHO_INSTRUCOES));
			adicionar(adicionarTextoComEspacoADireita("FINALIDADE: DONATIVO - PAGAVEL EM QUALQUER BANCO", TAMANHO_INSTRUCOES));
			adicionar(adicionarTextoComEspacoADireita(BRANCOS, TAMANHO_INSTRUCOES_FIM));
			adicionar(adicionarTextoComZeroAEsquerda(LINHA, TAMANHO_FIM_LINHA));
			adicionarQuebra();
		}
		adicionar(FIM_ARQUIVO);
		adicionar(adicionarTextoComEspacoADireita(BRANCOS, TAMANHO_ESPACO_FIM_DO_ARQUIVO));
		adicionar(adicionarTextoComZeroAEsquerda(LINHA, TAMANHO_FIM_LINHA));
		adicionarQuebra();
	}
	
	private String removerSeparador(String dataEmissao) {
		return dataEmissao.replace("/", "");
	}

	private String removerVirguraEPonto(String valor) {
		return valor.replace(",", "").replace(".", "");
	}

	private String carcularDacNossoNumero(String numeroAgencia, String numeroContaCorrente, String numeroCarteira,
			String nossoNumero) {
		int multiplicador = 0;
		int soma = 0;
		int restoDivisao = 0;
		char[] dadosSeparadorPorCaracter = (numeroAgencia + numeroContaCorrente + numeroCarteira + nossoNumero)
				.toCharArray();
		for (int i = dadosSeparadorPorCaracter.length - 1; i >= 0; i--) {
			if (multiplicador == 0 || multiplicador == 1) {
				multiplicador = 2;
			} else {
				multiplicador = 1;
			}
			int multiplicacao = Integer.parseInt(String.valueOf(dadosSeparadorPorCaracter[i])) * multiplicador;
			char[] valorMultiplicacao = String.valueOf(multiplicacao).toCharArray();
			soma += somar(valorMultiplicacao);
		}
		restoDivisao = (soma % 10);
		return String.valueOf(restoDivisao == 0 ? 0 : 10 - restoDivisao);
	}

	private int somar(char[] caracteres) {
		int soma = 0;
		for (char caractere : caracteres) {
			soma += Integer.parseInt(String.valueOf(caractere));
		}
		return soma;
	}
}

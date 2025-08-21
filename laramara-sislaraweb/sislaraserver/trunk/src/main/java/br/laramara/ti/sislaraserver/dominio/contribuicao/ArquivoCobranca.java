package br.laramara.ti.sislaraserver.dominio.contribuicao;

import java.util.List;

import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;

public class ArquivoCobranca extends Cobranca {

	private ArquivoCobrancaCabecalho arquivoCobrancaCabecalho;
	private ArquivoCobrancaCorpo arquivoCobrancaCorpo;

	public ArquivoCobranca(String numeroAgencia, String numeroContaCorrente, String dacContaCorrente,
			String nomeDaEmpresa, String codigoBanco, String nomeBanco, String dataGeracao, RepositorioSislara repositorioSislara,
			List<Contribuinte> contribuintes, String numeroCarteira, String codigoMoeda, String literalMoeda, String dataEmissao) {
		arquivoCobrancaCabecalho = new ArquivoCobrancaCabecalho(numeroAgencia, numeroContaCorrente, dacContaCorrente,
				nomeDaEmpresa, codigoBanco, nomeBanco, dataGeracao);
		arquivoCobrancaCorpo = new ArquivoCobrancaCorpo(contribuintes, repositorioSislara, numeroAgencia, numeroContaCorrente,
				dacContaCorrente, numeroCarteira, codigoMoeda, literalMoeda, dataEmissao);
	}
	
	public String obterTexto() {
		return arquivoCobrancaCabecalho.obterTexto() + arquivoCobrancaCorpo.obterTexto();
	}
}

package br.laramara.ti.sismovimentacaoserver.utilitarios;


public class EntidadeUtils {
	public static final Long incrementar(Long versao) {
		if (versao != null) {
			versao++;
			return versao;
		} else {
			return new Long(1);
		}
	}
}

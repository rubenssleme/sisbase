package br.laramara.ti.sismovimentacaoserver.dominio.seguranca;

import java.util.Comparator;

public enum Permissao {
	CONTA_ACESSO_EDICAO, CONTA_ACESSO_TELA_EDICAO_VISUALIZAR,
	MOVIMENTACAO_EDICAO, MOVIMENTACAO_TELA_EDICAO_VISUALIZAR,
	PERFIL_EDICAO, PERFIL_TELA_EDICAO_VISUALIZAR,
	DESBLOQUEIO_TELA,
	FINALIZAR_SERVICO;
	
	public static final Comparator<Permissao> obterComparador() {
		return new Comparator<Permissao>() {
			public int compare(Permissao o1, Permissao o2) {
				return o1.toString().compareTo(o2.toString());
			}
		};
	}
}

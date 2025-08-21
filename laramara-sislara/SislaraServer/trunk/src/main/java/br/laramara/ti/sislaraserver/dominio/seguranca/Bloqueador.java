package br.laramara.ti.sislaraserver.dominio.seguranca;

public interface Bloqueador<CHAVE, VALOR> {
	public VALOR obterContaAcessoEditando(CHAVE prontuario);

	public boolean estaBloqueadoParaEdicao(CHAVE chave);
}

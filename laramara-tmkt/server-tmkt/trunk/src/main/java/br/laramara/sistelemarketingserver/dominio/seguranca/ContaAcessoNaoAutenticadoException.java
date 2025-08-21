package br.laramara.sistelemarketingserver.dominio.seguranca;

public class ContaAcessoNaoAutenticadoException extends Exception {
	private static final long serialVersionUID = 4657708219957218937L;

	public ContaAcessoNaoAutenticadoException() {
		super("Usuário ou senha inválido. Tente novamente.");
	}
}

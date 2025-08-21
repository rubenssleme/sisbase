package br.laramara.ti.sislaraserver.dominio.seguranca;

public class UsuarioExternoNaoAutenticadoException extends Exception {
	private static final long serialVersionUID = 4657708219957218937L;

	public UsuarioExternoNaoAutenticadoException() {
		super("Usuário ou senha inválido. Tente novamente.");
	}
}

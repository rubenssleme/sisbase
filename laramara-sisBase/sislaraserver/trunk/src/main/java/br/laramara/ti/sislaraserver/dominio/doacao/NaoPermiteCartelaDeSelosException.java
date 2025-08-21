package br.laramara.ti.sislaraserver.dominio.doacao;

public class NaoPermiteCartelaDeSelosException extends Exception {
	
	private static final long serialVersionUID = 4657708219957218937L;

	public NaoPermiteCartelaDeSelosException(String mensagemErro) {
		super(mensagemErro);
	}
}

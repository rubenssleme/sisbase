package br.laramara.ti.sislaraserver.repositorios;

public class RgDuplicadoException extends Exception {
	
	private static final long serialVersionUID = 5178973940377059394L;

	public RgDuplicadoException() {
		super("O RG do Usuário já está Pré Cadastrado.");
	}
}

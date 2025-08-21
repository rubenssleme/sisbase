package br.laramara.ti.sislaraserver.dominio.doacao;

public class CartelaDeSelosDuplicada extends Exception {
	
	private static final long serialVersionUID = 4657708219957218937L;

	public CartelaDeSelosDuplicada(String texto) {
		super(texto);
	}
}

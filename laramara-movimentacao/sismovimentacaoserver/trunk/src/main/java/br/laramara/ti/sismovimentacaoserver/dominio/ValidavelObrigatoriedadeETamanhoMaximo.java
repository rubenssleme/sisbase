package br.laramara.ti.sismovimentacaoserver.dominio;

public interface ValidavelObrigatoriedadeETamanhoMaximo {
	public void validarObrigatoriedadeETamanhoMaximoDeDados();

	public boolean validado();

	public String obterDescricaoErros();
}

package br.laramara.ti.sislaraserver.dominio.espera;

public class Resultado {
	
	private boolean sucesso;
	private String mensagem;
	
	public Resultado(){
		mensagem = "";
	}
	
	public boolean sucesso() {
		return sucesso;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		sucesso = true;
		this.mensagem += mensagem;
	}
}

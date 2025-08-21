package br.laramara.ti.sislaraserver.dominio;

public class Resultado {
	
	protected boolean sucesso;
	protected String mensagem;
	
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
	
	protected void efetuadoComSucesso(){
		this.sucesso = true;
		this.mensagem = "Dados armazenados com sucesso. ";
	}
	
	public void adicionarErro(String mensagem) {
		this.sucesso = false;
		this.mensagem += mensagem + "\n";		
	}
}

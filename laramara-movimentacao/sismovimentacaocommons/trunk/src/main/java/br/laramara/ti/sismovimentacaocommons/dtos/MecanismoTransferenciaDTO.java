package br.laramara.ti.sismovimentacaocommons.dtos;


public abstract class MecanismoTransferenciaDTO extends ModeloDTO{

	private static final long serialVersionUID = 9031496520914590513L;

	protected static String DADOS_ARMAZENADOS_COM_SUCESSO = "Dados armazenados com sucesso. ";
	
	protected boolean sucesso;
	protected String mensagem;
	
	protected MecanismoTransferenciaDTO() {
		sucesso = false;
		mensagem = "";
	}
	
	public boolean sucesso() {
		return sucesso;
	}

	public void adicionarErro(String mensagem) {
		sucesso = false;
		this.mensagem += mensagem + "\n";
	}
	
	public void adicionarMensagem(String mensagem) {
		this.mensagem += mensagem + "\n";
	}

	public String obterMensagens() {
		return mensagem;
	}
	
	protected void efetuadoComSucesso() {
		sucesso = true;
	}
}

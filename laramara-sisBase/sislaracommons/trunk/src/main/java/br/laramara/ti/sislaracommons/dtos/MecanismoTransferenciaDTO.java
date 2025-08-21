package br.laramara.ti.sislaracommons.dtos;

public abstract class MecanismoTransferenciaDTO extends ModeloDTO {

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

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	protected void efetuadoComSucesso() {
		sucesso = true;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getMensagem() {
		return mensagem;
	}

}

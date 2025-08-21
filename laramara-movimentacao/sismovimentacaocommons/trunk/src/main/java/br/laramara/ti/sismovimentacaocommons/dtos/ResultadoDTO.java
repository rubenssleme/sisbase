package br.laramara.ti.sismovimentacaocommons.dtos;

public abstract class ResultadoDTO extends MecanismoTransferenciaDTO {

	private static final long serialVersionUID = -210506780726258248L;

	protected ModeloDTO objetoDtoResultado;

	public void efetuadoComSucesso(ModeloDTO objetoDtoEditado){
		efetuadoComSucesso(DADOS_ARMAZENADOS_COM_SUCESSO, objetoDtoEditado);
	}
	
	protected void efetuadoComSucesso(String mensagem, ModeloDTO objetoDtoEditado) {
		adicionarMensagem(mensagem);
		this.objetoDtoResultado = objetoDtoEditado;
		super.efetuadoComSucesso();
	}

	public void efetuadoComSucesso(String mensagem) {
		adicionarMensagem(mensagem);
		super.efetuadoComSucesso();
	}
	
	public ModeloDTO obterObjetoDtoEditado() {
		return objetoDtoResultado;
	}
}

package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.List;

public abstract class ResultadoDTO<T> extends MecanismoTransferenciaDTO {

	private static final long serialVersionUID = -210506780726258248L;

	protected List<T> objetos;
	
	public void efetuadoComSucesso(List<T> objetosDto) {
		armazenarObjetos(objetosDto);
		efetuadoComSucesso(DADOS_ARMAZENADOS_COM_SUCESSO);
	}
	
	protected void efetuadoComErro(List<T> objetosDto, String mensagem) {
		armazenarObjetos(objetosDto);
		adicionarErro(mensagem);
	}
	
	public void armazenarObjetos(List<T> objetosDto) {
		setObjetos(objetosDto);
	}
	
	private void setObjetos(List<T> objetosDto) {
		this.objetos = objetosDto;
	}
	
	protected T obterUnico() {
		return objetos.get(0);
	} 
	
	public void efetuadoComSucesso(String mensagem) {
		adicionarMensagem(mensagem);
		efetuadoComSucesso();
	}
}

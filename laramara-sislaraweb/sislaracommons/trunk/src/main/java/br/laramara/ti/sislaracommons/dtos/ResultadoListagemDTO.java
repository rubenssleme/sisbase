package br.laramara.ti.sislaracommons.dtos;

import java.util.ArrayList;
import java.util.List;

public abstract class ResultadoListagemDTO<T> extends MecanismoTransferenciaDTO{

	private static final long serialVersionUID = -4817330474449869756L;
	
	private List<T> objetosDto;
	
	public ResultadoListagemDTO(){
		super();
		this.objetosDto = new ArrayList<>();
	}

	public List<T> getObjetosDto() {
		return objetosDto;
	}

	public void efetuadoComSucesso(List<T> objetos) {
		this.objetosDto = objetos;
		super.efetuadoComSucesso();
	}
	
	protected void efetuadoComSucesso(String mensagem, List<T> objetoDtoEditado) {
		adicionarMensagem(mensagem);
		this.objetosDto = (List<T>) objetoDtoEditado;
		super.efetuadoComSucesso();
	}
	
	public void efetuadoComSucesso(T objeto){
		this.objetosDto.add(objeto);
		super.efetuadoComSucesso();
	}
	
	public void nenhumRegistroEncontrado(){
		adicionarErro("Nenhum registro encontrado.");
	}
}

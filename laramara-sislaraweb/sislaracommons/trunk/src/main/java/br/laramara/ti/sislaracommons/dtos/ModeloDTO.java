package br.laramara.ti.sislaracommons.dtos;

import java.io.Serializable;

public class ModeloDTO implements Serializable {

	private static final long serialVersionUID = -7924112239633468110L;
	
	private String versao;
	
	public ModeloDTO(){
		versao = "";
	}
	
	public String getVersao(){
		return versao;
	}
	
	public void setVersao(String versao) {
		if (versao != null) {
			this.versao = versao;
		}
	}
}

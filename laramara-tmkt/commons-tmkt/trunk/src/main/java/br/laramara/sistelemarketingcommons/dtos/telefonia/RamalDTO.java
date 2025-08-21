package br.laramara.sistelemarketingcommons.dtos.telefonia;

import br.laramara.sistelemarketingcommons.dtos.ItemComboboxComDescricao;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;

public class RamalDTO extends ModeloDTO implements ItemComboboxComDescricao{

	private static final long serialVersionUID = -8684725254859806811L;
	
	private String numero;
	
	public RamalDTO() {
	}

	public RamalDTO(String numero) {
		super();
		setNumero(numero);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return numero;
	}
}

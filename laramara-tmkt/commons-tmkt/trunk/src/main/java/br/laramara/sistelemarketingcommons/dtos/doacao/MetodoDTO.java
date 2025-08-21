package br.laramara.sistelemarketingcommons.dtos.doacao;

import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.ItemComboboxComDescricao;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;

public class MetodoDTO extends ModeloDTO implements ItemComboboxComDescricao {

	private static final long serialVersionUID = -2437803202291845337L;

	private String metodo;
	
	private List<TipoRetiradaDTO> tiposRetiradasDto;

	public MetodoDTO() {
	}

	public MetodoDTO(String metodo, List<TipoRetiradaDTO> tiposRetiradasDto) {
		setMetodo(metodo);
		setTiposRetiradasDto(tiposRetiradasDto);
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
	public List<TipoRetiradaDTO> getTiposRetiradasDto() {
		return tiposRetiradasDto;
	}

	public void setTiposRetiradasDto(List<TipoRetiradaDTO> tiposRetiradasDto) {
		this.tiposRetiradasDto = tiposRetiradasDto;
	}

	@Override
	public String toString() {
		return getMetodo();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetodoDTO other = (MetodoDTO) obj;
		if (metodo == null) {
			if (other.metodo != null)
				return false;
		} else if (!metodo.equals(other.metodo))
			return false;
		return true;
	}
}

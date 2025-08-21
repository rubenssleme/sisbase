package br.laramara.sistelemarketingcommons.dtos.doacao;

import br.laramara.sistelemarketingcommons.dtos.ItemComboboxComDescricao;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;

public class TipoRetiradaDTO extends ModeloDTO implements ItemComboboxComDescricao {

	private static final long serialVersionUID = -2437803202291845337L;

	private String tipoRetirada;

	public TipoRetiradaDTO() {
	}

	public TipoRetiradaDTO(String tipoRetirada) {
		setTipoRetirada(tipoRetirada);
	}

	public String getTipoRetirada() {
		return tipoRetirada;
	}

	public void setTipoRetirada(String tipoRetirada) {
		this.tipoRetirada = tipoRetirada;
	}

	@Override
	public String toString() {
		return getTipoRetirada();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoRetiradaDTO other = (TipoRetiradaDTO) obj;
		if (tipoRetirada == null) {
			if (other.tipoRetirada != null)
				return false;
		} else if (!tipoRetirada.equals(other.tipoRetirada))
			return false;
		return true;
	}
}

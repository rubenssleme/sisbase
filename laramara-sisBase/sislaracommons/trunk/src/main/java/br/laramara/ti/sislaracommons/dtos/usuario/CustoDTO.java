package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class CustoDTO extends ModeloDTO {

	private static final long serialVersionUID = -9143396164202455424L;

	private Long id;
	private ItemCustoDTO itemCustoDto;
	private String valor;

	public CustoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemCustoDTO getItemCustoDto() {
		return itemCustoDto;
	}

	public void setItemCustoDto(ItemCustoDTO itemCustoDto) {
		this.itemCustoDto = itemCustoDto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustoDTO other = (CustoDTO) obj;
		if (itemCustoDto == null) {
			if (other.itemCustoDto != null)
				return false;
		} else if (!itemCustoDto.equals(other.itemCustoDto))
			return false;
		return true;
	}

	public String toString() {
		return itemCustoDto.toString() + " - R$ " + valor;
	}
}

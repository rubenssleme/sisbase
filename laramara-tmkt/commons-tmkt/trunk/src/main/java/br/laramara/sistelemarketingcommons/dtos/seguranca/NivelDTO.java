package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.ItemComboboxComIdEDescricao;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;

public class NivelDTO extends ModeloDTO implements ItemComboboxComIdEDescricao {

	private static final long serialVersionUID = -5685241751699574712L;

	private Long id;
	private String descricao;
	private List<PermissaoDTO> permissoesDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<PermissaoDTO> getPermissoesDto() {
		return permissoesDto;
	}

	public void setPermissoesDto(List<PermissaoDTO> permissoesDto) {
		this.permissoesDto = permissoesDto;
	}
	
	@Override
	public String toString() {
		return descricao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NivelDTO other = (NivelDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

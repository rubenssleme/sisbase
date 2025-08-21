package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;

public class PerfilDTO extends ModeloDTO {

	private static final long serialVersionUID = 4866943368625138590L;

	private Long id;

	private String descricao;

	private List<PermissaoDTO> permissoesDto;

	public PerfilDTO() {
		this.permissoesDto = new ArrayList<>();
	}

	public PerfilDTO(Long id, String descricao) {
		this();
		this.id = id;
		this.descricao = descricao;
	}

	public PerfilDTO(Long id, String descricao, List<PermissaoDTO> permissoesDto) {
		this(id, descricao);
		this.permissoesDto = permissoesDto;
	}

	public Long getId() {
		return id;
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
		PerfilDTO other = (PerfilDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

package br.laramara.sistelemarketingcommons.dtos.seguranca;

import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;

public class PermissaoDTO extends ModeloDTO {

	private static final long serialVersionUID = 2004430618894014352L;

	private Long id;

	private String descricao;
	
	public PermissaoDTO() {
		super();
	}

	public PermissaoDTO(Long id) {
		this.id = id;
	}

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermissaoDTO other = (PermissaoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

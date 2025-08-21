package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.ListaUtils;

public class ResponsavelTecnicoDTO extends ModeloDTO {

	private static final long serialVersionUID = -8921804689571163618L;

	private Long id;
	private ProfissionalDTO profissionalDto;
	private boolean principal;
	
	public ResponsavelTecnicoDTO() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProfissionalDto(ProfissionalDTO profissionalDto) {
		this.profissionalDto = profissionalDto;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public Long getId() {
		return id;
	}

	public ProfissionalDTO getProfissionalDto() {
		return profissionalDto;
	}

	public boolean isPrincipal() {
		return principal;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponsavelTecnicoDTO other = (ResponsavelTecnicoDTO) obj;
		if (profissionalDto == null) {
			if (other.profissionalDto != null)
				return false;
		} else if (!profissionalDto.equals(other.profissionalDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return profissionalDto.toString() + ", Principal: " + ListaUtils.obterTexto(principal); 
	}
}

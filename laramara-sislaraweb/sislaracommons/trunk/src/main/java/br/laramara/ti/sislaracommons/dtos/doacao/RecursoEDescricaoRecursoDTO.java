package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;

public class RecursoEDescricaoRecursoDTO extends ModeloDTO {

	private static final long serialVersionUID = 1L;

	private RecursoDTO recursoDTO;
	private DescricaoRecursoDTO descricaoRecursoDTO;

	public RecursoEDescricaoRecursoDTO(RecursoDTO recursoDTO, DescricaoRecursoDTO descricaoRecursoDTO) {
		super();
		this.recursoDTO = recursoDTO;
		this.descricaoRecursoDTO = descricaoRecursoDTO;
	}

	public RecursoDTO getRecursoDTO() {
		return recursoDTO;
	}

	public DescricaoRecursoDTO getDescricaoRecursoDTO() {
		return descricaoRecursoDTO;
	}

	@Override
	public String toString() {
		return recursoDTO + (descricaoRecursoDTO != null ? " - " + descricaoRecursoDTO : "");
	}
}

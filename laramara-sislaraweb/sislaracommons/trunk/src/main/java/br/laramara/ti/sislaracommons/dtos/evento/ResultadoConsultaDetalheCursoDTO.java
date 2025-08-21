package br.laramara.ti.sislaracommons.dtos.evento;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoConsultaDetalheCursoDTO extends ResultadoDTO {

	private static final long serialVersionUID = -2313016182459989176L;
	
	private DetalheCursoDTO detalheCursoDto;
	
	public void efetuadoComSucesso(ModeloDTO objetoDtoEditado) {
		efetuadoComSucesso("Pesquisa de Detalhe de Curso realizada com sucesso.");
		setDetalheCursoDto((DetalheCursoDTO) objetoDtoEditado);
	}
	
	public void setDetalheCursoDto(DetalheCursoDTO detalheCursoDto) {
		this.detalheCursoDto = detalheCursoDto;
	}
	
	public DetalheCursoDTO getDetalheCursoDto() {
		return detalheCursoDto;
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.evento.DetalheCursoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.fabricas.externa.FabricaDescricaoEvento;

public class FabricaDetalheCurso extends FabricaRecursiva<DetalheCursoDTO, Grupo>{

	@Override
	public Grupo converterParaDominio(DetalheCursoDTO objetoDto, Grupo grupo) {
		if (objetoDto != null) {
			grupo = obterNovo();
			grupo.setId(objetoDto.getIdGrupo());
		}
		
		return grupo;
	}

	@Override
	public Grupo obterNovo() {
		return new Grupo();
	}

	@Override
	public DetalheCursoDTO converterParaDTO(Grupo grupo) {
		DetalheCursoDTO detalheCursoDto = new DetalheCursoDTO();

		if (grupo != null) {
			if (grupo.getId() != null) {
				detalheCursoDto.setIdGrupo(grupo.getId());
			}

			detalheCursoDto.setCargaHoraria(grupo.getModulosPeriodos().get(0).getCargaHoraria());
			detalheCursoDto.setNumeroVagas(grupo.getModulosPeriodos().get(0).getVagas());
			detalheCursoDto.setDataCurso(grupo.getDataCurso());
			detalheCursoDto.setPeriodoPreInscricoes(grupo.getPeriodoPreInscricoes());
			detalheCursoDto
					.setDescricoesEventoDto(new FabricaDescricaoEvento().converterParaDTO(grupo.getDescricoesEvento()));
			detalheCursoDto.setInvestimento(grupo.getInvestimento());
			detalheCursoDto.setNomeCurso(grupo.getNomeCurso());
			detalheCursoDto.setValorTotalAlmoco(grupo.getValorTotalAlmoco());
		}

		return detalheCursoDto;
	}

	public Grupo converterParaDominio(DetalheCursoDTO objetoDto) {
		return converterParaDominio(objetoDto, obterNovo());
	}
}

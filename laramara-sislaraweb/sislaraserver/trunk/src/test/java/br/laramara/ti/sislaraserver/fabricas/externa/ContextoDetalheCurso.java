package br.laramara.ti.sislaraserver.fabricas.externa;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.evento.DescricaoEventoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.DetalheCursoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.TipoDescricaoEventoDTO;

public class ContextoDetalheCurso {

	public static DetalheCursoDTO fabricarDetalheCursoDTOComTodosOsDados() {
		Long idGrupo = new Long(16666L);
		String cargaHoraria = "255:00";
		String dataCurso = "29/12/2017 at� 31/12/2017";
		String investimento = "300,00";
		String nomeCurso = "Ensino e Aplica��o do Sistema Braille em N�vel B�sico";
		String numeroVagas = "1222";
		String periodoPreInscricoes = "29/11/2017 at� 30/11/2017";
		String valorTotalAlmoco = "100.00";

		List<DescricaoEventoDTO> descricoesEventoDto = new ArrayList<>();

		DescricaoEventoDTO descricaoEmenta = new DescricaoEventoDTO();

		descricaoEmenta.setTipoDescricaoEvento(new TipoDescricaoEventoDTO("Descri��o / Ementa"));
		descricaoEmenta.setConteudo("Este curso visa blablabla");

		DescricaoEventoDTO publicoAlvo = new DescricaoEventoDTO();

		publicoAlvo.setTipoDescricaoEvento(new TipoDescricaoEventoDTO("P�blico Alvo"));
		publicoAlvo.setConteudo("Educadores\nEstudantes\nProfissionais da Sa�de");

		descricoesEventoDto.add(descricaoEmenta);
		descricoesEventoDto.add(publicoAlvo);

		DetalheCursoDTO detalheCursoDto = new DetalheCursoDTO(idGrupo, nomeCurso, periodoPreInscricoes, dataCurso,
				cargaHoraria, numeroVagas, investimento, descricoesEventoDto, valorTotalAlmoco);
		
		return detalheCursoDto;
	}
}

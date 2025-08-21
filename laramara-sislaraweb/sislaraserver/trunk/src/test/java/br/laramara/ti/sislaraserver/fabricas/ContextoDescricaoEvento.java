package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.evento.DescricaoEventoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.TipoDescricaoEventoDTO;
import br.laramara.ti.sislaraserver.dominio.evento.DescricaoEvento;
import br.laramara.ti.sislaraserver.dominio.evento.TipoDescricaoEvento;

public class ContextoDescricaoEvento {

	public static DescricaoEvento fabricarDescricaoEmentaComTodosOsDados() {
		DescricaoEvento descricaoEvento = new DescricaoEvento();

		descricaoEvento
				.setTipoDescricaoEvento(TipoDescricaoEvento.DESCRICAO_EMENTA);
		descricaoEvento.setConteudo("Esse curso visa blablabla.\n" + "Descrição e Ementa");

		return descricaoEvento;
	}

	public static DescricaoEventoDTO construirDescricaoEventoDescricaoEmentaDTO() {
		DescricaoEventoDTO descricaoEventoDto = new DescricaoEventoDTO();

		descricaoEventoDto
				.setTipoDescricaoEvento(new TipoDescricaoEventoDTO(TipoDescricaoEvento.DESCRICAO_EMENTA.toString()));
		descricaoEventoDto.setConteudo("Esse curso visa blablabla.\n" + "Descrição e Ementa");

		return descricaoEventoDto;
	}
	
	public static DescricaoEventoDTO construirDescricaoEventoCoordenacaoDTO() {
		DescricaoEventoDTO descricaoEventoDto = new DescricaoEventoDTO();

		descricaoEventoDto
				.setTipoDescricaoEvento(new TipoDescricaoEventoDTO(TipoDescricaoEvento.COORDENACAO.toString()));
		descricaoEventoDto.setConteudo("Coordenação");

		return descricaoEventoDto;
	}

}

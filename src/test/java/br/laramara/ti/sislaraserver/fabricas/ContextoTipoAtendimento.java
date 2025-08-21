package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.TipoAtendimentoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.TipoAtendimento;

public class ContextoTipoAtendimento {

	public static TipoAtendimentoDTO construirTipoAtendimentoDTO() {
		Long id = new Long(122222);
		String nome = "Atendimento Educacional Especializado.";

		List<DescricaoTipoAtendimentoDTO> descricaoTipoAtendientoDto = new ArrayList<>();
		descricaoTipoAtendientoDto.add(ContextoDescricaoTipoAtendimento
				.construirDescricaoTipoAtendimentoDTO());

		TipoAtendimentoDTO tipoAtendimentoDto = new TipoAtendimentoDTO();
		tipoAtendimentoDto.setId(id);
		tipoAtendimentoDto.setNome(nome);
		tipoAtendimentoDto
				.setDescricoesTipoAtendimentoDto(descricaoTipoAtendientoDto);

		return tipoAtendimentoDto;
	}

	public static TipoAtendimento fabricarComTodosOsDados() {

		List<DescricaoTipoAtendimento> descricaoTipoAtendmento = new ArrayList<>();
		descricaoTipoAtendmento.add(ContextoDescricaoTipoAtendimento
				.fabricarComTodosOsDados());

		TipoAtendimento tipoAtendimento = new TipoAtendimento();
		tipoAtendimento.setId(new Long(12222));
		tipoAtendimento.setNome("Atendimento Educacional Especializado");
		tipoAtendimento.setDescricoesTipoAtendimento(descricaoTipoAtendmento);
		return tipoAtendimento;
	}
}

package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTipoAtendimentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tipoatendimentodto_foi_construida_com_sucesso() {

		Long id = new Long(12222);
		String nomeTipoAtendimento = "Atendimento Educacional Especializado";

		List<DescricaoTipoAtendimentoDTO> descricoesTipoAtendimentoDto = new ArrayList<>();
		descricoesTipoAtendimentoDto.add(new DescricaoTipoAtendimentoDTO());

		TipoAtendimentoDTO tipoAtendimentoDto = new TipoAtendimentoDTO();
		tipoAtendimentoDto.setId(id);
		tipoAtendimentoDto.setNome(nomeTipoAtendimento);
		tipoAtendimentoDto
				.setDescricoesTipoAtendimentoDto(descricoesTipoAtendimentoDto);

		Assert.assertEquals(tipoAtendimentoDto.getId(), id);
		Assert.assertEquals(tipoAtendimentoDto.toString(), nomeTipoAtendimento);
		Assert.assertEquals(tipoAtendimentoDto
				.getDescricoesTipoAtendimentoDto().size(),
				descricoesTipoAtendimentoDto.size());
	}
}

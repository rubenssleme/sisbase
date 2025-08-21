package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesRecursoRelacionamentoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_relacionamento_foi_construido_com_sucesso() {
		Long id = new Long(12222);
		RecursoDTO recursoDto = new RecursoDTO(id, "", false, false, "", Arrays.asList());
		PossuiNecessitaDTO relacaoRecursoDTO = new PossuiNecessitaDTO("POSSUI");

		RecursoRelacionamentoDTO recursoRelacionamentoDto = new RecursoRelacionamentoDTO();
		recursoRelacionamentoDto.setId(id);
		recursoRelacionamentoDto.setRecursoDto(recursoDto);
		recursoRelacionamentoDto.setPossuiNecessitaDto(relacaoRecursoDTO);

		Assert.assertEquals(recursoRelacionamentoDto.getId(), id);
		Assert.assertEquals(recursoRelacionamentoDto.getRecursoDto().toString(), recursoDto.toString());
		Assert.assertEquals(recursoRelacionamentoDto.getPossuiNecessitaDto().toString(), relacaoRecursoDTO.toString());
	}
}

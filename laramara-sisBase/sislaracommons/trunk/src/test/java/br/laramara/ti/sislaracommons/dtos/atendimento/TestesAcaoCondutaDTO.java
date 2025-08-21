package br.laramara.ti.sislaracommons.dtos.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesAcaoCondutaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void acao_conduta_dto_foi_construida_com_sucesso() {
		Long id = new Long(12333);
		TipoAcaoCondutaDTO tipoAcaoCondutaDTO = new TipoAcaoCondutaDTO("TESTE");
		boolean cancelada = true;
		GrupoDTO grupoDto = new GrupoDTO();
		String dataExpectativa = "31/12/2000";
		
		AcaoCondutaDTO acaoCondutaDto = new AcaoCondutaDTO();
		acaoCondutaDto.setId(id);
		acaoCondutaDto.setTipoAcaoCondutaDto(tipoAcaoCondutaDTO);
		acaoCondutaDto.setCancelada(cancelada);
		acaoCondutaDto.setGrupoDto(grupoDto);
		acaoCondutaDto.setDataExpectativa(dataExpectativa);

		Assert.assertEquals(acaoCondutaDto.getId(), id);
		Assert.assertEquals(acaoCondutaDto.getTipoAcaoCondutaDto(), tipoAcaoCondutaDTO);
		Assert.assertEquals(acaoCondutaDto.isCancelada(), cancelada);
		Assert.assertEquals(acaoCondutaDto.getGrupoDto().getId(), grupoDto.getId());
		Assert.assertEquals(acaoCondutaDto.getDataExpectativa(), dataExpectativa);
	}
}

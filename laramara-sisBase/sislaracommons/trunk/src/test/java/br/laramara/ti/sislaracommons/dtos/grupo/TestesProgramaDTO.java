package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesProgramaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void programadto_foi_construida_com_sucesso() {

		Long id = new Long(1222);
		String aula = "1º";
		String data = "31/12/2012";
		String temaConteudo = "Texto do tema do conteúdo.";
		String estrategias = "Texto de estratégias.";
		LocalAtendimentoDTO localAtendimentoDTO = new LocalAtendimentoDTO(new Long(1222), "SALA 1");

		ProgramacaoDTO programaDto = new ProgramacaoDTO();
		programaDto.setId(id);
		programaDto.setAula(aula);
		programaDto.setData(data);
		programaDto.setTemaConteudo(temaConteudo);
		programaDto.setEstrategias(estrategias);
		programaDto.setLocalAtendimentoDTO(localAtendimentoDTO);

		Assert.assertEquals(programaDto.getId(), id);
		Assert.assertEquals(programaDto.getAula(), aula);
		Assert.assertEquals(programaDto.getData(), data);
		Assert.assertEquals(programaDto.getTemaConteudo(), temaConteudo);
		Assert.assertEquals(programaDto.getEstrategias(), estrategias);
		Assert.assertEquals(programaDto.getLocalAtendimentoDTO().toString(), localAtendimentoDTO.toString());
	}
}

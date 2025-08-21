package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesProgramacaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void programadto_foi_construida_com_sucesso() {

		Long id = new Long(1222);
		String aula = "1º";
		String data = "31/12/2012";
		String temaConteudo = "Texto do tema do conteúdo.";
		String recadoFamilia = "Texto de recado para a familia";
		String estrategias = "Texto de estratégias.";
		LocalAtendimentoDTO localAtendimentoDTO = new LocalAtendimentoDTO(new Long(1222), "SALA 1");

		ProgramacaoDTO programacaoDto = new ProgramacaoDTO();
		programacaoDto.setId(id);
		programacaoDto.setAula(aula);
		programacaoDto.setData(data);
		programacaoDto.setTemaConteudo(temaConteudo);
		programacaoDto.setRecadoFamilia(recadoFamilia);
		programacaoDto.setEstrategias(estrategias);
		programacaoDto.setLocalAtendimentoDTO(localAtendimentoDTO);

		Assert.assertEquals(programacaoDto.getId(), id);
		Assert.assertEquals(programacaoDto.getAula(), aula);
		Assert.assertEquals(programacaoDto.getData(), data);
		Assert.assertEquals(programacaoDto.getTemaConteudo(), temaConteudo);
		Assert.assertEquals(programacaoDto.getRecadoFamilia(), recadoFamilia);
		Assert.assertEquals(programacaoDto.getEstrategias(), estrategias);
		Assert.assertEquals(programacaoDto.getLocalAtendimentoDTO().toString(), localAtendimentoDTO.toString());
	}
}

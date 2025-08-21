package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.ProgramacaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.Programacao;

public class TestesFabricaProgramacao {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_programacao_converte_objeto_de_dominio_para_dto() {
		Programacao programacao = ContextoProgramacao
				.fabricarProgramacaoComTodosOsDados();

		ProgramacaoDTO programacaoDTO = new FabricaProgramacao()
				.converterParaDTO(programacao);

		Assert.assertEquals(programacaoDTO.getId(), programacao.getId());
		Assert.assertEquals(programacaoDTO.getAula(), programacao.getAula());
		Assert.assertEquals(programacaoDTO.getData(), programacao.getData());
		Assert.assertEquals(programacaoDTO.getTemaConteudo(),
				programacao.getTemaConteudo());
		Assert.assertEquals(programacaoDTO.getEstrategias(),
				programacao.getEstrategias());
		Assert.assertEquals(programacaoDTO.getLocalAtendimentoDTO().toString(),
				programacao.getLocalAtendimento().getNome());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_programacao_converte_objeto_de_dto_para_dominio() {
		ProgramacaoDTO programacaoDto = ContextoProgramacao
				.construirProgramacaoDTO();

		Programacao programacaoObtido = new FabricaProgramacao()
				.converterParaDominio(programacaoDto);

		Assert.assertEquals(programacaoObtido.getId(), programacaoDto.getId());
		Assert.assertEquals(programacaoObtido.getAula(),
				programacaoDto.getAula());
		Assert.assertEquals(programacaoObtido.getData(),
				programacaoDto.getData());
		Assert.assertEquals(programacaoObtido.getTemaConteudo(),
				programacaoDto.getTemaConteudo());
		Assert.assertEquals(programacaoObtido.getEstrategias(),
				programacaoDto.getEstrategias());
		Assert.assertEquals(programacaoObtido.getLocalAtendimento().getNome(),
				programacaoDto.getLocalAtendimentoDTO().toString());
	}
}

package br.laramara.ti.sislaracommons.dtos.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaopesquisaAtendimentoIndividualDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_atendimento_invidualdto_sem_erro_foi_construido() {
		String data = "31/12/2012";
		String prontuario = "12345";
		ProfissionalDTO profissionalDto = new ProfissionalDTO(new Long(1),
				"Josep", "1234");
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(1));
		ModuloDTO moduloDto = new ModuloDTO(new Long(12), "Informcao");

		EspecificacaoPesquisaAtendimentoIndividualDTO especificacaoDto = new EspecificacaoPesquisaAtendimentoIndividualDTO();
		especificacaoDto.setProfissionalDto(profissionalDto);
		especificacaoDto
				.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDTO);
		especificacaoDto.setModuloDto(moduloDto);
		especificacaoDto.setDataInicio(data);
		especificacaoDto.setDataTermino(data);
		especificacaoDto.setProntuario(prontuario);

		Assert.assertEquals(especificacaoDto.obterProfissionalDto().getId(),
				profissionalDto.getId());
		Assert.assertEquals(especificacaoDto.obterDescricaoTipoAtendimentoDto()
				.getId(), descricaoTipoAtendimentoDTO.getId());
		Assert.assertEquals(especificacaoDto.obterModuloDto().getId(),
				moduloDto.getId());
		Assert.assertEquals(especificacaoDto.getDataInicio(), data);
		Assert.assertEquals(especificacaoDto.getDataTermino(), data);
		Assert.assertEquals(especificacaoDto.getProntuario().toString(), prontuario);
	}
}

package br.laramara.ti.sislaracommons.dtos.agenda;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoPesquisaAgendamentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaopesquisaagendamentodto_sem_erro_foi_construido() {
		String data = "31/12/2012";
		ProfissionalDTO profissionalDTO = new ProfissionalDTO(new Long(1),
				"Josep", "1234");
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(1));
		String prontuario = "1234";
		ModuloDTO moduloDto = new ModuloDTO(new Long(12), "Informcao");
		String rg = "982734A";

		StatusAgendamentoDTO statusAgendamentoDTO = new StatusAgendamentoDTO(
				"AGENDADO");

		EspecificacaoPesquisaAgendamentoDTO especificacaoDto = new EspecificacaoPesquisaAgendamentoDTO();
		especificacaoDto.setProfissionalDto(profissionalDTO);
		especificacaoDto
				.setDescricaoTipoAtendimentoDTO(descricaoTipoAtendimentoDTO);
		especificacaoDto.setModuloDTO(moduloDto);
		especificacaoDto.setDataInicio(data);
		especificacaoDto.setDataTermino(data);
		especificacaoDto.setStatusAgendamentoDTO(statusAgendamentoDTO);
		especificacaoDto.setProntuario(prontuario);
		especificacaoDto.setRgPreCadastro(rg);
		especificacaoDto.marcarDataFutura();
		
		Assert.assertEquals(especificacaoDto.getProfissionalDto().getId(),
				profissionalDTO.getId());
		Assert.assertEquals(especificacaoDto.getDescricaoTipoAtendimentoDTO()
				.getId(), descricaoTipoAtendimentoDTO.getId());
		Assert.assertEquals(especificacaoDto.getModuloDTO().getId(),
				moduloDto.getId());
		Assert.assertEquals(especificacaoDto.getDataInicio(), data);
		Assert.assertEquals(especificacaoDto.getDataTermino(), data);
		Assert.assertEquals(especificacaoDto.getStatusAgendamentoDTO()
				.toString(), statusAgendamentoDTO.toString());
		Assert.assertEquals(especificacaoDto.getProntuario(), prontuario);
		Assert.assertEquals(especificacaoDto.getRgPreCadastro(), rg);
		Assert.assertTrue(especificacaoDto.estaDataFutura());
	}
}

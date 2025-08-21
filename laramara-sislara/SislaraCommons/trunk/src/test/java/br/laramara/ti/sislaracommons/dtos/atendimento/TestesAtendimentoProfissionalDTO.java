package br.laramara.ti.sislaracommons.dtos.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.InformacaoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesAtendimentoProfissionalDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentoprofissionaldto_foi_construida_com_sucesso() {
		Long id = new Long("1222");
		FrequenciaDTO frequenciaDto = new FrequenciaDTO("AT");
		String textoGrande = "super texto";
		InformacaoAtendimentoDTO informacoesAtendimentoDto = new InformacaoAtendimentoDTO();

		AtendimentoProfissionalDTO atendimentoProfissionalDto = new AtendimentoProfissionalDTO();
		ProfissionalDTO profissionalDto = new ProfissionalDTO(id, "Nome",
				"1234");

		atendimentoProfissionalDto.setId(id);
		atendimentoProfissionalDto.setProfissionalDto(profissionalDto);
		informacoesAtendimentoDto.setFrequenciaDto(frequenciaDto);
		informacoesAtendimentoDto.setDescricao(textoGrande);
		informacoesAtendimentoDto.setJustificativa(textoGrande);
		atendimentoProfissionalDto
				.setInformacaoAtendimentoDto(informacoesAtendimentoDto);

		Assert.assertEquals(atendimentoProfissionalDto.getId(), id);
		Assert.assertEquals(atendimentoProfissionalDto.getProfissionalDto()
				.getId(), id);
		Assert.assertEquals(atendimentoProfissionalDto
				.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
				frequenciaDto.toString());
		Assert.assertEquals(atendimentoProfissionalDto
				.getInformacaoAtendimentoDto().getDescricao(), textoGrande);
		Assert.assertEquals(atendimentoProfissionalDto
				.getInformacaoAtendimentoDto().getJustificativa(), textoGrande);
	}
}

package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesInformacaoAtendimentoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaoatendimentodto_foi_construida_com_sucesso() {
		Long id = new Long(83444);
		String descricao = "Descrição";
		String justificativa = "justificacao";
		FrequenciaDTO frequenciaDTO = new FrequenciaDTO("FJ");
		ParticipacaoDTO participacaoDto = new ParticipacaoDTO("COM_FAMILIA");

		InformacaoAtendimentoDTO informacaoAtendimentoDTO = new InformacaoAtendimentoDTO();
		informacaoAtendimentoDTO.setId(id);
		informacaoAtendimentoDTO.setDescricao(descricao);
		informacaoAtendimentoDTO.setJustificativa(justificativa);
		informacaoAtendimentoDTO.setFrequenciaDto(frequenciaDTO);
		informacaoAtendimentoDTO.setParticipacaoDto(participacaoDto);

		Assert.assertEquals(informacaoAtendimentoDTO.getId(), id);
		Assert.assertEquals(informacaoAtendimentoDTO.getDescricao(), descricao);
		Assert.assertEquals(informacaoAtendimentoDTO.getFrequenciaDto()
				.toString(), frequenciaDTO.toString());
		Assert.assertEquals(informacaoAtendimentoDTO.getJustificativa(),
				justificativa);
		Assert.assertEquals(informacaoAtendimentoDTO.getParticipacaoDto()
				.toString(), participacaoDto.toString());
	}
}

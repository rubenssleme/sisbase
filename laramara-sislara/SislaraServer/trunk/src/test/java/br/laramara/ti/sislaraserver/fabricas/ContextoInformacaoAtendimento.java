package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.InformacaoAtendimentoDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.Participacao;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;

public class ContextoInformacaoAtendimento {
	public static InformacaoAtendimento fabricarInformacaoTrabalhoComTodosOsDados() {
		InformacaoAtendimento informacaoAtendimento = new InformacaoAtendimento();
		informacaoAtendimento.setDescricao("Grande descrição");
		informacaoAtendimento.setFrequencia(Frequencia.FJ);
		informacaoAtendimento.setJustificativa("Grande justificativa");
		informacaoAtendimento.setParticipacao(Participacao.COM_FAMILIA);
		return informacaoAtendimento;
	}

	public static InformacaoAtendimentoDTO construirInformacaoAtendimentoDTO() {
		InformacaoAtendimentoDTO informacaoAtendimentoDTO = new InformacaoAtendimentoDTO();
		informacaoAtendimentoDTO.setDescricao("Grande descrição");
		informacaoAtendimentoDTO.setFrequenciaDto(new FrequenciaDTO("FJ"));
		informacaoAtendimentoDTO.setJustificativa("Grande justificativa");
		informacaoAtendimentoDTO.setParticipacaoDto(new ParticipacaoDTO(
				Participacao.APENAS_FAMILIA.toString()));
		return informacaoAtendimentoDTO;
	}
	
	public static InformacaoAtendimentoDTO construirInformacaoAtendimentoComATDTO() {
		InformacaoAtendimentoDTO informacaoAtendimentoDTO = construirInformacaoAtendimentoDTO();
		informacaoAtendimentoDTO.setFrequenciaDto(new FrequenciaDTO("AT"));
		return informacaoAtendimentoDTO;
	}
}

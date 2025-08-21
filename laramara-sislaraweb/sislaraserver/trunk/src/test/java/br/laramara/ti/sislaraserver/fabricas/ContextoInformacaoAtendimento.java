package br.laramara.ti.sislaraserver.fabricas;

import java.util.Arrays;

import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.InformacaoAtendimentoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;

public class ContextoInformacaoAtendimento {
	public static InformacaoAtendimento fabricarInformacaoAtendimentoComTodosOsDados() {
		InformacaoAtendimento informacaoAtendimento = new InformacaoAtendimento();
		informacaoAtendimento.setDescricao("Grande descrição");
		informacaoAtendimento.setFrequencia(Frequencia.FJ);
		informacaoAtendimento.setJustificativa("Grande justificativa");
		informacaoAtendimento.setParticipacaoDetalhada(
				Arrays.asList(ContextoParticipacaoDetalhada.contruirParticipacaoDetalhada()));
		return informacaoAtendimento;
	}
	
	public static InformacaoAtendimento fabricarInformacaoAtendimentoATComTodosOsDados() {
		InformacaoAtendimento informacaoAtendimento = fabricarInformacaoAtendimentoComTodosOsDados();
		informacaoAtendimento.setFrequencia(Frequencia.AT);
		return informacaoAtendimento;
	}

	public static InformacaoAtendimentoDTO construirInformacaoAtendimentoDTO() {
		InformacaoAtendimentoDTO informacaoAtendimentoDTO = new InformacaoAtendimentoDTO();
		informacaoAtendimentoDTO.setDescricao("Grande descrição");
		informacaoAtendimentoDTO.setFrequenciaDto(new FrequenciaDTO("FJ"));
		informacaoAtendimentoDTO.setJustificativa("Grande justificativa");
		return informacaoAtendimentoDTO;
	}
	
	public static InformacaoAtendimentoDTO construirInformacaoAtendimentoComATDTO() {
		InformacaoAtendimentoDTO informacaoAtendimentoDTO = construirInformacaoAtendimentoDTO();
		informacaoAtendimentoDTO.setFrequenciaDto(new FrequenciaDTO("AT"));
		return informacaoAtendimentoDTO;
	}
}

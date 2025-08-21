package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.InformacaoAtendimentoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;

public class FabricaInformacaoAtendimento extends
		FabricaBase<InformacaoAtendimentoDTO, InformacaoAtendimento> {

	@Override
	public InformacaoAtendimentoDTO converterParaDTO(
			InformacaoAtendimento informacoesAtendimento) {

		InformacaoAtendimentoDTO informacoesAtendimentoDto = null;
		if (informacoesAtendimento != null) {
			informacoesAtendimentoDto = new InformacaoAtendimentoDTO();
			informacoesAtendimentoDto.setId(informacoesAtendimento.getId());
			informacoesAtendimentoDto.setFrequenciaDto(new FabricaFrequencia()
					.converterParaDTO(informacoesAtendimento.getFrequencia()));
			informacoesAtendimentoDto.setDescricao(informacoesAtendimento
					.getDescricao());
			informacoesAtendimentoDto.setJustificativa(informacoesAtendimento
					.getJustificativa());
			informacoesAtendimentoDto
					.setParticipacaoDto(new FabricaParticipacao()
							.converterParaDTO(informacoesAtendimento
									.getParticipacao()));

		}
		return informacoesAtendimentoDto;
	}

	@Override
	public InformacaoAtendimento converterParaDominio(
			InformacaoAtendimentoDTO informacoesAtendimentoDto) {

		InformacaoAtendimento informacoesAtendimento = null;
		if (informacoesAtendimentoDto != null) {
			informacoesAtendimento = new InformacaoAtendimento();
			informacoesAtendimento.setId(informacoesAtendimentoDto.getId());

			informacoesAtendimento.setFrequencia(new FabricaFrequencia()
					.converterParaDominio(informacoesAtendimentoDto
							.getFrequenciaDto()));
			informacoesAtendimento.setDescricao(informacoesAtendimentoDto
					.getDescricao());
			informacoesAtendimento.setJustificativa(informacoesAtendimentoDto
					.getJustificativa());
			informacoesAtendimento.setParticipacao(new FabricaParticipacao()
					.converterParaDominio(informacoesAtendimentoDto
							.getParticipacaoDto()));
		}
		return informacoesAtendimento;
	}
}

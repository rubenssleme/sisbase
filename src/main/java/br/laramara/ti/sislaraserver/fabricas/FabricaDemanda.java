package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.DemandaDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Demanda;

public class FabricaDemanda extends FabricaRecursiva<DemandaDTO, Demanda> {

	@Override
	public Demanda converterParaDominio(DemandaDTO demandaDto, Demanda demanda) {
		if (demandaDto != null) {
			if (demanda == null) {
				demanda = obterNovo();
			}
			demanda.setId(demandaDto.getId());
			demanda.setUsuario(new FabricaUsuario().converterParaDominio(
					demandaDto.getUsuarioDto(), demanda.getUsuario()));
			demanda.setPreCadastro(new FabricaPreCadastro()
					.converterParaDominio(demandaDto.getPreCadastroDto(),
							demanda.getPreCadastro()));
			demanda.setRecurso(new FabricaRecurso()
					.converterParaDominio(demandaDto.getRecursoDto()));
			demanda.setGrupo(new FabricaGrupo().converterParaDominio(
					demandaDto.getGrupoDto(), demanda.getGrupo()));
			demanda.setObs(demandaDto.getObs());
			demanda.setDocumentosSolicitacaoDoacao(new FabricaDocumentoSolicitacaoDoacao()
					.converterParaDominio(demandaDto.getDocumentosSolicitacaoDoacaoDto()));
			demanda.setCarteloDeSelosEInicializarDataPrazoCaptacao(demandaDto.isCartelaDeSelos());
			demanda.setMotivoCancelamento(
					new FabricaMotivoCancelamento().converterParaDominio(demandaDto.getMotivoCancelamentoDTO()));
		}
		return demanda;
	}

	@Override
	public DemandaDTO converterParaDTO(Demanda demanda) {
		DemandaDTO demandaDto = new DemandaDTO();
		demandaDto.setId(demanda.getId());
		demandaDto.setDataPrazoDeCaptacao(demanda.getDataPrazoDeCaptacao());
		demandaDto.setUsuarioDto(new FabricaUsuario().converterParaDTO(demanda
				.getUsuario()));
		demandaDto.setPreCadastroDto(new FabricaPreCadastro()
				.converterParaDTO(demanda.getPreCadastro()));
		demandaDto.setRecursoDto(new FabricaRecurso().converterParaDTO(demanda
				.getRecurso()));
		demandaDto.setGrupoDto(new FabricaGrupo().converterParaDTO(demanda
				.getGrupo()));
		demandaDto.setObs(demanda.getObs());
		demandaDto.setResponsaveisOperacoes(demanda.getHistoricoOperacoes());
		demandaDto.setStatusDemandaDto(new FabricaStatusDemanda()
				.converterParaDTO(demanda.obterStatusDemantaAtual()));
		demandaDto.setEstaNovo(demanda.estaAguardando());
		demandaDto.setDocumentosSolicitacaoDoacao(
				new FabricaDocumentoSolicitacaoDoacao().converterParaDTO(demanda.getDocumentosSolicitacaoDoacao()));
		demandaDto.setCartelaDeSelos(demanda.isCarteloDeSelos());
		demandaDto.setValorTotalCaptado(demanda.obterValorTotalCaptado());
		demandaDto.setValorCartela(demanda.obterValorCartela());
		demandaDto.setValorSaldo(demanda.obterValorSaldo());
		demandaDto.setMotivoCancelamentoDTO(
				new FabricaMotivoCancelamento().converterParaDTO(demanda.getMotivoCancelamento()));
		return demandaDto;
	}

	@Override
	public Demanda obterNovo() {
		return new Demanda();
	}
}

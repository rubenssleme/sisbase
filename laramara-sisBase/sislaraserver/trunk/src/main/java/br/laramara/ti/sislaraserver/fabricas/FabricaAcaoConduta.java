package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.AcaoCondutaDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AcaoConduta;

public class FabricaAcaoConduta extends FabricaRecursiva<AcaoCondutaDTO, AcaoConduta> {

	@Override
	public AcaoConduta converterParaDominio(AcaoCondutaDTO acaoCondutaDto, AcaoConduta acaoConduta) {
		if (acaoCondutaDto != null) {
			if (acaoConduta == null){
				acaoConduta = new AcaoConduta();
			}
			acaoConduta.setId(acaoCondutaDto.getId());
			acaoConduta.setTipoAcaoConduta(
					new FabricaTipoAcaoConduta().converterParaDominio(acaoCondutaDto.getTipoAcaoCondutaDto()));
			acaoConduta.setGrupo(new FabricaGrupo().converterParaDominio(acaoCondutaDto.getGrupoDto()));
			acaoConduta.setCancelada(acaoCondutaDto.isCancelada());
			acaoConduta.setDataExpectativa(acaoCondutaDto.getDataExpectativa());
			acaoConduta.setSetor(new FabricaSetor().converterParaDominio(acaoCondutaDto.getSetorDto()));
			acaoConduta.setObs(acaoCondutaDto.getObs());
		}
		return acaoConduta;
	}

	@Override
	public AcaoCondutaDTO converterParaDTO(AcaoConduta acaoConduta) {
		AcaoCondutaDTO acaoCondutaDto = new AcaoCondutaDTO();
		if (acaoConduta.getId() != null) {
			acaoCondutaDto.setId(acaoConduta.getId());
		}
		acaoCondutaDto
				.setTipoAcaoCondutaDto(new FabricaTipoAcaoConduta().converterParaDTO(acaoConduta.getTipoAcaoConduta()));
		acaoCondutaDto.setGrupoDto(new FabricaGrupo().converterParaDTO(acaoConduta.getGrupo()));
		acaoCondutaDto.setObs(acaoConduta.getObs());
		acaoCondutaDto.setResultadoProcessamento(acaoConduta.getResultadoProcessamento());
		acaoCondutaDto.setDataProcessamento(acaoConduta.getDataProcessamento());
		acaoCondutaDto.setCancelada(acaoConduta.cancelada());
		acaoCondutaDto.setDataExpectativa(acaoConduta.getDataExpectativa());
		acaoCondutaDto.setSetorDto(new FabricaSetor().converterParaDTO(acaoConduta.getSetor()));
		return acaoCondutaDto;
	}

	@Override
	public AcaoConduta obterNovo() {
		return new AcaoConduta();
	}
}

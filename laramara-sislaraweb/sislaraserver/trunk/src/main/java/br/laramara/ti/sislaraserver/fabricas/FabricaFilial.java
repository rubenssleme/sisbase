package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.FilialDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Filial;

public class FabricaFilial extends FabricaBase<FilialDTO, Filial> {

	public final FilialDTO converterParaDTO(Filial filial) {
		FilialDTO filialDTO = new FilialDTO();
		filialDTO.setId(filial.getId());
		filialDTO.setCep(filial.getCep());
		filialDTO.setCnpj(filial.getCnpj());
		filialDTO.setEndereco(filial.getEndereco());
		return filialDTO;
	}

	public final Filial converterParaDominio(FilialDTO filialDto) {
		Filial filial = null;
		if (filialDto != null) {
			filial = new Filial();
			filial.setId(filialDto.getId());
			filial.setCep(filialDto.getCep());
			filial.setCnpj(filialDto.getCnpj());
			filial.setEndereco(filialDto.getEndereco());
		}
		return filial;
	}
}

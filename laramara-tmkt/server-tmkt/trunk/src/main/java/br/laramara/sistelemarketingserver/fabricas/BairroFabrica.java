package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.BairroDTO;
import br.laramara.sistelemarketingserver.dominio.Bairro;

public class BairroFabrica extends FabricaBase<BairroDTO, Bairro> {
	public final BairroDTO converterParaDTO(Bairro bairro) {
		BairroDTO bairroDto = new BairroDTO();
		if (bairro != null) {
			bairroDto.setNome(bairro.getNome());
		}
		return bairroDto;
	}

	public final Bairro converterParaDominio(BairroDTO bairroDto) {
		Bairro bairro = new Bairro();
		if (bairroDto != null) {
			bairro.setNome(bairroDto.getNome());
		}
		return bairro;
	}
}

package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.contato.DistribuicaoContatoDTO;
import br.laramara.sistelemarketingserver.dominio.contato.DistribuicaoContato;

public class DistribuicaoContatoFabrica extends FabricaBase<DistribuicaoContatoDTO, DistribuicaoContato> {
	public final DistribuicaoContatoDTO converterParaDTO(DistribuicaoContato distribuicaoContato) {
		DistribuicaoContatoDTO distribuicaoContatoDto = new DistribuicaoContatoDTO();
		if (distribuicaoContato != null) {
			distribuicaoContatoDto.setId(distribuicaoContato.getId());
			distribuicaoContatoDto
					.setContatoDto(new ContatoFabrica().converterParaDTO(distribuicaoContato.getContato()));
		}
		return distribuicaoContatoDto;
	}

	public final DistribuicaoContato converterParaDominio(DistribuicaoContatoDTO distribuicaoContatoDto) {
		DistribuicaoContato distribuicaoContato = new DistribuicaoContato();
		if (distribuicaoContatoDto != null) {
			distribuicaoContato.setId(distribuicaoContatoDto.getId());
		}
		return distribuicaoContato;
	}
}

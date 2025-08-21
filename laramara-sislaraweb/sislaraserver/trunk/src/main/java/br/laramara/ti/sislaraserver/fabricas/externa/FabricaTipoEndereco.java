package br.laramara.ti.sislaraserver.fabricas.externa;

import br.laramara.ti.sislaracommons.dtos.endereco.TipoEnderecoDTO;
import br.laramara.ti.sislaraserver.dominio.endereco.TipoEndereco;
import br.laramara.ti.sislaraserver.fabricas.FabricaBase;

public class FabricaTipoEndereco extends FabricaBase<TipoEnderecoDTO, TipoEndereco> {

	public final TipoEnderecoDTO converterParaDTO(TipoEndereco tipoEndereco) {
		return tipoEndereco != null ? new TipoEnderecoDTO(tipoEndereco.toString()) : null;
	}

	public final TipoEndereco converterParaDominio(TipoEnderecoDTO tipoEnderecoDto) {
		return tipoEnderecoDto != null ? TipoEndereco.valueOf(TipoEndereco.class, tipoEnderecoDto.toString()) : null;
	}

}

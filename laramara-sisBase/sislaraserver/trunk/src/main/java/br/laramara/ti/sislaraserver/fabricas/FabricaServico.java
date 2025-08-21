package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.ServicoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Servico;

public class FabricaServico extends FabricaBase<ServicoDTO, Servico> {

	public final ServicoDTO converterParaDTO(Servico convenio) {
		return convenio != null ? new ServicoDTO(convenio.getId(), convenio.getDescricao()) : null;
	}

	public final Servico converterParaDominio(ServicoDTO convenioDto) {
		return convenioDto != null ? new Servico(convenioDto.getId(), convenioDto.toString()) : null;
	}
}

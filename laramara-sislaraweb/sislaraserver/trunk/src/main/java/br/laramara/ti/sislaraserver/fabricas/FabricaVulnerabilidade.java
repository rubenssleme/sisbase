package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.VulnerabilidadeDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Vulnerabilidade;

public class FabricaVulnerabilidade extends FabricaBase<VulnerabilidadeDTO, Vulnerabilidade> {

	public final VulnerabilidadeDTO converterParaDTO(Vulnerabilidade vulnerabilidade) {
		return vulnerabilidade != null ? new VulnerabilidadeDTO(vulnerabilidade.getId(), vulnerabilidade.getDescricao())
				: null;
	}

	public final Vulnerabilidade converterParaDominio(VulnerabilidadeDTO vulnerabilidadeDto) {
		return vulnerabilidadeDto != null
				? new Vulnerabilidade(vulnerabilidadeDto.getId(), vulnerabilidadeDto.toString()) : null;
	}
}

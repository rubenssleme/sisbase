package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.contribuicao.MotivoDesativacaoDTO;
import br.laramara.ti.sislaraserver.dominio.contribuicao.MotivoDesativacao;

public class FabricaMotivoDesativacao extends FabricaBase<MotivoDesativacaoDTO, MotivoDesativacao> {
	public final MotivoDesativacaoDTO converterParaDTO(MotivoDesativacao motivoDesativacao) {
		return motivoDesativacao != null
				? new MotivoDesativacaoDTO(motivoDesativacao.getId(), motivoDesativacao.getDescricao()) : null;
	}

	public final MotivoDesativacao converterParaDominio(MotivoDesativacaoDTO estadoCivilDto) {
		return estadoCivilDto != null ? new MotivoDesativacao(estadoCivilDto.getId(), estadoCivilDto.toString()) : null;
	}
}

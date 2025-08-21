package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ClassificacaoDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Classificacao;

public class FabricaClassificacao extends
		FabricaBase<ClassificacaoDTO, Classificacao> {
	public final ClassificacaoDTO converterParaDTO(Classificacao classificacao) {
		return classificacao != null ? new ClassificacaoDTO(
				classificacao.toString()) : null;
	}

	public final Classificacao converterParaDominio(
			ClassificacaoDTO classificacaoDto) {
		return classificacaoDto != null ? Classificacao
				.valueOf(classificacaoDto.toString()) : null;
	}
}

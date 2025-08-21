package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.instituicao.ClassificacaoInstituicaoDTO;
import br.laramara.ti.sislaraserver.dominio.instituicao.ClassificacaoInstituicao;

public class FabricaClassificacaoInstituicao extends
		FabricaBase<ClassificacaoInstituicaoDTO, ClassificacaoInstituicao> {
	public final ClassificacaoInstituicaoDTO converterParaDTO(
			ClassificacaoInstituicao classificacaoInstituicao) {
		return classificacaoInstituicao != null ? new ClassificacaoInstituicaoDTO(
				classificacaoInstituicao.toString()) : null;
	}

	public final ClassificacaoInstituicao converterParaDominio(
			ClassificacaoInstituicaoDTO classificacaoInstituicao) {
		return classificacaoInstituicao != null ? ClassificacaoInstituicao
				.valueOf(ClassificacaoInstituicao.class,
						classificacaoInstituicao.toString()) : null;
	}
}

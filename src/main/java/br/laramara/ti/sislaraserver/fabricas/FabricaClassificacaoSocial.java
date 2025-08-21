package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.ClassificacaoSocialDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.ClassificacaoSocial;

public class FabricaClassificacaoSocial extends
		FabricaBase<ClassificacaoSocialDTO, ClassificacaoSocial> {

	public final ClassificacaoSocialDTO converterParaDTO(
			ClassificacaoSocial classificacaoSocial) {
		return classificacaoSocial != null ? new ClassificacaoSocialDTO(
				classificacaoSocial.toString()) : null;
	}

	public final ClassificacaoSocial converterParaDominio(
			ClassificacaoSocialDTO classificacaoSocial) {
		return classificacaoSocial != null ? ClassificacaoSocial.valueOf(
				ClassificacaoSocial.class, classificacaoSocial.toString())
				: null;
	}
}

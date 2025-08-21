package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.telefonia.LigacaoDTO;
import br.laramara.sistelemarketingserver.dominio.telefonia.Ligacao;

public class LigacaoFabrica extends FabricaBase<LigacaoDTO, Ligacao> {
	public final LigacaoDTO converterParaDTO(Ligacao ligacao) {
		return null;
	}

	public final Ligacao converterParaDominio(LigacaoDTO ligacaoDto) {
		Ligacao ligacao = new Ligacao();
		if (ligacaoDto != null) {
			ligacao.setToken(ligacaoDto.getToken());
			ligacao.setTelefone(new TelefoneFabrica().converterParaDominio(ligacaoDto.getTelefoneDto()));
		}
		return ligacao;
	}
}

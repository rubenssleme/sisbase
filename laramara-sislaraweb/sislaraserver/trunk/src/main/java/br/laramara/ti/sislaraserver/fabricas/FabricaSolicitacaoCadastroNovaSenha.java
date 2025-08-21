package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroNovaSenhaDTO;
import br.laramara.ti.sislaraserver.dominio.solicitacao.externa.SolicitacaoCadastroNovaSenha;

public class FabricaSolicitacaoCadastroNovaSenha
		extends FabricaBase<SolicitacaoCadastroNovaSenhaDTO, SolicitacaoCadastroNovaSenha> {

	@Override
	public SolicitacaoCadastroNovaSenha converterParaDominio(SolicitacaoCadastroNovaSenhaDTO solicitacaoCadastroNovaSenhaDto) {
		return new SolicitacaoCadastroNovaSenha(solicitacaoCadastroNovaSenhaDto.getChave(), solicitacaoCadastroNovaSenhaDto.getNovaSenha(),
				solicitacaoCadastroNovaSenhaDto.getConfirmacaoSenha());
	}

	@Override
	public SolicitacaoCadastroNovaSenhaDTO converterParaDTO(SolicitacaoCadastroNovaSenha objetoDominio) {
		return null;
	}

}

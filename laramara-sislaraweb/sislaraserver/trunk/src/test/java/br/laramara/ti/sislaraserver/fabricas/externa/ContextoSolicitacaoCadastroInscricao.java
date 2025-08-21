package br.laramara.ti.sislaraserver.fabricas.externa;

import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroInscricaoDTO;

public class ContextoSolicitacaoCadastroInscricao {

	public static SolicitacaoCadastroInscricaoDTO construirSolicitacaoCadastroInscricaoDTO(String token) {
		SolicitacaoCadastroInscricaoDTO solicitacaoCadastroInscricaoDto = new SolicitacaoCadastroInscricaoDTO();

		solicitacaoCadastroInscricaoDto.setToken(token);
		solicitacaoCadastroInscricaoDto.setInscricaoDto(ContextoInscricao.fabricarInscricaoDTOComTodosOsDados());

		return solicitacaoCadastroInscricaoDto;
	}
	
	public static SolicitacaoCadastroInscricaoDTO construirSolicitacaoCadastroInscricaoDTOComInscricaoInvalida(String token) {
		SolicitacaoCadastroInscricaoDTO solicitacaoCadastroInscricaoDto = new SolicitacaoCadastroInscricaoDTO();

		solicitacaoCadastroInscricaoDto.setToken(token);
		solicitacaoCadastroInscricaoDto.setInscricaoDto(ContextoInscricao.fabricarInscricaoDTOInvalida());

		return solicitacaoCadastroInscricaoDto;
	}
}

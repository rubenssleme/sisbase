package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoPreCadastroDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoPreCadastro;

public class ContextoAtendimentoPreCadastro {
	public static AtendimentoPreCadastro fabricarAtendimentoPreCadastroComTodosOsDados() {
		AtendimentoPreCadastro atendimentoPreCadastro = new AtendimentoPreCadastro();
		atendimentoPreCadastro.setId(new Long(1222));
		atendimentoPreCadastro.setPreCadastro(ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados());
		atendimentoPreCadastro
				.setInformacaoAtendimento(ContextoInformacaoAtendimento
						.fabricarInformacaoAtendimentoComTodosOsDados());
		atendimentoPreCadastro.setInstituicao(ContextoInstituicao.fabricarInstituicaoComTodosOsDados());
		atendimentoPreCadastro.setDiretoriaEnsino(ContextoDiretoriaEnsino.fabricarDiretoriaEnsinoComTodosOsDados());
		atendimentoPreCadastro.setDreCefai(ContextoDreCefai.fabricarDreCefaiComTodosOsDados());
		atendimentoPreCadastro.setTipoVinculo(ContextoTipoVinculo.fabricarComTodosOsDados());
		atendimentoPreCadastro.setNomeOrigem("Nome da Origem");
		return atendimentoPreCadastro;
	}

	public static AtendimentoPreCadastroDTO construirAtendimentoPreCadastroDTO() {
		AtendimentoPreCadastroDTO atendimentoPreCadastroDTO = new AtendimentoPreCadastroDTO();
		atendimentoPreCadastroDTO.setId(new Long(1222));
		atendimentoPreCadastroDTO.setPreCadastroDto(ContextoPreCadastro
				.construirPreCadastroDTO());
		atendimentoPreCadastroDTO
				.setInformacaoAtendimentoDto(ContextoInformacaoAtendimento
						.construirInformacaoAtendimentoDTO());
		return atendimentoPreCadastroDTO;
	}
}

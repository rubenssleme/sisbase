package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ProgramacaoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Programacao;

public class ContextoProgramacao {

	public static Programacao fabricarProgramacaoComTodosOsDados() {
		Programacao programacao = new Programacao();
		programacao.setAula("2");
		programacao.setData("31/12/2011");
		programacao.setTemaConteudo("Descri��o do tema conte�do.");
		programacao.setEstrategias("Descri��o da estrat�gia.");
		programacao.setLocalAtendimento(ContextoLocalAtendimento.fabricarComTodosOsDados());
		return programacao;
	}

	public static ProgramacaoDTO construirProgramacaoDTO() {
		ProgramacaoDTO programacaoDto = new ProgramacaoDTO();
		programacaoDto.setAula("1");
		programacaoDto.setData("31/12/2008");
		programacaoDto.setTemaConteudo("Descri��o do tema.");
		programacaoDto.setEstrategias("Descri��o de estrat�gias.");
		programacaoDto.setLocalAtendimentoDTO(ContextoLocalAtendimento.construirLocalAtendimentoDTO());
		return programacaoDto;
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ProgramacaoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Programacao;

public class ContextoProgramacao {

	public static Programacao fabricarProgramacaoComTodosOsDados() {
		Programacao programacao = new Programacao();
		programacao.setAula("2");
		programacao.setData("31/12/2011");
		programacao.setTemaConteudo("Descrição do tema conteúdo.");
		programacao.setEstrategias("Descrição da estratégia.");
		programacao.setLocalAtendimento(ContextoLocalAtendimento.fabricarComTodosOsDados());
		return programacao;
	}

	public static ProgramacaoDTO construirProgramacaoDTO() {
		ProgramacaoDTO programacaoDto = new ProgramacaoDTO();
		programacaoDto.setAula("1");
		programacaoDto.setData("31/12/2008");
		programacaoDto.setTemaConteudo("Descrição do tema.");
		programacaoDto.setEstrategias("Descrição de estratégias.");
		programacaoDto.setLocalAtendimentoDTO(ContextoLocalAtendimento.construirLocalAtendimentoDTO());
		return programacaoDto;
	}
}

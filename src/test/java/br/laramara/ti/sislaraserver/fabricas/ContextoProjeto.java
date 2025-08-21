package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.doacao.ProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.LoteRecurso;
import br.laramara.ti.sislaraserver.dominio.doacao.Projeto;

public class ContextoProjeto {
	public static Projeto fabricarProjetoComTodosOsDados() {
		Projeto projeto = new Projeto();
		projeto.setId(new Long(14444));
		projeto.setAtivo(true);
		projeto.setDataFinal("31/12/2012");
		projeto.setDataInicial("31/12/2012");
		projeto.setNome("Projeto X");
		projeto.setValorOutros("1000,00");
		projeto.setValorProdutos("5100,00");
		List<LoteRecurso> lotes = new ArrayList<>();
		lotes.add(ContextoLoteRecurso.fabricarComTodosOsDados());
		projeto.setLoteRecurso(lotes);
		return projeto;
	}

	public static ProjetoDTO construirProjetoDTO() {
		ProjetoDTO projetoDto = new ProjetoDTO();
		projetoDto.setId(new Long(1222));
		projetoDto.setAtivo(false);
		projetoDto.setDataInicial("31/12/2012");
		projetoDto.setDataFinal("31/12/2012");
		projetoDto.setNome("Paulo Augusto");
		projetoDto.setValorTotal("12222,22");
		projetoDto.setValorProdutos("26000,00");
		projetoDto.setValorOutros("343,44");
		List<LoteRecursoDTO> lotesRecursoDto = new ArrayList<>();
		lotesRecursoDto.add(ContextoLoteRecurso.fabricarComTodosOsDadosDTO());
		projetoDto.setLoteRecursoDto(lotesRecursoDto);
		return projetoDto;
	}
}

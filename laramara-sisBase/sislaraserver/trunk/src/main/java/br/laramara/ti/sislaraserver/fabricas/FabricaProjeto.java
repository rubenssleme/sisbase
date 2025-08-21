package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.ProjetoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Projeto;

public class FabricaProjeto extends FabricaRecursiva<ProjetoDTO, Projeto> {

	@Override
	public Projeto converterParaDominio(ProjetoDTO projetoDto, Projeto projeto) {
		if (projetoDto != null) {
			if (projeto == null) {
				projeto = obterNovo();
			}
			projeto.setId(projetoDto.getId());
			projeto.setAtivo(projetoDto.isAtivo());
			projeto.setDataInicial(projetoDto.getDataInicial());
			projeto.setDataFinal(projetoDto.getDataFinal());
			projeto.setNome(projetoDto.getNome());
			projeto.setValorProdutos(projetoDto.getValorProdutos());
			projeto.setValorOutros(projetoDto.getValorOutros());
			projeto.setLoteRecurso(new FabricaLoteRecurso()
					.converterParaDominio(projetoDto.getLoteRecursoDto()));
		}
		return projeto;
	}

	@Override
	public ProjetoDTO converterParaDTO(Projeto projeto) {
		ProjetoDTO projetoDto = null;
		if (projeto != null) {
			projetoDto = new ProjetoDTO();
			projetoDto.setId(projeto.getId());
			projetoDto.setAtivo(projeto.isAtivo());
			projetoDto.setDataInicial(projeto.getDataInicial());
			projetoDto.setDataFinal(projeto.getDataFinal());
			projetoDto.setNome(projeto.getNome());
			projetoDto.setValorTotal(projeto.getValorTotal());
			projetoDto.setValorProdutos(projeto.getValorProdutos());
			projetoDto.setValorOutros(projeto.getValorOutros());
			projetoDto.setLoteRecursoDto(new FabricaLoteRecurso()
					.converterParaDTO(projeto.getLoteRecurso()));
			projetoDto.setSomaTotalProdutos(projeto.obterSomaTotalProdutos());
			projetoDto.setResumoReservas(projeto.obterResumoReservas());
		}
		return projetoDto;
	}

	@Override
	public Projeto obterNovo() {
		return new Projeto();
	}
}

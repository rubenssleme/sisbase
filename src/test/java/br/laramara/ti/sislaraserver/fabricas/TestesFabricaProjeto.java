package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.ProjetoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.Projeto;

public class TestesFabricaProjeto {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_projeto_converte_objeto_de_dominio_para_dto() {

		Projeto projeto = ContextoProjeto.fabricarProjetoComTodosOsDados();

		ProjetoDTO projetoDtoCovertido = new FabricaProjeto()
				.converterParaDTO(projeto);

		Assert.assertEquals(projetoDtoCovertido.getId(), projeto.getId());
		Assert.assertEquals(projetoDtoCovertido.isAtivo(), projeto.isAtivo());
		Assert.assertEquals(projetoDtoCovertido.getDataInicial(),
				projeto.getDataInicial());
		Assert.assertEquals(projetoDtoCovertido.getDataFinal(),
				projeto.getDataFinal());
		Assert.assertEquals(projetoDtoCovertido.getNome(), projeto.getNome());
		Assert.assertEquals(projetoDtoCovertido.getValorTotal(), projeto.getValorTotal());
		Assert.assertEquals(projetoDtoCovertido.getValorOutros(), projeto.getValorOutros());
		Assert.assertEquals(projetoDtoCovertido.getValorProdutos(), projeto.getValorProdutos());
		Assert.assertEquals(projetoDtoCovertido.getLoteRecursoDto().size(),
				projeto.getLoteRecurso().size());
		Assert.assertEquals(projetoDtoCovertido.getSomaTotalProdutos(), projeto.obterSomaTotalProdutos());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_projeto_converte_objeto_dto_para_dominio() {

		ProjetoDTO projetoDto = ContextoProjeto.construirProjetoDTO();

		Projeto projetoCovertido = new FabricaProjeto()
				.converterParaDominio(projetoDto);

		Assert.assertEquals(projetoDto.getId(), projetoCovertido.getId());
		Assert.assertEquals(projetoDto.isAtivo(), projetoCovertido.isAtivo());
		Assert.assertEquals(projetoDto.getDataInicial(),
				projetoCovertido.getDataInicial());
		Assert.assertEquals(projetoDto.getDataFinal(),
				projetoCovertido.getDataFinal());
		Assert.assertEquals(projetoDto.getNome(), projetoCovertido.getNome());
		Assert.assertEquals(projetoDto.getValorOutros(), projetoCovertido.getValorOutros());
		Assert.assertEquals(projetoDto.getValorProdutos(), projetoCovertido.getValorProdutos());
		Assert.assertEquals(projetoDto.getLoteRecursoDto().size(),
				projetoCovertido.getLoteRecurso().size());
	}
}

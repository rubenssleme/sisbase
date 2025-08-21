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
		Assert.assertEquals(projetoDtoCovertido.getDataInicialVigencia(),
				projeto.getDataInicialVigencia());
		Assert.assertEquals(projetoDtoCovertido.getDataFinalVigencia(),
				projeto.getDataFinalVigencia());
		Assert.assertEquals(projetoDtoCovertido.getNome(), projeto.getNome());
		Assert.assertEquals(projetoDtoCovertido.getEditalInvestimento(), projeto.getEditalInvestimento());
		Assert.assertEquals(projetoDtoCovertido.getOrgaoParceiroFinanciador(), projeto.getOrgaoParceiroFinanciador());
		Assert.assertEquals(projetoDtoCovertido.getLei(), projeto.getLei());
		Assert.assertEquals(projetoDtoCovertido.getIncentivadoDto().toString(), projeto.getIncentivado().toString());
		Assert.assertEquals(projetoDtoCovertido.getClassificacaoDto().toString(), projeto.getClassificacao().toString());
		Assert.assertEquals(projetoDtoCovertido.getObjetivoGeral(), projeto.getObjetivoGeral());
		Assert.assertEquals(projetoDtoCovertido.getPublicoAlvo(), projeto.getPublicoAlvo());
		Assert.assertEquals(projetoDtoCovertido.getProfissionalAdministrativoResponsavelDto().getId(), projeto.getProfissionalAdministrativoResponsavel().getId());
		Assert.assertEquals(projetoDtoCovertido.getResponsaveisTecnicosDto().size(), projeto.getResponsaveisTecnicos().size());
		Assert.assertEquals(projetoDtoCovertido.getPrestacaoContaDto().toString(), projeto.getPrestacaoConta().toString());
		Assert.assertEquals(projetoDtoCovertido.getPatrociniosDto().size(), projeto.getPatrocinios().size());
		Assert.assertEquals(projetoDtoCovertido.getNumeroTermoFomentoParceria(), projeto.getNumeroTermoFomentoParceria());
		Assert.assertEquals(projetoDtoCovertido.getArquivos().size(), projeto.getArquivos().size());
		Assert.assertEquals(projetoDtoCovertido.getDuracao(), projeto.getDuracao());
		Assert.assertEquals(projetoDtoCovertido.getAditamento(), projeto.getAditamento());
		Assert.assertEquals(projetoDtoCovertido.getValorTotal(), projeto.getValorTotal());
		Assert.assertEquals(projetoDtoCovertido.getValorOutros(), projeto.getValorOutros());
		Assert.assertEquals(projetoDtoCovertido.getValorProdutos(), projeto.getValorProdutos());
		Assert.assertEquals(projetoDtoCovertido.getRecursoDisponivelDto().size(), projeto.getRecursosDisponiveis().size());
		Assert.assertEquals(projetoDtoCovertido.getIdadeMinima(), projeto.getIdadeMinima());
		Assert.assertEquals(projetoDtoCovertido.getIdadeMaxima(), projeto.getIdadeMaxima());
		//TODO: REMOVER
		/*
		Assert.assertEquals(projetoDtoCovertido.getLoteRecursoDto().size(),
				projeto.getLoteRecurso().size());
		Assert.assertEquals(projetoDtoCovertido.getSomaTotalProdutos(), projeto.obterSomaTotalProdutos());
		*/
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_projeto_converte_objeto_dto_para_dominio() {

		ProjetoDTO projetoDto = ContextoProjeto.construirProjetoDTO();

		Projeto projetoCovertido = new FabricaProjeto()
				.converterParaDominio(projetoDto);

		Assert.assertEquals(projetoCovertido.getId(), projetoDto.getId());
		Assert.assertEquals(projetoCovertido.isAtivo(), projetoDto.isAtivo());
		Assert.assertEquals(projetoCovertido.getDataInicialVigencia(), projetoDto.getDataInicialVigencia());
		Assert.assertEquals(projetoCovertido.getNome(), projetoDto.getNome());
		Assert.assertEquals(projetoCovertido.getEditalInvestimento(), projetoDto.getEditalInvestimento());
		Assert.assertEquals(projetoCovertido.getOrgaoParceiroFinanciador(), projetoDto.getOrgaoParceiroFinanciador());
		Assert.assertEquals(projetoCovertido.getLei(), projetoDto.getLei());
		Assert.assertEquals(projetoCovertido.getIncentivado().toString(), projetoDto.getIncentivadoDto().toString());
		Assert.assertEquals(projetoCovertido.getClassificacao().toString(),
				projetoDto.getClassificacaoDto().toString());
		Assert.assertEquals(projetoCovertido.getObjetivoGeral(), projetoDto.getObjetivoGeral());
		Assert.assertEquals(projetoCovertido.getPublicoAlvo(), projetoDto.getPublicoAlvo());
		Assert.assertEquals(projetoCovertido.getProfissionalAdministrativoResponsavel().getId(),
				projetoDto.getProfissionalAdministrativoResponsavelDto().getId());
		Assert.assertEquals(projetoCovertido.getResponsaveisTecnicos().size(),
				projetoDto.getResponsaveisTecnicosDto().size());
		Assert.assertEquals(projetoCovertido.getPrestacaoConta().toString(), projetoDto.getPrestacaoContaDto().toString());
		Assert.assertEquals(projetoCovertido.getPatrocinios().size(), projetoDto.getPatrociniosDto().size());
		Assert.assertEquals(projetoCovertido.getNumeroTermoFomentoParceria(),
				projetoDto.getNumeroTermoFomentoParceria());
		Assert.assertEquals(projetoCovertido.getArquivos().size(), projetoDto.getArquivos().size());
		Assert.assertEquals(projetoCovertido.getDuracao(), projetoDto.getDuracao());
		Assert.assertEquals(projetoCovertido.getAditamento(), projetoDto.getAditamento());
		Assert.assertEquals(projetoCovertido.getValorOutros(), projetoDto.getValorOutros());
		Assert.assertEquals(projetoCovertido.getValorProdutos(), projetoDto.getValorProdutos());
		Assert.assertEquals(projetoCovertido.getRecursosDisponiveis().size(),
				projetoDto.getRecursoDisponivelDto().size());
		Assert.assertEquals(projetoCovertido.getIdadeMinima(), projetoDto.getIdadeMinima());
		Assert.assertEquals(projetoCovertido.getIdadeMaxima(), projetoDto.getIdadeMaxima());
	}
}

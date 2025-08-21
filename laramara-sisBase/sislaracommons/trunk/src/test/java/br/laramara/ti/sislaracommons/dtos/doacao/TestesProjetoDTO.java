package br.laramara.ti.sislaracommons.dtos.doacao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesProjetoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projetodto_foi_construida_com_sucesso() {

		Long id = new Long(1222);
		String nome = "Projeto Alcoa Teste";
		String valor = "35000,00";
		String data = "32/12/2012";
		boolean ativo = true;

		LoteRecursoDTO loterecursoDto = new LoteRecursoDTO(id, new RecursoDTO(
				new Long(1222), "Bengala", false, "100,00"), "15", "2500,00");
		List<LoteRecursoDTO> lotesrecursos = new ArrayList<>();
		lotesrecursos.add(loterecursoDto);

		ProjetoDTO projetoDto = new ProjetoDTO();
		projetoDto.setId(id);
		projetoDto.setNome(nome);
		projetoDto.setValorTotal(valor);
		projetoDto.setValorProdutos(valor);
		projetoDto.setValorOutros(valor);
		projetoDto.setLoteRecursoDto(lotesrecursos);
		projetoDto.setDataInicial(data);
		projetoDto.setDataFinal(data);
		projetoDto.setAtivo(ativo);
		projetoDto.setSomaTotalProdutos(valor);

		Assert.assertEquals(projetoDto.getId(), id);
		Assert.assertEquals(projetoDto.getNome(), nome);
		Assert.assertEquals(projetoDto.getValorTotal(), valor);
		Assert.assertEquals(projetoDto.getValorProdutos(), valor);
		Assert.assertEquals(projetoDto.getValorOutros(), valor);
		Assert.assertEquals(projetoDto.getDataInicial(), data);
		Assert.assertEquals(projetoDto.getDataFinal(), data);
		Assert.assertEquals(projetoDto.getLoteRecursoDto().size(),
				lotesrecursos.size());
		Assert.assertEquals(projetoDto.isAtivo(), ativo);
		Assert.assertEquals(projetoDto.getSomaTotalProdutos(), valor);
	}
}
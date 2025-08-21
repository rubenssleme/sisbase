package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;

public class TestesFabricaDescricaoTipoAtendimento {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_descricaotipoatendimento_converte_objeto_de_dto_para_dominio() {
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = ContextoDescricaoTipoAtendimento
				.construirDescricaoTipoAtendimentoDTO();

		DescricaoTipoAtendimento descricaoTipoAtendimento = new FabricaDescricaoTipoAtendimento()
				.converterParaDominio(descricaoTipoAtendimentoDto);

		Assert.assertEquals(descricaoTipoAtendimento.getId(),
				descricaoTipoAtendimentoDto.getId());
		Assert.assertEquals(descricaoTipoAtendimento.getNome(),
				descricaoTipoAtendimentoDto.getNome());
		Assert.assertEquals(descricaoTipoAtendimento.getDescricao(),
				descricaoTipoAtendimentoDto.getDescrica());
		Assert.assertEquals(descricaoTipoAtendimento.getSigla(),
				descricaoTipoAtendimentoDto.getSigla());
		Assert.assertEquals(descricaoTipoAtendimento.getSetor().size(),
				descricaoTipoAtendimentoDto.getSetoresDto().size());
		Assert.assertEquals(descricaoTipoAtendimento.getTipoAtendimento()
				.getId(), descricaoTipoAtendimentoDto.getTipoAtendimentoDto()
				.getId());
		Assert.assertEquals(descricaoTipoAtendimento.getNomesGrupos().size(),
				descricaoTipoAtendimentoDto.getNomesGruposDto().size());
		Assert.assertEquals(descricaoTipoAtendimento.getModulos().size(),
				descricaoTipoAtendimentoDto.getModulosDto().size());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_descricaotipoatendimento_converte_objeto_de_dominio_para_dto() {
		DescricaoTipoAtendimento descricaoTipoAtendimento = ContextoDescricaoTipoAtendimento
				.fabricarComTodosOsDados();

		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = new FabricaDescricaoTipoAtendimento()
				.converterParaDTO(descricaoTipoAtendimento);

		Assert.assertEquals(descricaoTipoAtendimentoDto.getId(),
				descricaoTipoAtendimento.getId());
		Assert.assertEquals(descricaoTipoAtendimentoDto.getNome(),
				descricaoTipoAtendimento.getNome());
		Assert.assertEquals(descricaoTipoAtendimentoDto.getDescrica(),
				descricaoTipoAtendimento.getDescricao());
		Assert.assertEquals(descricaoTipoAtendimentoDto.getSigla(),
				descricaoTipoAtendimento.getSigla());
		Assert.assertEquals(descricaoTipoAtendimentoDto.getSetoresDto().size(),
				2);
		Assert.assertEquals(descricaoTipoAtendimentoDto.getTipoAtendimentoDto()
				.getId(), descricaoTipoAtendimento.getTipoAtendimento().getId());
		Assert.assertEquals(descricaoTipoAtendimentoDto.getNomesGruposDto()
				.size(), descricaoTipoAtendimento.getNomesGrupos().size());
		Assert.assertEquals(descricaoTipoAtendimentoDto.getModulosDto().size(),
				descricaoTipoAtendimento.getModulos().size());
	}
}

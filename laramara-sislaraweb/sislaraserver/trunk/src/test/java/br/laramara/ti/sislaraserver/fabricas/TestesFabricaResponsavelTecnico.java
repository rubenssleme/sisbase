package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.ResponsavelTecnicoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.ResponsavelTecnico;

public class TestesFabricaResponsavelTecnico {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_responsavel_tecnico_converte_objeto_de_dominio_para_dto() {
		ResponsavelTecnico responsavelTecnico = ContextoResponsavelTecnico.fabricarProjetoComTodosOsDados();

		ResponsavelTecnicoDTO responsavelTecnicoDtoCovertido = new FabricaResponsavelTecnico()
				.converterParaDTO(responsavelTecnico);

		Assert.assertEquals(responsavelTecnicoDtoCovertido.getId(), responsavelTecnico.getId());
		Assert.assertEquals(responsavelTecnicoDtoCovertido.getProfissionalDto().getId(),
				responsavelTecnico.getProfissional().getId());
		Assert.assertEquals(responsavelTecnicoDtoCovertido.isPrincipal(), responsavelTecnico.isPrincipal());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_responsavel_tecnico_converte_objeto_dto_para_dominio() {

		ResponsavelTecnicoDTO responsavelTecnicoDto = ContextoResponsavelTecnico.construirResponsavelTecnicoDTO();

		ResponsavelTecnico projetoCovertido = new FabricaResponsavelTecnico()
				.converterParaDominio(responsavelTecnicoDto);

		Assert.assertEquals(projetoCovertido.getId(), responsavelTecnicoDto.getId());
		Assert.assertEquals(projetoCovertido.getProfissional().getId(),
				responsavelTecnicoDto.getProfissionalDto().getId());
		Assert.assertEquals(projetoCovertido.isPrincipal(), responsavelTecnicoDto.isPrincipal());
	}
}

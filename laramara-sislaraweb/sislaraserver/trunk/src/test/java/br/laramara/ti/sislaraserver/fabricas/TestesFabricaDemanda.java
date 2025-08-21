package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.DemandaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.Demanda;

public class TestesFabricaDemanda {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_demanda_converte_objeto_de_dominio_para_dto() {

		Demanda demanda = ContextoDemanda.fabricarDemandaComTodosOsDados();

		DemandaDTO demandaDtoCovertido = new FabricaDemanda()
				.converterParaDTO(demanda);

		Assert.assertEquals(demandaDtoCovertido.getId(), demanda.getId());
		Assert.assertEquals(demandaDtoCovertido.getDataPrazoCaptacao(), demanda.getDataPrazoDeCaptacao());
		Assert.assertEquals(demandaDtoCovertido.getUsuarioDto()
				.getInformacaoEssencialDto().getNome(), demanda.getUsuario()
				.getInformacaoEssencial().getNome());
		Assert.assertEquals(demandaDtoCovertido.getPreCadastroDto()
				.getInformacaoEssencialDto().getNome(), demanda
				.getPreCadastro().getInformacaoEssencial().getNome());
		Assert.assertEquals(demandaDtoCovertido.getGrupoDto().getDescricao(),
				demanda.getGrupo().getDescricao());
		Assert.assertEquals(demandaDtoCovertido.isEstaNovo(), demanda.estaAguardando());
		Assert.assertEquals(demandaDtoCovertido.getValorTotalCaptado(), demanda.obterValorTotalCaptado());
		Assert.assertEquals(demandaDtoCovertido.getValorCartela(), demanda.obterValorCartela());
		Assert.assertEquals(demandaDtoCovertido.getValorSaldo(), demanda.obterValorSaldo());
		Assert.assertEquals(demandaDtoCovertido.getDataExpectativa(), demanda.getDataExpectativa());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_demanda_converte_objeto_dto_para_dominio() {

		DemandaDTO demandaDto = ContextoDemanda.construirDemandaDTO();

		Demanda demandaCovertido = new FabricaDemanda()
				.converterParaDominio(demandaDto);

		Assert.assertEquals(demandaCovertido.getId(), demandaDto.getId());
		Assert.assertEquals(demandaCovertido.getUsuario()
				.getInformacaoEssencial().getNome(), demandaDto.getUsuarioDto()
				.getInformacaoEssencialDto().getNome());
		Assert.assertEquals(demandaCovertido.getPreCadastro()
				.getInformacaoEssencial().getNome(), demandaDto
				.getPreCadastroDto().getInformacaoEssencialDto().getNome());
		Assert.assertEquals(demandaCovertido.getGrupo().getDescricao(),
				demandaDto.getGrupoDto().getDescricao());
		Assert.assertEquals(demandaCovertido.isCarteloDeSelos(), demandaDto.isCartelaDeSelos());
		Assert.assertEquals(demandaCovertido.getDataExpectativa(), demandaDto.getDataExpectativa());
	}
}

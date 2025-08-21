package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AcaoCondutaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.AcaoConduta;

public class TestesFabricaAcaoConduta {
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_acao_conduta_converte_objeto_de_dominio_para_dto() {

		AcaoConduta acaoConduta = ContextoAcaoConduta.fabricarAcaoCondutaComTodosOsDados();

		AcaoCondutaDTO acaoCondutaDtoCovertido = new FabricaAcaoConduta().converterParaDTO(acaoConduta);

		Assert.assertEquals(acaoCondutaDtoCovertido.getId(), acaoConduta.getId());
		Assert.assertEquals(acaoCondutaDtoCovertido.getGrupoDto().getId(), acaoConduta.getGrupo().getId());
		Assert.assertEquals(acaoCondutaDtoCovertido.getDataProcessamento(), acaoConduta.getDataProcessamento());
		Assert.assertEquals(acaoCondutaDtoCovertido.getTipoAcaoCondutaDto().toString(),
				acaoConduta.getTipoAcaoConduta().toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_acao_conduta_converte_objeto_dto_para_dominio() {
		AcaoCondutaDTO acaoCondutaDto = ContextoAcaoConduta.fabricarAcoesCondutaDto();

		AcaoConduta acaoCondutaCovertido = new FabricaAcaoConduta().converterParaDominio(acaoCondutaDto);

		Assert.assertEquals(acaoCondutaCovertido.getId(), acaoCondutaDto.getId());
		Assert.assertEquals(acaoCondutaCovertido.getGrupo().getId(), acaoCondutaDto.getGrupoDto().getId());
		Assert.assertEquals(acaoCondutaCovertido.getTipoAcaoConduta().toString(),
				acaoCondutaDto.getTipoAcaoCondutaDto().toString());
	}
}

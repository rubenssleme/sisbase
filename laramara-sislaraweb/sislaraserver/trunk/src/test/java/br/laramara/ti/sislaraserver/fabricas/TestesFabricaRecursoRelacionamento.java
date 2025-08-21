package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.RecursoRelacionamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.RecursoRelacionamento;

public class TestesFabricaRecursoRelacionamento {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_recurso_relacionamento_converte_objeto_de_dto_para_dominio() {
		RecursoRelacionamentoDTO recursoRelacionamentoDto = ContextoRecursoRelacionamento
				.construirRecursoRelacionamentoDTO();

		RecursoRelacionamento recursoRelacionamento = new FabricaRecursoRelacionamento()
				.converterParaDominio(recursoRelacionamentoDto);

		Assert.assertEquals(recursoRelacionamento.getId(), recursoRelacionamentoDto.getId());
		Assert.assertEquals(recursoRelacionamento.getRecurso().getId(),
				recursoRelacionamentoDto.getRecursoDto().getId());
		Assert.assertEquals(recursoRelacionamento.getPossuiNecessita().toString(),
				recursoRelacionamentoDto.getPossuiNecessitaDto().toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_recurso_relacionamento_converte_objeto_de_dominio_para_dto() {
		RecursoRelacionamento recursoRelacionamento = ContextoRecursoRelacionamento.construirRecursoRelacionamento();

		RecursoRelacionamentoDTO recursoRelacionamentoDto = new FabricaRecursoRelacionamento()
				.converterParaDTO(recursoRelacionamento);

		Assert.assertEquals(recursoRelacionamentoDto.getId(), recursoRelacionamento.getId());
		Assert.assertEquals(recursoRelacionamentoDto.getRecursoDto().getId(),
				recursoRelacionamento.getRecurso().getId());
		Assert.assertEquals(recursoRelacionamentoDto.getPossuiNecessitaDto().toString(),
				recursoRelacionamento.getPossuiNecessita().toString());
	}
}

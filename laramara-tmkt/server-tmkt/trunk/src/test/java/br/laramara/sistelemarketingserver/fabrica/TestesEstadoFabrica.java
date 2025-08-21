package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.EstadoDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.ContextoEstado;
import br.laramara.sistelemarketingserver.dominio.Estado;
import br.laramara.sistelemarketingserver.fabricas.EstadoFabrica;

public class TestesEstadoFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_estado_converte_objeto_de_dominio_para_dto() {
		Estado estado = ContextoEstado.fabricarEstado();

		EstadoDTO estadoDtoCovertido = new EstadoFabrica().converterParaDTO(estado);

		Assert.assertEquals(estadoDtoCovertido.getId(), estado.getId());
		Assert.assertEquals(estadoDtoCovertido.getDescricao(), estado.getDescricao());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_nivel_converte_objeto_dto_para_dominio() {
		EstadoDTO estadoDto = ContextoEstado.fabricarEstadoDto();

		Estado estadoCovertido = new EstadoFabrica().converterParaDominio(estadoDto);

		Assert.assertEquals(estadoCovertido.getId(), estadoDto.getId());
		Assert.assertEquals(estadoCovertido.getDescricao(), estadoDto.getDescricao());
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoEdicaoModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.ResultadoEdicaoModuloPeriodo;

public class TestesFabricaResultadoEdicaoModuloPeriodo {
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_resultado_edicao_modulo_periodo_dominio_para_dto() {
		ResultadoEdicaoModuloPeriodo resultadoEdicaoModuloPeriodo = new ResultadoEdicaoModuloPeriodo();
		resultadoEdicaoModuloPeriodo.efetuadoComSucesso(ContextoModuloPeriodo.fabricarComTodosOsDadosInformatica());
		
		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDTO = new FabricaEdicaoModuloPeriodo()
				.converterParaDTO(resultadoEdicaoModuloPeriodo);

		Assert.assertTrue(resultadoEdicaoModuloPeriodoDTO.sucesso());
		Assert.assertEquals(((Identificavel) resultadoEdicaoModuloPeriodoDTO.obterObjetoDtoEditado()).getId(),
				resultadoEdicaoModuloPeriodo.getModuloPeriodo().getId());
		Assert.assertTrue(resultadoEdicaoModuloPeriodoDTO.obterMensagens().contains("Dados armazenados com sucesso."));
	}
}

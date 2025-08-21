package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoGeracaoAtendimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.ResultadoGeracaoAtendimento;

public class TestesFabricaResultadoGeracaoAtendimento {
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_resultado_geracao_atendimento_grupo_converte_objeto_de_dominio_para_dto() {

		AtendimentoGrupo atendimentoGrupo = ContextoAtendimentoGrupo.fabricarAtendimentoComTodosOsDados();
		ModuloPeriodo moduloPeriodo = ContextoModuloPeriodo.fabricarComTodosOsDadosInformatica();
		Grupo grupo = ContextoGrupo.fabricarGrupoComTodosOsDados();
		
		ResultadoGeracaoAtendimento resultadoEdicaoAtendimento = new ResultadoGeracaoAtendimento();
		resultadoEdicaoAtendimento.efetuadoComSucesso(grupo, moduloPeriodo, atendimentoGrupo);

		ResultadoGeracaoAtendimentoDTO resultadoEdicaoAtendimentoDto = new FabricaResultadoGeracaoAtendimento()
				.converterParaDTO(resultadoEdicaoAtendimento);

		Assert.assertEquals(
				((AtendimentoGrupoDTO) resultadoEdicaoAtendimentoDto.obterObjetoDtoEditado()).getData(),
				atendimentoGrupo.getData());
		Assert.assertEquals(
				((ModuloPeriodoDTO) resultadoEdicaoAtendimentoDto.getModuloPeriodoDTO()).getId(),
				moduloPeriodo.getId());
		Assert.assertEquals(
				((GrupoDTO) resultadoEdicaoAtendimentoDto.getGrupoDto()).getId(),
				grupo.getId());
	}
}

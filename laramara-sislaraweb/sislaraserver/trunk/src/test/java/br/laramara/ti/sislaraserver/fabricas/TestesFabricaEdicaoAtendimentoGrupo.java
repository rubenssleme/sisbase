package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoEdicaoAtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.grupo.ResultadoEdicaoAtendimentoGrupo;

public class TestesFabricaEdicaoAtendimentoGrupo {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_resultado_edicao_atendimento_grupo_converte_objeto_de_dto_para_dominio() {

		AtendimentoGrupo atendimentoGrupo = ContextoAtendimentoGrupo.fabricarAtendimentoComTodosOsDados();

		ResultadoEdicaoAtendimentoGrupo resultadoEdicaoAtendimentoGrupo = new ResultadoEdicaoAtendimentoGrupo();
		resultadoEdicaoAtendimentoGrupo.efetuadoComSucesso(atendimentoGrupo);

		ResultadoEdicaoAtendimentoGrupoDTO resultadoEdicaoAtendimentoGrupoDto = new FabricaEdicaoAtendimentoGrupo()
				.converterParaDTO(resultadoEdicaoAtendimentoGrupo);

		Assert.assertEquals(
				((AtendimentoGrupoDTO) resultadoEdicaoAtendimentoGrupoDto.obterObjetoDtoEditado()).getData(),
				atendimentoGrupo.getData());
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoGeracaoDemandaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoGeracaoDemanda;

public class TestesFabricaEspecificacaoGeracaoDemanda {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_especificacao_geracao_demanda_converte_objeto_de_dto_para_dominio() {
		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDto = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();
		especificacaoGeracaoDemandaDto.setUsuariosDto(ContextoUsuario.construirUsuarioDTO());

		EspecificacaoGeracaoDemanda especificacaoGeracaoDemanda = new FabricaEspecificacaoGeracaoDemanda()
				.converterParaDominio(especificacaoGeracaoDemandaDto);

		Assert.assertEquals(especificacaoGeracaoDemanda.getUsuario().getId(),
				especificacaoGeracaoDemandaDto.getUsuariosDto().getId());
		Assert.assertEquals(
				especificacaoGeracaoDemanda.getPreCadastro().getId(),
				especificacaoGeracaoDemandaDto.getPreCadastrosDto().getId());
		Assert.assertEquals(especificacaoGeracaoDemanda.getProjeto().getId(),
				especificacaoGeracaoDemandaDto.getProjetoDto().getId());
		Assert.assertEquals(especificacaoGeracaoDemanda.getRecursos().get(0).getId(),
				especificacaoGeracaoDemandaDto.getRecursosDto().get(0).getId());
		Assert.assertEquals(especificacaoGeracaoDemanda.getGrupo().getId(),
				especificacaoGeracaoDemandaDto.getGrupoDto().getId());
		Assert.assertEquals(especificacaoGeracaoDemanda.isCartelaDeSelos(),
				especificacaoGeracaoDemandaDto.isCartelaDeSelos());
	}
}

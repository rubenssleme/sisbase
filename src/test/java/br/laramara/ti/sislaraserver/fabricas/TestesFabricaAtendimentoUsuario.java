package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoUsuario;

public class TestesFabricaAtendimentoUsuario {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_atendimento_usuario_converte_objeto_de_dominio_para_dto() {
		AtendimentoUsuario atendimentoUsuario = ContextoAtendimentoUsuario
				.fabricarAtendimentoUsuarioComTodosOsDados();

		AtendimentoUsuarioDTO atendimentoUsuarioDtoCovertido = new FabricaAtendimentoUsuario()
				.converterParaDTO(atendimentoUsuario);

		Assert.assertEquals(atendimentoUsuarioDtoCovertido.getId(),
				atendimentoUsuario.getId());
		Assert.assertEquals(atendimentoUsuarioDtoCovertido.getUsuarioDto()
				.getId(), atendimentoUsuario.getUsuario().getId());
		Assert.assertEquals(atendimentoUsuarioDtoCovertido
				.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
				atendimentoUsuario.getInformacaoAtendimento().getFrequencia()
						.toString());
		Assert.assertEquals(atendimentoUsuarioDtoCovertido
				.getInformacaoAtendimentoDto().getDescricao(),
				atendimentoUsuario.getInformacaoAtendimento().getDescricao());
		Assert.assertEquals(atendimentoUsuarioDtoCovertido
				.getInformacaoAtendimentoDto().getJustificativa(),
				atendimentoUsuario.getInformacaoAtendimento()
						.getJustificativa());
		
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_atendimento_usuario_converte_objeto_de_dto_para_dominio() {
		AtendimentoUsuarioDTO atendimentoUsuarioDto = ContextoAtendimentoUsuario
				.construirAtendimentoUsuarioDTO();

		AtendimentoUsuario atendimentoUsuarioCovertido = new FabricaAtendimentoUsuario()
				.converterParaDominio(atendimentoUsuarioDto);

		Assert.assertEquals(atendimentoUsuarioCovertido.getId(),
				atendimentoUsuarioDto.getId());
		Assert.assertEquals(atendimentoUsuarioCovertido.getUsuario().getId(),
				atendimentoUsuarioDto.getUsuarioDto().getId());
		Assert.assertEquals(atendimentoUsuarioCovertido
				.getInformacaoAtendimento().getFrequencia().toString(),
				atendimentoUsuarioDto.getInformacaoAtendimentoDto()
						.getFrequenciaDto().toString());
		Assert.assertEquals(atendimentoUsuarioCovertido
				.getInformacaoAtendimento().getDescricao(),
				atendimentoUsuarioDto.getInformacaoAtendimentoDto()
						.getDescricao());
		Assert.assertEquals(atendimentoUsuarioCovertido
				.getInformacaoAtendimento().getJustificativa(),
				atendimentoUsuarioDto.getInformacaoAtendimentoDto()
						.getJustificativa());
	}
}

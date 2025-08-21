package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;

public class TestesFabricaAtendimentoGrupo {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_atendimento_grupo_converte_objeto_de_dominio_para_dto() {
		AtendimentoGrupo atendimento = ContextoAtendimentoGrupo
				.fabricarAtendimentoComTodosOsDados();

		AtendimentoGrupoDTO atendimentoDtoCovertido = new FabricaAtendimentoGrupo()
				.converterParaDTO(atendimento);

		Assert.assertEquals(atendimentoDtoCovertido.getId(),
				atendimento.getId());
		Assert.assertEquals(atendimentoDtoCovertido.getVersao(),
				atendimento.getVersao());
		Assert.assertEquals(atendimentoDtoCovertido.getData(),
				atendimento.getData());
		Assert.assertEquals(atendimentoDtoCovertido.getHorarioDto()
				.getHoraInicio(), atendimento.getHorario().getHoraInicio());
		Assert.assertEquals(atendimentoDtoCovertido.getHorarioDto()
				.getHoraTermino(), atendimento.getHorario().getHoraTermino());
		Assert.assertEquals(atendimentoDtoCovertido.getDescricao(),
				atendimento.getDescricao());
		Assert.assertEquals(atendimentoDtoCovertido.getInterdisciplinar(),
				atendimento.getInterdisciplinar());
		Assert.assertEquals(atendimentoDtoCovertido
				.getAtendimentosUsuariosDto().size(), atendimento
				.getAtendimentosUsuarios().size());
		Assert.assertEquals(atendimentoDtoCovertido
				.getAtendimentosPreCadastroDto().size(), atendimento
				.getAtendimentosPreCadastros().size());
		Assert.assertEquals(atendimentoDtoCovertido
				.getAtendimentosProfissionaisDto().size(), atendimento
				.getAtendimentosProfissionais().size());
		Assert.assertEquals(atendimentoDtoCovertido.getPessoasSemCadastro(), atendimento.getPessoasSemCadastro());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_atendimento_grupo_converte_objeto_dto_para_dominio() {
		AtendimentoGrupoDTO atendimentoDto = ContextoAtendimentoGrupo
				.construirAtendimentoDTO();

		AtendimentoGrupo atendimentoCovertido = new FabricaAtendimentoGrupo()
				.converterParaDominio(atendimentoDto);

		Assert.assertEquals(atendimentoCovertido.getId(),
				atendimentoDto.getId());
		Assert.assertEquals(atendimentoCovertido.getData(),
				atendimentoDto.getData());
		Assert.assertEquals(atendimentoCovertido.getHorario().getHoraInicio(),
				atendimentoDto.getHorarioDto().getHoraInicio());
		Assert.assertEquals(atendimentoCovertido.getHorario().getHoraTermino(),
				atendimentoDto.getHorarioDto().getHoraTermino());
		Assert.assertEquals(atendimentoCovertido.getDescricao(),
				atendimentoDto.getDescricao());
		Assert.assertEquals(atendimentoCovertido.getInterdisciplinar(),
				atendimentoDto.getInterdisciplinar());
		Assert.assertEquals(atendimentoCovertido.getAtendimentosUsuarios()
				.size(), atendimentoDto.getAtendimentosUsuariosDto().size());
		Assert.assertEquals(atendimentoCovertido.getAtendimentosPreCadastros()
				.size(), atendimentoDto.getAtendimentosPreCadastroDto().size());
		Assert.assertEquals(atendimentoCovertido.getAtendimentosProfissionais()
				.size(), atendimentoDto.getAtendimentosProfissionaisDto()
				.size());
		Assert.assertEquals(atendimentoCovertido.getPessoasSemCadastro(), atendimentoDto.getPessoasSemCadastro());
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;

public class TestesFabricaAtendimentoIndividual {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_atendimento_individual_converte_objeto_de_dominio_para_dto() {
		AtendimentoIndividual atendimentoIndividual = ContextoAtendimentoIndividual
				.fabricarComTodosOsDados();

		AtendimentoIndividualDTO atendimentoIndividualDtoCovertido = new FabricaAtendimentoIndividual().converterParaDTO(
						atendimentoIndividual);

		Assert.assertEquals(atendimentoIndividualDtoCovertido.getId(),
				atendimentoIndividual.getId());
		Assert.assertEquals(atendimentoIndividualDtoCovertido.getData(),
				atendimentoIndividual.getData());
		Assert.assertEquals(atendimentoIndividualDtoCovertido
				.getDescricaoTipoAtendimentoDto().getId(),
				atendimentoIndividual.getDescricaoTipoAtendimento().getId());
		Assert.assertEquals(atendimentoIndividualDtoCovertido.getHorarioDto()
				.getHoraInicio(), atendimentoIndividual.getHorario()
				.getHoraInicio());
		Assert.assertEquals(atendimentoIndividualDtoCovertido
				.getInformacaoAtendimentoDto().getId(), atendimentoIndividual
				.getInformacaoAtendimento().getId());
		Assert.assertEquals(
				atendimentoIndividualDtoCovertido.getInterdisciplinar(),
				atendimentoIndividual.getInterdisciplinar());
		Assert.assertEquals(atendimentoIndividualDtoCovertido.getModuloDto()
				.getId(), atendimentoIndividual.getModulo().getId());
		Assert.assertEquals(atendimentoIndividualDtoCovertido.getSetorDto()
				.toString(), atendimentoIndividual.getSetor().toString());
		Assert.assertEquals(atendimentoIndividualDtoCovertido
				.getStatusAtendimentoDto().toString(), atendimentoIndividual
				.getStatusAtendimento().toString());
		Assert.assertEquals(atendimentoIndividualDtoCovertido
				.getLocalAtendimentoDto().getId(), atendimentoIndividual
				.getLocalAtendimento().getId());
		Assert.assertEquals(atendimentoIndividualDtoCovertido
				.getParticipacaoDto().toString(), atendimentoIndividual
				.getParticipacao().toString());
		Assert.assertEquals(atendimentoIndividualDtoCovertido
				.getAtendimentosProfissionalDto().size(), atendimentoIndividual
				.getAtendimentosProfissionais().size());
		Assert.assertEquals(atendimentoIndividualDtoCovertido
				.getAtendimentosComunidadeDto().size(), atendimentoIndividual
				.getAtendimentosComunidade().size());
		Assert.assertEquals(atendimentoIndividualDtoCovertido.getPrimeiraVezOuRetornoDto().toString(),
				atendimentoIndividual.getPrimeiraVezOuRetorno().toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_atendimento_individual_converte_objeto_de_dto_para_dominio() {
		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosPreCadastro();
		atendimentoIndividualDto.setUsuarioDto(null);
		atendimentoIndividualDto.setPreCadastroDto(null);

		AtendimentoIndividual atendimentoIndividualCovertido = new FabricaAtendimentoIndividual().converterParaDominio(
						atendimentoIndividualDto);

		Assert.assertEquals(atendimentoIndividualCovertido.getId(),
				atendimentoIndividualDto.getId());
		Assert.assertEquals(atendimentoIndividualCovertido.getData(),
				atendimentoIndividualDto.getData());
		Assert.assertEquals(atendimentoIndividualCovertido
				.getDescricaoTipoAtendimento().getId(),
				atendimentoIndividualDto.getDescricaoTipoAtendimentoDto()
						.getId());
		Assert.assertEquals(atendimentoIndividualCovertido.getHorario()
				.getHoraInicio(), atendimentoIndividualDto.getHorarioDto()
				.getHoraInicio());
		Assert.assertEquals(atendimentoIndividualCovertido
				.getInformacaoAtendimento().getId(), atendimentoIndividualDto
				.getInformacaoAtendimentoDto().getId());
		Assert.assertEquals(
				atendimentoIndividualCovertido.getInterdisciplinar(),
				atendimentoIndividualDto.getInterdisciplinar());
		Assert.assertEquals(atendimentoIndividualCovertido.getModulo().getId(),
				atendimentoIndividualDto.getModuloDto().getId());
		Assert.assertEquals(atendimentoIndividualCovertido.getSetor()
				.toString(), atendimentoIndividualDto.getSetorDto().toString());
		Assert.assertEquals(atendimentoIndividualCovertido
				.getStatusAtendimento().toString(), atendimentoIndividualDto
				.getStatusAtendimentoDto().toString());
		Assert.assertEquals(atendimentoIndividualCovertido
				.getLocalAtendimento().getId(), atendimentoIndividualDto
				.getLocalAtendimentoDto().getId());
		Assert.assertEquals(atendimentoIndividualCovertido.getParticipacao()
				.toString(), atendimentoIndividualDto.getParticipacaoDto()
				.toString());
		Assert.assertEquals(atendimentoIndividualCovertido
				.getAtendimentosProfissionais().size(),
				atendimentoIndividualDto.getAtendimentosProfissionalDto()
						.size());
		Assert.assertEquals(atendimentoIndividualCovertido
				.getAtendimentosComunidade().size(), atendimentoIndividualDto
				.getAtendimentosComunidadeDto().size());
		Assert.assertEquals(atendimentoIndividualCovertido.getPrimeiraVezOuRetorno().toString(),
				atendimentoIndividualDto.getPrimeiraVezOuRetornoDto().toString());
	}
}

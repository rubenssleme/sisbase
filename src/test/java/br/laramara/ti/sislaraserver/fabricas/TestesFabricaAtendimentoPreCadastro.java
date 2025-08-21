package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoPreCadastroDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoPreCadastro;

public class TestesFabricaAtendimentoPreCadastro {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_atendimento_pre_cadastro_converte_objeto_de_dominio_para_dto() {
		AtendimentoPreCadastro atendimentoPreCadastro = ContextoAtendimentoPreCadastro
				.fabricarAtendimentoPreCadastroComTodosOsDados();

		AtendimentoPreCadastroDTO atendimentoPreCadastroDtoCovertido = new FabricaAtendimentoPreCadastro()
				.converterParaDTO(atendimentoPreCadastro);

		Assert.assertEquals(atendimentoPreCadastroDtoCovertido.getId(),
				atendimentoPreCadastro.getId());
		Assert.assertEquals(atendimentoPreCadastroDtoCovertido
				.getPreCadastroDto().getId(), atendimentoPreCadastro
				.getPreCadastro().getId());
		Assert.assertEquals(atendimentoPreCadastroDtoCovertido
				.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
				atendimentoPreCadastro.getInformacaoAtendimento()
						.getFrequencia().toString());
		Assert.assertEquals(atendimentoPreCadastroDtoCovertido
				.getInformacaoAtendimentoDto().getDescricao(),
				atendimentoPreCadastro.getInformacaoAtendimento()
						.getDescricao());
		Assert.assertEquals(atendimentoPreCadastroDtoCovertido
				.getInformacaoAtendimentoDto().getJustificativa(),
				atendimentoPreCadastro.getInformacaoAtendimento()
						.getJustificativa());
		Assert.assertEquals(atendimentoPreCadastroDtoCovertido
				.getInstituicaoDto().getId(), atendimentoPreCadastro
				.getInstituicao().getId());
		Assert.assertEquals(atendimentoPreCadastroDtoCovertido.getDreCefaiDto()
				.getId(), atendimentoPreCadastro.getDreCefai().getId());
		Assert.assertEquals(atendimentoPreCadastroDtoCovertido
				.getDiretoriaEnsinoDto().getId(), atendimentoPreCadastro
				.getDiretoriaEnsino().getId());
		Assert.assertEquals(atendimentoPreCadastroDtoCovertido.getNomeOrigem(),
				atendimentoPreCadastro.getNomeOrigem());
		Assert.assertEquals(atendimentoPreCadastroDtoCovertido
				.getTipoVinculoDto().getId(), atendimentoPreCadastro
				.getTipoVinculo().getId());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_atendimento_pre_cadastro_converte_objeto_de_dto_para_dominio() {
		AtendimentoPreCadastroDTO atendimentoPreCadastroDto = ContextoAtendimentoPreCadastro
				.construirAtendimentoPreCadastroDTO();

		AtendimentoPreCadastro atendimentoPreCadastroCovertido = new FabricaAtendimentoPreCadastro()
				.converterParaDominio(atendimentoPreCadastroDto);

		Assert.assertEquals(atendimentoPreCadastroCovertido.getId(),
				atendimentoPreCadastroDto.getId());
		Assert.assertEquals(atendimentoPreCadastroCovertido.getPreCadastro()
				.getId(), atendimentoPreCadastroDto.getPreCadastroDto().getId());
		Assert.assertEquals(atendimentoPreCadastroCovertido
				.getInformacaoAtendimento().getFrequencia().toString(),
				atendimentoPreCadastroDto.getInformacaoAtendimentoDto()
						.getFrequenciaDto().toString());
		Assert.assertEquals(atendimentoPreCadastroCovertido
				.getInformacaoAtendimento().getDescricao(),
				atendimentoPreCadastroDto.getInformacaoAtendimentoDto()
						.getDescricao());
		Assert.assertEquals(atendimentoPreCadastroCovertido
				.getInformacaoAtendimento().getJustificativa(),
				atendimentoPreCadastroDto.getInformacaoAtendimentoDto()
						.getJustificativa());
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;

public class FabricaAtendimentoIndividual extends
		FabricaRecursiva<AtendimentoIndividualDTO, AtendimentoIndividual> {

	@Override
	public AtendimentoIndividualDTO converterParaDTO(
			AtendimentoIndividual atendimentoIndividual) {
		AtendimentoIndividualDTO atendimentoIndividualDto = null;
		if (atendimentoIndividual != null) {
			atendimentoIndividualDto = new AtendimentoIndividualDTO();
			atendimentoIndividualDto.setId(atendimentoIndividual.getId());
			atendimentoIndividualDto.setData(atendimentoIndividual.getData());
			atendimentoIndividualDto
					.setDescricaoTipoAtendimentoDto(new FabricaDescricaoTipoAtendimento()
							.converterParaDTO(atendimentoIndividual
									.getDescricaoTipoAtendimento()));
			atendimentoIndividualDto.setHorarioDto(new FabricaHorario()
					.converterParaDTO(atendimentoIndividual.getHorario()));
			atendimentoIndividualDto
					.setInformacaoAtendimentoDto(new FabricaInformacaoAtendimento()
							.converterParaDTO(atendimentoIndividual
									.getInformacaoAtendimento()));
			atendimentoIndividualDto.setInterdisciplinar(atendimentoIndividual
					.getInterdisciplinar());
			atendimentoIndividualDto.setModuloDto(new FabricaModulo()
					.converterParaDTO(atendimentoIndividual.getModulo()));
			atendimentoIndividualDto.setPreCadastroDto(new FabricaPreCadastro()
					.converterParaDTO(atendimentoIndividual.getPreCadastro()));
			atendimentoIndividualDto.setUsuarioDto(new FabricaUsuario()
					.converterParaDTO(atendimentoIndividual.getUsuario()));
			atendimentoIndividualDto.setSetorDto(new FabricaSetor()
					.converterParaDTO(atendimentoIndividual.getSetor()));
			atendimentoIndividualDto
					.setStatusAtendimentoDto(new FabricaStatusAtendimento()
							.converterParaDTO(atendimentoIndividual
									.getStatusAtendimento()));
			atendimentoIndividualDto
					.setAtendimentosProfissionalDto(new FabricaAtendimentoProfissional()
							.converterParaDTO(atendimentoIndividual
									.getAtendimentosProfissionais()));
			atendimentoIndividualDto
					.setAtendimentosComunidadeDto(new FabricaAtendimentoComunidade()
							.converterParaDTO(atendimentoIndividual
									.getAtendimentosComunidade()));
			atendimentoIndividualDto
					.setLocalAtendimentoDto(new FabricaLocalAtendimento()
							.converterParaDTO(atendimentoIndividual
									.getLocalAtendimento()));
			atendimentoIndividualDto.setPrimeiraVezOuRetornoDto(
					new FabricaStatus().converterParaDTO(atendimentoIndividual.getPrimeiraVezOuRetorno()));
			atendimentoIndividualDto
					.setArquivos(new FabricaArquivo().converterParaDTO(atendimentoIndividual.getArquivos()));
			atendimentoIndividualDto
					.setIdadeDoUsuarioOuPreCadastro(atendimentoIndividual.obterIdadeDoUsuarioOuPreCadastro());
			atendimentoIndividualDto.setAcoesDeCondutasDto(
					new FabricaAcaoConduta().converterParaDTO(atendimentoIndividual.getAcoesDeCondutas()));
			atendimentoIndividualDto
					.setOpcaoIntegracaoDto(new FabricaOpcaoIntegracao().converterParaDTO(atendimentoIndividual.getOpcaoIntegracao()));
			atendimentoIndividualDto.setMotivoNaoIntegracao(atendimentoIndividual.getMotivoNaoIntegracao());
			atendimentoIndividualDto
					.setDiscussaoCasoSimNaoDto(new FabricaSimNao().converterParaDTO(atendimentoIndividual.getDiscussaoCasoSimNao()));
			atendimentoIndividualDto.setResumoIntegracao(atendimentoIndividual.getResumoIntegracao());
			atendimentoIndividualDto.setRelatorioAvfunNaoConfeccionado(atendimentoIndividual.isRelatorioAvfunNaoConfeccionado());
			atendimentoIndividualDto.setMotivoNaoConfeccaoRelatorioAvfun(atendimentoIndividual.getMotivoNaoConfeccaoRelatorioAvfun());
		}
		return atendimentoIndividualDto;
	}

	@Override
	public AtendimentoIndividual converterParaDominio(
			AtendimentoIndividualDTO atendimentoIndividualDto,
			AtendimentoIndividual atendimentoIndividual) {
		if (atendimentoIndividualDto != null) {
			if (atendimentoIndividual == null) {
				atendimentoIndividual = new AtendimentoIndividual();
			}
			atendimentoIndividual.setId(atendimentoIndividualDto.getId());
			atendimentoIndividual.setData(atendimentoIndividualDto.getData());
			atendimentoIndividual
					.setDescricaoTipoAtendimento(new FabricaDescricaoTipoAtendimento()
							.converterParaDominio(atendimentoIndividualDto
									.getDescricaoTipoAtendimentoDto()));
			atendimentoIndividual.setHorario(new FabricaHorario()
					.converterParaDominio(atendimentoIndividualDto
							.getHorarioDto()));
			atendimentoIndividual.setInterdisciplinar(atendimentoIndividualDto
					.getInterdisciplinar());
			atendimentoIndividual.setModulo(new FabricaModulo()
					.converterParaDominio(atendimentoIndividualDto
							.getModuloDto()));
			atendimentoIndividual.setUsuario(new FabricaUsuario()
					.converterParaDominio(
							atendimentoIndividualDto.getUsuarioDto(),
							atendimentoIndividual.getUsuario()));
			atendimentoIndividual.setPreCadastro(new FabricaPreCadastro()
					.converterParaDominio(
							atendimentoIndividualDto.getPreCadastroDto(),
							atendimentoIndividual.getPreCadastro()));
			atendimentoIndividual.setSetor(new FabricaSetor()
					.converterParaDominio(atendimentoIndividualDto
							.getSetorDto()));
			atendimentoIndividual
					.setStatusAtendimento(new FabricaStatusAtendimento()
							.converterParaDominio(atendimentoIndividualDto
									.getStatusAtendimentoDto()));
			atendimentoIndividual
					.setAtendimentosProfissionais(new FabricaAtendimentoProfissional()
							.converterParaDominio(atendimentoIndividualDto
									.getAtendimentosProfissionalDto()));
			atendimentoIndividual
					.setAtendimentosComunidade(new FabricaAtendimentoComunidade()
							.converterParaDominio(atendimentoIndividualDto
									.getAtendimentosComunidadeDto()));
			atendimentoIndividual
					.setLocalAtendimento(new FabricaLocalAtendimento()
							.converterParaDominio(atendimentoIndividualDto
									.getLocalAtendimentoDto()));
			atendimentoIndividual
					.setInformacaoAtendimento(new FabricaInformacaoAtendimento()
							.converterParaDominio(atendimentoIndividualDto
									.getInformacaoAtendimentoDto()));
			atendimentoIndividual.setPrimeiraVezOuRetorno(
					new FabricaStatus().converterParaDominio(atendimentoIndividualDto.getPrimeiraVezOuRetornoDto()));
			atendimentoIndividual
					.setArquivos(new FabricaArquivo().converterParaDominio(atendimentoIndividualDto.getArquivos()));
			atendimentoIndividual.setAcoesDeCondutas(new FabricaAcaoConduta().converterParaDominio(
					atendimentoIndividualDto.getAcoesDeCondutasDto(), atendimentoIndividual.getAcoesDeCondutas()));
			atendimentoIndividual.setOpcaoIntegracao(new FabricaOpcaoIntegracao()
					.converterParaDominio(atendimentoIndividualDto.getOpcaoIntegracaoDto()));
			atendimentoIndividual.setMotivoNaoIntegracao(atendimentoIndividualDto.getMotivoNaoIntegracao());
			atendimentoIndividual.setDiscussaoCasoSimNao(
					new FabricaSimNao().converterParaDominio(atendimentoIndividualDto.getDiscussaoCasoSimNaoDto()));
			atendimentoIndividual.setRelatorioAvfunNaoConfeccionado(atendimentoIndividualDto.isRelatorioAvfunNaoConfeccionado());
			atendimentoIndividual.setMotivoNaoConfeccaoRelatorioAvfun(atendimentoIndividualDto.getMotivoNaoConfeccaoRelatorioAvfun());
		}
		return atendimentoIndividual;
	}

	@Override
	public AtendimentoIndividual obterNovo() {
		return new AtendimentoIndividual();
	}
}

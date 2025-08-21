package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ProgramacaoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Programacao;

public class FabricaProgramacao extends FabricaRecursiva<ProgramacaoDTO, Programacao> {

	@Override
	public ProgramacaoDTO converterParaDTO(Programacao programacao) {
		ProgramacaoDTO programacaoDto = null;
		if (programacao != null) {
			programacaoDto = new ProgramacaoDTO();
			programacaoDto.setId(programacao.getId());
			programacaoDto.setAula(programacao.getAula());
			programacaoDto.setData(programacao.getData());
			programacaoDto.setTemaConteudo(programacao.getTemaConteudo());
			programacaoDto.setRecadoFamilia(programacao.getRecadoFamilia());
			programacaoDto.setEstrategias(programacao.getEstrategias());
			programacaoDto.setLocalAtendimentoDTO(new FabricaLocalAtendimento()
					.converterParaDTO(programacao.getLocalAtendimento()));
		}
		return programacaoDto;
	}

	@Override
	public Programacao converterParaDominio(ProgramacaoDTO programacaoDto,
			Programacao programacao) {

		if (programacaoDto != null) {
			if (programacao != null) {
				programacao = new Programacao();
			}
			programacao.setId(programacaoDto.getId());
			programacao.setAula(programacaoDto.getAula());
			programacao.setData(programacaoDto.getData());
			programacao.setTemaConteudo(programacaoDto.getTemaConteudo());
			programacao.setRecadoFamilia(programacaoDto.getRecadoFamilia());
			programacao.setEstrategias(programacaoDto.getEstrategias());
			programacao.setLocalAtendimento(new FabricaLocalAtendimento()
					.converterParaDominio(programacaoDto
							.getLocalAtendimentoDTO()));
		}
		return programacao;
	}

	@Override
	public Programacao obterNovo() {
		return new Programacao();
	}
}

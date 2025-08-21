package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.espera.EspecificacaoPesquisaEsperaDTO;
import br.laramara.ti.sislaraserver.dominio.espera.EspecificacaoPesquisaEspera;

public class FabricaEspecificacaoPesquisaEspera extends
		FabricaBase<EspecificacaoPesquisaEsperaDTO, EspecificacaoPesquisaEspera> {

	@Override
	public EspecificacaoPesquisaEsperaDTO converterParaDTO(
			EspecificacaoPesquisaEspera especificacaoPesquisaEspera) {
		return null;
	}

	@Override
	public EspecificacaoPesquisaEspera converterParaDominio(
			EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEsperaDto) {
		EspecificacaoPesquisaEspera especificacaoPesquisaEspera = new EspecificacaoPesquisaEspera();
		especificacaoPesquisaEspera
				.setDescricaoTipoAtendimento(new FabricaDescricaoTipoAtendimento()
						.converterParaDominio(especificacaoPesquisaEsperaDto
								.obterDescricaoTipoAtendimentoDto()));
		especificacaoPesquisaEspera.setModulo(new FabricaModulo()
				.converterParaDominio(especificacaoPesquisaEsperaDto
						.obterModuloDto()));
		especificacaoPesquisaEspera.setNomeGrupo(new FabricaNomeGrupo()
				.converterParaDominio(especificacaoPesquisaEsperaDto
						.getNomeGrupo()));
		especificacaoPesquisaEspera.setSetor(new FabricaSetor()
				.converterParaDominio(especificacaoPesquisaEsperaDto
						.obterSetorDto()));
		especificacaoPesquisaEspera.setStatusEspera(new FabricaStatusEspera()
				.converterParaDominio(especificacaoPesquisaEsperaDto
						.obterStatusEsperaDto()));
		especificacaoPesquisaEspera.setTipoEspera(new FabricaTipoEspera()
				.converterParaDominio(especificacaoPesquisaEsperaDto
						.obterTipoEsperaDto()));
		especificacaoPesquisaEspera
				.setDataInicio(especificacaoPesquisaEsperaDto.getDataInicio());
		especificacaoPesquisaEspera
				.setDataTermino(especificacaoPesquisaEsperaDto.getDataTermino());
		especificacaoPesquisaEspera.setProntuario(especificacaoPesquisaEsperaDto.getProntuario());
		especificacaoPesquisaEspera.setRg(especificacaoPesquisaEsperaDto.getRg());
		especificacaoPesquisaEspera.setInteresse(especificacaoPesquisaEsperaDto.getInteresse());
		especificacaoPesquisaEspera.setLmLigou(especificacaoPesquisaEsperaDto.getLmLigou());
		especificacaoPesquisaEspera.setPendencias(especificacaoPesquisaEsperaDto.getPendencias());
		return especificacaoPesquisaEspera;
	}
}

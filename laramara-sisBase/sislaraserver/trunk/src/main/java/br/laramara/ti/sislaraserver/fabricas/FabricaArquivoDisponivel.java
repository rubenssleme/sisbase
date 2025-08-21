package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.ArquivoDisponivelDTO;
import br.laramara.ti.sislaraserver.dominio.ArquivoDisponivel;

public class FabricaArquivoDisponivel extends FabricaBase<ArquivoDisponivelDTO, ArquivoDisponivel> {

	@Override
	public ArquivoDisponivel converterParaDominio(ArquivoDisponivelDTO arquivoDisponivelDto) {
		return null;
	}

	@Override
	public ArquivoDisponivelDTO converterParaDTO(ArquivoDisponivel arquivoDisponivel) {
		ArquivoDisponivelDTO arquivoDisponivelDto = new ArquivoDisponivelDTO();
		arquivoDisponivelDto.setDataAtendimento(arquivoDisponivel.getDataAtendimento());
		arquivoDisponivelDto.setHoraInicio(arquivoDisponivel.getHoraInicio());
		arquivoDisponivelDto.setHoraTermino(arquivoDisponivel.getHoraTermino());
		arquivoDisponivelDto.setIdAtendimento(arquivoDisponivel.getIdAtendimento());
		arquivoDisponivelDto.setNomeArquivo(arquivoDisponivel.getNomeArquivo());
		arquivoDisponivelDto.setNomeGrupo(arquivoDisponivel.getNomeGrupo());
		arquivoDisponivelDto.setTipo(arquivoDisponivel.getTipo());
		return arquivoDisponivelDto;
	}
}

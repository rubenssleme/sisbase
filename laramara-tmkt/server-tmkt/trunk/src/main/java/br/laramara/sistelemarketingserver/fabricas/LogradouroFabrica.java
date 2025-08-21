package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.LogradouroDTO;
import br.laramara.sistelemarketingserver.dominio.Logradouro;

public class LogradouroFabrica extends FabricaBase<LogradouroDTO, Logradouro> {
	public final LogradouroDTO converterParaDTO(Logradouro logradouro) {
		LogradouroDTO logradouroDto = new LogradouroDTO();
		if (logradouro != null) {
			logradouroDto.setCep(logradouro.getCep());
			logradouroDto.setDescricao(logradouro.getDescricao());
			logradouroDto.setBairro(logradouro.getBairro());
			logradouroDto.setMunicipioDto(new MunicipioFabrica().converterParaDTO(logradouro.getMunicipio()));
		}
		return logradouroDto;
	}

	public final Logradouro converterParaDominio(LogradouroDTO logradouroDto) {
		Logradouro logradouro = new Logradouro();
		if (logradouroDto != null) {
			logradouro.setCep(logradouroDto.getCep());
			logradouro.setDescricao(logradouroDto.getDescricao());
			logradouro.setBairro(logradouroDto.getBairro());
			logradouro.setMunicipio(new MunicipioFabrica().converterParaDominio(logradouroDto.getMunicipioDto()));
		}
		return logradouro;
	}
}

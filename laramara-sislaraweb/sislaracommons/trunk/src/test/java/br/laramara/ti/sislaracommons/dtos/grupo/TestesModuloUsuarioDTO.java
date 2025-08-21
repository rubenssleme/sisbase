package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesModuloUsuarioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void modulousuariodto_foi_construida_com_sucesso() {

		Long id = new Long(12222);
		ModuloPeriodoDTO moduloPeriodoDTO = new ModuloPeriodoDTO();
		ModuloDTO moduloDto = new ModuloDTO(new Long(1), "Informática");
		moduloPeriodoDTO.setModuloDto(moduloDto);
		String dataInicio = "01/01/2011";
		String dataDesligamento = "31/12/2011";
		String status = "33";
		String obs = "GIgantesto texto de observações.";
		boolean aprovado = true;
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setId(id);
		
		ModuloUsuarioDTO moduloUsuarioDto = new ModuloUsuarioDTO();
		moduloUsuarioDto.setId(id);
		moduloUsuarioDto.setUsuarioDto(usuarioDto);
		moduloUsuarioDto.setDataInicio(dataInicio);
		moduloUsuarioDto.setDataOcorrencia(dataDesligamento);
		moduloUsuarioDto.setStatusDto(new StatusRelacaoComModuloDTO(status));
		moduloUsuarioDto.setObs(obs);
		moduloUsuarioDto.setAprovado(aprovado);

		Assert.assertEquals(moduloUsuarioDto.getId(), id);
		Assert.assertEquals(moduloUsuarioDto.getUsuarioDto().getId(), id);
		Assert.assertEquals(moduloUsuarioDto.getDataInicio(), dataInicio);
		Assert.assertEquals(moduloUsuarioDto.getDataOcorrencia(),
				dataDesligamento);
		Assert.assertEquals(moduloUsuarioDto.getStatusDto().toString(), status);
		Assert.assertEquals(moduloUsuarioDto.getObs(), obs);
		Assert.assertEquals(moduloUsuarioDto.isAprovado(), aprovado);
	}
}

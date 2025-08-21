package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloUsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloUsuario;

public class TestesFabricaModuloUsuario {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_modulousuario_converte_objeto_de_dominio_para_dto() {
		ModuloUsuario moduloUsuario = ContextoModuloUsuario
				.fabricarComTodosOsDados();
		moduloUsuario.setId(new Long(1222));
		moduloUsuario.getUsuario().setId(new Long(12222));

		ModuloUsuarioDTO moduloUsuarioDTO = new FabricaModuloUsuario().converterParaDTO(moduloUsuario);

		Assert.assertEquals(moduloUsuarioDTO.getId(), moduloUsuario.getId());
		Assert.assertEquals(moduloUsuarioDTO.getUsuarioDto().getId(),
				moduloUsuario.getUsuario().getId());
		Assert.assertEquals(moduloUsuarioDTO.getDataInicio(),
				moduloUsuario.getDataInicio());
		Assert.assertEquals(moduloUsuarioDTO.getDataOcorrencia(),
				moduloUsuario.getDataOcorrencia());
		Assert.assertEquals(moduloUsuarioDTO.getStatusDto().toString(),
				moduloUsuario.getStatus().toString());
		Assert.assertEquals(moduloUsuarioDTO.getObs(), moduloUsuario.getObs());
		Assert.assertEquals(moduloUsuarioDTO.isAprovado(),
				moduloUsuario.isAprovado());
		Assert.assertEquals(moduloUsuarioDTO.isIgnorarRegraDeReuniaoDeIntegracao(),
				moduloUsuario.isIgnorarRegraDeReuniaoDeIntegracao());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_modulousuario_converte_objeto_de_dto_para_dominio() {
		ModuloUsuarioDTO moduloUsuarioDto = ContextoModuloUsuario
				.fabricarModuloUsuarioDTO();
		moduloUsuarioDto.setId(new Long(1233));

		ModuloUsuario moduloUsuarioObtido = new FabricaModuloUsuario().converterParaDominio(
						moduloUsuarioDto);

		Assert.assertEquals(moduloUsuarioObtido.getId(),
				moduloUsuarioDto.getId());
		Assert.assertEquals(moduloUsuarioObtido.getDataInicio(), DataHoraUtils
				.formatarData(DataHoraUtils.criar(moduloUsuarioDto
						.getDataInicio())));
		Assert.assertEquals(moduloUsuarioObtido.getDataOcorrencia(),
				DataHoraUtils.formatarData(DataHoraUtils.criar(moduloUsuarioDto
						.getDataOcorrencia())));
		Assert.assertEquals(moduloUsuarioObtido.getStatus().toString(),
				moduloUsuarioDto.getStatusDto().toString());
		Assert.assertEquals(moduloUsuarioObtido.getObs(),
				moduloUsuarioDto.getObs());
		Assert.assertEquals(moduloUsuarioObtido.isAprovado(),
				moduloUsuarioDto.isAprovado());
		Assert.assertEquals(moduloUsuarioObtido.isIgnorarRegraDeReuniaoDeIntegracao(),
				moduloUsuarioDto.isIgnorarRegraDeReuniaoDeIntegracao());
	}
}

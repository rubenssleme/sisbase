package br.laramara.ti.sislaracommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesGrupoContaAcessoBloqueadoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void grupo_conta_acesso_bloqueado_foi_construida_com_sucesso() {
		String identificacaoGrupo = "G02-01-31/12/1921";
		PerfilDTO perfilDTO = new PerfilDTO(new Long(2), "Administrador");

		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();
		contaAcessoDto.setId(new Long(1));
		contaAcessoDto.setUsuario("pabsantos");
		contaAcessoDto.setPerfilDto(perfilDTO);
		contaAcessoDto.setSenha("1234");

		GrupoContaAcessoBloqueadoDTO grupoContaAcessoBloqueadoDTO = new GrupoContaAcessoBloqueadoDTO(
				identificacaoGrupo, contaAcessoDto);

		Assert.assertEquals(grupoContaAcessoBloqueadoDTO.getIdentificacao(),
				identificacaoGrupo);
		Assert.assertEquals(grupoContaAcessoBloqueadoDTO.getContaAcessoDTO()
				.getLogin(), contaAcessoDto.getLogin());
	}
}

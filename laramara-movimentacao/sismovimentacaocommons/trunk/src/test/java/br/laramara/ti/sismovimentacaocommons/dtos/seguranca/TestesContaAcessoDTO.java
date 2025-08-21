package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesContaAcessoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void contaacessodto_foi_construida_com_sucesso() {
		Long id = new Long(2);
		String usuarioEsperado = "pabsantos";
		String md5 = "81dc9bdb52d04dc20036dbd8313ed055";
		PerfilDTO perfilDTO = new PerfilDTO(new Long(2), "Administrador");
		boolean bloqueado = true;
		String chave = "G02";
		
		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();
		contaAcessoDto.setId(id);
		contaAcessoDto.setUsuario(usuarioEsperado);
		contaAcessoDto.setPerfilDto(perfilDTO);
		contaAcessoDto.setSenha("1234");
		contaAcessoDto.setBloqueado(bloqueado);
		contaAcessoDto.setPalavraChaveGrupo(chave);

		Assert.assertEquals(contaAcessoDto.getId(), id);
		Assert.assertEquals(contaAcessoDto.getLogin(), usuarioEsperado);
		Assert.assertEquals(contaAcessoDto.getSenha(), md5);
		Assert.assertEquals(contaAcessoDto.getPerfilDto().getId(),
				perfilDTO.getId());
		Assert.assertEquals(contaAcessoDto.isBloqueado(), bloqueado);
		Assert.assertEquals(contaAcessoDto.getPalavraChaveGrupo(), chave);
	}
}

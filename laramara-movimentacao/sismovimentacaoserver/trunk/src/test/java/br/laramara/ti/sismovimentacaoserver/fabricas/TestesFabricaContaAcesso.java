package br.laramara.ti.sismovimentacaoserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Perfil;

public class TestesFabricaContaAcesso {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_conta_acesso_converte_objeto_de_dominio_para_dto() {
		Perfil perfil = new Perfil(new Long(4), "Administrador");
		ContaAcesso contaAcesso = new ContaAcesso(new Long(1), "Paulo", "1234",
				perfil);
		contaAcesso.setBloqueado(true);
		contaAcesso.setProfissional(ContextoProfissional.fabricarComTodosOsDados());
		contaAcesso.setPalavraChaveGrupo("G02");

		ContaAcessoDTO contaAcessoDTO = new FabricaContaAcesso()
				.converterParaDTO(contaAcesso);

		Assert.assertEquals(contaAcessoDTO.getId(), contaAcesso.getId());
		Assert.assertEquals(contaAcessoDTO.getLogin(), contaAcesso.getLogin());
		Assert.assertEquals(contaAcessoDTO.getPerfilDto().getId(), contaAcesso
				.getPerfil().getId());
		Assert.assertEquals(contaAcessoDTO.isBloqueado(),
				contaAcesso.isBloqueado());
		Assert.assertEquals(contaAcessoDTO.getProfissionalDto().getId(),
				contaAcesso.getProfissional().getId());
		Assert.assertEquals(contaAcessoDTO.getPalavraChaveGrupo(), contaAcesso.getPalavraChaveGrupo());
		
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_conta_acesso_converte_objeto_de_dto_para_dominio() {
		
		PerfilDTO perfilDto = new PerfilDTO(new Long(4), "Administrador");
		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();
		contaAcessoDto.setUsuario("Paulo");
		contaAcessoDto.setSenha("1234");
		contaAcessoDto.setPerfilDto(perfilDto);
		contaAcessoDto.setBloqueado(true);
		contaAcessoDto.setProfissionalDto(ContextoProfissional.construirProfissionalDTO());
		contaAcessoDto.setPalavraChaveGrupo("G02");

		ContaAcesso contaAcesso = new FabricaContaAcesso()
				.converterParaDominio(contaAcessoDto);

		Assert.assertEquals(contaAcesso.getId(), contaAcessoDto.getId());
		Assert.assertEquals(contaAcesso.getLogin(), contaAcessoDto.getLogin());
		Assert.assertEquals(contaAcesso.getPerfil().getId(), contaAcessoDto
				.getPerfilDto().getId());
		Assert.assertEquals(contaAcesso.isBloqueado(),
				contaAcessoDto.isBloqueado());
		Assert.assertEquals(contaAcesso.getProfissional().getId(),
				contaAcessoDto.getProfissionalDto().getId());
		Assert.assertEquals(contaAcesso.getPalavraChaveGrupo(),
				contaAcessoDto.getPalavraChaveGrupo());
	}
}

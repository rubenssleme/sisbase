package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Perfil;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;

public class TestesFabricaContaAcesso {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_conta_acesso_converte_objeto_de_dominio_para_dto() {
		List<Permissao> permissoes = new ArrayList<>();
		permissoes.add(Permissao.AGENDA_REAGENDAMENTO_EDICAO_VISUALIZAR);
		permissoes.add(Permissao.CONTA_ACESSO_EDICAO);
		
		List<Profissional> profissionaisEquivalentes = new ArrayList<>();
		profissionaisEquivalentes.add(ContextoProfissional.fabricarComTodosOsDados());
		
		Perfil perfil = new Perfil(new Long(4), "Administrador");
		ContaAcesso contaAcesso = new ContaAcesso(new Long(1), "Paulo", "1234",
				perfil);
		contaAcesso.setBloqueado(true);
		contaAcesso.setProfissional(ContextoProfissional.fabricarComTodosOsDados());
		contaAcesso.setPalavraChaveGrupo("G02");
		contaAcesso.setPermissoes(permissoes);
		contaAcesso.setProfissionalEquivalente(profissionaisEquivalentes);

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
		Assert.assertEquals(contaAcessoDTO.getPermissoesDto().size(), contaAcesso.getPermissoes().size());
		Assert.assertEquals(contaAcessoDTO.getProfissionaisEquivalentesDto().size(),
				contaAcesso.getProfissionalEquivalente().size());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_conta_acesso_converte_objeto_de_dto_para_dominio() {
		
		List<PermissaoDTO> permissoes = new ArrayList<>();
		permissoes.add(new PermissaoDTO(Permissao.AGENDA_REAGENDAMENTO_EDICAO_VISUALIZAR.toString()));
		permissoes.add(new PermissaoDTO(Permissao.CONTA_ACESSO_EDICAO.toString()));
		
		List<ProfissionalDTO> profissionaisEquivalentesDto = new ArrayList<>();
		profissionaisEquivalentesDto.add(ContextoProfissional.construirProfissionalDTO());
		
		PerfilDTO perfilDto = new PerfilDTO(new Long(4), "Administrador");
		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();
		contaAcessoDto.setUsuario("Paulo");
		contaAcessoDto.setSenha("1234");
		contaAcessoDto.setPerfilDto(perfilDto);
		contaAcessoDto.setBloqueado(true);
		contaAcessoDto.setProfissionalDto(ContextoProfissional.construirProfissionalDTO());
		contaAcessoDto.setPalavraChaveGrupo("G02");
		contaAcessoDto.setPermissoesDto(permissoes);
		contaAcessoDto.setProfissionaisEquivalentesDto(profissionaisEquivalentesDto);

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
		Assert.assertEquals(contaAcesso.getPermissoes().size(), contaAcessoDto.getPermissoesDto().size());
		Assert.assertEquals(contaAcesso.getProfissionalEquivalente().size(),
				contaAcessoDto.getProfissionaisEquivalentesDto().size());
	}
}

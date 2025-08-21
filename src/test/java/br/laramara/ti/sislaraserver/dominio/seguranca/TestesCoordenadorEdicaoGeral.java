package br.laramara.ti.sislaraserver.dominio.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoContaAcesso;

public class TestesCoordenadorEdicaoGeral {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void coordenador_edicao_grupo_remove_todos_grupos_esta_sendo_editado() {

		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G2"));
		grupoDTO.setTurma("02");
		grupoDTO.setDataInicio("31/12/2012");

		ContaAcesso contaAcesso = ContextoContaAcesso.fabricarComTodosOsDados();

		CoordenadorEdicaoGeral coordenadorEdicao = new CoordenadorEdicaoGeral();
		coordenadorEdicao.bloquear(grupoDTO.obterNome(), contaAcesso);

		coordenadorEdicao.desbloquearTodos();

		Assert.assertTrue(coordenadorEdicao.obterObjetosBloqueados().isEmpty());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void coordenador_edicao_grupo_confirma_grupo_esta_sendo_editado() {

		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G2"));
		grupoDTO.setTurma("02");
		grupoDTO.setDataInicio("31/12/2012");
		
		ContaAcesso contaAcesso = ContextoContaAcesso.fabricarComTodosOsDados();

		CoordenadorEdicaoGeral coordenadorEdicao = new CoordenadorEdicaoGeral();
		coordenadorEdicao.bloquear(grupoDTO.obterNome(), contaAcesso);

		Assert.assertTrue(coordenadorEdicao
				.estaBloqueadoParaEdicao(grupoDTO.obterNome()));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void coordenador_edicao_grupo_nao_confirma_grupo_esta_sendo_editado() {

		String identificacaoGrupo = "G05-01";

		CoordenadorEdicaoGeral coordenadorEdicao = new CoordenadorEdicaoGeral();

		Assert.assertFalse(coordenadorEdicao
				.estaBloqueadoParaEdicao(identificacaoGrupo));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void coordenador_edicao_grupo_remove_grupo_da_lista_como_sendo_editado() {

		ContaAcesso contaAcesso = ContextoContaAcesso.fabricarComTodosOsDados();

		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G2"));
		grupoDTO.setTurma("04");
		grupoDTO.setDataInicio("31/12/2012");
		
		CoordenadorEdicaoGeral coordenadorEdicao = new CoordenadorEdicaoGeral();
		coordenadorEdicao.bloquear(grupoDTO.obterNome(), contaAcesso);
		coordenadorEdicao.desbloquearChave(grupoDTO.obterNome(), contaAcesso);

		Assert.assertFalse(coordenadorEdicao
				.estaBloqueadoParaEdicao(grupoDTO.obterNome()));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void coordenador_edicao_grupo_obtem_conta_acesso_grupo_sendo_editado() {

		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G02"));
		grupoDTO.setTurma("01");
		grupoDTO.setDataInicio("31/12/1921");
		
		ContaAcesso contaAcesso = ContextoContaAcesso.fabricarComTodosOsDados();

		CoordenadorEdicaoGeral coordenadorEdicao = new CoordenadorEdicaoGeral();
		coordenadorEdicao.bloquear(grupoDTO.obterNome(), contaAcesso);

		ContaAcesso contaAcessoObtido = coordenadorEdicao
				.obterContaAcessoEditando(grupoDTO.obterNome());

		Assert.assertEquals(contaAcesso, contaAcessoObtido);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void coordenador_edicao_grupo_remove_grupo_bloqueado_a_partir_de_conta_acesso() {

		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setId(new Long(123));
		grupoDTO.setNomeGrupoDto(new NomeGrupoDTO(new Long(1004), "G04"));
		grupoDTO.setTurma("01");
		grupoDTO.setDataInicio("31/12/1921");
		
		GrupoDTO grupoDTO2 = new GrupoDTO();
		grupoDTO2.setId(new Long(12));
		grupoDTO2.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G02"));
		grupoDTO2.setTurma("01");
		grupoDTO2.setDataInicio("31/12/1921");
		
		ContaAcesso contaAcesso = new ContaAcesso(new Long(1), "Paulo", "",
				null);
		ContaAcesso contaAcesso2 = new ContaAcesso(new Long(2), "Paulo2", "",
				null);

		CoordenadorEdicaoGeral coordenadorEdicao = new CoordenadorEdicaoGeral();
		coordenadorEdicao.bloquear(grupoDTO.obterNome(), contaAcesso);
		coordenadorEdicao.bloquear(grupoDTO2.obterNome(), contaAcesso2);

		coordenadorEdicao.desbloquearValor(contaAcesso);

		Assert.assertFalse(coordenadorEdicao
				.estaBloqueadoParaEdicao(grupoDTO.obterNome()));
		Assert.assertTrue(coordenadorEdicao
				.estaBloqueadoParaEdicao(grupoDTO2.obterNome()));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void coordenador_edicao_usuario_confirma_usuario_esta_sendo_editado() {

		UsuarioDTO usuarioDTO = new UsuarioDTO(new Long(1234));

		ContaAcesso contaAcesso = ContextoContaAcesso.fabricarComTodosOsDados();

		CoordenadorEdicaoGeral coordenadorEdicao = new CoordenadorEdicaoGeral();
		coordenadorEdicao.bloquear(usuarioDTO.obterNome(), contaAcesso);

		Assert.assertTrue(coordenadorEdicao.estaBloqueadoParaEdicao(usuarioDTO
				.obterNome()));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void coordenador_edicao_usuario_nao_confirma_usuario_esta_sendo_editado() {

		CoordenadorEdicaoGeral coordenadorEdicao = new CoordenadorEdicaoGeral();
		UsuarioDTO usuarioDTO = new UsuarioDTO(new Long(1234545));

		Assert.assertFalse(coordenadorEdicao.estaBloqueadoParaEdicao(usuarioDTO
				.obterNome()));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void coordenador_edicao_usuario_remove_usuario_da_lista_como_sendo_editado() {

		String identificacao = "434898";
		UsuarioDTO usuarioDTO = new UsuarioDTO(new Long(identificacao));

		ContaAcesso contaAcesso = new ContaAcesso(new Long(1), "Paulo", "",
				null);

		CoordenadorEdicaoGeral coordenadorEdicao = new CoordenadorEdicaoGeral();
		coordenadorEdicao.bloquear(usuarioDTO.obterNome(), contaAcesso);
		coordenadorEdicao.desbloquearChave(usuarioDTO.obterNome(), contaAcesso);

		Assert.assertFalse(coordenadorEdicao.estaBloqueadoParaEdicao(usuarioDTO
				.obterNome()));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void coordenador_edicao_usuario_obtem_conta_acesso_usuario_sendo_editado() {

		String identificacao = "1234";
		UsuarioDTO usuarioDTO = new UsuarioDTO(new Long(identificacao));

		ContaAcesso contaAcesso = new ContaAcesso(new Long(1), "Paulo", "",
				null);

		CoordenadorEdicaoGeral coordenadorEdicao = new CoordenadorEdicaoGeral();
		coordenadorEdicao.bloquear(usuarioDTO.obterNome(), contaAcesso);

		ContaAcesso contaAcessoObtido = coordenadorEdicao
				.obterContaAcessoEditando(usuarioDTO.obterNome());

		Assert.assertEquals(contaAcesso, contaAcessoObtido);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void coordenador_edicao_usuario_remove_usuario_bloqueado_a_partir_de_conta_acesso() {

		String prontuario = "1234";
		String prontuario2 = "12345";

		UsuarioDTO usuarioDTO = new UsuarioDTO(new Long(prontuario));
		UsuarioDTO usuarioDTO2 = new UsuarioDTO(new Long(prontuario2));

		ContaAcesso contaAcesso = new ContaAcesso(new Long(1), "Paulo", "",
				null);
		ContaAcesso contaAcesso2 = new ContaAcesso(new Long(2), "Paulo2", "",
				null);

		CoordenadorEdicaoGeral coordenadorEdicao = new CoordenadorEdicaoGeral();
		coordenadorEdicao.bloquear(usuarioDTO.obterNome(), contaAcesso);
		coordenadorEdicao.bloquear(usuarioDTO2.obterNome(), contaAcesso2);

		coordenadorEdicao.desbloquearValor(contaAcesso);

		Assert.assertFalse(coordenadorEdicao.estaBloqueadoParaEdicao(usuarioDTO
				.obterNome()));
		Assert.assertTrue(coordenadorEdicao.estaBloqueadoParaEdicao(usuarioDTO2
				.obterNome()));
	}
}

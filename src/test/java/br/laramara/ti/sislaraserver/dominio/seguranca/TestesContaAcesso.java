package br.laramara.ti.sislaraserver.dominio.seguranca;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesContaAcesso {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_possui_permissao_exigida() {
		Permissao permissaoExigida = Permissao.USUARIO_EDICAO;
		Perfil perfilAdministrador = new Perfil(new Long(1), "Administrador");
		perfilAdministrador.adicionarPermissao(permissaoExigida);
		
		ContaAcesso contaAcesso = new ContaAcesso(new Long(1), "pabsantos", "1234", perfilAdministrador);

		Assert.assertTrue(contaAcesso.possuiPermissao(permissaoExigida));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_nao_possui_permissao_exigida() {
		Permissao permissaoExigida = Permissao.USUARIO_EDICAO;
		Perfil perfilUsuario = new Perfil(new Long(2), "Usuário");
		perfilUsuario.adicionarPermissao(Permissao.GRUPO_EDICAO);

		ContaAcesso contaAcesso = new ContaAcesso(new Long(1), "pabsantos", "1234", perfilUsuario);
		
		Assert.assertFalse(contaAcesso.possuiPermissao(permissaoExigida));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_possui_permissao_especifica_exigida() {
		Permissao permissaoEspecifica = Permissao.AGENDA_REAGENDAMENTO_EDICAO_VISUALIZAR;
		List<Permissao> permissoesEspecificas = new ArrayList<>();
		permissoesEspecificas.add(permissaoEspecifica);

		Perfil perfilUsuario = new Perfil(new Long(2), "Usuário");
		perfilUsuario.adicionarPermissao(Permissao.GRUPO_EDICAO);

		ContaAcesso contaAcesso = new ContaAcesso(new Long(1), "pabsantos", "1234", perfilUsuario);
		contaAcesso.setPermissoes(permissoesEspecificas);

		Assert.assertTrue(contaAcesso.possuiPermissao(permissaoEspecifica));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_validacao_com_erro_obrigatoriedade() {
		ContaAcesso contaAcesso = new ContaAcesso();
		contaAcesso.setPalavraChaveGrupo(ContextoGenerico.obterGrande());
		contaAcesso.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(contaAcesso.validado());
		Assert.assertEquals(contaAcesso.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_validacao_sem_erro_obrigatoriedade() {
		ContaAcesso contaAcesso = ContextoContaAcesso.fabricarComTodosOsDados();
		contaAcesso.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(contaAcesso.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_novo_com_senha_criptografada() {
		String senha = "81dc9bdb52d04dc20036dbd8313ed055";
		ContaAcesso contaAcesso = new ContaAcesso();
		contaAcesso.setSenha(senha);

		Assert.assertEquals(contaAcesso.getSenha(), senha);
	}
}

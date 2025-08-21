package br.laramara.ti.sislaraserver.dominio.doacao;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.fabricas.ContextoGrupo;
import br.laramara.ti.sislaraserver.fabricas.ContextoPreCadastro;
import br.laramara.ti.sislaraserver.fabricas.ContextoProjeto;
import br.laramara.ti.sislaraserver.fabricas.ContextoRecurso;
import br.laramara.ti.sislaraserver.fabricas.ContextoUsuario;

public class TestesEspecificacaoGeracaoDemanda {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_demanda_construido_com_sucesso() {

		PreCadastro preCadastro = ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados();
		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();

		Projeto projeto = ContextoProjeto.fabricarProjetoComTodosOsDados();
		Recurso recurso = ContextoRecurso.fabricarRecursoComTodosOsDados();
		Grupo grupo = ContextoGrupo.fabricarGrupoComTodosOsDados();

		EspecificacaoGeracaoDemanda especificacao = new EspecificacaoGeracaoDemanda();
		especificacao.setUsuario(usuario);
		especificacao.setPreCadastro(preCadastro);
		especificacao.setProjeto(projeto);
		especificacao.setRecursos(Arrays.asList(recurso));
		especificacao.setGrupo(grupo);

		Assert.assertEquals(especificacao.getUsuario().getId(), usuario.getId());
		Assert.assertEquals(especificacao.getPreCadastro().getId(),
				preCadastro.getId());
		Assert.assertEquals(especificacao.getProjeto().getId(), projeto.getId());
		Assert.assertEquals(especificacao.getRecursos().get(0).getId(), recurso.getId());
		Assert.assertEquals(especificacao.getGrupo().getId(), grupo.getId());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_demanda_validacao_com_erro_de_obrigatoriedade_de_dados() {
		EspecificacaoGeracaoDemanda especificacao = new EspecificacaoGeracaoDemanda();
		especificacao.setDocumentosSolicitacaoDoacao(Arrays.asList(new DocumentoSolicitacaoDoacao()));
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 3);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_nao_geracao_demanda_validacao_com_erro_de_usuario() {
		EspecificacaoGeracaoDemanda especificacao = new EspecificacaoGeracaoDemanda();
		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.getInformacaoEssencial().setCpf(null);
		especificacao.setUsuario(usuario);
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_nao_geracao_demanda_validacao_com_erro_de_pre_cadastro() {
		EspecificacaoGeracaoDemanda especificacao = new EspecificacaoGeracaoDemanda();
		PreCadastro preCadastro = ContextoPreCadastro.fabricarPreCadastroComTodosOsDados();
		preCadastro.getInformacaoEssencial().setCpf(null);
		especificacao.setPreCadastro(preCadastro);
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 2);
	}
}

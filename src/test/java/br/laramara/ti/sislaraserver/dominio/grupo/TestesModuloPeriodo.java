package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.fabricas.ContextoModuloPeriodo;
import br.laramara.ti.sislaraserver.fabricas.ContextoModuloPreCadastro;
import br.laramara.ti.sislaraserver.fabricas.ContextoModuloUsuario;
import br.laramara.ti.sislaraserver.fabricas.ContextoProfissionalVinculo;

public class TestesModuloPeriodo {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloperiodo_validacao_com_erro_obrigatoriedade() {
		ModuloPeriodo moduloPeriodo = new ModuloPeriodo();
		moduloPeriodo.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(moduloPeriodo.obterNumeroErros(), 7);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloperiodo_validacao_com_erro_de_numero_de_vagas_excedidas() {

		List<ModuloUsuario> modulosUsuarios = new ArrayList<>();
		modulosUsuarios.add(ContextoModuloUsuario.fabricarComTodosOsDados());
		modulosUsuarios.add(ContextoModuloUsuario.fabricarComTodosOsDados());
		modulosUsuarios.add(ContextoModuloUsuario.fabricarComTodosOsDados());

		ModuloPeriodo moduloPeriodo = ContextoModuloPeriodo
				.fabricarComTodosOsDadosInformatica();
		moduloPeriodo.setVagas("2");
		moduloPeriodo
				.setModulosUsuarios(modulosUsuarios);

		moduloPeriodo.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(moduloPeriodo.obterNumeroErros(), 1);
	}

	@SuppressWarnings("static-access")
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloperiodo_validacao_com_erro_de_usuario_e_precadastro_duplicado() {

		List<ModuloUsuario> modulosUsuarios = new ArrayList<>();
		modulosUsuarios.add(ContextoModuloUsuario.fabricarComTodosOsDados());

		List<ModuloPreCadastro> modulosPreCadastro = new ArrayList<>();
		modulosPreCadastro.add(ContextoModuloPreCadastro
				.fabricarComTodosOsDados());

		ModuloPeriodo moduloPeriodo = ContextoModuloPeriodo
				.fabricarComTodosOsDadosInformatica();
		moduloPeriodo.setModulosUsuarios(modulosUsuarios);
		moduloPeriodo.setModulosPreCadastro(modulosPreCadastro);

		Assert.assertTrue(moduloPeriodo.possuiUsuarioDuplicado(modulosUsuarios,
				ContextoModuloUsuario.fabricarComTodosOsDados()));
		Assert.assertTrue(moduloPeriodo.possuiPreCadastroDuplicado(
				modulosPreCadastro,
				ContextoModuloPreCadastro.fabricarComTodosOsDados()));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloperiodo_validacao_sem_erro_obrigatoriedade() {
		ModuloPeriodo moduloPeriodo = ContextoModuloPeriodo.fabricarComTodosOsDadosInformatica();

		moduloPeriodo.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(moduloPeriodo.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloperiodo_filtra_apenas_profissionais_sem_os_participantes() {
		ProfissionalVinculo profissionalVinculo = ContextoProfissionalVinculo
				.fabricarComTodosOsDados();
		profissionalVinculo.setParticipante(true);

		ModuloPeriodo moduloPeriodo = ContextoModuloPeriodo
				.fabricarComTodosOsDadosInformatica();
		moduloPeriodo.getProfissionaisVinculo().add(profissionalVinculo);

		Assert.assertEquals(moduloPeriodo.obterSomenteProfissionais().size(), 1);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void grupo_adiciona_e_altera_relacao_modulo_usuario() {
		ModuloPeriodo moduloPeriodo = ContextoModuloPeriodo
				.fabricarComTodosOsDadosInformatica();
		moduloPeriodo.getModulosUsuarios().clear();

		ModuloUsuario moduloUsuarioA = ContextoModuloUsuario
				.fabricarComTodosOsDados();
		moduloUsuarioA.setId(new Long(1));
		ModuloUsuario moduloUsuarioB = ContextoModuloUsuario
				.fabricarComTodosOsDados();
		moduloUsuarioB.setId(new Long(2));
		List<ModuloUsuario> modulosUsuarios = new ArrayList<>();
		modulosUsuarios.add(moduloUsuarioA);
		modulosUsuarios.add(moduloUsuarioB);
		moduloPeriodo.setModulosUsuarios(modulosUsuarios);

		List<ModuloUsuario> modulosUsuariosAAlterar = new ArrayList<>();
		moduloUsuarioA.setStatus(StatusRelacaoComModulo.REMOVIDO);
		modulosUsuariosAAlterar.add(moduloUsuarioA);
		modulosUsuariosAAlterar.add(ContextoModuloUsuario
				.fabricarComTodosOsDados());
		modulosUsuariosAAlterar.add(ContextoModuloUsuario
				.fabricarComTodosOsDados());
		moduloPeriodo.setModulosUsuarios(modulosUsuariosAAlterar);

		Assert.assertEquals(moduloPeriodo.getModulosUsuarios().size(), 4);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloperiodo_adiciona_relacao_modulo_usuario_integrado() {
		List<ModuloUsuario> modulosUsuarios = new ArrayList<>();
		ModuloUsuario moduloUsuarioIntegrado1 = ContextoModuloUsuario
				.fabricarComTodosOsDados();
		moduloUsuarioIntegrado1.setStatus(StatusRelacaoComModulo.INTEGRADO);
		modulosUsuarios.add(moduloUsuarioIntegrado1);
		ModuloUsuario moduloUsuarioIntegrado2 = ContextoModuloUsuario
				.fabricarComTodosOsDados();
		moduloUsuarioIntegrado2.setStatus(StatusRelacaoComModulo.INTEGRADO);
		modulosUsuarios.add(moduloUsuarioIntegrado2);
		ModuloUsuario moduloUsuarioDesligado = ContextoModuloUsuario
				.fabricarComTodosOsDados();
		moduloUsuarioDesligado.setStatus(StatusRelacaoComModulo.DESLIGADO);
		modulosUsuarios.add(moduloUsuarioDesligado);

		ModuloPeriodo moduloPeriodo = new ModuloPeriodo();
		moduloPeriodo
				.setModulosUsuarios(modulosUsuarios);

		moduloPeriodo.gerarAtendimentosDeGrupo("31/12/2010", "08:00", "12:00");

		Assert.assertEquals(moduloPeriodo.getAtendimentosGrupo().get(0)
				.getAtendimentosUsuarios().size(), 2);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloPeriodo_validacao_com_erro_de_hora_termino_anterior_hora_inicial() {
		ModuloPeriodo moduloPeriodo = ContextoModuloPeriodo.fabricarComTodosOsDadosInformatica();
		moduloPeriodo.getDiasSemanaEHorariosDaAtividade().get(0).setHorario(new Horario("08:00", "06:00"));
		moduloPeriodo.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(moduloPeriodo.validado());
		Assert.assertEquals(moduloPeriodo.obterNumeroErros(), 1);
	}
}

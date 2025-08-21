package br.laramara.ti.sislaraserver.dominio.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.atendimento.CategoriaAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.fabricas.ContextoAtendimentoGrupo;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoPreCadastro;
import br.laramara.ti.sislaraserver.fabricas.ContextoProfissional;
import br.laramara.ti.sislaraserver.fabricas.ContextoUsuario;

public class TestesAtendimento {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimento_gera_atendimentos_por_usuario_e_pre_cadastro() {
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(ContextoUsuario.fabricarUsuarioComTodosOsDados());
		usuarios.add(ContextoUsuario.fabricarUsuarioComTodosOsDados());
		
		List<PreCadastro> preCadastros = new ArrayList<>();
		preCadastros.add(ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados());
		preCadastros.add(ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados());
		
		List<Profissional> profissionais = new ArrayList<>();
		profissionais.add(ContextoProfissional.fabricarComTodosOsDados());
		profissionais.add(ContextoProfissional.fabricarComTodosOsDados());

		AtendimentoGrupo atendimento = new AtendimentoGrupo(usuarios, preCadastros, profissionais);

		Assert.assertEquals(atendimento.getAtendimentosUsuarios().size(),
				usuarios.size());
		Assert.assertEquals(atendimento.getAtendimentosPreCadastros().size(),
				preCadastros.size());
		Assert.assertEquals(atendimento.getAtendimentosProfissionais().size(), profissionais.size());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimento_gera_atendimentos_da_categoria_grupo() {
		AtendimentoGrupo atendimento = new AtendimentoGrupo();

		Assert.assertEquals(atendimento.getCategoriaAtendimento(),
				CategoriaAtendimento.GRUPO);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimento_calcula_hora_total() {
		AtendimentoGrupo atendimento = new AtendimentoGrupo();
		atendimento.setHorario(new Horario("08:00", "12:59"));

		Assert.assertTrue(atendimento.toString()
				.contains("totalHoras=04:59:00"));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimento_validacao_com_erro_de_obrigatoriedade_de_dados() {
		AtendimentoGrupo atendimento = new AtendimentoGrupo();

		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(atendimento.validado());
		Assert.assertEquals(atendimento.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimento_validacao_com_erro_de_tamanho_maximo_de_dados() {
		AtendimentoGrupo atendimento = new AtendimentoGrupo();
		atendimento.setData("9384239874");
		atendimento.setHorario(new Horario("984729", "fdihafdusy"));
		atendimento.setDescricao(ContextoGenerico.obterGrande());
		atendimento.setInterdisciplinar(ContextoGenerico.obterGrande());
		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(atendimento.validado());
		Assert.assertEquals(atendimento.obterNumeroErros(), 6);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimento_validacao_com_erro_de_hora_termino_anterior_hora_inicial() {
		AtendimentoGrupo atendimento = ContextoAtendimentoGrupo.fabricarAtendimentoComTodosOsDados();
		atendimento.setHorario(new Horario("12:00", "10:00"));
		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(atendimento.validado());
		Assert.assertEquals(atendimento.obterNumeroErros(), 1);
	}
		
}

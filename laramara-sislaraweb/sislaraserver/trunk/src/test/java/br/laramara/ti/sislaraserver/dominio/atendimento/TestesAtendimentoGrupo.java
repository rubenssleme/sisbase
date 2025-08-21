package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;
import br.laramara.ti.sislaraserver.fabricas.ContextoAtendimentoGrupo;
import br.laramara.ti.sislaraserver.fabricas.ContextoAtendimentoProfissional;
import br.laramara.ti.sislaraserver.fabricas.ContextoAtendimentoUsuario;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesAtendimentoGrupo {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentogrupo_validacao_com_erro_obrigatoriedade_e_tamanho_maximo() {
		AtendimentoGrupo atendimento = new AtendimentoGrupo();
		atendimento.setDescricao(ContextoGenerico.obterGrande());
		atendimento.setInterdisciplinar(ContextoGenerico.obterGrande());
		atendimento.setArquivos(Arrays.asList(new Arquivo(new Long(1), "Arquivo.pdf", null),
				new Arquivo(new Long(1), "Arquivo.pdf", null)));
		atendimento.setAtendimentosProfissionais(
				Arrays.asList(ContextoAtendimentoProfissional.fabricarAtendimentoProfissionalComTodosOsDados(),
						ContextoAtendimentoProfissional.fabricarAtendimentoProfissionalComTodosOsDados()));
		atendimento.setPessoasSemCadastro("XYZ");
		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(atendimento.validado());
		Assert.assertEquals(atendimento.obterNumeroErros(), 8);
		Assert.assertTrue(atendimento.obterDescricaoErros().contains("Existem arquivos duplicados."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentogrupo_validacao_com_sucesso() {
		AtendimentoGrupo atendimento = new AtendimentoGrupo();
		atendimento.setDescricao("Descrição");
		atendimento.setInterdisciplinar("Inter");
		atendimento.setData("31/12/2015");
		atendimento.setHorario(new Horario("09:00", "10:00"));
		atendimento.setAtendimentosUsuarios(
				Arrays.asList(ContextoAtendimentoUsuario.fabricarAtendimentoUsuarioATComTodosOsDados()));
		atendimento.setAtendimentosProfissionais(
				Arrays.asList(ContextoAtendimentoProfissional.fabricarAtendimentoProfissionalComATComTodosOsDados()));
		
		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(atendimento.validado());
		Assert.assertFalse(atendimento.getAtendimentosUsuarios().get(0).getInformacaoAtendimento()
				.getParticipacaoDetalhada().isEmpty());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentogrupo_validacao_sem_erro_contendo_usuario_com_frequencia_AT_sem_participacao_detalhada() {
		AtendimentoGrupo atendimento = ContextoAtendimentoGrupo.fabricarAtendimentoComTodosOsDados();
		atendimento.setId(new Long(2222));
		atendimento.getAtendimentosUsuarios().get(0).getInformacaoAtendimento().setFrequencia(Frequencia.FJ);
		atendimento.getAtendimentosUsuarios().get(0).getInformacaoAtendimento()
				.setParticipacaoDetalhada(Arrays.asList());

		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(atendimento.validado());
	}
}

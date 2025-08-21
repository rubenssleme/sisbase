package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.fabricas.ContextoAtendimentoUsuario;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesAtendimentoUsuario {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentousuario_validacao_com_erro_obrigatoriedade_e_tamanho_maximo() {
		AtendimentoUsuario atendimento = new AtendimentoUsuario();
		InformacaoAtendimento informacoesAtendimento = new InformacaoAtendimento();
		informacoesAtendimento.setFrequencia(null);
		informacoesAtendimento.setDescricao(ContextoGenerico.obterGrande());
		informacoesAtendimento.setJustificativa(ContextoGenerico.obterGrande());
		atendimento.setInformacaoAtendimento(informacoesAtendimento);
		atendimento.setArquivos(Arrays.asList(new Arquivo(new Long(1), "Arquivo.pdf", null),
				new Arquivo(new Long(1), "Arquivo.pdf", null)));

		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(atendimento.validado());
		Assert.assertEquals(atendimento.obterNumeroErros(), 5);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentousuario_validacao_sem_erro_contendo_usuario_com_frequencia_diferente_de_AT_e_sem_participacao_detalhada() {
		AtendimentoUsuario atendimento = ContextoAtendimentoUsuario.fabricarAtendimentoUsuarioComTodosOsDados();
		atendimento.getInformacaoAtendimento().setFrequencia(Frequencia.FR);
		atendimento.getInformacaoAtendimento().setParticipacaoDetalhada(Arrays.asList());

		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(atendimento.validado());
	}
}

package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.fabricas.ContextoAtendimentoProfissional;
import br.laramara.ti.sislaraserver.fabricas.ContextoDescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoInformacaoAtendimento;
import br.laramara.ti.sislaraserver.fabricas.ContextoModulo;
import br.laramara.ti.sislaraserver.fabricas.ContextoUsuario;

public class TestesAtendimentoIndividual {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimento_individual_construido_com_sucesso() {
		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		InformacaoAtendimento informacaoAtendimento = ContextoInformacaoAtendimento
				.fabricarInformacaoTrabalhoComTodosOsDados();
		DescricaoTipoAtendimento descricaoTipoAtendimento = ContextoDescricaoTipoAtendimento
				.fabricarComTodosOsDados();
		Modulo modulo = ContextoModulo.fabricarComTodosOsDados();
		List<AtendimentoProfissional> atendimentosProfissional = new ArrayList<>();
		atendimentosProfissional.add(ContextoAtendimentoProfissional.fabricarAtendimentoProfissionalComTodosOsDados());
		Setor setor = Setor.CTO;
		String texto = "Grande observação.";
		String data = "31/12/2012";
		Horario horario = new Horario("09:00", "10:00");
		LocalAtendimento localAtendimento = new LocalAtendimento(new Long(12), "Sala 1");

		AtendimentoIndividual atendimento = new AtendimentoIndividual();
		atendimento.setUsuario(usuario);
		atendimento.setInformacaoAtendimento(informacaoAtendimento);
		atendimento.setDescricaoTipoAtendimento(descricaoTipoAtendimento);
		atendimento.setModulo(modulo);
		atendimento.setSetor(setor);
		atendimento.setLocalAtendimento(localAtendimento);
		atendimento.setInterdisciplinar(texto);
		atendimento.setData(data);
		atendimento.setHorario(horario);
		atendimento.setAtendimentosProfissionais(atendimentosProfissional);
		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(atendimento.validado());
		Assert.assertEquals(atendimento.getUsuario().getId(), usuario.getId());
		Assert.assertEquals(atendimento.getInformacaoAtendimento().getId(),
				informacaoAtendimento.getId());
		Assert.assertEquals(atendimento.getDescricaoTipoAtendimento().getId(),
				descricaoTipoAtendimento.getId());
		Assert.assertEquals(atendimento.getModulo().getId(), modulo.getId());
		Assert.assertEquals(atendimento.getLocalAtendimento().getId(),
				localAtendimento.getId());
		Assert.assertEquals(atendimento.getSetor().toString(), setor.toString());
		Assert.assertEquals(atendimento.getInterdisciplinar(), texto);
		Assert.assertEquals(atendimento.getCategoriaAtendimento(),
				CategoriaAtendimento.INDIVIDUAL);
		Assert.assertEquals(atendimento.getData(), data);
		Assert.assertEquals(atendimento.getHorario().toString(),
				horario.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimento_individual_validacao_com_erro_de_obrigatoriedade_de_dados_e_tamanho_maximo_de_dados() {
		AtendimentoIndividual atendimento = new AtendimentoIndividual();
		atendimento.getInformacaoAtendimento().setDescricao(ContextoGenerico.obterGrande());
		atendimento.getInformacaoAtendimento().setJustificativa(ContextoGenerico.obterGrande());
		atendimento.getInformacaoAtendimento().setFrequencia(null);
		atendimento.setInterdisciplinar(ContextoGenerico.obterGrande());
		atendimento.setArquivos(Arrays.asList(new Arquivo(new Long(1), "Arquivo.pdf", null),
				new Arquivo(new Long(1), "Arquivo.pdf", null)));
		atendimento.setAtendimentosProfissionais(
				Arrays.asList(ContextoAtendimentoProfissional.fabricarAtendimentoProfissionalComTodosOsDados(),
						ContextoAtendimentoProfissional.fabricarAtendimentoProfissionalComTodosOsDados()));
		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(atendimento.validado());
		Assert.assertEquals(atendimento.obterNumeroErros(), 14);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimento_individual_validacao_com_erro_de_obrigatoriedade_primeira_vez_ou_retorno() {
		AtendimentoIndividual atendimento = new AtendimentoIndividual();
		atendimento.setDescricaoTipoAtendimento(ContextoDescricaoTipoAtendimento.fabricarServicoSocial());
		atendimento.setModulo(ContextoModulo.contruirAvaliacaoETriagem());
		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(atendimento.validado());
		Assert.assertEquals(atendimento.obterNumeroErros(), 8);
	}
}

package br.laramara.ti.sislaraserver.dominio.agenda;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemana;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.fabricas.ContextoDescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspecificacaoGeracaoAgendamento;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoGrupo;
import br.laramara.ti.sislaraserver.fabricas.ContextoModulo;
import br.laramara.ti.sislaraserver.fabricas.ContextoPreCadastro;
import br.laramara.ti.sislaraserver.fabricas.ContextoUsuario;

public class TestesEspecificacaoGeracaoAgendamento {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_agendamento_construido_com_sucesso() {
		Long id = new Long(12);
		String data = "31/12/2012";
		DiaSemana diaSemana = DiaSemana.SEGUNDA;
		String horaInicio = "09:00";
		String horaTermino = "12:00";
		List<Profissional> profissionais = new ArrayList<>();
		Profissional profissional = new Profissional(id, "Josep", "1234");
		profissionais.add(profissional);
		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		DescricaoTipoAtendimento descricaoTipoAtendimento = ContextoDescricaoTipoAtendimento
				.fabricarComTodosOsDados();
		Modulo modulo = ContextoModulo.fabricarComTodosOsDados();
		Setor setor = Setor.CTO;
		LocalAtendimento localAtendimento = new LocalAtendimento(new Long(1),
				"Sala 1");
		Status statusParaReserva = Status.CASO_NOVO;
		String obs = "Grande observação.";

		EspecificacaoGeracaoAgendamento especificacao = new EspecificacaoGeracaoAgendamento();
		especificacao.setUsuario(usuario);
		especificacao.setProfissionais(profissionais);
		especificacao.setDescricaoTipoAtendimento(descricaoTipoAtendimento);
		especificacao.setModulo(modulo);
		especificacao.setSetor(setor);
		especificacao.setLocalAtendimento(localAtendimento);
		especificacao.setDataInicio(data);
		especificacao.setDataTermino(data);
		especificacao.setDiaSemana(diaSemana);
		especificacao.setHorario(new Horario(horaInicio, horaTermino));
		especificacao.setReservaStatus(statusParaReserva);
		especificacao.setObs(obs);

		Assert.assertEquals(especificacao.getUsuario().getId(), usuario.getId());
		Assert.assertEquals(especificacao.getProfissionais().get(0).getId(), id);
		Assert.assertEquals(
				especificacao.getDescricaoTipoAtendimento().getId(),
				descricaoTipoAtendimento.getId());
		Assert.assertEquals(
				especificacao.getModulo().getId(),
				modulo.getId());
		Assert.assertEquals(especificacao.getSetor().toString(),
				setor.toString());
		Assert.assertEquals(especificacao.getLocalAtendimento().getId(),
				localAtendimento.getId());
		Assert.assertEquals(especificacao.getDataInicio(), data);
		Assert.assertEquals(especificacao.getDataTermino(), data);
		Assert.assertEquals(especificacao.getDiaSemana().toString(),
				diaSemana.toString());
		Assert.assertEquals(especificacao.getHorario().getHoraInicio(), horaInicio);
		Assert.assertEquals(especificacao.getHorario().getHoraTermino(), horaTermino);
		Assert.assertEquals(especificacao.getReservaStatus(), statusParaReserva);
		Assert.assertEquals(especificacao.getObs(), obs);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_agendamento_unico_validacao_com_erro_de_obrigatoriedade_de_dados() {
		EspecificacaoGeracaoAgendamento especificacao = new EspecificacaoGeracaoAgendamento();
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 9);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_agendamento_multiplo_validacao_com_erro_de_obrigatoriedade_e_tamanho_maximo_de_dados() {
		EspecificacaoGeracaoAgendamento especificacao = new EspecificacaoGeracaoAgendamento();
		especificacao.setDataTermino("31/12/2012");
		especificacao.setObs(ContextoGenerico.obterGrande());
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 11);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_agendamento_multiplo_validacao_com_erro_de_obrigatoriedade_de_dados_alternativo() {
		EspecificacaoGeracaoAgendamento especificacao = new EspecificacaoGeracaoAgendamento();
		especificacao.setDiaSemana(DiaSemana.QUINTA);
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 10);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_agendamento_multiplo_validacao_com_erro_de_data_igual() {
		EspecificacaoGeracaoAgendamento especificacao = ContextoEspecificacaoGeracaoAgendamento
				.fabricarComTodosOsDados();
		especificacao.setDataInicio("01/01/2001");
		especificacao.setDataTermino("01/01/2001");
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_agendamento_multiplo_validacao_com_erro_de_faixa_de_data_e_horario_invalidos() {
		EspecificacaoGeracaoAgendamento especificacao = new EspecificacaoGeracaoAgendamento();
		especificacao.setDataInicio("31/12/2010");
		especificacao.setDataTermino("01/12/2010");
		especificacao.setHorario(new Horario("12:00", "01:00"));
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 9);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_agendamento_unico() {
		EspecificacaoGeracaoAgendamento especificacao = ContextoEspecificacaoGeracaoAgendamento
				.fabricarComTodosOsDados();
		especificacao.setDataTermino(null);
		especificacao.setDiaSemana(null);

		Assert.assertTrue(especificacao.solicitacaoAgendamentoUnico());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_agendamento_multiplo() {
		EspecificacaoGeracaoAgendamento especificacao = ContextoEspecificacaoGeracaoAgendamento
				.fabricarComTodosOsDados();

		Assert.assertTrue(especificacao.solicitacaoAgendamentoMultiplo());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_agendamento_multiplo_validacao_com_erro_de_violacao_maxima_de_agendamento() {
		EspecificacaoGeracaoAgendamento especificacao = ContextoEspecificacaoGeracaoAgendamento
				.fabricarComTodosOsDados();
		especificacao.setDataInicio("01/01/2012");
		especificacao.setDataTermino("02/07/2012");
		
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_geracao_agendamento_validacao_com_erro_de_mais_de_um_itempara_agendar() {
		EspecificacaoGeracaoAgendamento especificacao = ContextoEspecificacaoGeracaoAgendamento
				.fabricarComTodosOsDados();
		especificacao.setPreCadastro(ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados());
		List<Grupo> grupos = new ArrayList<>();
		grupos.add(ContextoGrupo.fabricarGrupoComTodosOsDados());
		especificacao.setGrupos(grupos);

		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 2);
	}
}

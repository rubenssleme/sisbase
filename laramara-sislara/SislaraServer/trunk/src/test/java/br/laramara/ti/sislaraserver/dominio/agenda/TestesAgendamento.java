package br.laramara.ti.sislaraserver.dominio.agenda;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemana;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.fabricas.ContextoAgendamento;
import br.laramara.ti.sislaraserver.fabricas.ContextoPreCadastro;
import br.laramara.ti.sislaraserver.fabricas.ContextoUsuario;

public class TestesAgendamento {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void agendamento_construido_com_sucesso() {
		Long id = new Long(12);
		Usuario usuario = new Usuario();
		usuario.setId(id);
		PreCadastro preCadastro = new PreCadastro();
		preCadastro.setId(id);
		List<Grupo> grupos = new ArrayList<>();
		Grupo grupo = new Grupo();
		grupo.setId(id);
		grupos.add(grupo);
		String data = "31/12/2012";
		String horaInicio = "09:00";
		String horaTermino = "12:00";
		Profissional profissional = new Profissional(id, "Josep", "1234");
		Agendamento agendamento = new Agendamento();
		agendamento.setId(id);
		agendamento.setUsuario(usuario);
		agendamento.setPreCadastro(preCadastro);
		agendamento.setGrupos(grupos);
		agendamento.setProfissional(profissional);
		agendamento.setData(data);
		agendamento.setHorario(new Horario(horaInicio, horaTermino));

		Assert.assertEquals(agendamento.getId(), id);
		Assert.assertEquals(agendamento.getUsuario().getId(), id);
		Assert.assertEquals(agendamento.getPreCadastro().getId(), id);
		Assert.assertEquals(agendamento.getGrupos().get(0).getId(), id);
		Assert.assertEquals(agendamento.getProfissional().getId(), id);
		Assert.assertEquals(agendamento.getData(), data);
		Assert.assertEquals(agendamento.getDiaSemana(), DiaSemana.SEGUNDA);
		Assert.assertEquals(agendamento.getHorario().getHoraInicio(), horaInicio);
		Assert.assertEquals(agendamento.getHorario().getHoraTermino(), horaTermino);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void agendamento_com_erro_de_usuario_e_precadastro_simultaneamente_atribuidos() {

		Agendamento agendamento = ContextoAgendamento
				.fabricarAgendamentoComTodosOsDados();
		agendamento.setUsuario(ContextoUsuario
				.fabricarUsuarioComTodosOsDados());
		agendamento.setPreCadastro(ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados());
		agendamento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(agendamento.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void agendamento_sem_conflito() {

		String horaInicio = "08:00";
		String horaTermino = "09:00";
		Agendamento agendamentoExistente = ContextoAgendamento
				.fabricarAgendamentoComTodosOsDados();
		agendamentoExistente.setHorario(new Horario(horaInicio, horaTermino));

		String horaInicio2 = "09:00";
		String horaTermino2 = "10:00";
		Agendamento agendamentoParaInserir = ContextoAgendamento
				.fabricarAgendamentoComTodosOsDados();
		agendamentoParaInserir.setHorario(new Horario(horaInicio2, horaTermino2));

		Assert.assertFalse(agendamentoExistente
				.conflito(agendamentoParaInserir));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void agendamento_com_conflito_hora_inicio() {

		String horaInicio = "08:00";
		String horaTermino = "09:00";
		Agendamento agendamentoExistente = ContextoAgendamento
				.fabricarAgendamentoComTodosOsDados();
		agendamentoExistente.setHorario(new Horario(horaInicio, horaTermino));

		String horaInicio2 = "08:00";
		String horaTermino2 = "10:00";
		Agendamento agendamentoParaInserir = ContextoAgendamento
				.fabricarAgendamentoComTodosOsDados();
		agendamentoParaInserir.setHorario(new Horario(horaInicio2, horaTermino2));

		Assert.assertTrue(agendamentoExistente
				.conflito(agendamentoParaInserir));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void agendamento_com_conflito_hora_termino() {

		String horaInicio = "08:00";
		String horaTermino = "10:00";
		Agendamento agendamentoExistente = ContextoAgendamento
				.fabricarAgendamentoComTodosOsDados();
		agendamentoExistente.setHorario(new Horario(horaInicio, horaTermino));

		String horaInicio2 = "09:00";
		String horaTermino2 = "10:00";
		Agendamento agendamentoParaInserir = ContextoAgendamento
				.fabricarAgendamentoComTodosOsDados();
		agendamentoParaInserir.setHorario(new Horario(horaInicio2, horaTermino2));

		Assert.assertTrue(agendamentoExistente
				.conflito(agendamentoParaInserir));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void agendamento_com_conflito_hora_inicio_e_termino() {

		String horaInicio = "08:01";
		String horaTermino = "09:00";
		Agendamento agendamentoExistente = ContextoAgendamento
				.fabricarAgendamentoComTodosOsDados();
		agendamentoExistente.setHorario(new Horario(horaInicio, horaTermino));

		String horaInicio2 = "08:00";
		String horaTermino2 = "08:59";
		Agendamento agendamentoParaInserir = ContextoAgendamento
				.fabricarAgendamentoComTodosOsDados();
		agendamentoParaInserir.setHorario(new Horario(horaInicio2, horaTermino2));

		Assert.assertTrue(agendamentoExistente
				.conflito(agendamentoParaInserir));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void agendamento_com_status_disponivel() {
		Agendamento agendamento = new Agendamento();

		Assert.assertEquals(agendamento.getStatusAtual(),
				StatusAgendamento.DISPONIVEL);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void agendamento_com_status_agendado() {
		Agendamento agendamento = new Agendamento();
		agendamento.setUsuario(ContextoUsuario.fabricarUsuarioComTodosOsDados());

		Assert.assertEquals(agendamento.getStatusAtual(),
				StatusAgendamento.AGENDADO);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void agendamento_com_status_cancelado() {
		Agendamento agendamento = new Agendamento();
		agendamento.setMotivoCancelamento(new MotivoCancelamento(new Long(1), "Ligou e cancelou"));

		Assert.assertEquals(agendamento.getStatusAtual(),
				StatusAgendamento.CANCELADO);
	}
}

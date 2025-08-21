package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaAgendaEditarJustificativaCancelamento;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import javax.swing.JDialog;


public class TelaAgendaEditarJustificativaCancelamento extends TelaAgendaEditarJustificativa{
    public TelaAgendaEditarJustificativaCancelamento(JDialog telaPai, AgendamentoDTO agendamentoDto){
        super(telaPai, agendamentoDto);
        controlador = new ControladorTelaAgendaEditarJustificativaCancelamento(this, agendamentoDto, jtfDataAgendamento, jtfHoraInicio, jtfHoraTermino, jtfProfissional, jcbMotivo, jepJustificativa);
        controlador.abrirTela();
    }
}

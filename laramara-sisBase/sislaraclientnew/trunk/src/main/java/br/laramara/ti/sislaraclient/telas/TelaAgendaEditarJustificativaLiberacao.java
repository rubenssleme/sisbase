package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaAgendaEditarJustificativaLiberacao;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import javax.swing.JDialog;

public class TelaAgendaEditarJustificativaLiberacao extends TelaAgendaEditarJustificativa{
    public TelaAgendaEditarJustificativaLiberacao(JDialog telaPai, AgendamentoDTO agendamentoDto){
        super(telaPai, agendamentoDto);
        controlador = new ControladorTelaAgendaEditarJustificativaLiberacao(this, agendamentoDto, jtfDataAgendamento, jtfHoraInicio, jtfHoraTermino, jtfProfissional, jcbMotivo, jepJustificativa);
        controlador.abrirTela();
    }
}

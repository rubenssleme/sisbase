package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class ControladorTelaAgendaEditarJustificativaCancelamento extends ControladorTelaAgendaEditarJustificativa {
    
    public ControladorTelaAgendaEditarJustificativaCancelamento(JDialog telaPai, AgendamentoDTO agendamentoDto, JTextField jtfDataAgendamento, JTextField jtfHoraInicio, JTextField jtfHoraTermino, JTextField jtfProfissional, JComboBox jcbMotivo, JEditorPane jepJustificativa){
        super(telaPai, agendamentoDto, jtfDataAgendamento, jtfHoraInicio, jtfHoraTermino, jtfProfissional, jcbMotivo, jepJustificativa);
    }
            
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
         return (ResultadoDTO) servicoSisLaraServer.cancelarAgendamento((AgendamentoDTO)objetoDto, Sessao.obterInstancia().obterToken());
    }
}

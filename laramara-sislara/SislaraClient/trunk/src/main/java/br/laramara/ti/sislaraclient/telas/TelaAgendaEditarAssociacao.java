
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaAgendaEditarAssociacao;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import javax.swing.JDialog;

public class TelaAgendaEditarAssociacao extends TelaAgendaEditar{
    
    public TelaAgendaEditarAssociacao(JDialog telaPai, AgendamentoDTO agendamentoDto){
        super(telaPai, true);
        controlador = new ControladorTelaAgendaEditarAssociacao(this, agendamentoDto, jtfNomePreCadastro, jcbProfissional, jliProfissionalAgendados, jcbReservaPara, jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade, jcbSetor, jcbLocalAtendimento, jftDataInicio, jftDataTermino, jcbDiaSemana, jftHoraInicio, jftHoraTermino, jepObservacoes, jpaDadosAgendamento, jpaDadosObservacoes);
        controlador.abrirTela();
    }
}

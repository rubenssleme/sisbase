
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoCopiaAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoEdicaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

public class ControladorTelaAgendaCopiarAgendamento extends ControladorTela{
    
    private JFormattedTextField jftData;
    private JFormattedTextField jftHoraInicio;
    private JFormattedTextField jftHoraTermino;
    
    private AgendamentoDTO agendamentoDto;
    
    public ControladorTelaAgendaCopiarAgendamento(JDialog telaPai, AgendamentoDTO agendamentoDto, JFormattedTextField jftData, JFormattedTextField jftHoraInicio, JFormattedTextField jftHoraTermino){
        super(telaPai);
        this.agendamentoDto = agendamentoDto;
        this.jftData = jftData;
        this.jftHoraInicio = jftHoraInicio;
        this.jftHoraTermino = jftHoraTermino;
    }
        
    public void efetuarCopiaAgendamento() {
        EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDTO = new EspecificacaoGeracaoCopiaAgendamentoDTO();
        especificacaoGeracaoCopiaAgendamentoDTO.setData(jftData.getText());
        especificacaoGeracaoCopiaAgendamentoDTO.setHorarioDto(new HorarioDTO(jftHoraInicio.getText(), jftHoraTermino.getText()));

        int resposta = JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO);
        if (resposta == JOptionPane.OK_OPTION) {
            try {
                ResultadoEdicaoAgendamentoDTO resultadoDto = servicoSisLaraServer.copiarAgendamento(especificacaoGeracaoCopiaAgendamentoDTO, agendamentoDto, Sessao.obterInstancia().obterToken());
                if (resultadoDto.sucesso()) {
                    Sessao.obterInstancia().armazenarDTO(Sessao.CHAVE_AGENDAMENTO, resultadoDto.obterObjetoDtoEditado());
                    JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultadoDto.obterMensagens());
                    fecharTela();
                } else {
                    JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoDto.obterMensagens());
                }
            } catch (Exception e) {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
                logger.fatal(e);
            }
        }
    }
}

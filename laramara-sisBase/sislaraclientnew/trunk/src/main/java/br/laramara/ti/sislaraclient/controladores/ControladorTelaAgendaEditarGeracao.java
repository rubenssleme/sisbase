
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoGeracaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorTelaAgendaEditarGeracao extends ControladorTelaAgendaEditar {
 
    public ControladorTelaAgendaEditarGeracao(JDialog telaPai, JTextField jtfNomePreCadastro, JComboBox jcbProfissional, JList jliProfissionaisAgendados, JComboBox jcbReservaPara, JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade, JComboBox jcbSetor, JComboBox jcbLocalAtendimento, JFormattedTextField jftDataInicio, JFormattedTextField jftDataTermino, JComboBox jcbDiaSemana, JFormattedTextField jftHoraInicio, JFormattedTextField jftHoraTermino, JEditorPane jepObs){
        super(telaPai, jtfNomePreCadastro, jcbProfissional, jliProfissionaisAgendados, jcbReservaPara, jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade, jcbSetor, jcbLocalAtendimento, jftDataInicio, jftDataTermino, jcbDiaSemana, jftHoraInicio, jftHoraTermino, jepObs);
    }
    
    @Override
    public void salvar() {
        int resposta = JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO);
        if (resposta == JOptionPane.OK_OPTION) {
            EspecificacaoGeracaoAgendamentoDTO especificacao = obterEspecificacao();
            try {
                ResultadoGeracaoAgendamentoDTO resultado = servicoSisLaraServer.gerarAgendamento(especificacao, Sessao.obterInstancia().obterToken());
                if (resultado.sucesso()) {
                    Sessao.obterInstancia().armazenarDTO(Sessao.CHAVE_LISTAGEM_AGENDAMENTOS, resultado);
                    JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultado.obterMensagens());
                    fecharTela();
                } else {
                    JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
                }
            } catch (Exception ex) {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_GERACAO_AGENDAMENTO);
                logger.error(JOptionPanePersonalizado.ERRO_NA_GERACAO_AGENDAMENTO + ex.getMessage());
            }
        }
    }
}

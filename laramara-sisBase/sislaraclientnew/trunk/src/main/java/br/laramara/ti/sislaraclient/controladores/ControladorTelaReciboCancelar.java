
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoEdicaoReciboDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class ControladorTelaReciboCancelar extends ControladorTela {
    
    private JTextField jftNumeroRecibo; 
    private JEditorPane jepMotivoCancelamento;
    
    public ControladorTelaReciboCancelar(JDialog telaPai, JTextField jftNumeroRecibo, JEditorPane jepMotivoCancelamento){
        super(telaPai);
        this.jftNumeroRecibo = jftNumeroRecibo;
        this.jepMotivoCancelamento = jepMotivoCancelamento;
    }

    public void abrirRecibo() {
        try {
            ResultadoEdicaoReciboDTO resultadoEdicaoReciboDTO = servicoSisLaraServer.cancelarRecibo(Long.valueOf(jftNumeroRecibo.getText()), jepMotivoCancelamento.getText(), Sessao.obterInstancia().obterToken());
            if (resultadoEdicaoReciboDTO.sucesso()) {
                JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultadoEdicaoReciboDTO.obterMensagens());
                fecharTela();
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoEdicaoReciboDTO.obterMensagens());
            }
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_CANCELAMENTO_RECIBO + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_CANCELAMENTO_RECIBO + "\nDetalhes: " + e.getMessage());
        }
    }
}


package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class ControladorTelaRelatorioRecibo extends ControladorTela {

    private JTextField jtfNumeroRecibo;
    
    public ControladorTelaRelatorioRecibo(JDialog telaPai, JTextField jtfNumeroRecibo){
        super(telaPai);
        this.jtfNumeroRecibo = jtfNumeroRecibo;
    }
    
    public void exibir() {
         try {
            exibir(servicoSisLaraServer.obterRelatorioRecibo(Long.valueOf(jtfNumeroRecibo.getText()), Sessao.obterInstancia().obterToken()));
            fecharTela();
        } catch (Exception ex) {
            logger.error("Erro durante solicitação de relatório. \nDetalhes:" + ex);
        }
    }
}

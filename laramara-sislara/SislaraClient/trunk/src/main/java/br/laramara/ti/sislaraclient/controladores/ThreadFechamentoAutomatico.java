package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.Configuracoes;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import org.jdesktop.application.FrameView;

public class ThreadFechamentoAutomatico extends ControladorTela implements Runnable {

    public ThreadFechamentoAutomatico(FrameView frame) {
        super(frame.getFrame());
    }
    
    @Override
    public void run() {
         while (true) {
            try {
                if(servicoSisLaraServer.fechamentoAutomaticoClientAtivado()){
                    JOptionPanePersonalizado.mostrarTelaInformacao(telaPai, JOptionPanePersonalizado.INFORMACAO_FECHAMENTO_AUTOMATICO);
                    System.exit(0);
                }
                Thread.sleep(Configuracoes.INTERVALO_VERIFICACAO_FECHAMENTO_AUTOMATICO);
            } catch (Exception ex) {
                logger.fatal("Erro durante operação de fechamento automatico. \nDetalhes: " + ex);
            }
        }
    }
}

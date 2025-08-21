package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaAviso;
import br.laramara.ti.sislaraclient.utilitarios.Configuracoes;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import org.jdesktop.application.FrameView;

public class ThreadTelaAviso extends ControladorTela implements Runnable {

    private boolean possuiMensagemDisponivel = false;
    private final JDialog tela;

    public ThreadTelaAviso(FrameView frame) {
        super(frame.getFrame());
        tela = new TelaAviso(frame.getFrame(), this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                possuiMensagemDisponivel = servicoSisLaraServer.exibirAvisoDeAtualizacao(Sessao.obterInstancia().obterToken());
                if (possuiMensagemDisponivel) {
                    tela.setVisible(true);
                }
                Thread.sleep(Configuracoes.INTERVALO_VERIFICACAO_AVISO);
            } catch (Exception ex) {
                logger.fatal("Erro durante operação de aviso. \nDetalhes: " + ex);
            }
        }
    }

    public void confirmarLeituraDeAviso() {
        try {
            servicoSisLaraServer.confirmarLeituraDeAvisoDeAtualizacao(Sessao.obterInstancia().obterToken());
            tela.setVisible(false);
        } catch (Exception ex) {
            logger.fatal("Erro durante operação de aviso. \nDetalhes: " + ex);
        }
    }
}

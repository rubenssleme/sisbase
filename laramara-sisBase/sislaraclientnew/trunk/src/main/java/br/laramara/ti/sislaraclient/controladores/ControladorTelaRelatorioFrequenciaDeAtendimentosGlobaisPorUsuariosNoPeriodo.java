package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

public class ControladorTelaRelatorioFrequenciaDeAtendimentosGlobaisPorUsuariosNoPeriodo extends ControladorTela {

    private JTextArea jtaProntuarios;
    private JFormattedTextField jftDataInicio;
    private JFormattedTextField jftDataTermino;
    
    public ControladorTelaRelatorioFrequenciaDeAtendimentosGlobaisPorUsuariosNoPeriodo(JDialog telaPai, JTextArea jtaProntuarios, JFormattedTextField jftDataInicio, JFormattedTextField jftDataTermino)
    {
        super(telaPai);
        this.jtaProntuarios = jtaProntuarios;
        this.jftDataInicio = jftDataInicio;
        this.jftDataTermino = jftDataTermino;
    }

    public void exibir() {
        try {
            exibir(servicoSisLaraServer.gerarRelatorioFrequenciasDeAtendimentoGlobaisPorUsuarioNoPeriodo(jtaProntuarios.getText(), jftDataInicio.getText(), jftDataTermino.getText(), Sessao.obterInstancia().obterToken()));
        } catch (Exception ex) {
            logger.error("Erro durante solicitação de relatório. \nDetalhes:" + ex);
        }
    }
}

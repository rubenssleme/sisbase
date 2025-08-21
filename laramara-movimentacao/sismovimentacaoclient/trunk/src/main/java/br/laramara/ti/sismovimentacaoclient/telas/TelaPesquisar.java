package br.laramara.ti.sismovimentacaoclient.telas;

import br.laramara.ti.sismovimentacaoclient.modelos.ModeloTabela;
import javax.swing.JDialog;
import javax.swing.JFrame;

public abstract class TelaPesquisar extends JDialog {

    public TelaPesquisar(JFrame telaPai) {
        super(telaPai, true);
    }
    
    public TelaPesquisar(JDialog telaPai) {
        super(telaPai, true);
    }

    protected abstract ModeloTabela obterModeloTabela();
}

package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaGeren;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaGerenContribuintes;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaContribuinte;
import javax.swing.JFrame;

public class TelaGerenContribuintes extends TelaGeren {

    public TelaGerenContribuintes(JFrame telaPai){
        super("Contribuintes", telaPai);
    }
    
    @Override
    protected ControladorTelaGeren obterControlador() {
        return new ControladorTelaGerenContribuintes(this, jtaObjetosDto, jcbTipoPesquisa, jtfDadosPesquisar);
    }

    @Override
    protected ModeloTabela obterModeloTabela() {
        return new ModeloTabelaContribuinte();
    }
}

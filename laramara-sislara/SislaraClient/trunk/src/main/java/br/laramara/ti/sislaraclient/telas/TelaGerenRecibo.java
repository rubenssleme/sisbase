
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaGeren;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaGerenRecibo;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaRecibo;
import javax.swing.JFrame;

public class TelaGerenRecibo extends TelaGeren {

    public TelaGerenRecibo(JFrame telaPai){
        super("Recibos", telaPai);
    }
    
    @Override
    protected ControladorTelaGeren obterControlador() {
        return new ControladorTelaGerenRecibo(this, jtaObjetosDto, jcbTipoPesquisa, jtfDadosPesquisar);
    }

    @Override
    protected ModeloTabela obterModeloTabela() {
        return new ModeloTabelaRecibo();
    }
}

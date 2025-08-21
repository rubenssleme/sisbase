
package br.laramara.ti.sismovimentacaoclient.telas;

import br.laramara.ti.sismovimentacaoclient.controladores.ControladorTelaGeren;
import br.laramara.ti.sismovimentacaoclient.controladores.ControladorTelaGerenContaAcesso;
import br.laramara.ti.sismovimentacaoclient.modelos.ModeloTabela;
import br.laramara.ti.sismovimentacaoclient.modelos.ModeloTabelaContaAcesso;
import javax.swing.JFrame;


public class TelaGerenContaAcesso extends TelaGeren {
    
    public TelaGerenContaAcesso (JFrame telaPai){
        super("Contas Acesso", telaPai);
    }

    @Override
    protected ControladorTelaGeren obterControlador() {
        return new ControladorTelaGerenContaAcesso(this, jtaObjetosDto, jcbTipoPesquisa, jtfDadosPesquisar);
    }

    @Override
    protected ModeloTabela obterModeloTabela() {
        return new ModeloTabelaContaAcesso();
    }
}

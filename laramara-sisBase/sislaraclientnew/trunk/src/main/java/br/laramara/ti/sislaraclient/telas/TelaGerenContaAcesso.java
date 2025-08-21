
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaGeren;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaGerenContaAcesso;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaContaAcesso;
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

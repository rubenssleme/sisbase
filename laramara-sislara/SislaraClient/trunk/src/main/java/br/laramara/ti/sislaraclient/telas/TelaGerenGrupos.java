package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaGeren;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaGerenGrupos;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaGrupo;
import javax.swing.JFrame;

public class TelaGerenGrupos extends TelaGeren {
    
    public TelaGerenGrupos (JFrame telaPai){
        super("Grupos", telaPai);
    }

    @Override
    protected ControladorTelaGeren obterControlador() {
        return new ControladorTelaGerenGrupos(this, jtaObjetosDto, jcbTipoPesquisa, jtfDadosPesquisar);
    }

    @Override
    protected ModeloTabela obterModeloTabela() {
        return new ModeloTabelaGrupo();
    }
    
}

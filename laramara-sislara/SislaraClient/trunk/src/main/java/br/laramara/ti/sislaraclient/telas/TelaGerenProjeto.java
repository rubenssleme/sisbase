package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaGeren;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaGerenProjeto;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaProjeto;
import javax.swing.JFrame;


public class TelaGerenProjeto extends TelaGeren {

    public TelaGerenProjeto (JFrame telaPai){
        super("Projetos", telaPai);
    }
            
    @Override
    protected ControladorTelaGeren obterControlador() {
        return new ControladorTelaGerenProjeto(this, jtaObjetosDto, jcbTipoPesquisa, jtfDadosPesquisar);
    }

    @Override
    protected ModeloTabela obterModeloTabela() {
        return new ModeloTabelaProjeto();
    }
    
    
}

package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaGeren;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaGerenInstituicoes;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaInstituicoes;
import javax.swing.JFrame;

public class TelaGerenInstituicoes extends TelaGeren {

    public TelaGerenInstituicoes(JFrame telaPai){
        super("Instituições de Ensino", telaPai);
    }
    
    @Override
    protected ControladorTelaGeren obterControlador() {
        return new ControladorTelaGerenInstituicoes(this, jtaObjetosDto, jcbTipoPesquisa, jtfDadosPesquisar);
    }

    @Override
    protected ModeloTabela obterModeloTabela() {
        return new ModeloTabelaInstituicoes();
    }
}

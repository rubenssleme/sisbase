package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaGeren;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaGerenPerfil;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaPerfil;
import javax.swing.JFrame;

public class TelaGerenPerfil extends TelaGeren {

    public TelaGerenPerfil(JFrame telaPai){
        super("Perfil", telaPai);
    }
    
    @Override
    protected ControladorTelaGeren obterControlador() {
        return new ControladorTelaGerenPerfil(this, jtaObjetosDto, jcbTipoPesquisa, jtfDadosPesquisar);
    }

    @Override
    protected ModeloTabela obterModeloTabela() {
        return new ModeloTabelaPerfil();
    }
}
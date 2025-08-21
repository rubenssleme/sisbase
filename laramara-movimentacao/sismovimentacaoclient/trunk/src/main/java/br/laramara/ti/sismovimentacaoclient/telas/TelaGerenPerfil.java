package br.laramara.ti.sismovimentacaoclient.telas;

import br.laramara.ti.sismovimentacaoclient.controladores.ControladorTelaGeren;
import br.laramara.ti.sismovimentacaoclient.controladores.ControladorTelaGerenPerfil;
import br.laramara.ti.sismovimentacaoclient.modelos.ModeloTabela;
import br.laramara.ti.sismovimentacaoclient.modelos.ModeloTabelaPerfil;
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
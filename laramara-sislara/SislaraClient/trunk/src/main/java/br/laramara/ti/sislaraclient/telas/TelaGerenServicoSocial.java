package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaGeren;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaGerenUsuarios;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaUsuarios;
import javax.swing.JFrame;

public class TelaGerenServicoSocial extends TelaGeren {
   
    public TelaGerenServicoSocial(JFrame telaPai){
        super("Serviço Social", telaPai);
    }

    @Override
    protected ControladorTelaGeren obterControlador() {
        return new ControladorTelaGerenUsuarios(this, jtaObjetosDto, jcbTipoPesquisa, jtfDadosPesquisar);
    }

    @Override
    protected ModeloTabela obterModeloTabela() {
       return new ModeloTabelaUsuarios();
    }
}

package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaGeren;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaGerenPreCadastro;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaPreCadastro;
import javax.swing.JFrame;

public class TelaGerenPreCadastro extends TelaGeren{
    
    public TelaGerenPreCadastro (JFrame telaPai){
        super("Pré Cadastro", telaPai);
    }

    @Override
    protected ControladorTelaGeren obterControlador() {
        return new ControladorTelaGerenPreCadastro(this, jtaObjetosDto, jcbTipoPesquisa, jtfDadosPesquisar);
    }

    @Override
    protected ModeloTabela obterModeloTabela() {
       return new ModeloTabelaPreCadastro();
    }
}

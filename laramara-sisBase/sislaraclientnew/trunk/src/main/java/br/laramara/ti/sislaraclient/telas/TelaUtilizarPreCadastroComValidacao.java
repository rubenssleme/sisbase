package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaUtilizar;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaUtilizarPreCadastro;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaPreCadastro;
import javax.swing.JDialog;

public class TelaUtilizarPreCadastroComValidacao extends TelaUtilizar {
    
    public TelaUtilizarPreCadastroComValidacao(JDialog parent){
        super("Pré-Cadastro", parent);
    }

    @Override
    protected ControladorTelaUtilizar obterControlador() {
        return new ControladorTelaUtilizarPreCadastro(this, true, jcbTipoPesquisa, jtfDadosPesquisar, jtaListagem);
    }

    @Override
    protected ModeloTabela obterModeloTabela() {
        return new ModeloTabelaPreCadastro();
    }
}

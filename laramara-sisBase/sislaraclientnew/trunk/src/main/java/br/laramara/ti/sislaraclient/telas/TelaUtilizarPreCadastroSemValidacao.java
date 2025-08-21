
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaUtilizar;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaUtilizarPreCadastro;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaPreCadastro;
import javax.swing.JDialog;

public class TelaUtilizarPreCadastroSemValidacao extends TelaUtilizar {
    
    public TelaUtilizarPreCadastroSemValidacao(JDialog parent){
        super("Pré-Cadastro", parent);
    }

    @Override
    protected ControladorTelaUtilizar obterControlador() {
        return new ControladorTelaUtilizarPreCadastro(this, false, jcbTipoPesquisa, jtfDadosPesquisar, jtaListagem);
    }

    @Override
    protected ModeloTabela obterModeloTabela() {
        return new ModeloTabelaPreCadastro();
    }
}

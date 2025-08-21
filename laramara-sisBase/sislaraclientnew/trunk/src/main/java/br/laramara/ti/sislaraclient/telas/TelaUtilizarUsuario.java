
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaUtilizar;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaUtilizarUsuario;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaUsuarios;
import javax.swing.JDialog;

public class TelaUtilizarUsuario extends TelaUtilizar {

    public TelaUtilizarUsuario(JDialog parent){
        super("Usuário", parent);
    }
    
    @Override
    protected ControladorTelaUtilizar obterControlador() {
        return new ControladorTelaUtilizarUsuario(this, jcbTipoPesquisa, jtfDadosPesquisar, jtaListagem);
    }

    @Override
    protected ModeloTabela obterModeloTabela() {
        return new ModeloTabelaUsuarios();
    }
    
}

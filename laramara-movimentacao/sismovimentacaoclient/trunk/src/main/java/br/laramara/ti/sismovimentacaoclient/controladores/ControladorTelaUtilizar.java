package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaocommons.dtos.ChavePesquisaDTO;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;


public abstract class ControladorTelaUtilizar extends ControladorTelaPesquisar {
    
    protected JComboBox jcbTipoPesquisa;
    protected JTextField jtfDadosPesquisar;
    protected JTable jtaListagem;
    
    public ControladorTelaUtilizar(JDialog telaPai, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar, JTable jtaListagem){
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jtaListagem);
        this.jcbTipoPesquisa = jcbTipoPesquisa;
        this.jtfDadosPesquisar = jtfDadosPesquisar;
        this.jtaListagem = jtaListagem;
        inicializarComboPesquisa();
    }

    public void inicializarComboPesquisa() {
        for(ChavePesquisaDTO chave : obterEspecificacao().obterOpcoes()){
            jcbTipoPesquisa.addItem(chave);
        }
        jcbTipoPesquisa.requestFocusInWindow();
    }
    
    public void moverFocoNaTabulacaoOuUtilizar(KeyEvent evt){
        transferirFocoNaTabulacao(evt);
        if (teclaEnterPressionada(evt)){
            utilizar();
        }
    }

    public abstract void utilizar();
}

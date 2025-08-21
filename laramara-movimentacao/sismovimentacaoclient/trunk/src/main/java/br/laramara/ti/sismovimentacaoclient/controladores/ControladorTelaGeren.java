package br.laramara.ti.sismovimentacaoclient.controladores;


import br.laramara.ti.sismovimentacaoclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sismovimentacaocommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public abstract class ControladorTelaGeren extends ControladorTelaPesquisar implements SessaoUtilizavel{
    
    private JComboBox jcbTipoPesquisa;
    protected JTextField jtfDadosPesquisar;
    private JTable jta;
     
    public ControladorTelaGeren(JDialog telaPai, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar, JTable jta){
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jta);
        this.jcbTipoPesquisa = jcbTipoPesquisa;
        this.jtfDadosPesquisar = jtfDadosPesquisar;
        this.jta = jta;
        inicializarComboPesquisa();
    }
    
    public final void inicializarComboPesquisa(){
        for(ChavePesquisaDTO chave : obterEspecificacao().obterOpcoes()){
            jcbTipoPesquisa.addItem(chave);
        }
        jcbTipoPesquisa.requestFocusInWindow();
    }
        
    public void abrirTelaParaAlteracao(){
        if (estaComItemValidoSelecionado(jta)){
            abrirTelaParaEdicao(obterDtoSelecionado(jta));
            if (Sessao.obterInstancia().possuiDto(obterChaveSessao())){
                adicionarDtoNoItemSelecionado(jta, (ModeloDTO) Sessao.obterInstancia().obterDto(obterChaveSessao()));
            }
        } else{
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.SELECIONAR_REGISTRO);
        }
    }
    
    public void moverFocoOuAbrirTelaDeAlteracao(KeyEvent evt){
        transferirFocoNaTabulacao(evt);
        if (teclaEnterPressionada(evt)){
            abrirTelaParaAlteracao();
        }
    }
    
    public abstract void abrirTelaParaInclusao();
    
    protected abstract void abrirTelaParaEdicao(ModeloDTO objetoDto);
        
}

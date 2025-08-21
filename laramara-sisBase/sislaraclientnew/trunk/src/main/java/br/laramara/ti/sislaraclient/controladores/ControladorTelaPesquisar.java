package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.EsperaUtils;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public abstract class ControladorTelaPesquisar extends ControladorTelaPesquisarBase{
    
    private JComboBox jcbTipoPesquisa;
    private JTextField jtfDadosPesquisar;
    private JTable jta;
    
    public ControladorTelaPesquisar(JDialog telaPai, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar, JTable jta){
        super(telaPai, jta);
        this.jcbTipoPesquisa = jcbTipoPesquisa;
        this.jtfDadosPesquisar = jtfDadosPesquisar;
        this.jta = jta;
        configurarColunasTabela();
    }
    
    public final void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jta);
    }
    
    public void efetuarPesquisa(){
        ChavePesquisaDTO chaveSelecionada = estaComItemValidoSelecionado(jcbTipoPesquisa) ? (ChavePesquisaDTO)jcbTipoPesquisa.getSelectedItem() : null;
        final EspecificacaoPesquisaDTO especificacao = obterEspecificacao();
        especificacao.adicionarParametro(chaveSelecionada, (Object)jtfDadosPesquisar.getText());
        Runnable comando = new Runnable() {
            @Override
            public void run() {
                pesquisar(especificacao);
            }
        };
        new EsperaUtils(comando, (JDialog)telaPai).execute();
    }
    
    protected abstract EspecificacaoPesquisaDTO obterEspecificacao();
}

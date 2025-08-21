package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.TipoVinculoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ControladorTelaMailing extends ControladorTelaRelatorioApartirDeData {
        
    private final JFormattedTextField jftDataInicial;
    private final JFormattedTextField jftDataTermino;
    private final JComboBox jcbCategorias;
    private final JList jliCategorias;
        
    public ControladorTelaMailing(JDialog telaPai, JFormattedTextField jftDataInicial, JFormattedTextField jftDataTermino, JComboBox jcbCategorias, JList jliCategorias){
        super(telaPai);
        this.jftDataInicial = jftDataInicial;
        this.jftDataTermino = jftDataTermino;
        this.jcbCategorias = jcbCategorias;
        this.jliCategorias = jliCategorias;
        inicializarCategorias();
    }
    
    @Override
    protected ArquivoDTO obterRelatorio() throws RemoteException {
        return servicoSisLaraServer.gerarRelatorioMailingVisitaMonitorada(jftDataInicial.getText(), jftDataTermino.getText(), (List<TipoVinculoDTO>) obterDtos(jliCategorias), Sessao.obterInstancia().obterToken());
    }

    private void inicializarCategorias() {
        try {
            adicionarDtos(jcbCategorias, servicoSisLaraServer.obterListagemTipoVinculo().getObjetosDto());
        } catch (Exception ex) {
            logger.fatal("Erro durante inicialização de tipos de vinculos. Detalhes: " + ex);
        }
    }

    public void adicionarTipoVinculoSelecionado() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliCategorias, jcbCategorias);   
    }

    public void removerTipoVinculoSelecionado() {
        if ((estaComItemValidoSelecionado(jliCategorias) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliCategorias);
        }
    }

    public void abrirRelatorio() {
        try {
            exibirArquivo(obterRelatorio());
        } catch (Exception ex) {
            logger.fatal("Erro durante abertura de relatório. Detalhes: "+ ex);
        }
    }
}

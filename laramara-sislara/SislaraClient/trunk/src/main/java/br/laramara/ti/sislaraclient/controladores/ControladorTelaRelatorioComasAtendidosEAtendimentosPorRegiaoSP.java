package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

public class ControladorTelaRelatorioComasAtendidosEAtendimentosPorRegiaoSP extends ControladorTelaRelatorioApartirDeData {
        
    private JFormattedTextField jftDataInicial;
    private JFormattedTextField jftDataTermino;
        
    public ControladorTelaRelatorioComasAtendidosEAtendimentosPorRegiaoSP(JDialog telaPai, JFormattedTextField jftDataInicial, JFormattedTextField jftDataTermino){
        super(telaPai);
        this.jftDataInicial = jftDataInicial;
        this.jftDataTermino = jftDataTermino;
    }
    
    @Override
    protected ArquivoDTO obterRelatorio() throws RemoteException {
        return servicoSisLaraServer.gerarRelatorioComasAtendidosAtendimentosPorRegiaoSP(jftDataInicial.getText(), jftDataTermino.getText(), Sessao.obterInstancia().obterToken());
    }
    
}

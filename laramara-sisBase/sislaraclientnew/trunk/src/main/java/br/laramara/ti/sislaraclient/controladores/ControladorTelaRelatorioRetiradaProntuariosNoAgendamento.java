package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

public class ControladorTelaRelatorioRetiradaProntuariosNoAgendamento extends ControladorTelaRelatorioApartirDeData {

    private JFormattedTextField jftData;
    
    public ControladorTelaRelatorioRetiradaProntuariosNoAgendamento(JDialog telaPai, JFormattedTextField jftData){
        super(telaPai);
        this.jftData = jftData;
    }
    
    @Override
    protected ArquivoDTO obterRelatorio() throws RemoteException {
        return servicoSisLaraServer.gerarRelatorioRetiradaProntuariosNoAgendamento(jftData.getText(), Sessao.obterInstancia().obterToken());
    }
}

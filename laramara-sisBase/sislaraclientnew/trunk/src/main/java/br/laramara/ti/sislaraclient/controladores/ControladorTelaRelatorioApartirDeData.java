package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import java.rmi.RemoteException;
import javax.swing.JDialog;

public abstract class ControladorTelaRelatorioApartirDeData  extends ControladorTela {

    protected ControladorTelaRelatorioApartirDeData(JDialog telaPai){
        super(telaPai);
    }

    public void exibir() {
        try {
            exibir(obterRelatorio());
        } catch (Exception ex) {
            logger.error("Erro durante solicitação de relatório. \nDetalhes:" + ex);
        }
    }

    protected abstract ArquivoDTO obterRelatorio() throws RemoteException; 
}

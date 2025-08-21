
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

public abstract class ControladorRelatorioApartirDeProntuario extends ControladorTela {

    protected JFormattedTextField jftProntuario;

    public ControladorRelatorioApartirDeProntuario(JDialog telaPai, JFormattedTextField jftProntuario){
        super(telaPai);
        this.jftProntuario = jftProntuario;
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

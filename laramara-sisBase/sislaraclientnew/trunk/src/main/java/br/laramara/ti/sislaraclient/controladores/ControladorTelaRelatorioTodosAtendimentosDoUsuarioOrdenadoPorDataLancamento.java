
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

public class ControladorTelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento extends ControladorRelatorioApartirDeProntuario {
    
    public ControladorTelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento(JDialog telaPai, JFormattedTextField jftProntuario){
        super(telaPai, jftProntuario);
    }

    @Override
    protected ArquivoDTO obterRelatorio() throws RemoteException {
        return servicoSisLaraServer.gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento(NumeroUtils.retornaLongoOuInvalido(jftProntuario.getText()), Sessao.obterInstancia().obterToken());
    }
}

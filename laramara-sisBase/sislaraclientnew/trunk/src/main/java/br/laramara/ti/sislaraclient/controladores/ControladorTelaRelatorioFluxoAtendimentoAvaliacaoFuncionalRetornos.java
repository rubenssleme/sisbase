/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

/**
 *
 * @author pbandeira
 */
public class ControladorTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos extends ControladorTelaRelatorioApartirDeData {
        
    private JFormattedTextField jftDataInicial;
    private JFormattedTextField jftDataTermino;
        
    public ControladorTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos(JDialog telaPai, JFormattedTextField jftDataInicial, JFormattedTextField jftDataTermino){
        super(telaPai);
        this.jftDataInicial = jftDataInicial;
        this.jftDataTermino = jftDataTermino;
    }
    
    @Override
    protected ArquivoDTO obterRelatorio() throws RemoteException {
        return servicoSisLaraServer.gerarRelatorioFluxoAtendimentoRetornosDeAvaliacaoFuncional(jftDataInicial.getText(), jftDataTermino.getText(), Sessao.obterInstancia().obterToken());
    }
    
}

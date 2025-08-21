
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoEdicaoSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;

public class ControladorTelaSolicitacaoRelatorioEmissaoProfissional extends ControladorTelaSolicitacaoRelatorioObsQuantidadeRelatorios {
    public ControladorTelaSolicitacaoRelatorioEmissaoProfissional(JDialog telaPai, SolicitacaoRelatorioDTO solicitacaoRelatorioDto, JEditorPane jepObs, JFormattedTextField jftQuantidadeRelatorios){
        super(telaPai, solicitacaoRelatorioDto, jepObs, jftQuantidadeRelatorios);
    }
    
    private SolicitacaoRelatorioDTO obterSolicitacaoRelatorioDTO(){
        solicitacaoRelatorioDto.setObs(jepObs.getText());
        solicitacaoRelatorioDto.setQuantidadeRelatoriosEmitidos(jftQuantidadeRelatorios.getText());
        return solicitacaoRelatorioDto;
    }
    
    @Override
    protected ResultadoEdicaoSolicitacaoRelatorioDTO efetuarOperacao() throws RemoteException {
        return servicoSisLaraServer.efetuarEmissaoProfissional(obterSolicitacaoRelatorioDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected String obterChave() {
        return Sessao.CHAVE_SOLICITACAO_RELATORIO;
    }
}

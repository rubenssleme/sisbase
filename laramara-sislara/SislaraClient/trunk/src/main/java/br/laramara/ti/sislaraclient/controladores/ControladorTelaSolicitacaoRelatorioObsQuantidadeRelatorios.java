package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoEdicaoSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;

public abstract class ControladorTelaSolicitacaoRelatorioObsQuantidadeRelatorios extends ControladorTela{
    
    protected JEditorPane jepObs;
    protected JFormattedTextField jftQuantidadeRelatorios;
    protected SolicitacaoRelatorioDTO solicitacaoRelatorioDto;

    public ControladorTelaSolicitacaoRelatorioObsQuantidadeRelatorios(JDialog telaPai, SolicitacaoRelatorioDTO solicitacaoRelatorioDto, JEditorPane jepObs, JFormattedTextField jftQuantidadeRelatorios) {
        super(telaPai);
        this.jepObs = jepObs;
        this.jftQuantidadeRelatorios = jftQuantidadeRelatorios;
        this.solicitacaoRelatorioDto = solicitacaoRelatorioDto;
        carregarCampos();
    }

    public void salvar() {
        try {
            ResultadoEdicaoSolicitacaoRelatorioDTO resultadoEdicaoSolicitacaoRelatorioDto =  efetuarOperacao();
            if (resultadoEdicaoSolicitacaoRelatorioDto.sucesso()){
                Sessao.obterInstancia().armazenarDTO(obterChave(), resultadoEdicaoSolicitacaoRelatorioDto.obterObjetoDtoEditado());
                JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultadoEdicaoSolicitacaoRelatorioDto.obterMensagens());
                fecharTela();
            }else{
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoEdicaoSolicitacaoRelatorioDto.obterMensagens());
            }
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_EDICAO_SOLICITACAO_RELATORIO);
            logger.error(JOptionPanePersonalizado.ERRO_EDICAO_SOLICITACAO_RELATORIO + ex.getMessage());
        }
    }

    protected abstract ResultadoEdicaoSolicitacaoRelatorioDTO efetuarOperacao() throws RemoteException;
    
    protected abstract String obterChave();

    private void carregarCampos() {
        jepObs.setText(solicitacaoRelatorioDto.getObs());
    }
}

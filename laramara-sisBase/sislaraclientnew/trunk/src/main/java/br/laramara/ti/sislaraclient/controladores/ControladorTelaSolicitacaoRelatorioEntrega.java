package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoEdicaoSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemStatusSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.StatusSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;

public class ControladorTelaSolicitacaoRelatorioEntrega extends ControladorTelaSolicitacaoRelatorioObsQuantidadeRelatorios {
    
    private ResultadoListagemStatusSolicitacaoRelatorioDTO resultadoListagemStatusSolicitacaoRelatorioDTO;
    
    private JComboBox jcbStatus;
    
    public ControladorTelaSolicitacaoRelatorioEntrega(JDialog telaPai, SolicitacaoRelatorioDTO solicitacaoRelatorioDto, JEditorPane jepObs, JFormattedTextField jftQuantidadeRelatorios, JComboBox jcbStatus){
        super(telaPai, solicitacaoRelatorioDto, jepObs, jftQuantidadeRelatorios);
        this.jcbStatus = jcbStatus;
        inicializarCombosDadosAuxiliares();      
    }
    
    private SolicitacaoRelatorioDTO obterSolicitacaoRelatorioDTO(){
        solicitacaoRelatorioDto.setObs(jepObs.getText());
        solicitacaoRelatorioDto.setQuantidadeRelatoriosEntregues(jftQuantidadeRelatorios.getText());
        solicitacaoRelatorioDto.setStatusDaEntrega((StatusSolicitacaoRelatorioDTO)obterDtoSelecionado(jcbStatus));
        return solicitacaoRelatorioDto;
    }
    
    @Override
    protected ResultadoEdicaoSolicitacaoRelatorioDTO efetuarOperacao() throws RemoteException {
        return servicoSisLaraServer.efetuarEntrega(obterSolicitacaoRelatorioDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected String obterChave() {
        return Sessao.CHAVE_SOLICITACAO_RELATORIO;
    }

    private void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbStatus, resultadoListagemStatusSolicitacaoRelatorioDTO.getObjetosDto());  
    }

    private void obterDadosAuxiliares() {
        try {            
            resultadoListagemStatusSolicitacaoRelatorioDTO = servicoSisLaraServer.obterListagemStatusEntrega();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
}

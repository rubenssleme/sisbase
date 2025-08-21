package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaSolicitacaoRelatorioCancelamento;
import br.laramara.ti.sislaraclient.infra.PermissaoDeTelas;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import javax.swing.JDialog;

public class TelaSolicitacaoRelatorioCancelamento extends TelaSolicitacaoRelatorioObsQuantidadeRelatorios {
    
    public TelaSolicitacaoRelatorioCancelamento(JDialog telaPai, SolicitacaoRelatorioDTO solicitacaoRelatorioDto){
        super(telaPai, "Cancelamento de Solicitação de Relatório", solicitacaoRelatorioDto);
        jpaQuantidadeRelatorios.setVisible(false);
        controlador = new ControladorTelaSolicitacaoRelatorioCancelamento(this, solicitacaoRelatorioDto, jepObs, jftQuantidadeRelatorios);
        controlador.abrirSomenteSeHouverPermissao(PermissaoDeTelas.SOLICITACAO_RELATORIO_TELA_EDICAO_VISUALIZAR);
    }
}

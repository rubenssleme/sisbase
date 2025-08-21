
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaSolicitacaoRelatorioEntrega;
import br.laramara.ti.sislaraclient.infra.PermissaoDeTelas;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import javax.swing.JDialog;

public class TelaSolicitacaoRelatorioEntrega extends TelaSolicitacaoRelatorioObsQuantidadeRelatorios {
    
    public TelaSolicitacaoRelatorioEntrega(JDialog telaPai, SolicitacaoRelatorioDTO solicitacaoRelatorioDto){
        super(telaPai, "Entrega do Relatório", solicitacaoRelatorioDto);
        jpaStatus.setVisible(true);
        controlador = new ControladorTelaSolicitacaoRelatorioEntrega(this, solicitacaoRelatorioDto, jepObs, jftQuantidadeRelatorios, jcbStatusEntrega);
        controlador.abrirSomenteSeHouverPermissao(PermissaoDeTelas.SOLICITACAO_RELATORIO_TELA_EDICAO_VISUALIZAR);
    }
}

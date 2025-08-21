package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaSolicitacaoRelatorioEmissaoProfissional;
import br.laramara.ti.sislaraclient.infra.PermissaoDeTelas;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import javax.swing.JDialog;

public class TelaSolicitacaoRelatorioEmissaoProfissional extends TelaSolicitacaoRelatorioObsQuantidadeRelatorios {
    
    public TelaSolicitacaoRelatorioEmissaoProfissional(JDialog telaPai, SolicitacaoRelatorioDTO solicitacaoRelatorioDto){
        super(telaPai, "Emissão pelo Profissional", solicitacaoRelatorioDto);
        controlador = new ControladorTelaSolicitacaoRelatorioEmissaoProfissional(this, solicitacaoRelatorioDto, jepObs, jftQuantidadeRelatorios);
        controlador.abrirSomenteSeHouverPermissao(PermissaoDeTelas.SOLICITACAO_RELATORIO_TELA_EDICAO_VISUALIZAR);
    }
}

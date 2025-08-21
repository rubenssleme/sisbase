package br.laramara.ti.sismovimentacaoclient.telas;

import br.laramara.ti.sismovimentacaoclient.controladores.ControladorTelaStatusMovimentacaoAvancar;
import br.laramara.ti.sismovimentacaoclient.infra.PermissaoDeTelas;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import javax.swing.JDialog;

public class TelaStatusMovimentacaoAvancar extends TelaEditarStatus{
    
    public TelaStatusMovimentacaoAvancar(JDialog telaPai, MovimentacaoDTO movimentacaoDto){
        super(telaPai, "Avançar Status de Moviementação", false, movimentacaoDto);
        controlador = new ControladorTelaStatusMovimentacaoAvancar(this, movimentacaoDto, jcbStatus, jftData, jftHora);
        controlador.abrirSomenteSeHouverPermissao(PermissaoDeTelas.MOVIMENTACAO_TELA_EDICAO_VISUALIZAR);
    }
}

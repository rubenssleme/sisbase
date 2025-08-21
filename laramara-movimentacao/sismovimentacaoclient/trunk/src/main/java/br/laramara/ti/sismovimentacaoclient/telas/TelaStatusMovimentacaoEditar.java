package br.laramara.ti.sismovimentacaoclient.telas;

import br.laramara.ti.sismovimentacaoclient.controladores.ControladorTelaStatusMovimentacaoEditar;
import br.laramara.ti.sismovimentacaoclient.infra.PermissaoDeTelas;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import javax.swing.JDialog;

public class TelaStatusMovimentacaoEditar extends TelaEditarStatus{
    
    public TelaStatusMovimentacaoEditar(JDialog telaPai, MovimentacaoDTO movimentacaoDto){
        super(telaPai, "Mudar Status da Movimentação", true, movimentacaoDto);
        controlador = new ControladorTelaStatusMovimentacaoEditar(this, movimentacaoDto, jcbStatus, jftData, jftHora);
        controlador.abrirSomenteSeHouverPermissao(PermissaoDeTelas.MOVIMENTACAO_TELA_EDICAO_VISUALIZAR);
    }
}


package br.laramara.ti.sismovimentacaoclient.telas;

import br.laramara.ti.sismovimentacaoclient.controladores.ControladorTelaStatusMovimentacaoIncluir;
import br.laramara.ti.sismovimentacaoclient.infra.PermissaoDeTelas;
import javax.swing.JDialog;

public class TelaStatusMovimentacaoIncluir extends TelaEditarStatus{
    
    public TelaStatusMovimentacaoIncluir(JDialog telaPai){
        super(telaPai, "Inclusão de Movimentação", false, null);
        controlador = new ControladorTelaStatusMovimentacaoIncluir(this, jcbStatus, jftData, jftHora);
        controlador.abrirSomenteSeHouverPermissao(PermissaoDeTelas.MOVIMENTACAO_TELA_EDICAO_VISUALIZAR);
    }
}

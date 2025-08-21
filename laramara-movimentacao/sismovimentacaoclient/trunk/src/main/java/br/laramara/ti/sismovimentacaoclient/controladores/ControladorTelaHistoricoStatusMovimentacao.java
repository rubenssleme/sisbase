package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import javax.swing.JDialog;
import javax.swing.JEditorPane;

public class ControladorTelaHistoricoStatusMovimentacao extends ControladorTela {

    public ControladorTelaHistoricoStatusMovimentacao(JDialog telaPai, MovimentacaoDTO movimentacaoDto, JEditorPane jepHistoricoAtual, JEditorPane jepHistoricoLegado) {
        super(telaPai);
        carregarHistorico(movimentacaoDto, jepHistoricoAtual, jepHistoricoLegado);
    }

    private void carregarHistorico(MovimentacaoDTO movimentacaoDto, JEditorPane jepHistoricoAtual, JEditorPane jepHistoricoLegado) {
        try {
            jepHistoricoAtual.setText(movimentacaoDto.getHistoricoOperacoes());
            jepHistoricoLegado.setText(servicoSisMovimentacaoServer.obterHistoricoLegado(movimentacaoDto.getId()));
        } catch (Exception e) {
            mostrarTelaErroPesquisa();
            logger.fatal(e);
        }
    }
}

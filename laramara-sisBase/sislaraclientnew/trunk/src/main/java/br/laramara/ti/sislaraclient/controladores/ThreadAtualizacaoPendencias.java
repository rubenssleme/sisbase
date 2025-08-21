package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.Configuracoes;
import br.laramara.ti.sislaracommons.dtos.pendencia.PendenciaDTO;
import br.laramara.ti.sislaracommons.dtos.pendencia.ResultadoListagemPendenciaDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.util.List;
import javax.swing.JList;

public class ThreadAtualizacaoPendencias extends ControladorTela implements Runnable {

    private static boolean pesquisandoPendencias;
    private final JList jli;

    public ThreadAtualizacaoPendencias(JList jli) {
        this.jli = jli;
    }

    @Override
    public void run() {
        while (true) {
            if (!pesquisandoPendencias && jli.isFocusOwner()) {
                try {
                    pesquisandoPendencias = true;
                    ResultadoListagemPendenciaDTO resultadoListagemPendenciaDTO = servicoSisLaraServer.obterListagemPendencia(Sessao.obterInstancia().obterToken());
                    adicionarESelecionarDto(resultadoListagemPendenciaDTO.getObjetosDto(), jli);
                    pesquisandoPendencias = false;
                    Thread.sleep(Configuracoes.INTERVALO_VERIFICACAO_PENDENCIAS);
                } catch (Exception ex) {
                    logger.fatal("Erro durante operação de listagem de pendencias. \nDetalhes: " + ex);
                }
            }
        }
    }

    private void adicionarESelecionarDto(List<PendenciaDTO> objetosDto, JList jliPendencias) {
        int ultimaSelecionada = 0;
        if (estaComItemValidoSelecionado(jliPendencias)) {
            ultimaSelecionada = jliPendencias.getSelectedIndex();
        }
        adicionarDtos(objetosDto, jliPendencias);
        jliPendencias.setSelectedIndex(ultimaSelecionada);
    }
}

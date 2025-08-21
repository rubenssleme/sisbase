package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalCasosNovos;
import javax.swing.JFrame;

public class TelaRelatorioFluxoAtendimentoAvaliacaoFuncionalCasosNovos extends TelaRelatorioApartirDeData {

    public TelaRelatorioFluxoAtendimentoAvaliacaoFuncionalCasosNovos(JFrame telaPai) {
        super(telaPai, "Relatório do fluxo de atendimentos da avaliação funcional dos casos novos.", true);
    }

    @Override
    protected ControladorTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalCasosNovos obterControlador() {
        return new ControladorTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalCasosNovos(this, jftDataInicio, jftDataTermino);
    }
}

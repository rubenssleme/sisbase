package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos;
import javax.swing.JFrame;

public class TelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos extends TelaRelatorioApartirDeData {

    public TelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos(JFrame telaPai) {
        super(telaPai, "Relatório do fluxo de atendimentos da avaliação funcional dos retornos.", true);
    }

    @Override
    protected ControladorTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos obterControlador() {
        return new ControladorTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos(this, jftDataInicio, jftDataTermino);
    }
}

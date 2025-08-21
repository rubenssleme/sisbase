package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos;
import javax.swing.JFrame;

public class TelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos extends TelaRelatorioApartirDeData {

    public TelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos(JFrame telaPai) {
        super(telaPai, "Relat�rio do fluxo de atendimentos da avalia��o funcional dos retornos.", true);
    }

    @Override
    protected ControladorTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos obterControlador() {
        return new ControladorTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos(this, jftDataInicio, jftDataTermino);
    }
}

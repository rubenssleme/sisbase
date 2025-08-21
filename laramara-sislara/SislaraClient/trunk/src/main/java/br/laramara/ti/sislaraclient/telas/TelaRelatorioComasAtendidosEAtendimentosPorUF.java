package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioComasAtendidosEAtendimentosPorUF;
import javax.swing.JFrame;

public class TelaRelatorioComasAtendidosEAtendimentosPorUF extends TelaRelatorioApartirDeData {

    public TelaRelatorioComasAtendidosEAtendimentosPorUF(JFrame telaPai) {
        super(telaPai, "Relatório do COMAS de Atendidos e Atendimentos por UF", true);
    }

    @Override
    protected ControladorTelaRelatorioComasAtendidosEAtendimentosPorUF obterControlador() {
        return new ControladorTelaRelatorioComasAtendidosEAtendimentosPorUF(this, jftDataInicio, jftDataTermino);
    }
}

package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioComasAtendidosPorIdadeEGenero;
import javax.swing.JFrame;

public class TelaRelatorioComasAtendidosPorIdadeEGenero extends TelaRelatorioApartirDeData {

    public TelaRelatorioComasAtendidosPorIdadeEGenero(JFrame telaPai) {
        super(telaPai, "Relatório do COMAS de Atendidos por idade e gênero", true);
    }

    @Override
    protected ControladorTelaRelatorioComasAtendidosPorIdadeEGenero obterControlador() {
        return new ControladorTelaRelatorioComasAtendidosPorIdadeEGenero(this, jftDataInicio, jftDataTermino);
    }
}

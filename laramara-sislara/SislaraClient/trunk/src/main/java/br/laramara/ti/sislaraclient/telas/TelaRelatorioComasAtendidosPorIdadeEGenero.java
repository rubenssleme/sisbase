package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioComasAtendidosPorIdadeEGenero;
import javax.swing.JFrame;

public class TelaRelatorioComasAtendidosPorIdadeEGenero extends TelaRelatorioApartirDeData {

    public TelaRelatorioComasAtendidosPorIdadeEGenero(JFrame telaPai) {
        super(telaPai, "Relat�rio do COMAS de Atendidos por idade e g�nero", true);
    }

    @Override
    protected ControladorTelaRelatorioComasAtendidosPorIdadeEGenero obterControlador() {
        return new ControladorTelaRelatorioComasAtendidosPorIdadeEGenero(this, jftDataInicio, jftDataTermino);
    }
}

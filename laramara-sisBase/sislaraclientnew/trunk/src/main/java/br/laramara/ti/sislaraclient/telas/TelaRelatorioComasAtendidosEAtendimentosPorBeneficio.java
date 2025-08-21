package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioComasAtendidosEAtendimentosPorBeneficio;
import javax.swing.JFrame;

public class TelaRelatorioComasAtendidosEAtendimentosPorBeneficio extends TelaRelatorioApartirDeData {

    public TelaRelatorioComasAtendidosEAtendimentosPorBeneficio(JFrame telaPai) {
        super(telaPai, "Relat�rio do COMAS de Atendidos e Atendimentos por benef�cio", true);
    }

    @Override
    protected ControladorTelaRelatorioComasAtendidosEAtendimentosPorBeneficio obterControlador() {
        return new ControladorTelaRelatorioComasAtendidosEAtendimentosPorBeneficio(this, jftDataInicio, jftDataTermino);
    }
}

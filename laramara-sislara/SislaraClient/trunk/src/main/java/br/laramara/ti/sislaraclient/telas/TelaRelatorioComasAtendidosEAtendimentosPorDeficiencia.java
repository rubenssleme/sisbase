package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioComasAtendidosEAtendimentosPorDeficiencia;
import javax.swing.JFrame;

public class TelaRelatorioComasAtendidosEAtendimentosPorDeficiencia extends TelaRelatorioApartirDeData {

    public TelaRelatorioComasAtendidosEAtendimentosPorDeficiencia(JFrame telaPai) {
        super(telaPai, "Relatório do COMAS de Atendidos e Atendimentos por deficiência", true);
    }

    @Override
    protected ControladorTelaRelatorioComasAtendidosEAtendimentosPorDeficiencia obterControlador() {
        return new ControladorTelaRelatorioComasAtendidosEAtendimentosPorDeficiencia(this, jftDataInicio, jftDataTermino);
    } 
}

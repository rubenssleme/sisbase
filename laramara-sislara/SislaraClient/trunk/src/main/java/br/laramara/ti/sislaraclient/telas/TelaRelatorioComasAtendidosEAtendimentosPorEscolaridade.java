package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioComasAtendidosEAtendimentosPorEscolaridade;
import javax.swing.JFrame;

public class TelaRelatorioComasAtendidosEAtendimentosPorEscolaridade extends TelaRelatorioApartirDeData {

    public TelaRelatorioComasAtendidosEAtendimentosPorEscolaridade(JFrame telaPai) {
        super(telaPai, "Relatório do COMAS de Atendidos e Atendimentos por escolaridade", true);
    }

    @Override
    protected ControladorTelaRelatorioComasAtendidosEAtendimentosPorEscolaridade obterControlador() {
        return new ControladorTelaRelatorioComasAtendidosEAtendimentosPorEscolaridade(this, jftDataInicio, jftDataTermino);
    } 
}

package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioComasAtendidosEAtendimentosPorTipoAtendimento;
import javax.swing.JFrame;


public class TelaRelatorioComasAtendidosEAtendimentosPorTipoAtendimento extends TelaRelatorioApartirDeData {

    public TelaRelatorioComasAtendidosEAtendimentosPorTipoAtendimento(JFrame telaPai) {
        super(telaPai, "Relatório do COMAS de Atendidos e Atendimentos por tipo de atendimento", true);
    }

    @Override
    protected ControladorTelaRelatorioComasAtendidosEAtendimentosPorTipoAtendimento obterControlador() {
        return new ControladorTelaRelatorioComasAtendidosEAtendimentosPorTipoAtendimento(this, jftDataInicio, jftDataTermino);
    } 
}

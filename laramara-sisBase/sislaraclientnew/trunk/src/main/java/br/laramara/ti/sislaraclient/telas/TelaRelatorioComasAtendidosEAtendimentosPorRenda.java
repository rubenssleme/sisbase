
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioComasAtendidosEAtendimentosPorRenda;
import javax.swing.JFrame;

public class TelaRelatorioComasAtendidosEAtendimentosPorRenda extends TelaRelatorioApartirDeData {

    public TelaRelatorioComasAtendidosEAtendimentosPorRenda(JFrame telaPai) {
        super(telaPai, "Relatório do COMAS de Atendidos e Atendimentos por Renda", true);
    }

    @Override
    protected ControladorTelaRelatorioComasAtendidosEAtendimentosPorRenda obterControlador() {
        return new ControladorTelaRelatorioComasAtendidosEAtendimentosPorRenda(this, jftDataInicio, jftDataTermino);
    } 
    
}

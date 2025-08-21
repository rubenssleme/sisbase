package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioComasAtendidosEAtendimentosPorRegiaoSP;
import javax.swing.JFrame;

public class TelaRelatorioComasAtendidosEAtendimentosPorRegiaoSP extends TelaRelatorioApartirDeData {

    public TelaRelatorioComasAtendidosEAtendimentosPorRegiaoSP(JFrame telaPai) {
        super(telaPai, "Relatório do COMAS de Atendidos e Atendimentos por região de SP", true);
    }

    @Override
    protected ControladorTelaRelatorioComasAtendidosEAtendimentosPorRegiaoSP obterControlador() {
        return new ControladorTelaRelatorioComasAtendidosEAtendimentosPorRegiaoSP(this, jftDataInicio, jftDataTermino);
    } 
    
}

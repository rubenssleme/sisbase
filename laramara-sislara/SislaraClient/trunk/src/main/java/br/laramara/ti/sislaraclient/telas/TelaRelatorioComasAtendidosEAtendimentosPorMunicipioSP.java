package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP;
import javax.swing.JFrame;

public class TelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP extends TelaRelatorioApartirDeData {

    public TelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP(JFrame telaPai) {
        super(telaPai, "Relatório do COMAS de Atendidos e Atendimentos por município de SP", true);
    }

    @Override
    protected ControladorTelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP obterControlador() {
        return new ControladorTelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP(this, jftDataInicio, jftDataTermino);
    } 
}

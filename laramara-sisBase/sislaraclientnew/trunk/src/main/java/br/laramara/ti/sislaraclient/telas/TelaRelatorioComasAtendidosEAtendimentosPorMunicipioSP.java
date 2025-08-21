package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP;
import javax.swing.JFrame;

public class TelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP extends TelaRelatorioApartirDeData {

    public TelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP(JFrame telaPai) {
        super(telaPai, "Relat�rio do COMAS de Atendidos e Atendimentos por munic�pio de SP", true);
    }

    @Override
    protected ControladorTelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP obterControlador() {
        return new ControladorTelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP(this, jftDataInicio, jftDataTermino);
    } 
}

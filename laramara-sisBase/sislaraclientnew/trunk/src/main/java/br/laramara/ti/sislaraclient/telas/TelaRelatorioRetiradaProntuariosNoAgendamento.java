package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioApartirDeData;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioRetiradaProntuariosNoAgendamento;
import javax.swing.JFrame;

public class TelaRelatorioRetiradaProntuariosNoAgendamento  extends TelaRelatorioApartirDeData {
    public TelaRelatorioRetiradaProntuariosNoAgendamento(JFrame telaPai){
        super(telaPai, "Relatório de Retirada de Prontuários", false);
    }

    @Override
    protected ControladorTelaRelatorioApartirDeData obterControlador() {
        return new ControladorTelaRelatorioRetiradaProntuariosNoAgendamento(this, jftDataInicio);
    }
}

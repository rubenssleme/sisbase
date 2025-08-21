package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioQuantidadeAtendidosPorComunidadeDeVisitaMonitorada;
import javax.swing.JFrame;

public class TelaRelatorioQuantidadeAtendidosPorComunidadeDeVisitaMonitorada extends TelaRelatorioApartirDeData {

    public TelaRelatorioQuantidadeAtendidosPorComunidadeDeVisitaMonitorada(JFrame telaPai) {
        super(telaPai, "Relatório do quantidade de atendidos nos Grupos da Visita Monitorada", true);
    }

    @Override
    protected ControladorTelaRelatorioQuantidadeAtendidosPorComunidadeDeVisitaMonitorada obterControlador() {
        return new ControladorTelaRelatorioQuantidadeAtendidosPorComunidadeDeVisitaMonitorada(this, jftDataInicio, jftDataTermino);
    } 
}

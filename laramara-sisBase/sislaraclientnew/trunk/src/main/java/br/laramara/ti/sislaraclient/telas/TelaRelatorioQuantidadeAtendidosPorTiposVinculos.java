package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioQuantidadeAtendidosPorTiposVinculos;
import javax.swing.JFrame;



public class TelaRelatorioQuantidadeAtendidosPorTiposVinculos extends TelaRelatorioApartirDeData {
    public TelaRelatorioQuantidadeAtendidosPorTiposVinculos(JFrame telaPai){
        super(telaPai, "Relatório de Quantidade Atendidos por Tipos de Vinculos", true);
    }

    @Override
    protected ControladorTelaRelatorioQuantidadeAtendidosPorTiposVinculos obterControlador() {
        return new ControladorTelaRelatorioQuantidadeAtendidosPorTiposVinculos(this, jftDataInicio, jftDataTermino);
    }
}

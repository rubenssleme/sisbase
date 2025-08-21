package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo;
import javax.swing.JFrame;

public class TelaRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo extends TelaRelatorioApartirDeData {
    public TelaRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo(JFrame telaPai){
        super(telaPai, "Relatório de Quantidade Atendidos por Tipos de Vinculos por Grupo", true);
    }

    @Override
    protected ControladorTelaRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo obterControlador() {
        return new ControladorTelaRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo(this, jftDataInicio, jftDataTermino);
    }
        
    
}

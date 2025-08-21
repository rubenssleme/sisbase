
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioAtendidosSemInformacaoDeficiencia;
import javax.swing.JFrame;

public class TelaRelatorioAtendidosSemInformacaoDeficiencia extends TelaRelatorioApartirDeData {
    
    public TelaRelatorioAtendidosSemInformacaoDeficiencia(JFrame telaPai){
        super(telaPai, "Relatório de atendidos sem informações de deficiência", true);
    }

    @Override
    protected ControladorTelaRelatorioAtendidosSemInformacaoDeficiencia obterControlador() {
        return new ControladorTelaRelatorioAtendidosSemInformacaoDeficiencia(this, jftDataInicio, jftDataTermino);
    }
}

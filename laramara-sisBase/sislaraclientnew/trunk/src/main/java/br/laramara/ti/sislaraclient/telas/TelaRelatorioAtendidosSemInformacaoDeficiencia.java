
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioAtendidosSemInformacaoDeficiencia;
import javax.swing.JFrame;

public class TelaRelatorioAtendidosSemInformacaoDeficiencia extends TelaRelatorioApartirDeData {
    
    public TelaRelatorioAtendidosSemInformacaoDeficiencia(JFrame telaPai){
        super(telaPai, "Relat�rio de atendidos sem informa��es de defici�ncia", true);
    }

    @Override
    protected ControladorTelaRelatorioAtendidosSemInformacaoDeficiencia obterControlador() {
        return new ControladorTelaRelatorioAtendidosSemInformacaoDeficiencia(this, jftDataInicio, jftDataTermino);
    }
}


package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorRelatorioApartirDeProntuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioRetiradasPorProntuario;
import javax.swing.JFrame;

public class TelaRelatorioRetiradasPorProntuario extends TelaRelatorioApartirDeProntuario{

    public TelaRelatorioRetiradasPorProntuario(JFrame telaPai){
        super(telaPai, "Relat�rio de retiradas por prontu�rio");
    }
    
    @Override
    protected ControladorRelatorioApartirDeProntuario obterControlador() {
        return new ControladorTelaRelatorioRetiradasPorProntuario(this, jftProntuario);
    }
}

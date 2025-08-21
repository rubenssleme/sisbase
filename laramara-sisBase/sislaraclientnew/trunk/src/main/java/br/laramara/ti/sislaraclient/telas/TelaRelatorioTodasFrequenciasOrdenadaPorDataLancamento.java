package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorRelatorioApartirDeProntuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioTodasFrequenciasOrdenadaPorDataLancamento;
import javax.swing.JFrame;

public class TelaRelatorioTodasFrequenciasOrdenadaPorDataLancamento extends TelaRelatorioApartirDeProntuario{

    public TelaRelatorioTodasFrequenciasOrdenadaPorDataLancamento(JFrame telaPai){
        super(telaPai, "Relat�rio de todas as frequ�ncias ordenadas por data de lan�amento");
    }
    
    @Override
    protected ControladorRelatorioApartirDeProntuario obterControlador() {
        return new ControladorTelaRelatorioTodasFrequenciasOrdenadaPorDataLancamento(this, jftProntuario);
    }
}

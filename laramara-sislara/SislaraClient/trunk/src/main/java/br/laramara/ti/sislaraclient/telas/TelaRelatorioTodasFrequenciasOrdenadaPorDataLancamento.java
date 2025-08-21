package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorRelatorioApartirDeProntuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioTodasFrequenciasOrdenadaPorDataLancamento;
import javax.swing.JFrame;

public class TelaRelatorioTodasFrequenciasOrdenadaPorDataLancamento extends TelaRelatorioApartirDeProntuario{

    public TelaRelatorioTodasFrequenciasOrdenadaPorDataLancamento(JFrame telaPai){
        super(telaPai, "Relatório de todas as frequências ordenadas por data de lançamento");
    }
    
    @Override
    protected ControladorRelatorioApartirDeProntuario obterControlador() {
        return new ControladorTelaRelatorioTodasFrequenciasOrdenadaPorDataLancamento(this, jftProntuario);
    }
}

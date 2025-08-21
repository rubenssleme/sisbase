package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorRelatorioApartirDeProntuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioTodosAtendimentosIndividuaisDoUsuarioOrdenadoPorDataLancamento;
import javax.swing.JFrame;

public class TelaRelatorioTodosAtendimentosIndividuaisDoUsuarioOrdenadoPorDataLancamento extends TelaRelatorioApartirDeProntuario {
    
    public TelaRelatorioTodosAtendimentosIndividuaisDoUsuarioOrdenadoPorDataLancamento(JFrame telaPai){
        super(telaPai, "Relatório de todos os atendimento individuais ordenado por Data de Lançamento");
    }

    @Override
    protected ControladorRelatorioApartirDeProntuario obterControlador() {
        return new ControladorTelaRelatorioTodosAtendimentosIndividuaisDoUsuarioOrdenadoPorDataLancamento(this, jftProntuario);
    }
}

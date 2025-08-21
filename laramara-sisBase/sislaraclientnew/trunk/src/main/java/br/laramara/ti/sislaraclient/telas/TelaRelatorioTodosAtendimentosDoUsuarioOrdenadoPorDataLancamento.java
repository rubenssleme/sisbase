package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorRelatorioApartirDeProntuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento;
import javax.swing.JFrame;


public class TelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento extends TelaRelatorioApartirDeProntuario {
    
    public TelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento(JFrame telaPai){
        super(telaPai, "Relatório de todos os atendimento ordenado por Data de Lançamento");
    }

    @Override
    protected ControladorRelatorioApartirDeProntuario obterControlador() {
        return new ControladorTelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento(this, jftProntuario);
    }
}

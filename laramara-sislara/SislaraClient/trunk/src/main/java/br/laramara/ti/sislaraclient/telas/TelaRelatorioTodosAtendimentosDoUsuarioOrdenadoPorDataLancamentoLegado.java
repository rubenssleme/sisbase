package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorRelatorioApartirDeProntuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamentoLegado;
import javax.swing.JFrame;

public class TelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamentoLegado extends TelaRelatorioApartirDeProntuario {
    
    public TelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamentoLegado(JFrame telaPai){
        super(telaPai, "Relat�rio de todos os atendimento ordenado por Data de Lan�amento do Legado");
    }

    @Override
    protected ControladorRelatorioApartirDeProntuario obterControlador() {
        return new ControladorTelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamentoLegado(this, jftProntuario);
    }
}

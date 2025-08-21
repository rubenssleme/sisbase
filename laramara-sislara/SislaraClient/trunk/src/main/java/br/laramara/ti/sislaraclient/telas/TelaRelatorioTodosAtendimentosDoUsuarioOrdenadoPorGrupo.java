
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorRelatorioApartirDeProntuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorGrupo;
import javax.swing.JFrame;

public class TelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorGrupo extends TelaRelatorioApartirDeProntuario {
    
    public TelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorGrupo(JFrame telaPai){
        super(telaPai, "Relatório de todos os atendimento ordenado por Grupo");
    }

    @Override
    protected ControladorRelatorioApartirDeProntuario obterControlador() {
        return new ControladorTelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorGrupo(this, jftProntuario);
    }
}

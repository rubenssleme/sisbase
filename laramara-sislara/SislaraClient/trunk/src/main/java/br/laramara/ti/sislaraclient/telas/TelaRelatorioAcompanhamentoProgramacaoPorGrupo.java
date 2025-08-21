
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioAcompanhamentoProgramacaoPorGrupo;
import javax.swing.JFrame;

public class TelaRelatorioAcompanhamentoProgramacaoPorGrupo extends TelaRelatorioApartirDeNomeGrupo {
    
    public TelaRelatorioAcompanhamentoProgramacaoPorGrupo(JFrame telaPai){
        super(telaPai, "Relatório de Acompanhamento de Programação do Grupo");
        controlador = new ControladorTelaRelatorioAcompanhamentoProgramacaoPorGrupo(this, jtfNomeGrupo, jliNomesCompletosGrupos);
        controlador.abrirTela();
    }
}

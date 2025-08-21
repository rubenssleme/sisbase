
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioAcompanhamentoProgramacaoPorGrupo;
import javax.swing.JFrame;

public class TelaRelatorioAcompanhamentoProgramacaoPorGrupo extends TelaRelatorioApartirDeNomeGrupo {
    
    public TelaRelatorioAcompanhamentoProgramacaoPorGrupo(JFrame telaPai){
        super(telaPai, "Relat�rio de Acompanhamento de Programa��o do Grupo");
        controlador = new ControladorTelaRelatorioAcompanhamentoProgramacaoPorGrupo(this, jtfNomeGrupo, jliNomesCompletosGrupos);
        controlador.abrirTela();
    }
}

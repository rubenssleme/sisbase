
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioCargaHorariaUsuarioPorGrupo;
import javax.swing.JFrame;

public class TelaRelatorioCargaHorariaUsuarioPorGrupo extends TelaRelatorioApartirDeNomeGrupo {
    
    public TelaRelatorioCargaHorariaUsuarioPorGrupo(JFrame telaPai){
        super(telaPai, "Relat�rio de Carga Hor�ria de Usu�rio por Grupo");
        controlador = new ControladorTelaRelatorioCargaHorariaUsuarioPorGrupo(this, jtfNomeGrupo, jliNomesCompletosGrupos);
        controlador.abrirTela();
    }
    
}


package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioAtendimentoPorUsuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioListaFrequenciaPorGrupo;
import javax.swing.JFrame;

public class TelaRelatorioListaFrequenciaPorGrupo extends TelaRelatorioAtendimentoPorUsuario {
    
    public TelaRelatorioListaFrequenciaPorGrupo(JFrame telaPai){
        super("Relatório de Frequência por Grupo", false, false, true, telaPai);
    }
    
    @Override
    protected ControladorTelaRelatorioAtendimentoPorUsuario obterControlador() {
        return new ControladorTelaRelatorioListaFrequenciaPorGrupo(this, jtfGrupo, jcbModuloAtividade, jcbUsuario, jchPaisagem);
    }
}

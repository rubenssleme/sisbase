package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioPorcentagensFrequenciaPorGrupo;
import javax.swing.JFrame;

public class TelaRelatorioPorcentagensFrequenciaPorGrupo extends TelaRelatorioAtendimentoPorUsuario {
    
    public TelaRelatorioPorcentagensFrequenciaPorGrupo(JFrame telaPai){
        super("Relat�rio de porcentagens de frequ�ncia por Grupo", false, false, false, telaPai);
    }
    
    @Override
    protected ControladorTelaRelatorioPorcentagensFrequenciaPorGrupo obterControlador() {
        return new ControladorTelaRelatorioPorcentagensFrequenciaPorGrupo(this, jtfGrupo, jcbModuloAtividade);
    }
}


package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioAtendimentoPorUsuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioAtendimentoPorUsuarioDetalhado;
import javax.swing.JFrame;

public class TelaRelatorioAtendimentoPorUsuarioDetalhado extends TelaRelatorioAtendimentoPorUsuario {
    
    public TelaRelatorioAtendimentoPorUsuarioDetalhado(JFrame telaPai){
        super("Relat�rio de Atendimento Detalhado por Usu�rio ", true, false, false, telaPai);
    }

    @Override
    protected ControladorTelaRelatorioAtendimentoPorUsuario obterControlador() {
        return new ControladorTelaRelatorioAtendimentoPorUsuarioDetalhado(this, jtfGrupo, jcbModuloAtividade, jcbUsuario);
    }
}

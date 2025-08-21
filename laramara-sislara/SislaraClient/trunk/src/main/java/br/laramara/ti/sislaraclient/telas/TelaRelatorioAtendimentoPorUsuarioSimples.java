package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioAtendimentoPorUsuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioAtendimentoPorUsuarioSimples;
import javax.swing.JFrame;

public class TelaRelatorioAtendimentoPorUsuarioSimples extends TelaRelatorioAtendimentoPorUsuario {
    
    public TelaRelatorioAtendimentoPorUsuarioSimples(JFrame telaPai){
        super("Relat�rio de Frequ�ncia por Usu�rio ", false, false, false, telaPai);
    }

    @Override
    protected ControladorTelaRelatorioAtendimentoPorUsuario obterControlador() {
       return new ControladorTelaRelatorioAtendimentoPorUsuarioSimples(this, jtfGrupo, jcbModuloAtividade, jcbUsuario);
    }
}

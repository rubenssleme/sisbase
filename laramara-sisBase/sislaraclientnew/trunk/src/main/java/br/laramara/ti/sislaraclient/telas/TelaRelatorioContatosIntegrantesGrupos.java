
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioContatosIntegrantesGrupos;
import javax.swing.JFrame;

public class TelaRelatorioContatosIntegrantesGrupos extends TelaRelatorioAtendimentoPorUsuario {
    
    public TelaRelatorioContatosIntegrantesGrupos(JFrame telaPai){
        super("Relatório de Contatos de Integrantes do Grupo ", false, false, false, telaPai);
    }

    @Override
    protected ControladorTelaRelatorioContatosIntegrantesGrupos obterControlador() {
       return new ControladorTelaRelatorioContatosIntegrantesGrupos(this, jtfGrupo, jcbModuloAtividade, jcbUsuario);
    }
}

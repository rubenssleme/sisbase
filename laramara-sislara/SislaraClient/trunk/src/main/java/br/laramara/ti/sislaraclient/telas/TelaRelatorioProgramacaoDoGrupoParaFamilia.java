
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioAtendimentoPorUsuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioProgramacaoDoGrupoParaFamilia;
import javax.swing.JFrame;

public class TelaRelatorioProgramacaoDoGrupoParaFamilia extends TelaRelatorioAtendimentoPorUsuario {
    
    public TelaRelatorioProgramacaoDoGrupoParaFamilia(JFrame telaPai){
        super("Relatório de Programação do Grupo para Famílias", false, true, false, telaPai);
    }
    
    @Override
    protected ControladorTelaRelatorioAtendimentoPorUsuario obterControlador() {
        return new ControladorTelaRelatorioProgramacaoDoGrupoParaFamilia(this, jftDataInicio, jftDataTermino, jtfGrupo, jcbModuloAtividade, jcbUsuario);
    }
    
}

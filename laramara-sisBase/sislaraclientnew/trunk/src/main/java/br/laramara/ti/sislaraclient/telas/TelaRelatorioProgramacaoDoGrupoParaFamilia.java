
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioAtendimentoPorUsuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioProgramacaoDoGrupoParaFamilia;
import javax.swing.JFrame;

public class TelaRelatorioProgramacaoDoGrupoParaFamilia extends TelaRelatorioAtendimentoPorUsuario {
    
    public TelaRelatorioProgramacaoDoGrupoParaFamilia(JFrame telaPai){
        super("Relat�rio de Programa��o do Grupo para Fam�lias", false, true, false, telaPai);
    }
    
    @Override
    protected ControladorTelaRelatorioAtendimentoPorUsuario obterControlador() {
        return new ControladorTelaRelatorioProgramacaoDoGrupoParaFamilia(this, jftDataInicio, jftDataTermino, jtfGrupo, jcbModuloAtividade, jcbUsuario);
    }
    
}

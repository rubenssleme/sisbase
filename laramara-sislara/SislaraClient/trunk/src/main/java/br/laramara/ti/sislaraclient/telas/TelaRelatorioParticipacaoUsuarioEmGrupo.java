package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorRelatorioApartirDeProntuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioParticipacaoUsuarioEmGrupo;
import javax.swing.JFrame;

public class TelaRelatorioParticipacaoUsuarioEmGrupo extends TelaRelatorioApartirDeProntuario{
    public TelaRelatorioParticipacaoUsuarioEmGrupo(JFrame telaPai){
        super(telaPai, "Relatório de participação de Usuário em Grupos");
    }
    
    @Override
    protected ControladorRelatorioApartirDeProntuario obterControlador() {
        return new ControladorTelaRelatorioParticipacaoUsuarioEmGrupo(this, jftProntuario);
    }
}

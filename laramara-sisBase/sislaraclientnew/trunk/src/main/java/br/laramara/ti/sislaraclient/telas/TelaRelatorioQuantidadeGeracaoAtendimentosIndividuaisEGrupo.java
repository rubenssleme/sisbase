package br.laramara.ti.sislaraclient.telas;


import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioApartirDeData;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioGeracaoAtendimentoIndividualEGrupo;
import javax.swing.JFrame;

public class TelaRelatorioQuantidadeGeracaoAtendimentosIndividuaisEGrupo extends TelaRelatorioApartirDeData{

    public TelaRelatorioQuantidadeGeracaoAtendimentosIndividuaisEGrupo(JFrame telaPai) {
        super(telaPai, "Relat�rio de Quantidade de Gera��o de Atendimentos Individuais e Grupo", true);
    }
    @Override
    protected ControladorTelaRelatorioApartirDeData obterControlador() {
        return new ControladorTelaRelatorioGeracaoAtendimentoIndividualEGrupo(this, jftDataInicio, jftDataTermino);
    }
    
}

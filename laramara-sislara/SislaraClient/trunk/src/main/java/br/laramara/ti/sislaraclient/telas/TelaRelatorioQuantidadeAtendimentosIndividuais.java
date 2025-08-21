package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioQuantidadeAtendimentosIndividuais;
import javax.swing.JFrame;

public class TelaRelatorioQuantidadeAtendimentosIndividuais extends TelaRelatorioApartirDeData {
    public TelaRelatorioQuantidadeAtendimentosIndividuais(JFrame telaPai){
        super(telaPai, "Relatório de Quantidade de Atendimentos Individuais", true);
    }

    @Override
    protected ControladorTelaRelatorioQuantidadeAtendimentosIndividuais obterControlador() {
        return new ControladorTelaRelatorioQuantidadeAtendimentosIndividuais(this, jftDataInicio, jftDataTermino);
    }
}

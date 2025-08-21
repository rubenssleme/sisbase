package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioQuantidadeAtendimentosParaPrefeitura;
import javax.swing.JFrame;

public class TelaRelatorioQuantidadeAtendimentosParaPrefeitura extends TelaRelatorioApartirDeData {
    public TelaRelatorioQuantidadeAtendimentosParaPrefeitura(JFrame telaPai){
        super(telaPai, "Relatório de Quantidade Atendimentos para Prefeitura", true);
    }

    @Override
    protected ControladorTelaRelatorioQuantidadeAtendimentosParaPrefeitura obterControlador() {
        return new ControladorTelaRelatorioQuantidadeAtendimentosParaPrefeitura(this, jftDataInicio, jftDataTermino);
    }
}

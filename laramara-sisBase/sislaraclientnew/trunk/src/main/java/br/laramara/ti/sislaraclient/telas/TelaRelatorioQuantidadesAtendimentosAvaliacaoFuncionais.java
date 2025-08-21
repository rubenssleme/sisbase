package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioQuantidadesAtendimentosAvaliacoesFuncionais;
import javax.swing.JFrame;

public class TelaRelatorioQuantidadesAtendimentosAvaliacaoFuncionais extends TelaRelatorioApartirDeData {

    public TelaRelatorioQuantidadesAtendimentosAvaliacaoFuncionais(JFrame telaPai) {
        super(telaPai, "Relatório do quantidades de atendimentos de avaliações funcionais", true);
    }

    @Override
    protected ControladorTelaRelatorioQuantidadesAtendimentosAvaliacoesFuncionais obterControlador() {
        return new ControladorTelaRelatorioQuantidadesAtendimentosAvaliacoesFuncionais(this, jftDataInicio, jftDataTermino);
    } 
}

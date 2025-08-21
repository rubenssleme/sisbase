
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioComasAtendidosEAtendimentosPorClassificacaoInstituicao;
import javax.swing.JFrame;

public class TelaRelatorioComasAtendidosEAtendimentosPorClassificacaoInstituicao extends TelaRelatorioApartirDeData {

    public TelaRelatorioComasAtendidosEAtendimentosPorClassificacaoInstituicao(JFrame telaPai) {
        super(telaPai, "Relatório do COMAS de Atendidos e Atendimentos por classificação de instituição", true);
    }

    @Override
    protected ControladorTelaRelatorioComasAtendidosEAtendimentosPorClassificacaoInstituicao obterControlador() {
        return new ControladorTelaRelatorioComasAtendidosEAtendimentosPorClassificacaoInstituicao(this, jftDataInicio, jftDataTermino);
    } 
}

package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class ControladorTelaRelatorioAPartirFaixaIdade extends ControladorTela {

    private JTextField jtfIdadeMinima;
    private JTextField jtfIdadeMaxima;

    public ControladorTelaRelatorioAPartirFaixaIdade(JDialog telaPai, JTextField jtfIdadeMinima, JTextField jtfIdadeMaxima) {
        super(telaPai);
        this.jtfIdadeMinima = jtfIdadeMinima;
        this.jtfIdadeMaxima = jtfIdadeMaxima;
    }

    public void exibir() {
        try {
            ArquivoDTO relatorioDto = servicoSisLaraServer.gerarRelatorioQuantidadePessoasNaListaDeEsperaPorFaixaIdade(Long.valueOf(jtfIdadeMinima.getText()), Long.valueOf(jtfIdadeMaxima.getText()), Sessao.obterInstancia().obterToken());
            exibirArquivo(relatorioDto);
        } catch (Exception ex) {
            logger.error("Erro durante solicitação de relatório de espera por faixa de idade. \nDetalhes:" + ex);
        }
    }
}

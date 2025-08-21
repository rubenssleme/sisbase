
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextField;

public class ControladorTelaRelatorioAcompanhamentoProgramacaoPorGrupo extends ControladorTelaRelatorioApartirDeNomeGrupo{
 
    private JTextField jtfNomeGrupo;
    
    public ControladorTelaRelatorioAcompanhamentoProgramacaoPorGrupo(JDialog telaPai, JTextField jtfNomeGrupo, JList jliNomesCompletosGrupo){
        super(telaPai, jtfNomeGrupo, jliNomesCompletosGrupo);
        this.jtfNomeGrupo = jtfNomeGrupo;
    }

    @Override
    public void exibir() {
        try {
            ArquivoDTO relatorioDto = servicoSisLaraServer.gerarRelatorioAcompanhamentoProgramacaoNoGrupo(jtfNomeGrupo.getText(), Sessao.obterInstancia().obterToken());
            exibirArquivo(relatorioDto);
        } catch (Exception ex) {
            logger.error("Erro durante solicitação de relatório de acompanhamento de programação de grupo. \nDetalhes:" + ex);
        }
    }
}

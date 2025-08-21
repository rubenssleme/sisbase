
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.retirada.ResultadoEdicaoRetiradaDTO;
import br.laramara.ti.sislaracommons.dtos.retirada.RetiradaDTO;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class ControladorTelaRetiradaBaixar extends ControladorTelaRetirada{
    public ControladorTelaRetiradaBaixar(JDialog telaPai, JFormattedTextField jftProntuario, JTextField jtfNome){
       super(telaPai, jftProntuario, jtfNome);
    }

    @Override
    public void efetuar() {
        RetiradaDTO retiradaDTO = new RetiradaDTO();
        retiradaDTO.setProntuario(NumeroUtils.retornaLongoOuInvalido(jftProntuario.getText()));
        try {
            ResultadoEdicaoRetiradaDTO resultadoEdicaoBaixaDto = servicoSisLaraServer.baixarRetirada(retiradaDTO, Sessao.obterInstancia().obterToken());
            if (resultadoEdicaoBaixaDto.sucesso()){
                JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultadoEdicaoBaixaDto.obterMensagens());
                fecharTela();
            }else{
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoEdicaoBaixaDto.obterMensagens());
            }
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_EFETUACAO_BAIXA);
            logger.error(JOptionPanePersonalizado.ERRO_NA_EFETUACAO_BAIXA + ex.getMessage());
        }
    }
}

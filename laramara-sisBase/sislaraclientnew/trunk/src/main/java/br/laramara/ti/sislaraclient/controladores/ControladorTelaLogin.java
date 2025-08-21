package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoAutenticacaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;


public class ControladorTelaLogin extends ControladorTela {

    public ControladorTelaLogin(JDialog telaPai) {
        super(telaPai);
    }

    public void autenticarLogin(String login, String senha, JDialog tela) {
        ResultadoAutenticacaoDTO resultado;
        try {
            resultado = servicoSisLaraServer.autenticarLogin(new CredencialDTO(login, senha, ""));
            if (resultado.sucesso()) {
                tela.setVisible(false);
                Sessao.obterInstancia().armazenarTokenDTO(resultado.getToken());
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(tela, resultado.obterMensagens());
            }
        } catch (Exception e) {
            JOptionPanePersonalizado.mostrarTelaErro(tela, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
            logger.fatal(JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO + e);
        }
    }
}

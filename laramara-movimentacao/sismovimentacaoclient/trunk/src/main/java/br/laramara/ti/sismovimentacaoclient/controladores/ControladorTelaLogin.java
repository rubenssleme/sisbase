package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaoclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoAutenticacaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import javax.swing.JDialog;


public class ControladorTelaLogin extends ControladorTela {

    public ControladorTelaLogin(JDialog telaPai) {
        super(telaPai);
    }

    public void autenticarLogin(String login, String senha, JDialog tela) {
        ResultadoAutenticacaoDTO resultado;
        try {
            resultado = servicoSisMovimentacaoServer.autenticarLogin(new CredencialDTO(login, senha));
            if (resultado.sucesso()) {
                tela.setVisible(false);
                Sessao.obterInstancia().armazenarTokenDTO(resultado.getToken());
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(tela, JOptionPanePersonalizado.ERRO, resultado.obterMensagens());
            }
        } catch (Exception e) {
            JOptionPanePersonalizado.mostrarTelaErro(tela, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
            logger.fatal(JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO + e);
        }
    }
}

package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoAlteracaoSenhaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JPasswordField;

public class ControladorTelaEditarSenha extends ControladorTela {
    
    private JPasswordField jpfSenha;
    private JPasswordField jpfConfirmacaoSenha;
    
    public ControladorTelaEditarSenha(JDialog telaPai, JPasswordField jpfSenha, JPasswordField jpfCofirmacaoSenha){
        super(telaPai);
        this.jpfSenha = jpfSenha;
        this.jpfConfirmacaoSenha = jpfCofirmacaoSenha;
    }

    public void alterarSenha() {
        CredencialDTO credencialDto = new CredencialDTO(null, new String(jpfSenha.getPassword()), new String(jpfConfirmacaoSenha.getPassword()));
        try {
            ResultadoAlteracaoSenhaDTO resultado = servicoSisLaraServer.alterarSenha(credencialDto, Sessao.obterInstancia().obterToken());
            if (resultado.sucesso()) {
                JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultado.obterMensagens());
                fecharTela();
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
            }
        } catch (Exception ex) {
            logger.error("Erro durante solicitação de alteração de senha. \nDetalhes:" + ex);
        }
    }
}

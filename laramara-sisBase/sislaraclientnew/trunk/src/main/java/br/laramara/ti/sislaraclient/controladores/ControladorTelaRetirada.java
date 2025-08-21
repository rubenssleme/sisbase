package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public abstract class ControladorTelaRetirada extends ControladorTela {

    protected JFormattedTextField jftProntuario;
    protected JTextField jtfNome;

    public ControladorTelaRetirada(JDialog telaPai, JFormattedTextField jftProntuario, JTextField jtfNome) {
        super(telaPai);
        this.jftProntuario = jftProntuario;
        this.jtfNome = jtfNome;
    }

    public void exibirNomeUsuario() {
        try {
            EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDTO = new EspecificacaoPesquisaUsuarioDTO();
            especificacaoPesquisaUsuarioDTO.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, jftProntuario.getText());
            exibirNomeUsuario(servicoSisLaraServer.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDTO).getObjetosDto());
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    protected void exibirNomeUsuario(List<UsuarioDTO> usuarioDto) {
        if (!usuarioDto.isEmpty()) {
            jtfNome.setText(usuarioDto.get(0).getInformacaoEssencialDto().getNome());
        } else {
            jtfNome.setText("");
        }
    }
    
    public abstract void efetuar();
}

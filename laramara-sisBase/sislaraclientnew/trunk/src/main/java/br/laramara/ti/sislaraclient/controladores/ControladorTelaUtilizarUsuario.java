package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaUtilizarUsuario extends ControladorTelaUtilizar {

    public ControladorTelaUtilizarUsuario(JDialog telaPai, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar, JTable jtaListagemPreCadastro) {
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jtaListagemPreCadastro);
    }

    @Override
    public void utilizar() {
        if (estaComItemValidoSelecionado(jtaListagem)) {
            Sessao.obterInstancia().armazenarDTO(Sessao.CHAVE_USUARIO_SELECIONADO, ((UsuarioDTO) obterDtoSelecionado(jtaListagem)));
            telaPai.dispose();
        } else {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.SELECIONAR_REGISTRO);
        }
    }

    @Override
    public ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacao) throws Exception {
        return servicoSisLaraServer.pesquisarUsuarioPor((EspecificacaoPesquisaUsuarioDTO) especificacao);
    }

    @Override
    public EspecificacaoPesquisaDTO obterEspecificacao() {
        return new EspecificacaoPesquisaUsuarioDTO();
    }
}

package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.usuario.TelaEditarUsuario;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaGerenUsuarios extends ControladorTelaGeren {

    public ControladorTelaGerenUsuarios(JDialog telaPai, JTable jtaUsuarios, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar){
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jtaUsuarios);
    }
 
    @Override
    public EspecificacaoPesquisaDTO obterEspecificacao(){
        return new EspecificacaoPesquisaUsuarioDTO();
    }

    @Override
    protected ResultadoListagemUsuarioDTO obterListagem(EspecificacaoPesquisaDTO especificacao) throws Exception {
        return servicoSisLaraServer.pesquisarUsuarioPor((EspecificacaoPesquisaUsuarioDTO)especificacao);
    }

    @Override
    protected void abrirTelaParaEdicao(ModeloDTO usuarioDto) {
        new TelaEditarUsuario((JDialog) telaPai, (UsuarioDTO)usuarioDto);
    }

    @Override
    public void abrirTelaParaInclusao() {
        new TelaEditarUsuario((JDialog) telaPai, new UsuarioDTO());
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_USUARIO;
    }
}

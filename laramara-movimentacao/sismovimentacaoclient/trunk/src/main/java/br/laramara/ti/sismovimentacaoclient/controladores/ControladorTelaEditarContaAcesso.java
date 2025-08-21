
package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaoclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoListagemPerfilDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class ControladorTelaEditarContaAcesso extends ControladorTelaEditar{

    private ResultadoListagemPerfilDTO resultadoListagemPerfilDTO;
    private ResultadoListagemProfissionalDTO resultadoListagemProfissionalDto;
    
    private JTextField jtfLogin;
    private JTextField jtfSenha;
    private JCheckBox jchBloqueado;
    private JComboBox jcbPerfil;
    private JComboBox jcbProfissional;    
    private JEditorPane jepChaves;
    
    public ControladorTelaEditarContaAcesso(JDialog telaPai, ContaAcessoDTO contaAcessoDto, JTextField jtfLogin, JTextField jtfSenha, JComboBox jcbPerfil, JCheckBox jchBloqueado, JComboBox jcbProfissional, JEditorPane jepChaves){
        super(telaPai, contaAcessoDto);
        this.jtfLogin = jtfLogin;
        this.jtfSenha = jtfSenha;
        this.jchBloqueado = jchBloqueado;
        this.jcbPerfil = jcbPerfil;
        this.jcbProfissional = jcbProfissional;
        this.jepChaves = jepChaves;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoDTO) servicoSisMovimentacaoServer.editarContaAcesso(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setUsuario(jtfLogin.getText());
        obterObjetoDTO().setSenha(jtfSenha.getText());
        obterObjetoDTO().setBloqueado(jchBloqueado.isSelected());
        obterObjetoDTO().setPerfilDto((PerfilDTO)obterDtoSelecionado(jcbPerfil));
        obterObjetoDTO().setProfissionalDto((ProfissionalDTO)obterDtoSelecionado(jcbProfissional));
        obterObjetoDTO().setPalavraChaveGrupo(jepChaves.getText());
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        return obterObjetoDTO().getId() != null;
    }

    @Override
    protected void atualizarCamposTelaAposInclusao() {
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_CONTA_ACESSO;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbPerfil, resultadoListagemPerfilDTO.getObjetosDto());
        adicionarDtos(jcbProfissional, resultadoListagemProfissionalDto.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemPerfilDTO = servicoSisMovimentacaoServer.obterListagemPerfil();
            resultadoListagemProfissionalDto = servicoSisMovimentacaoServer.obterListagemTodosProfissionais();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    public void carregarCampos() {
        jtfLogin.setText(obterObjetoDTO().getLogin());
        selecionarDto(jcbPerfil, obterObjetoDTO().getPerfilDto());
        jchBloqueado.setSelected(obterObjetoDTO().isBloqueado());
        selecionarDto(jcbProfissional, obterObjetoDTO().getProfissionalDto());
        jepChaves.setText(obterObjetoDTO().getPalavraChaveGrupo());
    }
    
    private ContaAcessoDTO obterObjetoDTO() {
        return (ContaAcessoDTO) objetoDto;
    }
}

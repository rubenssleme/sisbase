
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoListagemPerfilDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoListagemPermissaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JTextField;

public class ControladorTelaEditarContaAcesso extends ControladorTelaEditar{

    private ResultadoListagemPerfilDTO resultadoListagemPerfilDTO;
    private ResultadoListagemProfissionalDTO resultadoListagemProfissionalDto;
    private ResultadoListagemPermissaoDTO resultadoListagemPermissaoDto;
    
    private JTextField jtfLogin;
    private JTextField jtfSenha;
    private JCheckBox jchBloqueado;
    private JComboBox jcbPerfil;
    private JComboBox jcbProfissional;    
    private JComboBox jcbPermissao;
    private JList jliPermissoes;
    private JComboBox jcbProfissionaisEquivalentes;
    private JList jliProfissionaisEquivalentesAdicionados;
    private JEditorPane jepChaves;
    
    public ControladorTelaEditarContaAcesso(JDialog telaPai, ContaAcessoDTO contaAcessoDto, JTextField jtfLogin, JTextField jtfSenha, JComboBox jcbPerfil, JCheckBox jchBloqueado, JComboBox jcbProfissional, JComboBox jcbPermissao, JList jliPermissoes, JComboBox jcbProfissionaisEquivalentes, JList jliProfissionaisEquivalentesAdicionados, JEditorPane jepChaves){
        super(telaPai, contaAcessoDto);
        this.jtfLogin = jtfLogin;
        this.jtfSenha = jtfSenha;
        this.jchBloqueado = jchBloqueado;
        this.jcbPerfil = jcbPerfil;
        this.jcbProfissional = jcbProfissional;
        this.jcbPermissao = jcbPermissao;
        this.jliPermissoes = jliPermissoes;
        this.jcbProfissionaisEquivalentes = jcbProfissionaisEquivalentes;
        this.jliProfissionaisEquivalentesAdicionados = jliProfissionaisEquivalentesAdicionados;
        this.jepChaves = jepChaves;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoDTO) servicoSisLaraServer.editarContaAcesso(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setUsuario(jtfLogin.getText());
        obterObjetoDTO().setSenha(jtfSenha.getText());
        obterObjetoDTO().setBloqueado(jchBloqueado.isSelected());
        obterObjetoDTO().setPerfilDto((PerfilDTO)obterDtoSelecionado(jcbPerfil));
        obterObjetoDTO().setProfissionalDto((ProfissionalDTO)obterDtoSelecionado(jcbProfissional));
        obterObjetoDTO().setPalavraChaveGrupo(jepChaves.getText());
        obterObjetoDTO().setPermissoesDto((List<PermissaoDTO>) obterDtos(jliPermissoes));
        obterObjetoDTO().setProfissionaisEquivalentesDto((List<ProfissionalDTO>)obterDtos(jliProfissionaisEquivalentesAdicionados));
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        return obterObjetoDTO().getId() != null;
    }

    @Override
    protected void executarAcaoAposInclusao() {
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
        adicionarDtos(jcbProfissionaisEquivalentes, resultadoListagemProfissionalDto.getObjetosDto());
        adicionarDtos(jcbPermissao, resultadoListagemPermissaoDto.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemPerfilDTO = servicoSisLaraServer.obterListagemPerfil();
            resultadoListagemProfissionalDto = servicoSisLaraServer.obterListagemTodosProfissionais();
            resultadoListagemPermissaoDto = servicoSisLaraServer.obterListagemPermissao();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
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
        adicionarDtos(obterObjetoDTO().getPermissoesDto(), jliPermissoes);
        adicionarDtos(obterObjetoDTO().getProfissionaisEquivalentesDto(), jliProfissionaisEquivalentesAdicionados);
    }
    
    private ContaAcessoDTO obterObjetoDTO() {
        return (ContaAcessoDTO) objetoDto;
    }

    public void removerPermissaoSelecionada() {
        removerDtoSelecionado(jliPermissoes);
    }

    public void adicionarPermissao() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliPermissoes, jcbPermissao);
    }

    public void adicionarProfissionalEquivalente() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliProfissionaisEquivalentesAdicionados, jcbProfissionaisEquivalentes);
    }

    public void removerProfissionalEquivalentSelecionado() {
        removerDtoSelecionado(jliProfissionaisEquivalentesAdicionados);
    }
}

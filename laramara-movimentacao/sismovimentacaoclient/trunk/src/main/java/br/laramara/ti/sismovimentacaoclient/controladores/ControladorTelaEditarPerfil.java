package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaoclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoListagemPermissaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextField;

public class ControladorTelaEditarPerfil extends ControladorTelaEditar {
    
    private ResultadoListagemPermissaoDTO resultadoListagemPermissaoDto;
    
    private JTextField jtfDescricao;
    private JComboBox jcbPermissao;
    private JList jliPermissoes;
    
    public ControladorTelaEditarPerfil(JDialog telaPai, PerfilDTO perfilDto, JTextField jtfDescricao, JComboBox jcbPermissao, JList jliPermissoes){
        super(telaPai, perfilDto);
        this.jtfDescricao = jtfDescricao;
        this.jcbPermissao = jcbPermissao;
        this.jliPermissoes = jliPermissoes;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
         return servicoSisMovimentacaoServer.editarPerfil(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setDescricao(jtfDescricao.getText());
        obterObjetoDTO().setPermissoesDto((List<PermissaoDTO>) obterDtos(jliPermissoes));
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
         return obterObjetoDTO().getId() != null;
    }

    private PerfilDTO obterObjetoDTO(){
        return (PerfilDTO)objetoDto;
    }
        
    @Override
    protected void atualizarCamposTelaAposInclusao() {
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_PERFIL;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbPermissao, resultadoListagemPermissaoDto.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemPermissaoDto = servicoSisMovimentacaoServer.obterListagemPermissao();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    public void carregarCampos() {
        if (verificarSeDtoEstaPreenchido()) {
            jtfDescricao.setText(obterObjetoDTO().toString());
            adicionarDtos(obterObjetoDTO().getPermissoesDto(), jliPermissoes);
        }
    }

    public void adicionarPermissao() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliPermissoes, jcbPermissao);
    }

    public void removerPermissaoSelecionada() {
        removerDtoSelecionado(jliPermissoes);
    }
}

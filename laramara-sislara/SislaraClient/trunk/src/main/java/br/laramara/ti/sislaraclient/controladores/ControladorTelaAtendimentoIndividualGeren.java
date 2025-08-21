package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.modelos.ModeloTabelaAtendimentoIndividual;
import br.laramara.ti.sislaraclient.telas.TelaAtendimentoIndividualCopiar;
import br.laramara.ti.sislaraclient.telas.TelaAtendimentoIndividualEditar;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarUsuario;
import br.laramara.ti.sislaraclient.utilitarios.EsperaUtils;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoPesquisaAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;

public class ControladorTelaAtendimentoIndividualGeren extends ControladorTelaPesquisarBase{
    
    private ControladorTipoAtendimento controladorTipoAtendimento;
    
    private ResultadoListagemProfissionalDTO resultadoListagemProfissionalDto;
    
    private JComboBox jcbProfissional;
    private JComboBox jcbDescricaoTipoAtendimento;
    private JComboBox jcbModuloAtividade;
    private JFormattedTextField jftDataInicio;
    private JFormattedTextField jftDataTermino;
    private JTable jtaAtendimentosIndividuais;
    private JFormattedTextField jftProntuario;
        
    public ControladorTelaAtendimentoIndividualGeren(JDialog telaPai, JComboBox jcbProfissional, JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade, JFormattedTextField jftDataInicio, JFormattedTextField jftDataTermino, JTable jtaAtendimentosIndividuais, JFormattedTextField jftProntuario){
        super(telaPai, jtaAtendimentosIndividuais);
        this.jcbProfissional = jcbProfissional;
        this.jcbDescricaoTipoAtendimento = jcbDescricaoTipoAtendimento;
        this.jcbModuloAtividade = jcbModuloAtividade;
        this.jftDataInicio = jftDataInicio;
        this.jftDataTermino = jftDataTermino;
        this.jtaAtendimentosIndividuais = jtaAtendimentosIndividuais;
        this.jftProntuario = jftProntuario;
        this.controladorTipoAtendimento = new ControladorTipoAtendimento(jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade);
        configurarColunasTabela();
        inicializarCombosDadosAuxiliares();
    }

    public void efetuarPesquisa() {
        final EspecificacaoPesquisaAtendimentoIndividualDTO especificacao = new EspecificacaoPesquisaAtendimentoIndividualDTO();
        especificacao.setProfissionalDto((ProfissionalDTO) obterDtoSelecionado(jcbProfissional));
        especificacao.setDescricaoTipoAtendimentoDto((DescricaoTipoAtendimentoDTO)obterDtoSelecionado(jcbDescricaoTipoAtendimento));
        especificacao.setModuloDto((ModuloDTO)obterDtoSelecionado(jcbModuloAtividade));
        especificacao.setDataInicio(jftDataInicio.getText());
        especificacao.setDataTermino(jftDataTermino.getText());
        especificacao.setProntuario(jftProntuario.getText());
        Runnable comando = new Runnable() {
            @Override
            public void run() {
                pesquisar(especificacao);
            }
        };
        new EsperaUtils(comando, (JDialog)telaPai).execute();
    }
        
    @Override
    protected ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacaoPesquisaDto) throws Exception {
        return servicoSisLaraServer.obterListagemAtendimentoIndividual((EspecificacaoPesquisaAtendimentoIndividualDTO)especificacaoPesquisaDto);
    }
    
    private void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaAtendimentosIndividuais);
    }
    
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbProfissional, resultadoListagemProfissionalDto.getObjetosDto());
    }

    public void obterDadosAuxiliares() {
        try {
            resultadoListagemProfissionalDto = servicoSisLaraServer.obterListagemProfissionalAtivos();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    public void inicializarDescricaoTipoAtendimento() {
        controladorTipoAtendimento.inicializarDescricaoTipoAtendimento();
    }
    
    public void inicializarModuloAtividade() {
        controladorTipoAtendimento.inicializarModuloAtividade();
    }

    public void abrirTelaParaInclusao() {
        new TelaAtendimentoIndividualEditar((JDialog)telaPai, new AtendimentoIndividualDTO());
        String chave = Sessao.CHAVE_ATENDIMENTO_INDIVIDUAL;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            obterModeloTabelaAtendimentoIndividual().adicionarDTO((AtendimentoIndividualDTO) Sessao.obterInstancia().obterDto(chave));
        }
    }

    public void abrirTelaParaAlteracao() {
        if (estaComItemValidoSelecionado(jtaAtendimentosIndividuais)) {
            new TelaAtendimentoIndividualEditar((JDialog) telaPai, (AtendimentoIndividualDTO) obterDtoSelecionado(jtaAtendimentosIndividuais));
            if (Sessao.obterInstancia().possuiDto(obterChaveSessao())) {
                adicionarDtoNoItemSelecionado(jtaAtendimentosIndividuais, (AtendimentoIndividualDTO) Sessao.obterInstancia().obterDto(obterChaveSessao()));
            }
        } else {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.SELECIONAR_REGISTRO);
        }
    }
    
    private ModeloTabelaAtendimentoIndividual obterModeloTabelaAtendimentoIndividual(){
        return (ModeloTabelaAtendimentoIndividual)obterModeloTabela(jtaAtendimentosIndividuais);
    }
    
    private String obterChaveSessao(){
        return Sessao.CHAVE_ATENDIMENTO_INDIVIDUAL;
    }
    
    public void moverFocoOuAbrirTelaDeAlteracao(KeyEvent evt){
        transferirFocoNaTabulacao(evt);
        if (teclaEnterPressionada(evt)){
            abrirTelaParaAlteracao();
        }
    }

    public void copiarAtendimentoIndividual() {
        if (estaComItemValidoSelecionado(jtaAtendimentosIndividuais)) {
            String chave = Sessao.CHAVE_ATENDIMENTO_INDIVIDUAL;
            new TelaAtendimentoIndividualCopiar((JDialog) telaPai, (AtendimentoIndividualDTO) obterDtoSelecionado(jtaAtendimentosIndividuais));
            if (Sessao.obterInstancia().possuiDto(chave)) {
                obterModeloTabelaAtendimentoIndividual().adicionarDTO((AtendimentoIndividualDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }
    
    public void localizarUsuario() {
        new TelaUtilizarUsuario((JDialog)telaPai);
        String chave = Sessao.CHAVE_USUARIO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            UsuarioDTO usuarioDTO = (UsuarioDTO) Sessao.obterInstancia().obterDto(chave);
            jftProntuario.setText(usuarioDTO.getId().toString());
        }
    }
}

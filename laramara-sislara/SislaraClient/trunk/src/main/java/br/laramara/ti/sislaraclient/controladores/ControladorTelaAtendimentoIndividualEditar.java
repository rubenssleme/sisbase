package br.laramara.ti.sislaraclient.controladores;

import static br.laramara.ti.sislaraclient.controladores.ControladorTela.logger;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaAtendimentoAcompanhante;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaAtendimentoProfissional;
import br.laramara.ti.sislaraclient.telas.TelaAtendimentoIndividualAcompanhanteEditar;
import br.laramara.ti.sislaraclient.telas.TelaAtendimentoIndividualProfissionalEditar;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarPreCadastroSemValidacao;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarUsuario;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoComunidadeDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemFrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemLocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemStatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ControladorTelaAtendimentoIndividualEditar extends ControladorTelaEditar {
    
    private PreCadastroDTO preCadastroDto;
    private UsuarioDTO usuarioDto;
    
    private ControladorTipoAtendimento controladorTipoAtendimento;
    private ResultadoListagemLocalAtendimentoDTO resultadoListagemLocalAtendimentoDto;
    private ResultadoListagemParticipacaoDTO resultadoListagemParticipacaoDto;
    private ResultadoListagemFrequenciaDTO resultadoListagemFrequenciaDto;
    private ResultadoListagemStatusDTO resultadoListagemStatusDTO;
    
    private JButton jbuUtilizarUsuario;
    private JButton jbuUtilizarPreCadastro;
    private JFormattedTextField jftProntuario;
    private JTextField jtfNomeUsuario;
    private JTextField jtfNomePreCadastro;
    private JComboBox jcbTipoAtendimento;
    private JComboBox jcbDescricaoTipoAtendimento;
    private JComboBox jcbModuloAtividade;
    private JComboBox jcbPrimeiraVez;
    private JComboBox jcbSetor;
    private JComboBox jcbLocalAtendimento;
    private JComboBox jcbParticipacao;
    private JTextArea jepDescricao;
    private JTextArea jatInterdisciplinar;
    private JTable jtaAtendimentosProfissionais;
    private JComboBox jcbFrequencia;
    private JTextArea jatJustificativa;
    private JTable jtaAcompanhante;
    private JFormattedTextField jftData;
    private JFormattedTextField jftHoraInicio;
    private JFormattedTextField jftHoraTermino;
    private JList jliArquivos;
    private boolean forcarCarregamentoAtendimentoIndividualDTO;
    
    public ControladorTelaAtendimentoIndividualEditar(JDialog telaPai, boolean forcarCarregamentoAtendimentoIndividualDTO, AtendimentoIndividualDTO atendimentoIndividualDto, JButton jbuUtilizarUsuario, JButton jbuUtilizarPreCadastro, JFormattedTextField jftProntuario, JTextField jtfNomeUsuario, JTextField jtfNomePreCadastro, JFormattedTextField jftData, JFormattedTextField jftHoraInicio, JFormattedTextField jftHoraTermino, JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade, JComboBox jcbPrimeiraVez, JComboBox jcbSetor, JComboBox jcbLocalAtendimento, JComboBox jcbParticipacao, JTextArea jatDescricao, JTextArea jatInterdisciplinar, JTable jtaAtendimentosProfissionais, JComboBox jcbFrequencia, JTextArea jatJustificativa, JTable jtaAcompanhante, JList jliArquivo){
        super(telaPai, atendimentoIndividualDto);
        this.forcarCarregamentoAtendimentoIndividualDTO = forcarCarregamentoAtendimentoIndividualDTO;
        this.jbuUtilizarUsuario = jbuUtilizarUsuario;
        this.jbuUtilizarPreCadastro = jbuUtilizarPreCadastro;
        this.jftProntuario = jftProntuario;
        this.jtfNomeUsuario = jtfNomeUsuario;
        this.jtfNomePreCadastro = jtfNomePreCadastro;
        this.jcbTipoAtendimento = jcbTipoAtendimento;
        this.jcbDescricaoTipoAtendimento = jcbDescricaoTipoAtendimento;
        this.jcbModuloAtividade = jcbModuloAtividade;
        this.jcbPrimeiraVez = jcbPrimeiraVez;
        this.jcbSetor = jcbSetor;
        this.jcbLocalAtendimento = jcbLocalAtendimento;
        this.jcbParticipacao = jcbParticipacao;
        this.jepDescricao = jatDescricao;
        this.jatInterdisciplinar = jatInterdisciplinar;
        this.jtaAtendimentosProfissionais = jtaAtendimentosProfissionais;
        this.jcbFrequencia = jcbFrequencia;
        this.jatJustificativa = jatJustificativa;
        this.jtaAcompanhante = jtaAcompanhante;
        this.controladorTipoAtendimento = new ControladorTipoAtendimento(jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade, jcbSetor);
        this.jftData = jftData;
        this.jftHoraInicio = jftHoraInicio;
        this.jftHoraTermino = jftHoraTermino;
        this.jliArquivos = jliArquivo;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
        configurarColunasTabela();
    }
    
    private void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaAtendimentosProfissionais);
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaAcompanhante);
    }
            
    public void inicializarDescricaoTipoAtendimento() {
        controladorTipoAtendimento.inicializarDescricaoTipoAtendimento();
    }
    
    public void inicializarModuloAtividadeESetor() {
        controladorTipoAtendimento.inicializarModuloAtividade();
        controladorTipoAtendimento.inicializarSetor();
    }
    
    public void inicializarSetor(){
        controladorTipoAtendimento.inicializarSetor();
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.editarAtendimentoIndividual(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setData(jftData.getText());
        obterObjetoDTO().setUsuarioDto(usuarioDto);
        obterObjetoDTO().setPreCadastroDto(preCadastroDto);
        obterObjetoDTO().setDescricaoTipoAtendimentoDto((DescricaoTipoAtendimentoDTO) obterDtoSelecionado(jcbDescricaoTipoAtendimento));
        obterObjetoDTO().setModuloDto((ModuloDTO) obterDtoSelecionado(jcbModuloAtividade));
        obterObjetoDTO().setSetorDto((SetorDTO) obterDtoSelecionado(jcbSetor));
        obterObjetoDTO().setHorarioDto(new HorarioDTO(jftHoraInicio.getText(), jftHoraTermino.getText()));
        obterObjetoDTO().getInformacaoAtendimentoDto().setDescricao(jepDescricao.getText());
        obterObjetoDTO().getInformacaoAtendimentoDto().setFrequenciaDto((FrequenciaDTO)obterDtoSelecionado(jcbFrequencia));
        obterObjetoDTO().getInformacaoAtendimentoDto().setJustificativa(jatJustificativa.getText());
        obterObjetoDTO().setInterdisciplinar(jatInterdisciplinar.getText());
        obterObjetoDTO().setAtendimentosProfissionalDto((List<AtendimentoProfissionalDTO>)obterDtos(jtaAtendimentosProfissionais));
        obterObjetoDTO().setAtendimentosComunidadeDto((List<AtendimentoComunidadeDTO>)obterDtos(jtaAcompanhante));
        obterObjetoDTO().setLocalAtendimentoDto((LocalAtendimentoDTO)obterDtoSelecionado(jcbLocalAtendimento));
        obterObjetoDTO().setParticipacaoDto((ParticipacaoDTO)obterDtoSelecionado(jcbParticipacao));
        obterObjetoDTO().setPrimeiraVezOuRetornoDto((StatusDTO)obterDtoSelecionado(jcbPrimeiraVez));
        obterObjetoDTO().setArquivos((List<ArquivoDTO>)obterDtos(jliArquivos));
    }

    @Override
    protected void executarAcaoAposInclusao() {
    }
    
    private boolean verificaSeDeveCarregarDTO(){
        return verificarSeDtoEstaPreenchido() || forcarCarregamentoAtendimentoIndividualDTO;
    }
    
    @Override
    public void carregarCampos() {
        if (verificaSeDeveCarregarDTO()) {
            desabilitarUtilizacaoUsuarioEPreCadastro();
            atualizarCampoUsuarioEPreCadastro();
            jftData.setText(obterObjetoDTO().getData());
            jftHoraInicio.setText(obterObjetoDTO().getHorarioDto().getHoraInicio());
            jftHoraTermino.setText(obterObjetoDTO().getHorarioDto().getHoraTermino());
            jepDescricao.setText(obterObjetoDTO().getInformacaoAtendimentoDto().getDescricao());
            jatInterdisciplinar.setText(obterObjetoDTO().getInterdisciplinar());
            adicionarDtos(obterObjetoDTO().getAtendimentosProfissionalDto(), jtaAtendimentosProfissionais);
            adicionarDtos(obterObjetoDTO().getAtendimentosComunidadeDto(), jtaAcompanhante);
            selecionarDto(jcbFrequencia, obterObjetoDTO().getInformacaoAtendimentoDto().getFrequenciaDto());
            jatJustificativa.setText(obterObjetoDTO().getInformacaoAtendimentoDto().getJustificativa());
            selecionarDto(jcbLocalAtendimento, obterObjetoDTO().getLocalAtendimentoDto());
            selecionarDto(jcbParticipacao, obterObjetoDTO().getParticipacaoDto());
            selecionarDto(jcbPrimeiraVez, obterObjetoDTO().getPrimeiraVezOuRetornoDto());
            adicionarDtos(obterObjetoDTO().getArquivos(), jliArquivos);
        }
    }

    private void desabilitarUtilizacaoUsuarioEPreCadastro(){
        jftProntuario.setEditable(false);
        jbuUtilizarUsuario.setEnabled(false);
        jbuUtilizarPreCadastro.setEnabled(false);
    }

    private void atualizarCampoUsuarioEPreCadastro() {
        if (obterObjetoDTO().getUsuarioDto() != null) {
            usuarioDto = obterObjetoDTO().getUsuarioDto();
            jftProntuario.setText(obterObjetoDTO().getUsuarioDto().getId().toString());
            jtfNomeUsuario.setText(obterObjetoDTO().getUsuarioDto().getInformacaoEssencialDto().getNome());
        } else {
            preCadastroDto = obterObjetoDTO().getPreCadastroDto();
            jtfNomePreCadastro.setText(obterObjetoDTO().getPreCadastroDto().getInformacaoEssencialDto().getNome());
        }
    }
    
    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
       return obterObjetoDTO().getId() != null;
    }

    private AtendimentoIndividualDTO obterObjetoDTO(){
        return (AtendimentoIndividualDTO)objetoDto;
    }
    
    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_ATENDIMENTO_INDIVIDUAL;
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemLocalAtendimentoDto = servicoSisLaraServer.obterListagemLocalAtendimento();
            resultadoListagemParticipacaoDto = servicoSisLaraServer.obterListagemParticipacao();
            resultadoListagemFrequenciaDto = servicoSisLaraServer.obterListagemFrequenciaDoUsuario();
            resultadoListagemStatusDTO = servicoSisLaraServer.obterListagemStatusDisponiveisParaAtendimentoIndividual();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbLocalAtendimento, resultadoListagemLocalAtendimentoDto.getObjetosDto());
        adicionarDtos(jcbParticipacao, resultadoListagemParticipacaoDto.getObjetosDto());
        adicionarDtos(jcbFrequencia, resultadoListagemFrequenciaDto.getObjetosDto());
        adicionarDtos(jcbPrimeiraVez, resultadoListagemStatusDTO.getObjetosDto());
    }

    public void abrirTelaUtilizarUsuario() {
        new TelaUtilizarUsuario((JDialog)telaPai);
        atualizaCamposUsuario();
    }
    
    private void atualizaCamposUsuario(){
        String chave = Sessao.CHAVE_USUARIO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            usuarioDto = (UsuarioDTO)Sessao.obterInstancia().obterDto(chave);
            carregarCamposUsuarioELimparPreCadastro(usuarioDto);
        }
    }
    
    private void carregarCamposUsuarioELimparPreCadastro(UsuarioDTO usuarioDto) {
        if (usuarioDto!=null){
            jftProntuario.setText(usuarioDto.getId().toString());
            jtfNomeUsuario.setText(usuarioDto.getInformacaoEssencialDto().getNome());
        }
        limparDadosPreCadastro();
    }
    
    private void limparDadosPreCadastro(){
        jtfNomePreCadastro.setText("");
        preCadastroDto = null;
    }

    public void abrirTelaUtilizarPreCadastro() {
        new TelaUtilizarPreCadastroSemValidacao((JDialog)telaPai);
        atualizaCamposPreCadastro();
    }
    
    private void atualizaCamposPreCadastro(){
        String chave = Sessao.CHAVE_PRE_CADASTRO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            preCadastroDto = (PreCadastroDTO)Sessao.obterInstancia().obterDto(chave);
            carregarCamposPreCadastroELimparUsuario(preCadastroDto);
        }
    }
    
    private void limparDadosUsuario(){
        jftProntuario.setText("");
        jtfNomeUsuario.setText("");
        usuarioDto = null;
    }
        
    private void carregarCamposPreCadastroELimparUsuario(PreCadastroDTO preCadastroDto) {
        if (preCadastroDto != null) {
            jtfNomePreCadastro.setText(preCadastroDto.getInformacaoEssencialDto().getNome());
        }
        limparDadosUsuario();
    }

    public void adicionarDetalheProfissional() {
        new TelaAtendimentoIndividualProfissionalEditar((JDialog) telaPai, new AtendimentoProfissionalDTO());
        String chave = Sessao.CHAVE_ATENDIMENTO_PROFISSIONAL;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            obterModeloTabelaAtendimentoProfissional().adicionarDTO((AtendimentoProfissionalDTO) Sessao.obterInstancia().obterDto(chave));
        }
    }

    private ModeloTabelaAtendimentoProfissional obterModeloTabelaAtendimentoProfissional() {
        return ((ModeloTabelaAtendimentoProfissional)jtaAtendimentosProfissionais.getModel());
    }

    public void editarAtendimentoProfissional() {
        if (estaComItemValidoSelecionado(jtaAtendimentosProfissionais)){
            new TelaAtendimentoIndividualProfissionalEditar((JDialog) telaPai, (AtendimentoProfissionalDTO)obterDtoSelecionado(jtaAtendimentosProfissionais));
            String chave = Sessao.CHAVE_ATENDIMENTO_PROFISSIONAL;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaAtendimentosProfissionais, (AtendimentoProfissionalDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }
    
    public void removerAtendimentoProfissionalSelecionado() {
        if ((estaComItemValidoSelecionado(jtaAtendimentosProfissionais) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jtaAtendimentosProfissionais);
        }
    }

    public void inicializarCombosComDependencia() {
        if (verificaSeDeveCarregarDTO()) {
            atualizarCamposTipoAtendimentoDescricaoAtendimentoNomeGrupoSetor();
        }
    }
    
    private void atualizarCamposTipoAtendimentoDescricaoAtendimentoNomeGrupoSetor(){
        jcbTipoAtendimento.setSelectedItem(obterObjetoDTO().getDescricaoTipoAtendimentoDto().getTipoAtendimentoDto());
        jcbDescricaoTipoAtendimento.setSelectedItem(obterObjetoDTO().getDescricaoTipoAtendimentoDto());
        jcbModuloAtividade.setSelectedItem(obterObjetoDTO().getModuloDto());
        jcbSetor.setSelectedItem(obterObjetoDTO().getSetorDto());
    }
        
    public void moverFocoOuAbrirTelaDeAlteracaoProfissional(KeyEvent evt){
        transferirFocoNaTabulacao(evt);
        if (teclaEnterPressionada(evt)){
            editarAtendimentoProfissional();
        }
    }

    public void moverFocoOuAbrirTelaDeAlteracaoComunidade(KeyEvent evt){
        transferirFocoNaTabulacao(evt);
        if (teclaEnterPressionada(evt)){
            editarAtendimentoProfissional();
        }
    }
        
    public void selecionarProntuario() {
        EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDto = new EspecificacaoPesquisaUsuarioDTO();
        especificacaoPesquisaUsuarioDto.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, jftProntuario.getText());
        try {
            ResultadoListagemUsuarioDTO resultadoListagemUsuarioDto = servicoSisLaraServer.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDto);
            if (resultadoListagemUsuarioDto.sucesso()) {
                usuarioDto = resultadoListagemUsuarioDto.getObjetosDto().get(0);
                carregarCamposUsuarioELimparPreCadastro(usuarioDto);
            } else {
                limparDadosUsuario();
            }
        } catch (Exception e) {
            logger.fatal("Error: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA);
        }
    }

    public void adicionarAcompanhante() {
        new TelaAtendimentoIndividualAcompanhanteEditar((JDialog)telaPai, new AtendimentoComunidadeDTO());
        String chave = Sessao.CHAVE_ATENDIMENTO_COMUNIDADE;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            obterModeloTabelaAtendimentoComunidade().adicionarDTO((AtendimentoComunidadeDTO) Sessao.obterInstancia().obterDto(chave));
        }
    }
    
    private ModeloTabelaAtendimentoAcompanhante obterModeloTabelaAtendimentoComunidade() {
        return ((ModeloTabelaAtendimentoAcompanhante)jtaAcompanhante.getModel());
    }

    public void removerAcompanhanteSelecionado() {
        if ((estaComItemValidoSelecionado(jtaAcompanhante) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jtaAcompanhante);
        }
    }

    public void editarAcompanhanteSelecionado() {
        if (estaComItemValidoSelecionado(jtaAcompanhante)){
            new TelaAtendimentoIndividualAcompanhanteEditar((JDialog) telaPai, (AtendimentoComunidadeDTO)obterDtoSelecionado(jtaAcompanhante));
            String chave = Sessao.CHAVE_ATENDIMENTO_COMUNIDADE;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaAcompanhante, (AtendimentoComunidadeDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }

    public void abrirArquivo() {
        ArquivoDTO arquivoSelecionadoDto = (ArquivoDTO) obterDtoSelecionado(jliArquivos);
        try {
            abrirArquivo(jliArquivos, arquivoSelecionadoDto, !arquivoSelecionadoDto.possuiConteudo() ? servicoSisLaraServer.obterArquivoAtendimentoIndividualDTO(obterObjetoDTO(), arquivoSelecionadoDto) : null);
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\nDetalhes: " + e.getMessage());
        }
    } 

    public void removerArquivoSelecionado() {
        removerArquivoSelecionado(jliArquivos);
    }

    public void adicionarArquivo() {
        adicionarArquivo(jliArquivos);
    }
}

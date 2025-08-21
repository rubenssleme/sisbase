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
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemSimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AcaoCondutaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoComunidadeDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.OpcaoIntegracaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDetalhadaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemOpcaoIntegracaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemTipoAcaoCondutaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoValidacaoAcaoCondutaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.TipoAcaoCondutaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemFrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemGrupoDTO;
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
import javax.swing.JEditorPane;
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
    private ResultadoListagemTipoAcaoCondutaDTO resultadoListagemTipoAcaoCondutaDTO;
    private ResultadoListagemGrupoDTO resultadoListagemGrupoDTO;
    private ResultadoListagemSimNaoDTO resultadoListagemSimNaoDTO;
    private ResultadoListagemOpcaoIntegracaoDTO resultadoListagemOpcaoIntegracaoDto;
    
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
    private JComboBox jcbParticipacaoSelecionada;
    private JTextField jtfQuantidadePessoas;
    private JList jliParticipacoesAdicionadas;
    private JComboBox jcbTipoAcaoConduta;
    private JFormattedTextField jftDataExpectativa;
    private JComboBox jcbGrupoConduta;
    private JTextArea jtaObs; 
    private JEditorPane jepInformacoesModuloAEEConduta;
    private JList jliCondutasSolicitadas;
    private JComboBox jcbIntegracao;
    private JTextArea jtaMotivoNaoIntegracao;
    private JTextArea jtaResumoIntegracao;
    private JComboBox jcbDiscussaoCaso;
    
    public ControladorTelaAtendimentoIndividualEditar(JDialog telaPai, boolean forcarCarregamentoAtendimentoIndividualDTO, AtendimentoIndividualDTO atendimentoIndividualDto, JButton jbuUtilizarUsuario, JButton jbuUtilizarPreCadastro, JFormattedTextField jftProntuario, JTextField jtfNomeUsuario, JTextField jtfNomePreCadastro, JFormattedTextField jftData, JFormattedTextField jftHoraInicio, JFormattedTextField jftHoraTermino, JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade, JComboBox jcbPrimeiraVez, JComboBox jcbSetor, JComboBox jcbLocalAtendimento,JTextArea jatDescricao, JTextArea jatInterdisciplinar, JTable jtaAtendimentosProfissionais, JComboBox jcbFrequencia, JTextArea jatJustificativa, JComboBox jcbParticipacaoSelecionada, JTextField jtfQuantidadePessoas, JList jliParticipacoesAdicionadas, JTable jtaAcompanhante, JList jliArquivo, JComboBox jcbTipoAcaoConduta, JFormattedTextField jftDataExpectativa, JComboBox jcbGrupoConduta, JTextArea jtaObs, JEditorPane jepInformacoesModuloAEEConduta, JList jliCondutasSolicitadas, JComboBox jcbIntegracao, JTextArea jtaMotivoNaoIntegracao, JTextArea jtaResumoIntegracao, JComboBox jcbDiscussaoCaso){
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
        this.jcbParticipacaoSelecionada = jcbParticipacaoSelecionada;
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
        this.jtfQuantidadePessoas = jtfQuantidadePessoas;
        this.jliParticipacoesAdicionadas = jliParticipacoesAdicionadas;
        this.jcbTipoAcaoConduta = jcbTipoAcaoConduta;
        this.jftDataExpectativa = jftDataExpectativa;
        this.jcbGrupoConduta = jcbGrupoConduta;
        this.jtaObs = jtaObs;
        this.jepInformacoesModuloAEEConduta = jepInformacoesModuloAEEConduta;
        this.jliCondutasSolicitadas = jliCondutasSolicitadas;
        this.jcbIntegracao = jcbIntegracao;
        this.jtaMotivoNaoIntegracao = jtaMotivoNaoIntegracao;
        this.jtaResumoIntegracao = jtaResumoIntegracao;
        this.jcbDiscussaoCaso = jcbDiscussaoCaso;
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
        obterObjetoDTO().getInformacaoAtendimentoDto().setParticipadaoDetalhadaDto((List<ParticipacaoDetalhadaDTO>)obterDtos(jliParticipacoesAdicionadas));
        obterObjetoDTO().setInterdisciplinar(jatInterdisciplinar.getText());
        obterObjetoDTO().setAtendimentosProfissionalDto((List<AtendimentoProfissionalDTO>)obterDtos(jtaAtendimentosProfissionais));
        obterObjetoDTO().setAtendimentosComunidadeDto((List<AtendimentoComunidadeDTO>)obterDtos(jtaAcompanhante));
        obterObjetoDTO().setLocalAtendimentoDto((LocalAtendimentoDTO)obterDtoSelecionado(jcbLocalAtendimento));
        obterObjetoDTO().setParticipacaoDto((ParticipacaoDTO)obterDtoSelecionado(jcbParticipacaoSelecionada));
        obterObjetoDTO().setPrimeiraVezOuRetornoDto((StatusDTO)obterDtoSelecionado(jcbPrimeiraVez));
        obterObjetoDTO().setArquivos((List<ArquivoDTO>)obterDtos(jliArquivos));
        obterObjetoDTO().setAcoesDeCondutasDto((List<AcaoCondutaDTO>)obterDtos(jliCondutasSolicitadas));
        obterObjetoDTO().setOpcaoIntegracaoDto((OpcaoIntegracaoDTO) obterDtoSelecionado(jcbIntegracao));
        obterObjetoDTO().setMotivoNaoIntegracao(jtaMotivoNaoIntegracao.getText());
        obterObjetoDTO().setDiscussaoCasoSimNaoDto((SimNaoDTO) obterDtoSelecionado(jcbDiscussaoCaso));
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
            adicionarDtos(obterObjetoDTO().getInformacaoAtendimentoDto().getParticipacaoDetalhadaDto(), jliParticipacoesAdicionadas);
            selecionarDto(jcbLocalAtendimento, obterObjetoDTO().getLocalAtendimentoDto());
            selecionarDto(jcbParticipacaoSelecionada, obterObjetoDTO().getParticipacaoDto());
            selecionarDto(jcbPrimeiraVez, obterObjetoDTO().getPrimeiraVezOuRetornoDto());
            adicionarDtos(obterObjetoDTO().getArquivos(), jliArquivos);
            adicionarDtos(obterObjetoDTO().getAcoesDeCondutasDto(), jliCondutasSolicitadas);
            selecionarDto(jcbIntegracao, obterObjetoDTO().getOpcaoIntegracaoDto());
            jtaMotivoNaoIntegracao.setText(obterObjetoDTO().getMotivoNaoIntegracao());
            jtaResumoIntegracao.setText(obterObjetoDTO().getResumoIntegracao());
            selecionarDto(jcbDiscussaoCaso, obterObjetoDTO().getDiscussaoCasoSimNaoDto());
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
            resultadoListagemTipoAcaoCondutaDTO = servicoSisLaraServer.obterListagemTipoAcaoConduta();
            resultadoListagemSimNaoDTO = servicoSisLaraServer.obterListagemSimNao();
            resultadoListagemOpcaoIntegracaoDto = servicoSisLaraServer.obterListagemOpcaoIntegracao();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbLocalAtendimento, resultadoListagemLocalAtendimentoDto.getObjetosDto());
        adicionarDtos(jcbParticipacaoSelecionada, resultadoListagemParticipacaoDto.getObjetosDto());
        adicionarDtos(jcbFrequencia, resultadoListagemFrequenciaDto.getObjetosDto());
        adicionarDtos(jcbPrimeiraVez, resultadoListagemStatusDTO.getObjetosDto());
        adicionarDtos(jcbTipoAcaoConduta, resultadoListagemTipoAcaoCondutaDTO.getObjetosDto());
        adicionarDtos(jcbIntegracao, resultadoListagemOpcaoIntegracaoDto.getObjetosDto());
        adicionarDtos(jcbDiscussaoCaso, resultadoListagemSimNaoDTO.getObjetosDto());
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
            carregarResumosDePosicionamentoDeIntegracao();
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
        jtaResumoIntegracao.setText("");
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
        adicionarArquivo(jliArquivos, jftProntuario.getText());
    }

    public void adicionarParticipacaoDetalhada() {
        adicionarParticipacaoDetalhada(jcbParticipacaoSelecionada, jtfQuantidadePessoas, jliParticipacoesAdicionadas);
    }

    public void removerParticipacaoDetalhadaSelecionada() {
        removerParticipacaoDetalhadaSelecionada(jliParticipacoesAdicionadas);
    }

    public void inicializarQuantidadePessoasPadrao() {
        if (jtfQuantidadePessoas.getText().isEmpty()){
            jtfQuantidadePessoas.setText("1");
        }
    }

    public void carregarInformacoesDoModuloAEESelecionado() {
        if (estaComItemValidoSelecionado(jcbGrupoConduta)){
            GrupoDTO grupoDto = (GrupoDTO) obterDtoSelecionado(jcbGrupoConduta);
            jepInformacoesModuloAEEConduta.setText(grupoDto.obterInformacoesModuloAEE());
        }else{
            jepInformacoesModuloAEEConduta.setText("");
        }
    }

    private void limparConduta(){
        deselecionarDto(jcbTipoAcaoConduta);
        if (estaComItemValidoSelecionado(jcbGrupoConduta)){
            deselecionarDto(jcbGrupoConduta);
        }
        jepInformacoesModuloAEEConduta.setText("");
        jftDataExpectativa.setText("");
        jtaObs.setText("");
    }
    
    public void adicionarAcaoConduta() {
        AcaoCondutaDTO acaoCondutaDto = new AcaoCondutaDTO();
        acaoCondutaDto.setTipoAcaoCondutaDto((TipoAcaoCondutaDTO) obterDtoSelecionado(jcbTipoAcaoConduta));
        acaoCondutaDto.setGrupoDto((GrupoDTO) obterDtoSelecionado(jcbGrupoConduta));
        acaoCondutaDto.setDataExpectativa(jftDataExpectativa.getText());
        acaoCondutaDto.setObs(jtaObs.getText());
        acaoCondutaDto.setSetorDto((SetorDTO)obterDtoSelecionado(jcbSetor));
        try {
            ResultadoValidacaoAcaoCondutaDTO resultadoValidacaoAcaoCondutaDTO = servicoSisLaraServer.validarAcaoConduta(acaoCondutaDto);
            if (resultadoValidacaoAcaoCondutaDTO.sucesso()) {
                adicionarDtos(resultadoValidacaoAcaoCondutaDTO.obterObjetoDtoEditado(), jliCondutasSolicitadas);
                limparConduta();
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoValidacaoAcaoCondutaDTO.obterMensagens());
            }
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_VALIDACAO_ACAO_CONDUTA + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_VALIDACAO_ACAO_CONDUTA + "\nDetalhes: " + e.getMessage());
        }
    }

    public void cancelarAcaoConduta() {
        if (estaComItemValidoSelecionado(jliCondutasSolicitadas)){
            AcaoCondutaDTO acaoCondutaDTO = (AcaoCondutaDTO) obterDtoSelecionado(jliCondutasSolicitadas);
            acaoCondutaDTO.setCancelada(true);
            adicionarDtoNoItemSelecionado(jliCondutasSolicitadas, acaoCondutaDTO);
        }
    }

    public void carregarComboGrupos() {
        try {
            if (resultadoListagemGrupoDTO == null) {
                resultadoListagemGrupoDTO = servicoSisLaraServer.obterListagemTodosGruposAtivos();
                adicionarDtos(jcbGrupoConduta, resultadoListagemGrupoDTO.getObjetosDto());
            }
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    private void carregarResumosDePosicionamentoDeIntegracao() {
        try {
            jtaResumoIntegracao.setText(servicoSisLaraServer.obterResumoIntegracao(jftProntuario.getText()));
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
}

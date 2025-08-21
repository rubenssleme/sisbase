package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaUtilizarPreCadastroSemValidacao;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarUsuario;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.ResultadoListagemTipoEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.TipoEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class ControladorTelaListaEsperaEditar extends ControladorTelaEditar{

    private ControladorTipoAtendimento controladorTipoAtendimento;
    
    private ResultadoListagemTipoEsperaDTO resultadoListagemTipoEsperaDto;
    private ResultadoListagemProfissionalDTO resultadoListagemProfissionalDto;
    
    private JFormattedTextField jtfProntuario;
    private JTextField jtfNomeUsuario;
    protected JTextField jtfIdadeUsuario;
    protected JTextField jtfStatus;
    private JTextField jtfNomePreCadastro;
    protected JTextField jtfIdadePre;
    private JComboBox jcbDescricaoTipoAtendimento;
    private JComboBox jcbModuloAtividade;
    private JComboBox jcbNomeGrupo;
    private JFormattedTextField jftData;
    private JComboBox jcbTipoEspera;
    private JComboBox jcbSetor;
    private JComboBox jcbSolicitadoPor;
    private JEditorPane jepObservacoes;
    private JEditorPane jepObservacoesHistoricas;
    
    private UsuarioDTO usuarioDto;
    private PreCadastroDTO preCadastroDto;
    
    public ControladorTelaListaEsperaEditar(JDialog telaPai, EsperaDTO esperaDto, JFormattedTextField jtfProntuario, JTextField jtfNomeUsuario,  JTextField jtfIdadeUsuario,  JTextField jtfStatus, JTextField jtfNomePreCadastro, JTextField jtfIdadePre, JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade, JComboBox jcbNomeGrupo, JFormattedTextField jftData, JComboBox jcbTipoEspera, JComboBox jcbSetor, JComboBox jcbSolicitadoPor, JEditorPane jepObservacoes, JEditorPane jepObservacoesHistoricas){
        super(telaPai, esperaDto);
        this.jtfProntuario = jtfProntuario;
        this.jtfNomeUsuario = jtfNomeUsuario;
        this.jtfIdadeUsuario = jtfIdadeUsuario;
        this.jtfStatus = jtfStatus;
        this.jtfNomePreCadastro = jtfNomePreCadastro;
        this.jtfIdadePre = jtfIdadePre;
        this.jcbDescricaoTipoAtendimento = jcbDescricaoTipoAtendimento;
        this.jcbModuloAtividade = jcbModuloAtividade;
        this.jcbNomeGrupo = jcbNomeGrupo;
        this.jftData = jftData;
        this.jcbTipoEspera = jcbTipoEspera;
        this.jcbSetor = jcbSetor;
        this.jcbSolicitadoPor = jcbSolicitadoPor;
        this.jepObservacoes = jepObservacoes;
        this.jepObservacoesHistoricas = jepObservacoesHistoricas;
        this.controladorTipoAtendimento = new ControladorTipoAtendimento(jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade, jcbSetor, jcbNomeGrupo);
        inicializarCombosDadosAuxiliares();
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbTipoEspera, resultadoListagemTipoEsperaDto.getObjetosDto());
        adicionarDtos(jcbSolicitadoPor, resultadoListagemProfissionalDto.getObjetosDto());
    }
    
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemTipoEsperaDto = servicoSisLaraServer.obterListagemTipoEsperaDisponiveisParaInclusao();
            resultadoListagemProfissionalDto = servicoSisLaraServer.obterListagemProfissionalAtivos();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
        
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoDTO) servicoSisLaraServer.editarEspera(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        EsperaDTO esperaDto = obterObjetoDTO();
        esperaDto.setUsuarioDto(usuarioDto);
        esperaDto.setPreCadastroDto(preCadastroDto);
        esperaDto.setDescricaoTipoAtendimentoDto((DescricaoTipoAtendimentoDTO)obterDtoSelecionado(jcbDescricaoTipoAtendimento));
        esperaDto.setModuloDto((ModuloDTO)obterDtoSelecionado(jcbModuloAtividade));
        esperaDto.setDataExpectativa(jftData.getText());
        esperaDto.setSetorDto((SetorDTO)obterDtoSelecionado(jcbSetor));
        esperaDto.setNomeGrupoDto((NomeGrupoDTO)obterDtoSelecionado(jcbNomeGrupo));
        esperaDto.setTipoEsperaDto((TipoEsperaDTO)obterDtoSelecionado(jcbTipoEspera));
        esperaDto.setProfissionalSolicitouDto((ProfissionalDTO)obterDtoSelecionado(jcbSolicitadoPor));
        esperaDto.setObs(jepObservacoes.getText());
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void executarAcaoAposInclusao() {
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_ESPERA;
    }
    
    private EsperaDTO obterObjetoDTO(){
        return (EsperaDTO)objetoDto;
    }

    @Override
    public void carregarCampos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void inicializarDescricaoTipoAtendimento() {
        controladorTipoAtendimento.inicializarDescricaoTipoAtendimento();
    }

    public void inicializarModuloAtividadeESetor() {
        controladorTipoAtendimento.inicializarModuloAtividadeESetor();
        controladorTipoAtendimento.inicializarNomeGrupo();
    }

    public void abrirTelaUtilizarUsuario() {
         new TelaUtilizarUsuario((JDialog)telaPai);
         atualizaCamposUsuario();
    }
    
    private void atualizaCamposUsuario(){
        String chave = Sessao.CHAVE_USUARIO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            usuarioDto = (UsuarioDTO)Sessao.obterInstancia().obterDto(chave);
            carregarCamposUsuario(usuarioDto);
            limparDadosPreCadastro();            
        }
    }
    
    private void limparDadosPreCadastro(){
        jtfNomePreCadastro.setText("");
        jtfIdadePre.setText("");
        preCadastroDto = null;
    }
    
    private void carregarCamposUsuario(UsuarioDTO usuarioDto) {
        jtfProntuario.setText(usuarioDto.getId().toString());
        jtfNomeUsuario.setText(usuarioDto.getInformacaoEssencialDto().getNome());
        jtfIdadeUsuario.setText(usuarioDto.getInformacaoEssencialDto().getIdade());
        jtfStatus.setText(usuarioDto.getStatusUsuarioAtualDto() != null ? usuarioDto.getStatusUsuarioAtualDto().toString() : "");
    }
    
    public void abrirTelaUtilizarPreCadastro() {
        new TelaUtilizarPreCadastroSemValidacao((JDialog)telaPai);
        atualizaCamposPreCadastro();
    }

    private void atualizaCamposPreCadastro() {
        String chave = Sessao.CHAVE_PRE_CADASTRO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            preCadastroDto = (PreCadastroDTO)Sessao.obterInstancia().obterDto(chave);
            carregarCamposPreCadastro(preCadastroDto);
            limparDadosUsuario();
        }
    }
    
    private void carregarCamposPreCadastro(PreCadastroDTO preCadastroDto) {
        jtfNomePreCadastro.setText(preCadastroDto.getInformacaoEssencialDto().getNome());
        jtfIdadePre.setText(preCadastroDto.getInformacaoEssencialDto().getIdade());
    }

    private void limparDadosUsuario(){
        usuarioDto = null;
        jtfProntuario.setText("");
        jtfNomeUsuario.setText("");
        jtfIdadeUsuario.setText("");
        jtfStatus.setText("");
    }
    
    public void selecionarProntuario() {
        EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDto = new EspecificacaoPesquisaUsuarioDTO();
        especificacaoPesquisaUsuarioDto.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, jtfProntuario.getText());
        try {
            ResultadoListagemUsuarioDTO resultadoListagemUsuarioDto = servicoSisLaraServer.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDto);
            if (resultadoListagemUsuarioDto.sucesso()) {
                usuarioDto = resultadoListagemUsuarioDto.getObjetosDto().get(0);
                carregarCamposUsuario(usuarioDto);
                carregarObservacoesHistoricas(usuarioDto.getInformacaoEssencialDto(), jepObservacoesHistoricas);
                limparDadosPreCadastro();
            } else {
                limparDadosUsuario();
            }
        } catch (Exception e) {
            logger.fatal("Error: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA);
        }
    }
}


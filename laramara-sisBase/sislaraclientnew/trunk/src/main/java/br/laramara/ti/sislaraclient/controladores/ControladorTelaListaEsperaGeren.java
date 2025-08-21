package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaListaEspera;
import br.laramara.ti.sislaraclient.telas.TelaListaEsperaAssociar;
import br.laramara.ti.sislaraclient.telas.TelaListaEsperaCancelamento;
import br.laramara.ti.sislaraclient.telas.TelaListaEsperaConclusao;
import br.laramara.ti.sislaraclient.telas.TelaListaEsperaEditar;
import br.laramara.ti.sislaraclient.telas.TelaListaEsperaObs;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarPreCadastroSemValidacao;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarUsuario;
import br.laramara.ti.sislaraclient.utilitarios.EsperaUtils;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EspecificacaoPesquisaEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.ResultadoListagemStatusEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.ResultadoListagemTipoEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.StatusEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.TipoEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemSetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaListaEsperaGeren extends ControladorTelaPesquisarBase {

    private ControladorTipoAtendimento controladorTipoAtendimento;
    
    private ResultadoListagemStatusEsperaDTO resultadoListagemStatusEsperaDto;
    private ResultadoListagemTipoEsperaDTO resultadoListagemTipoEsperaDto;
    private ResultadoListagemSetorDTO resultadoListagemSetorDto;
    
    private JComboBox jcbDescricaoTipoAtendimento;
    private JComboBox jcbModuloAtividade;
    private JComboBox jcbNomeGrupo;
    private JFormattedTextField jftDataInicio;
    private JFormattedTextField jftDataTermino;
    private JComboBox jcbTipoEspera;
    private JComboBox jcbStatus;
    private JComboBox jcbSetor;
    private JFormattedTextField jftProntuario;
    private JTextField jtfRg;
    private JCheckBox jchInteresse;
    private JCheckBox jchLigou;
    private JCheckBox jchPendencias;
    private JTable jtaListaEspera;
    
    public ControladorTelaListaEsperaGeren(JDialog telaPai, JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade, JComboBox jcbNomeGrupo, JFormattedTextField jftDataInicio, JFormattedTextField jftDataTermino, JComboBox jcbTipoEspera, JComboBox jcbStatus, JComboBox jcbSetor, JFormattedTextField jftProntuario, JTextField jtfRg, JCheckBox jchInteresse, JCheckBox jchLigou, JCheckBox jchPendencias, JTable jtaListaEspera){
        super(telaPai, jtaListaEspera);
        this.jcbDescricaoTipoAtendimento = jcbDescricaoTipoAtendimento;
        this.jcbModuloAtividade = jcbModuloAtividade;
        this.jcbNomeGrupo = jcbNomeGrupo;
        this.jftDataInicio = jftDataInicio;
        this.jftDataTermino = jftDataTermino;
        this.jcbTipoEspera = jcbTipoEspera;
        this.jcbStatus = jcbStatus;
        this.jcbSetor = jcbSetor;
        this.jftProntuario = jftProntuario;
        this.jtfRg = jtfRg;
        this.jchInteresse = jchInteresse;
        this.jchLigou = jchLigou;
        this.jchPendencias = jchPendencias;
        this.jtaListaEspera = jtaListaEspera;
        this.controladorTipoAtendimento = new ControladorTipoAtendimento(jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade, jcbSetor, jcbNomeGrupo);
        configurarColunasTabela();
        inicializarCombosDadosAuxiliares();
    }
    
    public void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaListaEspera);
    }
    
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbTipoEspera, resultadoListagemTipoEsperaDto.getObjetosDto());
        adicionarDtos(jcbStatus, resultadoListagemStatusEsperaDto.getObjetosDto());
        adicionarDadosSetor();
    }
    
    private void adicionarDadosSetor(){
        adicionarDtos(jcbSetor, resultadoListagemSetorDto.getObjetosDto());
    }
    
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemStatusEsperaDto = servicoSisLaraServer.obterListagemStatusEspera();
            resultadoListagemTipoEsperaDto = servicoSisLaraServer.obterListagemTipoEsperaTotal();
            resultadoListagemSetorDto = servicoSisLaraServer.obterListagemSetor();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    public void inicializarDescricaoTipoAtendimento() {
        controladorTipoAtendimento.inicializarDescricaoTipoAtendimento();
    }

    public void inicializarModuloAtividadeESetor() {
        if (estaComItemValidoSelecionado(jcbDescricaoTipoAtendimento)){
            controladorTipoAtendimento.inicializarModuloAtividadeESetor();
            controladorTipoAtendimento.inicializarNomeGrupo();
        }else{
            removerDtos(jcbModuloAtividade);
            removerDtos(jcbNomeGrupo);
            adicionarDadosSetor();
        }
    }

    public void novaEspera() {
        new TelaListaEsperaEditar((JDialog)telaPai, new EsperaDTO());
        String chave = Sessao.CHAVE_ESPERA;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            obterModeloTabelaEspera().adicionarDTO((EsperaDTO) Sessao.obterInstancia().obterDto(chave));
        }
    }
    
    private ModeloTabela obterModeloTabelaEspera(){
        return ((ModeloTabelaListaEspera)jtaListaEspera.getModel());
    }

    public void efetuarPesquisa() {
        final EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
        especificacao.setDescricaoTipoAtendimentoDto((DescricaoTipoAtendimentoDTO)obterDtoSelecionado(jcbDescricaoTipoAtendimento));
        especificacao.setModuloDto((ModuloDTO)obterDtoSelecionado(jcbModuloAtividade));
        especificacao.setNomeGrupo((NomeGrupoDTO)obterDtoSelecionado(jcbNomeGrupo));
        especificacao.setDataInicio(jftDataInicio.getText());
        especificacao.setDataTermino(jftDataTermino.getText());
        especificacao.setTipoEsperaDto((TipoEsperaDTO)obterDtoSelecionado(jcbTipoEspera));
        especificacao.setSetorDto((SetorDTO)obterDtoSelecionado(jcbSetor));
        especificacao.setStatusEsperadoDto((StatusEsperaDTO)obterDtoSelecionado(jcbStatus));
        especificacao.setProntuario(jftProntuario.getText());
        especificacao.setRg(jtfRg.getText());
        especificacao.setInteresse(jchInteresse.isSelected());
        especificacao.setLmLigou(jchLigou.isSelected());
        especificacao.setPendencias(jchPendencias.isSelected());
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
        return servicoSisLaraServer.obterListagemEspera((EspecificacaoPesquisaEsperaDTO)especificacaoPesquisaDto);
    }

    public void cancelarEspera() {
        if ((estaComItemValidoSelecionado(jtaListaEspera)) && !((EsperaDTO) obterDtoSelecionado(jtaListaEspera)).estaCancelado()) {
            new TelaListaEsperaCancelamento((JDialog) telaPai, (EsperaDTO) obterDtoSelecionado(jtaListaEspera));
            atualizarDto();
        }
    }
    
    private void atualizarDto() {
        String chave = Sessao.CHAVE_ESPERA;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            adicionarDtoNoItemSelecionado(jtaListaEspera, (EsperaDTO) Sessao.obterInstancia().obterDto(chave));
        }
    }

    public void concluirEspera() {
        if ((estaComItemValidoSelecionado(jtaListaEspera)) && (((EsperaDTO) obterDtoSelecionado(jtaListaEspera)).estaAguardando() || ((EsperaDTO) obterDtoSelecionado(jtaListaEspera)).estaTriando())) {
            new TelaListaEsperaConclusao((JDialog) telaPai, (EsperaDTO) obterDtoSelecionado(jtaListaEspera));
            atualizarDto();
        }
    }

    public void localizarUsuario() {
        new TelaUtilizarUsuario((JDialog) telaPai);
        String chave = Sessao.CHAVE_USUARIO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            UsuarioDTO usuarioDTO = (UsuarioDTO) Sessao.obterInstancia().obterDto(chave);
            jftProntuario.setText(usuarioDTO.getId().toString());
        }
    }

    public void localizarPreCadastro() {
        new TelaUtilizarPreCadastroSemValidacao((JDialog) telaPai);
        String chave = Sessao.CHAVE_PRE_CADASTRO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            PreCadastroDTO preCadastroDTO = (PreCadastroDTO) Sessao.obterInstancia().obterDto(chave);
            jtfRg.setText(preCadastroDTO.getInformacaoEssencialDto().getRg());
        }
    }

    public void abrirTelaAssociacao() {
        if (estaComItemValidoSelecionado(jtaListaEspera) && ((EsperaDTO) obterDtoSelecionado(jtaListaEspera)).estaAguardando()) {
            EsperaDTO esperaDtoSelecionado = (EsperaDTO) obterDtoSelecionado(jtaListaEspera);
            new TelaListaEsperaAssociar((JDialog) telaPai, esperaDtoSelecionado);
            atualizarDto();
        }
    }

    public void alterarObsEspera() {
        if (estaComItemValidoSelecionado(jtaListaEspera)) {
            new TelaListaEsperaObs((JDialog) telaPai, (EsperaDTO) obterDtoSelecionado(jtaListaEspera));
            atualizarDto();
        }
    }
}

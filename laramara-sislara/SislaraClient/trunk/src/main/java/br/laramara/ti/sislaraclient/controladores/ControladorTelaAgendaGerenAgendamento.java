package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaAgendamento;
import br.laramara.ti.sislaraclient.telas.TelaAgendaCopiarAgendamento;
import br.laramara.ti.sislaraclient.telas.TelaAgendaEditarAssociacao;
import br.laramara.ti.sislaraclient.telas.TelaAgendaEditarGeracao;
import br.laramara.ti.sislaraclient.telas.TelaAgendaEditarJustificativaCancelamento;
import br.laramara.ti.sislaraclient.telas.TelaAgendaEditarJustificativaLiberacao;
import br.laramara.ti.sislaraclient.telas.TelaAgendaReagendamento;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarPreCadastroSemValidacao;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarUsuario;
import br.laramara.ti.sislaraclient.utilitarios.EsperaUtils;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaraclient.utilitarios.TelaUtils;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoPesquisaAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoGeracaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoListagemStatusAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.StatusAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaAgendaGerenAgendamento extends ControladorTelaPesquisarBase {

    private ControladorTipoAtendimento controladorTipoAtendimento;
    
    private ResultadoListagemProfissionalDTO resultadoListagemProfissionalDto;
    private ResultadoListagemStatusAgendamentoDTO resultadoListagemStatusAgendamentoDto;
    
    private JComboBox jcbProfissional;
    private JComboBox jcbDescricaoTipoAtendimento;
    private JComboBox jcbModuloAtividade;
    private JFormattedTextField jftDataInicio;
    private JFormattedTextField jftDataTermino;
    private JComboBox jcbStatusAgendamento;
    private JFormattedTextField jftProntuario;
    private JTextField jtfRgPreCadastro;
    private JDayChooser jdcData;
    private JMonthChooser jmcData;
    private JYearChooser jycData;
    private JTable jtaAgendamentos;

    public ControladorTelaAgendaGerenAgendamento(JDialog telaPai, JComboBox jcbProfissional, JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade, JFormattedTextField jftDataInicio, JFormattedTextField jftDataTermino, JComboBox jcbStatusAgendamento, JFormattedTextField jftProntuario, JTextField jtfRgPreCadastro, JDayChooser jdcData, JMonthChooser jmcData, JYearChooser jycData, JTable jtaAgendamentos) {
        super(telaPai, jtaAgendamentos);
        this.jcbProfissional = jcbProfissional;
        this.jcbDescricaoTipoAtendimento = jcbDescricaoTipoAtendimento;
        this.jcbModuloAtividade = jcbModuloAtividade;
        this.jftDataInicio = jftDataInicio;
        this.jftDataTermino = jftDataTermino;
        this.jcbStatusAgendamento = jcbStatusAgendamento;
        this.jftProntuario = jftProntuario;
        this.jtfRgPreCadastro = jtfRgPreCadastro;
        this.jdcData = jdcData;
        this.jmcData = jmcData;
        this.jycData = jycData;
        this.jtaAgendamentos = jtaAgendamentos;
        this.controladorTipoAtendimento = new ControladorTipoAtendimento(jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade);
        configurarColunasTabela();
        inicializarCombosDadosAuxiliares();
    }

    public void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaAgendamentos);
    }
    
    public void novoAgendamento() {
        new TelaAgendaEditarGeracao((JDialog) telaPai);
        String chave = Sessao.CHAVE_LISTAGEM_AGENDAMENTOS;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            ResultadoGeracaoAgendamentoDTO resultado = (ResultadoGeracaoAgendamentoDTO) Sessao.obterInstancia().obterDto(chave);
            adicionarDtos(resultado.getObjetosDto(), jtaAgendamentos);
        }
    }

    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbProfissional, resultadoListagemProfissionalDto.getObjetosDto());
        adicionarDtos(jcbStatusAgendamento, resultadoListagemStatusAgendamentoDto.getObjetosDto());
    }

    public void obterDadosAuxiliares() {
        try {
            resultadoListagemProfissionalDto = servicoSisLaraServer.obterListagemProfissionalAtivos();
            resultadoListagemStatusAgendamentoDto = servicoSisLaraServer.obterListagemStatusAgendamento();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    public void inicializarDescricaoTipoAtendimento() {
        controladorTipoAtendimento.inicializarDescricaoTipoAtendimento();
    }

    public void pesquisarAgendamentoUsandoCalendario() {
        if (telaPai.isShowing()) {
            jftDataInicio.setText(TelaUtils.obterData(jdcData, jmcData, jycData));
            jftDataTermino.setText("");
            efetuarPesquisa();
        }
    }

    public void abrirTelaAgendaEditarCancelamento() {
        if (estaComItemValidoSelecionado(jtaAgendamentos) && !((AgendamentoDTO) obterDtoSelecionado(jtaAgendamentos)).isEstaCancelado()) {
            new TelaAgendaEditarJustificativaCancelamento((JDialog) telaPai, (AgendamentoDTO) obterDtoSelecionado(jtaAgendamentos));
            String chave = Sessao.CHAVE_AGENDAMENTO;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaAgendamentos, (AgendamentoDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }
            
    private ModeloTabela obterModeloTabelaAgendamento(){
        return ((ModeloTabelaAgendamento)jtaAgendamentos.getModel());
    }

    public void abrirTelaAgendaEditarAssociacao() {
        if (estaComItemValidoSelecionado(jtaAgendamentos) && ((AgendamentoDTO) obterDtoSelecionado(jtaAgendamentos)).isEstaDisponivel()) {
            new TelaAgendaEditarAssociacao((JDialog) telaPai, (AgendamentoDTO) obterDtoSelecionado(jtaAgendamentos));
            String chave = Sessao.CHAVE_AGENDAMENTO;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaAgendamentos, (AgendamentoDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }

    public void efetuarCopiaAgendamento() {
        if (estaComItemValidoSelecionado(jtaAgendamentos) && ((AgendamentoDTO)obterDtoSelecionado(jtaAgendamentos)).isEstaCancelado()){
            new TelaAgendaCopiarAgendamento((JDialog)telaPai, (AgendamentoDTO)obterDtoSelecionado(jtaAgendamentos));
            String chave = Sessao.CHAVE_AGENDAMENTO;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                obterModeloTabelaAgendamento().adicionarDTO((AgendamentoDTO)Sessao.obterInstancia().obterDto(chave));
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

    public void efetuarPesquisa() {
        final EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
        especificacao.setProfissionalDto((ProfissionalDTO) obterDtoSelecionado(jcbProfissional));
        especificacao.setDescricaoTipoAtendimentoDTO((DescricaoTipoAtendimentoDTO) obterDtoSelecionado(jcbDescricaoTipoAtendimento));
        especificacao.setModuloDTO((ModuloDTO) obterDtoSelecionado(jcbModuloAtividade));
        especificacao.setDataInicio(jftDataInicio.getText());
        especificacao.setDataTermino(jftDataTermino.getText());
        especificacao.setStatusAgendamentoDTO((StatusAgendamentoDTO) obterDtoSelecionado(jcbStatusAgendamento));
        especificacao.setProntuario(jftProntuario.getText());
        especificacao.setRgPreCadastro(jtfRgPreCadastro.getText());
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
         return servicoSisLaraServer.obterListagemAgendamento((EspecificacaoPesquisaAgendamentoDTO)especificacaoPesquisaDto);
    }

    public void inicializarModuloAtividade() {
        controladorTipoAtendimento.inicializarModuloAtividade();
    }

    public void localizarPreCadastro() {
        new TelaUtilizarPreCadastroSemValidacao((JDialog)telaPai);
        String chave = Sessao.CHAVE_PRE_CADASTRO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            PreCadastroDTO preCadastroDTO = (PreCadastroDTO) Sessao.obterInstancia().obterDto(chave);
            jtfRgPreCadastro.setText(preCadastroDTO.getInformacaoEssencialDto().getRg());
        }
    }

    public void liberarAgendamentoSelecionado() {
         if (estaComItemValidoSelecionado(jtaAgendamentos) &&  ((AgendamentoDTO)obterDtoSelecionado(jtaAgendamentos)).isEstaAgendado()){
            new TelaAgendaEditarJustificativaLiberacao((JDialog)telaPai, (AgendamentoDTO)obterDtoSelecionado(jtaAgendamentos));
            String chave = Sessao.CHAVE_AGENDAMENTO;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaAgendamentos, (AgendamentoDTO)Sessao.obterInstancia().obterDto(chave));
            }
        }
    }

    public void abrirTelaReagendamento() {
        if (estaComItemValidoSelecionado(jtaAgendamentos) && ((AgendamentoDTO) obterDtoSelecionado(jtaAgendamentos)).isEstaAgendado()) {
            new TelaAgendaReagendamento((JDialog) telaPai, (AgendamentoDTO) obterDtoSelecionado(jtaAgendamentos));
            String chave = Sessao.CHAVE_AGENDAMENTO;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaAgendamentos, (AgendamentoDTO)Sessao.obterInstancia().obterDto(chave));
            }
        }
    }
}

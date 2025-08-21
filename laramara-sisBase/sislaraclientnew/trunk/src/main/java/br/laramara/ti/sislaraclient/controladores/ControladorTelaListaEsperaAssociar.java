package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.EsperaUtils;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoAssociacaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoPesquisaAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoListagemAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.ResultadoEdicaoEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;

public class ControladorTelaListaEsperaAssociar extends ControladorTelaEditar {

    private ControladorTipoAtendimento controladorTipoAtendimento;
    
    private EsperaDTO esperaDto;
    private JComboBox jcbTipoAtendimento;
    private JComboBox jcbDescricaoTipoAtendimento;
    private JComboBox jcbModuloAtividade;
    private JTable jtaAgendamentos;

    public ControladorTelaListaEsperaAssociar(JDialog telaPai, EsperaDTO esperaDto, JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade, JTable jtaAgendamentos) {
        super(telaPai, null);
        this.esperaDto = esperaDto;
        this.jcbTipoAtendimento = jcbTipoAtendimento;
        this.jcbDescricaoTipoAtendimento = jcbDescricaoTipoAtendimento;
        this.jcbModuloAtividade = jcbModuloAtividade;
        this.jtaAgendamentos = jtaAgendamentos;
        this.controladorTipoAtendimento = new ControladorTipoAtendimento(jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade);
        configurarColunasTabela();
        pesquisarAgendamentosDto();
    }

    public void inicializarCombosComDependencia() {
        if (esperaDto != null) {
            atualizarCamposTipoAtendimentoDescricaoAtendimentoNomeGrupoSetor();
        }
    }
    
    private void atualizarCamposTipoAtendimentoDescricaoAtendimentoNomeGrupoSetor(){
        selecionarDto(jcbTipoAtendimento, esperaDto.getDescricaoTipoAtendimentoDto().getTipoAtendimentoDto());
        selecionarDto(jcbDescricaoTipoAtendimento, esperaDto.getDescricaoTipoAtendimentoDto());
        selecionarDto(jcbModuloAtividade, esperaDto.getModuloDto());
        pesquisarAgendamentosDto();
    }
    
    public void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaAgendamentos);
    }

    public void inicializarCombosDadosAuxiliares() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void obterDadosAuxiliares() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        ResultadoEdicaoEsperaDTO resultadoEdicaoAgendamentoDto = new ResultadoEdicaoEsperaDTO();
        if (estaComItemValidoSelecionado(jtaAgendamentos)) {
            AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDtoSelecionado(jtaAgendamentos);
            try {
                EspecificacaoAssociacaoAgendamentoDTO especificacaoAssociacaoAgendamentoDTO = new EspecificacaoAssociacaoAgendamentoDTO();
                especificacaoAssociacaoAgendamentoDTO.setEsperaDto(esperaDto);
                especificacaoAssociacaoAgendamentoDTO.setAgendamentoDto(agendamentoDto);
                resultadoEdicaoAgendamentoDto = servicoSisLaraServer.editarEsperaEAssociarAgendamento(especificacaoAssociacaoAgendamentoDTO, Sessao.obterInstancia().obterToken());
            } catch (Exception ex) {
                resultadoEdicaoAgendamentoDto.adicionarErro(JOptionPanePersonalizado.ERRO_NA_GERACAO_AGENDAMENTO);
            }
        } else {
            resultadoEdicaoAgendamentoDto.adicionarErro(JOptionPanePersonalizado.ERRO_DURANTE_SELECAO_AGENDAMENTO);
        }
        return resultadoEdicaoAgendamentoDto;
    }

    @Override
    protected void prepararDtoParaEditar() {
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

    @Override
    public void carregarCampos() {
    }

    public void pesquisarAgendamentosDtoAPartirParametrosSelecionadosNoCombobox() {
        final EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto = new EspecificacaoPesquisaAgendamentoDTO();
        especificacaoPesquisaAgendamentoDto.setDescricaoTipoAtendimentoDTO((DescricaoTipoAtendimentoDTO) obterDtoSelecionado(jcbDescricaoTipoAtendimento));
        especificacaoPesquisaAgendamentoDto.setModuloDTO((ModuloDTO) obterDtoSelecionado(jcbModuloAtividade));
        
        Runnable comando = new Runnable() {
            @Override
            public void run() {
                pesquisarAgendamentosDto(especificacaoPesquisaAgendamentoDto);
            }
        };
        new EsperaUtils(comando, (JDialog) telaPai).execute();
    }
    
    public void pesquisarAgendamentosDto(){
        EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto = new EspecificacaoPesquisaAgendamentoDTO();
        especificacaoPesquisaAgendamentoDto.setDescricaoTipoAtendimentoDTO(esperaDto.getDescricaoTipoAtendimentoDto());
        especificacaoPesquisaAgendamentoDto.setModuloDTO(esperaDto.getModuloDto());
        pesquisarAgendamentosDto(especificacaoPesquisaAgendamentoDto);
    }
    
    public void pesquisarAgendamentosDto(EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto) {
        if (especificacaoPesquisaAgendamentoDto.getModuloDTO() != null) {
            try {
                ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServer.obterListagemAgendamentoDisponiveis(especificacaoPesquisaAgendamentoDto);
                if (resultado.sucesso()) {
                    adicionarDtos(resultado.getObjetosDto(), jtaAgendamentos);
                } else {
                    limparTabela(jtaAgendamentos);
                    JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
                }
            } catch (Exception ex) {
                logger.error("Erro durante pesquisa de agendamentos disponíveis. \nDetalhes:" + ex);
            }
        } else {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_SEM_TODOS_PARAMETROS_PESQUISA);
            limparTabela(jtaAgendamentos);
        }
    }

    public void inicializarDescricaoTipoAtendimento() {
        controladorTipoAtendimento.inicializarDescricaoTipoAtendimento();
    }

    public void inicializarModuloAtividade() {
        controladorTipoAtendimento.inicializarModuloAtividade();
    }
}

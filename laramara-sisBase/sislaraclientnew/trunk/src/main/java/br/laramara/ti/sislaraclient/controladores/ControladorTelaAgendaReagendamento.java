package br.laramara.ti.sislaraclient.controladores;

import static br.laramara.ti.sislaraclient.controladores.ControladorTela.logger;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoPesquisaAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoListagemAgendamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JTable;

public class ControladorTelaAgendaReagendamento extends ControladorTelaEditar {

    private JTable jtaAgendamentos;
    
    public ControladorTelaAgendaReagendamento(JDialog telaPai, AgendamentoDTO agendamentoDto, JTable jtaAgendamentos) {
        super(telaPai, agendamentoDto);
        this.jtaAgendamentos = jtaAgendamentos;
        configurarColunasTabela();
        inicializarCombosDadosAuxiliares();
    }

    public void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaAgendamentos);
    }
        
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.reagendarAgendamento((AgendamentoDTO)objetoDto, (AgendamentoDTO)obterDtoSelecionado(jtaAgendamentos), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        return true;
    }

    @Override
    protected void executarAcaoAposInclusao() {
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_AGENDAMENTO;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
    }

    @Override
    public void obterDadosAuxiliares() {
        EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto = new EspecificacaoPesquisaAgendamentoDTO();
        especificacaoPesquisaAgendamentoDto.setDescricaoTipoAtendimentoDTO(((AgendamentoDTO) objetoDto).getDescricaoTipoAtendimentoDto());
        especificacaoPesquisaAgendamentoDto.setModuloDTO(((AgendamentoDTO) objetoDto).getModuloDto());
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
    }

    @Override
    public void carregarCampos() {
    }
}

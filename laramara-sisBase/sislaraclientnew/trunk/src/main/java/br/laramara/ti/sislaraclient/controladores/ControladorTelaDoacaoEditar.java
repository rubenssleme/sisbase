package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.MotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoListagemMotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.DemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemStatusDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.StatusDemandaDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class ControladorTelaDoacaoEditar extends ControladorTelaEditar{
    
    private ResultadoListagemStatusDemandaDTO resultadoListagemStatusDemandaDto;
    private ResultadoListagemMotivoCancelamentoDTO resultadoListagemMotivoCancelamentoDTO;
    
    private JComboBox jcbStatus;
    private JComboBox jcbMotivoCancelamento;
    private JTextArea jtaObs;
            
    public ControladorTelaDoacaoEditar(JDialog telaPai, DemandaDTO demandaDto, JComboBox jcbStatus, JComboBox jcbMotivoCancelamento, JTextArea jtaObs){
        super(telaPai, demandaDto);
        this.jcbStatus = jcbStatus;
        this.jcbMotivoCancelamento = jcbMotivoCancelamento;
        this.jtaObs = jtaObs;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoDTO) servicoSisLaraServer.mudarStatusDemanda(obterObjetoDTO(), (StatusDemandaDTO)obterDtoSelecionado(jcbStatus), (MotivoCancelamentoDTO)obterDtoSelecionado(jcbMotivoCancelamento), jtaObs.getText(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        DemandaDTO demandaDto = obterObjetoDTO();
        demandaDto.setStatusDemandaDto((StatusDemandaDTO)obterDtoSelecionado(jcbStatus));
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
        return Sessao.CHAVE_DEMANDA;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbStatus, resultadoListagemStatusDemandaDto.getObjetosDto());
        adicionarDtos(jcbMotivoCancelamento, resultadoListagemMotivoCancelamentoDTO.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemStatusDemandaDto = servicoSisLaraServer.obterListagemStatusDemandaLimitada();
            resultadoListagemMotivoCancelamentoDTO = servicoSisLaraServer.obterListagemMotivoCancelamentoDemanda();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    private DemandaDTO obterObjetoDTO(){
        return (DemandaDTO)objetoDto;
    }

    @Override
    public void carregarCampos() {
    }
}

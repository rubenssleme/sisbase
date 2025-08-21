
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.MotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoListagemMotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

public abstract class ControladorTelaAgendaEditarJustificativa extends ControladorTelaEditar{
    
    private ResultadoListagemMotivoCancelamentoDTO resultadoListagemMotivoCancelamentoDto;
    
    private JTextField jtfDataAgendamento; 
    private JTextField jtfHoraInicio;
    private JTextField jtfHoraTermino;
    private JTextField jtfProfissional;
    private JComboBox jcbMotivo;
    private JEditorPane jepJustificativa;
    
    public ControladorTelaAgendaEditarJustificativa(JDialog telaPai, AgendamentoDTO agendamentoDto, JTextField jtfDataAgendamento, JTextField jtfHoraInicio, JTextField jtfHoraTermino, JTextField jtfProfissional, JComboBox jcbMotivo, JEditorPane jepJustificativa){
        super(telaPai, agendamentoDto);
        this.jtfDataAgendamento = jtfDataAgendamento;
        this.jtfHoraInicio = jtfHoraInicio;
        this.jtfHoraTermino = jtfHoraTermino;
        this.jtfProfissional = jtfProfissional;
        this.jcbMotivo = jcbMotivo;
        this.jepJustificativa = jepJustificativa;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbMotivo, resultadoListagemMotivoCancelamentoDto.getObjetosDto());
    }

    @Override
    protected void prepararDtoParaEditar() {
        AgendamentoDTO agendamentoDto = obterObjetoDTO();
                
        agendamentoDto.setMotivoCancelamentoDTO((MotivoCancelamentoDTO)obterDtoSelecionado(jcbMotivo));
        agendamentoDto.setJustificativaCancelamento(jepJustificativa.getText());
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
        return Sessao.CHAVE_AGENDAMENTO;
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemMotivoCancelamentoDto = servicoSisLaraServer.obterListagemMotivoCancelamentoAgendamento();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
        
    private AgendamentoDTO obterObjetoDTO(){
        return (AgendamentoDTO)objetoDto;
    }

    @Override
    public void carregarCampos() {
        jtfDataAgendamento.setText(obterObjetoDTO().getData());
        jtfHoraInicio.setText(obterObjetoDTO().getHorarioDto().getHoraInicio());
        jtfHoraTermino.setText(obterObjetoDTO().getHorarioDto().getHoraTermino());
        jtfProfissional.setText(obterObjetoDTO().getProfissionalDto().toString());
        selecionarDto(jcbMotivo, obterObjetoDTO().getMotivoCancelamentoDTO());
        jepJustificativa.setText(obterObjetoDTO().getJustificativaCancelamento());
    }
}

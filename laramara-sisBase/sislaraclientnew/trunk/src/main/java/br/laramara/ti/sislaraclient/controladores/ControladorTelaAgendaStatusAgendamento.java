package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class ControladorTelaAgendaStatusAgendamento extends ControladorTelaEditar {
    
    private JTextField jtfDataAgendamento;
    private JTextField jtfHoraInicio;
    private JTextField jtfHoraTermino;
    private JComboBox jcbProfissional;
    private JComboBox jcbMotivo;
    private JEditorPane jepJustificativa;
    
    private AgendamentoDTO agendamentoDto;
    
     public ControladorTelaAgendaStatusAgendamento (JDialog telaPai, AgendamentoDTO agendamentoDto, JTextField jtfDataAgendamento, JTextField jtfHoraInicio, JTextField jtfHoraTermino, JComboBox jcbProfissional, JComboBox jcbMotivo, JEditorPane jepJustificativa){
        super(telaPai, agendamentoDto);
        this.jtfDataAgendamento = jtfDataAgendamento;
        this.jtfHoraInicio = jtfHoraInicio;
        this.jtfHoraTermino = jtfHoraTermino;
        this.jcbProfissional = jcbProfissional;
        this.jcbMotivo = jcbMotivo;
        this.jepJustificativa = jepJustificativa;
        this.agendamentoDto = agendamentoDto;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void prepararDtoParaEditar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void executarAcaoAposInclusao() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String obterChaveSessao() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void obterDadosAuxiliares() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void carregarCampos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

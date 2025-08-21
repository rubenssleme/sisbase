package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemFrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;

public class ControladorTelaAtendimentoIndividualProfissionalEditar extends ControladorTelaEditar{
    
    private ResultadoListagemProfissionalDTO resultadoListagemProfissionalDto;
    private ResultadoListagemFrequenciaDTO resultadoListagemFrequenciaDto;
    
    private JComboBox jcbProfissional;
    private JTextArea jatDescricao;
    private JComboBox jcbFrequencia;
    private JTextArea jatJustificativa;
    
    public ControladorTelaAtendimentoIndividualProfissionalEditar(JDialog telaPai, AtendimentoProfissionalDTO atendimentoProfissionalDto, JComboBox jcbProfissional, JTextArea jatDescricao, JComboBox jcbFrequencia, JTextArea jatJustificativa){
        super(telaPai, atendimentoProfissionalDto);
        this.jcbProfissional = jcbProfissional;
        this.jatDescricao = jatDescricao;
        this.jcbFrequencia = jcbFrequencia;
        this.jatJustificativa = jatJustificativa;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }
    
    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemProfissionalDto = servicoSisLaraServer.obterListagemProfissionalAtivos();
            resultadoListagemFrequenciaDto = servicoSisLaraServer.obterListagemFrequenciaDoProfissional();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbProfissional, resultadoListagemProfissionalDto.getObjetosDto());
        adicionarDtos(jcbFrequencia, resultadoListagemFrequenciaDto.getObjetosDto());
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoDTO) servicoSisLaraServer.validarAtendimentoAuxiliarBase(obterObjetoDTO());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setProfissionalDto((ProfissionalDTO)obterDtoSelecionado(jcbProfissional));
        obterObjetoDTO().getInformacaoAtendimentoDto().setDescricao(jatDescricao.getText());
        obterObjetoDTO().getInformacaoAtendimentoDto().setJustificativa(jatJustificativa.getText());
        obterObjetoDTO().getInformacaoAtendimentoDto().setFrequenciaDto((FrequenciaDTO)obterDtoSelecionado(jcbFrequencia));
    }

    private AtendimentoProfissionalDTO obterObjetoDTO(){
        return (AtendimentoProfissionalDTO)objetoDto;
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
        return Sessao.CHAVE_ATENDIMENTO_PROFISSIONAL;
    }

    @Override
    public void carregarCampos() {
        selecionarDto(jcbProfissional, obterObjetoDTO().getProfissionalDto());
        selecionarDto(jcbFrequencia, obterObjetoDTO().getInformacaoAtendimentoDto().getFrequenciaDto());
        jatDescricao.setText(obterObjetoDTO().getInformacaoAtendimentoDto().getDescricao());
        jatJustificativa.setText(obterObjetoDTO().getInformacaoAtendimentoDto().getJustificativa());
    }
}

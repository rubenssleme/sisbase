package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProgramacaoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemLocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoProgramacaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;

public class ControladorTelaGruposEditarProgramacao extends ControladorTelaEditar{
    
    private ResultadoListagemLocalAtendimentoDTO resultadoListagemLocalAtendimentoDTO;
    
    private JFormattedTextField jftAula;
    private JFormattedTextField jftData;
    private JEditorPane jepConteudo;
    private JEditorPane jepRecadoFamilia;
    private JEditorPane jepEstrategia;
    private JComboBox jcbLocalAtendimento;

    public ControladorTelaGruposEditarProgramacao(JDialog telaPai, ProgramacaoDTO programacaoDto, JFormattedTextField jftAula, JFormattedTextField jftData, JEditorPane jepConteudo, JEditorPane jepRecadoFamilia, JEditorPane jepEstrategia, JComboBox jcbLocalAtendimento){
        super(telaPai, programacaoDto);
        this.jftAula = jftAula;
        this.jftData = jftData;
        this.jepConteudo = jepConteudo;
        this.jepRecadoFamilia = jepRecadoFamilia;
        this.jepEstrategia = jepEstrategia;
        this.jcbLocalAtendimento = jcbLocalAtendimento;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
}
    
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoValidacaoProgramacaoDTO) servicoSisLaraServer.validarProgramacao(obterObjetoDTO());
    }

    @Override
    protected void prepararDtoParaEditar() {
        ProgramacaoDTO programacaoDto = obterObjetoDTO();
        programacaoDto.setAula(jftAula.getText());
        programacaoDto.setData(jftData.getText());
        programacaoDto.setTemaConteudo(jepConteudo.getText());
        programacaoDto.setRecadoFamilia(jepRecadoFamilia.getText());
        programacaoDto.setEstrategias(jepEstrategia.getText());
        programacaoDto.setLocalAtendimentoDTO((LocalAtendimentoDTO)obterDtoSelecionado(jcbLocalAtendimento));
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
        return Sessao.CHAVE_PROGRAMACAO;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbLocalAtendimento, resultadoListagemLocalAtendimentoDTO.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemLocalAtendimentoDTO = servicoSisLaraServer.obterListagemLocalAtendimento();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    public void carregarCampos() {
        jftAula.setText(obterObjetoDTO().getAula());
        jftData.setText(obterObjetoDTO().getData());
        jepConteudo.setText(obterObjetoDTO().getTemaConteudo());
        jepRecadoFamilia.setText(obterObjetoDTO().getRecadoFamilia());
        jepEstrategia.setText(obterObjetoDTO().getEstrategias());
        selecionarDto(jcbLocalAtendimento, obterObjetoDTO().getLocalAtendimentoDTO());
    }

    private ProgramacaoDTO obterObjetoDTO() {
        return (ProgramacaoDTO) objetoDto;
    }
}

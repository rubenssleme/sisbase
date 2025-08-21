package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaEditarInstituicoes;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TelaUtils;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.AreaFormacaoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.EscolaridadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.escola.PeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemAreaFormacaoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemEscolaridadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemSituacaoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoValidacaoInformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.escola.SerieDTO;
import br.laramara.ti.sislaracommons.dtos.escola.SituacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemInstituicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public final class ControladorTelaEditarEducacional extends ControladorTelaEditar {

    private ResultadoListagemInstituicaoDTO resultadoListagemInstituicaoDTO;
    private ResultadoListagemEscolaridadeDTO resultadoListagemEscolaridadeDTO;
    private ResultadoListagemSituacaoDTO resultadoListagemSituacaoDTO;
    private ResultadoListagemPeriodoDTO resultadoListagemPeriodoDTO;
    private ResultadoListagemAreaFormacaoDTO resultadiListagemAreaFormacaoDTO;
    
    private JComboBox jcbInstituicaoEnsino;
    private JComboBox jcbEscolaridade;
    private JComboBox jcbSerie;
    private JComboBox jcbSituacao;
    private JComboBox jcbPeriodo;
    private JFormattedTextField jftDataReferencia;
    private JTextField jtfNomeProfessor;
    private JComboBox jcbAreaFormacao;
    
    public ControladorTelaEditarEducacional(JDialog telaPai, InformacaoEducacionalDTO informacaoEducacionalDto, JComboBox jcbInstituicaoEnsino, JComboBox jcbEscolaridade, JComboBox jcbSerie, JComboBox jcbSituacao, JComboBox jcbPeriodo, JFormattedTextField jftDataReferencia, JComboBox jcbAreaFormacao, JTextField jtfNomeProfessor) {
        super(telaPai, informacaoEducacionalDto);
        this.jcbInstituicaoEnsino = jcbInstituicaoEnsino;
        this.jcbEscolaridade = jcbEscolaridade;
        this.jcbSerie = jcbSerie;
        this.jcbSituacao = jcbSituacao;
        this.jcbPeriodo = jcbPeriodo;
        this.jftDataReferencia = jftDataReferencia;
        this.jtfNomeProfessor = jtfNomeProfessor;
        this.jcbAreaFormacao = jcbAreaFormacao;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
        TelaUtils.habilitarSomenteUpperCaseNosCamposTexto(this);
    }
        
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
         return (ResultadoValidacaoInformacaoEducacionalDTO) servicoSisLaraServer.validarInformacaoEducacional(obterObjetoDTO());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setInstituicaoDto((InstituicaoDTO)obterDtoSelecionado(jcbInstituicaoEnsino));
        obterObjetoDTO().setEscolaridadeDto((EscolaridadeDTO) obterDtoSelecionado(jcbEscolaridade));
        obterObjetoDTO().setSerieDto((SerieDTO) obterDtoSelecionado(jcbSerie));
        obterObjetoDTO().setSituacaoEducacionalDto((SituacaoEducacionalDTO) obterDtoSelecionado(jcbSituacao));
        obterObjetoDTO().setPeriodoDto((PeriodoDTO) obterDtoSelecionado(jcbPeriodo));
        obterObjetoDTO().setDataReferencia(jftDataReferencia.getText());
        obterObjetoDTO().setAreaFormacaoDto((AreaFormacaoDTO)obterDtoSelecionado(jcbAreaFormacao));
        obterObjetoDTO().setNomeProfessor(jtfNomeProfessor.getText());
    }

    public void inicializarCombosComDependencia() {
        if (obterObjetoDTO().getEscolaridadeDto() != null) {
            atualizarCamposInstituicaoEscolaridadeSerie();
        }
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void executarAcaoAposInclusao() {
       return;
    }

    @Override
    public String obterChaveSessao() {
         return Sessao.CHAVE_INFORMACAO_EDUCACIONAL;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbInstituicaoEnsino, resultadoListagemInstituicaoDTO.getObjetosDto());
        inicializarComboEscolaridade();
        adicionarDtos(jcbSituacao, resultadoListagemSituacaoDTO.getObjetosDto());
        adicionarDtos(jcbPeriodo, resultadoListagemPeriodoDTO.getObjetosDto());
        adicionarDtos(jcbAreaFormacao, resultadiListagemAreaFormacaoDTO.getObjetosDto());
    }
    
    private void inicializarComboEscolaridade(){
        adicionarDtos(jcbEscolaridade, resultadoListagemEscolaridadeDTO.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemInstituicaoDTO = servicoSisLaraServer.obterListagemInstituicao();
            resultadoListagemEscolaridadeDTO = servicoSisLaraServer.obterListagemEscolaridade();
            resultadoListagemSituacaoDTO = servicoSisLaraServer.obterListagemSituacao();
            resultadoListagemPeriodoDTO = servicoSisLaraServer.obterListagemPeriodo();
            resultadiListagemAreaFormacaoDTO = servicoSisLaraServer.obterListagemAreaFormacao();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    public void carregarCampos() {
        selecionarDto(jcbSituacao, obterObjetoDTO().getSituacaoEducacionalDto());
        selecionarDto(jcbPeriodo, obterObjetoDTO().getPeriodoDto());
        jftDataReferencia.setText(obterObjetoDTO().getDataReferencia());
        selecionarDto(jcbAreaFormacao, obterObjetoDTO().getAreaFormacaoDto());
        jtfNomeProfessor.setText(obterObjetoDTO().getNomeProfessor());
    }

    private InformacaoEducacionalDTO obterObjetoDTO() {
        return (InformacaoEducacionalDTO) objetoDto;
    }

    private void atualizarCamposInstituicaoEscolaridadeSerie() {
        jcbInstituicaoEnsino.setSelectedItem(obterObjetoDTO().getInstituicaoDto());
        atualizarCamposEscolaridadeESerie();
    }
    
    private void atualizarCamposEscolaridadeESerie() {
        jcbEscolaridade.setSelectedItem(obterObjetoDTO().getEscolaridadeDto());
        jcbSerie.setSelectedItem(obterObjetoDTO().getSerieDto());
    }

    public void adicionarInstituicao() {
        String chaveInstituicao = Sessao.CHAVE_INSTITUICAO;
        new TelaEditarInstituicoes((JDialog)telaPai, new InstituicaoDTO());
        if (Sessao.obterInstancia().possuiDto(chaveInstituicao)){
            InstituicaoDTO instituicaoDto = (InstituicaoDTO) Sessao.obterInstancia().obterDto(chaveInstituicao);
            adicionarESelecionadDto(jcbInstituicaoEnsino, instituicaoDto);
        }
    }
    
    public void inicializarSerie() {
        List<? extends ModeloDTO> objetosDto = null;
        EscolaridadeDTO escolaridadeSelecionada = ((EscolaridadeDTO) obterDtoSelecionado(jcbEscolaridade));
        if (escolaridadeSelecionada != null){
            objetosDto = ((EscolaridadeDTO) obterDtoSelecionado(jcbEscolaridade)).getSeries();
        }
        carregarCombosDependentes(jcbEscolaridade, jcbSerie, objetosDto);
    }
    
    public void inicializarEscolaridade() {
        List<? extends ModeloDTO> objetosDto = null;
        InstituicaoDTO instituicaoSelecionada = ((InstituicaoDTO) obterDtoSelecionado(jcbInstituicaoEnsino));
        if (instituicaoSelecionada != null) {
            objetosDto = ((InstituicaoDTO) obterDtoSelecionado(jcbInstituicaoEnsino)).getEscolaridadesDto();
            carregarCombosDependentes(jcbInstituicaoEnsino, jcbEscolaridade, objetosDto);
        }else{
            inicializarComboEscolaridade();
            atualizarCamposEscolaridadeESerie();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.laramara.ti.sislaraclient.controladores;

import static br.laramara.ti.sislaraclient.controladores.ControladorTela.logger;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ComprometimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EpocaIncidenciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoComprometimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemComprometimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemEpocaIncidenciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoComprometimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

/**
 *
 * @author paulo.bandeira
 */
public class ControladorTelaEditarComprometimento extends ControladorTelaEditar{

    private ResultadoListagemComprometimentoDTO resultadoListagemComprometimentoDTO;
    private ResultadoListagemEpocaIncidenciaDTO resultadoListagemEpocaIncidenciaDTO;
    
    private JComboBox jcbComprometimento;
    private JFormattedTextField jftDataInicio;
    private JFormattedTextField jftDataTermino;
    private JComboBox jcbEpocaIncidencia;
    
    public ControladorTelaEditarComprometimento(JDialog telaPai, PeriodoComprometimentoDTO periodoComprometimentoDto, JComboBox jcbComprometimento, JFormattedTextField jftDataInicio, JFormattedTextField jftDataTermino, JComboBox jcbEpocaIncidencia) {
        super(telaPai, periodoComprometimentoDto);
        this.jcbComprometimento = jcbComprometimento;
        this.jftDataInicio = jftDataInicio;
        this.jftDataTermino = jftDataTermino;
        this.jcbEpocaIncidencia = jcbEpocaIncidencia;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
         return (ResultadoValidacaoPeriodoComprometimentoDTO) servicoSisLaraServer.validarPeriodoComprometimento(obterObjetoDTO());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setComprometimentoDto((ComprometimentoDTO)obterDtoSelecionado(jcbComprometimento));
        obterObjetoDTO().setEpocaIncidenciaDto((EpocaIncidenciaDTO) obterDtoSelecionado(jcbEpocaIncidencia));
        obterObjetoDTO().setDataInicial(jftDataInicio.getText());
        obterObjetoDTO().setDataFinal(jftDataTermino.getText());
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        return obterObjetoDTO() != null;
    }
    
    private PeriodoComprometimentoDTO obterObjetoDTO() {
        return (PeriodoComprometimentoDTO) objetoDto;
    }

    @Override
    protected void executarAcaoAposInclusao() {
        return;
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_PERIODO_COMPROMETIMENTO;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbComprometimento, resultadoListagemComprometimentoDTO.getObjetosDto());
        adicionarDtos(jcbEpocaIncidencia, resultadoListagemEpocaIncidenciaDTO.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemComprometimentoDTO = servicoSisLaraServer.obterListagemComprometimento();
            resultadoListagemEpocaIncidenciaDTO = servicoSisLaraServer.obterListagemEpocaIncidencia();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    public void carregarCampos() {
        if (verificarSeDtoEstaPreenchido()) {
            selecionarDto(jcbComprometimento, obterObjetoDTO().getComprometimentoDto());
            selecionarDto(jcbEpocaIncidencia, obterObjetoDTO().getEpocaIncidenciaDto());
            jftDataInicio.setText(obterObjetoDTO().getDataInicial());
            jftDataTermino.setText(obterObjetoDTO().getDataFinal());
        }
    }
    
}

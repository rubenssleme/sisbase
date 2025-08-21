package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemSimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.CargoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.InformacaoTrabalhoCompletaDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.LocalTrabalhoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.ResultadoListagemCargoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.ResultadoListagemLocalTrabalhoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.ResultadoValidacaoInformacaoTrabalhoCompletaDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

public class ControladorTelaEditarInformacaoTrabalhoCompleta extends ControladorTelaEditar {
    
    private ResultadoListagemCargoDTO resultadoListagemCargoDTO;
    private ResultadoListagemLocalTrabalhoDTO resultadoListagemLocalTrabalhoDTO;
    private ResultadoListagemSimNaoDTO resultadoListagemSimNaoDTO;
    
    private JComboBox jcbCargo;
    private JComboBox jcbLocalTrabalho;
    private JComboBox jcbEstagiario;
    private JFormattedTextField jftDataInicio;
    private JFormattedTextField jftDataTermino;
    private JComboBox jcbVoluntario;
    private JComboBox jcbEncaminhado;
    private JTextArea jtaObs;
    
    public ControladorTelaEditarInformacaoTrabalhoCompleta(JDialog telaPai, InformacaoTrabalhoCompletaDTO informacaoTrabalhoCompletaDto, JComboBox jcbCargo, JComboBox jcbLocalTrabalho, JComboBox jcbEstagiario, JFormattedTextField jftDataInicio, JFormattedTextField jftDataTermino, JComboBox jcbVoluntario, JComboBox jcbEncaminhado, JTextArea jtaObs) {
        super(telaPai, informacaoTrabalhoCompletaDto);
        this.jcbCargo = jcbCargo;
        this.jcbLocalTrabalho = jcbLocalTrabalho;
        this.jcbEstagiario = jcbEstagiario;
        this.jftDataInicio = jftDataInicio;
        this.jftDataTermino = jftDataTermino;
        this.jcbVoluntario = jcbVoluntario;
        this.jcbEncaminhado = jcbEncaminhado;
        this.jtaObs = jtaObs;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws RemoteException {
         return (ResultadoValidacaoInformacaoTrabalhoCompletaDTO) servicoSisLaraServer.validarInformacaoTrabalhoCompleta(obterObjetoDTO());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setCargoDto((CargoDTO)obterDtoSelecionado(jcbCargo));
        obterObjetoDTO().setLocalTrabalhoDto((LocalTrabalhoDTO) obterDtoSelecionado(jcbLocalTrabalho));
        obterObjetoDTO().setEstagiarioDto((SimNaoDTO) obterDtoSelecionado(jcbEstagiario));
        obterObjetoDTO().setVoluntarioDto((SimNaoDTO) obterDtoSelecionado(jcbVoluntario));
        obterObjetoDTO().setEncaminhadoPorLaramaraDto((SimNaoDTO) obterDtoSelecionado(jcbEncaminhado));
        obterObjetoDTO().setDataInicialVigencia(jftDataInicio.getText());
        obterObjetoDTO().setDataFinalVigencia(jftDataTermino.getText());
        obterObjetoDTO().setObs(jtaObs.getText());
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
        return Sessao.CHAVE_INFORMACAO_TRABALHO_COMPLETA;
    }

    private InformacaoTrabalhoCompletaDTO obterObjetoDTO() {
        return (InformacaoTrabalhoCompletaDTO) objetoDto;
    }
    
    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbCargo, resultadoListagemCargoDTO.getObjetosDto());
        adicionarDtos(jcbLocalTrabalho, resultadoListagemLocalTrabalhoDTO.getObjetosDto());
        adicionarDtos(jcbEstagiario, resultadoListagemSimNaoDTO.getObjetosDto());
        adicionarDtos(jcbVoluntario, resultadoListagemSimNaoDTO.getObjetosDto());
        adicionarDtos(jcbEncaminhado, resultadoListagemSimNaoDTO.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemCargoDTO = servicoSisLaraServer.obterListagemCargo();
            resultadoListagemLocalTrabalhoDTO = servicoSisLaraServer.obterListagemLocalTrabalho();
            resultadoListagemSimNaoDTO = servicoSisLaraServer.obterListagemSimNao();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    public void carregarCampos() {
        selecionarDto(jcbCargo, obterObjetoDTO().getCargoDto());
        selecionarDto(jcbLocalTrabalho, obterObjetoDTO().getLocalTrabalhoDto());
        selecionarDto(jcbEstagiario, obterObjetoDTO().getEstagiarioDto());
        selecionarDto(jcbVoluntario, obterObjetoDTO().getVoluntarioDto());
        selecionarDto(jcbEncaminhado, obterObjetoDTO().getEncaminhadoPorLaramaraDto());
        jftDataInicio.setText(obterObjetoDTO().getDataInicialVigencia());
        jftDataTermino.setText(obterObjetoDTO().getDataFinalVigencia());
        jtaObs.setText(obterObjetoDTO().getObs());
    }
}

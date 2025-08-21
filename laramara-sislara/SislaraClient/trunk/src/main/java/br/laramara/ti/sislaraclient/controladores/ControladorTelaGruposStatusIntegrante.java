package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloRelacaoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemStatusRelacaoUsuarioModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.StatusRelacaoComModuloDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ControladorTelaGruposStatusIntegrante extends ControladorTelaEditar {

    private ResultadoListagemStatusRelacaoUsuarioModuloDTO resultadoListagemStatusRelacaoUsuarioModuloDTO;

    private JTextField jtfUsuarioComunidade;
    private JComboBox jcbStatus;
    private JFormattedTextField jftDataOcorrencia;
    private JCheckBox jchAprovado;
    private JCheckBox jchIgnorarReunicaoIntegracao;
    private JTextArea jepObs;
    private ModuloPeriodoDTO moduloPeriodoDTO;
    
    public ControladorTelaGruposStatusIntegrante(JDialog telaPai, ModuloRelacaoBaseDTO moduloRelacaoBaseDto, ModuloPeriodoDTO moduloPeriodoDto, JTextField jtfUsuarioComunidade, JComboBox jcbStatus, JFormattedTextField jftDataOcorrencia, JCheckBox jchAprovado, JCheckBox jchIgnorarReunicaoIntegracao, JTextArea jepObs){
        super(telaPai, moduloRelacaoBaseDto);
        this.jtfUsuarioComunidade = jtfUsuarioComunidade;
        this.jcbStatus = jcbStatus;
        this.jftDataOcorrencia = jftDataOcorrencia;
        this.jchAprovado = jchAprovado;
        this.jchIgnorarReunicaoIntegracao = jchIgnorarReunicaoIntegracao;
        this.jepObs = jepObs;
        this.moduloPeriodoDTO = moduloPeriodoDto;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.validarModuloRelacaoBase(obterObjetoDTO());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setStatusDto((StatusRelacaoComModuloDTO)obterDtoSelecionadoObrigatorio(jcbStatus));
        obterObjetoDTO().setDataOcorrencia(jftDataOcorrencia.getText());
        obterObjetoDTO().setAprovado(jchAprovado.isSelected());
        obterObjetoDTO().setIgnorarRegraDeReuniaoDeIntegracao(jchIgnorarReunicaoIntegracao.isSelected());
        obterObjetoDTO().setObs(jepObs.getText());
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
        return Sessao.CHAVE_MODULO_RELACAO_BASE;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbStatus, resultadoListagemStatusRelacaoUsuarioModuloDTO.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
         try {
            resultadoListagemStatusRelacaoUsuarioModuloDTO = servicoSisLaraServer.obterListagemStatusRelacaoUsuarioModulo(moduloPeriodoDTO);
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    @Override
    public void carregarCampos() {
        jtfUsuarioComunidade.setText(obterObjetoDTO().toString());
        selecionarDto(jcbStatus, obterObjetoDTO().getStatusDto());
        jftDataOcorrencia.setText(obterObjetoDTO().getDataOcorrencia());
        jchAprovado.setSelected(obterObjetoDTO().isAprovado());
        jchIgnorarReunicaoIntegracao.setSelected(obterObjetoDTO().isIgnorarRegraDeReuniaoDeIntegracao());
        jepObs.setText(obterObjetoDTO().getObs());
    }
    
    private ModuloRelacaoBaseDTO obterObjetoDTO(){
        return (ModuloRelacaoBaseDTO)objetoDto;
    }
}

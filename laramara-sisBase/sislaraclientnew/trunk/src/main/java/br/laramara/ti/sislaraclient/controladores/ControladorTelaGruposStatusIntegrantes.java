package br.laramara.ti.sislaraclient.controladores;

import static br.laramara.ti.sislaraclient.controladores.ControladorTela.logger;
import br.laramara.ti.sislaraclient.utilitarios.ClonadorUtils;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloRelacaoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemStatusRelacaoUsuarioModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoModuloRelacaoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.StatusRelacaoComModuloDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;

public class ControladorTelaGruposStatusIntegrantes extends ControladorTela{
    
    private ResultadoListagemStatusRelacaoUsuarioModuloDTO resultadoListagemStatusRelacaoUsuarioModuloDTO;
    private JComboBox jcbStatus;
    private JFormattedTextField jftDataOcorrencia;
    private JCheckBox jchAprovado;
    private JEditorPane jepObs;
    private List<ModuloRelacaoBaseDTO> modulosRelacaoDto;
    private ModuloPeriodoDTO moduloPeriodoDTO;
    
    public ControladorTelaGruposStatusIntegrantes(JDialog telaPai, List<? extends ModuloRelacaoBaseDTO> modulosRelacaoDto, ModuloPeriodoDTO moduloPeriodoDto, JComboBox jcbStatus, JFormattedTextField jftDataOcorrencia, JCheckBox jchAprovado, JEditorPane jepObs){
        super(telaPai);
        this.modulosRelacaoDto = (List<ModuloRelacaoBaseDTO>)ClonadorUtils.copiar(modulosRelacaoDto);
        this.jcbStatus = jcbStatus;
        this.jftDataOcorrencia = jftDataOcorrencia;
        this.jchAprovado = jchAprovado;
        this.jepObs = jepObs;
        this.moduloPeriodoDTO = moduloPeriodoDto;
        inicializarCombosDadosAuxiliares();
    }
    
    public void salvar() {
        ResultadoValidacaoModuloRelacaoBaseDTO resultado = new ResultadoValidacaoModuloRelacaoBaseDTO();
        try {
            for (ModuloRelacaoBaseDTO moduloUsuarioDTO : modulosRelacaoDto) {
                moduloUsuarioDTO.setStatusDto((StatusRelacaoComModuloDTO) obterDtoSelecionado(jcbStatus));
                moduloUsuarioDTO.setDataOcorrencia(jftDataOcorrencia.getText());
                moduloUsuarioDTO.setAprovado(jchAprovado.isSelected());
                moduloUsuarioDTO.setObs(jepObs.getText());
                resultado = servicoSisLaraServer.validarModuloRelacaoBase(moduloUsuarioDTO);
                if (!resultado.sucesso()) {
                    resultado.adicionarErro(resultado.obterMensagens());
                    break;
                }
            }
            if (resultado.sucesso()) {
                servicoSisLaraServer.gravarTela(obterTela());
                Sessao.obterInstancia().armazenarDTOs(Sessao.CHAVE_MODULO_RELACAO_BASE, modulosRelacaoDto);
                JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultado.obterMensagens());
                fecharTela();
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
            }
        } catch (Exception e) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_VALIDACAO_RELACAO_BASE);
            logger.error(JOptionPanePersonalizado.ERRO_VALIDACAO_RELACAO_BASE + e.getMessage());
        }
    }
    
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbStatus, resultadoListagemStatusRelacaoUsuarioModuloDTO.getObjetosDto());
    }

    public void obterDadosAuxiliares() {
         try {
            resultadoListagemStatusRelacaoUsuarioModuloDTO = servicoSisLaraServer.obterListagemStatusRelacaoUsuarioModulo(moduloPeriodoDTO);
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
}

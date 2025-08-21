package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaoclient.utilitarios.ComboBoxUtils;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemStatusDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public abstract class ControladorTelaEditarStatus extends ControladorTelaEditar {
  
    private ResultadoListagemStatusDTO resultadoListagemStatusDto;
    
    private JComboBox jcbStatus;
    
    public ControladorTelaEditarStatus(JDialog telaPai, MovimentacaoDTO movimentacaoDto, JComboBox jcbStatus){
        super(telaPai, movimentacaoDto);
        this.jcbStatus = jcbStatus;
        configurarCombox();
        inicializarCombosDadosAuxiliares();
    }
    
    private void configurarCombox() {
        ComboBoxUtils.habilitar(jcbStatus);
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_MOVIMENTACAO;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbStatus, resultadoListagemStatusDto.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemStatusDto = servicoSisMovimentacaoServer.obterListagemStatus();
        } catch (Exception ex) {
            logger.error("Erro durante obtenção de dados auxiliares. \nDetalhes: " + ex);
        }
    }

}

package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;


public abstract class ControladorTelaListaEsperaMudancaStatus extends ControladorTelaEditar{

    public ControladorTelaListaEsperaMudancaStatus(JDialog telaPai, EsperaDTO esperaDto){
        super(telaPai, esperaDto);
    }
    
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return solicitarEdicao();
    }
        
    protected EsperaDTO obterObjetoDTO(){
        return (EsperaDTO)objetoDto;
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
        return Sessao.CHAVE_ESPERA;
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
    }
    
    protected abstract ResultadoDTO solicitarEdicao() throws Exception;
    
}

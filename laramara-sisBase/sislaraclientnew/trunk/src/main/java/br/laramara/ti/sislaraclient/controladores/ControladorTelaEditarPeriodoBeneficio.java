package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.BeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusBeneficioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

public class ControladorTelaEditarPeriodoBeneficio extends ControladorTelaEditarComboEDataInicioFinal {
    
    public ControladorTelaEditarPeriodoBeneficio(JDialog telaPai, PeriodoBeneficioDTO periodoBeneficioDto, JComboBox jcbBeneficios, JFormattedTextField jftDataInicial, JFormattedTextField jftDataFinal, JComboBox jcbStatus){
        super(telaPai, periodoBeneficioDto, jcbBeneficios, jftDataInicial, jftDataFinal, jcbStatus);
    }

    @Override
    protected ResultadoListagemDTO obterListagemAuxiliares() throws RemoteException {
        return servicoSisLaraServer.obterListagemBeneficio();
    }
    
    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_PERIODO_BENEFICIO;
    }
    
    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setBeneficioDto((BeneficioDTO) obterDtoSelecionado (jcbDominio));
        obterObjetoDTO().setDataInicial(jftDataInicial.getText());
        obterObjetoDTO().setDataFinal(jftDataFinal.getText());
        obterObjetoDTO().setStatusDto((StatusBeneficioDTO)obterDtoSelecionado(jcbStatus));
    }

    protected PeriodoBeneficioDTO obterObjetoDTO() {
        return (PeriodoBeneficioDTO) objetoDto;
    }
    
    @Override
    public void carregarCampos() {
        selecionarDto(jcbDominio, obterObjetoDTO().getBeneficioDto());
        jftDataInicial.setText(obterObjetoDTO().getDataInicial());
        jftDataFinal.setText(obterObjetoDTO().getDataFinal());
        selecionarDto(jcbStatus, obterObjetoDTO().getStatusDto());
    }
    
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoValidacaoPeriodoBeneficioDTO) servicoSisLaraServer.validarPeriodoBeneficio(obterObjetoDTO());
    }
}
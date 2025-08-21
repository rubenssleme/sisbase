package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.CidDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.DeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EpocaIncidenciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EtiologiaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoConsultaCidDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoDeficienciaDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorTelaEditarPeriodoDeficiencia extends ControladorTelaEditarComboEDataInicioFinal {

    private CidDTO cidDtoSelecionado;
    private CidDTO cidEtiologiaSelecionado;

    public ControladorTelaEditarPeriodoDeficiencia(JDialog telaPai, PeriodoDeficienciaDTO periodoDeficienciaDto, JComboBox jcbDeficiencia, JFormattedTextField jftDataInicial, JFormattedTextField jftDataFinal, JTextField jtfCidEtiologia, JLabel jlaCidEtiologia, JList jliEtiologiasAdicionadas, JTextField jtfCid, JLabel jlaCid, JComboBox jcbEpocaIncidencia) {
        super(telaPai, periodoDeficienciaDto, jcbDeficiencia, jftDataInicial, jftDataFinal, jtfCidEtiologia, jlaCidEtiologia, jliEtiologiasAdicionadas, jtfCid, jlaCid, jcbEpocaIncidencia);
    }

    @Override
    protected ResultadoListagemDTO obterListagemAuxiliares() throws RemoteException {
        return servicoSisLaraServer.obterListagemDeficiencia();
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_PERIODO_DEFICIENCIA;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        super.inicializarCombosDadosAuxiliares();
        adicionarDtos(jcbEpocaIncidencia, resultadoListagemEpocaIncidenciaDTO.getObjetosDto());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setDeficienciaDto((DeficienciaDTO) obterDtoSelecionado(jcbDominio));
        obterObjetoDTO().setDataInicial(jftDataInicial.getText());
        obterObjetoDTO().setDataFinal(jftDataFinal.getText());
        obterObjetoDTO().setEtiologiasDto((List<EtiologiaDTO>)obterDtos(jliEtiologiasAdicionadas));
        obterObjetoDTO().setCidDto(cidDtoSelecionado);
        obterObjetoDTO().setEpocaIncidenciaDto((EpocaIncidenciaDTO) obterDtoSelecionado(jcbEpocaIncidencia));
    }

    protected PeriodoDeficienciaDTO obterObjetoDTO() {
        return (PeriodoDeficienciaDTO) objetoDto;
    }

    @Override
    public void carregarCampos() {
        selecionarDto(jcbDominio, obterObjetoDTO().getDeficienciaDto());
        selecionarDto(jcbEpocaIncidencia, obterObjetoDTO().getEpocaIncidenciaDto());
        jftDataInicial.setText(obterObjetoDTO().getDataInicial());
        jftDataFinal.setText(obterObjetoDTO().getDataFinal());
        adicionarDtos(obterObjetoDTO().getEtiologiasDto(), jliEtiologiasAdicionadas);
        if (obterObjetoDTO().getCidDto() != null) {
            cidDtoSelecionado = obterObjetoDTO().getCidDto();
            jtfCid.setText(obterObjetoDTO().getCidDto().getId());
            jlaCid.setText(obterObjetoDTO().getCidDto().toString());
        }
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoValidacaoPeriodoDeficienciaDTO) servicoSisLaraServer.validarPeriodoDeficiencia(obterObjetoDTO());
    }

    private CidDTO consultarCidEAtualizarCampos(JTextField jtfCid, JLabel jlaCid) {
        CidDTO cidDtoSelecionado = null;
        try {
            ResultadoConsultaCidDTO resultadoConsultaCidDTO = servicoSisLaraServer.consultarCid(jtfCid.getText());
            if (resultadoConsultaCidDTO.sucesso()) {
                jlaCid.setText(resultadoConsultaCidDTO.obterObjetoDtoEditado().toString());
                cidDtoSelecionado = (CidDTO) resultadoConsultaCidDTO.obterObjetoDtoEditado();
            } else {
                jlaCid.setText(resultadoConsultaCidDTO.obterMensagens());
            }
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA);
            logger.error(JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA + ex.getMessage());
        }
        return cidDtoSelecionado;
    }

    public void consultarCid() {
        cidDtoSelecionado = consultarCidEAtualizarCampos(jtfCid, jlaCid);
    }

    public void consultarEtiologia() {
        cidEtiologiaSelecionado = consultarCidEAtualizarCampos(jtfCidEtiologia, jlaCidEtiologia);
    }

    public void adicionarEtiologiaSelecinada() {
        if (cidEtiologiaSelecionado != null) {
            EtiologiaDTO etiologiaDto = new EtiologiaDTO();
            etiologiaDto.setCidDto(cidEtiologiaSelecionado);
            adicionarDtoSemRepetir(etiologiaDto, jliEtiologiasAdicionadas);
            jtfCidEtiologia.setText(null);
            jlaCidEtiologia.setText(null);
            cidEtiologiaSelecionado = null;
        } else {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_CID_INVALIDO);
        }
    }

    public void removerEtiologiaSelecinada() {
        if ((estaComItemValidoSelecionado(jliEtiologiasAdicionadas) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliEtiologiasAdicionadas);
        }
    }
}

package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.CertidaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemTipoCertidaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUfDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoCertidaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.TipoCertidaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class ControladorTelaEditarCertidao extends ControladorTelaEditar {

    private ResultadoListagemUfDTO resultadoListagemUfDTO;
    private ResultadoListagemTipoCertidaoDTO resultadoListagemTipoCertidaoDto;
     
    private JComboBox jcbTipoCertidao;
    private JTextField jtfNumeroCertidao;
    private JTextField jtfLivro;
    private JTextField jtfFolha;
    private JComboBox jcbUf;
    private JComboBox jcbMunicipio;
    private JTextField jtfDistrito;

    public ControladorTelaEditarCertidao(JDialog telaPai, CertidaoDTO certidaoDto, JComboBox jcbTipoCertidao, JTextField jtfNumeroCertidao, JTextField jtfLivro, JTextField jtfFolha, JComboBox jcbUf, JComboBox jcbMunicipio, JTextField jtfDistrito) {
        super(telaPai, certidaoDto);
        this.jcbTipoCertidao = jcbTipoCertidao;
        this.jtfNumeroCertidao = jtfNumeroCertidao;
        this.jtfLivro = jtfLivro;
        this.jtfFolha = jtfFolha;
        this.jcbUf = jcbUf;
        this.jcbMunicipio = jcbMunicipio;
        this.jtfDistrito = jtfDistrito;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoValidacaoCertidaoDTO) servicoSisLaraServer.validarCertidao(obterObjetoDTO());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setTipoCertidaoDto((TipoCertidaoDTO) obterDtoSelecionado (jcbTipoCertidao));
        obterObjetoDTO().setNumero(jtfNumeroCertidao.getText());
        obterObjetoDTO().setLivro(jtfLivro.getText());   
        obterObjetoDTO().setFolha(jtfFolha.getText());
        obterObjetoDTO().setUfDto((UfDTO)obterDtoSelecionado(jcbUf));
        obterObjetoDTO().setMunicipioDto((MunicipioDTO)obterDtoSelecionado(jcbMunicipio));
        obterObjetoDTO().setDistrito(jtfDistrito.getText());
    }

    protected CertidaoDTO obterObjetoDTO() {
        return (CertidaoDTO) objetoDto;
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
         return Sessao.CHAVE_CERTIDAO;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbTipoCertidao, resultadoListagemTipoCertidaoDto.getObjetosDto());
        adicionarDtos(jcbUf, resultadoListagemUfDTO.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemTipoCertidaoDto = servicoSisLaraServer.obterListagemTipoCertidao();
            resultadoListagemUfDTO = servicoSisLaraServer.obterListagemUf();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    public void carregarCampos() {
        selecionarDto(jcbTipoCertidao, (TipoCertidaoDTO)obterObjetoDTO().getTipoCertidaoDto());
        jtfNumeroCertidao.setText(obterObjetoDTO().getNumero());
        jtfLivro.setText(obterObjetoDTO().getLivro());
        jtfFolha.setText(obterObjetoDTO().getFolha());
        jtfDistrito.setText(obterObjetoDTO().getDistrito());
        carregarUFeMunicipio(jcbUf, jcbMunicipio, obterObjetoDTO().getUfDto(), obterObjetoDTO().getMunicipioDto());
    }
}

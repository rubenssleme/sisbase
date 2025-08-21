package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaUtilizarUsuario;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TelaUtils;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ElaboradorDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.FinalidadeRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemElaboradorDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemFinalidadeRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorTelaSolicitacaoRelatorioEditar extends ControladorTelaEditar {

    private ResultadoListagemFinalidadeRelatorioDTO resultadoListagemFinalidadeRelatorioDTO;
    private ResultadoListagemElaboradorDTO resultadoListagemElaboradorDTO;
    
    private JTextField jtfNomeSolicitante;
    private JTextField jtfRgSolicitante;
    private JFormattedTextField jftProntuario;
    private JTextField jtfNomeUsuario;
    private JComboBox jcbTipoRelatorio;
    private JList jliTiposRelatorios;
    private JComboBox jcbElaborador;
    private JCheckBox jchEnviarViaCorreio;
    
    private UsuarioDTO usuarioDto;

    public ControladorTelaSolicitacaoRelatorioEditar(JDialog telaPai, SolicitacaoRelatorioDTO solicitacaoRelatorioDto, JTextField jtfNomeSolicitante, JTextField jtfRgSolicitante, JFormattedTextField jftProntuario, JTextField jtfNomeUsuario, JComboBox jcbTipoRelatorio, JList jliTiposRelatorios, JComboBox jcbElaborador, JCheckBox jchEnviarViaCorreio) {
        super(telaPai, solicitacaoRelatorioDto);
        this.jtfNomeSolicitante = jtfNomeSolicitante;
        this.jtfRgSolicitante = jtfRgSolicitante;
        this.jftProntuario = jftProntuario;
        this.jtfNomeUsuario = jtfNomeUsuario;
        this.jcbTipoRelatorio = jcbTipoRelatorio;
        this.jliTiposRelatorios = jliTiposRelatorios;
        this.jcbElaborador = jcbElaborador;
        this.jchEnviarViaCorreio = jchEnviarViaCorreio;
        TelaUtils.habilitarSomenteUpperCaseNosCamposTexto(this);
        inicializarCombosDadosAuxiliares();
    }

    public void selecionarProntuario() {
        EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDto = new EspecificacaoPesquisaUsuarioDTO();
        especificacaoPesquisaUsuarioDto.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, jftProntuario.getText());
        try {
            ResultadoListagemUsuarioDTO resultadoListagemUsuarioDto = servicoSisLaraServer.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDto);
            if (resultadoListagemUsuarioDto.sucesso()) {
                usuarioDto = resultadoListagemUsuarioDto.getObjetosDto().get(0);
                carregarCamposUsuario(usuarioDto);
            } else {
                limparDadosUsuario();
            }
        } catch (Exception e) {
            logger.fatal("Error: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA);
        }
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoDTO) servicoSisLaraServer.editarSolicitacaoRelatorio(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    private SolicitacaoRelatorioDTO obterObjetoDTO(){
        return (SolicitacaoRelatorioDTO)objetoDto;
    }
        
    @Override
    protected void prepararDtoParaEditar() {
        SolicitacaoRelatorioDTO solicitacaoRelatorioDto = obterObjetoDTO();
        solicitacaoRelatorioDto.setUsuarioDto(usuarioDto);
        solicitacaoRelatorioDto.setNomeSolicitante(jtfNomeSolicitante.getText());
        solicitacaoRelatorioDto.setRgSolicitante(jtfRgSolicitante.getText());
        solicitacaoRelatorioDto.setFinalidadesRelatoriosDto((List<FinalidadeRelatorioDTO>)obterDtos(jliTiposRelatorios));
        solicitacaoRelatorioDto.setElaboradorDto((ElaboradorDTO)obterDtoSelecionado(jcbElaborador));
        solicitacaoRelatorioDto.setEnviarPeloCorreio(jchEnviarViaCorreio.isSelected());
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
        return Sessao.CHAVE_SOLICITACAO_RELATORIO;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbTipoRelatorio, resultadoListagemFinalidadeRelatorioDTO.getObjetosDto());
        adicionarDtos(jcbElaborador, resultadoListagemElaboradorDTO.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemFinalidadeRelatorioDTO = servicoSisLaraServer.obterListagemFinalidadeRelatorio();
            resultadoListagemElaboradorDTO = servicoSisLaraServer.obterListagemElaborador();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    public void carregarCampos() {
    }

    private void carregarCamposUsuario(UsuarioDTO usuarioDto) {
        jtfNomeUsuario.setText(usuarioDto.getInformacaoEssencialDto().getNome());
    }

    private void limparDadosUsuario() {
        jtfNomeUsuario.setText(null);
    }

    public void abrirTelaUtilizarUsuario() {
         new TelaUtilizarUsuario((JDialog)telaPai);
         atualizaCamposUsuario();
    }
        
    private void atualizaCamposUsuario(){
        String chave = Sessao.CHAVE_USUARIO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            usuarioDto = (UsuarioDTO)Sessao.obterInstancia().obterDto(chave);
            carregarCamposUsuarioELimparPreCadastro(usuarioDto);
        }
    }
    
    private void carregarCamposUsuarioELimparPreCadastro(UsuarioDTO usuarioDto) {
        if (usuarioDto!=null){
            jftProntuario.setText(usuarioDto.getId().toString());
            jtfNomeUsuario.setText(usuarioDto.getInformacaoEssencialDto().getNome());
        }
    }

    public void adicionarTipoRelatorio() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliTiposRelatorios, jcbTipoRelatorio);
    }

    public void removerTipoRelatorioSelecionado() {
        if ((estaComItemValidoSelecionado(jliTiposRelatorios) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliTiposRelatorios);
        }
    }
}

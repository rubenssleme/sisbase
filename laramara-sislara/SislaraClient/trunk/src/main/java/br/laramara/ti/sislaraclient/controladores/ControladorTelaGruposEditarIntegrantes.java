
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.infra.PermissaoDeTelas;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaModuloPreCadastro;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaModuloUsuario;
import br.laramara.ti.sislaraclient.telas.TelaGruposEditarOrigemComunidade;
import br.laramara.ti.sislaraclient.telas.TelaGruposStatusIntegrante;
import br.laramara.ti.sislaraclient.telas.TelaGruposStatusIntegrantes;
import br.laramara.ti.sislaraclient.telas.TelaTexto;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarUsuario;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaraclient.utilitarios.TelaUtils;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloRelacaoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoEdicaoModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemStatusRelacaoUsuarioModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoModuloRelacaoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.StatusRelacaoComModuloDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ControladorTelaGruposEditarIntegrantes extends ControladorTelaEditar {

    private ResultadoListagemStatusRelacaoUsuarioModuloDTO resultadoListagemStatusRelacaoUsuarioModuloDTO;
    
    private JTextField jtfGrupo;
    private JTextField jtfModuloAtividade;
    private JFormattedTextField jftProntuario;
    private JTextField jtfUsuario;
    private JFormattedTextField jftDataInicioUsuario;
    private JComboBox jcbStatus;
    private JCheckBox jchIgnorarReunicaoIntegracao;
    private JTable jtaUsuarios;
    
    private JTable jtaPreCadastros;

    private UsuarioDTO usuarioDtoSelecionado;
    private GrupoDTO grupoDtoSelecionado;
    
    public ControladorTelaGruposEditarIntegrantes(JDialog telaPai, GrupoDTO grupoDtoSelecionado, ModuloPeriodoDTO moduloPeriodoDto, JTextField jtfGrupo, JTextField jtfModuloAtividade, JFormattedTextField jftProntuario, JTextField jtfUsuario, JFormattedTextField jftDataInicioUsuario, JComboBox jcbStatus, JCheckBox jchIgnorarReunicaoIntegracao, JTable jtaUsuarios, JTable jtaPreCadastros){
        super(telaPai, moduloPeriodoDto);
        this.jtfGrupo = jtfGrupo;
        this.jtfModuloAtividade = jtfModuloAtividade;
        this.jftProntuario = jftProntuario;
        this.jtfUsuario = jtfUsuario;
        this.jftDataInicioUsuario = jftDataInicioUsuario;
        this.jcbStatus = jcbStatus;
        this.jchIgnorarReunicaoIntegracao = jchIgnorarReunicaoIntegracao;
        this.jtaUsuarios = jtaUsuarios;
        this.jtaPreCadastros = jtaPreCadastros;
        this.grupoDtoSelecionado = grupoDtoSelecionado; 
        inicializarCombosDadosAuxiliares();
        carregarCampos();
        configurarColunasTabela();
        configurarSelecaoMultiplaModuloUsuario();
        TelaUtils.habilitarSomenteUpperCaseNosCamposTexto(this);
    }
    
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoEdicaoModuloPeriodoDTO) servicoSisLaraServer.editarModuloPeriodo(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }
    
    private ResultadoDTO solicitarValidacaoModuloRelacaoBaseDto(List<ModuloRelacaoBaseDTO> modulosRelacaoBaseDto, ModuloRelacaoBaseDTO moduloRelacaoBaseDTO) throws Exception {
        return (ResultadoValidacaoModuloRelacaoBaseDTO) servicoSisLaraServer.validarModuloRelacaoBase(modulosRelacaoBaseDto, moduloRelacaoBaseDTO);
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setModulosUsuariosDto((List<ModuloUsuarioDTO>) obterDtos(jtaUsuarios));
        obterObjetoDTO().setModulosPreCadastroDto((List<ModuloPreCadastroDTO>)obterDtos(jtaPreCadastros));
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void executarAcaoAposInclusao() {
    }
    
    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbStatus, resultadoListagemStatusRelacaoUsuarioModuloDTO.getObjetosDto());
    }
  
    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemStatusRelacaoUsuarioModuloDTO = servicoSisLaraServer.obterListagemStatusRelacaoUsuarioModuloDisponiveisParaInclusao(grupoDtoSelecionado);
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    public String obterChaveSessao() {
       return Sessao.CHAVE_MODULO_PERIODO;
    }

    private ModuloPeriodoDTO obterObjetoDTO(){
        return (ModuloPeriodoDTO)objetoDto;
    }
        
    private StatusRelacaoComModuloDTO obterStatusRelacionamentoPadrao() {
        try {
            return servicoSisLaraServer.obterStatusRelacaoPadrao(grupoDtoSelecionado);
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
            return null;
        }
    }

    @Override
    public void carregarCampos() {
        jtfGrupo.setText(grupoDtoSelecionado.toStringApenasNomeETurma());
        jtfModuloAtividade.setText(obterObjetoDTO().toString());
        adicionarDtos(obterObjetoDTO().getModulosUsuariosDto(), jtaUsuarios);
        adicionarDtos(obterObjetoDTO().getModulosPreCadastroDto(), jtaPreCadastros);
    }

    public void localizarUsuario() {
        new TelaUtilizarUsuario((JDialog)telaPai); 
        String chave = Sessao.CHAVE_USUARIO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            usuarioDtoSelecionado = (UsuarioDTO) Sessao.obterInstancia().obterDto(chave);
            atualizarCampoNomeUsuarioSelecionado(usuarioDtoSelecionado);
        }
    }
    
    private void atualizarCampoNomeUsuarioSelecionado(UsuarioDTO usuarioDto) {
        jftProntuario.setText(usuarioDto.getId().toString());
        jtfUsuario.setText(usuarioDto.getInformacaoEssencialDto().getNome());
    }

    public void adicionarIntegrante() {
        ModuloUsuarioDTO moduloUsuarioDTO = new ModuloUsuarioDTO();
        moduloUsuarioDTO.setDataInicio(jftDataInicioUsuario.getText());
        moduloUsuarioDTO.setUsuarioDto(usuarioDtoSelecionado);
        moduloUsuarioDTO.setStatusDto((StatusRelacaoComModuloDTO) obterDtoSelecionado(jcbStatus));
        moduloUsuarioDTO.setIgnorarRegraDeReuniaoDeIntegracao(jchIgnorarReunicaoIntegracao.isSelected());
        if (usuarioDtoSelecionado != null) {
            try {
                ResultadoDTO resultado = solicitarValidacaoModuloRelacaoBaseDto((List<ModuloRelacaoBaseDTO>) obterDtos(jtaUsuarios), moduloUsuarioDTO);
                if (resultado.sucesso()) {
                    List<ModuloUsuarioDTO> moduloUsuario = new ArrayList<>();
                    moduloUsuario.add((ModuloUsuarioDTO) resultado.obterObjetoDtoEditado());
                    obterObjetoDTO().setModulosUsuariosDto(moduloUsuario);
                    obterModeloTabelaModuloUsuario().adicionarDTO((ModuloUsuarioDTO) resultado.obterObjetoDtoEditado());
                    limparCampoNomeUsuarioDataInicio();
                } else {
                    JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
                }
            } catch (Exception e) {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
                logger.fatal(e);
            }
        }
    }
    
    private void limparCampoNomeUsuarioDataInicio(){
        usuarioDtoSelecionado = null;
        jftDataInicioUsuario.setText("");
        jtfUsuario.setText("");
        jftProntuario.setText("");
        jchIgnorarReunicaoIntegracao.setSelected(false);
        deselecionarDto(jcbStatus);
    }

    private ModeloTabelaModuloUsuario obterModeloTabelaModuloUsuario() {
        return ((ModeloTabelaModuloUsuario)jtaUsuarios.getModel());
    }
    
    private ModeloTabelaModuloPreCadastro obterModeloTabelaModuloPreCadastro() {
        return ((ModeloTabelaModuloPreCadastro)jtaPreCadastros.getModel());
    }

    private void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaUsuarios);
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaPreCadastros);
    }

    public void abrirTelaDeAlteracaoModuloUsuario(KeyEvent evt) {
        if (teclaEnterPressionada(evt)){
            alterarModuloUsuarioSelecionado();
        }
    }
        
    public void alterarModuloUsuarioSelecionado() {
        if (multiplosItensSelecionados(jtaUsuarios)) {
            List<ModuloUsuarioDTO> modulosUsuariosSelecionados = (List<ModuloUsuarioDTO>) obterDtosSelecionados(jtaUsuarios);
            new TelaGruposStatusIntegrantes((JDialog) telaPai, modulosUsuariosSelecionados, obterObjetoDTO());
            if (Sessao.obterInstancia().possuiDtos(Sessao.CHAVE_MODULO_RELACAO_BASE)){
                atualizarDtosSelecionados(jtaUsuarios, Sessao.obterInstancia().obterDtos(Sessao.CHAVE_MODULO_RELACAO_BASE));
            }
        } else {
            new TelaGruposStatusIntegrante((JDialog) telaPai, (ModuloUsuarioDTO) obterDtoSelecionado(jtaUsuarios), obterObjetoDTO());
            String chave = Sessao.CHAVE_MODULO_RELACAO_BASE;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaUsuarios, (ModuloUsuarioDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }

    public void adicionarComunidade() {
        new TelaGruposEditarOrigemComunidade((JDialog) telaPai, new ModuloPreCadastroDTO());
        String chave = Sessao.CHAVE_MODULO_RELACAO_BASE;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            obterModeloTabelaModuloPreCadastro().adicionarDTO((ModuloPreCadastroDTO)Sessao.obterInstancia().obterDto(chave));
        }
    }

    public void alterarModuloPreCadastroSelecionado() {
        new TelaGruposStatusIntegrante((JDialog) telaPai, (ModuloPreCadastroDTO) obterDtoSelecionado(jtaPreCadastros), obterObjetoDTO());
        atualizaComunidade();
    }

    public void alterarOrigem() {
        new TelaGruposEditarOrigemComunidade((JDialog) telaPai, (ModuloPreCadastroDTO) obterDtoSelecionado(jtaPreCadastros));
        atualizaComunidade();
    }
    
    private void atualizaComunidade(){
      String chave = Sessao.CHAVE_MODULO_RELACAO_BASE;
      if (Sessao.obterInstancia().possuiDto(chave)) {
            adicionarDtoNoItemSelecionado(jtaPreCadastros, (ModuloPreCadastroDTO) Sessao.obterInstancia().obterDto(chave));
        }
    }

    public void selecionarProntuario() {
        EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDto = new EspecificacaoPesquisaUsuarioDTO();
        especificacaoPesquisaUsuarioDto.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, jftProntuario.getText());
        try {
            ResultadoListagemUsuarioDTO resultadoListagemUsuarioDto = servicoSisLaraServer.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDto);
            if (resultadoListagemUsuarioDto.sucesso()) {
                usuarioDtoSelecionado = resultadoListagemUsuarioDto.getObjetosDto().get(0);
                selecionarStatusIntegradoComoPadrao();
                carregarCamposUsuario(usuarioDtoSelecionado);
            } else {
                limparDadosUsuario();
            }
        } catch (Exception e) {
            logger.fatal("Error: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA);
        }
    }
    
    private void carregarCamposUsuario(UsuarioDTO usuarioDto) {
        if (usuarioDto!=null){
            jftProntuario.setText(usuarioDto.getId().toString());
            jtfUsuario.setText(usuarioDto.getInformacaoEssencialDto().getNome());
        }
    }
    
    private void limparDadosUsuario(){
        jftProntuario.setText("");
        jtfUsuario.setText("");
        usuarioDtoSelecionado = null;
    }

    private void selecionarStatusIntegradoComoPadrao() {
        if (!estaComItemValidoSelecionado(jcbStatus)){
            StatusRelacaoComModuloDTO statusRelacaoComModuloDTO = obterStatusRelacionamentoPadrao();
            if (statusRelacaoComModuloDTO != null) {
                selecionarDto(jcbStatus, statusRelacaoComModuloDTO);
            }
        }
    }

    private void configurarSelecaoMultiplaModuloUsuario() {
        try {
            if (servicoSisLaraServer.possuiAutorizacao(Sessao.obterInstancia().obterToken(), PermissaoDeTelas.EDICAO_VARIAS_RELACAOES_MODULO_USUARIO_SIMULTANEAMENTE.toString())) {
                jtaUsuarios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            }else{
                 jtaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            }
        } catch (Exception e) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, e.getMessage());
        }
    }

    public void fecharTelaTexto(MouseEvent evento) {
        TelaTexto.fechar();
    }

    public void abrirTelaTexto(MouseEvent evt) {
        if (clickouUmaVez(evt) && estaComColunaSelecionada(jtaUsuarios, "OBS") && estaComItemValidoSelecionado(jtaUsuarios)) {
            ModuloUsuarioDTO moduloUsuarioDTO = (ModuloUsuarioDTO) obterDtoSelecionado(jtaUsuarios);
            TelaTexto.exibir(evt.getLocationOnScreen(), moduloUsuarioDTO.getObs());
        }
    }
}

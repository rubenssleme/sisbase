package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaUtilizarPreCadastroSemValidacao;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TelaUtils;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemTipoVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.TipoVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DiretoriaEnsinoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DreCefaiDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemDiretoriaEnsinoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemDreCefaiDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloRelacaoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class ControladorTelaGruposEditarOrigemComunidade extends ControladorTelaEditar {

    private ResultadoListagemTipoVinculoDTO resultadoListagemTipoVinculoDto;
    private ResultadoListagemInstituicaoDTO resultadoListagemInstituicaoDto;
    private ResultadoListagemDreCefaiDTO resultadoListagemDreCefaiDto;
    private ResultadoListagemDiretoriaEnsinoDTO resultadoListagemDiretoriaEnsinoDto;
    private ResultadoListagemInstituicaoDTO resultadoListagemInstituicaoComSRMsDto;
    private ResultadoListagemInstituicaoDTO resultadoListagemInstituicaoComSalaDeRecursoDto;
    private ResultadoListagemInstituicaoDTO resultadoListagemInstituicaoComOutrosAEEDto;
    
    private PreCadastroDTO preCadastroDtoSelecionado;
    private UsuarioDTO usuarioDtoSelecionado;
    
    private JTextField jtfNomePreCadastro;
    private JFormattedTextField jftDataInicioPreCadastro;
    private JComboBox jcbFormacao;
    private JFormattedTextField jftUsuarioVinculado;
    private JTextField jtfNomeUsuarioVinculado;
    private JTextField jtfNomeOrigem;
    private JTextField jtfCurso;
    private JComboBox jcbInstituicao;
    private JComboBox jcbDreCefai;
    private JComboBox jcbDiretoriaEnsino;
    private JComboBox jcbSaais;
    private JComboBox jcbSalaRecurso;
    private JComboBox jcbOutrosAEE;
    private JFormattedTextField jftQuantidadeCriancas;
    private JFormattedTextField jftQuantidadeAdultos;
    
    public ControladorTelaGruposEditarOrigemComunidade(JDialog telaPai, ModuloPreCadastroDTO moduloPreCadastroDto, JButton jbuLocalizar, JTextField jtfNomePreCadastro, JFormattedTextField jftDataIncicioPreCadastro, JComboBox jcbFormacao, JFormattedTextField jftUsuarioVinculado, JTextField jtfNomeUsuarioVinculado, JTextField jtfNomeOrigem, JTextField jtfCurso, JComboBox jcbInstituicao, JComboBox jcbDreCefai, JComboBox jcbDiretoriaEnsino, JComboBox jcbSaais, JComboBox jcbSalaRecurso, JComboBox jcbOutrosAEE, JFormattedTextField jftQuantidadeCriancas, JFormattedTextField jftQuantidadeAdultos) {
        super(telaPai, moduloPreCadastroDto);
        this.jtfNomePreCadastro = jtfNomePreCadastro;
        this.jftDataInicioPreCadastro = jftDataIncicioPreCadastro;
        this.jcbFormacao = jcbFormacao;
        this.jftUsuarioVinculado = jftUsuarioVinculado;
        this.jtfNomeUsuarioVinculado = jtfNomeUsuarioVinculado;
        this.jtfNomeOrigem = jtfNomeOrigem;
        this.jtfCurso = jtfCurso;
        this.jtfNomeOrigem = jtfNomeOrigem;
        this.jtfCurso = jtfCurso;
        this.jcbInstituicao = jcbInstituicao;
        this.jcbDreCefai = jcbDreCefai;
        this.jcbDiretoriaEnsino = jcbDiretoriaEnsino;
        this.jcbSaais = jcbSaais;
        this.jcbSalaRecurso = jcbSalaRecurso;
        this.jcbOutrosAEE = jcbOutrosAEE;
        this.jftQuantidadeCriancas = jftQuantidadeCriancas;
        this.jftQuantidadeAdultos = jftQuantidadeAdultos;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
        habilitarBotaoLocalizacaoPreCadastro(jbuLocalizar);
        TelaUtils.habilitarSomenteUpperCaseNosCamposTexto(this);
    }

    private void habilitarBotaoLocalizacaoPreCadastro(JButton jbu){
        if (obterObjetoDTO().getPreCadastroDto() == null){
            jbu.setEnabled(true);
        }
    }
    
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoDTO) servicoSisLaraServer.validarModuloRelacaoBase((ModuloRelacaoBaseDTO)obterObjetoDTO());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setDataInicio(jftDataInicioPreCadastro.getText());
        obterObjetoDTO().setPreCadastroDto(preCadastroDtoSelecionado);
        obterObjetoDTO().setUsuarioVinculadoDto(usuarioDtoSelecionado);
        obterObjetoDTO().setInstituicaoDto((InstituicaoDTO) obterDtoSelecionado(jcbInstituicao));
        obterObjetoDTO().setDreCefaiDto((DreCefaiDTO) obterDtoSelecionado(jcbDreCefai));
        obterObjetoDTO().setDiretoriaEnsinoDto((DiretoriaEnsinoDTO) obterDtoSelecionado(jcbDiretoriaEnsino));
        obterObjetoDTO().setInstituicaoComSrmsDto((InstituicaoDTO) obterDtoSelecionado(jcbSaais));
        obterObjetoDTO().setInstituicaoComSalaDeRecursoDto((InstituicaoDTO) obterDtoSelecionado(jcbSalaRecurso));
        obterObjetoDTO().setInstituicaoComOutrosAEEDto((InstituicaoDTO) obterDtoSelecionado(jcbOutrosAEE));
        obterObjetoDTO().setTipoVinculoDto((TipoVinculoDTO) obterDtoSelecionado(jcbFormacao));
        obterObjetoDTO().setNomeOrigemComunidade(jtfNomeOrigem.getText());
        obterObjetoDTO().setCurso(jtfCurso.getText());
        obterObjetoDTO().setQuantidadeCriancas(jftQuantidadeCriancas.getText());
        obterObjetoDTO().setQuantidadeAdultos(jftQuantidadeAdultos.getText());
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
        adicionarDtos(jcbFormacao, resultadoListagemTipoVinculoDto.getObjetosDto());
        adicionarDtos(jcbFormacao, resultadoListagemTipoVinculoDto.getObjetosDto());
        adicionarDtos(jcbInstituicao, resultadoListagemInstituicaoDto.getObjetosDto());
        adicionarDtos(jcbDreCefai, resultadoListagemDreCefaiDto.getObjetosDto());
        adicionarDtos(jcbDiretoriaEnsino, resultadoListagemDiretoriaEnsinoDto.getObjetosDto());
        adicionarDtos(jcbSaais, resultadoListagemInstituicaoComSRMsDto.getObjetosDto());
        adicionarDtos(jcbSalaRecurso, resultadoListagemInstituicaoComSalaDeRecursoDto.getObjetosDto());
        adicionarDtos(jcbOutrosAEE, resultadoListagemInstituicaoComOutrosAEEDto.getObjetosDto());
    }
  
    @Override
    public void obterDadosAuxiliares() {
        try{
            resultadoListagemTipoVinculoDto = servicoSisLaraServer.obterListagemTipoVinculo();
            resultadoListagemInstituicaoDto = servicoSisLaraServer.obterListagemInstituicao();
            resultadoListagemDreCefaiDto = servicoSisLaraServer.obterListagemDreCefai();
            resultadoListagemDiretoriaEnsinoDto = servicoSisLaraServer.obterListagemDiretoriaEnsino();
            resultadoListagemInstituicaoComSRMsDto = servicoSisLaraServer.obterListagemInstituicaoComSRMs();
            resultadoListagemInstituicaoComSalaDeRecursoDto = servicoSisLaraServer.obterListagemInstituicaoComSalaRecurso();
            resultadoListagemInstituicaoComOutrosAEEDto = servicoSisLaraServer.obterListagemInstituicaoComOutrosAEE();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    public void carregarCampos() {
        preCadastroDtoSelecionado = obterObjetoDTO().getPreCadastroDto();
        usuarioDtoSelecionado = obterObjetoDTO().getUsuarioVinculadoDto();
        jtfNomePreCadastro.setText(obterObjetoDTO().getPreCadastroDto()!=null ? obterObjetoDTO().getPreCadastroDto().getInformacaoEssencialDto().getNome() : "");
        jtfNomeUsuarioVinculado.setText((obterObjetoDTO().getUsuarioVinculadoDto() != null ? obterObjetoDTO().getUsuarioVinculadoDto().getInformacaoEssencialDto().getNome() : ""));
        jftUsuarioVinculado.setText(obterObjetoDTO().getUsuarioVinculadoDto()!=null ? obterObjetoDTO().getUsuarioVinculadoDto().getId().toString() : "");
        jftDataInicioPreCadastro.setText(obterObjetoDTO().getDataInicio());
        selecionarDto(jcbFormacao, obterObjetoDTO().getTipoVinculoDto());
        jtfNomeOrigem.setText(obterObjetoDTO().getNomeOrigemComunidade());
        jtfCurso.setText(obterObjetoDTO().getCurso());
        selecionarDto(jcbInstituicao, obterObjetoDTO().getInstituicaoDto());
        selecionarDto(jcbDreCefai, obterObjetoDTO().getDreCefaiDto());
        selecionarDto(jcbDiretoriaEnsino, obterObjetoDTO().getDiretoriaEnsinoDto());
        selecionarDto(jcbSaais, obterObjetoDTO().getInstituicaoComSRMsDto());
        selecionarDto(jcbSalaRecurso, obterObjetoDTO().getInstituicaoComSalaDeRecursoDto());
        selecionarDto(jcbOutrosAEE, obterObjetoDTO().getInstituicaoComOutrosAEEDto());
        jftQuantidadeCriancas.setText(obterObjetoDTO().getQuantidadeCriancas());
        jftQuantidadeAdultos.setText(obterObjetoDTO().getQuantidadeAdultos());
    }
    
    private ModuloPreCadastroDTO obterObjetoDTO(){
        return (ModuloPreCadastroDTO)objetoDto;
    }

    public void localizarPreCadastro() {
        new TelaUtilizarPreCadastroSemValidacao((JDialog)telaPai); 
        String chave = Sessao.CHAVE_PRE_CADASTRO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            preCadastroDtoSelecionado = (PreCadastroDTO) Sessao.obterInstancia().obterDto(chave);
            atualizarCampoNomePrecadastroSelecionado(preCadastroDtoSelecionado);
        }
    }
    
    private void atualizarCampoNomePrecadastroSelecionado(PreCadastroDTO preCadastroDto) {
        jtfNomePreCadastro.setText(preCadastroDto.getInformacaoEssencialDto().getNome());
    }

    public void selecionarProntuario() {
        EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDto = new EspecificacaoPesquisaUsuarioDTO();
        especificacaoPesquisaUsuarioDto.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, jftUsuarioVinculado.getText());
        try {
            ResultadoListagemUsuarioDTO resultadoListagemUsuarioDto = servicoSisLaraServer.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDto);
            if (resultadoListagemUsuarioDto.sucesso()) {
                usuarioDtoSelecionado = resultadoListagemUsuarioDto.getObjetosDto().get(0);
                carregarCamposUsuario(usuarioDtoSelecionado);
            } else {
                deselecionarUsuario();
            }
        } catch (Exception e) {
            logger.fatal("Error: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA);
        }
    }

    private void carregarCamposUsuario(UsuarioDTO usuarioDtoSelecionado) {
        jtfNomeUsuarioVinculado.setText(usuarioDtoSelecionado.getInformacaoEssencialDto().getNome());
    }

    private void deselecionarUsuario() {
        usuarioDtoSelecionado = null;
        jtfNomeUsuarioVinculado.setText("");
    }
}

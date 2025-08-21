package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaModuloPeriodo;
import br.laramara.ti.sislaraclient.telas.TelaEditarInstituicoes;
import br.laramara.ti.sislaraclient.telas.TelaGruposEditarModuloPeriodo;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemTipificacaoServicoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoLoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.TipificacaoServicoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ControladorTelaGruposEditar extends ControladorTelaEditar{

    private ControladorTipoAtendimento controladorTipoAtendimento;
    
    private ResultadoListagemTipificacaoServicoDTO resultadoListagemTipificacaoServicoDTO;
    private ResultadoListagemInstituicaoDTO resultadoListagemInstituicaoDTO;
    private ResultadoListagemRecursoDTO resultadoListagemRecursoDTO;
           
    private JComboBox jcbTipoAtendimento;
    private JComboBox jcbDescricaoTipoAtendimento;
    private JFormattedTextField jftDataInicio;
    private JFormattedTextField jftDataTermino;
    private JComboBox jcbSetor;
    private JComboBox jcbGrupo;
    private JFormattedTextField jftTurma;
    private JFormattedTextField jftNivel;
    private JCheckBox jchAtivo;
    private JTable jtaModuloAtividadesAdicionados;
    private JButton jbuRemoverModulo;
    private JEditorPane jepDescricao;
    private JComboBox jcbTipificacao;
    private JList jliTipificacaoAdicionadas;
    private JComboBox jcbInstituicaoEnsino;
    private JComboBox jcbRecursos;
    private JFormattedTextField jftQuantidadeRecurso;
    private JList jliDoacaoRecursosAdicionados;
             
    public ControladorTelaGruposEditar(JDialog telaPai, GrupoDTO grupoDto, JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JFormattedTextField jftDataInicio, JFormattedTextField jftDataTermino, JComboBox jcbSetor, JComboBox jcbGrupo, JFormattedTextField jftTurma, JFormattedTextField jftNivel, JCheckBox jchAtivo, JTable jtaModuloAtividadesAdicionados, JButton jbuRemoverModulo, JEditorPane jepDescricao, JComboBox jcbTipificacao, JList jliTipificacaoAdicionadas, JComboBox jcbInstituicaoEnsino, JComboBox jcbRecursos, JFormattedTextField jftQuantidadeRecurso, JList jliDoacaoRecursosAdicionados){
        super(telaPai, grupoDto);
        this.jcbTipoAtendimento = jcbTipoAtendimento;
        this.jcbDescricaoTipoAtendimento = jcbDescricaoTipoAtendimento;
        this.jftDataInicio = jftDataInicio;
        this.jftDataTermino = jftDataTermino;
        this.jcbGrupo = jcbGrupo;
        this.jftDataInicio = jftDataInicio;
        this.jftDataTermino = jftDataTermino;
        this.jcbSetor = jcbSetor;
        this.jcbGrupo = jcbGrupo;
        this.jftTurma = jftTurma;
        this.jftNivel = jftNivel;
        this.jchAtivo = jchAtivo;
        this.jtaModuloAtividadesAdicionados = jtaModuloAtividadesAdicionados;
        this.jbuRemoverModulo = jbuRemoverModulo;
        this.jepDescricao  = jepDescricao;
        this.jcbTipificacao = jcbTipificacao;
        this.jliTipificacaoAdicionadas = jliTipificacaoAdicionadas;
        this.jcbInstituicaoEnsino = jcbInstituicaoEnsino;
        this.jcbRecursos = jcbRecursos;
        this.jftQuantidadeRecurso = jftQuantidadeRecurso;
        this.jliDoacaoRecursosAdicionados = jliDoacaoRecursosAdicionados;
        this.controladorTipoAtendimento = new ControladorTipoAtendimento(jcbTipoAtendimento, jcbDescricaoTipoAtendimento, null, jcbSetor, jcbGrupo);
        inicializarCombosDadosAuxiliares();
        carregarCampos();
        configurarColunasTabela();
        esconderBotaoExcluirModuloPeriodo();
    }
    
    private void esconderBotaoExcluirModuloPeriodo(){
        if (obterObjetoDTO().isInicializado()){
            jbuRemoverModulo.setVisible(false);
        }
    }
    
    private void configurarColunasTabela(){
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaModuloAtividadesAdicionados);
    }
    
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.editarGrupo(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setSetorDto((SetorDTO) obterDtoSelecionado(jcbSetor));
        obterObjetoDTO().setDescricaoTipoAtendimentoDTO((DescricaoTipoAtendimentoDTO) obterDtoSelecionado(jcbDescricaoTipoAtendimento));
        obterObjetoDTO().setDataInicio(jftDataInicio.getText());
        obterObjetoDTO().setDataTermino(jftDataTermino.getText());
        obterObjetoDTO().setNomeGrupoDto((NomeGrupoDTO) obterDtoSelecionado(jcbGrupo));
        obterObjetoDTO().setTurma(jftTurma.getText());
        obterObjetoDTO().setNivel(jftNivel.getText());
        obterObjetoDTO().setAtivo(jchAtivo.isSelected());
        obterObjetoDTO().setModulosPeriodosDto((List<ModuloPeriodoDTO>) obterDtos(jtaModuloAtividadesAdicionados));
        obterObjetoDTO().setDescricao(jepDescricao.getText());
        obterObjetoDTO().setTipificacoesServicoDto((List<TipificacaoServicoDTO>) obterDtos(jliTipificacaoAdicionadas));
        obterObjetoDTO().setInstituicaoDoacaoDto((InstituicaoDTO) obterDtoSelecionado(jcbInstituicaoEnsino));
        
        obterObjetoDTO().setDoacaoRecursoDto((List<LoteRecursoDTO>) obterDtos(jliDoacaoRecursosAdicionados));
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
        return Sessao.CHAVE_GRUPO;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbTipificacao, resultadoListagemTipificacaoServicoDTO.getObjetosDto());        
        adicionarDtos(jcbInstituicaoEnsino, resultadoListagemInstituicaoDTO.getObjetosDto());
        adicionarDtos(jcbRecursos, resultadoListagemRecursoDTO.getObjetosDto());
    }
    
    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemTipificacaoServicoDTO = servicoSisLaraServer.obterListagemTipificacaoServico(); 
            resultadoListagemInstituicaoDTO = servicoSisLaraServer.obterListagemInstituicao();
            resultadoListagemRecursoDTO = servicoSisLaraServer.obterListagemRecurso();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
   }
    
    private GrupoDTO obterObjetoDTO(){
        return (GrupoDTO)objetoDto;
    }

    public void inicializarCombosComDependencia() {
        if (obterObjetoDTO().getDescricaoTipoAtendimentoDTO() != null) {
            atualizarCamposTipoAtendimentoDescricaoAtendimentoNomeGrupoSetor();
        }
        
    }
    
    private void atualizarCamposTipoAtendimentoDescricaoAtendimentoNomeGrupoSetor() {
        jcbTipoAtendimento.setSelectedItem(obterObjetoDTO().getDescricaoTipoAtendimentoDTO().getTipoAtendimentoDto());
        jcbDescricaoTipoAtendimento.setSelectedItem(obterObjetoDTO().getDescricaoTipoAtendimentoDTO());
        jcbGrupo.setSelectedItem(obterObjetoDTO().getNomeGrupoDto());
        jcbSetor.setSelectedItem(obterObjetoDTO().getSetorDto());
    }
       
    @Override
    public void carregarCampos() {
        selecionarDto(jcbSetor, obterObjetoDTO().getSetorDto());
        jftDataInicio.setText(obterObjetoDTO().getDataInicio());
        jftDataTermino.setText(obterObjetoDTO().getDataTermino());
        selecionarDto(jcbGrupo, obterObjetoDTO().getNomeGrupoDto());
        jftTurma.setText(obterObjetoDTO().getTurma());
        jftNivel.setText(obterObjetoDTO().getNivel());
        jchAtivo.setSelected(obterObjetoDTO().isAtivo());
        adicionarDtos(obterObjetoDTO().getModulosPeriodosDto(), jtaModuloAtividadesAdicionados);
        jepDescricao.setText(obterObjetoDTO().getDescricao());
        adicionarDtos(obterObjetoDTO().getTipificacoesServicoDto(), jliTipificacaoAdicionadas);
        adicionarDtos(obterObjetoDTO().getDoacaoRecursoDto(), jliDoacaoRecursosAdicionados);
        selecionarDto(jcbInstituicaoEnsino, obterObjetoDTO().getInstituicaoDoacaoDto());
    }

    public void adicionarModuloPeriodo() {
        new TelaGruposEditarModuloPeriodo((JDialog) telaPai, new ModuloPeriodoDTO(), (DescricaoTipoAtendimentoDTO)obterDtoSelecionado(jcbDescricaoTipoAtendimento));
        String chave = Sessao.CHAVE_MODULO_PERIODO;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            ModuloPeriodoDTO moduloPeriodoDTO = (ModuloPeriodoDTO) Sessao.obterInstancia().obterDto(chave);
            obterModeloTabelaModuloPeriodo().adicionarDTO(moduloPeriodoDTO);
        }
    }

    public void removerModuloAtividadeSelecionado() {
        if ((estaComItemValidoSelecionado(jtaModuloAtividadesAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jtaModuloAtividadesAdicionados);
        }
    }
    
    private ModeloTabela obterModeloTabelaModuloPeriodo() {
        return ((ModeloTabelaModuloPeriodo)jtaModuloAtividadesAdicionados.getModel());
    }

    public void alterarModuloPeriodo() {
         if (estaComItemValidoSelecionado(jtaModuloAtividadesAdicionados)){
            new TelaGruposEditarModuloPeriodo((JDialog) telaPai, (ModuloPeriodoDTO)obterDtoSelecionado(jtaModuloAtividadesAdicionados), (DescricaoTipoAtendimentoDTO)obterDtoSelecionado(jcbDescricaoTipoAtendimento));
            String chave = Sessao.CHAVE_MODULO_PERIODO;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaModuloAtividadesAdicionados, (ModuloPeriodoDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }

    public void abrirTelaDeAlteracaoModuloPeriodo(KeyEvent evt) {
        if (teclaEnterPressionada(evt)){
            alterarModuloPeriodo();
        }
    }
    
    
    public void adicionarInstituicao() {
        String chaveInstituicao = Sessao.CHAVE_INSTITUICAO;
        new TelaEditarInstituicoes((JDialog)telaPai, new InstituicaoDTO());
        if (Sessao.obterInstancia().possuiDto(chaveInstituicao)){
            InstituicaoDTO instituicaoDto = (InstituicaoDTO) Sessao.obterInstancia().obterDto(chaveInstituicao);
            adicionarESelecionadDto(jcbInstituicaoEnsino, instituicaoDto);
        }
    }
        
    public void inicializarDescricaoTipoAtendimento() {
        controladorTipoAtendimento.inicializarDescricaoTipoAtendimento();
    }

    public void inicializarNomeGrupo() {
        controladorTipoAtendimento.inicializarNomeGrupo();
    }

    public void inicializarSetor() {
       controladorTipoAtendimento.inicializarSetor(); 
    }
    
    public void adicionarTipificacao() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliTipificacaoAdicionadas, jcbTipificacao);    
    }
    
    public void removerTipificacaoSelecionada() {
        if ((estaComItemValidoSelecionado(jliTipificacaoAdicionadas) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliTipificacaoAdicionadas);
        }
    }

    public void adicionarRecurso(){
        RecursoDTO recursoDto = (RecursoDTO) obterDtoSelecionado(jcbRecursos);
        LoteRecursoDTO doacaoRecursoDto = new LoteRecursoDTO(recursoDto, jftQuantidadeRecurso.getText());
        try {
            ResultadoValidacaoLoteRecursoDTO resultadoValidacaoDoacaoRecursoDTO = servicoSisLaraServer.validarLoteRecurso(doacaoRecursoDto);
            if (resultadoValidacaoDoacaoRecursoDTO.sucesso()) {
                adicionarDtos(resultadoValidacaoDoacaoRecursoDTO.obterObjetoDtoEditado(), jliDoacaoRecursosAdicionados);
                limparCamposDoacaoRecurso();
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoValidacaoDoacaoRecursoDTO.obterMensagens());
            }
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_ADICAO_LOTE_RECURSO);
            logger.error(JOptionPanePersonalizado.ERRO_ADICAO_LOTE_RECURSO + ex.getMessage());
        }
    }
    
    public void removerRecursoSelecionado(){
        if ((estaComItemValidoSelecionado(jliDoacaoRecursosAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliDoacaoRecursosAdicionados);
        }
    }    

    private void limparCamposDoacaoRecurso() {
        deselecionarDto(jcbRecursos);
        jftQuantidadeRecurso.setText("");
    }
}

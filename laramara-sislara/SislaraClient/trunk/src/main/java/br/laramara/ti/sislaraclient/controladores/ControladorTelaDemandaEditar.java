
package br.laramara.ti.sislaraclient.controladores;

import static br.laramara.ti.sislaraclient.controladores.ControladorTela.logger;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarPreCadastroSemValidacao;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.DocumentoSolicitacaoDoacaoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoGeracaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.NomeDocumentoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoGeracaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemNomeDocumentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.EspecificacaoPesquisaPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.ResultadoListagemPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.io.File;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ControladorTelaDemandaEditar extends ControladorTela{
    private PreCadastroDTO preCadastroDto;
    private UsuarioDTO usuarioDto;
    
    private ResultadoListagemRecursoDTO resultadoListagemRecursoDto;
    private ResultadoListagemNomeDocumentoDTO resultadoListagemNomeDocumentoDTO;
    
    private JComboBox jcbRecurso;
    private JList jliRecursosAdicionados;
    private JCheckBox jchCartelaDeSelos;
    private JTextField jtfProntuario; 
    private JTextField jtfNomeUsuario;
    private JFormattedTextField jftCpf;
    private JTextField jtfNomePreCadastro;
    private JComboBox jcbNomeArquivoAAdicionar;
    private JButton jbuAdicionarDocumento;
    private JList jliDocumentosAdicionados;
    
    public ControladorTelaDemandaEditar(JDialog telaPai, JComboBox jcbRecurso, JList jliRecursosAdicionados, JCheckBox jchCartelaDeSelos, JTextField jtfProntuario, JTextField jtfNomeUsuario, JFormattedTextField jftCpf, JTextField jtfNomePreCadastro, JComboBox jcbNomeArquivoAAdicionar, JButton jbuAdicionarDocumento, JList jliDocumentosAdicionados){
        super(telaPai);
        this.jcbRecurso = jcbRecurso;
        this.jliRecursosAdicionados = jliRecursosAdicionados;
        this.jchCartelaDeSelos = jchCartelaDeSelos;
        this.jtfProntuario = jtfProntuario;
        this.jtfNomeUsuario = jtfNomeUsuario;
        this.jftCpf = jftCpf;
        this.jtfNomePreCadastro = jtfNomePreCadastro;
        this.jcbNomeArquivoAAdicionar = jcbNomeArquivoAAdicionar;
        this.jbuAdicionarDocumento = jbuAdicionarDocumento;
        this.jliDocumentosAdicionados = jliDocumentosAdicionados;
        inicializarCombosDadosAuxiliares();
    }
    
    private void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbRecurso, resultadoListagemRecursoDto.getObjetosDto());
        adicionarDtos(jcbNomeArquivoAAdicionar, resultadoListagemNomeDocumentoDTO.getObjetosDto());
    }
    
    private void obterDadosAuxiliares() {
        try {
          resultadoListagemRecursoDto = servicoSisLaraServer.obterListagemRecurso();
          resultadoListagemNomeDocumentoDTO = servicoSisLaraServer.obterListagemNomeDocumento();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    private EspecificacaoGeracaoDemandaDTO obterEspecificacao() {
        EspecificacaoGeracaoDemandaDTO especificacao = new EspecificacaoGeracaoDemandaDTO();
        especificacao.setRecursosDto((List<RecursoDTO>) obterDtos(jliRecursosAdicionados));
        especificacao.setUsuariosDto(usuarioDto);
        especificacao.setPreCadastrosDto(preCadastroDto);
        especificacao.setDocumentosSolicitacaoDocacaoDto((List<DocumentoSolicitacaoDoacaoDTO>) obterDtos(jliDocumentosAdicionados));
        especificacao.setCartelaDeSelos(jchCartelaDeSelos.isSelected());
        return especificacao;
    }
    
    public void salvar() {
        int resposta = JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO);
        if (resposta == JOptionPane.OK_OPTION) {
            EspecificacaoGeracaoDemandaDTO especificacao = obterEspecificacao();
            try {
                ResultadoGeracaoDemandaDTO resultado = servicoSisLaraServer.gerarDemanda(especificacao, Sessao.obterInstancia().obterToken());
                if (resultado.sucesso()) {
                    servicoSisLaraServer.gravarTela(obterTela());
                    Sessao.obterInstancia().armazenarDTO(Sessao.CHAVE_LISTAGEM_DEMANDAS, resultado);
                    JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultado.obterMensagens());
                    fecharTela();
                } else {
                    JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
                }
            } catch (Exception ex) {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_GERACAO_DEMANDA);
                logger.error(JOptionPanePersonalizado.ERRO_NA_GERACAO_DEMANDA + ex.getMessage());
            }
        }
    }

    public void selecionarProntuario() {
        EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDto = new EspecificacaoPesquisaUsuarioDTO();
        especificacaoPesquisaUsuarioDto.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, jtfProntuario.getText());
        try {
            ResultadoListagemUsuarioDTO resultadoListagemUsuarioDto = servicoSisLaraServer.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDto);
            if (resultadoListagemUsuarioDto.sucesso()) {
                usuarioDto = resultadoListagemUsuarioDto.getObjetosDto().get(0);
                carregarCamposUsuarioELimparCampoPreCadastro(usuarioDto);
            } else {
                limparDadosUsuario();
            }
        } catch (Exception e) {
            logger.fatal("Error: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA);
        }
    }
                
    private void carregarCamposUsuarioELimparCampoPreCadastro(UsuarioDTO usuarioDto) {
        if (usuarioDto!=null){
            jtfProntuario.setText(usuarioDto.getId().toString());
            jtfNomeUsuario.setText(usuarioDto.getInformacaoEssencialDto().getNome());
            limparDadosPreCadastro();
         }else{
            limparDadosUsuario();
        }
    }
    
    private void limparDadosPreCadastro(){
        jftCpf.setText("");
        jtfNomePreCadastro.setText("");
        preCadastroDto = null;
    }
    
    private void limparDadosUsuario(){
        jtfProntuario.setText("");
        jtfNomeUsuario.setText("");
        usuarioDto = null;
    }
    
    private void carregarCamposPreCadastroELimparUsuario(PreCadastroDTO preCadastroDto) {
        if (preCadastroDto != null) {
            jftCpf.setText(preCadastroDto.getInformacaoEssencialDto().getCpf());
            jtfNomePreCadastro.setText(preCadastroDto.getInformacaoEssencialDto().getNome());
        }
        limparDadosUsuario();
    }
    
    private void atualizaCamposPreCadastro(){
        String chave = Sessao.CHAVE_PRE_CADASTRO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            preCadastroDto = (PreCadastroDTO)Sessao.obterInstancia().obterDto(chave);
            carregarCamposPreCadastroELimparUsuario(preCadastroDto);
        }        
    }
    
    public void abrirTelaUtilizarPreCadastro() {
        new TelaUtilizarPreCadastroSemValidacao((JDialog)telaPai);
        atualizaCamposPreCadastro();
    }

    public void adicionarDocumento() {
        JFileChooser jfcArquivo = new JFileChooser();
        jfcArquivo.setAcceptAllFileFilterUsed(false);
        jfcArquivo.addChoosableFileFilter(new FileNameExtensionFilter("Arquivo PDF", "pdf"));
        jfcArquivo.addChoosableFileFilter(new FileNameExtensionFilter("Imagem", "jpg"));
        
        if (jfcArquivo.showDialog(telaPai, "Selecione o arquivo") == JFileChooser.APPROVE_OPTION) {
            try {
                if (jfcArquivo.getSelectedFile() != null && estaComItemValidoSelecionado(jcbNomeArquivoAAdicionar)) {
                    File arquivoSelecionado = jfcArquivo.getSelectedFile();
                    ArquivoDTO arquivoDTO = new ArquivoDTO(null, arquivoSelecionado.getName(), FileUtils.readFileToByteArray(arquivoSelecionado), FilenameUtils.getExtension(arquivoSelecionado.getPath()));
                    DocumentoSolicitacaoDoacaoDTO documentoSolicitacaoDoacaoDTO = new DocumentoSolicitacaoDoacaoDTO();
                    documentoSolicitacaoDoacaoDTO.setNomeDocumentoDTO((NomeDocumentoDTO) obterDtoSelecionado(jcbNomeArquivoAAdicionar));
                    documentoSolicitacaoDoacaoDTO.setArquivoDTO(arquivoDTO);
                    if (!existe(jliDocumentosAdicionados, documentoSolicitacaoDoacaoDTO)){
                        adicionarDtoSemRepetir(documentoSolicitacaoDoacaoDTO, jliDocumentosAdicionados);
                        deselecionarDto(jcbNomeArquivoAAdicionar);
                        jcbNomeArquivoAAdicionar.requestFocusInWindow();
                    }else{
                        JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_ITEM_EXISTENTE);
                    }
                }
            } catch (Exception ex) {
                logger.error(JOptionPanePersonalizado.ERRO_DURANTE_SELECAO_FOTO + "\n Detalhes: " + ex);
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_SELECAO_FOTO);
            }
        }
    }

    public void abrirDocumento() {
        DocumentoSolicitacaoDoacaoDTO documentoSolicitacaoDoacaoDto = (DocumentoSolicitacaoDoacaoDTO) obterDtoSelecionado(jliDocumentosAdicionados);
        try {
            exibirArquivo(documentoSolicitacaoDoacaoDto.getArquivoDTO());
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\nDetalhes: " + e.getMessage());
        }
    }

    public void habilitarAdicionar() {
        jbuAdicionarDocumento.setEnabled(estaComItemValidoSelecionado(jcbNomeArquivoAAdicionar));
    }

    public void removerDocumento() {
        removerDtoSelecionado(jliDocumentosAdicionados);
    }

    public void selecionarPreCadastro() {
        EspecificacaoPesquisaPreCadastroDTO especificacaoPesquisaPreCadastroDto = new EspecificacaoPesquisaPreCadastroDTO();
        especificacaoPesquisaPreCadastroDto.adicionarParametro(ChavePesquisaDTO.CPF, jftCpf.getText());
        try {
            ResultadoListagemPreCadastroDTO resultadoListagemPreCadastroDto = servicoSisLaraServer.pesquisarPreCadastroPor(especificacaoPesquisaPreCadastroDto);
            if (resultadoListagemPreCadastroDto.sucesso()) {
                if (resultadoListagemPreCadastroDto.getObjetosDto().size() > 1){
                    JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_JA_EXISTE_CPF_DUPLICADO);
                }else{
                    preCadastroDto = resultadoListagemPreCadastroDto.getObjetosDto().get(0);
                    carregarCamposPreCadastroELimparUsuario(preCadastroDto);
                }
            } else {
                limparDadosPreCadastro();
            }
        } catch (Exception e) {
            logger.fatal("Error: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA);
        }
    }

    public void adicionarRecurso() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliRecursosAdicionados, jcbRecurso);
    }

    public void removerRecursoAdicionado() {
        if ((estaComItemValidoSelecionado(jliRecursosAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliRecursosAdicionados);
        }
    }
}
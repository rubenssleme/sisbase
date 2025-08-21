package br.laramara.ti.sislaraclient.controladores;

import static br.laramara.ti.sislaraclient.controladores.ControladorTela.logger;
import br.laramara.ti.sislaraclient.infra.PermissaoDeTelas;
import br.laramara.ti.sislaraclient.utilitarios.ClonadorUtils;
import br.laramara.ti.sislaraclient.utilitarios.EsperaUtils;
import br.laramara.ti.sislaraclient.utilitarios.FiltroArquivosAvaliacaoFuncional;
import br.laramara.ti.sislaraclient.utilitarios.FiltroArquivosOrtoptica;
import br.laramara.ti.sislaraclient.utilitarios.ImagePreview;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.SomUtils;
import br.laramara.ti.sislaracommons.Bloqueavel;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDetalhadaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoValidacaoParticipacaoDetalhadaDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoCEPDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ResultadoConsultaCEP;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoCoordenacaoEdicaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemMunicipioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.commons.io.FileUtils;

public abstract class ControladorTelaEditar extends ControladorTela implements SessaoUtilizavel, CombosAuxiliaresInicializaveis, CamposDadosCarregavel{
    
    protected ModeloDTO objetoDto;
    
    public ControladorTelaEditar(JDialog telaPai, ModeloDTO objetoReferenciaDto){
        super(telaPai);
        this.objetoDto = (ModeloDTO) ClonadorUtils.copiar(objetoReferenciaDto);
        Sessao.obterInstancia().removerDto(obterChaveSessao());
    }

    public void inicializarCampoMunicipio(JComboBox jcbUF, JComboBox jcbMunicipio) {
        try {
            List<? extends ModeloDTO> objetosDto = null;
            if (estaComItemValidoSelecionado(jcbUF)) {
                objetosDto = ((ResultadoListagemMunicipioDTO) servicoSisLaraServer.obterListagemMunicipio((UfDTO) obterDtoSelecionado(jcbUF))).getObjetosDto();
            }
            carregarCombosDependentes(jcbUF, jcbMunicipio, objetosDto);
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    protected void removerArquivoSelecionado(JList jliArquivos) {
       if (estaComItemValidoSelecionado(jliArquivos) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION){
           removerDtoSelecionado(jliArquivos);
       }
    }
    
    protected void adicionarArquivo(JList jliArquivos) {
         adicionarArquivo(jliArquivos, null);
    }   
    
    protected void adicionarArquivo(JList jliArquivos, String prontuario) {
        JFileChooser jfcArquivo = new JFileChooser();
        jfcArquivo.setAccessory(new ImagePreview(jfcArquivo));
        jfcArquivo.setMultiSelectionEnabled(true);
        if (prontuario != null && !prontuario.isEmpty()) {
            jfcArquivo.addChoosableFileFilter(new FiltroArquivosOrtoptica(prontuario));
            jfcArquivo.addChoosableFileFilter(new FiltroArquivosAvaliacaoFuncional(prontuario));
        }
        int opcao = jfcArquivo.showDialog(telaPai, "Selecione o arquivo");
        if (opcao == JFileChooser.APPROVE_OPTION){
            try {
                List<File> arquivos = Arrays.asList(jfcArquivo.getSelectedFiles());
                for (File arquivo : arquivos){
                    ArquivoDTO arquivoDTO = new ArquivoDTO(null, arquivo.getName(), FileUtils.readFileToByteArray(arquivo), null);
                    adicionarDtos((ModeloDTO) arquivoDTO, jliArquivos);
                }
            } catch (Exception ex) {
                logger.error(JOptionPanePersonalizado.ERRO_DURANTE_SELECAO_FOTO + "\n Detalhes: " + ex);
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_SELECAO_FOTO);
            }
        }
    }
    
    protected void abrirArquivo(JList jliArquivos, ArquivoDTO arquivoSelecionadoDto, ArquivoDTO arquivoArmazenadoDto) {
        try{
            if (arquivoSelecionadoDto.possuiConteudo()){
                abrirArquivo(gravarArquivoUsandoNomeArquivo(arquivoSelecionadoDto));
            }else{
                abrirArquivo(gravarArquivoUsandoNomeArquivo(arquivoArmazenadoDto));
            }
        }catch(Exception e){
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\nDetalhes: "+ e.getMessage());
        }
    }
        
    protected void carregarUFeMunicipio(JComboBox jcbUf, JComboBox jcbMunicipio, UfDTO ufDto, MunicipioDTO municipioDto){
        if (ufDto != null && municipioDto != null){
            jcbUf.setSelectedItem(ufDto);
            inicializarCampoMunicipio(jcbUf, jcbMunicipio);
            jcbMunicipio.setSelectedItem(municipioDto);
        }    
    }
    
    protected void carregarUFeMunicipio(JComboBox jcbUf, JComboBox jcbMunicipio, EnderecoDTO enderecoDto) {
        carregarUFeMunicipio(jcbUf, jcbMunicipio, enderecoDto.getUfDto(), enderecoDto.getMunicipioDto());
    }
    
    protected void carregarObservacoesHistoricas(InformacaoEssencialDTO informacaoEssencialDto, JEditorPane jepObservacoesHistoricas) {
        try {
            String resultado = servicoSisLaraServer.obterObservacoesHistoricasEspera(informacaoEssencialDto);
            jepObservacoesHistoricas.setText(resultado);
        } catch (Exception e) {
            logger.fatal("Error: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA);
        }
    }
    
    public void salvar() {
        prepararDtoParaEditar();
        int resposta = JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO);
        if (resposta == JOptionPane.OK_OPTION) {
            Runnable comando = new Runnable() {
                @Override
                public void run() {
                    try {
                        SomUtils.iniciar();
                        ResultadoDTO resultado = solicitarEdicaoDto();
                        SomUtils.parar();
                        if (resultado.sucesso()) {
                            servicoSisLaraServer.gravarTela(obterTela());
                            objetoDto = resultado.obterObjetoDtoEditado();
                            executarAcaoAposInclusao();
                            Sessao.obterInstancia().armazenarDTO(obterChaveSessao(), objetoDto);
                            JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultado.obterMensagens());
                            desbloquearDTOeFecharTela();
                        } else {
                            JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
                        }
                    } catch (Exception ex) {
                        JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
                        logger.fatal(ex);
                    }

                }
            };
            new EsperaUtils(comando, (JDialog) telaPai).execute();
        }
    }
    public void abrirSomenteSeHouverPermissaoEBloquearDTO(Bloqueavel objeto, PermissaoDeTelas permissaoExigida) {
        abrirSomenteSeHouverPermissaoEBloquearDTO(objeto.obterNome(), permissaoExigida);
    }    
    
    public void abrirSomenteSeHouverPermissaoEBloquearDTO(String nomeObjeto, PermissaoDeTelas permissaoExigida) {
        try {
            if (servicoSisLaraServer.possuiAutorizacao(Sessao.obterInstancia().obterToken(), permissaoExigida.toString())) {
                if (!nomeObjeto.isEmpty()) {
                    if (!servicoSisLaraServer.estaBloqueadoParaEdicao(nomeObjeto, Sessao.obterInstancia().obterToken())) {
                        bloquearParaEdicao(nomeObjeto);
                        abrirTela();
                    } else {
                        ResultadoCoordenacaoEdicaoDTO resultadoCoordenacaoEdicaoDTO = servicoSisLaraServer.obterContaAcessoEditando(nomeObjeto);
                        JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoCoordenacaoEdicaoDTO.obterMensagens());
                    }
                } else {
                    abrirTela();
                }
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.NAO_POSSUI_ACESSO_A_AREA);
            }
        } catch (Exception ex) {
            logger.fatal(JOptionPanePersonalizado.ERRO_NA_ABERTURA_DE_TELA + ex);
        }
    }

    protected void carregarEnderecoSeEstiverVazioOuDiferenteDoCEP(JFormattedTextField jftCep, JTextField jtfEndereco, JTextField jtfBairro, JComboBox jcbUF, JComboBox jcbMunicipio, JComboBox jcbPais) {
        try {
            ResultadoConsultaCEP resultadoConsultaCEP = servicoSisLaraServer.consultarEndereco(jftCep.getText());
            EnderecoCEPDTO enderecoCEPDTO = (EnderecoCEPDTO) resultadoConsultaCEP.obterObjetoDtoEditado();
            if (resultadoConsultaCEP.sucesso()) {
                if (jtfEndereco.getText().isEmpty()) {
                    atualizarEndereco(enderecoCEPDTO, jftCep, jtfEndereco, jtfBairro, jcbUF, jcbMunicipio, jcbPais);
                } else {
                    if (!jtfEndereco.getText().toUpperCase().equals(enderecoCEPDTO.getEndereco().toUpperCase())) {
                        if (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO_ATUALIZA_ENDERECO_APARTIR_CEP) == JOptionPane.OK_OPTION) {
                            atualizarEndereco(enderecoCEPDTO, jftCep, jtfEndereco, jtfBairro, jcbUF, jcbMunicipio, jcbPais);
                        }
                    }
                }
            } 
        } catch (Exception ex) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_CARREGAMENTO_DO_ENDERECO + "\n Detalhes: " + ex);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_CARREGAMENTO_DO_ENDERECO);
        }
    }
    
    private void atualizarEndereco(EnderecoCEPDTO enderecoCEPDTO, JFormattedTextField jftCep, JTextField jtfEndereco, JTextField jtfBairro, JComboBox jcbUF, JComboBox jcbMunicipio, JComboBox jcbPais) {
        jtfEndereco.setText(enderecoCEPDTO.getEndereco());
        jtfBairro.setText(enderecoCEPDTO.getBairro());
        selecionarDto(jcbUF, enderecoCEPDTO.getUfDto());
        selecionarDto(jcbMunicipio, enderecoCEPDTO.getMunicipioDto());
        selecionarDto(jcbPais, enderecoCEPDTO.getPaisDto());
    }
    
    public void adicionarParticipacaoDetalhada(JComboBox jcbParticipacaoSelecionada, JTextField jtfQuantidadePessoas, JList jliParticipacoesAdicionadas) {
        ParticipacaoDetalhadaDTO participacaoDetalhadaDTO = new ParticipacaoDetalhadaDTO();
        participacaoDetalhadaDTO.setParticipacaoDto((ParticipacaoDTO)obterDtoSelecionado(jcbParticipacaoSelecionada));
        participacaoDetalhadaDTO.setQuantidade(jtfQuantidadePessoas.getText());
        try {
            ResultadoValidacaoParticipacaoDetalhadaDTO resultadoValidacao = servicoSisLaraServer.validarParticipacaoDetalhada(participacaoDetalhadaDTO);
            if (resultadoValidacao.sucesso()) {
                if (!existe(jliParticipacoesAdicionadas, participacaoDetalhadaDTO)) {
                    adicionarDtoSemRepetir(participacaoDetalhadaDTO, jliParticipacoesAdicionadas);
                    deselecionarDto(jcbParticipacaoSelecionada);
                    jtfQuantidadePessoas.setText("");
                }else{
                    JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_ITEM_EXISTENTE);
                }
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoValidacao.obterMensagens());
            }
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_ADICAO_PARTICIPACAO_DETALHADA + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_ADICAO_PARTICIPACAO_DETALHADA);
        }
    }
    
    public void removerParticipacaoDetalhadaSelecionada(JList jliParticipacoesAdicionadas) {
        if ((estaComItemValidoSelecionado(jliParticipacoesAdicionadas) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliParticipacoesAdicionadas);
        }
    }
        
    protected abstract ResultadoDTO solicitarEdicaoDto() throws Exception;
            
    protected abstract void prepararDtoParaEditar();
    
    protected abstract boolean verificarSeDtoEstaPreenchido();
    
    protected abstract void executarAcaoAposInclusao();
}

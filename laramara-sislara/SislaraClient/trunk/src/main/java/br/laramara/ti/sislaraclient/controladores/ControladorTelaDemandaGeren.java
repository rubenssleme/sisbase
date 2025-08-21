package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaDemandaCaptar;
import br.laramara.ti.sislaraclient.telas.TelaDemandaEditar;
import br.laramara.ti.sislaraclient.telas.TelaDemandaVisualizacaoDocumento;
import br.laramara.ti.sislaraclient.telas.TelaDoacaoEditar;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarPreCadastroSemValidacao;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarUsuario;
import br.laramara.ti.sislaraclient.utilitarios.EsperaUtils;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.DemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoGeracaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemStatusDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoProrrogacaoCartelaDeSelosDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.StatusDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaDemandaGeren extends ControladorTelaPesquisarBase{
    
    private UsuarioDTO usuarioDto;
    private PreCadastroDTO preCadastroDto;
    private ResultadoListagemRecursoDTO resultadoListagemRecursoDto;
    private ResultadoListagemStatusDemandaDTO resultadoListatemStatusDemandaDto;
    
    private JComboBox jcbStatus;
    private JComboBox jcbRecurso;
    private JTextField jtfProntuario;
    private JFormattedTextField jftCpf;
    private JTable jtaDemandas;
    
    public ControladorTelaDemandaGeren(JDialog telaPai, JComboBox jcbStatus, JComboBox jcbRecurso, JTextField jtfProntuario, JFormattedTextField jftCpf, JTable jtaDemandas) {
        super(telaPai, jtaDemandas);
        this.jcbStatus = jcbStatus;
        this.jcbRecurso = jcbRecurso;
        this.jtfProntuario = jtfProntuario;
        this.jftCpf = jftCpf;
        this.jtaDemandas = jtaDemandas;
        configurarColunasTabela();
        inicializarCombosDadosAuxiliares();
    }
    
    private void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaDemandas);
    }
    
    public void novaDemanda() {
       new TelaDemandaEditar((JDialog)telaPai);
       String chave = Sessao.CHAVE_LISTAGEM_DEMANDAS;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            ResultadoGeracaoDemandaDTO resultado = (ResultadoGeracaoDemandaDTO) Sessao.obterInstancia().obterDto(chave);
            adicionarDtos(resultado.getObjetosDto(), jtaDemandas);
        }
    }

    private void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbStatus, resultadoListatemStatusDemandaDto.getObjetosDto());
        adicionarDtos(jcbRecurso, resultadoListagemRecursoDto.getObjetosDto());
    }

    private void obterDadosAuxiliares() {
        try {
            resultadoListagemRecursoDto = servicoSisLaraServer.obterListagemRecurso();
            resultadoListatemStatusDemandaDto = servicoSisLaraServer.obterListagemStatusDemanda();            
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    protected ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacaoPesquisaDto) throws Exception {
        return servicoSisLaraServer.obterListagemDemanda((EspecificacaoPesquisaDemandaDTO)especificacaoPesquisaDto);
    }

    public void efetuarPesquisa() {
        final EspecificacaoPesquisaDemandaDTO especificacao = new EspecificacaoPesquisaDemandaDTO();
        especificacao.setStatusDemandaDto((StatusDemandaDTO)obterDtoSelecionado(jcbStatus));
        especificacao.setRecursoDto((RecursoDTO)obterDtoSelecionado(jcbRecurso));
        especificacao.setProntuario(jtfProntuario.getText());
        especificacao.setPreCadastroDto(preCadastroDto);
        especificacao.setCpf(jftCpf.getText());
        Runnable comando = new Runnable() {
            @Override
            public void run() {
                pesquisar(especificacao);
            }
        };
        new EsperaUtils(comando, (JDialog)telaPai).execute();
    }
    
    public void selecionarProntuario() {
        EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDto = new EspecificacaoPesquisaUsuarioDTO();
        especificacaoPesquisaUsuarioDto.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, jtfProntuario.getText());
        try {
            ResultadoListagemUsuarioDTO resultadoListagemUsuarioDto = servicoSisLaraServer.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDto);
            if (resultadoListagemUsuarioDto.sucesso()) {
                usuarioDto = resultadoListagemUsuarioDto.getObjetosDto().get(0);
                carregarCamposUsuario(usuarioDto);
            } else {
                desmarcarUsuario();
            }
        } catch (Exception e) {
            logger.fatal("Error: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA);
        }
    }
                
    private void carregarCamposUsuario(UsuarioDTO usuarioDto) {
        if (usuarioDto!=null){
            jtfProntuario.setText(usuarioDto.getId().toString());
         }else{
            limparDadosUsuario();
        }
    }
   
    private void limparDadosUsuario(){
        jtfProntuario.setText("");
        desmarcarUsuario();
    }
    
    private void desmarcarUsuario(){
        usuarioDto = null;
    }
    
    public void localizarUsuario() {
        new TelaUtilizarUsuario((JDialog) telaPai);
        String chave = Sessao.CHAVE_USUARIO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            UsuarioDTO usuarioDTO = (UsuarioDTO) Sessao.obterInstancia().obterDto(chave);
            jtfProntuario.setText(usuarioDTO.getId().toString());
        }
        limparDadosPreCadastro();
    }

    public void localizarPreCadastro() {
        new TelaUtilizarPreCadastroSemValidacao((JDialog) telaPai);
        String chave = Sessao.CHAVE_PRE_CADASTRO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            preCadastroDto = (PreCadastroDTO) Sessao.obterInstancia().obterDto(chave);
            jftCpf.setText(preCadastroDto.getInformacaoEssencialDto().getCpf());
        }
        limparDadosUsuario();
    }
    
    private void limparDadosPreCadastro(){
        preCadastroDto = null;
        jftCpf.setText("");
    }        

    public void abrirTelaVisualizacaoDocumento() {
        new TelaDemandaVisualizacaoDocumento((JDialog)telaPai, (DemandaDTO) obterDtoSelecionado(jtaDemandas));
    }

    public void prorrogar() {
        try {
            if (estaComItemValidoSelecionado(jtaDemandas)) {
                if (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO) == JOptionPane.OK_OPTION) {
                    DemandaDTO demandaDto = (DemandaDTO) obterDtoSelecionado(jtaDemandas);
                    ResultadoProrrogacaoCartelaDeSelosDTO resultadoProrrogacaoDto = servicoSisLaraServer.prorrogar(demandaDto, Sessao.obterInstancia().obterToken());
                    if (resultadoProrrogacaoDto.sucesso()) {
                        servicoSisLaraServer.gravarTela(obterTela());
                        adicionarDtoNoItemSelecionado(jtaDemandas, (DemandaDTO) resultadoProrrogacaoDto.obterObjetoDtoEditado());
                        JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultadoProrrogacaoDto.obterMensagens());
                    } else {
                        JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoProrrogacaoDto.obterMensagens());
                    }
                }
            }
        } catch (Exception e) {
            logger.fatal("Error: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_PRORROGACAO);
        }
    }

    public void captar() {
        new TelaDemandaCaptar((JDialog) telaPai, (DemandaDTO) obterDtoSelecionado(jtaDemandas));
        String chave = Sessao.CHAVE_DEMANDA;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            adicionarDtoNoItemSelecionado(jtaDemandas, (ModeloDTO)Sessao.obterInstancia().obterDto(chave));
        }
    }

    public void alterarStatus() {
        new TelaDoacaoEditar((JDialog)telaPai, (DemandaDTO) obterDtoSelecionado(jtaDemandas));
        String chave = Sessao.CHAVE_DEMANDA;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            adicionarDtoNoItemSelecionado(jtaDemandas, (ModeloDTO)Sessao.obterInstancia().obterDto(chave));
        }
    }
}

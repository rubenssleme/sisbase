package br.laramara.ti.sislaraclient.controladores;

import static br.laramara.ti.sislaraclient.controladores.ControladorTela.logger;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDetalhadaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoValidacaoParticipacaoDetalhadaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemFrequenciaDTO;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class ControladorTelaGruposEditarDescricaoIndividual extends ControladorTelaEditar{

    private ResultadoListagemFrequenciaDTO resultadoListagemFrequenciaDto;
    private ResultadoListagemParticipacaoDTO resultadoListagemParticipacaoDto;
        
    protected GrupoDTO grupoDto;
    protected ModuloPeriodoDTO moduloPeriodoDto;
    protected AtendimentoGrupoDTO atendimentoDto;
    
    private JTextField jtfGrupo;
    private JTextField jtfModuloAtividade;
    private JTextField jtfData;
    private JTextField jtfHoraInicio;
    private JTextField jtfHoraTermino;
    protected JTextField jtfNome;
    private JTextArea jatDescricao;
    private JComboBox jcbFrequencia;
    private JTextArea jatJustificativa;
    private JList jliArquivos;
    private JPanel jpaParticipacao;
    private JComboBox jcbParticipacaoSelecionada;
    private JTextField jtfQuantidadePessoas;
    private JList jliParticipacoesAdicionadas;
        
    public ControladorTelaGruposEditarDescricaoIndividual(JDialog telaPai, GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto, AtendimentoGrupoDTO atendimentoDto, AtendimentoBaseDTO atendimentoBaseDto, JTextField jtfGrupo, JTextField jtfModuloAtividade, JTextField jtfData, JTextField jtfHoraInicio, JTextField jtfHoraTermino, JTextField jtfNome, JPanel jpaParticipacao, JComboBox jcbParticipacaoSelecionada, JTextField jtfQuantidadePessoas, JList jliParticipacoesAdicionadas, JTextArea jatDescricao, JComboBox jcbFrequencia, JTextArea jatJustificativa, JList jliArquivos){
        super(telaPai, atendimentoBaseDto);
        this.grupoDto = grupoDto;
        this.moduloPeriodoDto = moduloPeriodoDto;
        this.atendimentoDto = atendimentoDto;
        this.jtfGrupo = jtfGrupo;
        this.jtfModuloAtividade = jtfModuloAtividade;
        this.jtfData = jtfData;
        this.jtfHoraInicio = jtfHoraInicio;
        this.jtfHoraTermino = jtfHoraTermino;
        this.jtfNome = jtfNome;
        this.jatDescricao = jatDescricao;
        this.jcbFrequencia = jcbFrequencia;
        this.jatJustificativa = jatJustificativa;
        this.jliArquivos = jliArquivos;
        this.jpaParticipacao = jpaParticipacao;
        this.jcbParticipacaoSelecionada = jcbParticipacaoSelecionada;
        this.jtfQuantidadePessoas = jtfQuantidadePessoas;
        this.jliParticipacoesAdicionadas = jliParticipacoesAdicionadas;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
        esconderParticipacao();
    }

    private void esconderParticipacao(){
        jpaParticipacao.setVisible(false);
    }
    
    protected void exibirParticipacao(){
        jpaParticipacao.setVisible(true);
    }
    
    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().getInformacaoAtendimentoDto().setDescricao(jatDescricao.getText());
        obterObjetoDTO().getInformacaoAtendimentoDto().setFrequenciaDto((FrequenciaDTO)obterDtoSelecionado(jcbFrequencia));
        obterObjetoDTO().getInformacaoAtendimentoDto().setJustificativa(jatJustificativa.getText());
        obterObjetoDTO().getInformacaoAtendimentoDto().setParticipadaoDetalhadaDto((List<ParticipacaoDetalhadaDTO>) obterDtos(jliParticipacoesAdicionadas));
        obterObjetoDTO().setArquivos((List<ArquivoDTO>)obterDtos(jliArquivos));
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
        adicionarDtos(jcbFrequencia, resultadoListagemFrequenciaDto.getObjetosDto());
        adicionarDtos(jcbParticipacaoSelecionada, resultadoListagemParticipacaoDto.getObjetosDto());
    }
    
    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemFrequenciaDto = obterListagemFrequencia();
            resultadoListagemParticipacaoDto = servicoSisLaraServer.obterListagemParticipacao();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    protected abstract ResultadoListagemFrequenciaDTO obterListagemFrequencia() throws RemoteException;

    @Override
    public void carregarCampos() {
        jtfGrupo.setText(grupoDto.toStringApenasNomeETurma());
        jtfModuloAtividade.setText(moduloPeriodoDto.toString());
        jtfData.setText(atendimentoDto.getData());
        jtfHoraInicio.setText(atendimentoDto.getHorarioDto().getHoraInicio());
        jtfHoraTermino.setText(atendimentoDto.getHorarioDto().getHoraTermino());
        jatDescricao.setText(obterObjetoDTO().getInformacaoAtendimentoDto().getDescricao());
        jatJustificativa.setText(obterObjetoDTO().getInformacaoAtendimentoDto().getJustificativa());
        selecionarDto(jcbFrequencia, obterObjetoDTO().getInformacaoAtendimentoDto().getFrequenciaDto());
        adicionarDtos(obterObjetoDTO().getInformacaoAtendimentoDto().getParticipacaoDetalhadaDto(), jliParticipacoesAdicionadas);
        adicionarDtos(obterObjetoDTO().getArquivos(), jliArquivos);
    }
    
    protected AtendimentoBaseDTO obterObjetoDTO(){
        return (AtendimentoBaseDTO)objetoDto;
    }

    public void abrirArquivo(){
        ArquivoDTO arquivoSelecionadoDto = (ArquivoDTO) obterDtoSelecionado(jliArquivos);
        try {
            abrirArquivo(jliArquivos, arquivoSelecionadoDto, !arquivoSelecionadoDto.possuiConteudo() ? servicoSisLaraServer.obterArquivoAtendimentoUsuarioDTO((AtendimentoUsuarioDTO) obterObjetoDTO(), arquivoSelecionadoDto) : null);
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\nDetalhes: " + e.getMessage());
        }
    }
    
    public void adicionarArquivo() {
        adicionarArquivo(jliArquivos);
    }

    public void removerArquivo() {
        removerDtoSelecionado(jliArquivos);
    }

    public void adicionarParticipacaoDetalhada() {
         adicionarParticipacaoDetalhada(jcbParticipacaoSelecionada, jtfQuantidadePessoas, jliParticipacoesAdicionadas);
    }

    public void removerParticipacaoDetalhadaSelecionada() {
        removerArquivoSelecionado(jliParticipacoesAdicionadas);
    }

    public void inicializarQuantidadeDePessoasPadrao() {
        if (jtfQuantidadePessoas.getText().isEmpty()){
            jtfQuantidadePessoas.setText("1");
        }
    }
}

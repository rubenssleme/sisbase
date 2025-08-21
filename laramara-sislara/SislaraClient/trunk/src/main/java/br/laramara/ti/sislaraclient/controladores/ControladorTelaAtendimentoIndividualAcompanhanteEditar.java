package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaUtilizarPreCadastroSemValidacao;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoComunidadeDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemTipoVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.TipoVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemFrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ControladorTelaAtendimentoIndividualAcompanhanteEditar extends ControladorTelaEditar{
    
    private ResultadoListagemTipoVinculoDTO resultadoListagemTipoVinculoDto;
    private ResultadoListagemFrequenciaDTO resultadoListagemFrequenciaDto;
    
    private JTextField jtfNome;
    private JComboBox jcbFormacao;
    private JTextArea jatDescricao;
    private JTextArea jatJustificativa;
    private JComboBox jcbFrequencia;
    
    private PreCadastroDTO preCadastroDto;
    
    public ControladorTelaAtendimentoIndividualAcompanhanteEditar(JDialog telaPai, AtendimentoComunidadeDTO atendimentoComunidadeDto, JTextField jtfNome, JComboBox jcbFormacao, JTextArea jatDescricao, JComboBox jcbFrequencia, JTextArea jatJustificativa){
        super(telaPai, atendimentoComunidadeDto);
        this.jtfNome = jtfNome;
        this.jcbFormacao = jcbFormacao;
        this.jatDescricao = jatDescricao;
        this.jcbFrequencia = jcbFrequencia;
        this.jatJustificativa = jatJustificativa;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }
    
    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemTipoVinculoDto = servicoSisLaraServer.obterListagemTipoVinculo();
            resultadoListagemFrequenciaDto = servicoSisLaraServer.obterListagemFrequenciaDoUsuario();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbFormacao, resultadoListagemTipoVinculoDto.getObjetosDto());
        adicionarDtos(jcbFrequencia, resultadoListagemFrequenciaDto.getObjetosDto());
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoDTO) servicoSisLaraServer.validarAtendimentoAuxiliarBase(obterObjetoDTO());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setPreCadastroDto(preCadastroDto);
        obterObjetoDTO().setTipoVinculoDto((TipoVinculoDTO)obterDtoSelecionado(jcbFormacao));
        obterObjetoDTO().getInformacaoAtendimentoDto().setDescricao(jatDescricao.getText());
        obterObjetoDTO().getInformacaoAtendimentoDto().setJustificativa(jatJustificativa.getText());
        obterObjetoDTO().getInformacaoAtendimentoDto().setFrequenciaDto((FrequenciaDTO)obterDtoSelecionado(jcbFrequencia));
    }

    private AtendimentoComunidadeDTO obterObjetoDTO(){
        return (AtendimentoComunidadeDTO)objetoDto;
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
        return Sessao.CHAVE_ATENDIMENTO_COMUNIDADE;
    }

    @Override
    public void carregarCampos() {
        carregarCamposPreCadastro(obterObjetoDTO().getPreCadastroDto());
        selecionarDto(jcbFormacao, obterObjetoDTO().getTipoVinculoDto());
        selecionarDto(jcbFrequencia, obterObjetoDTO().getInformacaoAtendimentoDto().getFrequenciaDto());
        jatDescricao.setText(obterObjetoDTO().getInformacaoAtendimentoDto().getDescricao());
        jatJustificativa.setText(obterObjetoDTO().getInformacaoAtendimentoDto().getJustificativa()); 
    }

    public void abrirTelaUtilizarPreCadastro() {
         new TelaUtilizarPreCadastroSemValidacao((JDialog)telaPai);
         atualizaCamposPreCadastro();
    }
    
    private void atualizaCamposPreCadastro(){
        String chave = Sessao.CHAVE_PRE_CADASTRO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            preCadastroDto = (PreCadastroDTO)Sessao.obterInstancia().obterDto(chave);
            carregarCamposPreCadastro(preCadastroDto);
        }
    }
    
    private void carregarCamposPreCadastro(PreCadastroDTO preCadastroDto) {
        if (preCadastroDto != null) {
            jtfNome.setText(preCadastroDto.getInformacaoEssencialDto().getNome());
            this.preCadastroDto = preCadastroDto;
        }
    }
}

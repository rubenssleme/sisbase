package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TelaUtils;
import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemTipoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUfDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public final class ControladorTelaEditarPreCadastro extends ControladorTelaEditar{

    private ResultadoListagemTipoTelefoneDTO resultadoListagemTipoTelefoneDto;
    private ResultadoListagemUfDTO resultadoListagemUfDto;
    private ControladorTelefones controladorTelefone;
        
    private JTextField jtfDataPreCadastro;
    private JTextField jtfNome;
    private JFormattedTextField jtfRG;
    private JFormattedTextField jftCPF;
    private JFormattedTextField jftDataNascimento;
    private JComboBox jcbTipoTelefone;
    private JList jliTelefonesAdicionados;
    private JFormattedTextField jftRamal;
    private JTextField jtfNomeContato;
    private JTextField jtfEmail;
    private JEditorPane jepObservacoes;
    private JComboBox jcbUf;
    private JComboBox jcbMunicipio;
        
    public ControladorTelaEditarPreCadastro(JDialog telaPai, PreCadastroDTO preCadastroDTO, JTextField jtfDataPreCadastro, JTextField jtfNome, JFormattedTextField jtfRG,  JFormattedTextField jftCPF, JFormattedTextField jftDataNascimento, JComboBox jcbTipoTelefone, JFormattedTextField jftTelefone, JList jliTelefonesAdicionados, JFormattedTextField jftRamal, JTextField jtfNomeContato, JTextField jtfEmail, JEditorPane jepObsservacoes, JComboBox jcbUf, JComboBox jcbMunicipio){
        super(telaPai, preCadastroDTO);
        this.jtfDataPreCadastro = jtfDataPreCadastro;
        this.jtfNome = jtfNome;
        this.jtfRG = jtfRG;
        this.jftCPF = jftCPF;
        this.jftDataNascimento = jftDataNascimento;
        this.jftRamal = jftRamal;
        this.jtfNomeContato = jtfNomeContato;
        this.jtfEmail = jtfEmail;
        this.jcbTipoTelefone = jcbTipoTelefone;
        this.jliTelefonesAdicionados = jliTelefonesAdicionados;
        this.jepObservacoes = jepObsservacoes;
        this.jcbUf = jcbUf;
        this.jcbMunicipio = jcbMunicipio;
        controladorTelefone = new ControladorTelefones(telaPai, jcbTipoTelefone, jftTelefone, jliTelefonesAdicionados);
        inicializarCombosDadosAuxiliares();
        carregarCampos();
        TelaUtils.habilitarSomenteUpperCaseNosCamposTexto(this);
    }
    
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.editarPreCadastro(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        ContatoDTO contatoDto = obterObjetoDTO().getInformacaoEssencialDto().getContatoDto();
        contatoDto.setRamal(jftRamal.getText());
        contatoDto.setNomeContato(jtfNomeContato.getText());
        contatoDto.setEmail(jtfEmail.getText());
        
        obterObjetoDTO().getInformacaoEssencialDto().setNome(jtfNome.getText());
        obterObjetoDTO().getInformacaoEssencialDto().setRg(jtfRG.getText());
        obterObjetoDTO().getInformacaoEssencialDto().setCpf(jftCPF.getText());
        obterObjetoDTO().getInformacaoEssencialDto().setDataNascimento(jftDataNascimento.getText());
        obterObjetoDTO().getInformacaoEssencialDto().setContatoDto(contatoDto);
        obterObjetoDTO().setObs(jepObservacoes.getText());
        
        obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().setTelefonesDto((List<TelefoneDTO>) obterDtos(jliTelefonesAdicionados));        
        obterObjetoDTO().getInformacaoEssencialDto().getEnderecoDto().setMunicipioDto((MunicipioDTO) obterDtoSelecionado(jcbMunicipio));
        obterObjetoDTO().getInformacaoEssencialDto().getEnderecoDto().setUfDto((UfDTO) obterDtoSelecionado(jcbUf));
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        return obterObjetoDTO().getId() != null;
    }

    @Override
    protected void executarAcaoAposInclusao() {
        jtfDataPreCadastro.setText(obterObjetoDTO().getDataCadastro());
    }
        
    @Override
    public void carregarCampos() {
        if (verificarSeDtoEstaPreenchido()) {
            jtfDataPreCadastro.setText(obterObjetoDTO().getDataCadastro());
            jtfNome.setText(obterObjetoDTO().getInformacaoEssencialDto().getNome());
            jtfRG.setText(obterObjetoDTO().getInformacaoEssencialDto().getRg());
            jftCPF.setText(obterObjetoDTO().getInformacaoEssencialDto().getCpf());
            jftDataNascimento.setText(obterObjetoDTO().getInformacaoEssencialDto().getDataNascimento());
            jftRamal.setText(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getRamal());
            jtfNomeContato.setText(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getNomeContato());
            jtfEmail.setText(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getEmail());
            jepObservacoes.setText(obterObjetoDTO().getObs());

            adicionarDtos(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getTelefonesDto(), jliTelefonesAdicionados);
            carregarUFeMunicipio(jcbUf, jcbMunicipio, obterObjetoDTO().getInformacaoEssencialDto().getEnderecoDto());
        }
    }
    
    private PreCadastroDTO obterObjetoDTO() {
        return (PreCadastroDTO) objetoDto;
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_PRE_CADASTRO;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbTipoTelefone, resultadoListagemTipoTelefoneDto.getObjetosDto());
        adicionarDtos(jcbUf, resultadoListagemUfDto.getObjetosDto());
    }
  
    @Override
    public void obterDadosAuxiliares() {
        try{
            resultadoListagemTipoTelefoneDto = servicoSisLaraServer.obterListagemTipoTelefone();
            resultadoListagemUfDto = servicoSisLaraServer.obterListagemUf();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    public void adicionarTelefone() {
        controladorTelefone.adicionarTelefone();
    }

    public void removerTelefoneSelecionado() {
        if ((estaComItemValidoSelecionado(jliTelefonesAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            controladorTelefone.removerTelefoneSelecionado();
        }
    }
}

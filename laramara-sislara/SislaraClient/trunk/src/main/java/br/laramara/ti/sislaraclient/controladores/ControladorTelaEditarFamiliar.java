package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaraclient.utilitarios.TelaUtils;
import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemTipoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.FamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.InformacaoTrabalhoDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.ParentescoDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.ResultadoListagemParentescoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EstadoCivilDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemEstadoCivilDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemStatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;

public final class ControladorTelaEditarFamiliar extends ControladorTelaEditar {
    
    private ResultadoListagemEstadoCivilDTO resultadoListagemEstadoCivilDTO;
    private ResultadoListagemParentescoDTO resultadoListagemParentescoDTO;
    private ResultadoListagemTipoTelefoneDTO resultadoListagemTipoTelefoneDTO;
    private ResultadoListagemStatusDTO resultadoListagemStatusDTO;
    
    private ControladorTelefones controladorTelefones;
    private ControladorInformacoesEducacionais controladorInformacoesEducacionais;
    
    private JComboBox jcbFamiliarParentesco;
    private JTextField jtfFamiliarNome;
    private JFormattedTextField jftFamiliarDataNascimento;
    private JCheckBox jchFamiliarPrincipalResponsavel;
    private JComboBox jcbStatus;
    private JComboBox jcbFamiliarEstadoCivil;
    private JFormattedTextField jtfFamiliarRG;
    private JTextField jftFamiliarCpf;
    private JTextField jtfFamiliarNomeEmpresa;
    private JTextField jtfFamiliarFuncao;
    private JTextField jtfFamiliarRendaFamiliar;
    private JFormattedTextField jftFamiliarRamal;
    private JTextField jtfFamiliarNomeContato;
    private JTextField jtfFamiliarEmail;
    private JComboBox jcbTipoTelefone;
    private JList jliTelefonesAdicionados;
    private JCheckBox jchFamiliarNaoAlfabetizado;
    private JTable jtaInformacoesEducacionaisAdicionadas;

    public ControladorTelaEditarFamiliar(JDialog telaPai, FamiliarDTO familiarDto, JComboBox jcbFamiliarParentesco, JTextField jtfFamiliarNome, JFormattedTextField jftFamiliarDataNascimento, JCheckBox jchFamiliarPrincipalResponsavel, JComboBox jcbStatus, JComboBox jcbFamiliarEstadoCivil, JFormattedTextField jtfFamiliarRG, JTextField jftFamiliarCpf, JTextField jtfFamiliarNomeEmpresa, JTextField jtfFamiliarFuncao, JTextField jtfFamiliarRendaFamiliar, JFormattedTextField jftFamiliarRamal, JTextField jtfFamiliarNomeContato, JTextField jtfFamiliarEmail, JComboBox jcbTipoTelefone, JFormattedTextField jftTelefone, JList jliTelefonesAdicionados, JCheckBox jchFamiliarNaoAlfabetizado, JTable jtaInformacoesEducacionaisAdicionadas) {
        super(telaPai, familiarDto);
        this.jcbFamiliarParentesco = jcbFamiliarParentesco;
        this.jtfFamiliarNome = jtfFamiliarNome;
        this.jftFamiliarDataNascimento = jftFamiliarDataNascimento;
        this.jchFamiliarPrincipalResponsavel = jchFamiliarPrincipalResponsavel;
        this.jcbStatus = jcbStatus;
        this.jcbFamiliarEstadoCivil = jcbFamiliarEstadoCivil;
        this.jtfFamiliarRG = jtfFamiliarRG;
        this.jftFamiliarCpf = jftFamiliarCpf;
        this.jtfFamiliarNomeEmpresa = jtfFamiliarNomeEmpresa;
        this.jtfFamiliarFuncao = jtfFamiliarFuncao;
        this.jtfFamiliarRendaFamiliar = jtfFamiliarRendaFamiliar;
        this.jftFamiliarRamal = jftFamiliarRamal;
        this.jtfFamiliarNomeContato = jtfFamiliarNomeContato;
        this.jtfFamiliarEmail = jtfFamiliarEmail;
        this.jcbTipoTelefone = jcbTipoTelefone;
        this.jliTelefonesAdicionados = jliTelefonesAdicionados;
        this.jchFamiliarNaoAlfabetizado = jchFamiliarNaoAlfabetizado;
        this.jtaInformacoesEducacionaisAdicionadas = jtaInformacoesEducacionaisAdicionadas;
        controladorTelefones = new ControladorTelefones(telaPai, jcbTipoTelefone, jftTelefone, jliTelefonesAdicionados);
        controladorInformacoesEducacionais = new ControladorInformacoesEducacionais(telaPai, jtaInformacoesEducacionaisAdicionadas);
        inicializarCombosDadosAuxiliares();
        carregarCampos();
        configurarColunasTabela();
        TelaUtils.habilitarSomenteUpperCaseNosCamposTexto(this);
    }
    
    public void configurarColunasTabela(){
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaInformacoesEducacionaisAdicionadas);
    }
    
    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemEstadoCivilDTO = servicoSisLaraServer.obterListagemEstadoCivil();
            resultadoListagemParentescoDTO = servicoSisLaraServer.obterListagemParentesco();
            resultadoListagemTipoTelefoneDTO = servicoSisLaraServer.obterListagemTipoTelefone();
            resultadoListagemStatusDTO = servicoSisLaraServer.obterListagemStatusFamiliar();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai,JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoDTO) servicoSisLaraServer.validarFamiliar(obterObjetoDTO());
    }

    private FamiliarDTO obterObjetoDTO(){
        return (FamiliarDTO)objetoDto;
    }
        
    @Override
    protected void prepararDtoParaEditar() {
        FamiliarDTO familiarDto = obterObjetoDTO();
                
        familiarDto.setParentescoDto((ParentescoDTO)obterDtoSelecionado(jcbFamiliarParentesco));
        familiarDto.setDataNascimento(jftFamiliarDataNascimento.getText());
        familiarDto.setPrincipalResponsavel(estaMarcado(jchFamiliarPrincipalResponsavel));
        familiarDto.setStatusDto((StatusDTO)obterDtoSelecionado(jcbStatus));
        familiarDto.setEstadoCivilDto((EstadoCivilDTO)obterDtoSelecionado(jcbFamiliarEstadoCivil));
        familiarDto.setCpf(jftFamiliarCpf.getText());
        familiarDto.setRenda(jtfFamiliarRendaFamiliar.getText());
        
        InformacaoEssencialDTO informacaoEssencialDto = obterObjetoDTO().getInformacaoEssencialDto();
        informacaoEssencialDto.setNome(jtfFamiliarNome.getText());
        informacaoEssencialDto.setRg(jtfFamiliarRG.getText());
        
        InformacaoTrabalhoDTO informacaoTrabalhoDto = obterObjetoDTO().getInformacaoTrabalhoDto();
        informacaoTrabalhoDto.setEmpresa(jtfFamiliarNomeEmpresa.getText());
        informacaoTrabalhoDto.setFuncao(jtfFamiliarFuncao.getText());
        
        ContatoDTO contatoDto = obterObjetoDTO().getInformacaoEssencialDto().getContatoDto();
        contatoDto.setRamal(jftFamiliarRamal.getText());
        contatoDto.setNomeContato(jtfFamiliarNomeContato.getText());
        contatoDto.setEmail(jtfFamiliarEmail.getText());

        familiarDto.setNaoAlfabetizado(estaMarcado(jchFamiliarNaoAlfabetizado));
        obterObjetoDTO().setInformacoesEducacionaisDto((List<InformacaoEducacionalDTO>) obterDtos(jtaInformacoesEducacionaisAdicionadas));

        familiarDto.setInformacaoTrabalhoDto(informacaoTrabalhoDto);
        familiarDto.getInformacaoEssencialDto().setContatoDto(contatoDto);
        
        obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().setTelefonesDto((List<TelefoneDTO>) obterDtos(jliTelefonesAdicionados));        
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
        adicionarDtos(jcbFamiliarParentesco, resultadoListagemParentescoDTO.getObjetosDto());
        adicionarDtos(jcbFamiliarEstadoCivil, resultadoListagemEstadoCivilDTO.getObjetosDto());
        adicionarDtos(jcbTipoTelefone, resultadoListagemTipoTelefoneDTO.getObjetosDto());
        adicionarDtos(jcbStatus, resultadoListagemStatusDTO.getObjetosDto());
    }

    @Override
    public void carregarCampos() {
        jchFamiliarPrincipalResponsavel.setSelected(obterObjetoDTO().isPrincipalResponsavel());
        selecionarDto(jcbFamiliarParentesco, obterObjetoDTO().getParentescoDto());
        jtfFamiliarNome.setText(obterObjetoDTO().getInformacaoEssencialDto().getNome());
        jftFamiliarDataNascimento.setText(obterObjetoDTO().getDataNascimento());
        selecionarDto(jcbFamiliarEstadoCivil, obterObjetoDTO().getEstadoCivilDto());
        selecionarDto(jcbStatus, obterObjetoDTO().getStatusDto());
        jtfFamiliarRG.setText(obterObjetoDTO().getInformacaoEssencialDto().getRg());
        jftFamiliarCpf.setText(obterObjetoDTO().getCpf());
        jtfFamiliarNomeEmpresa.setText(obterObjetoDTO().getInformacaoTrabalhoDto().getEmpresa());
        jtfFamiliarFuncao.setText(obterObjetoDTO().getInformacaoTrabalhoDto().getFuncao());
        jftFamiliarRamal.setText(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getRamal());
        jtfFamiliarNomeContato.setText(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getNomeContato());
        jtfFamiliarEmail.setText(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getEmail());
        jtfFamiliarNomeEmpresa.setText(obterObjetoDTO().getInformacaoTrabalhoDto().getEmpresa());
        jtfFamiliarFuncao.setText(obterObjetoDTO().getInformacaoTrabalhoDto().getFuncao());
        jtfFamiliarRendaFamiliar.setText(obterObjetoDTO().getRenda());
        jchFamiliarNaoAlfabetizado.setSelected(obterObjetoDTO().isNaoAlfabetizado());
        adicionarDtos(obterObjetoDTO().getInformacoesEducacionaisDto(), jtaInformacoesEducacionaisAdicionadas);
        adicionarDtos(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getTelefonesDto(), jliTelefonesAdicionados);
    }
        
    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_FAMILIAR;
    }

    public void adicionarTelefone() {
         controladorTelefones.adicionarTelefone();
    }

    public void removerTelefoneSelecionado() {
        controladorTelefones.removerTelefoneSelecionado();
    }

    public void abrirTelaDeAlteracaoInformacaoEducacional(KeyEvent evt) {
        controladorInformacoesEducacionais.abrirTelaDeAlteracaoInformacaoEducacional(evt);
    }

    public void adicionarInformacaoEducacional() {
        controladorInformacoesEducacionais.adicionarInformacaoEducacional();
    }

    public void alterarInformacaoEducacional() {
        controladorInformacoesEducacionais.alterarInformacaoEducacional();
    }

    public void removerInformacaoEducacionalSelecionada() {
        controladorInformacoesEducacionais.removerInformacaoEducacionalSelecionada();
    }
}
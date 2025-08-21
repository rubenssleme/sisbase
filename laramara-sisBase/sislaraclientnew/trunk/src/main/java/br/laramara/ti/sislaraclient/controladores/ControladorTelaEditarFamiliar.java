package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaEditarPeriodoBeneficio;
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
import br.laramara.ti.sislaracommons.dtos.trabalho.CargoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.ResultadoListagemCargoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EstadoCivilDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.GeneroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemEstadoCivilDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemGeneroDTO;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public final class ControladorTelaEditarFamiliar extends ControladorTelaEditar {
    
    private ResultadoListagemEstadoCivilDTO resultadoListagemEstadoCivilDTO;
    private ResultadoListagemParentescoDTO resultadoListagemParentescoDTO;
    private ResultadoListagemTipoTelefoneDTO resultadoListagemTipoTelefoneDTO;
    private ResultadoListagemStatusDTO resultadoListagemStatusDTO;
    private ResultadoListagemGeneroDTO resultadoListagemGeneroDTO;
    private ResultadoListagemCargoDTO resultadoListagemCargoDTO;
    
    private ControladorTelefones controladorTelefones;
    private ControladorInformacoesEducacionais controladorInformacoesEducacionais;
    
    private JComboBox jcbFamiliarParentesco;
    private JTextField jtfFamiliarNome;
    private JFormattedTextField jftFamiliarDataNascimento;
    private JCheckBox jchFamiliarPrincipalResponsavel;
    private JCheckBox jchMoraNaCasa;
    private JComboBox jcbStatus;
    private JComboBox jcbFamiliarEstadoCivil;
    private JComboBox jcbGenero;
    private JFormattedTextField jtfFamiliarRG;
    private JTextField jftFamiliarCpf;
    private JTextField jtfFamiliarNomeEmpresa;
    private JTextField jtfFamiliarFuncao;
    private JComboBox jcbFamiliarProfissao;
    private JCheckBox jchResponsavelPelaAvaliacaoSocial;
    private JCheckBox jchAcompanhante;
    private JCheckBox jchResponsavelPeloUsuario;
    private JCheckBox jchParadeiroIgnorado;
    private JTextField jtfFamiliarRendaFamiliar;
    private JFormattedTextField jftFamiliarRamal;
    private JTextField jtfFamiliarNomeContato;
    private JTextField jtfFamiliarEmail;
    private JComboBox jcbTipoTelefone;
    private JList jliTelefonesAdicionados;
    private JCheckBox jchFamiliarNaoAlfabetizado;
    private JTable jtaInformacoesEducacionaisAdicionadas;
    private JList jliPeriodoBeneficiosAdicionados;

    public ControladorTelaEditarFamiliar(JDialog telaPai, FamiliarDTO familiarDto, JScrollPane jspFamiliar, JPanel jpaFamiliar, JComboBox jcbFamiliarParentesco, JTextField jtfFamiliarNome, JFormattedTextField jftFamiliarDataNascimento, JCheckBox jchFamiliarPrincipalResponsavel, JCheckBox jchMoraNaCasa, JComboBox jcbStatus, JComboBox jcbFamiliarEstadoCivil, JComboBox jcbGenero, JFormattedTextField jtfFamiliarRG, JTextField jftFamiliarCpf, JTextField jtfFamiliarNomeEmpresa, JTextField jtfFamiliarFuncao, JComboBox jcbFamiliarProfissao, JCheckBox jchResponsavelPelaAvaliacaoSocial, JCheckBox jchAcompanhante, JCheckBox jchResponsavelPeloUsuario, JCheckBox jchParadeiroIgnorado, JTextField jtfFamiliarRendaFamiliar, JFormattedTextField jftFamiliarRamal, JTextField jtfFamiliarNomeContato, JTextField jtfFamiliarEmail, JComboBox jcbTipoTelefone, JFormattedTextField jftTelefone, JList jliTelefonesAdicionados, JCheckBox jchFamiliarNaoAlfabetizado, JTable jtaInformacoesEducacionaisAdicionadas, JList jliPeriodoBeneficiosAdicionados) {
        super(telaPai, familiarDto);
        this.jcbFamiliarParentesco = jcbFamiliarParentesco;
        this.jtfFamiliarNome = jtfFamiliarNome;
        this.jftFamiliarDataNascimento = jftFamiliarDataNascimento;
        this.jchFamiliarPrincipalResponsavel = jchFamiliarPrincipalResponsavel;
        this.jchMoraNaCasa = jchMoraNaCasa;
        this.jcbStatus = jcbStatus;
        this.jcbFamiliarEstadoCivil = jcbFamiliarEstadoCivil;
        this.jcbGenero = jcbGenero;
        this.jtfFamiliarRG = jtfFamiliarRG;
        this.jftFamiliarCpf = jftFamiliarCpf;
        this.jtfFamiliarNomeEmpresa = jtfFamiliarNomeEmpresa;
        this.jtfFamiliarFuncao = jtfFamiliarFuncao;
        this.jcbFamiliarProfissao = jcbFamiliarProfissao;
        this.jchResponsavelPelaAvaliacaoSocial = jchResponsavelPelaAvaliacaoSocial;
        this.jchAcompanhante = jchAcompanhante;
        this.jchResponsavelPeloUsuario = jchResponsavelPeloUsuario;
        this.jchParadeiroIgnorado = jchParadeiroIgnorado;
        this.jtfFamiliarRendaFamiliar = jtfFamiliarRendaFamiliar;
        this.jftFamiliarRamal = jftFamiliarRamal;
        this.jtfFamiliarNomeContato = jtfFamiliarNomeContato;
        this.jtfFamiliarEmail = jtfFamiliarEmail;
        this.jcbTipoTelefone = jcbTipoTelefone;
        this.jliTelefonesAdicionados = jliTelefonesAdicionados;
        this.jchFamiliarNaoAlfabetizado = jchFamiliarNaoAlfabetizado;
        this.jtaInformacoesEducacionaisAdicionadas = jtaInformacoesEducacionaisAdicionadas;
        this.jliPeriodoBeneficiosAdicionados = jliPeriodoBeneficiosAdicionados;
        controladorTelefones = new ControladorTelefones(telaPai, jcbTipoTelefone, jftTelefone, jliTelefonesAdicionados);
        controladorInformacoesEducacionais = new ControladorInformacoesEducacionais(telaPai, jtaInformacoesEducacionaisAdicionadas);
        TelaUtils.habilitarSensibilidadeMouse(jspFamiliar);
        TelaUtils.gerarEventoAutoFoco(jpaFamiliar);
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
            resultadoListagemGeneroDTO = servicoSisLaraServer.obterListagemGenero();
            resultadoListagemCargoDTO = servicoSisLaraServer.obterListagemCargo();
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
        familiarDto.setMoraNaCasa(estaMarcado(jchMoraNaCasa));
        familiarDto.setStatusDto((StatusDTO)obterDtoSelecionado(jcbStatus));
        familiarDto.setEstadoCivilDto((EstadoCivilDTO)obterDtoSelecionado(jcbFamiliarEstadoCivil));
        familiarDto.setGeneroDto((GeneroDTO)obterDtoSelecionado(jcbGenero));
        familiarDto.setCpf(jftFamiliarCpf.getText());
        familiarDto.setRenda(jtfFamiliarRendaFamiliar.getText());
        familiarDto.setResponsavelPelaAvaliacaoSocial(estaMarcado(jchResponsavelPelaAvaliacaoSocial));
        familiarDto.setAcompanhante(estaMarcado(jchAcompanhante));
        familiarDto.setResponsavelPeloUsuario(estaMarcado(jchResponsavelPeloUsuario));
        familiarDto.setParadeiroIgnorado(estaMarcado(jchParadeiroIgnorado));
        
        InformacaoEssencialDTO informacaoEssencialDto = obterObjetoDTO().getInformacaoEssencialDto();
        informacaoEssencialDto.setNome(jtfFamiliarNome.getText());
        informacaoEssencialDto.setRg(jtfFamiliarRG.getText());
        
        InformacaoTrabalhoDTO informacaoTrabalhoDto = obterObjetoDTO().getInformacaoTrabalhoDto();
        informacaoTrabalhoDto.setEmpresa(jtfFamiliarNomeEmpresa.getText());
        informacaoTrabalhoDto.setFuncao(jtfFamiliarFuncao.getText());
        informacaoTrabalhoDto.setCargoDto((CargoDTO)obterDtoSelecionado(jcbFamiliarProfissao));
        
        ContatoDTO contatoDto = obterObjetoDTO().getInformacaoEssencialDto().getContatoDto();
        contatoDto.setRamal(jftFamiliarRamal.getText());
        contatoDto.setNomeContato(jtfFamiliarNomeContato.getText());
        contatoDto.setEmail(jtfFamiliarEmail.getText());

        familiarDto.setNaoAlfabetizado(estaMarcado(jchFamiliarNaoAlfabetizado));
        obterObjetoDTO().setInformacoesEducacionaisDto((List<InformacaoEducacionalDTO>) obterDtos(jtaInformacoesEducacionaisAdicionadas));
        
        obterObjetoDTO().setPeriodoBeneficiosDto((List<PeriodoBeneficioDTO>) obterDtos(jliPeriodoBeneficiosAdicionados));

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
        adicionarDtos(jcbGenero, resultadoListagemGeneroDTO.getObjetosDto());
        adicionarDtos(jcbFamiliarProfissao, resultadoListagemCargoDTO.getObjetosDto());
    }

    @Override
    public void carregarCampos() {
        jchFamiliarPrincipalResponsavel.setSelected(obterObjetoDTO().isPrincipalResponsavel());
        jchMoraNaCasa.setSelected(obterObjetoDTO().isMoraNaCasa());
        selecionarDto(jcbFamiliarParentesco, obterObjetoDTO().getParentescoDto());
        jtfFamiliarNome.setText(obterObjetoDTO().getInformacaoEssencialDto().getNome());
        jftFamiliarDataNascimento.setText(obterObjetoDTO().getDataNascimento());
        selecionarDto(jcbFamiliarEstadoCivil, obterObjetoDTO().getEstadoCivilDto());
        selecionarDto(jcbGenero, obterObjetoDTO().getGeneroDto());
        selecionarDto(jcbStatus, obterObjetoDTO().getStatusDto());
        jtfFamiliarRG.setText(obterObjetoDTO().getInformacaoEssencialDto().getRg());
        jftFamiliarCpf.setText(obterObjetoDTO().getCpf());
        jtfFamiliarNomeEmpresa.setText(obterObjetoDTO().getInformacaoTrabalhoDto().getEmpresa());
        jtfFamiliarFuncao.setText(obterObjetoDTO().getInformacaoTrabalhoDto().getFuncao());
        selecionarDto(jcbFamiliarProfissao, obterObjetoDTO().getInformacaoTrabalhoDto().getCargoDto());
        jftFamiliarRamal.setText(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getRamal());
        jtfFamiliarNomeContato.setText(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getNomeContato());
        jtfFamiliarEmail.setText(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getEmail());
        jtfFamiliarNomeEmpresa.setText(obterObjetoDTO().getInformacaoTrabalhoDto().getEmpresa());
        jtfFamiliarFuncao.setText(obterObjetoDTO().getInformacaoTrabalhoDto().getFuncao());
        jtfFamiliarRendaFamiliar.setText(obterObjetoDTO().getRenda());
        jchFamiliarNaoAlfabetizado.setSelected(obterObjetoDTO().isNaoAlfabetizado());
        adicionarDtos(obterObjetoDTO().getInformacoesEducacionaisDto(), jtaInformacoesEducacionaisAdicionadas);
        adicionarDtos(obterObjetoDTO().getPeriodoBeneficiosDto(), jliPeriodoBeneficiosAdicionados);
        adicionarDtos(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getTelefonesDto(), jliTelefonesAdicionados);
        jchResponsavelPelaAvaliacaoSocial.setSelected(obterObjetoDTO().isResponsavelPelaAvaliacaoSocial());
        jchAcompanhante.setSelected(obterObjetoDTO().isAcompanhante());
        jchResponsavelPeloUsuario.setSelected(obterObjetoDTO().isResponsavelPeloUsuario());
        jchParadeiroIgnorado.setSelected(obterObjetoDTO().isParadeiroIgnorado());
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

    public void adicionarPeriodoBeneficio() {
        new TelaEditarPeriodoBeneficio((JDialog) telaPai, new PeriodoBeneficioDTO());
        String chave = Sessao.CHAVE_PERIODO_BENEFICIO;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            adicionarDtos((PeriodoBeneficioDTO) Sessao.obterInstancia().obterDto(chave), jliPeriodoBeneficiosAdicionados);
        }
    }

    public void alterarPeriodoBeneficioSelecionado() {
         if (estaComItemValidoSelecionado(jliPeriodoBeneficiosAdicionados)){
            new TelaEditarPeriodoBeneficio((JDialog) telaPai, (PeriodoBeneficioDTO)obterDtoSelecionado(jliPeriodoBeneficiosAdicionados));
            String chave = Sessao.CHAVE_PERIODO_BENEFICIO;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                incluirObjetoDto((PeriodoBeneficioDTO) Sessao.obterInstancia().obterDto(chave), jliPeriodoBeneficiosAdicionados);
            }
         }
    }

    public void removerPeriodoBeneficioSelecionado() {
        if ((estaComItemValidoSelecionado(jliPeriodoBeneficiosAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliPeriodoBeneficiosAdicionados);
        }
    }
}
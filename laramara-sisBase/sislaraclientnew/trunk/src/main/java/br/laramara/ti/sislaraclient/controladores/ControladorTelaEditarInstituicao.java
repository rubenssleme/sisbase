package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TelaUtils;
import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemTipoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoCEPDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.PaisDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ResultadoConsultaCEP;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ZonaDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DiretoriaEnsinoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DreCefaiDTO;
import br.laramara.ti.sislaracommons.dtos.escola.EscolaridadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemDiretoriaEnsinoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemDreCefaiDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemEscolaridadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemTipoApoioDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemTipoEspecialidadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.TipoApoioDTO;
import br.laramara.ti.sislaracommons.dtos.escola.TipoEspecialidadeDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ClassificacaoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemClassificacaoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemTipoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.TipoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemPaisDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUfDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemZonaDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public final class ControladorTelaEditarInstituicao extends ControladorTelaEditar {

    private ResultadoListagemTipoInstituicaoDTO resultadoListagemTipoInstituicaoDto;
    private ResultadoListagemClassificacaoInstituicaoDTO resultadoListagemClassificacaoInstituicaoDto;
    private ResultadoListagemUfDTO resultadoListagemUfDTO;
    private ResultadoListagemZonaDTO resultadoListagemZonaDTO;
    private ResultadoListagemPaisDTO resultadoListagemPaisDTO;
    private ResultadoListagemTipoTelefoneDTO resultadoListagemTipoTelefoneDTO;
    private ResultadoListagemEscolaridadeDTO resultadoListagemEscolaridadeDTO;
    private ResultadoListagemTipoEspecialidadeDTO resultadoListagemTipoEspecialidadeDTO;
    private ResultadoListagemTipoApoioDTO resultadoListagemTipoApoioDTO;
    private ResultadoListagemDreCefaiDTO resultadoListagemDreCefaiDTO;
    private ResultadoListagemDiretoriaEnsinoDTO resultadoListagemDiretoriaEnsinoDTO;
    
    private ControladorTelefones controladorTelefones;
    
    private JComboBox jcbTipo;
    private JComboBox jcbClassificacao;
    private JComboBox jcbZona;
    private JComboBox jcbUF;
    private JComboBox jcbMunicipio;   
    private JComboBox jcbPais;
    private JTextField jtfNome;
    private JFormattedTextField jftCep;
    private JTextField jtfEndereco;
    private JTextField jtfNumero;
    private JTextField jtfComplemento;
    private JTextField jtfBairro;
    private JFormattedTextField jftRamal;
    private JTextField jtfContato;
    private JTextField jtfCoordenadorResponsavel;
    private JTextField jtfEmail;
    private JComboBox jcbTipoTelefone;
    private JList jliTelefonesAdicionados;
    private JComboBox jcbNivelEscolar;
    private JList jliNivelEscolarAdicionados;
    private JComboBox jcbTipoApoio;
    private JComboBox jcbTipoEspecialidade;
    private JList jliTipoEspecialidadeAdicionados;
    private JComboBox jcbDreCefai;
    private JComboBox jcbDiretoriaEnsino;
    private JEditorPane jepObservacoes;

    public ControladorTelaEditarInstituicao(JDialog telaPai, InstituicaoDTO instituicaoDto, JComboBox jcbTipo, JComboBox jcbClassificacao, JComboBox jcbZona, JComboBox jcbUF, JComboBox jcbMunicipio, JComboBox jcbPais, JTextField jtfNome, JFormattedTextField jftCEP, JTextField jtfEndereco, JTextField jtfNumero, JTextField jtfComplemento, JTextField jtfBairro, JFormattedTextField jftRamal, JTextField jtfContato, JTextField jtfCoordenadorResponsavel, JTextField jtfEmail,  JComboBox jcbTipoTelefone, JFormattedTextField jftTelefone, JList jliTelefonesAdicionados, JComboBox jcbNivelEscolar, JList jliNivelEscolarAdicionados, JComboBox jcbTipoApoio, JComboBox jcbTipoEspecialidade, JList jliTipoEspecialidadeAdicionados, JComboBox jcbDreCefai, JComboBox jcbDiretoriaEnsino, JEditorPane jepObservacoes){
        super(telaPai, instituicaoDto);
        this.jcbTipo = jcbTipo;
        this.jcbClassificacao = jcbClassificacao;
        this.jcbZona = jcbZona;
        this.jcbUF = jcbUF;
        this.jcbMunicipio = jcbMunicipio;
        this.jcbPais = jcbPais;
        this.jtfNome = jtfNome;
        this.jftCep = jftCEP;
        this.jtfEndereco = jtfEndereco;
        this.jtfNumero = jtfNumero;
        this.jtfComplemento = jtfComplemento;
        this.jtfBairro = jtfBairro;
        this.jftRamal = jftRamal;
        this.jtfContato = jtfContato;
        this.jtfCoordenadorResponsavel = jtfCoordenadorResponsavel;
        this.jtfEmail = jtfEmail;
        this.jcbTipoTelefone = jcbTipoTelefone;
        this.jliTelefonesAdicionados = jliTelefonesAdicionados;
        this.jcbNivelEscolar = jcbNivelEscolar;
        this.jliNivelEscolarAdicionados = jliNivelEscolarAdicionados;
        this.jcbTipoApoio = jcbTipoApoio;
        this.jcbTipoEspecialidade = jcbTipoEspecialidade;
        this.jliTipoEspecialidadeAdicionados = jliTipoEspecialidadeAdicionados;
        this.jcbDreCefai = jcbDreCefai;
        this.jcbDiretoriaEnsino = jcbDiretoriaEnsino;
        this.jepObservacoes = jepObservacoes;
        controladorTelefones = new ControladorTelefones(telaPai, jcbTipoTelefone, jftTelefone, jliTelefonesAdicionados);
        inicializarCombosDadosAuxiliares();
        carregarCampos();
        TelaUtils.habilitarSomenteUpperCaseNosCamposTexto(this);
    }
    
    @Override
    public void inicializarCombosDadosAuxiliares(){
        obterDadosAuxiliares();
        adicionarDtos(jcbTipo, resultadoListagemTipoInstituicaoDto.getObjetosDto());
        adicionarDtos(jcbClassificacao, resultadoListagemClassificacaoInstituicaoDto.getObjetosDto());
        adicionarDtos(jcbZona, resultadoListagemZonaDTO.getObjetosDto());
        adicionarDtos(jcbUF, resultadoListagemUfDTO.getObjetosDto());
        adicionarDtos(jcbPais, resultadoListagemPaisDTO.getObjetosDto());
        adicionarDtos(jcbTipoTelefone, resultadoListagemTipoTelefoneDTO.getObjetosDto());
        adicionarDtos(jcbNivelEscolar, resultadoListagemEscolaridadeDTO.getObjetosDto());
        adicionarDtos(jcbTipoApoio, resultadoListagemTipoApoioDTO.getObjetosDto());
        adicionarDtos(jcbTipoEspecialidade, resultadoListagemTipoEspecialidadeDTO.getObjetosDto());
        adicionarDtos(jcbDreCefai, resultadoListagemDreCefaiDTO.getObjetosDto());
        adicionarDtos(jcbDiretoriaEnsino, resultadoListagemDiretoriaEnsinoDTO.getObjetosDto());
    }
    
    @Override
    public void obterDadosAuxiliares(){
        try {
            resultadoListagemTipoInstituicaoDto = servicoSisLaraServer.obterListagemTipoInstituicao();
            resultadoListagemClassificacaoInstituicaoDto = servicoSisLaraServer.obterListagemClassificacaoInstituicao();
            resultadoListagemZonaDTO = servicoSisLaraServer.obterListagemZona();
            resultadoListagemUfDTO = servicoSisLaraServer.obterListagemUf();
            resultadoListagemPaisDTO = servicoSisLaraServer.obterListagemPais();
            resultadoListagemTipoTelefoneDTO = servicoSisLaraServer.obterListagemTipoTelefone();
            resultadoListagemEscolaridadeDTO = servicoSisLaraServer.obterListagemEscolaridade();
            resultadoListagemTipoEspecialidadeDTO = servicoSisLaraServer.obterListagemTipoEspecialidade();
            resultadoListagemTipoApoioDTO = servicoSisLaraServer.obterListagemTipoApoio();
            resultadoListagemDreCefaiDTO = servicoSisLaraServer.obterListagemDreCefai();
            resultadoListagemDiretoriaEnsinoDTO = servicoSisLaraServer.obterListagemDiretoriaEnsino();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
   
    private InstituicaoDTO obterObjetoDTO(){
        return (InstituicaoDTO)objetoDto;
    }
        
    @Override
    public void carregarCampos() {
        if (verificarSeDtoEstaPreenchido()) {
            jcbTipo.setSelectedItem(obterObjetoDTO().getTipoInstituicaoDto());
            jcbClassificacao.setSelectedItem(obterObjetoDTO().getClassificacaoInstituicaoDto());
            jcbZona.setSelectedItem(obterObjetoDTO().getEnderecoDto().getZonaDto());
            jcbPais.setSelectedItem(obterObjetoDTO().getEnderecoDto().getPaisDto());
            jtfNome.setText(obterObjetoDTO().getNome());
            jftCep.setText(obterObjetoDTO().getEnderecoDto().getCep());
            jtfEndereco.setText(obterObjetoDTO().getEnderecoDto().getEndereco());
            jtfNumero.setText(obterObjetoDTO().getEnderecoDto().getNumero());
            jtfComplemento.setText(obterObjetoDTO().getEnderecoDto().getComplemento());
            jtfBairro.setText(obterObjetoDTO().getEnderecoDto().getBairro());
            carregarUFeMunicipio(jcbUF, jcbMunicipio, obterObjetoDTO().getEnderecoDto());
            jftRamal.setText(obterObjetoDTO().getContatoDto().getRamal());
            jtfContato.setText(obterObjetoDTO().getContatoDto().getNomeContato());
            jtfCoordenadorResponsavel.setText(obterObjetoDTO().getNomeCoordenadorResponsavel());
            jtfEmail.setText(obterObjetoDTO().getContatoDto().getEmail());            
            jepObservacoes.setText(obterObjetoDTO().getObs());
            
            adicionarDtos(obterObjetoDTO().getContatoDto().getTelefonesDto(), jliTelefonesAdicionados);
            adicionarDtos(obterObjetoDTO().getTiposEspecialidadeDTO(), jliTipoEspecialidadeAdicionados);
            adicionarDtos(obterObjetoDTO().getEscolaridadesDto(), jliNivelEscolarAdicionados);
            selecionarDto(jcbTipoApoio, obterObjetoDTO().getTipoApoioDto());
            selecionarDto(jcbDreCefai, obterObjetoDTO().getDreCefaiDto());
            selecionarDto(jcbDiretoriaEnsino, obterObjetoDTO().getDiretoriaEnsinoDto());
        }
    }
        
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.editarInstituicao(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        EnderecoDTO enderecoDto = obterObjetoDTO().getEnderecoDto();
        enderecoDto.setCep(jftCep.getText());
        enderecoDto.setEndereco(jtfEndereco.getText());
        enderecoDto.setNumero(jtfNumero.getText());
        enderecoDto.setComplemento(jtfComplemento.getText());
        enderecoDto.setZonaDto((ZonaDTO) obterDtoSelecionado(jcbZona));
        enderecoDto.setBairro(jtfBairro.getText());
        enderecoDto.setMunicipioDto((MunicipioDTO)obterDtoSelecionado(jcbMunicipio));
        enderecoDto.setUfDto((UfDTO)obterDtoSelecionado(jcbUF));
        enderecoDto.setPaisDto((PaisDTO)obterDtoSelecionado(jcbPais));

        ContatoDTO contatoDto = obterObjetoDTO().getContatoDto();
        contatoDto.setRamal(jftRamal.getText());
        contatoDto.setNomeContato(jtfContato.getText());
        contatoDto.setEmail(jtfEmail.getText());
        
        obterObjetoDTO().setClassificacaoInstituicaoDto((ClassificacaoInstituicaoDTO) obterDtoSelecionado(jcbClassificacao));
        obterObjetoDTO().setNome(jtfNome.getText());
        obterObjetoDTO().setNomeCoordenadorResponsavel(jtfCoordenadorResponsavel.getText());
        obterObjetoDTO().setObs(jepObservacoes.getText());
        obterObjetoDTO().setTipoInstituicaoDto((TipoInstituicaoDTO) obterDtoSelecionado(jcbTipo));
        obterObjetoDTO().setTipoApoioDto((TipoApoioDTO)obterDtoSelecionado(jcbTipoApoio));
        obterObjetoDTO().setTiposEspecialidadeDTO((List<TipoEspecialidadeDTO>) obterDtos(jliTipoEspecialidadeAdicionados));
        obterObjetoDTO().setEscolaridadesDto((List<EscolaridadeDTO>) obterDtos(jliNivelEscolarAdicionados));
        obterObjetoDTO().setDreCefaiDto((DreCefaiDTO) obterDtoSelecionado(jcbDreCefai));
        obterObjetoDTO().setDiretoriaEnsinoDto((DiretoriaEnsinoDTO) obterDtoSelecionado(jcbDiretoriaEnsino));
        
        obterObjetoDTO().getContatoDto().setTelefonesDto((List<TelefoneDTO>) obterDtos(jliTelefonesAdicionados));        
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
       return obterObjetoDTO().getId() != null;
    }

    @Override
    protected void executarAcaoAposInclusao() {
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_INSTITUICAO;
    }

    public void adicionarTelefone() {
        controladorTelefones.adicionarTelefone();
    }

    public void removerTelefoneSelecionado() {
        controladorTelefones.removerTelefoneSelecionado();
    }

    public void adicionarTipoSaai() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliTipoEspecialidadeAdicionados, jcbTipoEspecialidade); 
    }

    public void removerTipoSaaiSelecionado() {
        if ((estaComItemValidoSelecionado(jliTipoEspecialidadeAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
          removerDtoSelecionado(jliTipoEspecialidadeAdicionados);
        }
    }

    public void adicionarNivelEscolarSelecionado() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliNivelEscolarAdicionados, jcbNivelEscolar);     
    }

    public void removerNivelEscolarSelecionado() {
        if ((estaComItemValidoSelecionado(jliNivelEscolarAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
          removerDtoSelecionado(jliNivelEscolarAdicionados);
        }
    }
    
    public void carregarEndereco() {
        carregarEnderecoSeEstiverVazioOuDiferenteDoCEP(jftCep, jtfEndereco, jtfBairro, jcbUF, jcbMunicipio, jcbPais);
    }
}

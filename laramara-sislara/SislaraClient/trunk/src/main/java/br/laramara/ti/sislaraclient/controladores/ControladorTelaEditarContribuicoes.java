
package br.laramara.ti.sislaraclient.controladores;

import static br.laramara.ti.sislaraclient.controladores.ControladorTela.logger;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemTipoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.MotivoDesativacaoDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoListagemMotivoDesativacaoDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoListagemStatusContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.StatusContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.PaisDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemPaisDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUfDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JTextField;


public class ControladorTelaEditarContribuicoes extends ControladorTelaEditar {

    private JTextField jtfCodigo; 
    private JTextField jtfDataCadastro;
    private JTextField jtfNomeEmpresa;
    private JFormattedTextField jftCep;
    private JTextField jtfEndereco;
    private JTextField jtfNumero;
    private JTextField jtfComplemento;
    private JTextField jtfBairro;
            
    private JComboBox jcbMunicipio;
    private JComboBox jcbUF;
    private JComboBox jcbPais;
    
    private JComboBox jcbTipoTelefone;
    private JList jliTelefonesAdicionados;
    
    private JFormattedTextField jftRamal;
    private JTextField jtfNomeContato;
    private JTextField jtfEmail;
    
    private JComboBox jcbStatus;
    private JTextField jtfContribuicao;
    private JComboBox jcbMotivoDesativacao;
    
    private ResultadoListagemUfDTO resultadoListagemUfDTO;
    private ResultadoListagemPaisDTO resultadoListagemPaisDTO;
    private ResultadoListagemTipoTelefoneDTO resultadoListagemTipoTelefoneDTO;
    private ResultadoListagemStatusContribuinteDTO resultadoListagemStatusContribuinteDto;
    private ResultadoListagemMotivoDesativacaoDTO resultadoListagemMotivoDesativacaoDto;
    
    private ControladorTelefones controladorTelefone;
            
    public ControladorTelaEditarContribuicoes(JDialog telaPai, ContribuinteDTO contribuinteDto, JTextField jtfCodigo, JTextField jtfDataCadastro, JTextField jtfNomeEmpresa, JFormattedTextField jftCep, JTextField jtfEndereco, JTextField jtfNumero, JTextField jtfComplemento, JTextField jtfBairro, JComboBox jcbMunicipio, JComboBox jcbUF, JComboBox jcbPais, JComboBox jcbTipoTelefone, JFormattedTextField jftTelefone, JList jliTelefonesAdicionados, JFormattedTextField jftRamal, JTextField jtfNomeContato, JTextField jtfEmail, JComboBox jcbStatus, JTextField jtfContribuicao, JComboBox jcbMotivoDesativacao){
        super(telaPai, contribuinteDto);
        this.jtfCodigo = jtfCodigo;
        this.jtfDataCadastro = jtfDataCadastro;
        this.jtfNomeEmpresa = jtfNomeEmpresa;
        this.jftCep = jftCep;
        this.jtfEndereco = jtfEndereco;
        this.jtfNumero = jtfNumero;
        this.jtfComplemento = jtfComplemento;
        this.jtfBairro = jtfBairro;
        this.jcbMunicipio = jcbMunicipio;
        this.jcbUF = jcbUF;
        this.jcbPais = jcbPais;
        this.jcbTipoTelefone = jcbTipoTelefone;
        this.jftRamal = jftRamal;
        this.jtfNomeContato = jtfNomeContato;
        this.jtfEmail = jtfEmail;
        this.jliTelefonesAdicionados = jliTelefonesAdicionados;
        this.controladorTelefone = new ControladorTelefones(telaPai, jcbTipoTelefone, jftTelefone, jliTelefonesAdicionados);
        this.jcbStatus = jcbStatus;
        this.jtfContribuicao = jtfContribuicao;
        this.jcbMotivoDesativacao = jcbMotivoDesativacao;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }
    
    @Override
    public void inicializarCombosDadosAuxiliares(){
        obterDadosAuxiliares();
        adicionarDtos(jcbUF, resultadoListagemUfDTO.getObjetosDto());
        adicionarDtos(jcbPais, resultadoListagemPaisDTO.getObjetosDto());
        adicionarDtos(jcbTipoTelefone, resultadoListagemTipoTelefoneDTO.getObjetosDto());
        adicionarDtos(jcbStatus, resultadoListagemStatusContribuinteDto.getObjetosDto());
        adicionarDtos(jcbMotivoDesativacao, resultadoListagemMotivoDesativacaoDto.getObjetosDto());
    }
    
    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemPaisDTO = servicoSisLaraServer.obterListagemPais();
            resultadoListagemUfDTO = servicoSisLaraServer.obterListagemUf();
            resultadoListagemTipoTelefoneDTO = servicoSisLaraServer.obterListagemTipoTelefone();
            resultadoListagemStatusContribuinteDto = servicoSisLaraServer.obterListagemStatusContribuinte();
            resultadoListagemMotivoDesativacaoDto = servicoSisLaraServer.obterListagemMotivoDesativacao();
        } catch (Exception e) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + e.getMessage());
        }
    }
   
    private ContribuinteDTO obterObjetoDTO(){
        return (ContribuinteDTO)objetoDto;
    }
        
  @Override
    public void carregarCampos() {
        if (verificarSeDtoEstaPreenchido()) {
            jtfCodigo.setText(obterObjetoDTO().getId().toString());
            jtfDataCadastro.setText(obterObjetoDTO().getDataCadastro());
            jtfNomeEmpresa.setText(obterObjetoDTO().getNomeEmpresa());
            jftCep.setText(obterObjetoDTO().getEnderecoDto().getCep());
            jtfEndereco.setText(obterObjetoDTO().getEnderecoDto().getEndereco());
            jtfNumero.setText(obterObjetoDTO().getEnderecoDto().getNumero());
            jtfComplemento.setText(obterObjetoDTO().getEnderecoDto().getComplemento());
            jtfBairro.setText(obterObjetoDTO().getEnderecoDto().getBairro());
            selecionarDto(jcbPais, obterObjetoDTO().getEnderecoDto().getPaisDto());
            carregarUFeMunicipio(jcbUF, jcbMunicipio, obterObjetoDTO().getEnderecoDto());
            adicionarDtos(obterObjetoDTO().getContatoDto().getTelefonesDto(), jliTelefonesAdicionados);
            jftRamal.setText(obterObjetoDTO().getContatoDto().getRamal());
            jtfNomeContato.setText(obterObjetoDTO().getContatoDto().getNomeContato());
            jtfEmail.setText(obterObjetoDTO().getContatoDto().getEmail());
            selecionarDto(jcbStatus, obterObjetoDTO().getStatusContribuinteDto());
            jtfContribuicao.setText(obterObjetoDTO().getContribuicao());
            selecionarDto(jcbMotivoDesativacao, obterObjetoDTO().getMotivoDesativacaoDTO());
        }
    }
    
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.editarContribuinte(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        EnderecoDTO enderecoDto = new EnderecoDTO();
        enderecoDto.setCep(jftCep.getText());
        enderecoDto.setEndereco(jtfEndereco.getText());
        enderecoDto.setNumero(jtfNumero.getText());
        enderecoDto.setComplemento(jtfComplemento.getText());
        enderecoDto.setBairro(jtfBairro.getText());
        enderecoDto.setMunicipioDto((MunicipioDTO) obterDtoSelecionado(jcbMunicipio));
        enderecoDto.setUfDto((UfDTO) obterDtoSelecionado(jcbUF));
        enderecoDto.setPaisDto((PaisDTO) obterDtoSelecionado(jcbPais));
        
        obterObjetoDTO().setEnderecoDto(enderecoDto);
        
        ContatoDTO contatoDto = obterObjetoDTO().getContatoDto();
        contatoDto.setTelefonesDto((List<TelefoneDTO>) obterDtos(jliTelefonesAdicionados));
        contatoDto.setRamal(jftRamal.getText());
        contatoDto.setNomeContato(jtfNomeContato.getText());
        contatoDto.setEmail(jtfEmail.getText());
        
        obterObjetoDTO().setNomeEmpresa(jtfNomeEmpresa.getText());
        
        obterObjetoDTO().setStatusContribuinteDto((StatusContribuinteDTO)obterDtoSelecionado(jcbStatus));
        obterObjetoDTO().setMotivoDesativacaoDTO((MotivoDesativacaoDTO)obterDtoSelecionado(jcbMotivoDesativacao));
        obterObjetoDTO().setContribuicao(jtfContribuicao.getText());
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
        return Sessao.CHAVE_CONTRIBUINTE;
    }

    public void adicionarTelefone() {
        controladorTelefone.adicionarTelefone();
    }

    public void removerTelefoneSelecionado() {
        controladorTelefone.removerTelefoneSelecionado();
    }

    public void carregarEndereco() {
        carregarEnderecoSeEstiverVazioOuDiferenteDoCEP(jftCep, jtfEndereco, jtfBairro, jcbUF, jcbMunicipio, jcbPais);
    }
}

package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.infra.PermissaoDeTelas;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaFamiliar;
import br.laramara.ti.sislaraclient.telas.TelaEditarCertidao;
import br.laramara.ti.sislaraclient.telas.TelaEditarComprometimento;
import br.laramara.ti.sislaraclient.telas.TelaEditarFamiliar;
import br.laramara.ti.sislaraclient.telas.TelaEditarInformacaoTrabalhoCompleta;
import br.laramara.ti.sislaraclient.telas.TelaEditarPeriodoBeneficio;
import br.laramara.ti.sislaraclient.telas.TelaEditarPeriodoDeficiencia;
import br.laramara.ti.sislaraclient.telas.TelaEditarPeriodoDoenca;
import br.laramara.ti.sislaraclient.telas.TelaUtilizarPreCadastroComValidacao;
import br.laramara.ti.sislaraclient.utilitarios.EsperaUtils;
import br.laramara.ti.sislaraclient.utilitarios.FiltroImagemJPG;
import br.laramara.ti.sislaraclient.utilitarios.ImagePreview;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.SomUtils;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaraclient.utilitarios.Tarefa;
import br.laramara.ti.sislaraclient.utilitarios.TelaUtils;
import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemSimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemTipoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.PaisDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ZonaDTO;
import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.FamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoListagemPermissaoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.InformacaoTrabalhoCompletaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.CertidaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.CondicaoMoradiaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ConvenioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.CustoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EstadoCivilDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ExpectativaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.GeneroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.GrupoEtnicoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.HabitacaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.InfraestruturaBasicaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ItemCustoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.NecessidadeDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.OrigemEncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoComprometimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ProntuarioEscaneadoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.RecursoMoradiaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.RedeEncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoEdicaoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemCondicaoMoradiaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemConvenioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemEstadoCivilDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemExpectativaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemGeneroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemGrupoEtnicoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemHabitacaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemInfraestruturaBasicaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemItensCustoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemNecessidadeDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemOrigemEncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemPaisDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemProntuarioEscaneadoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemRecursoMoradiaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemRedeEncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemServicoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemSituacaoGuardaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemSituacaoHabitacionalDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemStatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemTipoConstrucaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemTipoLeituraDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUfDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemZonaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoCustoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoEncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ServicoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SituacaoGuardaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SituacaoHabitacionalDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.TipoConstrucaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.TipoLeituraDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.ImagemUtils;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.awt.event.KeyEvent;
import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public final class ControladorTelaEditarUsuario extends ControladorTelaEditar implements TabelaConfiguravel, CamposDadosCarregavel {

    private ResultadoListagemStatusDTO resultadoListagemStatusDTO;
    private ResultadoListagemGeneroDTO resultadoListagemGeneroDTO;
    private ResultadoListagemUfDTO resultadoListagemUfDTO;
    private ResultadoListagemEstadoCivilDTO resultadoListagemEstadoCivilDTO;
    private ResultadoListagemZonaDTO resultadoListagemZonaDTO;
    private ResultadoListagemPaisDTO resultadoListagemPaisDTO;
    private ResultadoListagemSituacaoGuardaDTO resultadoListagemSituacaoGuardaDTO;    
    private ResultadoListagemConvenioDTO resultadoListagemConvenioDTO;
    private ResultadoListagemTipoTelefoneDTO resultadoListagemTipoTelefoneDTO;
    private ResultadoListagemGrupoEtnicoDTO resultadoListagemGrupoEtnicoDTO;
    private ResultadoListagemInstituicaoDTO resultadoListagemInstituicaoComSRMsDTO;
    private ResultadoListagemInstituicaoDTO resultadoListagemInstituicaoComSalaRecursoDTO;
    private ResultadoListagemInstituicaoDTO resultadoListagemInstituicaoComOutrosAEEDTO;
    private ResultadoListagemPermissaoDTO resultadoListagemPermissaoDto;
    private ResultadoListagemTipoLeituraDTO resultadoListagemTipoLeituraDTO;
    private ResultadoListagemRecursoDTO resultadoListagemRecursoDTO;
    private ResultadoListagemSimNaoDTO resultadoListagemSimNaoDTO;
    private ResultadoListagemItensCustoDTO resultadoListagemItensCustoDoencaDTO;
    private ResultadoListagemItensCustoDTO resultadoListagemItensCustoDeficienciaDTO;
    private ResultadoListagemOrigemEncaminhamentoDTO resultadoListagemOrigemEncaminhamentoDTO;
    private ResultadoListagemRedeEncaminhamentoDTO resultadoListagemRedeEncaminhamentoDTO;
    private ResultadoListagemServicoDTO resultadoListagemServicoDTO;
    private ResultadoListagemRecursoMoradiaDTO resultadoListagemRecursoMoradiaDTO;
    private ResultadoListagemNecessidadeDTO resultadoListagemNecessidadeDTO;
    private ResultadoListagemExpectativaDTO resultadoListagemExpectativaDTO;
    private ResultadoListagemCondicaoMoradiaDTO resultadoListagemCondicaoMoradiaDTO;
    private ResultadoListagemSituacaoHabitacionalDTO resultadoListagemSituacaoHabitacionalDTO;
    private ResultadoListagemHabitacaoDTO resultadoListagemHabitacaoDTO;
    private ResultadoListagemTipoConstrucaoDTO resultadoListagemTipoConstrucaoDTO;
    private ResultadoListagemInfraestruturaBasicaDTO resultadoListagemInfraestruturaBasicaDTO;
    private ControladorTelefones controladorTelefone;
    private ControladorInformacoesEducacionais controladorInformacoesEducacionais;
    
    private JButton jbuUtilizarPreCadastro;
    //Pessoais
    private JTextField jtfProntuario;
    private JTextField jtfDataCadastroServicoSocial;
    private JTextField jtfNome;
    private JTextField jtfClassificacaoSocial;
    private JComboBox jcbStatus;
    private JTextField jtfStatusUsuario;
    private JCheckBox jchSetorCTO;
    private JCheckBox jchSetorProceja;
    private JComboBox jcbGenero;
    private JFormattedTextField jftDataNascimento;
    private JTextField jtfNaturalidade;
    private JComboBox jcbUFRg;
    private JTextField jtfRg;
    private JFormattedTextField jftDataExpedicaoRG;
    private JTextField jtfEmissorRG;
    private JFormattedTextField jftCpf;
    private JComboBox jcbEstadoCivil;
    private JTextField jtfNacionalidade;
    private JFormattedTextField jftCep;
    private JTextField jtfEndereco;
    private JTextField jtfNumero;
    private JTextField jtfComplemento;
    private JComboBox jcbZona;
    private JTextField jtfBairro;
    private JComboBox jcbMunicipio;
    private JComboBox jcbUF;
    private JComboBox jcbPais;
    private JComboBox jcbGrupoEtnico;
    private JTextField jtfRenda;
    private JTextField jtfRendaTotalFamiliar;
    private JLabel jlaFoto;
    private JCheckBox jchFalecido;
    //Contatos
    private JComboBox jcbTipoTelefone;
    private JList jliTelefonesAdicionados;
    private JFormattedTextField jftRamal;
    private JTextField jtfNomeContato;
    private JTextField jtfEmail;
    //Escolar
    private JCheckBox jchNaoAlfabetizado;
    private JCheckBox jchNaoEstaNaEscola;
    private JComboBox jcbTipoLeitura;
    private JTextField jtfTamanhoFonte;
    private JComboBox jcbRecursoAAdicionar;
    private JList jliRecursosAdicionados;
    private JTable jtaInformacoesEducacionaisAdicionadas;
    private JComboBox jcbSaai;
    private JComboBox jcbSalaRecurso;
    private JComboBox jcbOutrosAEE;
    //Familiar
    private JCheckBox jchReponsavelPorSiMesmo;
    private JTable jtaFamiliares;
    //Servico Social
    private JComboBox jcbOrigemEncaminhamento;
    private JComboBox jcbRedeEncaminhamento;
    private JTextField jtfProfissionalLiberal;
    private JList jliEncaminhamentosAdicionados;
    private JComboBox jcbTipoAtendimentoServico;
    private JList jliTiposAtendimentosServicosAdicionados;
    private JComboBox jcbRecursoProximoAMoradia;
    private JList jliRecursosProximosAMoradiaAdicionados;
    private JComboBox jcbNecessidade;
    private JList jliNecessidadesAdicionadas;
    private JComboBox jcbExpectativa;
    private JList jliExpectativasAdicionadas;
    private JComboBox jcbSituacaoGuarda;
    private JList jliSituacaoGuardaAdicionadas;
    private JList jliPeriodoBeneficiosAdicionados;
    private JComboBox jcbCondicaoMoradia;
    private JComboBox jcbSituacaoHabitacional;
    private JComboBox jcbHabitacao;
    private JComboBox jcbTipoConstrucao;
    private JComboBox jcbInfraestruturaBasica;
    private JList jliInfraestruturaBasicaAdicionados;
    private JComboBox jcbConvenios;
    private JList jliConveniosAdicionados;
    private JTextField jtfOutrosApoiosServicos;
    private JList jliArquivosProntuariosEscaneados;
    //Deficiencia
    private JList jliPeriodoDeficienciasAdicionados;
    private JList jliPeriodoComprometimentosAdicionados;
    private JList jliPeriodoDoencasAdicionados;
    private JEditorPane jepCirurgias;
    private JEditorPane jepMedicamentos;
    private JComboBox jcbPossuiConsanguinidade;
    private JEditorPane jepConsanguinidade;
    private JComboBox jcbItemCustoDoencaAAdicionar;
    private JFormattedTextField jftValorCustoDoenca;
    private JList jliListagemCustosDoenca;
    private JComboBox jcbItemCustoDeficienciaAAdicionar;
    private JFormattedTextField jftValorCustoDeficiencia;
    private JList jliListagemCustosDeficiencia;
    private JCheckBox jchCadeiraDeRodas;
    private JCheckBox jchMultiplaDeficiencia;
    //Observações
    private JTextArea jatObs;
    private JTabbedPane jtpUsuario;
    //Certidao
    private JTable jtaCertidoesAdicionadas;
    //Trabalho
    private JComboBox jcbTrabalhandoAtualmente;
    private JTable jtaInformacaoTrabalhoCompleta;
    //Descricao
    private JTextArea jtaHistorico;
    private JTextArea jtaFuncionalidade;
    private JTextArea jtaReacaoFrenteADeficiencia;
    private JTextArea jtaReacaoFrenteADeficienciaFamilia;
    private JTextArea jtaRedeDeApoio;
    private JTextArea jtaRedeDeAmigos;
    private JTextArea jtaNamoroCasamentoSexualidade;
    private JTextArea jtaNecessidadeExpectativasQueixas;
    private JTextArea jtaEducacional;
    private JTextArea jtaComunicacao;
    private JTextArea jtaReligiaoLazerCulturaRotina;
    private JTextArea jtaParecer;
    private JTextArea jtaResumoAtendimentosPsicossocial;
    
    public ControladorTelaEditarUsuario(JDialog telaPai, UsuarioDTO usuarioDto, JScrollPane jspDeficienciaSaudeInterna, JPanel jpaDeficienciaSaudeInterna, JScrollPane jspDescricao, JPanel jpaDescricao, JScrollPane jspServicoSocial, JPanel jpaServicoSocial, JButton jbuUtilizarPreCadastro, JTextField jtfProntuario, JTextField jtfDataCadastroServicoSocial, JTextField jtfNome, JTextField jtfClassificacaoSocial, JComboBox jcbStatus, JTextField jtfStatusUsuario, JCheckBox jchSetorCTO, JCheckBox jchSetorProceja, JComboBox jcbGenero, JFormattedTextField jftDataNascimento, JTextField jtfNaturalidade, JComboBox jcbUFRg, JTextField jtfRg, JFormattedTextField jftDataExpedicaoRG, JTextField jtfEmissorRG, JFormattedTextField jftCpf, JComboBox jcbEstadoCivil, JTextField jtfNacionalidade, JFormattedTextField jftCep, JTextField jtfEndereco, JTextField jtfNumero, JTextField jtfComplemento, JComboBox jcbZona, JTextField jtfBairro, JComboBox jcbMunicipio, JComboBox jcbUF, JComboBox jcbPais, JComboBox jcbGrupoEtnico, JTextField jtfRenda, JTextField jtfRendaTotalFamiliar, JLabel jlaFoto, JCheckBox jchFalecido, JTextField jtfNomeRecado, JTextField jtfEmail, JComboBox jcbTipoTelefone, JFormattedTextField jftTelefone, JList jliTelefonesAdicionados, JFormattedTextField jftRamal, JCheckBox jchNaoAlfabetizado, JCheckBox jchNaoEstaNaEscola, JComboBox jcbTipoLeitura, JTextField jtfTamanhoFonte, JComboBox jcbRecursoAAdicionar, JList jliRecursosAdicionados, JCheckBox jchReponsavelPorSiMesmo, JTable jtaInformacoesEducacionaisAdicionadas, JComboBox jcbSaai, JComboBox jcbSalaRecurso, JComboBox jcbOutrosAEE, JTable jtaFamiliares, JComboBox jcbSituacaoGuarda, JList jliSituacaoGuardaAdicionada, JComboBox jcbCondicaoMoradia, JComboBox jcbSituacaoHabitacional, JComboBox jcbHabitacao, JComboBox jcbTipoConstrucao, JComboBox jcbInfraestruturaBasica, JList jliInfraestruturaBasicaAdicionados, JComboBox jcbOrigemEncaminhamento, JComboBox jcbRedeEncaminhamento, JTextField jtfProfissionalLiberal, JList jliEncaminhamentosAdicionados, JComboBox jcbTipoAtendimentoServico, JList jliTiposAtendimentosServicosAdicionados, JComboBox jcbRecursoProximoAMoradia, JList jliRecursosProximosAMoradia, JComboBox jcbNecessidade, JList jliNecessidadesAdicionadas, JComboBox jcbExpectativa, JList jliExpectativasAdicionadas, JList jliBeneficiosAdicionados,  JComboBox jcbConvenios, JList jliConveniosAdicionados, JTextField jtfOutrosApoiosServicos, JList jliArquivosProntuariosEscaneados, JList jliPeriodoDeficienciasAdicionados, JList jliPeriodoComprometimentoAdicionados, JList jliPeriodoDoencaAdicionados, JEditorPane jepCirurgias, JEditorPane jepMedicamentos, JComboBox jcbPossuiConsanguinidade, JEditorPane jepConsanguinidade, JComboBox jcbItemCustoDoencaAAdicionar, JFormattedTextField jftValorCustoDoenca, JList jliListagemCustosDoenca, JComboBox jcbItemCustoDeficienciaAAdicionar, JFormattedTextField jftValorCustoDeficiencia, JList jliListagemCustosDeficiencia, JTable jtaCertidoesAdicionadas, JCheckBox jchMultiplaDeficiencia, JCheckBox jchCadeiraDeRodas, JTextArea jatObs, JComboBox jcbTrabalhandoAtualmente, JTable jtaInformacaoTrabalhoCompleta, JTextArea jtaHistorico, JTextArea jtaFuncionalidade, JTextArea jtaReacaoFrenteADeficiencia, JTextArea jtaReacaoFrenteADeficienciaFamilia, JTextArea jtaRedeDeApoio, JTextArea jtaRedeDeAmigos, JTextArea jtaNamoroCasamentoSexualidade, JTextArea jtaNecessidadeExpectativasQueixas, JTextArea jtaEducacional, JTextArea jtaComunicacao, JTextArea jtaReligiaoLazerCulturaRotina, JTextArea jtaParecer, JTextArea jtaResumoAtendimentosPsicossocial, JTabbedPane jtpUsuario) {
        super(telaPai, usuarioDto);
        this.jbuUtilizarPreCadastro = jbuUtilizarPreCadastro;
        //Pessoais
        this.jtfProntuario = jtfProntuario;
        this.jtfDataCadastroServicoSocial = jtfDataCadastroServicoSocial;
        this.jtfNome = jtfNome;
        this.jtfClassificacaoSocial = jtfClassificacaoSocial;
        this.jcbStatus = jcbStatus;
        this.jtfStatusUsuario = jtfStatusUsuario;
        this.jchSetorCTO = jchSetorCTO;
        this.jchSetorProceja = jchSetorProceja;
        this.jcbGenero = jcbGenero;
        this.jftDataNascimento = jftDataNascimento;
        this.jtfNaturalidade = jtfNaturalidade;
        this.jcbUFRg = jcbUFRg;
        this.jtfRg = jtfRg;
        this.jftDataExpedicaoRG = jftDataExpedicaoRG;
        this.jtfEmissorRG = jtfEmissorRG;
        this.jftCpf = jftCpf;
        this.jcbEstadoCivil = jcbEstadoCivil;
        this.jtfNacionalidade = jtfNacionalidade;
        this.jftCep = jftCep;
        this.jtfEndereco = jtfEndereco;
        this.jtfNumero = jtfNumero;
        this.jtfComplemento = jtfComplemento;
        this.jcbZona = jcbZona;
        this.jtfBairro = jtfBairro;
        this.jcbMunicipio = jcbMunicipio;
        this.jcbUF = jcbUF;
        this.jcbPais = jcbPais;
        this.jcbGrupoEtnico = jcbGrupoEtnico;
        this.jtfRenda = jtfRenda;
        this.jtfRendaTotalFamiliar = jtfRendaTotalFamiliar;
        this.jlaFoto = jlaFoto;
        this.jtfNomeContato = jtfNomeRecado;
        this.jtfEmail = jtfEmail;
        this.jcbTipoTelefone = jcbTipoTelefone;
        this.jliTelefonesAdicionados = jliTelefonesAdicionados;
        this.jftRamal = jftRamal;
        this.jchFalecido = jchFalecido; 
        //Escolar
        this.jchNaoAlfabetizado = jchNaoAlfabetizado;
        this.jchNaoEstaNaEscola = jchNaoEstaNaEscola;
        this.jcbTipoLeitura = jcbTipoLeitura;
        this.jtfTamanhoFonte = jtfTamanhoFonte;
        this.jcbRecursoAAdicionar = jcbRecursoAAdicionar;
        this.jliRecursosAdicionados = jliRecursosAdicionados;
        this.jtaInformacoesEducacionaisAdicionadas = jtaInformacoesEducacionaisAdicionadas;
        this.jcbSaai = jcbSaai;
        this.jcbSalaRecurso = jcbSalaRecurso;
        this.jcbOutrosAEE = jcbOutrosAEE;
        //Familiar
        this.jchReponsavelPorSiMesmo = jchReponsavelPorSiMesmo;
        this.jtaFamiliares = jtaFamiliares;
        //Servico Social
        this.jcbOrigemEncaminhamento = jcbOrigemEncaminhamento;
        this.jcbRedeEncaminhamento = jcbRedeEncaminhamento;
        this.jtfProfissionalLiberal = jtfProfissionalLiberal;
        this.jliEncaminhamentosAdicionados = jliEncaminhamentosAdicionados;
        this.jcbTipoAtendimentoServico = jcbTipoAtendimentoServico;
        this.jliTiposAtendimentosServicosAdicionados = jliTiposAtendimentosServicosAdicionados;
        this.jcbRecursoProximoAMoradia = jcbRecursoProximoAMoradia;
        this.jliRecursosProximosAMoradiaAdicionados = jliRecursosProximosAMoradia;
        this.jcbNecessidade = jcbNecessidade;
        this.jliNecessidadesAdicionadas = jliNecessidadesAdicionadas;
        this.jcbExpectativa = jcbExpectativa;
        this.jliExpectativasAdicionadas = jliExpectativasAdicionadas;
        this.jcbSituacaoGuarda = jcbSituacaoGuarda;
        this.jliSituacaoGuardaAdicionadas = jliSituacaoGuardaAdicionada;        
        this.jliPeriodoBeneficiosAdicionados = jliBeneficiosAdicionados;
        this.jliPeriodoComprometimentosAdicionados = jliPeriodoComprometimentoAdicionados;
        this.jcbCondicaoMoradia = jcbCondicaoMoradia;
        this.jcbSituacaoHabitacional = jcbSituacaoHabitacional;
        this.jcbHabitacao = jcbHabitacao;
        this.jcbTipoConstrucao = jcbTipoConstrucao;
        this.jcbInfraestruturaBasica = jcbInfraestruturaBasica;
        this.jliInfraestruturaBasicaAdicionados = jliInfraestruturaBasicaAdicionados;
        this.jcbConvenios = jcbConvenios;
        this.jliConveniosAdicionados = jliConveniosAdicionados;
        this.jtfOutrosApoiosServicos = jtfOutrosApoiosServicos;
        this.jliArquivosProntuariosEscaneados = jliArquivosProntuariosEscaneados;
        //Deficiencia
        this.jliPeriodoDeficienciasAdicionados = jliPeriodoDeficienciasAdicionados;
        this.jliPeriodoDoencasAdicionados = jliPeriodoDoencaAdicionados;
        this.jepCirurgias = jepCirurgias;
        this.jepMedicamentos = jepMedicamentos;
        this.jcbPossuiConsanguinidade = jcbPossuiConsanguinidade;
        this.jepConsanguinidade = jepConsanguinidade;
        this.jcbItemCustoDoencaAAdicionar = jcbItemCustoDoencaAAdicionar;
        this.jftValorCustoDoenca = jftValorCustoDoenca;
        this.jliListagemCustosDoenca = jliListagemCustosDoenca;
        this.jcbItemCustoDeficienciaAAdicionar = jcbItemCustoDeficienciaAAdicionar;
        this.jftValorCustoDeficiencia = jftValorCustoDeficiencia;
        this.jliListagemCustosDeficiencia = jliListagemCustosDeficiencia;
        this.jchMultiplaDeficiencia = jchMultiplaDeficiencia;
        this.jchCadeiraDeRodas = jchCadeiraDeRodas;
        //Observações
        this.jatObs = jatObs;
        this.jtpUsuario = jtpUsuario;
        //Certidoes
        this.jtaCertidoesAdicionadas = jtaCertidoesAdicionadas;
        //Trabalhando
        this.jcbTrabalhandoAtualmente = jcbTrabalhandoAtualmente;
        this.jtaInformacaoTrabalhoCompleta =  jtaInformacaoTrabalhoCompleta;
        controladorTelefone = new ControladorTelefones(telaPai, jcbTipoTelefone, jftTelefone, jliTelefonesAdicionados);
        controladorInformacoesEducacionais = new ControladorInformacoesEducacionais(telaPai, jtaInformacoesEducacionaisAdicionadas);
        //Descricao
        this.jtaHistorico = jtaHistorico;
        this.jtaFuncionalidade = jtaFuncionalidade;
        this.jtaReacaoFrenteADeficiencia = jtaReacaoFrenteADeficiencia;
        this.jtaReacaoFrenteADeficienciaFamilia = jtaReacaoFrenteADeficienciaFamilia;
        this.jtaRedeDeApoio = jtaRedeDeApoio;
        this.jtaRedeDeAmigos = jtaRedeDeAmigos;
        this.jtaNamoroCasamentoSexualidade = jtaNamoroCasamentoSexualidade;
        this.jtaNecessidadeExpectativasQueixas = jtaNecessidadeExpectativasQueixas;
        this.jtaEducacional = jtaEducacional;
        this.jtaComunicacao = jtaComunicacao;
        this.jtaReligiaoLazerCulturaRotina = jtaReligiaoLazerCulturaRotina;
        this.jtaParecer = jtaParecer;
        this.jtaResumoAtendimentosPsicossocial = jtaResumoAtendimentosPsicossocial;
        TelaUtils.habilitarSensibilidadeMouse(jspDescricao);
        TelaUtils.gerarEventoAutoFoco(jpaDescricao);
        TelaUtils.habilitarSensibilidadeMouse(jspDeficienciaSaudeInterna);
        TelaUtils.gerarEventoAutoFoco(jpaDeficienciaSaudeInterna);
        TelaUtils.habilitarSensibilidadeMouse(jspServicoSocial);
        TelaUtils.gerarEventoAutoFoco(jpaServicoSocial);
        inicializarCombosDadosAuxiliares();
        carregarCampos();
        configurarColunasTabela();
        habilitarBotaoUtilizacaoPreCadastro();
        configurarAbas();
        desabilitarItemFalecido();
        TelaUtils.habilitarSomenteUpperCaseNosCamposTexto(this);
    }

    private void configurarAbas(){
        List<PermissaoDTO> permissoes = resultadoListagemPermissaoDto.getObjetosDto();
        verificarAba("Pessoais", PermissaoDeTelas.USUARIO_TELA_EDICAO_VISUALIZAR_PESSOAIS, permissoes, jtpUsuario);
        verificarAba("Educacional", PermissaoDeTelas.USUARIO_TELA_EDICAO_VISUALIZAR_EDUCACIONAL, permissoes, jtpUsuario);
        verificarAba("Familiar", PermissaoDeTelas.USUARIO_TELA_EDICAO_VISUALIZAR_FAMILIAR, permissoes, jtpUsuario); 
        verificarAba("Servico Social", PermissaoDeTelas.USUARIO_TELA_EDICAO_VISUALIZAR_OUTRAS, permissoes, jtpUsuario); 
        verificarAba("Prontuário Antigo", PermissaoDeTelas.USUARIO_TELA_EDICAO_VISUALIZAR_PRONTUARIO_ANTIGO, permissoes, jtpUsuario);
        verificarAba("Observações", PermissaoDeTelas.USUARIO_TELA_EDICAO_VISUALIZAR_OBSERVACOES, permissoes, jtpUsuario); 
        verificarAba("Certidões", PermissaoDeTelas.USUARIO_TELA_EDICAO_VISUALIZAR_CERTIDAO, permissoes, jtpUsuario);
        verificarAba("Trabalho", PermissaoDeTelas.USUARIO_TELA_EDICAO_VISUALIZAR_TRABALHO, permissoes, jtpUsuario);
        verificarAba("Deficiência/Saúde", PermissaoDeTelas.USUARIO_TELA_EDICAO_VISUALIZAR_DEFICIENCIA, permissoes, jtpUsuario); 
        verificarAba("Descrição", PermissaoDeTelas.USUARIO_TELA_EDICAO_VISUALIZAR_DESCRICAO, permissoes, jtpUsuario);
    }
    
    @Override
    public void configurarColunasTabela(){
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaFamiliares);
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaInformacoesEducacionaisAdicionadas);
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaCertidoesAdicionadas);
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaInformacaoTrabalhoCompleta);
    }
    
    @Override
    public void obterDadosAuxiliares() {
        try {
            Collection<Callable<Void>> tasks = new ArrayList<>();
            tasks.add(new Tarefa(new Runnable() {
                @Override
                public void run() {
                    try {
                        resultadoListagemPermissaoDto = servicoSisLaraServer.obterPermissoes(Sessao.obterInstancia().obterToken());
                        resultadoListagemStatusDTO = servicoSisLaraServer.obterListagemStatus();
                        resultadoListagemGeneroDTO = servicoSisLaraServer.obterListagemGenero();
                        resultadoListagemUfDTO = servicoSisLaraServer.obterListagemUf();
                        resultadoListagemEstadoCivilDTO = servicoSisLaraServer.obterListagemEstadoCivil();
                        resultadoListagemZonaDTO = servicoSisLaraServer.obterListagemZona();
                        resultadoListagemPaisDTO = servicoSisLaraServer.obterListagemPais();
                        resultadoListagemSituacaoGuardaDTO = servicoSisLaraServer.obterListagemSituacaoGuarda();
                        resultadoListagemConvenioDTO = servicoSisLaraServer.obterListagemConvenio();
                        resultadoListagemTipoTelefoneDTO = servicoSisLaraServer.obterListagemTipoTelefone();
                        resultadoListagemGrupoEtnicoDTO = servicoSisLaraServer.obterListagemGrupoEtnico();
                        resultadoListagemTipoLeituraDTO = servicoSisLaraServer.obterListagemTipoLeitura();
                        resultadoListagemRecursoDTO = servicoSisLaraServer.obterListagemRecurso();
                        resultadoListagemSimNaoDTO = servicoSisLaraServer.obterListagemSimNao();
                        resultadoListagemItensCustoDoencaDTO = servicoSisLaraServer.obterListagemItensCustoDoenca();
                        resultadoListagemItensCustoDeficienciaDTO = servicoSisLaraServer.obterListagemItensCustoDeficiencia();
                        resultadoListagemOrigemEncaminhamentoDTO = servicoSisLaraServer.obterListagemOrigemEncaminhamento();
                        resultadoListagemRedeEncaminhamentoDTO = servicoSisLaraServer.obterListagemRedeEncaminhamento();
                        resultadoListagemServicoDTO = servicoSisLaraServer.obterListagemServico();
                        resultadoListagemCondicaoMoradiaDTO = servicoSisLaraServer.obterListagemCondicaoMoradia();
                        resultadoListagemSituacaoHabitacionalDTO = servicoSisLaraServer.obterListagemSituacaoHabitacional();
                        resultadoListagemHabitacaoDTO = servicoSisLaraServer.obterListagemHabitacao();
                        resultadoListagemTipoConstrucaoDTO = servicoSisLaraServer.obterListagemTipoConstrucao();
                        resultadoListagemInfraestruturaBasicaDTO = servicoSisLaraServer.obterListagemInfraestruturaBasica();
                        resultadoListagemRecursoMoradiaDTO = servicoSisLaraServer.obterListagemRecursoProximoMoradia();
                        resultadoListagemNecessidadeDTO = servicoSisLaraServer.obterListagemNecessidade();
                        resultadoListagemExpectativaDTO = servicoSisLaraServer.obterListagemExpectativa();
                    } catch (Exception ex) {
                        erroObtencaoDadosAuxiliares(ex);
                    }
                }
            }));

            tasks.add(new Tarefa(new Runnable() {
                @Override
                public void run() {
                    try {
                        resultadoListagemInstituicaoComSalaRecursoDTO = servicoSisLaraServer.obterListagemInstituicaoComSalaRecurso();
                    } catch (Exception ex) {
                        erroObtencaoDadosAuxiliares(ex);
                    }
                }
            }));

            tasks.add(new Tarefa(new Runnable() {
                @Override
                public void run() {
                    try {
                        resultadoListagemInstituicaoComSRMsDTO = servicoSisLaraServer.obterListagemInstituicaoComSRMs(); 
                    } catch (Exception ex) {
                        erroObtencaoDadosAuxiliares(ex);
                    }
                }
            }));
            
            tasks.add(new Tarefa(new Runnable() {
                @Override
                public void run() {
                    try {
                        resultadoListagemInstituicaoComOutrosAEEDTO = servicoSisLaraServer.obterListagemInstituicaoComOutrosAEE(); 
                    } catch (Exception ex) {
                        erroObtencaoDadosAuxiliares(ex);
                    }
                }
            }));
            ExecutorService executor = Executors.newCachedThreadPool();
            executor.invokeAll(tasks);
            executor.shutdown();
        } catch (Exception ex) {
            logger.error("Erro durante execução de tarefas paralelas. \nDetalhes: " + ex);
        }
    }

    private void erroObtencaoDadosAuxiliares(Exception ex){
        JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
        logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
    }

    private UsuarioDTO obterObjetoDTO() {
        return (UsuarioDTO) objetoDto;
    }

    @Override
    public void carregarCampos() {
        if (verificarSeDtoEstaPreenchido()) {
            carregarCamposInformacaoEssencial(obterObjetoDTO().getInformacaoEssencialDto());
            jtfDataCadastroServicoSocial.setText(obterObjetoDTO().getDataCadastro());
            jtfProntuario.setText(obterObjetoDTO().getId().toString());
            jtfClassificacaoSocial.setText(obterObjetoDTO().getClassificacaoSocialDto() != null ? obterObjetoDTO().getClassificacaoSocialDto().toString() : "");
            selecionarDto(jcbStatus, obterObjetoDTO().getStatusDto());
            jtfStatusUsuario.setText(obterObjetoDTO().getStatusUsuarioAtualDto() != null ? obterObjetoDTO().getStatusUsuarioAtualDto().toString() : "");
            selecionarDto(jcbGenero, obterObjetoDTO().getGeneroDto());
            jftDataNascimento.setText(obterObjetoDTO().getInformacaoEssencialDto().getDataNascimento());
            jtfNaturalidade.setText(obterObjetoDTO().getNaturalidade());
            selecionarDto(jcbUFRg, obterObjetoDTO().getUfRgDto());
            jftDataExpedicaoRG.setText(obterObjetoDTO().getDataExpedicaoRg());
            jtfEmissorRG.setText(obterObjetoDTO().getOrgaoEmissorRg());
            jftCpf.setText(obterObjetoDTO().getInformacaoEssencialDto().getCpf());
            selecionarDto(jcbEstadoCivil, obterObjetoDTO().getEstadoCivilDto());
            jtfNacionalidade.setText(obterObjetoDTO().getNacionalidade());
            jftCep.setText(obterObjetoDTO().getInformacaoEssencialDto().getEnderecoDto().getCep());
            jtfEndereco.setText(obterObjetoDTO().getInformacaoEssencialDto().getEnderecoDto().getEndereco());
            jtfNumero.setText(obterObjetoDTO().getInformacaoEssencialDto().getEnderecoDto().getNumero());
            jtfComplemento.setText(obterObjetoDTO().getInformacaoEssencialDto().getEnderecoDto().getComplemento());
            selecionarDto(jcbZona, obterObjetoDTO().getInformacaoEssencialDto().getEnderecoDto().getZonaDto());
            jtfBairro.setText(obterObjetoDTO().getInformacaoEssencialDto().getEnderecoDto().getBairro());
            selecionarDto(jcbPais, obterObjetoDTO().getInformacaoEssencialDto().getEnderecoDto().getPaisDto());
            carregarUFeMunicipio(jcbUF, jcbMunicipio, obterObjetoDTO().getInformacaoEssencialDto().getEnderecoDto());
            jchSetorCTO.setSelected(obterObjetoDTO().isAssociadoAoSetorCTO());
            jchSetorProceja.setSelected(obterObjetoDTO().isAssociadoAoSetorProceja());
            selecionarDto(jcbGrupoEtnico, obterObjetoDTO().getGrupoEtnicoDto());
            jtfRenda.setText(obterObjetoDTO().getRenda());
            jtfRendaTotalFamiliar.setText(obterObjetoDTO().getRendaTotalFamiliar());
            atualizarCampoDeFoto(obterObjetoDTO().getFoto());
            
            jchFalecido.setSelected(obterObjetoDTO().isFalecido());
            jchNaoAlfabetizado.setSelected(obterObjetoDTO().isNaoAlfabetizado());
            jchNaoEstaNaEscola.setSelected(obterObjetoDTO().isNaoEstaNaEscola());
            jchReponsavelPorSiMesmo.setSelected(obterObjetoDTO().isResponsavelPorSiMesmo());
            adicionarDtos(obterObjetoDTO().getInformacoesEducacionaisDto(), jtaInformacoesEducacionaisAdicionadas);
            selecionarDto(jcbSaai, obterObjetoDTO().getInstituicaoComSRMsDto());
            selecionarDto(jcbSalaRecurso, obterObjetoDTO().getInstituicaoComSalaRecursoDto());
            selecionarDto(jcbOutrosAEE, obterObjetoDTO().getInstituicaoComOutrosAEE());
            adicionarDtos(obterObjetoDTO().getFamiliaresDto(), jtaFamiliares);
            adicionarDtos(obterObjetoDTO().getSituacoesGuardaDto(), jliSituacaoGuardaAdicionadas);
            adicionarDtos(obterObjetoDTO().getPeriodoBeneficiosDto(), jliPeriodoBeneficiosAdicionados);
            adicionarDtos(obterObjetoDTO().getPeriodoDeficienciaDto(), jliPeriodoDeficienciasAdicionados);
            adicionarDtos(obterObjetoDTO().getPeriodoComprometimentoDto(), jliPeriodoComprometimentosAdicionados);
            adicionarDtos(obterObjetoDTO().getPeriodoDoencasDto(), jliPeriodoDoencasAdicionados);
            adicionarDtos(obterObjetoDTO().getCertidoes(), jtaCertidoesAdicionadas);
            jchMultiplaDeficiencia.setSelected(obterObjetoDTO().isMultiplaDeficiencia());
            jchCadeiraDeRodas.setSelected(obterObjetoDTO().isCadeiraDeRodas());
            selecionarDto(jcbCondicaoMoradia, obterObjetoDTO().getCondicaoMoradiaDto());
            selecionarDto(jcbSituacaoHabitacional, obterObjetoDTO().getSituacaoHabitacionalDto());
            selecionarDto(jcbHabitacao, obterObjetoDTO().getHabitacaoDto());
            selecionarDto(jcbTipoConstrucao, obterObjetoDTO().getTipoConstrucaoDto());
            adicionarDtos(obterObjetoDTO().getInfraestruturaBasicaDtos(), jliInfraestruturaBasicaAdicionados);
            adicionarDtos(obterObjetoDTO().getConveniosDto(), jliConveniosAdicionados);
            adicionarDtos(obterObjetoDTO().getServicosDto(), jliTiposAtendimentosServicosAdicionados);
            adicionarDtos(obterObjetoDTO().getRecursosProximoAMoradia(), jliRecursosProximosAMoradiaAdicionados);
            adicionarDtos(obterObjetoDTO().getNecessidadesDto(), jliNecessidadesAdicionadas);
            adicionarDtos(obterObjetoDTO().getExpectativasDto(), jliExpectativasAdicionadas);
            
            adicionarDtos(obterObjetoDTO().getEncaminhamentosDto(), jliEncaminhamentosAdicionados);
            jtfOutrosApoiosServicos.setText(obterObjetoDTO().getOutrosApoiosServicos());
            jatObs.setText(obterObjetoDTO().getObs());
            jepCirurgias.setText(obterObjetoDTO().getCirurgias());
            jepMedicamentos.setText(obterObjetoDTO().getMedicamentos());
            selecionarDto(jcbPossuiConsanguinidade, obterObjetoDTO().getPossuiConsanguinidadeDto());
            jepConsanguinidade.setText(obterObjetoDTO().getConsanguinidade());
            adicionarDtos(obterObjetoDTO().getCustosDoencaDto(), jliListagemCustosDoenca);
            adicionarDtos(obterObjetoDTO().getCustosDeficienciaDto(), jliListagemCustosDeficiencia);
            selecionarDto(jcbTipoLeitura, obterObjetoDTO().getTipoLeituraDto());
            jtfTamanhoFonte.setText(obterObjetoDTO().getTamanhoFonte());
            adicionarDtos(obterObjetoDTO().getRecursosDto(), jliRecursosAdicionados);
            selecionarDto(jcbTrabalhandoAtualmente, obterObjetoDTO().getAtualmenteTrabalhandoDto());
            adicionarDtos(obterObjetoDTO().getInformacaoTrabalhoCompletaDto(), jtaInformacaoTrabalhoCompleta);
            jtaHistorico.setText(obterObjetoDTO().getHistorico());
            jtaFuncionalidade.setText(obterObjetoDTO().getFuncionalidade());
            jtaReacaoFrenteADeficiencia.setText(obterObjetoDTO().getReacaoFrenteADeficiencia());
            jtaReacaoFrenteADeficienciaFamilia.setText(obterObjetoDTO().getReacaoFrenteADeficienciaFamiliar());
            jtaRedeDeApoio.setText(obterObjetoDTO().getRedeDeApoio());
            jtaRedeDeAmigos.setText(obterObjetoDTO().getRedeDeAmigos());
            jtaNamoroCasamentoSexualidade.setText(obterObjetoDTO().getNamoroCasamentoSexualidade());
            jtaNecessidadeExpectativasQueixas.setText(obterObjetoDTO().getNecessidadesExpectativasQueixas());
            jtaEducacional.setText(obterObjetoDTO().getEducacional());
            jtaComunicacao.setText(obterObjetoDTO().getComunicacao());
            jtaReligiaoLazerCulturaRotina.setText(obterObjetoDTO().getReligiaoLazerCulturaRotina());
            jtaParecer.setText(obterObjetoDTO().getParecer());
            jtaResumoAtendimentosPsicossocial.setText(obterObjetoDTO().getResumoAtendimentosPsicossocial());
        }
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbStatus, resultadoListagemStatusDTO.getObjetosDto());
        adicionarDtos(jcbGenero, resultadoListagemGeneroDTO.getObjetosDto());
        adicionarDtos(jcbUFRg, resultadoListagemUfDTO.getObjetosDto());
        adicionarDtos(jcbUF, resultadoListagemUfDTO.getObjetosDto());
        adicionarDtos(jcbEstadoCivil, resultadoListagemEstadoCivilDTO.getObjetosDto());
        adicionarDtos(jcbZona, resultadoListagemZonaDTO.getObjetosDto());
        adicionarDtos(jcbPais, resultadoListagemPaisDTO.getObjetosDto());
        adicionarDtos(jcbSituacaoGuarda, resultadoListagemSituacaoGuardaDTO.getObjetosDto());        
        adicionarDtos(jcbConvenios, resultadoListagemConvenioDTO.getObjetosDto());
        adicionarDtos(jcbTipoTelefone, resultadoListagemTipoTelefoneDTO.getObjetosDto());
        adicionarDtos(jcbGrupoEtnico, resultadoListagemGrupoEtnicoDTO.getObjetosDto());
        adicionarDtos(jcbSaai, resultadoListagemInstituicaoComSRMsDTO.getObjetosDto());
        adicionarDtos(jcbSalaRecurso, resultadoListagemInstituicaoComSalaRecursoDTO.getObjetosDto());
        adicionarDtos(jcbOutrosAEE, resultadoListagemInstituicaoComOutrosAEEDTO.getObjetosDto());
        adicionarDtos(jcbTipoLeitura, resultadoListagemTipoLeituraDTO.getObjetosDto());
        adicionarDtos(jcbRecursoAAdicionar, resultadoListagemRecursoDTO.getObjetosDto());
        adicionarDtos(jcbOrigemEncaminhamento, resultadoListagemOrigemEncaminhamentoDTO.getObjetosDto());
        adicionarDtos(jcbRedeEncaminhamento, resultadoListagemRedeEncaminhamentoDTO.getObjetosDto());
        adicionarDtos(jcbTipoAtendimentoServico, resultadoListagemServicoDTO.getObjetosDto());
        adicionarDtos(jcbRecursoProximoAMoradia, resultadoListagemRecursoMoradiaDTO.getObjetosDto());
        adicionarDtos(jcbNecessidade, resultadoListagemNecessidadeDTO.getObjetosDto());
        adicionarDtos(jcbExpectativa, resultadoListagemExpectativaDTO.getObjetosDto());
        adicionarDtos(jcbTrabalhandoAtualmente, resultadoListagemSimNaoDTO.getObjetosDto());
        adicionarDtos(jcbPossuiConsanguinidade, resultadoListagemSimNaoDTO.getObjetosDto());
        adicionarDtos(jcbItemCustoDoencaAAdicionar, resultadoListagemItensCustoDoencaDTO.getObjetosDto());
        adicionarDtos(jcbItemCustoDeficienciaAAdicionar, resultadoListagemItensCustoDeficienciaDTO.getObjetosDto());
        adicionarDtos(jcbCondicaoMoradia, resultadoListagemCondicaoMoradiaDTO.getObjetosDto());
        adicionarDtos(jcbSituacaoHabitacional, resultadoListagemSituacaoHabitacionalDTO.getObjetosDto());
        adicionarDtos(jcbHabitacao, resultadoListagemHabitacaoDTO.getObjetosDto());
        adicionarDtos(jcbTipoConstrucao, resultadoListagemTipoConstrucaoDTO.getObjetosDto());
        adicionarDtos(jcbInfraestruturaBasica, resultadoListagemInfraestruturaBasicaDTO.getObjetosDto());
    }

    @Override
    protected void prepararDtoParaEditar() {
        EnderecoDTO enderecoDto = new EnderecoDTO();
        enderecoDto.setCep(jftCep.getText());
        enderecoDto.setEndereco(jtfEndereco.getText());
        enderecoDto.setNumero(jtfNumero.getText());
        enderecoDto.setComplemento(jtfComplemento.getText());
        enderecoDto.setZonaDto((ZonaDTO) obterDtoSelecionado(jcbZona));
        enderecoDto.setBairro(jtfBairro.getText());
        enderecoDto.setMunicipioDto((MunicipioDTO) obterDtoSelecionado(jcbMunicipio));
        enderecoDto.setUfDto((UfDTO) obterDtoSelecionado(jcbUF));
        enderecoDto.setPaisDto((PaisDTO) obterDtoSelecionado(jcbPais));

        obterObjetoDTO().getInformacaoEssencialDto().setEnderecoDto(enderecoDto);

        ContatoDTO contatoDto = obterObjetoDTO().getInformacaoEssencialDto().getContatoDto();
        contatoDto.setRamal(jftRamal.getText());
        contatoDto.setNomeContato(jtfNomeContato.getText());
        contatoDto.setEmail(jtfEmail.getText());

        obterObjetoDTO().getInformacaoEssencialDto().setNome(jtfNome.getText());
        obterObjetoDTO().setStatusDto((StatusDTO) obterDtoSelecionado(jcbStatus));
        obterObjetoDTO().setGeneroDto((GeneroDTO) obterDtoSelecionado(jcbGenero));
        obterObjetoDTO().getInformacaoEssencialDto().setDataNascimento(jftDataNascimento.getText());
        obterObjetoDTO().setNaturalidade(jtfNaturalidade.getText());
        obterObjetoDTO().setUfRgDto((UfDTO) obterDtoSelecionado(jcbUFRg));
        obterObjetoDTO().getInformacaoEssencialDto().setRg(jtfRg.getText());
        obterObjetoDTO().setDataExpedicaoRg(jftDataExpedicaoRG.getText());
        obterObjetoDTO().setOrgaoEmissorRg(jtfEmissorRG.getText());
        obterObjetoDTO().getInformacaoEssencialDto().setCpf(jftCpf.getText());
        obterObjetoDTO().setEstadoCivilDto((EstadoCivilDTO) obterDtoSelecionado(jcbEstadoCivil));
        obterObjetoDTO().setNacionalidade(jtfNacionalidade.getText());
        obterObjetoDTO().setAssociadoAoSetorCTO(estaMarcado(jchSetorCTO));
        obterObjetoDTO().setAssociadoAoSetorProceja(estaMarcado(jchSetorProceja));
        obterObjetoDTO().setGrupoEtnicoDto((GrupoEtnicoDTO) obterDtoSelecionado(jcbGrupoEtnico));
        obterObjetoDTO().setRenda(jtfRenda.getText());
        
        obterObjetoDTO().setSituacoesGuardaDto((List<SituacaoGuardaDTO>) obterDtos(jliSituacaoGuardaAdicionadas));
        obterObjetoDTO().setPeriodoBeneficiosDto((List<PeriodoBeneficioDTO>) obterDtos(jliPeriodoBeneficiosAdicionados));
        obterObjetoDTO().setPeriodoDeficienciaDto((List<PeriodoDeficienciaDTO>)obterDtos(jliPeriodoDeficienciasAdicionados));
        obterObjetoDTO().setPeriodoComprometimentoDto((List<PeriodoComprometimentoDTO>)obterDtos(jliPeriodoComprometimentosAdicionados));
        obterObjetoDTO().setPeriodoDoencasDto((List<PeriodoDoencaDTO>)obterDtos(jliPeriodoDoencasAdicionados));
        obterObjetoDTO().setMultiplaDeficiencia(jchMultiplaDeficiencia.isSelected());
        obterObjetoDTO().setCadeiraDeRodas(jchCadeiraDeRodas.isSelected());
        obterObjetoDTO().setEncaminhamentosDto((List<EncaminhamentoDTO>) obterDtos(jliEncaminhamentosAdicionados));
        obterObjetoDTO().setCondicaoMoradiaDto((CondicaoMoradiaDTO) obterDtoSelecionado(jcbCondicaoMoradia));
        obterObjetoDTO().setSituacaoHabitacionalDto((SituacaoHabitacionalDTO) obterDtoSelecionado(jcbSituacaoHabitacional));
        obterObjetoDTO().setHabitacaoDto((HabitacaoDTO) obterDtoSelecionado(jcbHabitacao));
        obterObjetoDTO().setTipoConstrucaoDto((TipoConstrucaoDTO) obterDtoSelecionado(jcbTipoConstrucao));
        obterObjetoDTO().setInfraestruturaBasicaDtos((List<InfraestruturaBasicaDTO>) obterDtos(jliInfraestruturaBasicaAdicionados));
        obterObjetoDTO().setConveniosDto((List<ConvenioDTO>) obterDtos(jliConveniosAdicionados));
        obterObjetoDTO().setServicosDto((List<ServicoDTO>) obterDtos(jliTiposAtendimentosServicosAdicionados));
        obterObjetoDTO().setRecursosProximoAMoradia((List<RecursoMoradiaDTO>) obterDtos(jliRecursosProximosAMoradiaAdicionados));
        obterObjetoDTO().setNecessidadesDto((List<NecessidadeDTO>) obterDtos(jliNecessidadesAdicionadas));
        obterObjetoDTO().setExpectativasDto((List<ExpectativaDTO>) obterDtos(jliExpectativasAdicionadas));
            
        obterObjetoDTO().setFalecido(estaMarcado(jchFalecido));
        obterObjetoDTO().setNaoAlfabetizado(estaMarcado(jchNaoAlfabetizado));
        obterObjetoDTO().setNaoEstaNaEscola(estaMarcado(jchNaoEstaNaEscola));
        obterObjetoDTO().setResponsavelPorSiMesmo(estaMarcado(jchReponsavelPorSiMesmo));
        obterObjetoDTO().setInformacaoEducacionaisDto((List<InformacaoEducacionalDTO>) obterDtos(jtaInformacoesEducacionaisAdicionadas));
        obterObjetoDTO().setInstituicaoComSRMSDto((InstituicaoDTO) obterDtoSelecionado(jcbSaai));
        obterObjetoDTO().setInstituicaoComSalaRecursoDto((InstituicaoDTO) obterDtoSelecionado(jcbSalaRecurso));
        obterObjetoDTO().setInstituicaoComOutrosAEE((InstituicaoDTO)obterDtoSelecionado(jcbOutrosAEE));
        obterObjetoDTO().setFamiliaresDto((List<FamiliarDTO>) obterDtos(jtaFamiliares));
        obterObjetoDTO().setCertidoes((List<CertidaoDTO>)obterDtos(jtaCertidoesAdicionadas));
        
        obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().setTelefonesDto((List<TelefoneDTO>) obterDtos(jliTelefonesAdicionados));      
        obterObjetoDTO().setOutrosApoiosServicos(jtfOutrosApoiosServicos.getText());
        obterObjetoDTO().setObs(jatObs.getText());
        obterObjetoDTO().setCirurgias(jepCirurgias.getText());
        obterObjetoDTO().setMedicamentos(jepMedicamentos.getText());
        obterObjetoDTO().setConsanguinidade(jepConsanguinidade.getText());
        obterObjetoDTO().setCustosDoencaDto((List<CustoDTO>)obterDtos(jliListagemCustosDoenca));
        obterObjetoDTO().setCustosDeficienciaDto((List<CustoDTO>)obterDtos(jliListagemCustosDeficiencia));
        obterObjetoDTO().setTipoLeituraDto((TipoLeituraDTO)obterDtoSelecionado(jcbTipoLeitura));
        obterObjetoDTO().setTamanhoFonte(jtfTamanhoFonte.getText());
        obterObjetoDTO().setRecursosDto((List<RecursoDTO>)obterDtos(jliRecursosAdicionados));
        
        obterObjetoDTO().setAtualmenteTrabalhandoDto((SimNaoDTO)obterDtoSelecionado(jcbTrabalhandoAtualmente));
        obterObjetoDTO().setPossuiConsanguinidadeDto((SimNaoDTO)obterDtoSelecionado(jcbPossuiConsanguinidade));
        obterObjetoDTO().setInformacaoTrabalhoCompletaDto((List<InformacaoTrabalhoCompletaDTO>)obterDtos(jtaInformacaoTrabalhoCompleta));
        
        obterObjetoDTO().setHistorico(jtaHistorico.getText());
        obterObjetoDTO().setFuncionalidade(jtaFuncionalidade.getText());
        obterObjetoDTO().setReacaoFrenteADeficiencia(jtaReacaoFrenteADeficiencia.getText());
        obterObjetoDTO().setReacaoFrenteADeficienciaFamiliar(jtaReacaoFrenteADeficienciaFamilia.getText());
        obterObjetoDTO().setRedeDeApoio(jtaRedeDeApoio.getText());
        obterObjetoDTO().setRedeDeAmigos(jtaRedeDeAmigos.getText());
        obterObjetoDTO().setNamoroCasamentoSexualidade(jtaNamoroCasamentoSexualidade.getText());
        obterObjetoDTO().setNecessidadesExpectativasQueixas(jtaNecessidadeExpectativasQueixas.getText());
        obterObjetoDTO().setEducacional(jtaEducacional.getText());
        obterObjetoDTO().setComunicacao(jtaComunicacao.getText());
        obterObjetoDTO().setReligiaoLazerCulturaRotina(jtaReligiaoLazerCulturaRotina.getText());
        obterObjetoDTO().setParecer(jtaParecer.getText());
    }

    @Override
    protected ResultadoEdicaoUsuarioDTO solicitarEdicaoDto() throws Exception {
        UsuarioDTO usuarioDto = obterObjetoDTO();
        ResultadoEdicaoUsuarioDTO resultado = servicoSisLaraServer.editarUsuario(usuarioDto, Sessao.obterInstancia().obterToken());
        return resultado;
    }

    @Override
    protected void executarAcaoAposInclusao() {
        jtfProntuario.setText(obterObjetoDTO().getId().toString());
        jtfDataCadastroServicoSocial.setText(obterObjetoDTO().getDataCadastro().toString());
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        return obterObjetoDTO().getId() != null;
    }

    private ModeloTabela obterModeloTabelaFamiliar(){
        return ((ModeloTabelaFamiliar)jtaFamiliares.getModel());
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_USUARIO;
    }
    
    public void adicionarFamiliar() {
        new TelaEditarFamiliar((JDialog) telaPai, new FamiliarDTO());
        String chave = Sessao.CHAVE_FAMILIAR;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            obterModeloTabelaFamiliar().adicionarDTO((FamiliarDTO) Sessao.obterInstancia().obterDto(chave));
        }
    }
    
    public void removerFamiliarSelecionado() {
        if ((estaComItemValidoSelecionado(jtaFamiliares) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jtaFamiliares);
        }
    }

    public void alterarFamiliar() {
        if (estaComItemValidoSelecionado(jtaFamiliares)){
            new TelaEditarFamiliar((JDialog) telaPai, (FamiliarDTO)obterDtoSelecionado(jtaFamiliares));
            String chave = Sessao.CHAVE_FAMILIAR;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaFamiliares, (FamiliarDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }
    
    public void abrirTelaDeAlteracaoFamiliar(KeyEvent evt){
        if (teclaEnterPressionada(evt)){
            alterarFamiliar();
        }
    }
    
    public void abrirTelaDeAlteracaoInformacaoEducacional(KeyEvent evt) {
       controladorInformacoesEducacionais.abrirTelaDeAlteracaoInformacaoEducacional(evt);
    }

    public void abrirTelaUtilizarPreCadastro() {
        new TelaUtilizarPreCadastroComValidacao((JDialog)telaPai);
        atualizaCamposInformacaoEssencial();
    }
    
    private void atualizaCamposInformacaoEssencial(){
        String chave = Sessao.CHAVE_PRE_CADASTRO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            PreCadastroDTO preCadastroDto = (PreCadastroDTO)Sessao.obterInstancia().obterDto(chave);
            obterObjetoDTO().setInformacaoEssencialDto(preCadastroDto.getInformacaoEssencialDto());
            carregarCamposInformacaoEssencial(preCadastroDto.getInformacaoEssencialDto());
        }
    }
    
    private void carregarCamposInformacaoEssencial(InformacaoEssencialDTO informacaoEssencialDto) {
        jtfNome.setText(informacaoEssencialDto.getNome());
        jtfRg.setText(informacaoEssencialDto.getRg());
        jftCpf.setText(informacaoEssencialDto.getCpf());
        jftDataNascimento.setText(informacaoEssencialDto.getDataNascimento());
        jftRamal.setText(informacaoEssencialDto.getContatoDto().getRamal());
        jtfNomeContato.setText(informacaoEssencialDto.getContatoDto().getNomeContato());
        jtfEmail.setText(informacaoEssencialDto.getContatoDto().getEmail());
        jcbUF.setSelectedItem(informacaoEssencialDto.getEnderecoDto().getUfDto());
        jcbMunicipio.setSelectedItem(informacaoEssencialDto.getEnderecoDto().getMunicipioDto());
        adicionarDtos(obterObjetoDTO().getInformacaoEssencialDto().getContatoDto().getTelefonesDto(), jliTelefonesAdicionados);
    }

    private void habilitarBotaoUtilizacaoPreCadastro() {
        if (obterObjetoDTO().getId() == null){
            habilitar(jbuUtilizarPreCadastro);
        }
    }

    public void adicionarPeriodoBeneficio() {
        new TelaEditarPeriodoBeneficio((JDialog) telaPai, new PeriodoBeneficioDTO());
        String chave = Sessao.CHAVE_PERIODO_BENEFICIO;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            adicionarDtos((PeriodoBeneficioDTO) Sessao.obterInstancia().obterDto(chave), jliPeriodoBeneficiosAdicionados);
        }
    }

    public void alterarPeriodoBeneficioSelecionado(){
         if (estaComItemValidoSelecionado(jliPeriodoBeneficiosAdicionados)){
            new TelaEditarPeriodoBeneficio((JDialog) telaPai, (PeriodoBeneficioDTO)obterDtoSelecionado(jliPeriodoBeneficiosAdicionados));
            String chave = Sessao.CHAVE_PERIODO_BENEFICIO;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                incluirObjetoDto((PeriodoBeneficioDTO) Sessao.obterInstancia().obterDto(chave), jliPeriodoBeneficiosAdicionados);
            }
         }
    }
    
    public void removerPeriodoBeneficioSelecionado(){
        if ((estaComItemValidoSelecionado(jliPeriodoBeneficiosAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliPeriodoBeneficiosAdicionados);
        }
    }
    
    public void adicionarSituacaoGuarda() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliSituacaoGuardaAdicionadas, jcbSituacaoGuarda);    
    }
    
    public void adicionarConvenio() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliConveniosAdicionados, jcbConvenios);    
    }

    public void removerSituacaoGuardaSelecionada() {
        if ((estaComItemValidoSelecionado(jliSituacaoGuardaAdicionadas) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliSituacaoGuardaAdicionadas);
        }
    }

    public void removerConvenioSelecionado() {
        if ((estaComItemValidoSelecionado(jliConveniosAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliConveniosAdicionados);
        }
    }

    public void adicionarInformacaoEducacional() {
        controladorInformacoesEducacionais.adicionarInformacaoEducacional();
    }

    public void alterarInformacaoEducacional() {
        controladorInformacoesEducacionais.alterarInformacaoEducacional();
    }

    public void removerInformacaoEducacionalSelecionado() {
        if ((estaComItemValidoSelecionado(jtaInformacoesEducacionaisAdicionadas) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            controladorInformacoesEducacionais.removerInformacaoEducacionalSelecionada();
        }
    }

    public void adicionarTelefone() {
        controladorTelefone.adicionarTelefone();
    }

    public void removerTelefoneSelecionado() {
        controladorTelefone.removerTelefoneSelecionado();
    }

    public void alterarFoto() {
        JFileChooser jfcArquivoFoto = new JFileChooser();
        jfcArquivoFoto.setFileFilter(new FiltroImagemJPG());
        jfcArquivoFoto.setAccessory(new ImagePreview(jfcArquivoFoto));
        int opcao = jfcArquivoFoto.showDialog(telaPai, "Selecione uma foto");
        if (opcao == JFileChooser.APPROVE_OPTION){
            try {
                if (jfcArquivoFoto.getSelectedFile() != null) {
                    byte[] arrayImagem = ImagemUtils.remimensionar(jfcArquivoFoto.getSelectedFile());
                    atualizarCampoDeFoto(arrayImagem);
                    obterObjetoDTO().setFoto(arrayImagem);
                }
            } catch (Exception ex) {
                logger.error(JOptionPanePersonalizado.ERRO_DURANTE_SELECAO_FOTO + "\n Detalhes: " + ex);
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_SELECAO_FOTO);
            }
        }
    }
    
    private void atualizarCampoDeFoto(byte[] imagem){
        if (imagem != null){
            jlaFoto.setIcon(new ImageIcon(imagem));
        }
    }

    public void carregarProntuarios() {
        try {
            ResultadoListagemProntuarioEscaneadoDTO resultado = servicoSisLaraServer.obterListagemProntuariosEscaneados(obterObjetoDTO());
            adicionarDtos(resultado.getObjetosDto(), jliArquivosProntuariosEscaneados);
            jliArquivosProntuariosEscaneados.requestFocusInWindow();
        } catch (RemoteException ex) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_CARREGAMENTO_PRONTUARIO_ESCANEADO + "\n Detalhes: " + ex);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_CARREGAMENTO_PRONTUARIO_ESCANEADO);
        }
    }

    public void exibirProntuarioEscaneadoSelecionado() {
        ProntuarioEscaneadoDTO prontuarioEscaneadoSelecionadoDto = (ProntuarioEscaneadoDTO)obterDtoSelecionado(jliArquivosProntuariosEscaneados);
        try{
            ProntuarioEscaneadoDTO prontuarioEscaneadoDto = servicoSisLaraServer.obterProntuarioEscaneado(obterObjetoDTO(), prontuarioEscaneadoSelecionadoDto);
            abrirArquivo(gravarArquivo(prontuarioEscaneadoDto.getArquivoDto()));
        }catch(Exception e){
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_PRONTUARIO_ESCANEADO + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_PRONTUARIO_ESCANEADO);
        }
    }

    public void exibirProntuarioEsnacenadoSelecionado(KeyEvent evt) {
       if (teclaEnterPressionada(evt)){
            exibirProntuarioEscaneadoSelecionado();
        }
    }

    public void adicionarPeriodoDeficiencia() {
        new TelaEditarPeriodoDeficiencia((JDialog)telaPai, new PeriodoDeficienciaDTO());
        String chave = Sessao.CHAVE_PERIODO_DEFICIENCIA;
        if (Sessao.obterInstancia().possuiDto(chave)){
            adicionarDtos((PeriodoDeficienciaDTO)Sessao.obterInstancia().obterDto(chave), jliPeriodoDeficienciasAdicionados);
         }
    }

    public void alterarPeriodoDeficienciaSelecionado() {
         if (estaComItemValidoSelecionado(jliPeriodoDeficienciasAdicionados)){
            new TelaEditarPeriodoDeficiencia((JDialog) telaPai, (PeriodoDeficienciaDTO)obterDtoSelecionado(jliPeriodoDeficienciasAdicionados));
            String chave = Sessao.CHAVE_PERIODO_DEFICIENCIA;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                incluirObjetoDto((PeriodoDeficienciaDTO) Sessao.obterInstancia().obterDto(chave), jliPeriodoDeficienciasAdicionados);
            }
         }
    }

    public void removerPeriodoDeficienciaSelecionado() {
        if ((estaComItemValidoSelecionado(jliPeriodoDeficienciasAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliPeriodoDeficienciasAdicionados);
        }
    }
    
    public void adicionarPeriodoDoenca() {
        new TelaEditarPeriodoDoenca((JDialog)telaPai, new PeriodoDoencaDTO());
        String chave = Sessao.CHAVE_PERIODO_DOENCA;
        if (Sessao.obterInstancia().possuiDto(chave)){
            adicionarDtos((PeriodoDoencaDTO)Sessao.obterInstancia().obterDto(chave), jliPeriodoDoencasAdicionados);
         }
    }

    public void alterarPeriodoDoencaSelecionado() {
         if (estaComItemValidoSelecionado(jliPeriodoDoencasAdicionados)){
            new TelaEditarPeriodoDoenca((JDialog) telaPai, (PeriodoDoencaDTO)obterDtoSelecionado(jliPeriodoDoencasAdicionados));
            String chave = Sessao.CHAVE_PERIODO_DOENCA;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                incluirObjetoDto((PeriodoDoencaDTO) Sessao.obterInstancia().obterDto(chave), jliPeriodoDoencasAdicionados);
            }
         }
    }

    public void removerPeriodoDoencaSelecionado() {
        if ((estaComItemValidoSelecionado(jliPeriodoDoencasAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliPeriodoDoencasAdicionados);
        }
    }

    public void carregarEndereco() {
        carregarEnderecoSeEstiverVazioOuDiferenteDoCEP(jftCep, jtfEndereco, jtfBairro, jcbUF, jcbMunicipio, jcbPais);
    }

    public void carregarTodosProntuarios() {
        Runnable comando = new Runnable() {
            @Override
            public void run() {
                removerArquivosTemporarios();
                try {
                    SomUtils.iniciar();
                    ResultadoListagemProntuarioEscaneadoDTO resultado = servicoSisLaraServer.obterListagemProntuariosEscaneados(obterObjetoDTO());
                    List<ProntuarioEscaneadoDTO> prontuarios = resultado.getObjetosDto();
                    List<File> arquivos = new ArrayList<>();
                    for (ProntuarioEscaneadoDTO prontuarioEscaneadoDto : prontuarios) {
                        ProntuarioEscaneadoDTO prontuarioEscaneadoComConteudoDto = servicoSisLaraServer.obterProntuarioEscaneado(obterObjetoDTO(), prontuarioEscaneadoDto);
                        arquivos.add(gravarArquivo(prontuarioEscaneadoComConteudoDto.getArquivoDto()));
                    }
                    adicionarDtos(resultado.getObjetosDto(), jliArquivosProntuariosEscaneados);
                    SomUtils.parar();
                    jliArquivosProntuariosEscaneados.requestFocusInWindow();
                    abrirArquivo(arquivos.get(0));
                } catch (Exception ex) {
                    logger.error(JOptionPanePersonalizado.ERRO_DURANTE_CARREGAMENTO_PRONTUARIO_ESCANEADO + "\n Detalhes: " + ex);
                    JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_CARREGAMENTO_PRONTUARIO_ESCANEADO);
                }
            }
        };
        new EsperaUtils(comando, (JDialog) telaPai).execute();
    }

    public void adicionarCertidao() {
        new TelaEditarCertidao((JDialog)telaPai, new CertidaoDTO());
        String chave = Sessao.CHAVE_CERTIDAO;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            adicionarDtos((CertidaoDTO) Sessao.obterInstancia().obterDto(chave), jtaCertidoesAdicionadas);
        }
    }

    public void alterarCertidao() {
        if (estaComItemValidoSelecionado(jtaCertidoesAdicionadas)){
            new TelaEditarCertidao((JDialog) telaPai, (CertidaoDTO)obterDtoSelecionado(jtaCertidoesAdicionadas));
            String chave = Sessao.CHAVE_CERTIDAO;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaCertidoesAdicionadas, (CertidaoDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }

    public void removerCertidaoSelecionada() {
        if ((estaComItemValidoSelecionado(jtaCertidoesAdicionadas) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jtaCertidoesAdicionadas);
        }
    }

    public void adicionarRecurso() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliRecursosAdicionados, jcbRecursoAAdicionar);   
    }

    public void removerRecursoSelecionado() {
        if ((estaComItemValidoSelecionado(jliRecursosAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliRecursosAdicionados);
        }
    }

    public void adicionarInformacaoTrabalhoCompleta() {
        new TelaEditarInformacaoTrabalhoCompleta((JDialog) telaPai, new InformacaoTrabalhoCompletaDTO());
        String chave = Sessao.CHAVE_INFORMACAO_TRABALHO_COMPLETA;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            adicionarDtos((InformacaoTrabalhoCompletaDTO) Sessao.obterInstancia().obterDto(chave), jtaInformacaoTrabalhoCompleta);
        }
    }

    public void alterarInformacaoTrabalhoCompleta() {
         if (estaComItemValidoSelecionado(jtaInformacaoTrabalhoCompleta)){
            new TelaEditarInformacaoTrabalhoCompleta((JDialog) telaPai, (InformacaoTrabalhoCompletaDTO)obterDtoSelecionado(jtaInformacaoTrabalhoCompleta));
            String chave = Sessao.CHAVE_INFORMACAO_TRABALHO_COMPLETA;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaInformacaoTrabalhoCompleta, (InformacaoTrabalhoCompletaDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }

    public void removerInformacaoTrabalhoCompleta() {
        if ((estaComItemValidoSelecionado(jtaInformacaoTrabalhoCompleta) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jtaInformacaoTrabalhoCompleta);
        }
    }

    public void adicionarCustoDoencaSelecionado() {
        adicionarCustoSelecionado(jcbItemCustoDoencaAAdicionar, jftValorCustoDoenca, jliListagemCustosDoenca);
    }
    
    private void adicionarCustoSelecionado(JComboBox jcbItemCustoDoencaAAdicionar, JFormattedTextField jftValorCustoDoenca, JList jliListagemCustosDoenca) {
        CustoDTO custoDto = new CustoDTO();
        custoDto.setItemCustoDto((ItemCustoDTO) obterDtoSelecionado(jcbItemCustoDoencaAAdicionar));
        custoDto.setValor(jftValorCustoDoenca.getText());
        try {
            ResultadoValidacaoCustoDTO resultadoValidacao = servicoSisLaraServer.validarCusto(custoDto);
            if (resultadoValidacao.sucesso()) {
                if (!existe(jliListagemCustosDoenca, custoDto)) {
                    adicionarDtoSemRepetir(custoDto, jliListagemCustosDoenca);
                    deselecionarDto(jcbItemCustoDoencaAAdicionar);
                    jftValorCustoDoenca.setText("");
                }else{
                    JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_ITEM_EXISTENTE);
                }
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoValidacao.obterMensagens());
            }
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_ADICAO_CUSTO_DOENCA + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_ADICAO_CUSTO_DOENCA);
        }
    }

    public void removerCustoDoencaSelecionado() {
        if ((estaComItemValidoSelecionado(jliListagemCustosDoenca) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliListagemCustosDoenca);
        }
    }

    public void adicionarCustoDeficienciaSelecionado() {
        adicionarCustoSelecionado(jcbItemCustoDeficienciaAAdicionar, jftValorCustoDeficiencia, jliListagemCustosDeficiencia);
    }
    
    public void removerCustoDeficienciaSelecionado() {
        if ((estaComItemValidoSelecionado(jliListagemCustosDeficiencia) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliListagemCustosDeficiencia);
        }
    }

    private void desabilitarItemFalecido() {
        if (obterObjetoDTO().isFalecido())
            jchFalecido.setEnabled(false);
    }

    public void adicionarTipoAtendimentoServicoSelecionado() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliTiposAtendimentosServicosAdicionados, jcbTipoAtendimentoServico);   
    }

    public void removerTipoAtendimentoServicoSelecionado() {
        if ((estaComItemValidoSelecionado(jliTiposAtendimentosServicosAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliTiposAtendimentosServicosAdicionados);
        }
    }

    public void adicionarRecursoProximoAMoradiaSelecionado() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliRecursosProximosAMoradiaAdicionados, jcbRecursoProximoAMoradia);   
    }

    public void removerRecursoProximoAMoradiaSelecionado() {
        if ((estaComItemValidoSelecionado(jliRecursosProximosAMoradiaAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliRecursosProximosAMoradiaAdicionados);
        }
    }

    public void adicionaNecessidadeSelecionada() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliNecessidadesAdicionadas, jcbNecessidade);   
    }

    public void removerNecessidadeSelecionada() {
        if ((estaComItemValidoSelecionado(jliNecessidadesAdicionadas) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliNecessidadesAdicionadas);
        }
    }

    public void adicionarExpectativaSelecionada() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliExpectativasAdicionadas, jcbExpectativa);   
    }

    public void removerExpectativaSelecionada() {
        if ((estaComItemValidoSelecionado(jliExpectativasAdicionadas) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliExpectativasAdicionadas);
        }
    }

    public void adicionarInfraestruturaBasicaSelecionado() {
        adicionarNaListaDtoSelecionadoPeloCombo(jliInfraestruturaBasicaAdicionados, jcbInfraestruturaBasica);   
    }

    public void removerInfraestruturaBasicaSelecionado() {
        if ((estaComItemValidoSelecionado(jliInfraestruturaBasicaAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliInfraestruturaBasicaAdicionados);
        }
    }

    public void adicionarEncaminhamentoSelecionado() {
        EncaminhamentoDTO encaminhamentoDto = new EncaminhamentoDTO();
        encaminhamentoDto.setOrigemEncaminhamentoDto((OrigemEncaminhamentoDTO) obterDtoSelecionado(jcbOrigemEncaminhamento));
        encaminhamentoDto.setRedeEncaminhamentoDto((RedeEncaminhamentoDTO) obterDtoSelecionado(jcbRedeEncaminhamento));
        encaminhamentoDto.setProfissionalLiberal(jtfProfissionalLiberal.getText());
        try {
            ResultadoValidacaoEncaminhamentoDTO resultadoValidacaoEncaminhamentoDTO = servicoSisLaraServer.validarEncaminhamento(encaminhamentoDto);
            if (resultadoValidacaoEncaminhamentoDTO.sucesso()) {
                adicionarDtos(encaminhamentoDto, jliEncaminhamentosAdicionados);
                jtfProfissionalLiberal.setText("");
                deselecionarDto(jcbOrigemEncaminhamento);
                deselecionarDto(jcbRedeEncaminhamento);
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoValidacaoEncaminhamentoDTO.obterMensagens());
            }
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_ADICAO_ENCAMINHAMENTO + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_ADICAO_ENCAMINHAMENTO);
        }
    }

    public void removerEncaminhamentoSelecionado() {
        removerArquivoSelecionado(jliEncaminhamentosAdicionados);
    }

    public void adicionarOutroComprometimento() {
        new TelaEditarComprometimento((JDialog)telaPai, new PeriodoComprometimentoDTO());
        String chave = Sessao.CHAVE_PERIODO_COMPROMETIMENTO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            adicionarDtos((PeriodoComprometimentoDTO)Sessao.obterInstancia().obterDto(chave), jliPeriodoComprometimentosAdicionados);
         }
    }

    public void alterarOutroComprometimento() {
        if (estaComItemValidoSelecionado(jliPeriodoComprometimentosAdicionados)) {
            new TelaEditarComprometimento((JDialog) telaPai, (PeriodoComprometimentoDTO) obterDtoSelecionado(jliPeriodoComprometimentosAdicionados));
            String chave = Sessao.CHAVE_PERIODO_COMPROMETIMENTO;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jliPeriodoComprometimentosAdicionados, (PeriodoComprometimentoDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }

    public void removerOutroCompromentimento() {
        if ((estaComItemValidoSelecionado(jliPeriodoComprometimentosAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliPeriodoComprometimentosAdicionados);
        }
    }

    public void adicionarTodasInfraEstruturasBasicas() {
        List<InfraestruturaBasicaDTO> infraEstruturasBasicas = resultadoListagemInfraestruturaBasicaDTO.getObjetosDto();
        esvaziarLista(jliRecursosAdicionados);
        adicionarDtos(infraEstruturasBasicas, jliInfraestruturaBasicaAdicionados);
    }
}

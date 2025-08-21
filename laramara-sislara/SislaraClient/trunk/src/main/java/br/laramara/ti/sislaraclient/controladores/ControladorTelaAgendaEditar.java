package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaUtilizarPreCadastroSemValidacao;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemDiaSemanaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemLocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemStatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public abstract class ControladorTelaAgendaEditar extends ControladorTela {

    private ControladorTipoAtendimento controladorTipoAtendimento;
    
    private ResultadoListagemProfissionalDTO resultadoListagemProfissionalDTO;
    private ResultadoListagemStatusDTO resultadoListagemStatusDTO;
    private ResultadoListagemLocalAtendimentoDTO resultadoListagemLocalAtendimentoDTO;
    private ResultadoListagemDiaSemanaDTO resultadoListagemDiaSemanaDTO;

    protected JTextField jtfNomePreCadastro;
    protected JComboBox jcbProfissional;
    protected JList jliProfissionaisAgendados;
    protected JComboBox jcbReservaPara;
    protected JComboBox jcbTipoAtendimento;
    protected JComboBox jcbDescricaoTipoAtendimento;
    protected JComboBox jcbModuloAtividade;
    protected JComboBox jcbSetor;
    protected JComboBox jcbLocalAtendimento;
    protected JFormattedTextField jftDataInicio;
    protected JFormattedTextField jftDataTermino;
    protected JComboBox jcbDiaSemana;
    protected JFormattedTextField jftHoraInicio;
    protected JFormattedTextField jftHoraTermino;
    protected JEditorPane jepObs;
    
    protected UsuarioDTO usuarioDto;
    protected PreCadastroDTO preCadastroDto;

    public ControladorTelaAgendaEditar(JDialog telaPai, JTextField jtfNomePreCadastro, JComboBox jcbProfissional, JList jliProfissionaisAgendados, JComboBox jcbReservaPara, JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade, JComboBox jcbSetor, JComboBox jcbLocalAtendimento, JFormattedTextField jftDataInicio, JFormattedTextField jftDataTermino, JComboBox jcbDiaSemana, JFormattedTextField jftHoraInicio, JFormattedTextField jftHoraTermino, JEditorPane jepObs) {
        super(telaPai);
        this.jtfNomePreCadastro = jtfNomePreCadastro;
        this.jcbProfissional = jcbProfissional;
        this.jliProfissionaisAgendados = jliProfissionaisAgendados;
        this.jcbReservaPara = jcbReservaPara;
        this.jcbTipoAtendimento = jcbTipoAtendimento;
        this.jcbDescricaoTipoAtendimento = jcbDescricaoTipoAtendimento;
        this.jcbModuloAtividade = jcbModuloAtividade;
        this.jcbSetor = jcbSetor;
        this.jcbLocalAtendimento = jcbLocalAtendimento;
        this.jftDataInicio = jftDataInicio;
        this.jftDataTermino = jftDataTermino;
        this.jcbDiaSemana = jcbDiaSemana;
        this.jftHoraInicio = jftHoraInicio;
        this.jftHoraTermino = jftHoraTermino;
        this.jepObs = jepObs;
        this.controladorTipoAtendimento = new ControladorTipoAtendimento(jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade, jcbSetor);
        inicializarCombosDadosAuxiliares();
    }

    private void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbProfissional, resultadoListagemProfissionalDTO.getObjetosDto());
        adicionarDtos(jcbReservaPara, resultadoListagemStatusDTO.getObjetosDto());
        adicionarDtos(jcbLocalAtendimento, resultadoListagemLocalAtendimentoDTO.getObjetosDto());
        adicionarDtos(jcbDiaSemana, resultadoListagemDiaSemanaDTO.getObjetosDto());
    }

    private void obterDadosAuxiliares() {
        try {
            resultadoListagemProfissionalDTO = servicoSisLaraServer.obterListagemProfissionalAtivos();
            resultadoListagemStatusDTO = servicoSisLaraServer.obterListagemStatusDisponiveisParaAgendamento();
            resultadoListagemLocalAtendimentoDTO = servicoSisLaraServer.obterListagemLocalAtendimento();
            resultadoListagemDiaSemanaDTO = servicoSisLaraServer.obterListagemDiaSemana();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    public void inicializarDescricaoTipoAtendimento() {
        controladorTipoAtendimento.inicializarDescricaoTipoAtendimento();
    }
    
    public void inicializarSetor() {
        controladorTipoAtendimento.inicializarModuloAtividadeESetor();
    }

    public abstract void salvar();
    
    public void adicionarProfissional() {
         adicionarNaListaDtoSelecionadoPeloCombo(jliProfissionaisAgendados, jcbProfissional);    
    }

    public void removerProfissionalSelecionado() {
        if ((estaComItemValidoSelecionado(jliProfissionaisAgendados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliProfissionaisAgendados);
        }
    }
    
    private void limparDadosPreCadastro(){
        jtfNomePreCadastro.setText("");
        preCadastroDto = null;
    }
    
    public void abrirTelaUtilizarPreCadastro() {
        new TelaUtilizarPreCadastroSemValidacao((JDialog)telaPai);
        atualizaCamposPreCadastro();
    }

    private void atualizaCamposPreCadastro() {
        String chave = Sessao.CHAVE_PRE_CADASTRO_SELECIONADO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            preCadastroDto = (PreCadastroDTO)Sessao.obterInstancia().obterDto(chave);
            carregarCamposPreCadastro(preCadastroDto);
        }
    }

    protected void carregarCamposPreCadastro(PreCadastroDTO preCadastroDto) {
        if (preCadastroDto != null){
            jtfNomePreCadastro.setText(preCadastroDto.getInformacaoEssencialDto().getNome());
        }
    }
    
    protected EspecificacaoGeracaoAgendamentoDTO obterEspecificacao() {
        EspecificacaoGeracaoAgendamentoDTO especificacao = new EspecificacaoGeracaoAgendamentoDTO();
        especificacao.setUsuarioDto(usuarioDto);
        especificacao.setPreCadastroDTO(preCadastroDto);
        especificacao.setProfissionaisDto((List<ProfissionalDTO>)obterDtos(jliProfissionaisAgendados));
        especificacao.setReservaStatusDto((StatusDTO)obterDtoSelecionado(jcbReservaPara));
        especificacao.setDescricaoTipoAtendimentoDto((DescricaoTipoAtendimentoDTO) obterDtoSelecionado(jcbDescricaoTipoAtendimento));
        especificacao.setModuloDto((ModuloDTO)obterDtoSelecionado(jcbModuloAtividade));
        especificacao.setSetorDto((SetorDTO) obterDtoSelecionado(jcbSetor));
        especificacao.setLocalAtendimentoDto((LocalAtendimentoDTO) obterDtoSelecionado(jcbLocalAtendimento));
        especificacao.setDiaSemanaDto((DiaSemanaDTO) obterDtoSelecionado(jcbDiaSemana));
        especificacao.setDataInicio(jftDataInicio.getText());
        especificacao.setDataTermino(jftDataTermino.getText());
        especificacao.setHorarioDto(new HorarioDTO(jftHoraInicio.getText(), jftHoraTermino.getText()));
        especificacao.setObs(jepObs.getText());
        return especificacao;
    }
}

package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaProgramacao;
import br.laramara.ti.sislaraclient.telas.TelaGruposEditarProgramacao;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaEHorarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProgramacaoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemDiaSemanaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemLocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoDiaSemanaEHorarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;

public class ControladorTelaGruposEditarModuloPeriodo extends ControladorTelaEditar {
 
    private ResultadoListagemDiaSemanaDTO resultadoListagemDiaSemanaDTO;
    private ResultadoListagemProfissionalDTO resultadoListagemProfissionalDTO;
    private ResultadoListagemLocalAtendimentoDTO resultadoListagemLocalAtendimentoDTO;
    
    private DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO;
                
    private JComboBox jcbModuloAtividade;
    private JComboBox jcbDiaSemana;
    private JFormattedTextField jftHoraInicio;
    private JFormattedTextField jftHoraTermino;
    private JTable jtaDiaSemanaEHorarioAdicionados; 
    private JComboBox jcbProfissional;
    private JCheckBox jchParticipante;
    private JTable jtaProfissionaisVinculosAdicionados;
    private JComboBox jcbLocalAtendimento;
    private JFormattedTextField jftVagas;
    private JFormattedTextField jftCargaHoraria;
    private JFormattedTextField jftCargaHorariaMinima;
    private JTable jtaProgramacao;
    
    public ControladorTelaGruposEditarModuloPeriodo(JDialog telaPai, ModuloPeriodoDTO moduloPeriodoDto, DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO, JComboBox jcbModuloAtividade, JComboBox jcbDiaSemana, JFormattedTextField jftHoraInicio, JFormattedTextField jftHoraTermino, JTable jtaDiaSemanaEHorarioAdicionados, JComboBox jcbProfissional, JCheckBox jchParticipante, JTable jtaProfissionaisVinculosAdicionados, JComboBox jcbLocalAtendimento, JFormattedTextField jftVagas, JFormattedTextField jftCargaHoraria, JFormattedTextField jftCargaHorariaMinima, JTable jtaProgramacao){
        super(telaPai, moduloPeriodoDto);
        this.descricaoTipoAtendimentoDTO = descricaoTipoAtendimentoDTO != null ? descricaoTipoAtendimentoDTO : new DescricaoTipoAtendimentoDTO();
        this.jcbModuloAtividade = jcbModuloAtividade;
        this.jcbDiaSemana = jcbDiaSemana;
        this.jftHoraInicio = jftHoraInicio;
        this.jftHoraTermino = jftHoraTermino;
        this.jtaDiaSemanaEHorarioAdicionados = jtaDiaSemanaEHorarioAdicionados;
        this.jcbProfissional = jcbProfissional;
        this.jchParticipante = jchParticipante;
        this.jtaProfissionaisVinculosAdicionados = jtaProfissionaisVinculosAdicionados;
        this.jcbLocalAtendimento = jcbLocalAtendimento;
        this.jftVagas = jftVagas;
        this.jftCargaHoraria = jftCargaHoraria;
        this.jftCargaHorariaMinima = jftCargaHorariaMinima;
        this.jtaProgramacao = jtaProgramacao;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
        configurarColunasTabela();
    }
    
    private void configurarColunasTabela(){
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaProgramacao);
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaProfissionaisVinculosAdicionados);
    }
        
    @Override
    public void carregarCampos() {
        selecionarDto(jcbModuloAtividade, obterObjetoDTO().getModuloDto());
        adicionarDtos(obterObjetoDTO().getDiasSemanaEHorariosDaAtividadeDto(), jtaDiaSemanaEHorarioAdicionados);
        adicionarDtos(obterObjetoDTO().getProfissionaisVinculoDto(), jtaProfissionaisVinculosAdicionados);
        selecionarDto(jcbLocalAtendimento, obterObjetoDTO().getLocalAtendimentoDTO());
        jftVagas.setText(obterObjetoDTO().getVagas());
        jftCargaHoraria.setText(obterObjetoDTO().getCargaHoraria());
        jftCargaHorariaMinima.setText(obterObjetoDTO().getCargaHorariaMinima());
        adicionarDtos(obterObjetoDTO().getProgramacaoDto(), jtaProgramacao);
    }
    
    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbModuloAtividade, descricaoTipoAtendimentoDTO.getModulosDto());
        adicionarDtos(jcbDiaSemana, resultadoListagemDiaSemanaDTO.getObjetosDto());
        adicionarDtos(jcbProfissional, resultadoListagemProfissionalDTO.getObjetosDto());
        adicionarDtos(jcbLocalAtendimento, resultadoListagemLocalAtendimentoDTO.getObjetosDto());
    }
     
    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemDiaSemanaDTO = servicoSisLaraServer.obterListagemDiaSemana();
            resultadoListagemProfissionalDTO = servicoSisLaraServer.obterListagemProfissionalAtivos();
            resultadoListagemLocalAtendimentoDTO = servicoSisLaraServer.obterListagemLocalAtendimento();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoDTO) servicoSisLaraServer.validarModuloPeriodo(obterObjetoDTO());
    }

    @Override
    protected void prepararDtoParaEditar() {
        ModuloPeriodoDTO moduloPeriodoDto = obterObjetoDTO();
        moduloPeriodoDto.setModuloDto((ModuloDTO)obterDtoSelecionado(jcbModuloAtividade));
        moduloPeriodoDto.setDiasSemanaEHorariosDaAtividadeDto((List<DiaSemanaEHorarioDTO>) obterDtos(jtaDiaSemanaEHorarioAdicionados));
        moduloPeriodoDto.setProfissionaisVinculoDto((List<ProfissionalVinculoDTO>) obterDtos(jtaProfissionaisVinculosAdicionados));
        moduloPeriodoDto.setLocalAtendimentoDTO((LocalAtendimentoDTO)obterDtoSelecionado(jcbLocalAtendimento));
        moduloPeriodoDto.setVagas(jftVagas.getText());
        moduloPeriodoDto.setCargaHoraria(jftCargaHoraria.getText());
        moduloPeriodoDto.setCargaHorariaMinima(jftCargaHorariaMinima.getText());
        moduloPeriodoDto.setProgramacaoDto((List<ProgramacaoDTO>)obterDtos(jtaProgramacao));
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
        return Sessao.CHAVE_MODULO_PERIODO;
    }

    private ModuloPeriodoDTO obterObjetoDTO() {
        return (ModuloPeriodoDTO)objetoDto;
    }

    public void adicionarProfissional() {
        ProfissionalVinculoDTO profissionalVinculoDTO = new ProfissionalVinculoDTO();
        profissionalVinculoDTO.setProfissionalDto((ProfissionalDTO)obterDtoSelecionado(jcbProfissional));
        profissionalVinculoDTO.setParticipante(jchParticipante.isSelected());
        adicionarNaListaDtoSelecionadoPeloComboECheck(jtaProfissionaisVinculosAdicionados, jcbProfissional, jchParticipante, profissionalVinculoDTO);
    }

    public void removerProfissional() {
        removerDtoSelecionado(jtaProfissionaisVinculosAdicionados);
    }

    public void adicionarProgramacao() {
        new TelaGruposEditarProgramacao((JDialog) telaPai, new ProgramacaoDTO());
        String chave = Sessao.CHAVE_PROGRAMACAO;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            obterModeloTabelaProgramacao().adicionarDTO((ProgramacaoDTO) Sessao.obterInstancia().obterDto(chave));
        }
    }

    public void removerProgramacaoSelecionada() {
        removerDtoSelecionado(jtaProgramacao);
    }

    public void alterarProgramacao() {
        if (estaComItemValidoSelecionado(jtaProgramacao)){
            new TelaGruposEditarProgramacao((JDialog) telaPai, (ProgramacaoDTO)obterDtoSelecionado(jtaProgramacao));
            String chave = Sessao.CHAVE_PROGRAMACAO;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaProgramacao, (ProgramacaoDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }
    
    private ModeloTabela obterModeloTabelaProgramacao(){
        return ((ModeloTabelaProgramacao)jtaProgramacao.getModel());
    }

    public void abrirTelaDeAlteracaoProgramacao(KeyEvent evt) {
        if (teclaEnterPressionada(evt)){
            alterarProgramacao();
        }
    }

    public void adicionarDiaSemanaEHorario() {
        DiaSemanaEHorarioDTO diaSemanaEHorarioDTO = new DiaSemanaEHorarioDTO();
        diaSemanaEHorarioDTO.setDiaSemanaDto((DiaSemanaDTO) obterDtoSelecionado(jcbDiaSemana));
        diaSemanaEHorarioDTO.setHorarioDto(new HorarioDTO(jftHoraInicio.getText(), jftHoraTermino.getText()));
        try {
            ResultadoValidacaoDiaSemanaEHorarioDTO resultadoValidacaoDiaSemanaEHorarioDTO = servicoSisLaraServer.validarDiaSemanaEHorario(diaSemanaEHorarioDTO);
            if (resultadoValidacaoDiaSemanaEHorarioDTO.sucesso()) {
                adicionarDtoSemRepetir(diaSemanaEHorarioDTO, jtaDiaSemanaEHorarioAdicionados);
                limparCampoDiaSemanaEHorario();
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoValidacaoDiaSemanaEHorarioDTO.obterMensagens());
            }
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_VALIDACAO_DIA_SEMANA_E_HORARIO + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_VALIDACAO_DIA_SEMANA_E_HORARIO);
        }
    }

    public void removerDiaSemanaEHorario() {
        removerDtoSelecionado(jtaDiaSemanaEHorarioAdicionados);
    }

    private void limparCampoDiaSemanaEHorario() {
        deselecionarDto(jcbDiaSemana);
        jftHoraInicio.setText("");
        jftHoraTermino.setText("");
    }
}

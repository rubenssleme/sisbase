package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoCopiaAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

public class ControladorTelaAtendimentoIndividualCopiar extends ControladorTelaEditar {
    
    private JFormattedTextField jftHoraInicio;
    private JFormattedTextField jftHoraTermino;
    
    public ControladorTelaAtendimentoIndividualCopiar(JDialog telaPai, AtendimentoIndividualDTO atendimentoIndividualDto, JFormattedTextField jftHoraInicio, JFormattedTextField jftHoraTermino){
        super(telaPai, atendimentoIndividualDto);
        this.jftHoraInicio = jftHoraInicio;
        this.jftHoraTermino = jftHoraTermino;
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.copiarAtendimentoIndividual(obterEspecificacaoDTO(), obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }
    
    private EspecificacaoCopiaAtendimentoIndividualDTO obterEspecificacaoDTO(){
        EspecificacaoCopiaAtendimentoIndividualDTO especificacaoCopiaAtendimentoIndividualDto = new EspecificacaoCopiaAtendimentoIndividualDTO();
        especificacaoCopiaAtendimentoIndividualDto.setHorarioDto(new HorarioDTO(jftHoraInicio.getText(), jftHoraTermino.getText()));
        return especificacaoCopiaAtendimentoIndividualDto;
    }
       
    private AtendimentoIndividualDTO obterObjetoDTO(){
        return (AtendimentoIndividualDTO)objetoDto;
    }
    
    @Override
    protected void prepararDtoParaEditar() {
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
        return Sessao.CHAVE_ATENDIMENTO_INDIVIDUAL;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void obterDadosAuxiliares() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void carregarCampos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoGeracaoAtendimentosDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class ControladorTelaGruposLancamentoAtendimento extends ControladorTelaEditar {
 
    private ModuloPeriodoDTO moduloAtividadePeriodoDto;
 
    private JTextField jtfGrupo;
    private JTextField jtfModuloPeriodo;
    private JFormattedTextField jftData;
    private JFormattedTextField jftHoraInicio;
    private JFormattedTextField jftHoraTermino;
    
    
    public ControladorTelaGruposLancamentoAtendimento(JDialog telaPai, GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto, JTextField jtfGrupo, JTextField jtfModuloPeriodo, JFormattedTextField jftData, JFormattedTextField jftHoraInicio, JFormattedTextField jftHoraTermino){
        super(telaPai, grupoDto);
        this.moduloAtividadePeriodoDto = moduloPeriodoDto;
        this.jtfGrupo = jtfGrupo;
        this.jtfModuloPeriodo = jtfModuloPeriodo;
        this.jftData = jftData;
        this.jftHoraInicio = jftHoraInicio;
        this.jftHoraTermino = jftHoraTermino;
        atualizarInformacoesGrupoeModuloPeriodo();
    }
    
    private void atualizarInformacoesGrupoeModuloPeriodo(){
        jtfGrupo.setText(obterObjetoDTO().toStringApenasNomeETurma());
        jtfModuloPeriodo.setText(moduloAtividadePeriodoDto.toString());
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.gerarAtendimentos(new EspecificacaoGeracaoAtendimentosDTO(obterObjetoDTO().getId(), moduloAtividadePeriodoDto.getId(), jftData.getText(), jftHoraInicio.getText(), jftHoraTermino.getText()), Sessao.obterInstancia().obterToken());
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
        return Sessao.CHAVE_MODULO_PERIODO;
    }
    
    private GrupoDTO obterObjetoDTO(){
        return (GrupoDTO)objetoDto;
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

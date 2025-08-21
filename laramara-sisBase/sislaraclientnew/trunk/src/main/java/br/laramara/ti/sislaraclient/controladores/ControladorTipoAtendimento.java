package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.TipoAtendimentoDTO;
import java.util.List;
import javax.swing.JComboBox;

public class ControladorTipoAtendimento extends ControladorTela {
    
    private ResultadoListagemTipoAtendimentoDTO resultadoListagemTipoAtendimentoDto;
    
    private JComboBox jcbTipoAtendimento;
    private JComboBox jcbDescricaoTipoAtendimento;
    private JComboBox jcbModuloAtividade;
    private JComboBox jcbSetor;
    private JComboBox jcbNomeGrupo;
    
    public ControladorTipoAtendimento(JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade){
        this(jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade, null, null);
    }

    public ControladorTipoAtendimento(JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade, JComboBox jcbSetor){
        this(jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade, jcbSetor, null);
    }

    public ControladorTipoAtendimento(JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade, JComboBox jcbSetor, JComboBox jcbNomeGrupo){
        super(null);
        this.jcbTipoAtendimento = jcbTipoAtendimento;
        this.jcbDescricaoTipoAtendimento = jcbDescricaoTipoAtendimento;
        this.jcbModuloAtividade = jcbModuloAtividade;
        this.jcbSetor = jcbSetor;
        this.jcbNomeGrupo = jcbNomeGrupo;
        inicializarComboxDadosAuxiliares();
    }
    
    public void inicializarDescricaoTipoAtendimento() {
        List<? extends ModeloDTO> objetosDto = null;
        if (estaComItemValidoSelecionado(jcbTipoAtendimento)) {
            objetosDto = ((TipoAtendimentoDTO) obterDtoSelecionado(jcbTipoAtendimento)).getDescricoesTipoAtendimentoDto();
        }
        carregarCombosDependentes(jcbTipoAtendimento, jcbDescricaoTipoAtendimento, objetosDto);
    }
    
    public void inicializarModuloAtividade(){
       List<? extends ModeloDTO> objetosDto = null;
        if (estaComItemValidoSelecionado(jcbDescricaoTipoAtendimento)) {
            objetosDto = ((DescricaoTipoAtendimentoDTO) obterDtoSelecionado(jcbDescricaoTipoAtendimento)).getModulosDto();
        }
        carregarCombosDependentes(jcbDescricaoTipoAtendimento, jcbModuloAtividade, objetosDto);
    }
    
    public void inicializarModuloAtividadeESetor() {
        inicializarModuloAtividade();
        inicializarSetor();
    }
    
    public void inicializarSetor(){
        List<? extends ModeloDTO> objetosDto = null;
        if (estaComItemValidoSelecionado(jcbDescricaoTipoAtendimento)) {
            objetosDto = ((DescricaoTipoAtendimentoDTO) obterDtoSelecionado(jcbDescricaoTipoAtendimento)).getSetoresDto();
        }
        carregarCombosDependentes(jcbDescricaoTipoAtendimento, jcbSetor, objetosDto);
    }

    private void inicializarComboxDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbTipoAtendimento, resultadoListagemTipoAtendimentoDto.getObjetosDto());
    }
    
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemTipoAtendimentoDto = servicoSisLaraServer.obterListagemTipoAtendimento();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    public void inicializarNomeGrupo() {
        List<? extends ModeloDTO> objetosDto = null;
        if (estaComItemValidoSelecionado(jcbDescricaoTipoAtendimento)) {
            objetosDto = ((DescricaoTipoAtendimentoDTO) obterDtoSelecionado(jcbDescricaoTipoAtendimento)).getNomesGruposDto();
        }
        carregarCombosDependentes(jcbDescricaoTipoAtendimento, jcbNomeGrupo, objetosDto);
    }
}

package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaGruposEditarIntegrantes;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class ControladorTelaGruposGerenIntegrantes extends ControladorTela {

    private GrupoDTO grupoDtoSelecionado;
    private JTextField jtfGrupo;
    private JComboBox jcbModuloPeriodo;
    
    public ControladorTelaGruposGerenIntegrantes(JDialog telaPai, JTextField jtfGrupo, JComboBox jcbModuloPeriodo){
        super(telaPai);
        this.jtfGrupo = jtfGrupo;
        this.jcbModuloPeriodo = jcbModuloPeriodo;
    }

    public void inicializarModuloAtividade() {
        if (!jtfGrupo.getText().isEmpty()){
            grupoDtoSelecionado = inicializarModuloPeriodo(jcbModuloPeriodo, jtfGrupo);
        }
    }

    public void alterarIntegrantes() {
        if (estaComItemValidoSelecionado(jcbModuloPeriodo)) {
            new TelaGruposEditarIntegrantes((JDialog) telaPai, grupoDtoSelecionado, ((ModuloPeriodoDTO)obterDtoSelecionado(jcbModuloPeriodo)));
            String chave = Sessao.CHAVE_MODULO_PERIODO;
            if (Sessao.obterInstancia().possuiDto(chave)){
                int indiceSelecionado = jcbModuloPeriodo.getSelectedIndex();
                inicializarModuloAtividade();
                jcbModuloPeriodo.setSelectedIndex(indiceSelecionado);
                Sessao.obterInstancia().obterDto(chave);
            }
        }
    }
}

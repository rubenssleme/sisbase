package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemFrequenciaDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ControladorTelaGruposEditarDescricaoIndividualProfissional extends ControladorTelaGruposEditarDescricaoIndividual {

    public ControladorTelaGruposEditarDescricaoIndividualProfissional(JDialog telaPai, GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto, AtendimentoGrupoDTO atendimentoDto, AtendimentoBaseDTO atendimentoBaseDto, JTextField jtfGrupo, JTextField jtfModuloAtividade, JTextField jtfData, JTextField jtfHoraInicio, JTextField jtfHoraTermino, JTextField jtfNome, JPanel jpaParticipacao, JComboBox jcbParticipacaoSelecionada, JTextField jtfQuantidadePessoas, JList jliParticipacoesAdicionadas, JTextArea jatDescricao, JComboBox jcbFrequencia, JTextArea jatJustificativa, JList jliArquivos) {
        super(telaPai, grupoDto, moduloPeriodoDto, atendimentoDto, atendimentoBaseDto, jtfGrupo, jtfModuloAtividade, jtfData, jtfHoraInicio, jtfHoraTermino, jtfNome, jpaParticipacao, jcbParticipacaoSelecionada, jtfQuantidadePessoas, jliParticipacoesAdicionadas, jatDescricao, jcbFrequencia, jatJustificativa, jliArquivos);
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.validarAtendimentoAuxiliarBase(obterObjetoDTO());
    }
     
    @Override
    public void carregarCampos() {
        jtfNome.setText(((AtendimentoProfissionalDTO) obterObjetoDTO()).getProfissionalDto().toString());
        super.carregarCampos();
    }
    
    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_ATENDIMENTO_PROFISSIONAL;
    }
    
    @Override
    protected ResultadoListagemFrequenciaDTO obterListagemFrequencia() throws RemoteException {
        return servicoSisLaraServer.obterListagemFrequenciaDoProfissional();
    }
}

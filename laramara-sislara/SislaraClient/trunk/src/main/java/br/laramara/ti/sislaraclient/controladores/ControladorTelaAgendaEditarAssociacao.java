package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoEdicaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControladorTelaAgendaEditarAssociacao extends ControladorTelaAgendaEditar {
    
    private JPanel jpaDadosAgendamento;
    private JPanel jpaDadosObservacoes;
    private AgendamentoDTO agendamentoDto;
    
    public  ControladorTelaAgendaEditarAssociacao(JDialog telaPai, AgendamentoDTO agendamentoDto, JTextField jtfNomePreCadastro, JComboBox jcbProfissional, JList jliProfissionaisAgendados, JComboBox jcbReservaPara, JComboBox jcbTipoAtendimento, JComboBox jcbDescricaoTipoAtendimento, JComboBox jcbModuloAtividade, JComboBox jcbSetor, JComboBox jcbLocalAtendimento, JFormattedTextField jftDataInicio, JFormattedTextField jftDataTermino, JComboBox jcbDiaSemana, JFormattedTextField jftHoraInicio, JFormattedTextField jftHoraTermino, JEditorPane jepObs, JPanel jpaDadosAgendamento, JPanel jpaDadosObservacoes){
        super(telaPai, jtfNomePreCadastro, jcbProfissional, jliProfissionaisAgendados, jcbReservaPara, jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade, jcbSetor, jcbLocalAtendimento, jftDataInicio, jftDataTermino, jcbDiaSemana, jftHoraInicio, jftHoraTermino, jepObs);
        this.agendamentoDto = agendamentoDto;
        this.jpaDadosAgendamento = jpaDadosAgendamento;
        this.jpaDadosObservacoes = jpaDadosObservacoes;
        desabilitarComponentesDesnecessarios();
        posicionarComponentes();
        carregarCampos();
    }
    
    private void desabilitarComponentesDesnecessarios(){
        jpaDadosAgendamento.setVisible(false);
    }
    
    private void posicionarComponentes(){
        jpaDadosObservacoes.setLocation(10, 235);
    }
    
    @Override
    public void salvar() {
        int resposta = JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO);
        if (resposta == JOptionPane.OK_OPTION) {
            try {
                ResultadoEdicaoAgendamentoDTO resultado = servicoSisLaraServer.editarAgendamento(obterObjetoDto(), Sessao.obterInstancia().obterToken());
                if (resultado.sucesso()) {
                    Sessao.obterInstancia().armazenarDTO(Sessao.CHAVE_AGENDAMENTO, resultado.obterObjetoDtoEditado());
                    JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultado.obterMensagens());
                    fecharTela();
                } else {
                    JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
                }
            } catch (Exception ex) {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_GERACAO_AGENDAMENTO);
                logger.error(JOptionPanePersonalizado.ERRO_NA_GERACAO_AGENDAMENTO + ex.getMessage());
            }
        }
    }

    private AgendamentoDTO obterObjetoDto() {
        agendamentoDto.setUsuarioDto(usuarioDto);
        agendamentoDto.setPreCadastroDto(preCadastroDto);
        agendamentoDto.setObs(jepObs.getText());
        
        return agendamentoDto;
    }

    private void carregarCampos() {
        jepObs.setText(agendamentoDto.getObs());
    }
}

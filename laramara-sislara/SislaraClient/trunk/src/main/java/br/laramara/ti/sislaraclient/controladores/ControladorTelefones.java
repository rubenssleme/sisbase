package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoValidacaoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TipoTelefoneDTO;
import java.awt.Window;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

class ControladorTelefones extends ControladorTela {

    private JComboBox jcbTipoTelefone;
    private JFormattedTextField jftTelefone;
    private JList jliTelefonesAdicionados;

    public ControladorTelefones(Window telaPai, JComboBox jcbTipoTelefone, JFormattedTextField jftTelefone, JList jliTelefonesAdicionados) {
        super(telaPai);
        this.jcbTipoTelefone = jcbTipoTelefone;
        this.jftTelefone = jftTelefone;
        this.jliTelefonesAdicionados = jliTelefonesAdicionados;
    }

    public void adicionarTelefone() {
        TipoTelefoneDTO tipoTelefoneDto = (TipoTelefoneDTO) obterDtoSelecionado(jcbTipoTelefone);
        TelefoneDTO telefoneDto = new TelefoneDTO(tipoTelefoneDto, jftTelefone.getText());
        try {
            ResultadoValidacaoTelefoneDTO resultadoValidacaoTelefoneDTO = servicoSisLaraServer.validarTelefone(telefoneDto);
            if (resultadoValidacaoTelefoneDTO.sucesso()) {
                adicionarDtos(resultadoValidacaoTelefoneDTO.obterObjetoDtoEditado(), jliTelefonesAdicionados);
                limparCamposTelefone();
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoValidacaoTelefoneDTO.obterMensagens());
            }
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_ADICAO_TELEFONE);
            logger.error(JOptionPanePersonalizado.ERRO_ADICAO_TELEFONE + ex.getMessage());
        }
    }

    private void limparCamposTelefone() {
        deselecionarDto(jcbTipoTelefone);
        jftTelefone.setText(null);
    }
        
    public void removerTelefoneSelecionado() {
        if ((estaComItemValidoSelecionado(jliTelefonesAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliTelefonesAdicionados);
        }
    }
}

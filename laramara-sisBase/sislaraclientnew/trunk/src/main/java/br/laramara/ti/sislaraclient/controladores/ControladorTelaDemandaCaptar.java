package br.laramara.ti.sislaraclient.controladores;

import static br.laramara.ti.sislaraclient.controladores.ControladorTela.logger;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.doacao.DemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoCaptacaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ReciboDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoCaptacaoCartelaDeSelosDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorTelaDemandaCaptar extends ControladorTela {

    private DemandaDTO demandaDto;
    private ReciboDTO reciboSelecionadoDto;
    private JFormattedTextField jftCpfCnpj;
    private JTextField jtfNomePatrocinador;
    private JTextField jtfValor;
    private JFormattedTextField jftNumeroRecibo;
    private JTextField jtfValorTotalRecibo;

    public ControladorTelaDemandaCaptar(JDialog telaPai, DemandaDTO demandaDto, JFormattedTextField jftCpfCnpj, JTextField jtfNomePatrocinador, JTextField jtfValor, JFormattedTextField jftNumeroRecibo, JTextField jtfValorTotalRecibo) {
        super(telaPai);
        this.demandaDto = demandaDto;
        this.jftCpfCnpj = jftCpfCnpj;
        this.jtfNomePatrocinador = jtfNomePatrocinador;
        this.jtfValor = jtfValor;
        this.jftNumeroRecibo = jftNumeroRecibo;
        this.jtfValorTotalRecibo = jtfValorTotalRecibo;
    }
    
    public void captar() {
        if (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO) == JOptionPane.OK_OPTION) {
            EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();
            especificacaoCaptacaoDemandaDTO.setDemandaDto(demandaDto);
            especificacaoCaptacaoDemandaDTO.setValor(jtfValor.getText());
            especificacaoCaptacaoDemandaDTO.setReciboDto(reciboSelecionadoDto);
            try {
                ResultadoCaptacaoCartelaDeSelosDTO resultadoCaptacaoCartelaDeSelosDTO = servicoSisLaraServer.captar(especificacaoCaptacaoDemandaDTO, Sessao.obterInstancia().obterToken());
                if (resultadoCaptacaoCartelaDeSelosDTO.sucesso()) {
                    servicoSisLaraServer.gravarTela(obterTela());
                    Sessao.obterInstancia().armazenarDTO(Sessao.CHAVE_DEMANDA, resultadoCaptacaoCartelaDeSelosDTO.obterObjetoDtoEditado());
                    JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultadoCaptacaoCartelaDeSelosDTO.obterMensagens());
                    fecharTela();
                } else {
                    JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoCaptacaoCartelaDeSelosDTO.obterMensagens());
                }
            } catch (Exception e) {
                logger.error(JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\n Detalhes: " + e);
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\nDetalhes: " + e.getMessage());
            }
        }
    }

    private void atualizaCamposCpfCnpjEValorTotalRecibo(ReciboDTO reciboDto){
        jtfValorTotalRecibo.setText(reciboDto.getValorTotalRecibo());
        jftCpfCnpj.setText(reciboDto.getCpfCnpj());
        jtfNomePatrocinador.setText(reciboDto.getNome());
    }
    
    private void limparCamposCpfCnpjEValorTotalRecibo(){
        jtfValorTotalRecibo.setText("");
        jftCpfCnpj.setText("");
        jtfNomePatrocinador.setText("");
    }
        
    public void carregarCpfCnpjEValorTotalRecibo() {
       try {
            ReciboDTO reciboDto = servicoSisLaraServer.obterReciboDto(jftNumeroRecibo.getText().trim());
            if (reciboDto != null) {
                atualizaCamposCpfCnpjEValorTotalRecibo(reciboDto);
                reciboSelecionadoDto = reciboDto;
            } else {
                limparCamposCpfCnpjEValorTotalRecibo();
                reciboSelecionadoDto = null;
            }
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + "\nDetalhes: " + e.getMessage());
        }
    }
}

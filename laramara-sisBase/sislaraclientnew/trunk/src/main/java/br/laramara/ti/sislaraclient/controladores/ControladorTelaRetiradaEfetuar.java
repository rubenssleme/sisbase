package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.retirada.ResultadoEdicaoRetiradaDTO;
import br.laramara.ti.sislaracommons.dtos.retirada.RetiradaDTO;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class ControladorTelaRetiradaEfetuar extends ControladorTelaRetirada{
    
    private ResultadoListagemProfissionalDTO resultadoListagemProfissionalDto;
    private ResultadoListagemProfissionalDTO resultadoListagemVoluntarioDto;

    private JComboBox jcbProfissional;
    private JComboBox jcbVoluntario;
    
    public ControladorTelaRetiradaEfetuar(JDialog telaPai, JFormattedTextField jftProntuario, JTextField jtfNome, JComboBox jcbProfissional, JComboBox jcbVoluntario){
        super(telaPai, jftProntuario, jtfNome);
        this.jcbProfissional = jcbProfissional;
        this.jcbVoluntario = jcbVoluntario;
        inicializarCombosDadosAuxiliares();
    }
    
    private void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbProfissional, resultadoListagemProfissionalDto.getObjetosDto());
        adicionarDtos(jcbVoluntario, resultadoListagemVoluntarioDto.getObjetosDto());
    }
    
    private void obterDadosAuxiliares() {
        try {
            resultadoListagemProfissionalDto = servicoSisLaraServer.obterListagemProfissionalAtivos();
            resultadoListagemVoluntarioDto = servicoSisLaraServer.obterListagemVoluntarioAtivos();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    @Override
    public void efetuar() {
        RetiradaDTO retiradaDTO = new RetiradaDTO();
        retiradaDTO.setProntuario(NumeroUtils.retornaLongoOuInvalido(jftProntuario.getText()));
        retiradaDTO.setProfissionalDto((ProfissionalDTO)obterDtoSelecionado(jcbProfissional));
        retiradaDTO.setVoluntarioDto((ProfissionalDTO)obterDtoSelecionado(jcbVoluntario));
        try {
            ResultadoEdicaoRetiradaDTO resultadoEdicaoRetiradaDto = servicoSisLaraServer.efetuarRetirada(retiradaDTO, Sessao.obterInstancia().obterToken());
            if (resultadoEdicaoRetiradaDto.sucesso()){
                JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultadoEdicaoRetiradaDto.obterMensagens());
                fecharTela();
            }else{
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoEdicaoRetiradaDto.obterMensagens());
            }
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_EFETUACAO_RETIRADA);
            logger.error(JOptionPanePersonalizado.ERRO_NA_EFETUACAO_RETIRADA + ex.getMessage());
        }
    }
}

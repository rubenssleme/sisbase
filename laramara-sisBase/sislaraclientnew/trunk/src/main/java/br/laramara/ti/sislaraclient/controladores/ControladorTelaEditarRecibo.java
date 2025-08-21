
package br.laramara.ti.sislaraclient.controladores;

import static br.laramara.ti.sislaraclient.controladores.ControladorTela.logger;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.FilialDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ReciboDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemFilialDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoReciboDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class ControladorTelaEditarRecibo extends ControladorTelaEditar{

    private JTextField jtfNumero;
    private JTextField jtfDataRegistro;
    private JComboBox jcbFilial;
    private JFormattedTextField jftData;
    private JTextField jtfValor;
    private JTextField jtfNome;
    private JFormattedTextField jftCpfCnpj;
    private JEditorPane jepDescricao;
    
    private ResultadoListagemFilialDTO resultadoListagemFilialDto;
    
    public ControladorTelaEditarRecibo(JDialog telaPai, ReciboDTO reciboDto, JTextField jtfNumero, JTextField jtfDataRegistro, JComboBox jcbFilial, JFormattedTextField jftData, JTextField jtfValor, JTextField jtfNome, JFormattedTextField jftCpfCnpj, JEditorPane jepDescricao) {
        super(telaPai, reciboDto);
        this.jtfNumero = jtfNumero;
        this.jtfDataRegistro = jtfDataRegistro;
        this.jcbFilial = jcbFilial;
        this.jftData = jftData;
        this.jtfValor = jtfValor;
        this.jtfNome = jtfNome;
        this.jftCpfCnpj = jftCpfCnpj;
        this.jepDescricao = jepDescricao;
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.editarRecibo(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setCpfCnpj(jftCpfCnpj.getText());
        obterObjetoDTO().setData(jftData.getText());
        obterObjetoDTO().setFilialDTO((FilialDTO)obterDtoSelecionado(jcbFilial));
        obterObjetoDTO().setNome(jtfNome.getText());
        obterObjetoDTO().setValorTotalRecibo(jtfValor.getText());
        obterObjetoDTO().setDescricao(jepDescricao.getText());
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        return obterObjetoDTO().getId() != null;
    }

    @Override
    protected void executarAcaoAposInclusao() {
        try {
            exibir(servicoSisLaraServer.obterRelatorioRecibo(obterObjetoDTO().getId(), Sessao.obterInstancia().obterToken()));
        } catch (Exception ex) {
            logger.error("Erro durante solicitação de relatório. \nDetalhes:" + ex);
        }
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_RECIBO;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbFilial, resultadoListagemFilialDto.getObjetosDto());
    }

    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemFilialDto = servicoSisLaraServer.obterListagemFilial();
        } catch (Exception e) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + e.getMessage());
        }
    }

    private ReciboDTO obterObjetoDTO(){
        return (ReciboDTO)objetoDto;
    }
        
    @Override
    public void carregarCampos() {
        if (verificarSeDtoEstaPreenchido()) {
            jtfNumero.setText(obterObjetoDTO().getId().toString());
            jtfDataRegistro.setText(obterObjetoDTO().getDataRegistro());
            selecionarDto(jcbFilial, obterObjetoDTO().getFilial());
            jftData.setText(obterObjetoDTO().getData());
            jtfValor.setText(obterObjetoDTO().getValorTotalRecibo());
            jtfNome.setText(obterObjetoDTO().getNome());
            jftCpfCnpj.setText(obterObjetoDTO().getCpfCnpj());
            jepDescricao.setText(obterObjetoDTO().getDescricao());
        }
    }

    public void carregarNomeRecibo() {
        try {
            ResultadoReciboDTO resultadoReciboDTO = servicoSisLaraServer.obterReciboMaisRecentePorCpfCnpj(jftCpfCnpj.getText().trim());
            if (resultadoReciboDTO.sucesso() && resultadoReciboDTO.obterObjetoDtoEditado() != null && estaVazio(jtfNome)) {
                jtfNome.setText(((ReciboDTO) resultadoReciboDTO.obterObjetoDtoEditado()).getNome());
            }
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + "\nDetalhes: " + e.getMessage());
        }
    }
}

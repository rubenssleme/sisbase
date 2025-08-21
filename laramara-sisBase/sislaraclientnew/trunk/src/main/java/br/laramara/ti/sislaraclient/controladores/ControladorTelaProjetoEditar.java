package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TelaUtils;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoEdicaoProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoLoteRecursoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ControladorTelaProjetoEditar extends ControladorTelaEditar{

    private ResultadoListagemRecursoDTO resultadoListagemRecursoDTO;
           
    
    private JTextField jtfNome;
    private JFormattedTextField jftDataInicial;
    private JFormattedTextField jftDataFinal;
    private JTextField jtfValorProdutos;
    private JTextField jtfOutrosValores;
    private JCheckBox jchAtivo;
    private JComboBox jcbRecurso;
    private JTextField jtfValorProduto;
    private JFormattedTextField jftQuantidade;
    private JList jliRecursosAdicionados;
    private JTextField jtfSomaTotalProdutos;
    private JTextArea jtaResumoReserva;
    
    public ControladorTelaProjetoEditar(JDialog telaPai, ProjetoDTO projetoDto, JTextField jtfNome, JFormattedTextField jftDataInicial, JFormattedTextField jftDataFinal, JTextField jtfValorProdutos, JTextField jtfOutrosValores, JCheckBox jchAtivo, JComboBox jcbRecurso, JTextField jtfValorProduto, JFormattedTextField jftQuantidade, JList jliRecursosAdicionados, JTextField jtfSomaTotalProdutos, JTextArea jtaResumoReserva){
             super(telaPai, projetoDto);
             this.jtfNome = jtfNome;
             this.jftDataInicial =jftDataInicial;
             this.jftDataFinal = jftDataFinal;
             this.jtfValorProdutos = jtfValorProdutos;
             this.jtfOutrosValores = jtfOutrosValores;
             this.jchAtivo = jchAtivo;
             this.jcbRecurso = jcbRecurso;
             this.jtfValorProduto = jtfValorProduto;
             this.jftQuantidade = jftQuantidade;
             this.jliRecursosAdicionados = jliRecursosAdicionados;
             this.jtfSomaTotalProdutos = jtfSomaTotalProdutos;
             this.jtaResumoReserva = jtaResumoReserva;
             inicializarCombosDadosAuxiliares();            
             carregarCampos();
             TelaUtils.habilitarSomenteUpperCaseNosCamposTexto(this);
    }
 
    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbRecurso, resultadoListagemRecursoDTO.getObjetosDto());        
    }

    @Override
    public void obterDadosAuxiliares() {
        try {            
            resultadoListagemRecursoDTO = servicoSisLaraServer.obterListagemRecurso();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    public void adicionarRecurso(){
        RecursoDTO recursoDto = (RecursoDTO) obterDtoSelecionado(jcbRecurso);
        LoteRecursoDTO loteRecursoDto = new LoteRecursoDTO(recursoDto, jftQuantidade.getText(), jtfValorProduto.getText());
        try {
            ResultadoValidacaoLoteRecursoDTO resultadoValidacaoLoteRecursoDTO = servicoSisLaraServer.validarLoteRecurso(loteRecursoDto);
            if (resultadoValidacaoLoteRecursoDTO.sucesso()) {
                adicionarDtos(resultadoValidacaoLoteRecursoDTO.obterObjetoDtoEditado(), jliRecursosAdicionados);
                limparCamposLoteRecurso();
                atualizarTotalProdutos();
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoValidacaoLoteRecursoDTO.obterMensagens());
            }
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_ADICAO_LOTE_RECURSO);
            logger.error(JOptionPanePersonalizado.ERRO_ADICAO_LOTE_RECURSO + ex.getMessage());
        }
    }
    
    public void removerRecursoSelecionado(){
        if ((estaComItemValidoSelecionado(jliRecursosAdicionados) && (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO)) == JOptionPane.OK_OPTION)) {
            removerDtoSelecionado(jliRecursosAdicionados);
            atualizarTotalProdutos();
        }
    }    

    private void limparCamposLoteRecurso() {
        deselecionarDto(jcbRecurso);
        jftQuantidade.setText("");
        jtfValorProduto.setText("");
    }

    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.editarProjeto(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setNome(jtfNome.getText());
        obterObjetoDTO().setDataInicial(jftDataInicial.getText());
        obterObjetoDTO().setDataFinal(jftDataFinal.getText());
        obterObjetoDTO().setValorProdutos(jtfValorProdutos.getText());
        obterObjetoDTO().setValorOutros(jtfOutrosValores.getText());
        obterObjetoDTO().setAtivo(jchAtivo.isSelected());
        obterObjetoDTO().setLoteRecursoDto((List<LoteRecursoDTO>) obterDtos(jliRecursosAdicionados));                
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        return obterObjetoDTO().getId() != null;
    }

    @Override
    protected void executarAcaoAposInclusao() {        
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_PROJETO;
    }
    
    private ProjetoDTO obterObjetoDTO() {
        return (ProjetoDTO) objetoDto;
    }

    @Override
    public void carregarCampos() {
        if (verificarSeDtoEstaPreenchido()) {            
            jtfNome.setText(obterObjetoDTO().getNome());
            jftDataInicial.setText(obterObjetoDTO().getDataInicial());
            jftDataFinal.setText(obterObjetoDTO().getDataFinal());
            jtfValorProdutos.setText(obterObjetoDTO().getValorProdutos());
            jtfOutrosValores.setText(obterObjetoDTO().getValorOutros());
            jchAtivo.setSelected(obterObjetoDTO().isAtivo());
            adicionarDtos(obterObjetoDTO().getLoteRecursoDto(), jliRecursosAdicionados);   
            jtaResumoReserva.setText(obterObjetoDTO().getResumoReservas());
            atualizarTotalProdutos();
        }
    }

    public void preencherValor() {
        if (estaComItemValidoSelecionado(jcbRecurso) && !jftQuantidade.getText().isEmpty()){
            try{
                jtfValorProduto.setText(servicoSisLaraServer.obterValorTotalPorDeRecurso((RecursoDTO)obterDtoSelecionado(jcbRecurso), jftQuantidade.getText()));
            }catch(Exception e){
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DA_SOMATORIA_DOS_PRODUTOS);
                logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DA_SOMATORIA_DOS_PRODUTOS + e.getMessage());
            }
        }
    }

    private void atualizarTotalProdutos() {
        try {
            prepararDtoParaEditar();
            ResultadoEdicaoProjetoDTO resultadoEdicaoProjetoDTO = servicoSisLaraServer.calcularTotais(obterObjetoDTO());
            if (resultadoEdicaoProjetoDTO.sucesso()){
                jtfSomaTotalProdutos.setText(((ProjetoDTO)resultadoEdicaoProjetoDTO.obterObjetoDtoEditado()).getSomaTotalProdutos());
            }
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DA_SOMATORIA_DOS_PRODUTOS + e.getMessage());
        }
    }
}
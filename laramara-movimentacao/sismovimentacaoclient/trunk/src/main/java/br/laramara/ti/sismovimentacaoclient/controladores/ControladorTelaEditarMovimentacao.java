package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaoclient.utilitarios.ComboBoxUtils;
import br.laramara.ti.sismovimentacaoclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sismovimentacaoclient.utilitarios.Tarefa;
import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.AbdbDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.FibraDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.PapelDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemAbdbDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemFibraDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemPapelDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemSimNaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemTextoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.SimNaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class ControladorTelaEditarMovimentacao extends ControladorTelaEditar {
    
    private ResultadoListagemTextoDTO resultadoListagemClientesDto;    
    private ResultadoListagemTextoDTO resultadoListagemDescricoesDto;
    private ResultadoListagemTextoDTO resultadoListagemCoresDto;
    private ResultadoListagemFibraDTO resultadoListagemFibraDTO;
    private ResultadoListagemTextoDTO resultadoListagemDescricoesProdutosDto;
    private ResultadoListagemPapelDTO resultadoListagemPapeisDto;
    private ResultadoListagemSimNaoDTO resultadoListagemSimNaoDTO;
    private ResultadoListagemAbdbDTO resultadoListagemAbdbDto;
    private ResultadoListagemTextoDTO resultadoListagemBobinasDto;
    private ResultadoListagemTextoDTO resultadoListagemPlanaPapelDto;
    private ResultadoListagemTextoDTO resultadoListagemTipoProvaDto;
    
    private JTextField jtfGl;
    private JComboBox jcbCliente;
    private JTextField jtfCodigoProduto;
    private JComboBox jcbDescricao;
    private JTextField jtfQtdCor;
    private JComboBox jcbCor;
    private JComboBox jcbFibra;
    private JTextField jtfDirecaoFibra;
    private JTextField jtfFormato;
    private JComboBox jcbDescricaoProduto;
    private JTextField jtfGramatura;
    private JTextField jtfCodigoAnterior;
    private JComboBox jcbPapel;
    private JTextField jtfLaetus;
    private JComboBox jcbSangria;
    private JComboBox jcbAbdb;
    private JComboBox jcbEspecificacao;
    private JEditorPane jepObsEspecificacao;
    private JEditorPane jepObsArte;
    private JComboBox jcbTipoProva;
    private JTextField jtfGr;
    private JTextField jtfPasta;
    private JComboBox jcbBobinas;
    private JComboBox jcbPlanaPapel;
    
    public ControladorTelaEditarMovimentacao(JDialog telaPai, MovimentacaoDTO movimentacaoDto, JTextField jtfGl, JComboBox jcbCliente, JTextField jtfCodigoProduto, JComboBox jcbDescricao, JTextField jtfQtdCor, JComboBox jcbCor, JComboBox jcbFibra, JTextField jtfDirecaoFibra, JTextField jtfFormato, JComboBox jcbDescricaoProduto, JTextField jtfGramatura, JTextField jtfCodigoAnterior, JComboBox jcbPapel, JTextField jtfLaetus, JComboBox jcbSangria, JComboBox jcbAbdb, JComboBox jcbEspecificacao, JEditorPane jepObsEspecificacao, JEditorPane jepObsArte, JComboBox jcbTipoProva, JTextField jtfGr, JTextField jtfPasta, JComboBox jcbBobina, JComboBox jcbPlanaPapel) {
        super(telaPai, movimentacaoDto);
        this.jtfGl = jtfGl;
        this.jcbCliente = jcbCliente;
        this.jtfCodigoProduto = jtfCodigoProduto;
        this.jcbDescricao = jcbDescricao;
        this.jtfQtdCor = jtfQtdCor;
        this.jcbCor = jcbCor;
        this.jcbFibra = jcbFibra;
        this.jtfDirecaoFibra = jtfDirecaoFibra;
        this.jtfFormato = jtfFormato;
        this.jcbDescricaoProduto = jcbDescricaoProduto;
        this.jtfGramatura = jtfGramatura;
        this.jtfCodigoAnterior = jtfCodigoAnterior;
        this.jcbPapel = jcbPapel;
        this.jtfLaetus = jtfLaetus;
        this.jcbSangria = jcbSangria;
        this.jcbAbdb = jcbAbdb;
        this.jcbEspecificacao = jcbEspecificacao;
        this.jepObsEspecificacao = jepObsEspecificacao;
        this.jepObsArte = jepObsArte;
        this.jcbTipoProva = jcbTipoProva;
        this.jtfGr = jtfGr;
        this.jtfPasta = jtfPasta;
        this.jcbBobinas = jcbBobina;
        this.jcbPlanaPapel = jcbPlanaPapel;
        configurarCombox();
        inicializarCombosDadosAuxiliares();
        carregarCampos();
    }
    
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisMovimentacaoServer.editarMovimentacao(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }
    
    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setGl(jtfGl.getText());
        obterObjetoDTO().setCliente(jcbCliente.getSelectedItem().toString());
        obterObjetoDTO().setCodigoProduto(jtfCodigoProduto.getText());
        obterObjetoDTO().setDescricao(jcbDescricao.getSelectedItem().toString());
        obterObjetoDTO().setQuantidadeCor(jtfQtdCor.getText());
        obterObjetoDTO().setCor(jcbCor.getSelectedItem().toString());
        obterObjetoDTO().setFibraDto((FibraDTO)obterDtoSelecionado(jcbFibra));
        obterObjetoDTO().setDirecaoFibra(jtfDirecaoFibra.getText());
        obterObjetoDTO().setFormato(jtfFormato.getText());
        obterObjetoDTO().setDescricaoProduto(jcbDescricaoProduto.getSelectedItem().toString());
        obterObjetoDTO().setCodigoAnterior(jtfCodigoAnterior.getText());
        obterObjetoDTO().setGramatura(jtfGramatura.getText());
        obterObjetoDTO().setPapelDto((PapelDTO)obterDtoSelecionado(jcbPapel));
        obterObjetoDTO().setLaetus(jtfLaetus.getText());
        obterObjetoDTO().setSangriaSimNaoDto((SimNaoDTO)obterDtoSelecionado(jcbSangria));
        obterObjetoDTO().setAbdbDto((AbdbDTO)obterDtoSelecionado(jcbAbdb));
        obterObjetoDTO().setEspecificacaoSimNaoDto((SimNaoDTO)obterDtoSelecionado(jcbEspecificacao));
        obterObjetoDTO().setObsEspecificacao(jepObsEspecificacao.getText());
        obterObjetoDTO().setObsArte(jepObsArte.getText());
        obterObjetoDTO().setTipoProva(jcbTipoProva.getSelectedItem().toString());
        obterObjetoDTO().setGr(jtfGr.getText());
        obterObjetoDTO().setPasta(jtfPasta.getText());
        obterObjetoDTO().setBobina(jcbBobinas.getSelectedItem().toString());
        obterObjetoDTO().setPlanaPapel(jcbPlanaPapel.getSelectedItem().toString());
    }
    
    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        return obterObjetoDTO().getId() != null;
    }
    
    private MovimentacaoDTO obterObjetoDTO() {
        return (MovimentacaoDTO) objetoDto;
    }
    
    @Override
    protected void atualizarCamposTelaAposInclusao() {
    }
    
    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_MOVIMENTACAO;
    }
    
    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarTextos(jcbCliente, resultadoListagemClientesDto.getObjetosDto());
        adicionarTextos(jcbDescricao, resultadoListagemDescricoesDto.getObjetosDto());
        adicionarTextos(jcbCor, resultadoListagemCoresDto.getObjetosDto());
        adicionarDtos(jcbFibra, resultadoListagemFibraDTO.getObjetosDto());
        adicionarTextos(jcbDescricaoProduto, resultadoListagemDescricoesProdutosDto.getObjetosDto());
        adicionarDtos(jcbPapel, resultadoListagemPapeisDto.getObjetosDto());
        adicionarDtos(jcbAbdb, resultadoListagemAbdbDto.getObjetosDto());
        adicionarDtos(jcbSangria, resultadoListagemSimNaoDTO.getObjetosDto());
        adicionarDtos(jcbEspecificacao, resultadoListagemSimNaoDTO.getObjetosDto());
        adicionarTextos(jcbBobinas, resultadoListagemBobinasDto.getObjetosDto());
        adicionarTextos(jcbPlanaPapel, resultadoListagemPlanaPapelDto.getObjetosDto());
        adicionarTextos(jcbTipoProva, resultadoListagemTipoProvaDto.getObjetosDto());
    }
    
    @Override
    public void obterDadosAuxiliares() {
        try {
            Collection<Callable<Void>> tasks = new ArrayList<>();
            tasks.add(new Tarefa(new Runnable() {
                @Override
                public void run() {
                    try {
                        resultadoListagemClientesDto = servicoSisMovimentacaoServer.obterListagemCliente();
                    } catch (Exception ex) {
                        erroObtencaoDadosAuxiliares(ex);
                    }
                }
            }));

            tasks.add(new Tarefa(new Runnable() {
                @Override
                public void run() {
                    try {
                        resultadoListagemDescricoesDto = servicoSisMovimentacaoServer.obterListagemDescricao();
                    } catch (Exception ex) {
                        erroObtencaoDadosAuxiliares(ex);
                    }
                }
            }));

            tasks.add(new Tarefa(new Runnable() {
                @Override
                public void run() {
                    try {
                        resultadoListagemDescricoesProdutosDto = servicoSisMovimentacaoServer.obterListagemDescricaoProduto();

                    } catch (Exception ex) {
                        erroObtencaoDadosAuxiliares(ex);
                    }
                }
            }));

            tasks.add(new Tarefa(new Runnable() {
                @Override
                public void run() {
                    try {
                        resultadoListagemCoresDto = servicoSisMovimentacaoServer.obterListagemCor();
                        resultadoListagemFibraDTO = servicoSisMovimentacaoServer.obterListagemFibra();
                        resultadoListagemPapeisDto = servicoSisMovimentacaoServer.obterListagemPapel();
                        resultadoListagemAbdbDto = servicoSisMovimentacaoServer.obterListagemAbdb();
                        resultadoListagemSimNaoDTO = servicoSisMovimentacaoServer.obterListagemSimNao();
                        resultadoListagemBobinasDto = servicoSisMovimentacaoServer.obterListagemBobina();
                        resultadoListagemPlanaPapelDto = servicoSisMovimentacaoServer.obterListagemPlanaPapel();
                        resultadoListagemTipoProvaDto = servicoSisMovimentacaoServer.obterListagemTipoProva();
                    } catch (Exception ex) {
                        erroObtencaoDadosAuxiliares(ex);
                    }
                }
            }));

            ExecutorService executor = Executors.newFixedThreadPool(5);
            executor.invokeAll(tasks);
            executor.shutdown();
        } catch (Exception ex) {
            logger.error("Erro durante execução de tarefas paralelas. \nDetalhes: " + ex);
        }
    }

    private void erroObtencaoDadosAuxiliares(Exception ex){
        JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
        logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
    }
    
    @Override
    public void carregarCampos() {
        if (verificarSeDtoEstaPreenchido()) {
            jtfGl.setText(obterObjetoDTO().getGl());
            selecionarTexto(jcbCliente, obterObjetoDTO().getCliente());
            jtfCodigoProduto.setText(obterObjetoDTO().getCodigoProduto());
            selecionarTexto(jcbDescricao, obterObjetoDTO().getDescricao());
            jtfQtdCor.setText(obterObjetoDTO().getQuantidadeCor());
            selecionarTexto(jcbCor, obterObjetoDTO().getCor());
            selecionarDto(jcbFibra, obterObjetoDTO().getFibraDto());
            jtfDirecaoFibra.setText(obterObjetoDTO().getDirecaoFibra());
            jtfFormato.setText(obterObjetoDTO().getFormato());
            selecionarTexto(jcbDescricaoProduto, obterObjetoDTO().getDescricaoProduto());
            jtfCodigoAnterior.setText(obterObjetoDTO().getCodigoAnterior());
            jtfGramatura.setText(obterObjetoDTO().getGramatura());
            selecionarDto(jcbPapel, obterObjetoDTO().getPapelDto());
            jtfLaetus.setText(obterObjetoDTO().getLaetus());
            selecionarDto(jcbSangria, obterObjetoDTO().getSangriaSimNaoDto());
            selecionarDto(jcbAbdb, obterObjetoDTO().getAbdbDto());
            selecionarDto(jcbEspecificacao, obterObjetoDTO().getEspecificacaoSimNaoDto());
            jepObsEspecificacao.setText(obterObjetoDTO().getObsEspecificacao());
            jepObsArte.setText(obterObjetoDTO().getObsArte());
            selecionarTexto(jcbTipoProva, obterObjetoDTO().getTipoProva());
            jtfGr.setText(obterObjetoDTO().getGr());
            jtfPasta.setText(obterObjetoDTO().getPasta());
            selecionarTexto(jcbBobinas, obterObjetoDTO().getBobina());
            selecionarTexto(jcbPlanaPapel, obterObjetoDTO().getPlanaPapel());
        }
    }
    
    public void adicionarCliente() {
        adicionarTextosESelecionar((JDialog) telaPai, "Insira o nome do Cliente", jcbCliente);
    }

    private void configurarCombox() {
        ComboBoxUtils.habilitar(jcbCliente);
        ComboBoxUtils.habilitar(jcbCor);
        ComboBoxUtils.habilitar(jcbDescricao);
        ComboBoxUtils.habilitar(jcbFibra);
        ComboBoxUtils.habilitar(jcbDescricaoProduto);
        ComboBoxUtils.habilitar(jcbPapel);
        ComboBoxUtils.habilitar(jcbSangria);
        ComboBoxUtils.habilitar(jcbAbdb);
        ComboBoxUtils.habilitar(jcbEspecificacao);
        ComboBoxUtils.habilitar(jcbBobinas);
        ComboBoxUtils.habilitar(jcbPlanaPapel);
        ComboBoxUtils.habilitar(jcbTipoProva);
    }

    public void adicionarDescricao() {
        adicionarTextosESelecionar((JDialog) telaPai, "Insira uma Descrição", jcbDescricao);
    }

    public void adicionarCor() {
        adicionarTextosESelecionar((JDialog) telaPai, "Insira uma Cor", jcbCor);
    }

    public void adicionarDescricaoProduto() {
        adicionarTextosESelecionar((JDialog) telaPai, "Insira uma Descrição do Produto", jcbDescricaoProduto);
    }

    public void adicionarPlanaPapel() {
        adicionarTextosESelecionar((JDialog) telaPai, "Insira uma Plana de Papel", jcbPlanaPapel);
    }

    public void adicionarBobina() {
        adicionarTextosESelecionar((JDialog) telaPai, "Insira uma Bobina", jcbBobinas);
    }
}

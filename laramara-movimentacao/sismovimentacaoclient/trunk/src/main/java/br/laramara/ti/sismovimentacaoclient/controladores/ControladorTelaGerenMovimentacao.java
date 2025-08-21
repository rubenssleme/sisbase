package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaoclient.telas.TelaEditarMovimentacao;
import br.laramara.ti.sismovimentacaoclient.telas.TelaHistoricoStatusMovimentacao;
import br.laramara.ti.sismovimentacaoclient.telas.TelaStatusMovimentacaoAvancar;
import br.laramara.ti.sismovimentacaoclient.telas.TelaStatusMovimentacaoEditar;
import br.laramara.ti.sismovimentacaoclient.telas.TelaStatusMovimentacaoIncluir;
import br.laramara.ti.sismovimentacaoclient.utilitarios.EsperaUtils;
import br.laramara.ti.sismovimentacaoclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sismovimentacaoclient.utilitarios.TabelaUtils;
import br.laramara.ti.sismovimentacaoclient.utilitarios.TelaUtils;
import br.laramara.ti.sismovimentacaocommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.EspecificacaoPesquisaMovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;

public class ControladorTelaGerenMovimentacao extends ControladorTelaPesquisarBase {

    private JFormattedTextField jftQuantidadeResultado; 
    private JTable jtaMovimentacao;
    
    public ControladorTelaGerenMovimentacao(JDialog telaPai, JFormattedTextField jftQuantidadeResultado, JTable jtaMovimentacao) {
        super(telaPai, jtaMovimentacao);
        this.jftQuantidadeResultado = jftQuantidadeResultado;
        this.jtaMovimentacao = jtaMovimentacao;
        TelaUtils.maximizar(telaPai);
        inicializarCorTabela();
        configurarColunasTabela();
    }
    
    private void inicializarCorTabela(){
        String colunaReferencia = "Classificacao";
        Map<String, Color> relacaoTipoCor = new HashMap<>();
        relacaoTipoCor.put("AMARELO", new Color(234, 234, 174)); 
        relacaoTipoCor.put("VERMELHO", new Color(255, 106, 106));
        relacaoTipoCor.put("AZUL", new Color(135, 206, 250)); 
        relacaoTipoCor.put("LILAS", new Color(238, 174, 238)); 
        relacaoTipoCor.put("VERDE", new Color(180, 238, 180));  
        relacaoTipoCor.put("CINZA",  new Color(156, 156, 156)); 
        relacaoTipoCor.put("LARANJA", new Color(222, 184, 135));  
        relacaoTipoCor.put("BRANCO", new Color(232, 232, 232)); 
        
        TabelaUtils.configurarCorTabela(colunaReferencia, relacaoTipoCor, jtaMovimentacao);
    }
    
    public void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaMovimentacao);
    }
    
    @Override
    protected ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacaoPesquisaDto) throws Exception {
         return servicoSisMovimentacaoServer.pesquisarMovimentacaoPor((EspecificacaoPesquisaMovimentacaoDTO) especificacaoPesquisaDto);
    }
    
    public void efetuarPesquisa() {
        final EspecificacaoPesquisaMovimentacaoDTO especificacao = new EspecificacaoPesquisaMovimentacaoDTO();
        especificacao.setQuantidadePesquisa(new Integer(jftQuantidadeResultado.getText().trim()));
        Runnable comando = new Runnable() {
            @Override
            public void run() {
                pesquisar(especificacao);
                jtaMovimentacao.updateUI();
            }
        };
        new EsperaUtils(comando, (JDialog) telaPai).execute();
    }

    public void alterarMovimentacaoSelecionada() {
        if (estaComItemValidoSelecionado(jtaMovimentacao)) {
            new TelaEditarMovimentacao((JDialog) telaPai, (MovimentacaoDTO) obterDtoSelecionado(jtaMovimentacao));
            if (Sessao.obterInstancia().possuiDto(obterChaveSessao())) {
                adicionarDtoNoItemSelecionado(jtaMovimentacao, (MovimentacaoDTO) Sessao.obterInstancia().obterDto(obterChaveSessao()));
            }
        } else {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.SELECIONAR_REGISTRO);
        }
    }

    private String obterChaveSessao() {
        return Sessao.CHAVE_MOVIMENTACAO;
    }

    public void abrirTelaAlteracaoStatus() {
        if (estaComItemValidoSelecionado(jtaMovimentacao)) {
            new TelaStatusMovimentacaoEditar((JDialog) telaPai, (MovimentacaoDTO) obterDtoSelecionado(jtaMovimentacao));
            if (Sessao.obterInstancia().possuiDto(obterChaveSessao())) {
                adicionarDtoNoItemSelecionado(jtaMovimentacao, (MovimentacaoDTO) Sessao.obterInstancia().obterDto(obterChaveSessao()));
            }
        } else {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.SELECIONAR_REGISTRO);
        }
    }

    public void abrirTelaAvancarStatus() {
        if (estaComItemValidoSelecionado(jtaMovimentacao)) {
            new TelaStatusMovimentacaoAvancar((JDialog) telaPai, (MovimentacaoDTO)obterDtoSelecionado(jtaMovimentacao));
            if (Sessao.obterInstancia().possuiDto(obterChaveSessao())) {
                adicionarDtoNoItemSelecionado(jtaMovimentacao, (MovimentacaoDTO) Sessao.obterInstancia().obterDto(obterChaveSessao()));
            }
        } else {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.SELECIONAR_REGISTRO);
        }
    }

    public void abrirTelaHistoricoStatus() {
        if (estaComItemValidoSelecionado(jtaMovimentacao)) {
            new TelaHistoricoStatusMovimentacao((JDialog) telaPai, (MovimentacaoDTO)obterDtoSelecionado(jtaMovimentacao));
        } else {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.SELECIONAR_REGISTRO);
        }
    }

    public void abrirTelaInclusaoMivimentacao() {
        new TelaStatusMovimentacaoIncluir((JDialog) telaPai);
        if (Sessao.obterInstancia().possuiDto(obterChaveSessao())) {
            adicionarDtos((MovimentacaoDTO) Sessao.obterInstancia().obterDto(obterChaveSessao()), jtaMovimentacao);
        }
    }
}

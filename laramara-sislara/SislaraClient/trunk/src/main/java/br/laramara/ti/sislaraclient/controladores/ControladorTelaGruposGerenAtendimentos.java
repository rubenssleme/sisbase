package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaGruposEditarAtendimentos;
import br.laramara.ti.sislaraclient.telas.TelaGruposLancamentoAtendimento;
import br.laramara.ti.sislaraclient.utilitarios.EsperaUtils;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.SomUtils;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.Bloqueavel;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoEdicaoAtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoCoordenacaoEdicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaGruposGerenAtendimentos extends ControladorTela{

    private GrupoDTO grupoDtoSelecionado;
    private JTextField jtfGrupo;
    private JComboBox jcbModuloAtividade;
    private JTable jtaListagemAtendimentos;
    
    public ControladorTelaGruposGerenAtendimentos(JDialog telaPai, JTextField jtfGrupo, JComboBox jcbModuloAtividade, JTable jtaListagemAtendimentos){
        super(telaPai);
        this.jtfGrupo = jtfGrupo;
        this.jcbModuloAtividade = jcbModuloAtividade;
        this.jtaListagemAtendimentos = jtaListagemAtendimentos;
        configurarColunasTabela();
    }
    
    private void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaListagemAtendimentos);
    }

    public void inicializarModuloAtividade() {
        if (!jtfGrupo.getText().isEmpty()) {
            grupoDtoSelecionado = inicializarModuloPeriodo(jcbModuloAtividade, jtfGrupo);
            String nomeObjeto = ((Bloqueavel) grupoDtoSelecionado).obterNome();
            try {
                if (!nomeObjeto.isEmpty()) {
                    if (!servicoSisLaraServer.estaBloqueadoParaEdicao(nomeObjeto, Sessao.obterInstancia().obterToken())) {
                        bloquearParaEdicao(nomeObjeto);
                    } else {
                        ResultadoCoordenacaoEdicaoDTO resultadoCoordenacaoEdicaoDTO = servicoSisLaraServer.obterContaAcessoEditando(nomeObjeto);
                        JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoCoordenacaoEdicaoDTO.obterMensagens());
                        fecharTela();
                    }
                }
            } catch (Exception ex) {
                logger.fatal(JOptionPanePersonalizado.ERRO_NA_ABERTURA_DE_TELA + ex);
            }
        }
    }

    public void gerarAtendimento() {
        if (estaComItemValidoSelecionado(jcbModuloAtividade)) {
            ModuloPeriodoDTO moduloPeriodoDTO = (ModuloPeriodoDTO) obterDtoSelecionado(jcbModuloAtividade);
            new TelaGruposLancamentoAtendimento((JDialog) telaPai, grupoDtoSelecionado, moduloPeriodoDTO);

            inserirAtendimentoNoModuloAtividadeSelecionado();
        } else {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.SELECIONAR_UM_MODULO_ATIVIDADE);
        }
    }
    
    private void inserirAtendimentoNoModuloAtividadeSelecionado(){
        String chave = Sessao.CHAVE_MODULO_PERIODO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            adicionarDtoNoItemSelecionado(jcbModuloAtividade, (ModuloPeriodoDTO) Sessao.obterInstancia().obterDto(chave));
        }    
    }
    
    private void atualizarModuloAtividadeSelecionado(){
        String chave = Sessao.CHAVE_ATENDIMENTO_GRUPO;
        if (Sessao.obterInstancia().possuiDto(chave)){
            int indiceModuloAtividadeSelecionado = obterIndiceDTOSelecionado(jcbModuloAtividade);
            adicionarDtoNoItemSelecionado(jtaListagemAtendimentos, (AtendimentoGrupoDTO) Sessao.obterInstancia().obterDto(chave));
            jcbModuloAtividade.setEnabled(true);
            jcbModuloAtividade.setSelectedIndex(indiceModuloAtividadeSelecionado);
            jtaListagemAtendimentos.setEnabled(true);
            jtaListagemAtendimentos.requestFocusInWindow();
        }    
    }

    public void inicializarAtendimentos() {
        ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO) obterDtoSelecionado(jcbModuloAtividade);
        
        if (moduloPeriodoDto != null) {
            adicionarDtos(moduloPeriodoDto.getAtendimentosGrupoDto(), jtaListagemAtendimentos);
        }else{
            removerDtos(jtaListagemAtendimentos);
        }
    }
    
    public void alterarAtendimento() {
        if (estaComItemValidoSelecionado(jtaListagemAtendimentos)){
            new TelaGruposEditarAtendimentos((JDialog) telaPai, grupoDtoSelecionado, (ModuloPeriodoDTO)obterDtoSelecionado(jcbModuloAtividade), (AtendimentoGrupoDTO)obterDtoSelecionado(jtaListagemAtendimentos), false);
            
            atualizarModuloAtividadeSelecionado();
        }
    }
    
    public void abrirTelaDeAlteracaoAtendimento(KeyEvent evt){
        if (teclaEnterPressionada(evt)){
            alterarAtendimento();
        }
    }

    public void cancelarAtemdimento() {
        if (estaComItemValidoSelecionado(jtaListagemAtendimentos)) {
            if (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO) == JOptionPane.OK_OPTION) {
                Runnable comando = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SomUtils.iniciar();
                            ResultadoEdicaoAtendimentoGrupoDTO resultadoEdicaoGrupoDto = servicoSisLaraServer.cancelarAtendimentoGrupo((AtendimentoGrupoDTO) obterDtoSelecionado(jtaListagemAtendimentos), Sessao.obterInstancia().obterToken());
                            SomUtils.parar();
                            if (resultadoEdicaoGrupoDto.sucesso()) {
                                Sessao.obterInstancia().armazenarDTO(Sessao.CHAVE_ATENDIMENTO_GRUPO, resultadoEdicaoGrupoDto.obterObjetoDtoEditado());
                                atualizarModuloAtividadeSelecionado();
                                JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultadoEdicaoGrupoDto.obterMensagens());
                            } else {
                                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoEdicaoGrupoDto.obterMensagens());
                            }
                        } catch (Exception ex) {
                            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_CANCELAMENTO_ATENDIMENTO);
                            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_CANCELAMENTO_ATENDIMENTO + ex.getMessage());
                        }
                    }
                };
                new EsperaUtils(comando, (JDialog) telaPai).execute();

            }
        }
    }
}

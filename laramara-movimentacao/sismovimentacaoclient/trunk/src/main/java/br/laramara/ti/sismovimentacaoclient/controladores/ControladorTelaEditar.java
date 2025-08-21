package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaoclient.infra.PermissaoDeTelas;
import br.laramara.ti.sismovimentacaoclient.utilitarios.ClonadorUtils;
import br.laramara.ti.sismovimentacaoclient.utilitarios.EsperaUtils;
import br.laramara.ti.sismovimentacaoclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sismovimentacaoclient.utilitarios.SomUtils;
import br.laramara.ti.sismovimentacaocommons.Bloqueavel;
import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoCoordenacaoEdicaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public abstract class ControladorTelaEditar extends ControladorTela implements SessaoUtilizavel, CombosAuxiliaresInicializaveis, CamposDadosCarregavel{
    
    private String nomeObjetoABloquearEDesbloquear;
    protected ModeloDTO objetoDto;
    
    public ControladorTelaEditar(JDialog telaPai, ModeloDTO objetoReferenciaDto){
        super(telaPai);
        this.objetoDto = (ModeloDTO) ClonadorUtils.copiar(objetoReferenciaDto);
        Sessao.obterInstancia().removerDto(obterChaveSessao());
    }
    
    public void salvar() {
        prepararDtoParaEditar();
        int resposta = JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.PERGUNTA, JOptionPanePersonalizado.CONFIRMACAO);
        if (resposta == JOptionPane.OK_OPTION) {
            Runnable comando = new Runnable() {
                @Override
                public void run() {
                    try {
                        SomUtils.iniciar();
                        ResultadoDTO resultado = solicitarEdicaoDto();
                        SomUtils.parar();
                        if (resultado.sucesso()) {
                            servicoSisMovimentacaoServer.gravarTela(obterTela());
                            objetoDto = resultado.obterObjetoDtoEditado();
                            atualizarCamposTelaAposInclusao();
                            Sessao.obterInstancia().armazenarDTO(obterChaveSessao(), objetoDto);
                            JOptionPanePersonalizado.mostrarTelaInformacao(telaPai, JOptionPanePersonalizado.SUCESSO, resultado.obterMensagens());
                            desbloquearDTOeFecharTela();
                        } else {
                            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, resultado.obterMensagens());
                        }
                    } catch (Exception ex) {
                        JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
                        logger.fatal(ex);
                    }

                }
            };
            new EsperaUtils(comando, (JDialog) telaPai).execute();
        }
    }
    public void abrirSomenteSeHouverPermissaoEBloquearDTO(Bloqueavel objeto, PermissaoDeTelas permissaoExigida) {
        abrirSomenteSeHouverPermissaoEBloquearDTO(objeto.obterNome(), permissaoExigida);
    }    
    
    public void abrirSomenteSeHouverPermissaoEBloquearDTO(String nomeObjeto, PermissaoDeTelas permissaoExigida) {
        try {
            if (servicoSisMovimentacaoServer.possuiAutorizacao(Sessao.obterInstancia().obterToken(), permissaoExigida.toString())) {
                if (!nomeObjeto.isEmpty()) {
                    if (!servicoSisMovimentacaoServer.estaBloqueadoParaEdicao(nomeObjeto, Sessao.obterInstancia().obterToken())) {
                        bloquearParaEdicao(nomeObjeto);
                        abrirTela();
                    } else {
                        ResultadoCoordenacaoEdicaoDTO resultadoCoordenacaoEdicaoDTO = servicoSisMovimentacaoServer.obterContaAcessoEditando(nomeObjeto);
                        JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ACESSO_NEGADO, resultadoCoordenacaoEdicaoDTO.obterMensagens());
                    }
                } else {
                    abrirTela();
                }
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ACESSO_NEGADO, JOptionPanePersonalizado.NAO_POSSUI_ACESSO_A_AREA);
            }
        } catch (Exception ex) {
            logger.fatal(JOptionPanePersonalizado.ERRO_NA_ABERTURA_DE_TELA + ex);
        }
    }
    
   private void bloquearParaEdicao(String nomeObjetoABloquear) {
       nomeObjetoABloquearEDesbloquear = nomeObjetoABloquear;
        try {
            servicoSisMovimentacaoServer.bloquearEdicao(nomeObjetoABloquear,  Sessao.obterInstancia().obterToken());
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.ERRO_COORDENACAO_BLOQUEIO);
            logger.error(JOptionPanePersonalizado.ERRO_COORDENACAO_BLOQUEIO + ex.getMessage());
        }
    }
    
    public void desbloquearDTOeFecharTela() {
        try {
            if (nomeObjetoABloquearEDesbloquear != null) {
                servicoSisMovimentacaoServer.desbloquearEdicao(nomeObjetoABloquearEDesbloquear, Sessao.obterInstancia().obterToken());
            }
            super.fecharTela();
        } catch (RemoteException ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.ERRO_COORDENACAO_DESBLOQUEIO);
            logger.error(JOptionPanePersonalizado.ERRO_COORDENACAO_DESBLOQUEIO + ex.getMessage());
        }
    }
        
    protected abstract ResultadoDTO solicitarEdicaoDto() throws Exception;
            
    protected abstract void prepararDtoParaEditar();
    
    protected abstract boolean verificarSeDtoEstaPreenchido();
    
    protected abstract void atualizarCamposTelaAposInclusao();
}


package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ArquivoDisponivelDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemArquivoDisponivelDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemNomeCompletoGrupoDTO;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladoTelaGerenArquivoDisponivel extends ControladorTela {

    private JTextField jtfDadosParaPesquisa;
    private JCheckBox jchSomenteGrupos;
    private JList jliGrupos;
    private JTable jtaArquivosDisponiveis;
   
    public ControladoTelaGerenArquivoDisponivel(JDialog telaPai, JTextField jtfDadosParaPesquisa, JCheckBox jchSomenteGrupos, JList jliGrupos, JTable jtaArquivosDisponiveis){
        super(telaPai);
        this.jtfDadosParaPesquisa = jtfDadosParaPesquisa;
        this.jchSomenteGrupos = jchSomenteGrupos;
        this.jliGrupos = jliGrupos;
        this.jtaArquivosDisponiveis = jtaArquivosDisponiveis;
        configurarColunasTabela();
    }
        
    private void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaArquivosDisponiveis);
    }
    
    public void pesquisar() {
        esvaziarLista(jliGrupos);
        limparTabela(jtaArquivosDisponiveis);
        try {
            if (jchSomenteGrupos.isSelected()) {
                ResultadoListagemNomeCompletoGrupoDTO resultadoDto = servicoSisLaraServer.pesquisarNomeGrupoPor(jtfDadosParaPesquisa.getText());
                if (resultadoDto.sucesso() && !resultadoDto.getObjetosDto().isEmpty()) {
                    adicionarNaLista(resultadoDto.getObjetosDto(), jliGrupos);
                }
            } else {
                ResultadoListagemArquivoDisponivelDTO resultadoListagemArquivoDisponivelDTO = servicoSisLaraServer.obterListagemArquivoDisponivelDTO(jtfDadosParaPesquisa.getText(), false);
                if (resultadoListagemArquivoDisponivelDTO.sucesso()) {
                    adicionarDtos(resultadoListagemArquivoDisponivelDTO.getObjetosDto(), jtaArquivosDisponiveis);
                }
            }
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    public void abrir() {
        ArquivoDisponivelDTO arquivoDisponivelDTO = (ArquivoDisponivelDTO) obterDtoSelecionado(jtaArquivosDisponiveis);
        try {
            ArquivoDTO arquivoDto = servicoSisLaraServer.obterArquivoDisponivelDTO(arquivoDisponivelDTO.getIdAtendimento().toString(), arquivoDisponivelDTO.getNomeArquivo(), arquivoDisponivelDTO.getTipo());
            abrirArquivo(gravarArquivoUsandoNomeArquivo(arquivoDto));
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\nDetalhes: " + e.getMessage());
        }
    }

    public void obterListagemArquivosGrupo() {
        try {
            String grupo = jliGrupos.getSelectedValue().toString();
            ResultadoListagemArquivoDisponivelDTO resultadoListagemArquivoDisponivelDTO = servicoSisLaraServer.obterListagemArquivoDisponivelDTO(grupo, true);
            if (resultadoListagemArquivoDisponivelDTO.sucesso()) {
                adicionarDtos(resultadoListagemArquivoDisponivelDTO.getObjetosDto(), jtaArquivosDisponiveis);
            }
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
}

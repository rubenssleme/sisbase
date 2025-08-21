package br.laramara.ti.sislaraclient.controladores;

import static br.laramara.ti.sislaraclient.controladores.ControladorTela.logger;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.DemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.DocumentoSolicitacaoDoacaoDTO;
import javax.swing.JDialog;
import javax.swing.JList;

public class ControladorTelaDemandaVisualizacaoDocumento extends ControladorTela{
    private DemandaDTO demandaDto;
    private JList jliDocumentos;
    public ControladorTelaDemandaVisualizacaoDocumento(JDialog telaPai, DemandaDTO demandaDto, JList jliDocumentos){
        super(telaPai);
        this.demandaDto = demandaDto;
        this.jliDocumentos = jliDocumentos;
        adicionarDtos(demandaDto.getDocumentosSolicitacaoDoacaoDto(), jliDocumentos);
    }

    public void abrirDocumentoSelecionado() {
        DocumentoSolicitacaoDoacaoDTO documentoSolicitacaoDoacaoDto = (DocumentoSolicitacaoDoacaoDTO) obterDtoSelecionado(jliDocumentos);
        try {
            ArquivoDTO arquivoDto = servicoSisLaraServer.obterArquivoDocumentoSolicitacaoDoacaoDTO(demandaDto, documentoSolicitacaoDoacaoDto.getArquivoDTO());
            abrirArquivo(gravarArquivoUsandoNomeArquivo(arquivoDto));
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\nDetalhes: " + e.getMessage());
        }
    }
}

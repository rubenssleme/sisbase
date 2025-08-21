package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.SomUtils;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import javax.swing.JDialog;
import javax.swing.JTable;

public abstract class ControladorTelaPesquisarBase extends ControladorTela {
    
    private JTable jta;
        
    public ControladorTelaPesquisarBase(JDialog telaPai, JTable jta){
        super(telaPai);
        this.jta = jta;
    }
    
    protected void pesquisar(EspecificacaoPesquisaDTO especificacaoPesquisaDto){
        try{
            SomUtils.iniciar();
            ResultadoListagemDTO resultado = obterListagem(especificacaoPesquisaDto);
            apresentarResultado(resultado, jta);
        }catch(Exception e){
            mostrarTelaErroPesquisa();
            logger.fatal(e);
        }
    }
    
    protected abstract ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacaoPesquisaDto) throws Exception;
}

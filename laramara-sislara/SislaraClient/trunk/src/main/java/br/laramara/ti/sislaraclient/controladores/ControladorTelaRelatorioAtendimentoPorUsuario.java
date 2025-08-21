package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

public abstract class ControladorTelaRelatorioAtendimentoPorUsuario extends ControladorTela{
    
    private GrupoDTO grupoDtoSelecionado;
    private JTextField jtfGrupo;
    private JComboBox jcbModuloAtividade;
    private JComboBox jcbUsuario;
    
    public ControladorTelaRelatorioAtendimentoPorUsuario(JDialog telaPai, JTextField jtfGrupo, JComboBox jcbModuloAtividade, JComboBox jcbUsuario){
        super(telaPai);
        this.jtfGrupo = jtfGrupo;
        this.jcbModuloAtividade = jcbModuloAtividade;
        this.jcbUsuario = jcbUsuario;
    }

    public void inicializarModuloAtividade() {
        grupoDtoSelecionado = inicializarModuloPeriodo(jcbModuloAtividade, jtfGrupo);
    }

    public void inicializarUsuarios() {
        List<? extends ModeloDTO> objetosDto = null;
        if (estaComItemValidoSelecionado(jcbModuloAtividade)){
            objetosDto = ((ModuloPeriodoDTO) obterDtoSelecionado(jcbModuloAtividade)).getModulosUsuariosDto();
        }
        carregarCombosDependentes(jcbModuloAtividade, jcbUsuario, objetosDto);
    }

    public void exibir() {
        ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO)obterDtoSelecionado(jcbModuloAtividade);
        UsuarioDTO usuarioDto = estaComItemValidoSelecionado(jcbUsuario) ? ((ModuloUsuarioDTO)obterDtoSelecionado(jcbUsuario)).getUsuarioDto() : null;
        try {
            ArquivoDTO relatorioDto = obterRelatorio(grupoDtoSelecionado, moduloPeriodoDto, usuarioDto);
            exibirArquivo(relatorioDto);
        } catch (Exception ex) {
            logger.error("Erro durante solicitação de relatório. \nDetalhes:" + ex);
        }
    }
    
    protected abstract ArquivoDTO obterRelatorio(GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto, UsuarioDTO usuarioDto) throws RemoteException;
}

package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class ControladorTelaRelatorioAtendimentoPorUsuarioSimples extends ControladorTelaRelatorioAtendimentoPorUsuario{
    
    public ControladorTelaRelatorioAtendimentoPorUsuarioSimples(JDialog telaPai, JTextField jtfGrupo, JComboBox jcbModuloAtividade, JComboBox jcbUsuario){
        super(telaPai, jtfGrupo, jcbModuloAtividade, jcbUsuario);
    }

    @Override
    protected ArquivoDTO obterRelatorio(GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto, UsuarioDTO usuarioDto) throws RemoteException {
        return servicoSisLaraServer.gerarRelatorioAtendimentoDeUsuarioNoGrupoSimples(grupoDto, moduloPeriodoDto, Sessao.obterInstancia().obterToken());
    }
}

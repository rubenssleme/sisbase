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

public class ControladorTelaRelatorioPorcentagensFrequenciaPorGrupo extends ControladorTelaRelatorioAtendimentoPorUsuario{
     
    public ControladorTelaRelatorioPorcentagensFrequenciaPorGrupo(JDialog telaPai, JTextField jtfGrupo, JComboBox jcbModuloAtividade){
        super(telaPai, jtfGrupo, jcbModuloAtividade);
    }

    @Override
    protected ArquivoDTO obterRelatorio(GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto, UsuarioDTO usuarioDto) throws RemoteException {
        return servicoSisLaraServer.gerarRelatorioPorcentagensFrequenciaPorGrupo(grupoDto, moduloPeriodoDto, Sessao.obterInstancia().obterToken());
    }
}

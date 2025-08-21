package br.laramara.ti.sislaraclient.utilitarios;


public class Configuracoes {
    private static final String HOME = System.getProperty("user.home");
    public static final String EXTENSAO_EXPORTACAO_TABELA = ".csv";
    public static final String EXTENSAO_EXPORTACAO_TEXTO = ".txt";
    public static final long INTERVALO_VERIFICACAO_AVISO = 300000;
    public static final long INTERVALO_VERIFICACAO_FECHAMENTO_AUTOMATICO = 60000;
    public static final long INTERVALO_VERIFICACAO_PENDENCIAS = 10000;
    public static final String DIRETORIO_TEMPORARIO_ARQUIVOS = HOME + "\\SisLara\\Temporario\\";
    public static final String DIRETORIO_PERMANENTE_ARQUIVOS = HOME + "\\SisLara\\Permanente\\";
    public static final String PASTA_LOCALIZACAO_OFFICE = "pasta_localizacao_office.obj";
    public static final String APLICATIVO_WORD = "WinWord.exe";
    public static final String HD_PROCURAR_OFFICE = "c:\\";
}

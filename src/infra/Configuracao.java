package infra;

public class Configuracao {
    // A variável estática que guarda a única instância
    private static Configuracao instance;
    
    // Propriedades da configuração
    private String versaoSistema = "1.0.0";
    private String moedaPadrao = "BRL";

    // Construtor PRIVADO: Ninguém consegue dar "new Configuracao()" fora daqui
    private Configuracao() {}

    // Método estático para pegar a instância única
    public static Configuracao getInstance() {
        if (instance == null) {
            instance = new Configuracao();
        }
        return instance;
    }

    public String getVersaoSistema() {
        return versaoSistema;
    }
    
    public String getMoedaPadrao() {
        return moedaPadrao;
    }
}
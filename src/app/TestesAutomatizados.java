package app;

import strategies.*;
import decorators.*;
import domain.GestorDeEmissoes;
import infra.Configuracao;

public class TestesAutomatizados {
    public static void main(String[] args) {
        System.out.println("=== RODANDO TESTES AUTOMATIZADOS (T27) ===");
        int erros = 0;

        // --- TESTE 1: Padrão Strategy ---
        System.out.print("1. Testando Strategy (Cálculos de Escopo)... ");
        ICalculoEmissao escopo1 = new CalculoEscopo1();
        ICalculoEmissao escopo2 = new CalculoEscopo2();
        
        // 10 litros * 2.3 deve ser 23.0
        if (escopo1.calcular(10) != 23.0) {
            System.out.println("FALHA! Escopo1 calculou errado.");
            erros++;
        } else if (escopo2.calcular(100) != 50.0) { // 100 * 0.5 = 50
            System.out.println("FALHA! Escopo2 calculou errado.");
            erros++;
        } else {
            System.out.println("OK!");
        }

        // --- TESTE 2: Padrão Decorator ---
        System.out.print("2. Testando Decorator (Acréscimo de taxas)... ");
        IServicoCompensacao servico = new CompensacaoBase();
        double valorBase = servico.getPreco(1.0); // 1 tonelada = 50.0
        
        servico = new CertificacaoGoldDecorator(servico);
        double valorGold = servico.getPreco(1.0); // 50 * 1.20 = 60.0
        
        if (valorBase != 50.0) {
            System.out.println("FALHA! Valor base incorreto.");
            erros++;
        } else if (Math.abs(valorGold - 60.0) > 0.01) {
            System.out.println("FALHA! Decorator Gold não aplicou 20%. Deu: " + valorGold);
            erros++;
        } else {
            System.out.println("OK!");
        }

        // --- TESTE 3: Padrão Singleton ---
        System.out.print("3. Testando Singleton (Unicidade)... ");
        Configuracao c1 = Configuracao.getInstance();
        Configuracao c2 = Configuracao.getInstance();
        if (c1 == c2) {
            System.out.println("OK!");
        } else {
            System.out.println("FALHA! Singleton criou instâncias diferentes.");
            erros++;
        }

        System.out.println("------------------------------------------");
        if (erros == 0) {
            System.out.println("SUCESSO: Todos os testes passaram! ✅");
        } else {
            System.out.println("ERRO: " + erros + " testes falharam. ❌");
        }
    }
}
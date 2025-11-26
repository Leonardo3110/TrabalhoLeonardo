package app;

import domain.GestorDeEmissoes;
import strategies.*;
import decorators.*;
import observers.*;
import infra.Configuracao; // Importando o Singleton que criamos

public class Main {
    public static void main(String[] args) {
        // 1. Uso do SINGLETON (Configurações globais)
        Configuracao config = Configuracao.getInstance();
        System.out.println("==================================================");
        System.out.println(" SISTEMA DE GESTÃO DE CARBONO - Versão " + config.getVersaoSistema());
        System.out.println(" Moeda base: " + config.getMoedaPadrao());
        System.out.println("==================================================\n");

        // 2. Configuração do OBSERVER (Monitoramento)
        // Definimos uma meta de 200kg de CO2. Se passar disso, o sistema avisa.
        GestorDeEmissoes gestor = new GestorDeEmissoes(200.0);
        
        // Registrando quem vai receber os alertas
        gestor.adicionarObservador(new EmailNotificador());
        gestor.adicionarObservador(new LogNotificador());

        // 3. Uso do STRATEGY (Cálculo de Emissões Variável)
        System.out.println(">>> INICIANDO REGISTRO DE EMISSÕES (STRATEGY)");

        // Cenário A: Queima de Combustível (Escopo 1)
        System.out.println("\n[Evento 1] Abastecimento da frota (Diesel)");
        gestor.setEstrategia(new CalculoEscopo1()); // Troca a estratégia para Escopo 1
        gestor.registrarConsumo(50); // 50 litros
        
        // Cenário B: Consumo de Energia (Escopo 2)
        System.out.println("\n[Evento 2] Conta de Luz da Fábrica");
        gestor.setEstrategia(new CalculoEscopo2()); // Troca a estratégia para Escopo 2
        gestor.registrarConsumo(300); // 300 kWh

        // Cenário C: Viagens Corporativas (Escopo 3)
        // OBS: Aqui vamos forçar o estouro da meta (> 200kg) para testar o Observer
        System.out.println("\n[Evento 3] Viagem internacional da diretoria");
        gestor.setEstrategia(new CalculoEscopo3()); // Troca a estratégia para Escopo 3
        gestor.registrarConsumo(1500); // 1500 km rodados

        // Exibir total acumulado
        System.out.println("\n--------------------------------------------------");
        System.out.println("TOTAL DE EMISSÕES ACUMULADO: " + gestor.getTotalEmitido() + " kg CO2");
        System.out.println("--------------------------------------------------\n");

        // 4. Uso do DECORATOR (Cálculo de Preço com Taxas)
        System.out.println(">>> INICIANDO COMPENSAÇÃO DE CRÉDITOS (DECORATOR)");
        
        // Converter kg para toneladas (pois o preço é por tonelada)
        double toneladasParaCompensar = gestor.getTotalEmitido() / 1000.0;
        
        // Começa com o serviço base
        IServicoCompensacao servico = new CompensacaoBase();
        System.out.println("1. Orçamento Inicial (" + servico.getDescricao() + "):");
        System.out.printf("   Valor: R$ %.2f\n", servico.getPreco(toneladasParaCompensar));

        // Decora com Certificação Gold (Encapsula o objeto anterior)
        servico = new CertificacaoGoldDecorator(servico);
        System.out.println("\n2. Adicionando Certificação (" + servico.getDescricao() + "):");
        System.out.printf("   Valor Final: R$ %.2f\n", servico.getPreco(toneladasParaCompensar));

        // 5. Rodapé Obrigatório (Exigência do Professor)
        System.out.println("\n\n==================================================");
        System.out.println("Desenvolvido por: Leonardo Luis Abelino");
        System.out.println("Trabalho 04 - Design Patterns");
        System.out.println("==================================================");
    }
}
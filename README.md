# Trabalho 04 - Design Patterns: Mercado de Carbono

**Disciplina:** Linguagem de Programação e Paradigmas
**Curso:** Sistemas de Informação
**Aluno:** Leonardo Luis Abelino

---

## 1. Descrição do Problema
O sistema simula um **Mercado de Carbono**, onde empresas precisam gerenciar suas emissões de CO2. O desafio técnico envolve:
* **Cálculos Variáveis:** A forma de calcular a emissão muda drasticamente dependendo da fonte (combustível fóssil, consumo elétrico ou viagens).
* **Taxas Dinâmicas:** Ao comprar créditos para compensação, existem taxas que podem ser adicionadas e acumuladas (certificações, taxas de serviço, impostos).
* **Monitoramento:** É necessário monitorar constantemente se a empresa ultrapassou sua meta de emissão estabelecida, notificando os gestores imediatamente.

---

## 2. Padrões de Projeto Aplicados

### A. Strategy (Cálculo de Emissões)
* **Problema:** Evitar múltiplos `if/else` para calcular emissões de fontes diferentes (Escopo 1, 2 e 3).
* **Solução:** Criamos a interface `ICalculoEmissao`. Cada regra de cálculo (ex: `CalculoEscopo1`, `CalculoEscopo2`) é uma classe separada.
* **Benefício:** Permite trocar a regra de cálculo em tempo de execução (runtime) e adicionar novos tipos de emissão sem alterar o código principal.

### B. Decorator (Compensação e Taxas)
* **Problema:** Precisamos adicionar serviços extras (como "Certificação Gold" ou "Taxa Administrativa") ao valor base da compensação, de forma dinâmica e acumulativa.
* **Solução:** A interface `IServicoCompensacao` é implementada pelo serviço base e pelos decoradores (`CertificacaoGoldDecorator`, `TaxaAdministrativaDecorator`).
* **Benefício:** Permite "empilhar" taxas sobre o preço base sem criar classes rígidas como `CompensacaoComGoldEComTaxa`.

### C. Observer (Monitoramento de Metas)
* **Problema:** O sistema precisa disparar alertas (Email, Log) quando a meta de carbono é excedida, mas o módulo de cálculo não deve saber os detalhes de como notificar.
* **Solução:** A classe `GestorDeEmissoes` atua como **Subject**, mantendo uma lista de observadores (`IObservadorMeta`). Quando o limite é atingido, ela notifica todos da lista.
* **Benefício:** Desacoplamento total. Podemos adicionar novos tipos de notificação (ex: SMS, WhatsApp) sem mexer na lógica de negócio.

---

## 3. Diagrama de Classes
```mermaid
classDiagram
    %% Strategy
    class ICalculoEmissao { <<interface>> +calcular() }
    class CalculoEscopo1 { +calcular() }
    class CalculoEscopo2 { +calcular() }
    CalculoEscopo1 ..|> ICalculoEmissao
    CalculoEscopo2 ..|> ICalculoEmissao
    
    %% Decorator
    class IServicoCompensacao { <<interface>> +getPreco() }
    class CompensacaoBase { +getPreco() }
    class Decorator { +getPreco() }
    CompensacaoBase ..|> IServicoCompensacao
    Decorator --|> IServicoCompensacao

    %% Observer
    class GestorDeEmissoes { +registrarConsumo() }
    class IObservadorMeta { <<interface>> +atualizar() }
    GestorDeEmissoes --> IObservadorMeta : Notifica
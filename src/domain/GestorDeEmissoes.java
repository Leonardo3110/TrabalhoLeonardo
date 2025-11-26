package domain;
import strategies.ICalculoEmissao;
import observers.IObservadorMeta;
import java.util.ArrayList;
import java.util.List;

public class GestorDeEmissoes {
    private double totalEmitido = 0;
    private double metaMaxima;
    private List<IObservadorMeta> observadores = new ArrayList<>();

    // Estratégia atual (Pattern Strategy)
    private ICalculoEmissao estrategiaCalculo;

    public GestorDeEmissoes(double meta) {
        this.metaMaxima = meta;
    }

    // Define qual estratégia usar (Strategy)
    public void setEstrategia(ICalculoEmissao estrategia) {
        this.estrategiaCalculo = estrategia;
    }

    // Adiciona observador (Observer)
    public void adicionarObservador(IObservadorMeta obs) {
        observadores.add(obs);
    }

    public void registrarConsumo(double quantidade) {
        if (estrategiaCalculo == null) {
            throw new RuntimeException("Defina uma estratégia de cálculo primeiro!");
        }

        double emissaoCalculada = estrategiaCalculo.calcular(quantidade);
        this.totalEmitido += emissaoCalculada;

        System.out.println("Emissão registrada: " + emissaoCalculada + "kg. Total: " + totalEmitido);

        // Verifica Meta (Observer Trigger)
        if (totalEmitido > metaMaxima) {
            notificarObservadores("ALERTA: Meta de " + metaMaxima + "kg excedida! Atual: " + totalEmitido);
        }
    }

    private void notificarObservadores(String msg) {
        for (IObservadorMeta obs : observadores) {
            obs.atualizar(msg);
        }
    }

    public double getTotalEmitido() { return totalEmitido; }
}
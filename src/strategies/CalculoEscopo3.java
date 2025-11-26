package strategies;
public class CalculoEscopo3 implements ICalculoEmissao {
    @Override
    public double calcular(double kmViagem) {
        return kmViagem * 0.15; // Exemplo: Voo comercial
    }
}
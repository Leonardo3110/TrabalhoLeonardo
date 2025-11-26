package strategies;
public class CalculoEscopo2 implements ICalculoEmissao {
    @Override
    public double calcular(double kwh) {
        return kwh * 0.5; // Exemplo: Rede elétrica
    }
}
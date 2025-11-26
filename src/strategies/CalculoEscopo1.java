package strategies;
public class CalculoEscopo1 implements ICalculoEmissao {
    @Override
    public double calcular(double litros) {
        return litros * 2.3; // Exemplo: Diesel emite 2.3kg/litro
    }
}
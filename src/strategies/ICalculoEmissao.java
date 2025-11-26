package strategies;
public interface ICalculoEmissao {
    // Recebe quantidade (ex: litros, kwh) e retorna kg de CO2
    double calcular(double quantidade);
}
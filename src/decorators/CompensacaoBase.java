package decorators;
public class CompensacaoBase implements IServicoCompensacao {
    @Override
    public double getPreco(double toneladasCO2) {
        return toneladasCO2 * 50.0; // R$ 50,00 por tonelada base
    }
    @Override
    public String getDescricao() {
        return "Compensação de Carbono Padrão";
    }
}
package decorators;
public class CertificacaoGoldDecorator implements IServicoCompensacao {
    private IServicoCompensacao envolucro;

    public CertificacaoGoldDecorator(IServicoCompensacao envolucro) {
        this.envolucro = envolucro;
    }

    @Override
    public double getPreco(double toneladasCO2) {
        return envolucro.getPreco(toneladasCO2) * 1.20; // +20% taxa
    }
    @Override
    public String getDescricao() {
        return envolucro.getDescricao() + " + Certificado Gold Standard";
    }
}
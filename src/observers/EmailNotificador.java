package observers;
public class EmailNotificador implements IObservadorMeta {
    @Override
    public void atualizar(String mensagem) {
        System.out.println("📧 [EMAIL ENVIADO] " + mensagem);
    }
}
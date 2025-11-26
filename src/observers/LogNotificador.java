package observers;
public class LogNotificador implements IObservadorMeta {
    @Override
    public void atualizar(String mensagem) {
        System.out.println("📝 [LOG DO SISTEMA] " + mensagem);
    }
}
public class PagoTransferencia extends MetodoPago implements Autenticable {

    public PagoTransferencia(double monto) {
        super(monto);
    }

    @Override
    public boolean autenticar() {
        // Simula validación externa (puede ser aleatorio o fijo para ejemplo)
        // Aquí simulo que siempre falla para mostrar el ejemplo:
        return false;
    }

    @Override
    public void procesarPago() {
        System.out.println("🔄 Procesando transferencia por $" + monto);
    }
}


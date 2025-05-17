public class PagoTarjeta extends MetodoPago implements Autenticable {
    private double saldoDisponible = 500; // Por ejemplo, saldo disponible

    public PagoTarjeta(double monto) {
        super(monto);
    }

    @Override
    public boolean autenticar() {
        // Validar fondos suficientes
        return monto <= saldoDisponible;
    }

    @Override
    public void procesarPago() {
        System.out.println("💳 Procesando pago con tarjeta por $" + monto);
    }
}

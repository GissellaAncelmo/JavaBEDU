import java.util.Scanner;

public class CajeroAutomatico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var saldo = 1000.0; // saldo inicial inferido con var
        int opcion;

        do {
            // Mostrar menú
            System.out.println("\nBienvenido al cajero automático");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("💰 Saldo actual: $" + saldo);
                    break;

                case 2:
                    System.out.print("Ingrese monto a depositar: ");
                    double deposito = scanner.nextDouble();
                    if (deposito <= 0) {
                        System.out.println("⚠️ Monto inválido. Debe ser mayor a 0.");
                        continue;
                    }
                    saldo += deposito;
                    System.out.println("✅ Depósito exitoso. Nuevo saldo: $" + saldo);
                    break;

                case 3:
                    System.out.print("Ingrese monto a retirar: ");
                    double retiro = scanner.nextDouble();
                    if (retiro <= 0) {
                        System.out.println("⚠️ Monto inválido. Debe ser mayor a 0.");
                        continue;
                    }
                    if (retiro > saldo) {
                        System.out.println("❌ Saldo insuficiente. No se puede retirar esa cantidad.");
                        continue;
                    }
                    saldo -= retiro;
                    System.out.println("✅ Retiro exitoso. Nuevo saldo: $" + saldo);
                    break;

                case 4:
                    System.out.println("👋 Gracias por usar el cajero automático. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("❌ Opción inválida. Intente de nuevo.");
                    continue;
            }

        } while (opcion != 4);

        scanner.close();
    }
}

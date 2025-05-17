import java.util.Scanner;

public class SimuladorFarmacia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos al usuario
        System.out.print("Nombre del medicamento: ");
        String nombreMedicamento = scanner.nextLine();

        System.out.print("Precio unitario: ");
        double precioUnitario = scanner.nextDouble();

        System.out.print("Cantidad de piezas: ");
        int cantidad = scanner.nextInt();

        // Cálculo del total sin descuento
        double totalSinDescuento = precioUnitario * cantidad;

        // Usamos var para inferencia de tipo
        var aplicaDescuento = totalSinDescuento > 500;

        // Operador ternario para calcular descuento
        double descuento = aplicaDescuento ? totalSinDescuento * 0.15 : 0.0;

        // Total final con descuento
        double totalFinal = totalSinDescuento - descuento;

        // Mostrar resumen
        System.out.println("\n--- Resumen de Compra ---");
        System.out.println("Medicamento: " + nombreMedicamento);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Precio unitario: $" + precioUnitario);
        System.out.println("Total sin descuento: $" + totalSinDescuento);
        System.out.println("¿Aplica descuento?: " + aplicaDescuento);
        System.out.println("Descuento: $" + descuento);
        System.out.println("Total a pagar: $" + totalFinal);

        scanner.close();
    }
}

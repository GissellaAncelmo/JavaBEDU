public class Main {
    public static void main(String[] args) {
        // Crear dos facturas con el mismo folio pero diferente cliente
        Factura factura1 = new Factura("FAC001", "Juan Pérez", 1450.0);
        Factura factura2 = new Factura("FAC001", "Comercial XYZ", 1450.0);

        // Mostrar ambas facturas
        System.out.println(factura1);
        System.out.println(factura2);

        // Comparar si son iguales
        System.out.println("¿Las facturas son iguales?: " + factura1.equals(factura2));
    }
}

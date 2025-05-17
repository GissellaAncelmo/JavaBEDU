// Entrada_Record.java
public record Entrada_Record(String nombreEvento, double precio) {
    public void mostrarInformacion() {
        System.out.println("Evento: " + nombreEvento + " | Precio: $" + precio);
    }
}


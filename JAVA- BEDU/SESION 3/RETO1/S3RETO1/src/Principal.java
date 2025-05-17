public class Principal {
    public static void main(String[] args) {
        // Crear pasajero y vuelo
        Pasajero p1 = new Pasajero("Ana Martínez", "P123456");
        Vuelo vuelo1 = new Vuelo("UX123", "París", "14:30");

        // Reservar asiento
        if (vuelo1.reservarAsiento(p1)) {
            System.out.println("✅ Reserva realizada con éxito.");
        }

        // Mostrar itinerario
        System.out.println(vuelo1.obtenerItinerario());

        // Cancelar reserva
        System.out.println("❌ Cancelando reserva...");
        vuelo1.cancelarReserva();

        // Mostrar itinerario nuevamente
        System.out.println(vuelo1.obtenerItinerario());

        // Reservar con nombre y pasaporte directamente
        vuelo1.reservarAsiento("Mario Gonzalez", "P987654");

        // Mostrar itinerario final
        System.out.println(vuelo1.obtenerItinerario());
    }
}


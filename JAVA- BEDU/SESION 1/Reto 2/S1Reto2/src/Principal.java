// Principal.java
public class Principal {
    public static void main(String[] args) {
        // Usando la clase Entrada
        Entrada entrada1 = new Entrada("Obra de Teatro", 450.0);
        Entrada entrada2 = new Entrada("Concierto de Rock", 850.0);

        entrada1.mostrarInformacion();
        entrada2.mostrarInformacion();

        System.out.println(); // Espacio

        // Usando el record Entrada_Record (desafío adicional)
        Entrada_Record entrada3 = new Entrada_Record("Feria del Libro", 120.0);
        entrada3.mostrarInformacion();
    }
}

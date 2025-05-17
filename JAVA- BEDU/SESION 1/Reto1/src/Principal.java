
import java.util.Scanner;
public class Principal {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Paciente paciente = new Paciente();

        System.out.println("=== Registro de Paciente ===");

        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();
        paciente.setNombre(nombre);

        System.out.print("Ingrese la edad del paciente: ");
        int edad = scanner.nextInt();
        paciente.setEdad(edad);


        scanner.nextLine();

        System.out.print("Ingrese el número de expediente: ");
        String expediente = scanner.nextLine();
        paciente.setNumeroExpediente(expediente);


        System.out.println("\n=== Información del Paciente ===");
        paciente.mostrarInformacion();


        scanner.close();
    }
}
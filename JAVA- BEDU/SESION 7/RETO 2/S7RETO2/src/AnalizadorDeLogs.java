import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AnalizadorDeLogs {

    public static void main(String[] args) {
        String rutaArchivoLog = "errores.log";
        int totalLineas = 0;
        int errores = 0;
        int advertencias = 0;

        // Bloque try-with-resources para leer el archivo línea por línea
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivoLog))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                totalLineas++;

                if (linea.contains("ERROR")) {
                    errores++;
                }

                if (linea.contains("WARNING")) {
                    advertencias++;
                }
            }

            // Mostrar resumen
            System.out.println("📄 Resumen del Análisis de Logs:");
            System.out.println("Total de líneas leídas: " + totalLineas);
            System.out.println("Cantidad de errores (ERROR): " + errores);
            System.out.println("Cantidad de advertencias (WARNING): " + advertencias);

            double porcentaje = ((double)(errores + advertencias) / totalLineas) * 100;
            System.out.printf("Porcentaje de líneas con errores o advertencias: %.2f%%\n", porcentaje);

        } catch (IOException e) {
            // En caso de error, registrar en archivo
            System.out.println("⚠️ Ocurrió un error al leer el archivo. Revisando registro de fallos...");
            registrarFallo(e.getMessage());
        }
    }

    // Método para registrar fallos en un archivo
    private static void registrarFallo(String mensaje) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("registro_fallos.txt", true))) {
            escritor.write("Error al procesar errores.log: " + mensaje);
            escritor.newLine();
        } catch (IOException ex) {
            System.out.println("❌ No se pudo escribir en registro_fallos.txt");
        }
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RegistroSimulacion {

    // Ruta del archivo de configuración
    private static final Path RUTA_ARCHIVO = Paths.get("config", "parametros.txt");

    public static void main(String[] args) {
        try {
            // Guardar parámetros en el archivo
            guardarParametros();

            // Validar que el archivo existe
            if (Files.exists(RUTA_ARCHIVO)) {
                System.out.println("Archivo creado correctamente.");

                // Leer y mostrar el contenido del archivo
                leerParametros();
            } else {
                System.out.println("No se pudo crear el archivo.");
            }
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }

    // Método para guardar los parámetros en el archivo
    private static void guardarParametros() throws IOException {
        // Crear carpeta config si no existe
        if (!Files.exists(RUTA_ARCHIVO.getParent())) {
            Files.createDirectories(RUTA_ARCHIVO.getParent());
            System.out.println("Directorio 'config' creado.");
        }

        // Contenido con los parámetros de simulación
        String contenido = """
                Tiempo de ciclo: 55.8 segundos
                Velocidad de línea: 1.2 m/s
                Número de estaciones: 8
                """;


        Files.writeString(RUTA_ARCHIVO, contenido);
    }

    // Método para leer y mostrar el contenido del archivo
    private static void leerParametros() throws IOException {
        String contenidoLeido = Files.readString(RUTA_ARCHIVO);
        System.out.println("Contenido del archivo parametros.txt:");
        System.out.println(contenidoLeido);
    }
}

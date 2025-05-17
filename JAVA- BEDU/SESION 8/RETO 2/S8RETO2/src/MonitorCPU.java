import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Excepción personalizada para cuando el consumo de CPU alcanza niveles críticos
 */
class ConsumoCriticoException extends Exception {
    public ConsumoCriticoException(String mensaje) {
        super(mensaje);
    }
}

/**
 * Clase que representa un registro de consumo de CPU para un servidor
 */
class RegistroConsumo {
    private String nombreServidor;
    private double porcentajeConsumo;

    public RegistroConsumo(String nombreServidor, double porcentajeConsumo) {
        this.nombreServidor = nombreServidor;
        this.porcentajeConsumo = porcentajeConsumo;
    }

    public String getNombreServidor() {
        return nombreServidor;
    }

    public double getPorcentajeConsumo() {
        return porcentajeConsumo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        RegistroConsumo that = (RegistroConsumo) obj;
        return nombreServidor.equals(that.nombreServidor);
    }

    @Override
    public int hashCode() {
        return nombreServidor.hashCode();
    }

    @Override
    public String toString() {
        return "Servidor: " + nombreServidor + ", Consumo CPU: " + porcentajeConsumo + "%";
    }
}

/**
 * Clase principal que implementa la herramienta de monitoreo de CPU
 */
public class MonitorCPU {

    private static final double UMBRAL_CRITICO = 95.0;
    private Set<RegistroConsumo> registros;
    private Scanner scanner;

    public MonitorCPU() {
        registros = new HashSet<>();
        scanner = new Scanner(System.in);
    }

    /**
     * Valida y registra el consumo de CPU de un servidor
     * @param nombreServidor Nombre del servidor
     * @param consumoCPU Porcentaje de consumo de CPU
     * @throws NumberFormatException Si el valor no es numérico
     * @throws IllegalArgumentException Si el valor está fuera del rango permitido
     * @throws ConsumoCriticoException Si el consumo supera el umbral crítico
     */
    public void registrarConsumo(String nombreServidor, String consumoCPU)
            throws NumberFormatException, IllegalArgumentException, ConsumoCriticoException {

        // Validar que el valor sea numérico
        double porcentaje;
        try {
            porcentaje = Double.parseDouble(consumoCPU);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error: El valor de consumo debe ser numérico.");
        }

        // Validar que el valor esté en el rango permitido
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("Error: El consumo debe estar entre 0% y 100%.");
        }

        // Validar que no supere el umbral crítico
        if (porcentaje > UMBRAL_CRITICO) {
            throw new ConsumoCriticoException("¡ALERTA CRÍTICA! El servidor " + nombreServidor +
                    " tiene un consumo de CPU del " + porcentaje + "%, superando el umbral crítico del " + UMBRAL_CRITICO + "%.");
        }

        // Crear y agregar el registro
        RegistroConsumo registro = new RegistroConsumo(nombreServidor, porcentaje);

        // Verificar si ya existe un registro para este servidor
        if (!registros.add(registro)) {
            System.out.println("Advertencia: Ya existe un registro para el servidor " + nombreServidor +
                    ". El valor anterior será actualizado.");

            // Si queremos actualizar el valor existente, debemos eliminarlo primero
            registros.removeIf(r -> r.getNombreServidor().equals(nombreServidor));
            registros.add(registro);
        } else {
            System.out.println("Registro añadido correctamente para el servidor " + nombreServidor);
        }
    }

    /**
     * Muestra todos los registros actuales
     */
    public void mostrarRegistros() {
        if (registros.isEmpty()) {
            System.out.println("No hay registros para mostrar.");
            return;
        }

        System.out.println("\n=== REGISTROS DE CONSUMO DE CPU ===");
        registros.forEach(System.out::println);

        // Calcular promedio de consumo
        double promedio = registros.stream()
                .mapToDouble(RegistroConsumo::getPorcentajeConsumo)
                .average()
                .orElse(0.0);

        System.out.printf("Promedio de consumo: %.2f%%\n", promedio);

        // Identificar servidor con mayor consumo
        registros.stream()
                .max((r1, r2) -> Double.compare(r1.getPorcentajeConsumo(), r2.getPorcentajeConsumo()))
                .ifPresent(r -> System.out.println("Servidor con mayor consumo: " + r.getNombreServidor() +
                        " (" + r.getPorcentajeConsumo() + "%)"));
    }

    /**
     * Inicializa el flujo de trabajo de la aplicación
     */
    public void iniciar() {
        System.out.println("=== MONITOR DE CONSUMO DE CPU ===");
        System.out.println("Ingrese los datos de los servidores (o 'salir' para terminar)");

        boolean continuar = true;

        while (continuar) {
            try {
                System.out.print("\nNombre del servidor (o 'salir'): ");
                String nombreServidor = scanner.nextLine().trim();

                if (nombreServidor.equalsIgnoreCase("salir")) {
                    continuar = false;
                    continue;
                }

                if (nombreServidor.isEmpty()) {
                    System.out.println("Error: El nombre del servidor no puede estar vacío.");
                    continue;
                }

                System.out.print("Consumo de CPU (%): ");
                String consumoCPU = scanner.nextLine().trim();

                // Intentar registrar el consumo
                registrarConsumo(nombreServidor, consumoCPU);

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            } catch (ConsumoCriticoException e) {
                System.err.println("\n*** " + e.getMessage() + " ***\n");
                System.err.println("Se ha registrado un evento crítico que requiere atención inmediata.");
            } catch (Exception e) {
                System.err.println("Error inesperado: " + e.getMessage());
            }
        }

        // Mostrar resultados finales
        mostrarRegistros();
    }

    /**
     * Limpia los recursos utilizados
     */
    public void cerrar() {
        if (scanner != null) {
            scanner.close();
            System.out.println("Recursos liberados correctamente.");
        }
    }

    /**
     * Método principal que ejecuta la aplicación
     */
    public static void main(String[] args) {
        MonitorCPU monitor = new MonitorCPU();

        try {
            monitor.iniciar();
        } finally {
            monitor.cerrar();
        }

        System.out.println("\n=== MONITOREO FINALIZADO ===");
    }

    /**
     * Método para facilitar pruebas con datos predefinidos
     */
    public void cargarDatosPrueba() {
        try {
            registrarConsumo("srv-db-01", "78.5");
            registrarConsumo("srv-web-01", "65.3");
            registrarConsumo("srv-app-01", "92.1");
            registrarConsumo("srv-backup-01", "25.7");
            // Este debería lanzar excepción por estar sobre el umbral crítico
            registrarConsumo("srv-auth-01", "97.8");
        } catch (ConsumoCriticoException e) {
            System.err.println("\n*** " + e.getMessage() + " ***\n");
        } catch (Exception e) {
            System.err.println("Error al cargar datos de prueba: " + e.getMessage());
        }
    }
}
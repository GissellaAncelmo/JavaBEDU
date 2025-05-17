import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class LaboratorioBiotecnologia {

    public static void main(String[] args) {
        // Paso 1: ArrayList para registrar todas las especies en orden de llegada
        ArrayList<String> ordenMuestras = new ArrayList<>();
        ordenMuestras.add("Homo sapiens");
        ordenMuestras.add("Mus musculus");
        ordenMuestras.add("Arabidopsis thaliana");
        ordenMuestras.add("Homo sapiens"); // repetida, debe mantenerse para el registro

        // Paso 2: HashSet para filtrar especies únicas (sin orden)
        HashSet<String> especiesUnicas = new HashSet<>(ordenMuestras);

        // Paso 3: HashMap para asociar ID de muestra con investigador
        HashMap<String, String> mapaMuestrasInvestigadores = new HashMap<>();
        mapaMuestrasInvestigadores.put("M-001", "Dra. López");
        mapaMuestrasInvestigadores.put("M-002", "Dr. Hernández");
        mapaMuestrasInvestigadores.put("M-003", "Dra. Martínez");

        // Paso 4: Mostrar resultados

        // Mostrar la lista completa y ordenada de muestras
        System.out.println("📥 Lista completa y ordenada de muestras:");
        for (String especie : ordenMuestras) {
            System.out.println(" - " + especie);
        }

        // Mostrar las especies únicas procesadas
        System.out.println("\n🧬 Especies únicas procesadas:");
        for (String especieUnica : especiesUnicas) {
            System.out.println(" - " + especieUnica);
        }

        // Mostrar la relación de ID de muestra → investigador
        System.out.println("\n🧑‍🔬 Relación ID muestra → investigador:");
        for (String idMuestra : mapaMuestrasInvestigadores.keySet()) {
            String investigador = mapaMuestrasInvestigadores.get(idMuestra);
            System.out.println(" - " + idMuestra + " → " + investigador);
        }

        // Búsqueda por ID de muestra: "M-002"
        String idBusqueda = "M-002";
        System.out.println("\n🔍 Búsqueda por ID de muestra: " + idBusqueda);
        if (mapaMuestrasInvestigadores.containsKey(idBusqueda)) {
            System.out.println("Investigador responsable: " + mapaMuestrasInvestigadores.get(idBusqueda));
        } else {
            System.out.println("ID de muestra no encontrado.");
        }
    }
}

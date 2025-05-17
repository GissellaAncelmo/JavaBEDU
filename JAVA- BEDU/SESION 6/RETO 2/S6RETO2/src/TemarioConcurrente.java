import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.Comparator;

class Tema implements Comparable<Tema> {
    private String titulo;
    private int prioridad;

    public Tema(String titulo, int prioridad) {
        this.titulo = titulo;
        this.prioridad = prioridad;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    // Orden natural por título (alfabético)
    @Override
    public int compareTo(Tema otro) {
        return this.titulo.compareToIgnoreCase(otro.titulo);
    }

    @Override
    public String toString() {
        return "Tema{" +
                "titulo='" + titulo + '\'' +
                ", prioridad=" + prioridad +
                '}';
    }
}

public class TemarioConcurrente {

    public static void main(String[] args) {
        // Lista concurrente para temas activos
        CopyOnWriteArrayList<Tema> temas = new CopyOnWriteArrayList<>();

        // Agregar temas
        temas.add(new Tema("Lectura comprensiva", 2));
        temas.add(new Tema("Matemáticas básicas", 1));
        temas.add(new Tema("Cuidado del medio ambiente", 3));
        temas.add(new Tema("Arte y cultura", 2));
        temas.add(new Tema("Ciencias naturales", 1));

        // Mostrar lista ordenada alfabéticamente (orden natural usando Comparable)
        Collections.sort(temas);
        System.out.println("Temas ordenados alfabéticamente:");
        for (Tema tema : temas) {
            System.out.println(tema);
        }

        // Ordenar lista por prioridad ascendente (1 = urgente, 3 = opcional) usando Comparator
        temas.sort(Comparator.comparingInt(Tema::getPrioridad));
        System.out.println("\nTemas ordenados por prioridad ascendente:");
        for (Tema tema : temas) {
            System.out.println(tema);
        }

        // Repositorio concurrente de recursos (clave = título, valor = enlace o descripción)
        ConcurrentHashMap<String, String> recursos = new ConcurrentHashMap<>();

        recursos.put("Lectura comprensiva", "https://recursos.edu/lectura");
        recursos.put("Matemáticas básicas", "https://recursos.edu/mate");
        recursos.put("Cuidado del medio ambiente", "https://recursos.edu/medioambiente");
        recursos.put("Arte y cultura", "https://recursos.edu/arte");
        recursos.put("Ciencias naturales", "https://recursos.edu/ciencias");

        System.out.println("\nRepositorio concurrente de recursos:");
        for (String clave : recursos.keySet()) {
            System.out.println("Tema: " + clave + " → Recurso: " + recursos.get(clave));
        }
    }
}

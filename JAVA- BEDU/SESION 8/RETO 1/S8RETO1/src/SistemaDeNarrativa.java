import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Sistema de Narrativa para videojuegos
 * Implementación refactorizada siguiendo principios SOLID
 */
public class SistemaDeNarrativa {
    public static void main(String[] args) {
        // Crear instancias de componentes
        TransicionHistoria transicion = new TransicionSimple();
        GestorDialogo dialogo = new DialogoTexto();
        LogicaDecision decision = new DecisionBinaria();

        // Crear el sistema narrativo con inyección de dependencias
        MainNarrativa narrativa = new MainNarrativa(transicion, dialogo, decision);

        // Ejecutar simulación interactiva
        Scanner scanner = new Scanner(System.in);
        narrativa.iniciarEscena();

        boolean continuar = true;
        while (continuar) {
            System.out.print("\nIngrese su decisión (o 'salir' para terminar): ");
            String eleccion = scanner.nextLine().toLowerCase();

            if (eleccion.equals("salir")) {
                continuar = false;
            } else {
                narrativa.procesarDecisionJugador(eleccion);
            }
        }

        System.out.println("\n¡Gracias por jugar!");
        scanner.close();
    }
}

/**
 * Interface que define cómo se manejan las transiciones entre escenas en la historia
 */
interface TransicionHistoria {
    /**
     * Realiza la transición a una nueva escena
     * @param escenaActual La escena actual
     * @param decision La decisión tomada por el jugador
     * @return La nueva escena
     */
    String transicionar(String escenaActual, String decision);
}

/**
 * Interface que define cómo se manejan los diálogos en el juego
 */
interface GestorDialogo {
    /**
     * Muestra un diálogo en la escena actual
     * @param escena La escena actual
     * @param personaje El personaje que habla
     * @param contexto Información adicional de contexto
     * @return El diálogo generado
     */
    String mostrarDialogo(String escena, String personaje, String contexto);
}

/**
 * Interface que define cómo se procesan las decisiones del jugador
 */
interface LogicaDecision {
    /**
     * Procesa la decisión tomada por el jugador
     * @param decision La opción elegida por el jugador
     * @param contexto El contexto actual del juego
     * @return El resultado de la decisión
     */
    String procesarDecision(String decision, String contexto);

    /**
     * Obtiene las opciones disponibles para el jugador
     * @param escena La escena actual
     * @return Array de opciones disponibles
     */
    String[] obtenerOpciones(String escena);
}

/**
 * Implementación simple de transiciones entre escenas
 */
class TransicionSimple implements TransicionHistoria {
    private final Map<String, Map<String, String>> mapaTransiciones;

    public TransicionSimple() {
        mapaTransiciones = new HashMap<>();

        // Inicializar algunas transiciones de ejemplo
        Map<String, String> transicionesInicio = new HashMap<>();
        transicionesInicio.put("explorar", "bosque");
        transicionesInicio.put("hablar", "aldeano");
        mapaTransiciones.put("inicio", transicionesInicio);

        Map<String, String> transicionesBosque = new HashMap<>();
        transicionesBosque.put("avanzar", "cueva");
        transicionesBosque.put("retroceder", "inicio");
        mapaTransiciones.put("bosque", transicionesBosque);

        Map<String, String> transicionesAldeano = new HashMap<>();
        transicionesAldeano.put("preguntar", "taberna");
        transicionesAldeano.put("ignorar", "inicio");
        mapaTransiciones.put("aldeano", transicionesAldeano);
    }

    @Override
    public String transicionar(String escenaActual, String decision) {
        if (mapaTransiciones.containsKey(escenaActual) &&
                mapaTransiciones.get(escenaActual).containsKey(decision)) {
            return mapaTransiciones.get(escenaActual).get(decision);
        }
        return escenaActual; // Si no hay transición definida, permanece en la misma escena
    }
}

/**
 * Implementación de diálogos basados en texto
 */
class DialogoTexto implements GestorDialogo {
    private final Map<String, Map<String, String>> dialogosPorEscena;

    public DialogoTexto() {
        dialogosPorEscena = new HashMap<>();

        // Inicializar algunos diálogos de ejemplo
        Map<String, String> dialogosInicio = new HashMap<>();
        dialogosInicio.put("narrador", "Te encuentras en una pequeña aldea. Puedes explorar el bosque cercano o hablar con un aldeano.");
        dialogosPorEscena.put("inicio", dialogosInicio);

        Map<String, String> dialogosBosque = new HashMap<>();
        dialogosBosque.put("narrador", "El bosque es denso y misterioso. A lo lejos ves la entrada de una cueva.");
        dialogosBosque.put("explorador", "Estos árboles son muy antiguos, puedo sentir su magia.");
        dialogosPorEscena.put("bosque", dialogosBosque);

        Map<String, String> dialogosAldeano = new HashMap<>();
        dialogosAldeano.put("aldeano", "¡Forastero! Ten cuidado si vas al bosque. Han desaparecido personas últimamente.");
        dialogosAldeano.put("narrador", "El aldeano parece preocupado. Tal vez puedas preguntar más detalles.");
        dialogosPorEscena.put("aldeano", dialogosAldeano);
    }

    @Override
    public String mostrarDialogo(String escena, String personaje, String contexto) {
        if (dialogosPorEscena.containsKey(escena) &&
                dialogosPorEscena.get(escena).containsKey(personaje)) {
            return personaje + ": " + dialogosPorEscena.get(escena).get(personaje);
        }
        return personaje + ": ..."; // Diálogo por defecto
    }
}

/**
 * Implementación de decisiones binarias (dos opciones)
 */
class DecisionBinaria implements LogicaDecision {
    private final Map<String, String[]> opcionesPorEscena;
    private final Map<String, Map<String, String>> resultadosPorDecision;

    public DecisionBinaria() {
        opcionesPorEscena = new HashMap<>();
        resultadosPorDecision = new HashMap<>();

        // Inicializar opciones de ejemplo
        opcionesPorEscena.put("inicio", new String[]{"explorar", "hablar"});
        opcionesPorEscena.put("bosque", new String[]{"avanzar", "retroceder"});
        opcionesPorEscena.put("aldeano", new String[]{"preguntar", "ignorar"});

        // Inicializar resultados de decisiones
        Map<String, String> resultadosInicio = new HashMap<>();
        resultadosInicio.put("explorar", "Has decidido explorar el bosque misterioso.");
        resultadosInicio.put("hablar", "Te acercas al aldeano para conversar.");
        resultadosPorDecision.put("inicio", resultadosInicio);

        Map<String, String> resultadosBosque = new HashMap<>();
        resultadosBosque.put("avanzar", "Decides adentrarte más y te diriges hacia la cueva.");
        resultadosBosque.put("retroceder", "Prefieres volver a la seguridad de la aldea.");
        resultadosPorDecision.put("bosque", resultadosBosque);

        Map<String, String> resultadosAldeano = new HashMap<>();
        resultadosAldeano.put("preguntar", "Preguntas al aldeano sobre los sucesos extraños.");
        resultadosAldeano.put("ignorar", "Decides ignorar al aldeano y seguir tu camino.");
        resultadosPorDecision.put("aldeano", resultadosAldeano);
    }

    @Override
    public String[] obtenerOpciones(String escena) {
        if (opcionesPorEscena.containsKey(escena)) {
            return opcionesPorEscena.get(escena);
        }
        return new String[0]; // No hay opciones disponibles
    }

    @Override
    public String procesarDecision(String decision, String escena) {
        if (resultadosPorDecision.containsKey(escena) &&
                resultadosPorDecision.get(escena).containsKey(decision)) {
            return resultadosPorDecision.get(escena).get(decision);
        }
        return "Esa acción no tiene efecto en este momento."; // Respuesta por defecto
    }
}

/**
 * Clase principal que integra todos los componentes narrativos
 */
class MainNarrativa {
    private final TransicionHistoria transicionHistoria;
    private final GestorDialogo gestorDialogo;
    private final LogicaDecision logicaDecision;
    private String escenaActual;

    /**
     * Constructor con inyección de dependencias
     */
    public MainNarrativa(TransicionHistoria transicionHistoria,
                         GestorDialogo gestorDialogo,
                         LogicaDecision logicaDecision) {
        this.transicionHistoria = transicionHistoria;
        this.gestorDialogo = gestorDialogo;
        this.logicaDecision = logicaDecision;
        this.escenaActual = "inicio"; // Escena inicial
    }

    /**
     * Inicia la escena actual mostrando diálogos y opciones
     */
    public void iniciarEscena() {
        System.out.println("\n--- Escena: " + escenaActual + " ---");
        System.out.println(gestorDialogo.mostrarDialogo(escenaActual, "narrador", ""));
        mostrarOpcionesDisponibles();
    }

    /**
     * Muestra las opciones disponibles para el jugador
     */
    public void mostrarOpcionesDisponibles() {
        String[] opciones = logicaDecision.obtenerOpciones(escenaActual);
        System.out.println("\nOpciones disponibles:");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
    }

    /**
     * Procesa la decisión del jugador y actualiza el estado del juego
     */
    public void procesarDecisionJugador(String decision) {
        // Verificar si la decisión es válida
        boolean decisionValida = false;
        for (String opcion : logicaDecision.obtenerOpciones(escenaActual)) {
            if (opcion.equalsIgnoreCase(decision)) {
                decisionValida = true;
                break;
            }
        }

        if (!decisionValida) {
            System.out.println("Esa no es una opción válida en este momento.");
            return;
        }

        // Procesar la decisión
        String resultado = logicaDecision.procesarDecision(decision, escenaActual);
        System.out.println("\n" + resultado);

        // Realizar la transición a una nueva escena
        String nuevaEscena = transicionHistoria.transicionar(escenaActual, decision);

        // Si hay cambio de escena, actualizar y mostrar nuevo diálogo
        if (!nuevaEscena.equals(escenaActual)) {
            escenaActual = nuevaEscena;
            System.out.println("\n--- Escena: " + escenaActual + " ---");
            System.out.println(gestorDialogo.mostrarDialogo(escenaActual, "narrador", ""));

            // Si hay personajes en la escena, mostrar sus diálogos
            if (escenaActual.equals("bosque")) {
                System.out.println(gestorDialogo.mostrarDialogo(escenaActual, "explorador", ""));
            } else if (escenaActual.equals("aldeano")) {
                System.out.println(gestorDialogo.mostrarDialogo(escenaActual, "aldeano", ""));
            }

            mostrarOpcionesDisponibles();
        }
    }
}
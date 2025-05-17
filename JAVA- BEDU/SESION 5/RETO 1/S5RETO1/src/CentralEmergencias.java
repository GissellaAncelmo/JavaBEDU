public class CentralEmergencias {
    public static void main(String[] args) {
        // Crear instancias
        Ambulancia ambulancia = new Ambulancia("Ambulancia", new SistemaGPS(), new Sirena(), new Operador("Juan"));
        Patrulla patrulla = new Patrulla("Patrulla", new SistemaGPS(), new Sirena(), new Operador("Laura"));
        UnidadBomberos bomberos = new UnidadBomberos("UnidadBomberos", new SistemaGPS(), new Sirena(), new Operador("Marco"));

        // Iniciar operaciones
        ambulancia.iniciarOperacion();
        System.out.println();
        patrulla.iniciarOperacion();
        System.out.println();
        bomberos.iniciarOperacion();
    }
}

// Clase abstracta
abstract class UnidadEmergencia {
    protected String nombre;

    public UnidadEmergencia(String nombre) {
        this.nombre = nombre;
    }

    public void activarUnidad() {
        System.out.println("🚨 Activando unidad: " + nombre);
    }

    public abstract void responder();
}

// Subclases
class Ambulancia extends UnidadEmergencia {
    private SistemaGPS gps;
    private Sirena sirena;
    private Operador operador;

    public Ambulancia(String nombre, SistemaGPS gps, Sirena sirena, Operador operador) {
        super(nombre);
        this.gps = gps;
        this.sirena = sirena;
        this.operador = operador;
    }

    public void iniciarOperacion() {
        activarUnidad();
        gps.localizar();
        sirena.activarSirena();
        operador.reportarse();
        responder();
    }

    @Override
    public void responder() {
        System.out.println("🩺 Ambulancia en camino al hospital más cercano.");
    }
}

class Patrulla extends UnidadEmergencia {
    private SistemaGPS gps;
    private Sirena sirena;
    private Operador operador;

    public Patrulla(String nombre, SistemaGPS gps, Sirena sirena, Operador operador) {
        super(nombre);
        this.gps = gps;
        this.sirena = sirena;
        this.operador = operador;
    }

    public void iniciarOperacion() {
        activarUnidad();
        gps.localizar();
        sirena.activarSirena();
        operador.reportarse();
        responder();
    }

    @Override
    public void responder() {
        System.out.println("🚓 Patrulla atendiendo situación de seguridad ciudadana.");
    }
}

class UnidadBomberos extends UnidadEmergencia {
    private SistemaGPS gps;
    private Sirena sirena;
    private Operador operador;

    public UnidadBomberos(String nombre, SistemaGPS gps, Sirena sirena, Operador operador) {
        super(nombre);
        this.gps = gps;
        this.sirena = sirena;
        this.operador = operador;
    }

    public void iniciarOperacion() {
        activarUnidad();
        gps.localizar();
        sirena.activarSirena();
        operador.reportarse();
        responder();
    }

    @Override
    public void responder() {
        System.out.println("🔥 Unidad de bomberos respondiendo a incendio estructural.");
    }
}

// Clases auxiliares
class SistemaGPS {
    public void localizar() {
        System.out.println("📍 GPS: Ubicación actual detectada.");
    }
}

class Sirena {
    public void activarSirena() {
        System.out.println("🔊 Sirena: Activada.");
    }
}

class Operador {
    private String nombre;

    public Operador(String nombre) {
        this.nombre = nombre;
    }

    public void reportarse() {
        System.out.println("👷 Operador " + nombre + " reportándose.");
    }
}


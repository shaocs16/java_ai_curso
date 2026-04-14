package unimanager.model;

/**
 * ============================================================
 * CLASE ABSTRACTA: Persona
 * ============================================================
 *
 * ¿Qué es una clase abstracta?
 * Es una clase que NO se puede instanciar directamente.
 * Es decir, NO puedes hacer: new Persona()  ← ERROR de compilación.
 * Su propósito es servir de PLANTILLA común para sus subclases.
 *
 * ¿Cuándo usarla?
 * Cuando tienes un concepto demasiado genérico para existir solo
 * pero que define características comunes a varios tipos:
 * - Una "Persona" en sí misma no tiene sentido en el sistema.
 * - Pero un "Estudiante" y un "Profesor" SÍ tienen sentido,
 *   y ambos COMPARTEN: nombre, id, edad.
 *
 * Diferencia entre clase abstracta e interface:
 * | Aspecto            | Clase Abstracta          | Interface              |
 * |--------------------|--------------------------|------------------------|
 * | Instanciar         | NO                       | NO                     |
 * | Herencia múltiple  | Solo 1 (extends)         | Varias (implements)    |
 * | Puede tener campos | SÍ (con estado)          | Solo constantes        |
 * | Puede tener código | SÍ (métodos concretos)   | Solo default methods   |
 *
 * Concepto clave: HERENCIA y ABSTRACCIÓN.
 * ============================================================
 */
public abstract class Persona {

    // =========================================================
    // ENCAPSULAMIENTO: Los campos son privados (private).
    // Solo se acceden desde afuera mediante getters y setters.
    // Esto protege la integridad del objeto.
    // =========================================================
    private String id;       // Identificador único (ej: "E001", "P001")
    private String nombre;   // Nombre completo de la persona
    private int edad;        // Edad en años

    /**
     * Constructor de la clase abstracta Persona.
     *
     * Aunque Persona es abstracta, su constructor SÍ existe.
     * Las subclases lo invocan con super(...) para inicializar
     * los campos heredados. Esto evita duplicar código.
     *
     * @param id     Identificador único de la persona (no puede ser nulo o vacío)
     * @param nombre Nombre completo
     * @param edad   Edad en años (debe ser positiva)
     */
    public Persona(String id, String nombre, int edad) {
        // Validación básica: si el nombre es nulo o vacío, asignamos un valor por defecto.
        // En un sistema real se lanzaría una excepción (IllegalArgumentException).
        this.id = (id != null && !id.isEmpty()) ? id : "SIN_ID";
        this.nombre = (nombre != null && !nombre.isEmpty()) ? nombre : "Sin nombre";
        this.edad = (edad > 0) ? edad : 0;
    }

    // =========================================================
    // MÉTODO ABSTRACTO: mostrarRol()
    //
    // Un método abstracto es un método SIN CUERPO (sin {}).
    // Obliga a que CADA subclase escriba su propia versión.
    // Esto garantiza que todo objeto de tipo Persona (Estudiante
    // o Profesor) siempre pueda decirnos cuál es su rol.
    //
    // Concepto: POLIMORFISMO por sobrescritura (Override).
    // =========================================================

    /**
     * Muestra el rol específico de la persona en la universidad.
     * Cada subclase implementa este método con su información propia.
     */
    public abstract void mostrarRol();

    // =========================================================
    // GETTERS Y SETTERS (Encapsulamiento)
    // Proporcionan acceso controlado a los campos privados.
    // Un setter puede incluir validación antes de asignar el valor.
    // =========================================================

    /** @return El identificador único de la persona */
    public String getId() {
        return id;
    }

    /** @return El nombre completo de la persona */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre, validando que no sea nulo ni vacío.
     * @param nombre El nuevo nombre a asignar
     */
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre;
        }
    }

    /** @return La edad de la persona */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece una nueva edad, validando que sea positiva.
     * @param edad La nueva edad (debe ser > 0)
     */
    public void setEdad(int edad) {
        if (edad > 0) {
            this.edad = edad;
        }
    }

    /**
     * Sobrescritura del método toString() heredado de Object.
     *
     * ¿Qué es Object? En Java, TODAS las clases heredan implícitamente
     * de Object. Object tiene métodos como toString(), equals(), hashCode().
     *
     * Al sobrescribir toString(), controlamos cómo se representa
     * el objeto cuando se hace System.out.println(persona).
     *
     * @return Representación en texto de la persona con sus datos básicos.
     */
    @Override
    public String toString() {
        // String.format permite construir strings con formato de forma legible.
        return String.format("[ID: %s] %s (Edad: %d)", id, nombre, edad);
    }
}

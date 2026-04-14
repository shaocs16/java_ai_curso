package unimanager.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * CLASE: Curso
 * ============================================================
 *
 * Representa un curso universitario. No hereda de Persona porque
 * un Curso NO ES una Persona. La herencia debe usarse solo cuando
 * existe una relación "ES UN/A" (is-a), no "TIENE UN/A" (has-a).
 *
 * Ejemplos correctos:
 *   ✓ Estudiante ES UNA Persona    → herencia correcta
 *   ✓ Profesor   ES UNA Persona    → herencia correcta
 *   ✗ Curso      ES UNA Persona    → NO tiene sentido → no hereda
 *
 * Esta clase demuestra:
 *
 * 1. SOBRECARGA DE CONSTRUCTORES (Overloading):
 *    Dos constructores con diferente firma (diferente número de parámetros).
 *    Java los diferencia por el número y tipo de argumentos en la llamada.
 *
 *    DIFERENCIA con Sobrescritura (Overriding):
 *    - Sobrecarga (Overloading): MISMO nombre, distintos parámetros, en la MISMA clase.
 *    - Sobrescritura (Overriding): MISMO nombre, mismos parámetros, en DISTINTA clase (herencia).
 *
 * 2. Uso de ArrayList para gestionar dinámicamente los estudiantes del curso.
 *
 * 3. Encapsulamiento clásico con campos private y getters.
 * ============================================================
 */
public class Curso {

    private String codigo;       // Código único del curso (ej: "CS101")
    private String nombre;       // Nombre completo del curso
    private int capacidadMaxima; // Número máximo de estudiantes permitidos
    private String descripcion;  // Descripción opcional del curso

    /**
     * ArrayList con los IDs de los estudiantes inscritos en este curso.
     *
     * Usamos ArrayList porque:
     * - Los estudiantes pueden inscribirse y darse de baja en cualquier momento.
     * - El número de inscritos varía entre 0 y capacidadMaxima.
     * - ArrayList ofrece add(), remove(), size(), contains() fácilmente.
     */
    private List<String> estudiantesInscritos;

    // =========================================================
    // SOBRECARGA DE CONSTRUCTORES
    // =========================================================

    /**
     * Constructor BÁSICO — sin descripción.
     *
     * ¿Cuándo se usa?
     * Cuando la descripción no es crítica al crear el curso.
     *
     * @param codigo          Código único del curso (ej: "MAT101")
     * @param nombre          Nombre del curso (ej: "Cálculo I")
     * @param capacidadMaxima Límite máximo de estudiantes
     */
    public Curso(String codigo, String nombre, int capacidadMaxima) {
        // Llamamos al otro constructor usando this(...), pasando una descripción por defecto.
        // Esto evita duplicar código de inicialización y asegura consistencia.
        // this(...) es llamada de constructor delegado (constructor chaining).
        this(codigo, nombre, capacidadMaxima, "Sin descripción");
    }

    /**
     * Constructor COMPLETO — con descripción.
     *
     * Este es el constructor "principal" que hace la inicialización real.
     * El constructor básico lo llama vía this(...).
     *
     * ¿Por qué tener dos constructores?
     * Para dar FLEXIBILIDAD al código que crea los cursos:
     *   - new Curso("MAT101", "Cálculo I", 30)
     *   - new Curso("MAT101", "Cálculo I", 30, "Fundamentos del cálculo diferencial")
     * Ambas son válidas. Esto es SOBRECARGA.
     *
     * @param codigo          Código único del curso
     * @param nombre          Nombre del curso
     * @param capacidadMaxima Límite máximo de estudiantes
     * @param descripcion     Descripción del contenido del curso
     */
    public Curso(String codigo, String nombre, int capacidadMaxima, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidadMaxima = (capacidadMaxima > 0) ? capacidadMaxima : 1;
        this.descripcion = descripcion;
        this.estudiantesInscritos = new ArrayList<>();
    }

    // =========================================================
    // MÉTODOS DE NEGOCIO
    // =========================================================

    /**
     * Intenta inscribir a un estudiante (por ID) en el curso.
     *
     * Este método demuestra múltiples validaciones de negocio:
     * 1. ¿Hay cupo disponible? (comparamos ArrayList.size() vs capacidadMaxima)
     * 2. ¿Ya está inscrito? (ArrayList.contains())
     *
     * @param idEstudiante ID del estudiante a inscribir
     * @return true si la inscripción fue exitosa
     */
    public boolean inscribirEstudiante(String idEstudiante) {
        if (estudiantesInscritos.size() >= capacidadMaxima) {
            System.out.println("  AVISO: El curso '" + nombre + "' está lleno (" + capacidadMaxima + "/" + capacidadMaxima + ")");
            return false;
        }
        if (estudiantesInscritos.contains(idEstudiante)) {
            System.out.println("  AVISO: El estudiante " + idEstudiante + " ya está inscrito en '" + nombre + "'");
            return false;
        }
        estudiantesInscritos.add(idEstudiante); // ArrayList.add() agrega al final. Complejidad O(1) amortizado.
        return true;
    }

    /**
     * Da de baja a un estudiante del curso.
     *
     * ArrayList.remove(Object) busca y elimina la PRIMERA ocurrencia.
     * Retorna true si el elemento fue encontrado y eliminado, false si no existía.
     *
     * @param idEstudiante ID del estudiante a dar de baja
     * @return true si fue dado de baja exitosamente
     */
    public boolean darDeBajaEstudiante(String idEstudiante) {
        return estudiantesInscritos.remove(idEstudiante);
    }

    /**
     * Verifica si hay cupo disponible en el curso.
     *
     * @return true si la cantidad de inscritos es menor a la capacidad máxima
     */
    public boolean tieneCupo() {
        return estudiantesInscritos.size() < capacidadMaxima;
    }

    /**
     * Muestra la información detallada del curso con sus estudiantes.
     */
    public void mostrarDetalle() {
        System.out.println("  ┌─────────────────────────────────────");
        System.out.printf("  │ [%s] %s%n", codigo, nombre);
        System.out.printf("  │ Capacidad: %d/%d | Cupos libres: %d%n",
                estudiantesInscritos.size(), capacidadMaxima,
                capacidadMaxima - estudiantesInscritos.size());
        System.out.println("  │ Descripción: " + descripcion);

        if (!estudiantesInscritos.isEmpty()) {
            System.out.println("  │ Estudiantes inscritos:");
            // for-each sobre ArrayList: más limpio y legible cuando no necesitamos el índice
            for (String idEst : estudiantesInscritos) {
                System.out.println("  │   → " + idEst);
            }
        }
        System.out.println("  └─────────────────────────────────────");
    }

    // =========================================================
    // GETTERS
    // =========================================================

    /** @return El código único del curso */
    public String getCodigo() { return codigo; }

    /** @return El nombre del curso */
    public String getNombre() { return nombre; }

    /** @return La capacidad máxima del curso */
    public int getCapacidadMaxima() { return capacidadMaxima; }

    /** @return La descripción del curso */
    public String getDescripcion() { return descripcion; }

    /** @return El número actual de estudiantes inscritos */
    public int getCantidadInscritos() { return estudiantesInscritos.size(); }

    /** @return Lista de IDs de estudiantes inscritos */
    public List<String> getEstudiantesInscritos() { return estudiantesInscritos; }

    @Override
    public String toString() {
        return String.format("[%s] %-25s | Inscritos: %d/%d | Cupo: %s",
                codigo, nombre,
                estudiantesInscritos.size(), capacidadMaxima,
                tieneCupo() ? "Disponible" : "LLENO");
    }
}

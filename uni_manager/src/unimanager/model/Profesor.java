package unimanager.model;

import unimanager.interfaces.Reportable;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * CLASE CONCRETA: Profesor
 * ============================================================
 *
 * Demuestra los siguientes conceptos OOP:
 *
 * 1. HERENCIA (extends Persona):
 *    Igual que Estudiante, Profesor hereda de Persona.
 *    Esto nos permite tratar a Estudiantes y Profesores como
 *    "Personas" cuando sea necesario (polimorfismo).
 *
 * 2. IMPLEMENTACIÓN DE INTERFACE diferente (implements Reportable):
 *    A diferencia del Estudiante (que implementa Evaluable),
 *    el Profesor implementa Reportable.
 *    Esto demuestra que:
 *    - Dos subclases de la misma clase padre pueden implementar
 *      INTERFACES DISTINTAS.
 *    - Las interfaces permiten "poder" comportamientos adicionales
 *      de forma flexible y selectiva.
 *
 * 3. Uso de String[] (array fijo) para materias principales
 *    y ArrayList<Estudiante> para sus estudiantes asignados.
 *
 * 4. POLIMORFISMO: Un Profesor ES UNA Persona Y ES UN Reportable.
 * ============================================================
 */
public class Profesor extends Persona implements Reportable {

    /** Departamento académico al que pertenece el profesor */
    private String departamento;

    /** Años de experiencia docente */
    private int aniosExperiencia;

    /**
     * Array fijo con las materias principales del profesor.
     *
     * ¿Por qué array aquí?
     * Un profesor tiene asignadas un número fijo de materias por
     * contrato (definido al crear el objeto). No cambia durante
     * el semestre. El array es la estructura correcta para datos
     * con tamaño conocido y estable.
     *
     * String[] = array de cadenas de texto (objetos String).
     */
    private String[] materiasPrincipales;

    /**
     * ArrayList dinámico con los IDs de los estudiantes asignados al profesor.
     *
     * Un profesor puede recibir o perder estudiantes en cualquier momento.
     * ArrayList maneja esto de forma eficiente y flexible.
     *
     * Nota: Guardamos los IDs (String) en lugar del objeto Estudiante completo
     * para evitar dependencias circulares entre clases del modelo.
     */
    private List<String> estudiantesAsignadosIds;

    /**
     * Constructor de Profesor.
     *
     * Recibe el array de materias directamente.
     * En Java, los arrays son objetos y se pasan por referencia.
     *
     * @param id                  Identificador único (ej: "P001")
     * @param nombre              Nombre completo del profesor
     * @param edad                Edad del profesor
     * @param departamento        Departamento al que pertenece
     * @param aniosExperiencia    Años de experiencia docente
     * @param materiasPrincipales Array con los nombres de las materias
     */
    public Profesor(String id, String nombre, int edad, String departamento,
                    int aniosExperiencia, String[] materiasPrincipales) {
        super(id, nombre, edad); // Delegamos a Persona la inicialización de campos comunes

        this.departamento = departamento;
        this.aniosExperiencia = (aniosExperiencia >= 0) ? aniosExperiencia : 0;

        // Guardamos una copia del array para evitar que código externo
        // pueda modificar el array original accidentalmente.
        // Arrays.copyOf crea un nuevo array con los mismos valores.
        if (materiasPrincipales != null) {
            this.materiasPrincipales = new String[materiasPrincipales.length];
            // System.arraycopy: método nativo de Java para copiar arrays, muy eficiente.
            System.arraycopy(materiasPrincipales, 0, this.materiasPrincipales, 0, materiasPrincipales.length);
        } else {
            this.materiasPrincipales = new String[0]; // Array vacío, nunca null
        }

        this.estudiantesAsignadosIds = new ArrayList<>();
    }

    // =========================================================
    // IMPLEMENTACIÓN DEL MÉTODO ABSTRACTO (de Persona)
    // =========================================================

    /**
     * Muestra el rol de PROFESOR con su información departamental.
     * Cada subclase de Persona tiene su propia implementación de mostrarRol().
     * Esto es polimorfismo en acción: mismo mensaje, comportamientos distintos.
     */
    @Override
    public void mostrarRol() {
        System.out.println("Rol: PROFESOR | Departamento: " + departamento +
                " | Experiencia: " + aniosExperiencia + " años");
    }

    // =========================================================
    // IMPLEMENTACIÓN DE LA INTERFACE Reportable
    // =========================================================

    /**
     * Genera un reporte completo del profesor en consola.
     *
     * Demuestra cómo recorrer:
     * 1. Un array con for-each (para materiasPrincipales).
     * 2. Un ArrayList con for-each (para estudiantesAsignadosIds).
     *
     * El for-each (enhanced for) es más legible cuando NO necesitas
     * el índice. Compara:
     *   for (int i=0; i<arr.length; i++) → cuando necesitas el índice
     *   for (String m : arr)             → cuando solo necesitas el valor
     */
    @Override
    public void generarReporte() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     REPORTE DE PROFESOR                  ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ " + toString());
        System.out.println("╠══════════════════════════════════════════╣");

        // Recorremos el ARRAY con for-each (no necesitamos el índice)
        System.out.println("║ Materias que imparte:");
        if (materiasPrincipales.length == 0) {
            System.out.println("║   (Sin materias asignadas)");
        } else {
            for (String materia : materiasPrincipales) {
                System.out.println("║   • " + materia);
            }
        }

        // Recorremos el ARRAYLIST con for-each
        System.out.println("║ Estudiantes asignados (" + estudiantesAsignadosIds.size() + "):");
        if (estudiantesAsignadosIds.isEmpty()) {
            // ArrayList.isEmpty() es más legible que .size() == 0
            System.out.println("║   (Sin estudiantes asignados)");
        } else {
            for (String idEst : estudiantesAsignadosIds) {
                System.out.println("║   → ID: " + idEst);
            }
        }
        System.out.println("╚══════════════════════════════════════════╝");
    }

    // =========================================================
    // MÉTODOS DE NEGOCIO
    // =========================================================

    /**
     * Asigna un estudiante (por su ID) a este profesor.
     * Evita duplicados usando ArrayList.contains().
     *
     * @param idEstudiante ID del estudiante a asignar
     * @return true si fue asignado, false si ya estaba asignado
     */
    public boolean asignarEstudiante(String idEstudiante) {
        if (estudiantesAsignadosIds.contains(idEstudiante)) {
            return false;
        }
        estudiantesAsignadosIds.add(idEstudiante);
        return true;
    }

    /**
     * Muestra todas las materias que imparte el profesor.
     * Demuestra recorrido de array con for clásico para mostrar el índice.
     */
    public void mostrarMaterias() {
        System.out.println("  Materias de " + getNombre() + ":");
        if (materiasPrincipales.length == 0) {
            System.out.println("    (Sin materias asignadas)");
            return;
        }
        // for clásico: útil cuando necesitamos el índice (para numerar)
        for (int i = 0; i < materiasPrincipales.length; i++) {
            System.out.printf("    %d. %s%n", (i + 1), materiasPrincipales[i]);
        }
    }

    // =========================================================
    // GETTERS
    // =========================================================

    /** @return El departamento del profesor */
    public String getDepartamento() {
        return departamento;
    }

    /** @return Los años de experiencia del profesor */
    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    /** @return Copia del array de materias (para proteger el original) */
    public String[] getMateriasPrincipales() {
        // Devolvemos una copia para evitar que el código externo modifique el array interno.
        // Esto es una práctica de ENCAPSULAMIENTO defensivo.
        String[] copia = new String[materiasPrincipales.length];
        System.arraycopy(materiasPrincipales, 0, copia, 0, materiasPrincipales.length);
        return copia;
    }

    /** @return Lista de IDs de estudiantes asignados */
    public List<String> getEstudiantesAsignadosIds() {
        return estudiantesAsignadosIds;
    }

    /**
     * Sobrescritura de toString() para mostrar información del profesor.
     * Utiliza super.toString() para incluir los datos de Persona.
     */
    @Override
    public String toString() {
        return String.format("%s | Dpto: %-15s | Exp: %d años | Materias: %d",
                super.toString(),
                departamento,
                aniosExperiencia,
                materiasPrincipales.length);
    }
}

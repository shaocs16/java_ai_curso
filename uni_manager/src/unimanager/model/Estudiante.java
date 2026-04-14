package unimanager.model;

import unimanager.interfaces.Evaluable;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * CLASE CONCRETA: Estudiante
 * ============================================================
 *
 * Demuestra los siguientes conceptos OOP:
 *
 * 1. HERENCIA (extends Persona):
 *    Estudiante hereda TODOS los campos y métodos de Persona
 *    (id, nombre, edad, toString, etc.) sin tener que reescribirlos.
 *    Esto es la reutilización de código en acción.
 *
 * 2. IMPLEMENTACIÓN DE INTERFACE (implements Evaluable):
 *    Estudiante firma el contrato Evaluable, por lo que ESTÁ
 *    OBLIGADO a implementar calcularPromedio().
 *
 * 3. ARRAY vs ARRAYLIST:
 *    - double[] notasCurso: Array de tamaño FIJO (max 10 notas por curso).
 *      Usamos array porque el reglamento establece un máximo de notas
 *      definido de antemano. Ideal cuando el tamaño es conocido.
 *    - ArrayList<String> cursoInscritos: Lista de tamaño DINÁMICO.
 *      Un estudiante puede inscribirse en más o menos cursos durante
 *      su carrera. No sabemos cuántos serán. Ideal para colecciones
 *      que crecen o reducen en tiempo de ejecución.
 *
 * 4. POLIMORFISMO:
 *    Un Estudiante ES UNA Persona (herencia).
 *    Un Estudiante ES UN Evaluable (interface).
 *    Por eso puede usarse en listas de tipo Persona[] o Evaluable.
 * ============================================================
 */
public class Estudiante extends Persona implements Evaluable {

    // =========================================================
    // CAMPOS ESPECÍFICOS DEL ESTUDIANTE
    // =========================================================

    /** Carrera universitaria en la que está inscrito */
    private String carrera;

    /**
     * ARRAY de tamaño fijo para las notas del semestre actual.
     *
     * ¿Por qué un array y no un ArrayList?
     * - Un array es más eficiente en memoria cuando el tamaño
     *   es conocido y no va a cambiar.
     * - El reglamento universitario limita a MAX_NOTAS evaluaciones
     *   por período. El array enforce este límite naturalmente.
     * - Los arrays son fundamentales en programación y se usan
     *   cuando el tamaño es fijo y conocido.
     *
     * La diferencia técnica:
     *   - Array: int[] arr = new int[5];  → tamaño fijo, acceso O(1)
     *   - ArrayList: ArrayList<Integer>  → tamaño dinámico, más métodos
     */
    private double[] notasSemestre;

    /**
     * Constante que define el número máximo de evaluaciones permitidas.
     *
     * ¿Por qué static final?
     * - static: Pertenece a la CLASE, no a cada instancia. Todos los
     *   estudiantes comparten el mismo límite.
     * - final: No puede cambiar una vez asignado (es una constante).
     * - Por convención, las constantes se escriben en MAYÚSCULAS_CON_GUIONES.
     */
    private static final int MAX_NOTAS = 10;

    /** Cuántas notas se han registrado actualmente en el array */
    private int cantidadNotas;

    /**
     * ARRAYLIST dinámico para los cursos inscritos.
     *
     * ¿Por qué ArrayList aquí?
     * - Un estudiante puede inscribirse en cualquier número de cursos.
     * - Puede agregar o dar de baja cursos durante el semestre.
     * - ArrayList permite add(), remove(), contains(), size(), etc.
     *   de forma flexible.
     *
     * List<String> vs ArrayList<String>:
     * - Declaramos el tipo de la variable como List (la interface),
     *   no como ArrayList (la clase concreta). Esto es buena práctica
     *   (programar hacia abstracciones, no implementaciones).
     */
    private List<String> cursosInscritos;

    /**
     * Constructor de Estudiante.
     * Llama con super() al constructor de Persona para inicializar
     * los campos heredados.
     *
     * @param id      Identificador único (ej: "E001")
     * @param nombre  Nombre completo del estudiante
     * @param edad    Edad del estudiante
     * @param carrera Nombre de la carrera universitaria
     */
    public Estudiante(String id, String nombre, int edad, String carrera) {
        // super(...) SIEMPRE debe ser la primera línea en un constructor
        // de subclase. Delega la inicialización de los campos de Persona
        // al constructor de la clase padre.
        super(id, nombre, edad);

        this.carrera = carrera;

        // Inicializamos el array con tamaño MAX_NOTAS.
        // Todos los elementos empiezan en 0.0 por defecto.
        this.notasSemestre = new double[MAX_NOTAS];

        // Ninguna nota ha sido registrada aún.
        this.cantidadNotas = 0;

        // Inicializamos el ArrayList vacío. Capacidad inicial por defecto: 10.
        this.cursosInscritos = new ArrayList<>();
    }

    // =========================================================
    // IMPLEMENTACIÓN DEL MÉTODO ABSTRACTO (de Persona)
    // La anotación @Override confirma que estamos sobrescribiendo,
    // el compilador lanzará error si el método no existe en el padre.
    // =========================================================

    /**
     * Implementación del método abstracto de Persona.
     * Muestra el rol específico de este objeto: "ESTUDIANTE".
     */
    @Override
    public void mostrarRol() {
        System.out.println("Rol: ESTUDIANTE | Carrera: " + carrera);
    }

    // =========================================================
    // IMPLEMENTACIÓN DE LA INTERFACE Evaluable
    // =========================================================

    /**
     * Calcula el promedio de las notas registradas en el array.
     *
     * Algoritmo:
     * 1. Si no hay notas, retorna 0.0 (evita división por cero).
     * 2. Suma todos los elementos del array hasta cantidadNotas.
     * 3. Divide entre la cantidad de notas para obtener el promedio.
     *
     * ¿Por qué iterar hasta cantidadNotas y no hasta notasSemestre.length?
     * Porque el array tiene MAX_NOTAS espacios, pero quizás solo se han
     * registrado 3 notas. Los demás espacios son 0.0 y no deben contarse.
     *
     * @return Promedio de las notas (0.0 si no hay notas registradas)
     */
    @Override
    public double calcularPromedio() {
        if (cantidadNotas == 0) {
            return 0.0;
        }

        double suma = 0.0;
        // Recorremos el array con un for clásico (estilo C), conociendo el índice.
        // Esto es diferente al for-each, que usaremos más adelante para ArrayLists.
        for (int i = 0; i < cantidadNotas; i++) {
            suma += notasSemestre[i];
        }

        return suma / cantidadNotas;
    }

    // =========================================================
    // MÉTODOS DE NEGOCIO
    // =========================================================

    /**
     * Agrega una nota al array del semestre actual.
     *
     * @param nota La calificación a registrar (entre 0.0 y 10.0)
     * @return true si se agregó correctamente, false si el array está lleno
     *         o la nota está fuera de rango.
     */
    public boolean agregarNota(double nota) {
        // Validación de rango de la nota.
        if (nota < 0.0 || nota > 10.0) {
            System.out.println("  ERROR: La nota debe estar entre 0.0 y 10.0");
            return false;
        }

        // Validación de capacidad del array.
        if (cantidadNotas >= MAX_NOTAS) {
            System.out.println("  ERROR: Ya se alcanzó el máximo de " + MAX_NOTAS + " evaluaciones.");
            return false;
        }

        // Asignamos la nota en la posición actual y avanzamos el contador.
        notasSemestre[cantidadNotas] = nota;
        cantidadNotas++;
        return true;
    }

    /**
     * Inscribe al estudiante en un curso, si no ya está inscrito.
     *
     * Usamos ArrayList.contains() para verificar duplicados.
     * Este método es O(n) — revisa cada elemento hasta encontrarlo.
     *
     * @param nombreCurso Nombre del curso a inscribir
     * @return true si la inscripción fue exitosa, false si ya estaba inscrito
     */
    public boolean inscribirEnCurso(String nombreCurso) {
        // ArrayList.contains() revisa si el elemento ya existe en la lista.
        if (cursosInscritos.contains(nombreCurso)) {
            System.out.println("  AVISO: " + getNombre() + " ya está inscrito en " + nombreCurso);
            return false;
        }
        cursosInscritos.add(nombreCurso); // ArrayList.add() agrega al final de la lista.
        return true;
    }

    /**
     * Muestra todas las notas registradas en el array con formato de tabla.
     * Demuestra cómo recorrer un array con for clásico.
     */
    public void mostrarNotas() {
        if (cantidadNotas == 0) {
            System.out.println("  No hay notas registradas.");
            return;
        }
        System.out.println("  Notas registradas (" + cantidadNotas + "/" + MAX_NOTAS + "):");
        for (int i = 0; i < cantidadNotas; i++) {
            // El +1 es para mostrar "Nota 1", "Nota 2" en lugar de "Nota 0".
            System.out.printf("    Nota %d: %.1f%n", (i + 1), notasSemestre[i]);
        }
        System.out.printf("  Promedio: %.2f | Estado: %s%n",
                calcularPromedio(),
                estaAprobado() ? "APROBADO ✓" : "REPROBADO ✗");
    }

    // =========================================================
    // GETTERS
    // =========================================================

    /** @return El nombre de la carrera del estudiante */
    public String getCarrera() {
        return carrera;
    }

    /** @return La lista de cursos inscritos (List, no ArrayList, por buena práctica) */
    public List<String> getCursosInscritos() {
        return cursosInscritos;
    }

    /** @return Cuántas notas se han registrado actualmente */
    public int getCantidadNotas() {
        return cantidadNotas;
    }

    /**
     * Sobrescritura de toString() para mostrar información completa del estudiante.
     * Llama al toString() del padre con super.toString() para reutilizar esa parte.
     */
    @Override
    public String toString() {
        return String.format("%s | Carrera: %-20s | Cursos: %d | Promedio: %.2f",
                super.toString(),         // Reutiliza el toString de Persona
                carrera,
                cursosInscritos.size(),   // ArrayList.size() devuelve la cantidad de elementos
                calcularPromedio());
    }
}

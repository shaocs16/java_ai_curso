package unimanager.service;

import unimanager.interfaces.Evaluable;
import unimanager.interfaces.Reportable;
import unimanager.model.Curso;
import unimanager.model.Estudiante;
import unimanager.model.Persona;
import unimanager.model.Profesor;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * CLASE DE SERVICIO: Universidad
 * ============================================================
 *
 * Esta clase es el NÚCLEO del sistema. Actúa como repositorio
 * central de datos (en memoria) y como motor de lógica de negocio.
 *
 * Conceptos OOP demostrados aquí:
 *
 * 1. POLIMORFISMO con listas:
 *    - Usamos List<Persona> personas para almacenar TANTO Estudiantes
 *      COMO Profesores en la MISMA lista.
 *    - Esto es posible porque ambos heredan de Persona.
 *    - Cuando queremos comportamiento específico, usamos instanceof
 *      y casting para "bajar" al tipo concreto.
 *
 * 2. CASTING (conversión de tipos):
 *    - (Estudiante) persona → downcast explícito
 *    - Necesario cuando queremos acceder a métodos específicos
 *      de Estudiante que no están en Persona.
 *
 * 3. instanceof:
 *    - Operador que verifica el tipo real de un objeto en tiempo de ejecución.
 *    - Siempre verifica ANTES de hacer un cast para evitar ClassCastException.
 *
 * 4. Separación de responsabilidades:
 *    - Universidad maneja los datos y la lógica.
 *    - MenuService maneja la interacción con el usuario (Scanner).
 *    - Esto es el patrón de diseño simplificado MVC (Model-View-Controller).
 * ============================================================
 */
public class Universidad {

    /** Nombre de la institución universitaria */
    private String nombre;

    /**
     * Lista polimórfica de TODAS las personas de la universidad.
     *
     * ¿Por qué List<Persona> y no List<Estudiante> + List<Profesor> separadas?
     * Porque en muchas operaciones queremos tratar a todos por igual:
     * buscar por ID, listar todos, contar, etc.
     * Cuando necesitamos comportamiento específico, filtramos con instanceof.
     *
     * Ejemplo: buscarPersonaPorId() funciona para cualquier tipo de persona.
     */
    private List<Persona> personas;

    /**
     * Lista específica de cursos.
     *
     * Curso NO hereda de Persona, por eso tiene su propia lista.
     * En un sistema real, cada entidad tendría su propio repositorio.
     */
    private List<Curso> cursos;

    /**
     * Contadores para generar IDs únicos auto-incrementados.
     * Simula el comportamiento de un AUTO_INCREMENT de base de datos.
     * Son static para que sean compartidos entre todas las instancias.
     */
    private static int contadorEstudiantes = 1;
    private static int contadorProfesores  = 1;
    private static int contadorCursos      = 1;

    /**
     * Constructor de Universidad.
     *
     * @param nombre Nombre de la institución
     */
    public Universidad(String nombre) {
        this.nombre = nombre;
        this.personas = new ArrayList<>();
        this.cursos   = new ArrayList<>();
    }

    // =========================================================
    // MÉTODOS DE GESTIÓN DE ESTUDIANTES
    // =========================================================

    /**
     * Crea y agrega un nuevo Estudiante a la lista de personas.
     *
     * Genera un ID único automáticamente (ej: "E001", "E002").
     * String.format con "%03d" formatea el número con 3 dígitos con
     * ceros a la izquierda: 1 → "001", 12 → "012".
     *
     * @param nombre  Nombre del estudiante
     * @param edad    Edad del estudiante
     * @param carrera Carrera universitaria
     * @return El objeto Estudiante recién creado y agregado
     */
    public Estudiante agregarEstudiante(String nombre, int edad, String carrera) {
        String id = String.format("E%03d", contadorEstudiantes++);
        Estudiante e = new Estudiante(id, nombre, edad, carrera);
        personas.add(e); // Agrega un Estudiante a una List<Persona> — POLIMORFISMO
        System.out.println("  ✓ Estudiante agregado con ID: " + id);
        return e;
    }

    /**
     * Obtiene una lista filtrada SOLO de Estudiantes.
     *
     * Demuestra cómo filtrar una lista polimórfica usando instanceof.
     *
     * ¿Qué es instanceof?
     * - Verifica si un objeto es instancia de una clase o subclase.
     * - Ejemplo: if (p instanceof Estudiante) → true si p es Estudiante
     *   aunque la variable sea de tipo Persona.
     *
     * @return Nueva lista conteniendo solo los objetos de tipo Estudiante
     */
    public List<Estudiante> obtenerEstudiantes() {
        List<Estudiante> resultado = new ArrayList<>();
        for (Persona p : personas) {
            // instanceof verifica el tipo REAL del objeto en tiempo de ejecución
            if (p instanceof Estudiante) {
                // CASTING: "bajamos" de Persona a Estudiante (downcast)
                // Solo es seguro hacerlo DESPUÉS de verificar con instanceof
                resultado.add((Estudiante) p);
            }
        }
        return resultado;
    }

    /**
     * Agrega una nota a un estudiante identificado por su ID.
     *
     * Combina búsqueda polimórfica + instanceof + casting + lógica de negocio.
     *
     * @param idEstudiante ID del estudiante
     * @param nota         Calificación a registrar
     * @return true si la nota fue agregada exitosamente
     */
    public boolean agregarNotaAEstudiante(String idEstudiante, double nota) {
        Persona p = buscarPersonaPorId(idEstudiante);
        if (p == null) {
            System.out.println("  ERROR: No se encontró ninguna persona con ID: " + idEstudiante);
            return false;
        }
        if (!(p instanceof Estudiante)) {
            System.out.println("  ERROR: La persona con ID " + idEstudiante + " no es un Estudiante.");
            return false;
        }
        // Cast seguro (ya verificamos con instanceof)
        Estudiante est = (Estudiante) p;
        return est.agregarNota(nota);
    }

    // =========================================================
    // MÉTODOS DE GESTIÓN DE PROFESORES
    // =========================================================

    /**
     * Crea y agrega un nuevo Profesor a la lista de personas.
     *
     * @param nombre           Nombre del profesor
     * @param edad             Edad del profesor
     * @param departamento     Departamento académico
     * @param aniosExp         Años de experiencia
     * @param materias         Array de materias que imparte
     * @return El objeto Profesor recién creado
     */
    public Profesor agregarProfesor(String nombre, int edad, String departamento,
                                    int aniosExp, String[] materias) {
        String id = String.format("P%03d", contadorProfesores++);
        Profesor prof = new Profesor(id, nombre, edad, departamento, aniosExp, materias);
        personas.add(prof); // Un Profesor (subclase) entra en List<Persona> (superclase)
        System.out.println("  ✓ Profesor agregado con ID: " + id);
        return prof;
    }

    /**
     * Obtiene una lista filtrada SOLO de Profesores.
     * Mismo patrón que obtenerEstudiantes() usando instanceof.
     *
     * @return Nueva lista conteniendo solo los objetos de tipo Profesor
     */
    public List<Profesor> obtenerProfesores() {
        List<Profesor> resultado = new ArrayList<>();
        for (Persona p : personas) {
            if (p instanceof Profesor) {
                resultado.add((Profesor) p);
            }
        }
        return resultado;
    }

    // =========================================================
    // MÉTODOS DE GESTIÓN DE CURSOS
    // =========================================================

    /**
     * Crea y agrega un nuevo Curso.
     * Usa el constructor con descripción (constructor completo de Curso).
     *
     * @param nombre      Nombre del curso
     * @param capacidad   Capacidad máxima de estudiantes
     * @param descripcion Descripción del curso
     * @return El objeto Curso recién creado
     */
    public Curso agregarCurso(String nombre, int capacidad, String descripcion) {
        String codigo = String.format("CUR%03d", contadorCursos++);
        Curso c;
        if (descripcion != null && !descripcion.isEmpty()) {
            // Usa el constructor COMPLETO (con descripción) — sobrecarga de constructores
            c = new Curso(codigo, nombre, capacidad, descripcion);
        } else {
            // Usa el constructor BÁSICO (sin descripción) — sobrecarga de constructores
            c = new Curso(codigo, nombre, capacidad);
        }
        cursos.add(c);
        System.out.println("  ✓ Curso agregado con código: " + codigo);
        return c;
    }

    /**
     * Inscribe a un estudiante en un curso.
     *
     * Este método coordina la actualización de AMBOS lados de la relación:
     * - El Curso sabe qué estudiantes tiene.
     * - El Estudiante sabe en qué cursos está.
     *
     * @param idEstudiante ID del estudiante a inscribir
     * @param codigoCurso  Código del curso destino
     * @return true si la inscripción fue exitosa en ambos lados
     */
    public boolean inscribirEstudianteEnCurso(String idEstudiante, String codigoCurso) {
        Persona p = buscarPersonaPorId(idEstudiante);
        Curso c = buscarCursoPorCodigo(codigoCurso);

        if (p == null || !(p instanceof Estudiante)) {
            System.out.println("  ERROR: Estudiante con ID '" + idEstudiante + "' no encontrado.");
            return false;
        }
        if (c == null) {
            System.out.println("  ERROR: Curso con código '" + codigoCurso + "' no encontrado.");
            return false;
        }

        Estudiante est = (Estudiante) p;

        // Inscribir en ambos lados de la relación.
        boolean inscritoEnCurso = c.inscribirEstudiante(idEstudiante);
        boolean inscritoEnEst   = est.inscribirEnCurso(c.getNombre());

        return inscritoEnCurso && inscritoEnEst;
    }

    // =========================================================
    // BÚSQUEDAS — Demuestran recorrido polimórfico de listas
    // =========================================================

    /**
     * Busca una Persona por su ID en la lista polimórfica.
     *
     * Recorre la MISMA lista donde conviven Estudiantes y Profesores.
     * Retorna un Persona (tipo padre), el llamador decide si hace cast.
     *
     * @param id ID a buscar
     * @return El objeto Persona encontrado, o null si no existe.
     *         Usar null como "no encontrado" es un patrón clásico en Java.
     */
    public Persona buscarPersonaPorId(String id) {
        for (Persona p : personas) {
            // String.equalsIgnoreCase(): comparación de strings IGNORANDO mayúsculas.
            // NUNCA usar == para comparar Strings (compara referencias, no contenido).
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null; // Convención: null indica "no encontrado"
    }

    /**
     * Busca un Curso por su código.
     *
     * @param codigo Código del curso (ej: "CUR001")
     * @return El Curso encontrado, o null si no existe.
     */
    public Curso buscarCursoPorCodigo(String codigo) {
        for (Curso c : cursos) {
            if (c.getCodigo().equalsIgnoreCase(codigo)) {
                return c;
            }
        }
        return null;
    }

    // =========================================================
    // REPORTES — Demuestran uso de interfaces como tipos
    // =========================================================

    /**
     * Genera el reporte general del sistema.
     *
     * Demuestra polimorfismo con INTERFACES:
     * - Llama a generarReporte() en todos los objetos Reportable.
     * - instanceof verifica si p implementa la interface Reportable.
     *
     * Nota: Una interface puede usarse como tipo en instanceof igual que una clase.
     */
    public void generarReporteGeneral() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║         REPORTE GENERAL — " + nombre);
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║ Total personas: " + personas.size());
        System.out.println("║ Total cursos:   " + cursos.size());
        System.out.println("╠══════════════════════════════════════════════════╣");

        // Usamos la INTERFACE Reportable para llamar generarReporte()
        // Solo los objetos que implementen Reportable entrarán aquí.
        for (Persona p : personas) {
            if (p instanceof Reportable) {
                ((Reportable) p).generarReporte(); // Cast a la interface, luego llamamos el método
            }
        }
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    /**
     * Muestra el top de estudiantes ordenado por promedio (burbuja simple).
     *
     * ALGORITMO DE ORDENAMIENTO: Burbuja (Bubble Sort).
     * No es el más eficiente (O(n²)), pero es el más didáctico para
     * entender cómo funciona el ordenamiento.
     *
     * En Java real se usaría Collections.sort() o List.sort() con
     * un Comparator, pero aquí implementamos el algoritmo manualmente
     * para aprender los fundamentos.
     */
    public void mostrarTopEstudiantes() {
        List<Estudiante> estudiantes = obtenerEstudiantes();

        if (estudiantes.isEmpty()) {
            System.out.println("  No hay estudiantes registrados.");
            return;
        }

        // Convertimos ArrayList a array para aplicar burbuja clásico.
        // Esto demuestra conversión entre List y Array.
        Estudiante[] arr = estudiantes.toArray(new Estudiante[0]);

        // ORDENAMIENTO BURBUJA — O(n²)
        // Cada pasada "burbujea" el mayor hacia el final.
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // Comparamos promedios usando el método de la interface Evaluable
                if (arr[j].calcularPromedio() < arr[j + 1].calcularPromedio()) {
                    // Intercambio (swap) de posiciones
                    Estudiante temp = arr[j];
                    arr[j]     = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("\n  🏆 TOP ESTUDIANTES POR PROMEDIO:");
        System.out.println("  ─────────────────────────────────────────────────");
        for (int i = 0; i < arr.length; i++) {
            // Usamos la interface Evaluable: calcularPromedio() y estaAprobado()
            Evaluable ev = arr[i]; // Un Estudiante IS-AN Evaluable
            System.out.printf("  %d. %-20s | Promedio: %.2f | %s%n",
                    (i + 1),
                    arr[i].getNombre(),
                    ev.calcularPromedio(),
                    ev.estaAprobado() ? "✓ APROBADO" : "✗ REPROBADO");
        }
        System.out.println("  ─────────────────────────────────────────────────");
    }

    /**
     * Muestra todas las personas con su rol (polimorfismo via mostrarRol).
     * mostrarRol() es abstracto en Persona, cada subclase lo implementa distinto.
     */
    public void listarTodasLasPersonas() {
        if (personas.isEmpty()) {
            System.out.println("  No hay personas registradas.");
            return;
        }
        System.out.println("  Lista de todas las personas (Polimorfismo):");
        System.out.println("  ─────────────────────────────────────────────");
        for (Persona p : personas) {
            System.out.println("  " + p); // Llama a toString() — polimorfismo
            p.mostrarRol();               // Llama a mostrarRol() — polimorfismo
            System.out.println();
        }
    }

    // =========================================================
    // GETTERS
    // =========================================================

    /** @return Nombre de la universidad */
    public String getNombre() { return nombre; }

    /** @return Lista de todos los cursos */
    public List<Curso> getCursos() { return cursos; }

    /** @return Número total de personas registradas */
    public int getTotalPersonas() { return personas.size(); }
}

package unimanager.service;

import unimanager.model.Curso;
import unimanager.model.Estudiante;
import unimanager.model.Persona;
import unimanager.model.Profesor;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * ============================================================
 * CLASE DE SERVICIO: MenuService
 * ============================================================
 *
 * Gestiona toda la interacción con el usuario a través de la
 * consola usando Scanner.
 *
 * ¿Qué es Scanner?
 * Es una clase de Java (java.util.Scanner) que permite leer
 * datos de la entrada estándar (teclado), de un archivo, de
 * un String, etc.
 * - scanner.nextLine() → lee una línea de texto completa
 * - scanner.nextInt()  → lee un número entero
 * - scanner.nextDouble() → lee un número decimal
 *
 * IMPORTANTE sobre Scanner:
 * Después de leer un número con nextInt() o nextDouble(), queda
 * un salto de línea (\n) en el buffer. Hay que consumirlo con
 * scanner.nextLine() antes de leer el siguiente String.
 * Este es un error muy común en Java para principiantes.
 *
 * Principio de diseño aplicado:
 * Separamos la lógica de presentación (MenuService) de la
 * lógica de negocio (Universidad). Esto facilita el mantenimiento:
 * si mañana queremos una UI gráfica o web, solo reemplazamos
 * MenuService, no Universidad.
 * ============================================================
 */
public class MenuService {

    /**
     * El objeto Scanner que leerá del teclado.
     * System.in es el stream de entrada estándar (el teclado).
     *
     * Es un campo de instancia para reutilizarlo en todos los métodos
     * sin necesidad de crearlo repetidamente.
     */
    private Scanner scanner;

    /**
     * Referencia a la lógica de negocio.
     * MenuService TIENE UNA Universidad (composición, relación has-a).
     */
    private Universidad universidad;

    /**
     * Flag (bandera booleana) que controla el ciclo principal del menú.
     * Cuando el usuario elige "Salir", se pone en false y el programa termina.
     */
    private boolean ejecutando;

    /**
     * Constructor de MenuService.
     *
     * @param universidad La instancia de Universidad ya creada y configurada
     */
    public MenuService(Universidad universidad) {
        // new Scanner(System.in) crea un lector conectado al teclado
        this.scanner = new Scanner(System.in);
        this.universidad = universidad;
        this.ejecutando = true;
    }

    // =========================================================
    // MÉTODO PRINCIPAL: iniciar()
    // =========================================================

    /**
     * Punto de entrada del menú. Contiene el bucle principal.
     *
     * ¿Por qué un bucle while con flag y no un do-while?
     * El flag ejecutando permite que CUALQUIER sub-método pueda
     * terminar el programa de forma limpia, sin usar System.exit().
     */
    public void iniciar() {
        mostrarBienvenida();

        // Bucle principal: se ejecuta hasta que el usuario elija "Salir"
        while (ejecutando) {
            mostrarMenuPrincipal();
            int opcion = leerEntero("Selecciona una opción: ");
            procesarMenuPrincipal(opcion);
        }

        // Al salir, cerramos el Scanner para liberar recursos del sistema.
        // Buena práctica: siempre cerrar los recursos abiertos (Scanner, archivos, etc.)
        scanner.close();
        System.out.println("\n¡Hasta luego! Cerrando " + universidad.getNombre() + "...\n");
    }

    /**
     * Controla la navegación del menú principal según la opción elegida.
     * Ejemplifica el uso de switch como alternativa limpia a muchos if-else.
     *
     * @param opcion La opción seleccionada por el usuario
     */
    private void procesarMenuPrincipal(int opcion) {
        switch (opcion) {
            case 1:
                menuEstudiantes();
                break;
            case 2:
                menuProfesores();
                break;
            case 3:
                menuCursos();
                break;
            case 4:
                menuReportes();
                break;
            case 0:
                ejecutando = false; // Cambiamos el flag para terminar el while
                break;
            default:
                System.out.println("  Opción inválida. Intenta de nuevo.\n");
        }
    }

    // =========================================================
    // SUBMENÚ: ESTUDIANTES
    // =========================================================

    private void menuEstudiantes() {
        boolean enSubmenu = true;
        while (enSubmenu) {
            System.out.println("\n  ╔═══════════════════════════════╗");
            System.out.println("  ║    GESTIÓN DE ESTUDIANTES     ║");
            System.out.println("  ╠═══════════════════════════════╣");
            System.out.println("  ║  1. Agregar Estudiante        ║");
            System.out.println("  ║  2. Listar Estudiantes        ║");
            System.out.println("  ║  3. Buscar Estudiante por ID  ║");
            System.out.println("  ║  4. Agregar Nota              ║");
            System.out.println("  ║  5. Ver Notas y Promedio      ║");
            System.out.println("  ║  0. Volver al menú principal  ║");
            System.out.println("  ╚═══════════════════════════════╝");

            int opcion = leerEntero("  Opción: ");

            switch (opcion) {
                case 1: agregarEstudiante(); break;
                case 2: listarEstudiantes(); break;
                case 3: buscarPersona();     break;
                case 4: agregarNota();       break;
                case 5: verNotasEstudiante(); break;
                case 0: enSubmenu = false;   break;
                default: System.out.println("  Opción inválida.");
            }
        }
    }

    private void agregarEstudiante() {
        System.out.println("\n  --- AGREGAR ESTUDIANTE ---");
        String nombre = leerTexto("  Nombre completo: ");
        int edad      = leerEntero("  Edad: ");
        String carrera = leerTexto("  Carrera: ");
        universidad.agregarEstudiante(nombre, edad, carrera);
    }

    private void listarEstudiantes() {
        System.out.println("\n  --- LISTA DE ESTUDIANTES ---");
        List<Estudiante> estudiantes = universidad.obtenerEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("  No hay estudiantes registrados.");
            return;
        }
        // for-each sobre ArrayList<Estudiante>
        for (Estudiante e : estudiantes) {
            System.out.println("  " + e); // Llama a toString() de Estudiante
        }
    }

    private void buscarPersona() {
        System.out.println("\n  --- BUSCAR PERSONA ---");
        String id = leerTexto("  Ingresa el ID (ej: E001 o P001): ");
        Persona p = universidad.buscarPersonaPorId(id);
        if (p == null) {
            System.out.println("  No se encontró ninguna persona con ID: " + id);
        } else {
            System.out.println("  Encontrado: " + p);
            p.mostrarRol(); // Polimorfismo: el método adecuado se ejecuta según el tipo real
        }
    }

    private void agregarNota() {
        System.out.println("\n  --- AGREGAR NOTA A ESTUDIANTE ---");
        String id  = leerTexto("  ID del estudiante (ej: E001): ");
        double nota = leerDecimal("  Nota (0.0 - 10.0): ");
        universidad.agregarNotaAEstudiante(id, nota);
    }

    private void verNotasEstudiante() {
        System.out.println("\n  --- VER NOTAS DE ESTUDIANTE ---");
        String id = leerTexto("  ID del estudiante (ej: E001): ");
        Persona p = universidad.buscarPersonaPorId(id);

        if (p == null) {
            System.out.println("  No se encontró ninguna persona con ID: " + id);
            return;
        }
        if (!(p instanceof Estudiante)) {
            System.out.println("  La persona encontrada no es un Estudiante.");
            return;
        }
        Estudiante est = (Estudiante) p;
        System.out.println("  Estudiante: " + est.getNombre());
        est.mostrarNotas();
    }

    // =========================================================
    // SUBMENÚ: PROFESORES
    // =========================================================

    private void menuProfesores() {
        boolean enSubmenu = true;
        while (enSubmenu) {
            System.out.println("\n  ╔═══════════════════════════════╗");
            System.out.println("  ║    GESTIÓN DE PROFESORES      ║");
            System.out.println("  ╠═══════════════════════════════╣");
            System.out.println("  ║  1. Agregar Profesor          ║");
            System.out.println("  ║  2. Listar Profesores         ║");
            System.out.println("  ║  3. Ver reporte de un Profesor║");
            System.out.println("  ║  0. Volver al menú principal  ║");
            System.out.println("  ╚═══════════════════════════════╝");

            int opcion = leerEntero("  Opción: ");

            switch (opcion) {
                case 1: agregarProfesor();        break;
                case 2: listarProfesores();       break;
                case 3: verReporteProfesor();     break;
                case 0: enSubmenu = false;        break;
                default: System.out.println("  Opción inválida.");
            }
        }
    }

    private void agregarProfesor() {
        System.out.println("\n  --- AGREGAR PROFESOR ---");
        String nombre      = leerTexto("  Nombre completo: ");
        int edad           = leerEntero("  Edad: ");
        String departamento = leerTexto("  Departamento: ");
        int aniosExp       = leerEntero("  Años de experiencia: ");
        int numMaterias    = leerEntero("  ¿Cuántas materias imparte? (máx 5): ");

        // Validamos el rango
        if (numMaterias < 1 || numMaterias > 5) {
            System.out.println("  AVISO: Número fuera de rango. Se usará 1 materia.");
            numMaterias = 1;
        }

        // Creamos el ARRAY de materias con el tamaño exacto ingresado.
        // Esto demuestra la creación dinámica de un array con tamaño variable.
        String[] materias = new String[numMaterias];
        for (int i = 0; i < numMaterias; i++) {
            materias[i] = leerTexto("  Materia " + (i + 1) + ": ");
        }

        universidad.agregarProfesor(nombre, edad, departamento, aniosExp, materias);
    }

    private void listarProfesores() {
        System.out.println("\n  --- LISTA DE PROFESORES ---");
        List<Profesor> profesores = universidad.obtenerProfesores();
        if (profesores.isEmpty()) {
            System.out.println("  No hay profesores registrados.");
            return;
        }
        for (Profesor prof : profesores) {
            System.out.println("  " + prof);
        }
    }

    private void verReporteProfesor() {
        System.out.println("\n  --- REPORTE DE PROFESOR ---");
        String id = leerTexto("  ID del profesor (ej: P001): ");
        Persona p = universidad.buscarPersonaPorId(id);

        if (p == null) {
            System.out.println("  No se encontró ninguna persona con ID: " + id);
            return;
        }
        if (!(p instanceof Profesor)) {
            System.out.println("  La persona encontrada no es un Profesor.");
            return;
        }
        // Cast a Profesor para acceder a su método generarReporte() de la interface Reportable
        Profesor prof = (Profesor) p;
        prof.generarReporte(); // Método de la interface Reportable
    }

    // =========================================================
    // SUBMENÚ: CURSOS
    // =========================================================

    private void menuCursos() {
        boolean enSubmenu = true;
        while (enSubmenu) {
            System.out.println("\n  ╔═══════════════════════════════════╗");
            System.out.println("  ║       GESTIÓN DE CURSOS           ║");
            System.out.println("  ╠═══════════════════════════════════╣");
            System.out.println("  ║  1. Crear Curso                   ║");
            System.out.println("  ║  2. Listar Cursos                 ║");
            System.out.println("  ║  3. Ver detalle de un Curso       ║");
            System.out.println("  ║  4. Inscribir Estudiante en Curso ║");
            System.out.println("  ║  0. Volver al menú principal      ║");
            System.out.println("  ╚═══════════════════════════════════╝");

            int opcion = leerEntero("  Opción: ");

            switch (opcion) {
                case 1: crearCurso();                       break;
                case 2: listarCursos();                     break;
                case 3: verDetalleCurso();                  break;
                case 4: inscribirEstudianteEnCurso();       break;
                case 0: enSubmenu = false;                  break;
                default: System.out.println("  Opción inválida.");
            }
        }
    }

    private void crearCurso() {
        System.out.println("\n  --- CREAR CURSO ---");
        String nombre   = leerTexto("  Nombre del curso: ");
        int capacidad   = leerEntero("  Capacidad máxima de estudiantes: ");
        String desc     = leerTexto("  Descripción (Enter para omitir): ");
        universidad.agregarCurso(nombre, capacidad, desc);
    }

    private void listarCursos() {
        System.out.println("\n  --- LISTA DE CURSOS ---");
        List<Curso> cursos = universidad.getCursos();
        if (cursos.isEmpty()) {
            System.out.println("  No hay cursos registrados.");
            return;
        }
        // for-each sobre ArrayList<Curso>
        for (Curso c : cursos) {
            System.out.println("  " + c); // Llama a toString() de Curso
        }
    }

    private void verDetalleCurso() {
        System.out.println("\n  --- DETALLE DE CURSO ---");
        String codigo = leerTexto("  Código del curso (ej: CUR001): ");
        Curso c = universidad.buscarCursoPorCodigo(codigo);
        if (c == null) {
            System.out.println("  No se encontró un curso con el código: " + codigo);
        } else {
            c.mostrarDetalle();
        }
    }

    private void inscribirEstudianteEnCurso() {
        System.out.println("\n  --- INSCRIBIR ESTUDIANTE EN CURSO ---");
        String idEst   = leerTexto("  ID del estudiante (ej: E001): ");
        String codCurso = leerTexto("  Código del curso  (ej: CUR001): ");
        boolean exito  = universidad.inscribirEstudianteEnCurso(idEst, codCurso);
        if (exito) {
            System.out.println("  ✓ Inscripción exitosa.");
        }
    }

    // =========================================================
    // SUBMENÚ: REPORTES
    // =========================================================

    private void menuReportes() {
        boolean enSubmenu = true;
        while (enSubmenu) {
            System.out.println("\n  ╔════════════════════════════════╗");
            System.out.println("  ║           REPORTES             ║");
            System.out.println("  ╠════════════════════════════════╣");
            System.out.println("  ║  1. Reporte General            ║");
            System.out.println("  ║  2. Top de Estudiantes         ║");
            System.out.println("  ║  3. Listar todos (Polimorf.)   ║");
            System.out.println("  ║  0. Volver al menú principal   ║");
            System.out.println("  ╚════════════════════════════════╝");

            int opcion = leerEntero("  Opción: ");

            switch (opcion) {
                case 1: universidad.generarReporteGeneral();    break;
                case 2: universidad.mostrarTopEstudiantes();    break;
                case 3: universidad.listarTodasLasPersonas();   break;
                case 0: enSubmenu = false;                      break;
                default: System.out.println("  Opción inválida.");
            }
        }
    }

    // =========================================================
    // MÉTODOS UTILITARIOS DE LECTURA (Scanner)
    // =========================================================

    /**
     * Lee y retorna un número entero del teclado de forma segura.
     *
     * ¿Por qué este método?
     * Si el usuario escribe "abc" cuando esperamos un número,
     * scanner.nextInt() lanza InputMismatchException.
     * Este método captura ese error y pide el número de nuevo.
     *
     * ¿Qué es InputMismatchException?
     * Una excepción (error) que ocurre cuando el tipo leído no
     * coincide con el tipo esperado. La manejamos con try-catch.
     *
     * ¿Qué es try-catch?
     * Mecanismo de manejo de excepciones en Java:
     * - try: bloque donde puede ocurrir el error
     * - catch: qué hacer si ocurre el error
     *
     * @param mensaje Prompt a mostrar al usuario
     * @return El número entero leído correctamente
     */
    private int leerEntero(String mensaje) {
        while (true) {  // Bucle infinito hasta que el usuario ingrese un valor válido
            System.out.print(mensaje);
            try {
                int valor = scanner.nextInt();
                // IMPORTANTE: nextInt() NO consume el salto de línea final.
                // Hay que consumirlo manualmente para que el próximo nextLine() funcione.
                scanner.nextLine(); // Consume el \n residual en el buffer
                return valor;
            } catch (InputMismatchException e) {
                // Si se lanzó la excepción, el valor incorrecto sigue en el buffer.
                // nextLine() lo descarta para limpiar el buffer.
                scanner.nextLine();
                System.out.println("  ERROR: Por favor ingresa un número entero válido.");
            }
        }
    }

    /**
     * Lee y retorna un número decimal del teclado de forma segura.
     *
     * Mismo patrón que leerEntero() pero para valores double.
     * En algunos sistemas la separación decimal es coma (,) en otros punto (.).
     * Scanner usa el Locale del sistema. Si hay problemas, intenta escribir
     * el número con coma: 7,5 en vez de 7.5 (dependiendo del sistema).
     *
     * @param mensaje Prompt a mostrar al usuario
     * @return El número decimal leído correctamente
     */
    private double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Consume el \n residual
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("  ERROR: Por favor ingresa un número decimal válido (ej: 7.5).");
            }
        }
    }

    /**
     * Lee y retorna una línea de texto del teclado.
     *
     * scanner.nextLine() lee TODA la línea hasta el Enter.
     * .trim() elimina espacios en blanco al inicio y al final.
     *
     * @param mensaje Prompt a mostrar al usuario
     * @return La cadena de texto ingresada, sin espacios externos
     */
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    // =========================================================
    // PRESENTACIÓN
    // =========================================================

    private void mostrarBienvenida() {
        System.out.println("\n");
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║                                                  ║");
        System.out.println("  ║         UniManager — Sistema Universitario       ║");
        System.out.println("  ║              Proyecto Educativo Java OOP         ║");
        System.out.println("  ║                                                  ║");
        System.out.println("  ╠══════════════════════════════════════════════════╣");
        System.out.printf("  ║  Institución: %-35s║%n", universidad.getNombre());
        System.out.println("  ╚══════════════════════════════════════════════════╝\n");
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n  ╔═══════════════════════════════════╗");
        System.out.println("  ║          MENÚ PRINCIPAL           ║");
        System.out.println("  ╠═══════════════════════════════════╣");
        System.out.println("  ║  1. Gestión de Estudiantes        ║");
        System.out.println("  ║  2. Gestión de Profesores         ║");
        System.out.println("  ║  3. Gestión de Cursos             ║");
        System.out.println("  ║  4. Reportes                      ║");
        System.out.println("  ║  0. Salir                         ║");
        System.out.println("  ╚═══════════════════════════════════╝");
    }
}

package unimanager;

import unimanager.service.MenuService;
import unimanager.service.Universidad;

/**
 * ============================================================
 * CLASE: Main (Punto de Entrada del Programa)
 * ============================================================
 *
 * En Java, el programa siempre inicia en el método main().
 * Las reglas del método main son fijas e inamovibles:
 *
 *   public static void main(String[] args)
 *   |      |      |    |    |____________|
 *   |      |      |    |    └─ Array de args. de línea de comandos
 *   |      |      |    └───── Nombre obligatorio: "main"
 *   |      |      └────────── No retorna nada
 *   |      └───────────────── Pertenece a la clase, no a un objeto
 *   └──────────────────────── Accesible desde fuera de la clase
 *
 * ¿Por qué main() es static?
 * Porque la JVM (Java Virtual Machine) necesita llamarlo SIN haber
 * creado ningún objeto de la clase. Si fuera de instancia, ¿qué
 * objeto usaría la JVM para llamarlo?
 *
 * Esta clase aplica el PRINCIPIO DE RESPONSABILIDAD ÚNICA:
 * Main SOLO se encarga de arrancar el sistema. Toda la lógica
 * real está en Universidad y MenuService.
 * ============================================================
 */
public class Main {

    /**
     * Punto de entrada del programa.
     *
     * Flujo:
     * 1. Crea la instancia de Universidad con su nombre.
     * 2. Crea el MenuService, inyectándole la Universidad.
     * 3. Inicia el bucle principal del menú.
     *
     * "Inyectar" la dependencia (pasarla como parámetro en el constructor)
     * en lugar de crearla dentro del MenuService es una práctica llamada
     * INYECCIÓN DE DEPENDENCIAS. Facilita las pruebas y la flexibilidad.
     *
     * @param args Argumentos de línea de comandos (no se usan en este proyecto)
     */
    public static void main(String[] args) {

        // 1. Instanciar la universidad (la "base de datos" en memoria)
        Universidad universidad = new Universidad("Universidad Tecnológica de Java");

        // 2. Crear el servicio de menú, pasándole la universidad
        MenuService menuService = new MenuService(universidad);

        // 3. Iniciar el bucle de menú — el programa se ejecuta aquí
        //    hasta que el usuario seleccione "Salir" (opción 0)
        menuService.iniciar();
    }
}

package unimanager.interfaces;

/**
 * ============================================================
 * INTERFACE: Reportable
 * ============================================================
 *
 * Segunda interface del proyecto. Representa la capacidad de
 * una entidad para GENERAR un reporte de sí misma.
 *
 * ¿Por qué tener una segunda interface en vez de meter todo
 * en Evaluable?
 *
 * Principio de Responsabilidad Única (SRP) y Segregación de
 * Interfaces (ISP) de SOLID:
 *   - Una interface debe tener UN propósito claro y acotado.
 *   - No se debe forzar a una clase a implementar métodos que
 *     no necesita.
 *
 * Ejemplo práctico:
 *   - Estudiante implementa Evaluable (puede ser calificado).
 *   - Profesor implementa Reportable (puede generar un informe
 *     de su actividad docente).
 *   - Si en el futuro un Coordinador puede hacer AMBAS cosas,
 *     simplemente implementa `Evaluable, Reportable`.
 *     Java permite implementar MÚLTIPLES interfaces (a diferencia
 *     de la herencia, que es solo de una clase).
 *
 * Concepto clave demostrado: Una clase puede implementar
 * MÚLTIPLES interfaces en Java.
 * ============================================================
 */
public interface Reportable {

    /**
     * Genera y muestra por consola un reporte detallado
     * de la entidad.
     *
     * Cada clase que implemente esta interface decidirá
     * el formato y el contenido de su propio reporte.
     * Eso es POLIMORFISMO: el mismo método se comporta
     * diferente según el tipo concreto del objeto.
     */
    void generarReporte();
}

package unimanager.interfaces;

/**
 * ============================================================
 * INTERFACE: Evaluable
 * ============================================================
 *
 * ¿Qué es una interface en Java?
 * Una interface es un CONTRATO. Define QUÉ métodos deben existir,
 * pero NO cómo funcionan. Cualquier clase que implemente esta
 * interface ESTÁ OBLIGADA a escribir el cuerpo de esos métodos.
 *
 * ¿Por qué usarla aquí?
 * Queremos garantizar que CUALQUIER entidad "evaluable" (que puede
 * ser calificada) tenga un método para calcular su promedio.
 * Hoy solo el Estudiante es evaluable, mañana podría serlo un
 * Grupo de Proyecto. La interface nos permite hacer ese cambio
 * sin romper el código existente. Esto se llama el principio
 * Open/Closed de SOLID.
 *
 * Concepto clave: ABSTRACCIÓN y POLIMORFISMO.
 * Una variable de tipo Evaluable puede apuntar a CUALQUIER clase
 * que la implemente, sin importar de qué tipo concreto sea.
 * ============================================================
 */
public interface Evaluable {

    /**
     * Calcula el promedio de las calificaciones de la entidad.
     *
     * Notar que aquí solo se DECLARA el método (sin cuerpo {}).
     * La clase que implemente esta interface debe escribir el cuerpo.
     *
     * @return El promedio como número decimal (double).
     *         Devuelve 0.0 si no hay calificaciones registradas.
     */
    double calcularPromedio();

    /**
     * Determina si la entidad ha aprobado según el reglamento.
     *
     * ¿Qué es un "default method" en una interface? (Java 8+)
     * Es un método con implementación DENTRO de la interface.
     * Esto permite agregar funcionalidad a una interface sin romper
     * todas las clases que ya la implementan.
     *
     * En este caso, la regla de aprobación (>=5.0) es común para
     * TODOS los evaluables, por eso tiene sentido ponerla aquí.
     * Las clases que implementen Evaluable pueden sobrescribirla
     * si sus reglas son diferentes.
     *
     * @return true si el promedio es mayor o igual a 5.0 (aprobado),
     *         false en caso contrario.
     */
    default boolean estaAprobado() {
        // Reutilizamos calcularPromedio() que cada clase implementará.
        // Este es un ejemplo de DRY (Don't Repeat Yourself).
        return calcularPromedio() >= 5.0;
    }
}

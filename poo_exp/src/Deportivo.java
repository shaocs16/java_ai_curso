/**
 * Deportivo hereda de Vehiculo (relación IS-A: "Deportivo ES UN Vehiculo").
 * 
 * COMUNICACIÓN CON VEHICULO:
 * - Hereda TODOS los atributos: marca, modelo, motor, tapiceria, ruedas.
 * - Llama a super(...) para inicializar los atributos del padre.
 * - Añade su propio atributo: velocidadMaxKmh.
 * - Implementa getTipoVehiculo() (método abstracto del padre).
 * 
 * COMUNICACIÓN INDIRECTA:
 * - Al heredar de Vehiculo, también accede a Motor, Tapiceria y Rueda
 *   a través de los getters heredados (getMotor(), getTapiceria(), getRuedas()).
 */
public class Deportivo extends Vehiculo {
    private int velocidadMaxKmh;

    public Deportivo() {}

    public Deportivo(String marca, String modelo, Motor motor, Tapiceria tapiceria,
                     Rueda[] ruedas, int velocidadMaxKmh) {
        super(marca, modelo, motor, tapiceria, ruedas); // <<< Llama al constructor de Vehiculo
        this.velocidadMaxKmh = velocidadMaxKmh;
    }

    // Getter y Setter propios
    public int getVelocidadMaxKmh() { return velocidadMaxKmh; }
    public void setVelocidadMaxKmh(int velocidadMaxKmh) { this.velocidadMaxKmh = velocidadMaxKmh; }

    @Override
    public String getTipoVehiculo() {
        return "DEPORTIVO";
    }

    @Override
    public String toString() {
        return super.toString() + "\n  Velocidad Max: " + velocidadMaxKmh + " km/h";
    }
}

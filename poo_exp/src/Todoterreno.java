/**
 * Todoterreno hereda de Vehiculo (relación IS-A: "Todoterreno ES UN Vehiculo").
 * 
 * COMUNICACIÓN CON VEHICULO:
 * - Hereda TODOS los atributos del padre (marca, modelo, motor, tapiceria, ruedas).
 * - Llama a super(...) para inicializar la parte del padre.
 * - Añade su propio atributo: traccion4x4.
 * - Implementa getTipoVehiculo() (método abstracto del padre).
 */
public class Todoterreno extends Vehiculo {
    private boolean traccion4x4;

    public Todoterreno() {}

    public Todoterreno(String marca, String modelo, Motor motor, Tapiceria tapiceria,
                       Rueda[] ruedas, boolean traccion4x4) {
        super(marca, modelo, motor, tapiceria, ruedas); // <<< Llama al constructor de Vehiculo
        this.traccion4x4 = traccion4x4;
    }

    // Getter y Setter propios
    public boolean isTraccion4x4() { return traccion4x4; }
    public void setTraccion4x4(boolean traccion4x4) { this.traccion4x4 = traccion4x4; }

    @Override
    public String getTipoVehiculo() {
        return "TODOTERRENO";
    }

    @Override
    public String toString() {
        return super.toString() + "\n  Traccion 4x4: " + (traccion4x4 ? "SI" : "NO");
    }
}

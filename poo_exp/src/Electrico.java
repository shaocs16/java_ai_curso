/**
 * Electrico hereda de Motor (relación IS-A: "Electrico ES UN Motor").
 * 
 * COMUNICACIÓN CON MOTOR:
 * - Hereda el atributo potenciaCV y sus getters/setters.
 * - Llama al constructor del padre con super(potenciaCV).
 * - Implementa el método abstracto getTipoMotor() del padre.
 */
public class Electrico extends Motor {
    private int autonomiaKm; // autonomía en kilómetros

    public Electrico() {}

    public Electrico(int potenciaCV, int autonomiaKm) {
        super(potenciaCV); // <<< Llama al constructor de Motor (clase padre)
        this.autonomiaKm = autonomiaKm;
    }

    // Getters y Setters propios
    public int getAutonomiaKm() { return autonomiaKm; }
    public void setAutonomiaKm(int autonomiaKm) { this.autonomiaKm = autonomiaKm; }

    /**
     * Implementación obligatoria del método abstracto del padre.
     * Esto es POLIMORFISMO: cada hijo responde de forma distinta.
     */
    @Override
    public String getTipoMotor() {
        return "Electrico";
    }

    @Override
    public String toString() {
        return "Motor Electrico [potenciaCV=" + getPotenciaCV() + ", autonomiaKm=" + autonomiaKm + "]";
    }
}

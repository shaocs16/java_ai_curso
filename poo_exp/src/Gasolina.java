/**
 * Gasolina hereda de Motor (relación IS-A: "Gasolina ES UN Motor").
 * 
 * COMUNICACIÓN CON MOTOR:
 * - Hereda potenciaCV del padre.
 * - Llama a super(potenciaCV) en su constructor.
 * - Implementa getTipoMotor() (método abstracto del padre).
 */
public class Gasolina extends Motor {
    private int cilindrada; // cilindrada en cc

    public Gasolina() {}

    public Gasolina(int potenciaCV, int cilindrada) {
        super(potenciaCV); // <<< Llama al constructor de Motor (clase padre)
        this.cilindrada = cilindrada;
    }

    // Getters y Setters propios
    public int getCilindrada() { return cilindrada; }
    public void setCilindrada(int cilindrada) { this.cilindrada = cilindrada; }

    @Override
    public String getTipoMotor() {
        return "Gasolina";
    }

    @Override
    public String toString() {
        return "Motor Gasolina [potenciaCV=" + getPotenciaCV() + ", cilindrada=" + cilindrada + "cc]";
    }
}

/**
 * Clase abstracta Motor.
 * Es la clase PADRE de Electrico y Gasolina.
 * 
 * COMUNICACIÓN: Motor se usa por COMPOSICIÓN dentro de Vehiculo.
 * Es decir, un Vehiculo "tiene un" Motor (relación HAS-A).
 */
public abstract class Motor {
    private int potenciaCV;

    public Motor() {}

    public Motor(int potenciaCV) {
        this.potenciaCV = potenciaCV;
    }

    // Getters y Setters
    public int getPotenciaCV() { return potenciaCV; }
    public void setPotenciaCV(int potenciaCV) { this.potenciaCV = potenciaCV; }

    /**
     * Método abstracto: cada tipo de motor describe su tipo de forma distinta.
     * Electrico y Gasolina DEBEN implementarlo (polimorfismo).
     */
    public abstract String getTipoMotor();

    @Override
    public String toString() {
        return "Motor [tipo=" + getTipoMotor() + ", potenciaCV=" + potenciaCV + "]";
    }
}

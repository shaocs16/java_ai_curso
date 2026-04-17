import java.util.Arrays;

/**
 * Clase abstracta Vehiculo.
 * Es la clase PADRE de Deportivo y Todoterreno.
 * 
 * ============================================================
 * COMUNICACIÓN ENTRE CLASES - AQUÍ SE VE TODO:
 * ============================================================
 * 
 * 1) COMPOSICIÓN (HAS-A / "tiene un"):
 *    - Vehiculo TIENE UN Motor   → atributo "motor" de tipo Motor
 *    - Vehiculo TIENE UNA Tapiceria → atributo "tapiceria" de tipo Tapiceria
 *    - Vehiculo TIENE Ruedas     → atributo "ruedas" de tipo Rueda[]
 *    
 *    Esto significa que Vehiculo USA objetos de otras clases como
 *    parte de sí mismo. Vehiculo no hereda de Motor ni de Tapiceria,
 *    sino que los contiene.
 * 
 * 2) HERENCIA (IS-A / "es un"):
 *    - Deportivo ES UN Vehiculo  → Deportivo extends Vehiculo
 *    - Todoterreno ES UN Vehiculo → Todoterreno extends Vehiculo
 *    
 *    Heredan TODOS los atributos y métodos de Vehiculo
 *    (marca, modelo, motor, tapiceria, ruedas...).
 * 
 * 3) POLIMORFISMO:
 *    - El atributo "motor" es de tipo Motor (clase abstracta).
 *      En tiempo de ejecución puede ser un Electrico o un Gasolina.
 *      Cuando llamamos motor.getTipoMotor(), Java ejecuta la versión
 *      correcta según el objeto real (Electrico o Gasolina).
 * 
 * ============================================================
 */
public abstract class Vehiculo {
    private String marca;
    private String modelo;
    private Motor motor;          // COMPOSICIÓN: Vehiculo "tiene un" Motor
    private Tapiceria tapiceria;  // COMPOSICIÓN: Vehiculo "tiene una" Tapiceria
    private Rueda[] ruedas;       // COMPOSICIÓN: Vehiculo "tiene" Ruedas

    public Vehiculo() {}

    public Vehiculo(String marca, String modelo, Motor motor, Tapiceria tapiceria, Rueda[] ruedas) {
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.tapiceria = tapiceria;
        this.ruedas = ruedas;
    }

    // Getters y Setters
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public Motor getMotor() { return motor; }
    public void setMotor(Motor motor) { this.motor = motor; }
    public Tapiceria getTapiceria() { return tapiceria; }
    public void setTapiceria(Tapiceria tapiceria) { this.tapiceria = tapiceria; }
    public Rueda[] getRuedas() { return ruedas; }
    public void setRuedas(Rueda[] ruedas) { this.ruedas = ruedas; }

    /**
     * Método abstracto: cada tipo de vehículo se describe de forma distinta.
     */
    public abstract String getTipoVehiculo();

    @Override
    public String toString() {
        return "=== " + getTipoVehiculo() + " ===" +
               "\n  Marca: " + marca +
               "\n  Modelo: " + modelo +
               "\n  " + motor +
               "\n  " + tapiceria +
               "\n  Ruedas: " + Arrays.toString(ruedas);
    }
}

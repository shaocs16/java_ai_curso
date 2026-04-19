package com.clinica.modelo;

/**
 * Gato — subclase concreta de Animal.
 *
 * CONCEPTOS POO aplicados:
 *  - Herencia     → extends Animal, hereda id, nombre, edad, peso, propietario.
 *  - Polimorfismo → implementa getTipoAnimal() y getSonido().
 *  - Sobrecarga   → 2 constructores: con y sin propietario.
 */
public class Gato extends Animal {

    // ─── ATRIBUTOS PROPIOS ────────────────────────────────────────────────────
    private boolean esDeInterior;
    private String colorPelaje;

    // ─── CONSTRUCTORES ────────────────────────────────────────────────────────

    // Sin propietario
    public Gato(String nombre, int edad, double peso, boolean esDeInterior, String colorPelaje) {
        super(nombre, edad, peso);
        this.esDeInterior = esDeInterior;
        this.colorPelaje  = colorPelaje;
    }

    // Con propietario
    public Gato(String nombre, int edad, double peso, Propietario propietario,
                boolean esDeInterior, String colorPelaje) {
        super(nombre, edad, peso, propietario);
        this.esDeInterior = esDeInterior;
        this.colorPelaje  = colorPelaje;
    }

    // ─── MÉTODOS ABSTRACTOS ───────────────────────────────────────────────────

    @Override
    public String getTipoAnimal() { return "Gato"; }

    @Override
    public String getSonido() { return "Miau"; }

    // ─── GETTERS / SETTERS ────────────────────────────────────────────────────

    public boolean isEsDeInterior()   { return esDeInterior; }
    public String getColorPelaje()    { return colorPelaje; }

    public void setEsDeInterior(boolean esDeInterior) { this.esDeInterior = esDeInterior; }
    public void setColorPelaje(String colorPelaje)    { this.colorPelaje = colorPelaje; }

    // ─── toString ─────────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return super.toString()
             + " | Interior: " + (esDeInterior ? "Sí" : "No")
             + " | Color: " + colorPelaje;
    }
}

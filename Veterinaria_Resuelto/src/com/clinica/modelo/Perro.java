package com.clinica.modelo;

/**
 * Perro — subclase concreta de Animal.
 *
 * CONCEPTOS POO aplicados:
 *  - Herencia     → extends Animal, hereda id, nombre, edad, peso, propietario.
 *  - Polimorfismo → implementa getTipoAnimal() y getSonido().
 *  - Sobrecarga   → 2 constructores: con y sin propietario.
 */
public class Perro extends Animal {

    // ─── ATRIBUTOS PROPIOS ────────────────────────────────────────────────────
    private String raza;
    private boolean estaAdiestrado;

    // ─── CONSTRUCTORES ────────────────────────────────────────────────────────

    // Sin propietario (animal callejero o sin dueño registrado aún)
    public Perro(String nombre, int edad, double peso, String raza, boolean estaAdiestrado) {
        super(nombre, edad, peso);          // llama al constructor base de Animal
        this.raza           = raza;
        this.estaAdiestrado = estaAdiestrado;
    }

    // Con propietario
    public Perro(String nombre, int edad, double peso, Propietario propietario,
                 String raza, boolean estaAdiestrado) {
        super(nombre, edad, peso, propietario);  // llama al constructor completo de Animal
        this.raza           = raza;
        this.estaAdiestrado = estaAdiestrado;
    }

    // ─── MÉTODOS ABSTRACTOS ───────────────────────────────────────────────────

    @Override
    public String getTipoAnimal() { return "Perro"; }

    @Override
    public String getSonido() { return "Guau"; }

    // ─── GETTERS / SETTERS ────────────────────────────────────────────────────

    public String getRaza()           { return raza; }
    public boolean isEstaAdiestrado() { return estaAdiestrado; }

    public void setRaza(String raza)                  { this.raza = raza; }
    public void setEstaAdiestrado(boolean adiestrado) { this.estaAdiestrado = adiestrado; }

    // ─── toString ─────────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return super.toString()
             + " | Raza: " + raza
             + " | Adiestrado: " + (estaAdiestrado ? "Sí" : "No");
    }
}

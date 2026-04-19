package com.clinica.modelo;

/**
 * Animal (clase abstracta) — clase base de la jerarquía de herencia.
 *
 * CONCEPTOS POO aplicados:
 *  - Abstracción  → clase abstracta con métodos abstractos.
 *  - Encapsulación → atributos private + getters/setters.
 *  - Composición  → referencia a Propietario.
 *  - ID estático  → contador static autoincremental.
 *  - Sobrecarga   → 2 constructores: con y sin propietario.
 */
public abstract class Animal {

    // ─── ID AUTOINCREMENTAL ───────────────────────────────────────────────────
    private static int contador = 0;

    // ─── ATRIBUTOS ────────────────────────────────────────────────────────────
    private int id;
    private String nombre;
    private int edad;
    private double peso;
    private Propietario propietario;

    // ─── CONSTRUCTORES SOBRECARGADOS ──────────────────────────────────────────

    // Constructor base: sin propietario (propietario queda null por defecto)
    protected Animal(String nombre, int edad, double peso) {
        this.id     = ++contador;
        this.nombre = nombre;
        this.edad   = edad;
        this.peso   = peso;
    }

    // Constructor completo: llama al base y añade propietario
    protected Animal(String nombre, int edad, double peso, Propietario propietario) {
        this(nombre, edad, peso);           // reutiliza el constructor base (ID solo se asigna una vez)
        this.propietario = propietario;
    }

    // ─── MÉTODOS ABSTRACTOS ───────────────────────────────────────────────────

    public abstract String getTipoAnimal();  // "Perro" / "Gato"
    public abstract String getSonido();      // "Guau"  / "Miau"

    // ─── GETTERS ──────────────────────────────────────────────────────────────

    public int getId()                  { return id; }
    public String getNombre()           { return nombre; }
    public int getEdad()                { return edad; }
    public double getPeso()             { return peso; }
    public Propietario getPropietario() { return propietario; }

    // ─── SETTERS ──────────────────────────────────────────────────────────────

    public void setNombre(String nombre)                    { this.nombre = nombre; }
    public void setEdad(int edad)                           { this.edad = edad; }
    public void setPeso(double peso)                        { this.peso = peso; }
    public void setPropietario(Propietario propietario)     { this.propietario = propietario; }

    // ─── toString ─────────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return getTipoAnimal() + " #" + id
             + " | Nombre: " + nombre
             + " | Edad: " + edad + " años"
             + " | Peso: " + peso + " kg"
             + " | Sonido: " + getSonido()
             + " | Dueño: " + (propietario != null ? propietario.getNombre() : "Sin dueño");
    }
}

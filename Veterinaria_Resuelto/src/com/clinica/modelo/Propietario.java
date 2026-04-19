package com.clinica.modelo;

import java.util.ArrayList;

/**
 * Propietario — dueño de uno o más animales registrados en la clínica.
 *
 * CONCEPTOS POO aplicados:
 *  - Encapsulación → atributos private + getters/setters controlados.
 *  - Composición   → contiene una ArrayList<Animal> (tiene-muchos animales).
 *  - ID estático   → contador static para IDs únicos globales.
 *  - Colecciones   → ArrayList de tamaño dinámico en lugar de array fijo.
 */
public class Propietario {

    // ─── ID AUTOINCREMENTAL ───────────────────────────────────────────────────
    // 'static' pertenece a la clase, no a cada objeto.
    // Cada vez que se crea un Propietario nuevo, el contador sube 1.
    private static int contador = 0;

    // ─── ATRIBUTOS ────────────────────────────────────────────────────────────
    private int id;
    private String nombre;
    private String telefono;

    // COMPOSICIÓN: Propietario *tiene* una lista de animales.
    // ArrayList porque el número de mascotas es variable (0..N).
    private ArrayList<Animal> animales;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────────────────
    public Propietario(String nombre, String telefono) {
        this.id       = ++contador;        // Pre-incremento: 1, 2, 3...
        this.nombre   = nombre;
        this.telefono = telefono;
        this.animales = new ArrayList<>(); // Lista vacía al inicio
    }

    // ─── MÉTODOS CLAVE ────────────────────────────────────────────────────────

    /**
     * Agrega un animal a la lista del propietario.
     * La unicidad ya está garantizada por el ID de Animal.
     */
    public void registrarAnimal(Animal a) {
        animales.add(a);
    }

    /**
     * Elimina un animal buscándolo por su ID.
     *
     * Se guarda el objeto encontrado en una variable auxiliar para
     * no modificar la lista mientras se itera (evita ConcurrentModificationException).
     */
    public void eliminarAnimal(int idAnimal) {
        Animal aEliminar = null;

        for (Animal a : animales) {
            if (a.getId() == idAnimal) {
                aEliminar = a;
                break; // Encontrado, salimos antes para no seguir recorriendo
            }
        }

        if (aEliminar != null) {
            animales.remove(aEliminar);
            System.out.println("Animal con ID " + idAnimal + " eliminado de " + nombre + ".");
        } else {
            System.out.println("No se encontró ningún animal con ID " + idAnimal + ".");
        }
    }

    /**
     * Muestra por consola todos los animales del propietario.
     * Llama al toString() polimórfico de cada Animal (Perro o Gato).
     */
    public void listarAnimales() {
        if (animales.isEmpty()) {
            System.out.println(nombre + " no tiene animales registrados.");
            return;
        }
        System.out.println("── Animales de " + nombre + " (" + animales.size() + ") ──");
        for (Animal a : animales) {
            System.out.println("  → " + a); // Invoca toString() de Perro o Gato
        }
    }

    /**
     * Devuelve el número total de animales registrados a este propietario.
     */
    public int getTotalAnimales() {
        return animales.size(); // ArrayList ya lleva la cuenta internamente
    }

    // ─── GETTERS ──────────────────────────────────────────────────────────────

    public int getId()                       { return id; }
    public String getNombre()                { return nombre; }
    public String getTelefono()              { return telefono; }
    public ArrayList<Animal> getAnimales()   { return animales; }

    // ─── SETTERS (el id nunca cambia, por eso no tiene setter) ───────────────

    public void setNombre(String nombre)     { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    // ─── toString ─────────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return "Propietario #" + id
             + " | " + nombre
             + " | Tel: " + telefono
             + " | Animales: " + animales.size();
    }
}

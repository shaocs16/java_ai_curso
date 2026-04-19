package com.clinica.gestion;

import com.clinica.modelo.Animal;
import com.clinica.modelo.Cita;
import com.clinica.modelo.Propietario;

import java.util.ArrayList;

/**
 * Clinica — clase central de gestión del sistema veterinario.
 *
 * CONCEPTOS POO aplicados:
 *  - Composición  → tiene listas de Animal, Propietario y Cita.
 *  - Polimorfismo → usa getTipoAnimal() para filtrar sin saber si es Perro o Gato.
 *  - Colecciones  → ArrayList para cada entidad gestionada.
 */
public class Clinica {

    // ─── ATRIBUTOS ────────────────────────────────────────────────────────────
    private String nombre;
    private ArrayList<Animal>      animales;
    private ArrayList<Propietario> propietarios;
    private ArrayList<Cita>        citas;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────────────────
    public Clinica(String nombre) {
        this.nombre       = nombre;
        this.animales     = new ArrayList<>();
        this.propietarios = new ArrayList<>();
        this.citas        = new ArrayList<>();
    }

    // ─── MÉTODOS DE REGISTRO ──────────────────────────────────────────────────

    /**
     * Registra un propietario si no existe ya (comprobado por ID).
     */
    public void registrarPropietario(Propietario p) {
        for (Propietario existing : propietarios) {
            if (existing.getId() == p.getId()) {
                System.out.println("El propietario " + p.getNombre() + " ya está registrado.");
                return;
            }
        }
        propietarios.add(p);
        System.out.println("Propietario registrado: " + p.getNombre());
    }

    /**
     * Registra un animal en la clínica.
     */
    public void registrarAnimal(Animal a) {
        animales.add(a);
        System.out.println("Animal registrado: " + a.getNombre() + " (" + a.getTipoAnimal() + ")");
    }

    /**
     * Agenda una nueva cita.
     */
    public void agendarCita(Cita c) {
        citas.add(c);
        System.out.println("Cita agendada: " + c.getFecha()
                + " — " + c.getAnimal().getNombre()
                + " con Dr. " + c.getVeterinario());
    }

    // ─── MÉTODOS DE BÚSQUEDA ──────────────────────────────────────────────────

    /**
     * Busca un animal por su ID. Devuelve null si no existe.
     */
    public Animal buscarAnimalPorId(int id) {
        for (Animal a : animales) {
            if (a.getId() == id) return a;
        }
        return null;   // convención Java: null si no se encontró
    }

    /**
     * Busca un propietario por su ID. Devuelve null si no existe.
     */
    public Propietario buscarPropietarioPorId(int id) {
        for (Propietario p : propietarios) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    /**
     * Busca una cita por su ID. Devuelve null si no existe.
     */
    public Cita buscarCitaPorId(int id) {
        for (Cita c : citas) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    // ─── MÉTODOS DE LISTADO ───────────────────────────────────────────────────

    /**
     * Lista animales filtrando por tipo ("Perro" o "Gato").
     * Usa polimorfismo: getTipoAnimal() responde distinto según la subclase.
     */
    public void listarAnimalesPorTipo(String tipo) {
        System.out.println("── Animales de tipo: " + tipo + " ──");
        boolean encontrado = false;
        for (Animal a : animales) {
            if (a.getTipoAnimal().equalsIgnoreCase(tipo)) {
                System.out.println("  " + a);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("  No hay animales de tipo " + tipo + ".");
    }

    /**
     * Lista todas las citas con realizada == false.
     */
    public void listarCitasPendientes() {
        System.out.println("── Citas pendientes ──");
        boolean encontrado = false;
        for (Cita c : citas) {
            if (!c.isRealizada()) {
                System.out.println("  " + c);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("  No hay citas pendientes.");
    }

    /**
     * Lista todos los animales que pertenecen a un propietario.
     */
    public void listarAnimalesPorPropietario(int idPropietario) {
        Propietario p = buscarPropietarioPorId(idPropietario);
        if (p == null) {
            System.out.println("Propietario con ID " + idPropietario + " no encontrado.");
            return;
        }
        p.listarAnimales();   // Delegamos en el método de Propietario
    }

    // ─── MÉTODOS DE CÁLCULO ───────────────────────────────────────────────────

    /**
     * Suma los precios de todas las citas marcadas como realizadas.
     */
    public double calcularIngresosTotales() {
        double total = 0;
        for (Cita c : citas) {
            if (c.isRealizada()) {
                total += c.getPrecio();
            }
        }
        System.out.println("Ingresos totales de " + nombre + ": " + total + "€");
        return total;
    }

    // ─── GETTER del nombre ────────────────────────────────────────────────────
    public String getNombre() { return nombre; }
}

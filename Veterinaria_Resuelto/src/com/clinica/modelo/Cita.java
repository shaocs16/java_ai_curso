package com.clinica.modelo;

/**
 * Cita — representa una consulta médica en la clínica.
 *
 * CONCEPTOS POO aplicados:
 *  - Composición  → contiene una referencia a Animal (tiene-un paciente).
 *  - Encapsulación → atributos private + getters controlados.
 *  - ID estático  → contador static autoincremental.
 */
public class Cita {

    // ─── ID AUTOINCREMENTAL ───────────────────────────────────────────────────
    private static int contador = 0;

    // ─── ATRIBUTOS ────────────────────────────────────────────────────────────
    private int id;
    private Animal animal;         // COMPOSICIÓN: la cita pertenece a un Animal
    private String veterinario;
    private String fecha;          // formato "dd/MM/yyyy" como String (sin librerías externas)
    private String motivo;
    private double precio;
    private boolean realizada;     // false por defecto al agendar la cita

    // ─── CONSTRUCTOR ──────────────────────────────────────────────────────────
    // 'realizada' siempre empieza en false: la cita se agenda, todavía no ocurre.
    public Cita(Animal animal, String veterinario, String fecha,
                String motivo, double precio) {
        this.id          = ++contador;
        this.animal      = animal;
        this.veterinario = veterinario;
        this.fecha       = fecha;
        this.motivo      = motivo;
        this.precio      = precio;
        this.realizada   = false;
    }

    // ─── MÉTODOS CLAVE ────────────────────────────────────────────────────────

    /**
     * Marca la cita como realizada.
     * Se usa un método propio en lugar de setRealizada() para proteger
     * contra cambios accidentales (no se puede "des-realizar" una cita).
     */
    public void marcarRealizada() {
        if (realizada) {
            System.out.println("La cita #" + id + " ya estaba marcada como realizada.");
        } else {
            this.realizada = true;
            System.out.println("Cita #" + id + " marcada como realizada. ✔");
        }
    }

    // ─── GETTERS ──────────────────────────────────────────────────────────────
    // No hay setter para 'id' ni setter directo para 'realizada'.

    public int getId()               { return id; }
    public Animal getAnimal()        { return animal; }
    public String getVeterinario()   { return veterinario; }
    public String getFecha()         { return fecha; }
    public String getMotivo()        { return motivo; }
    public double getPrecio()        { return precio; }
    public boolean isRealizada()     { return realizada; }

    // ─── SETTERS (solo los campos que tiene sentido modificar) ───────────────
    public void setVeterinario(String veterinario) { this.veterinario = veterinario; }
    public void setFecha(String fecha)             { this.fecha = fecha; }
    public void setMotivo(String motivo)           { this.motivo = motivo; }
    public void setPrecio(double precio)           { this.precio = precio; }

    // ─── toString ─────────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return "Cita #" + id
             + " | Fecha: "   + fecha
             + " | Animal: "  + animal.getNombre() + " (" + animal.getTipoAnimal() + ")"
             + " | Vet: "     + veterinario
             + " | Motivo: "  + motivo
             + " | Precio: "  + precio + "€"
             + " | Estado: "  + (realizada ? "✔ Realizada" : "⏳ Pendiente");
    }
}

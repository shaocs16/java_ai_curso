package com.clinica.modelo;


public class Cita {

    private static int contador = 0;
    private int id;
    private Animal animal;
    private String fecha, veterinario, motivo;
    private double precio;
    private boolean realizada;

    public Cita(Animal animal, String veterinario,
                String motivo, double precio, String fecha){
        this.id = ++contador;
        this.animal = animal;
        this.veterinario = veterinario;
        this.motivo = motivo;
        this.fecha = fecha;
        this.precio = precio;
        this.realizada = false;
    }

    public void marcarRealizada(){
        if (realizada) {
            System.out.println("La cita #" + id + " ya estaba marcada como realizada.");
        } else {
            this.realizada = true;
            System.out.println("Cita #" + id + " marcada como realizada. ✔");
        }
    }

    public int getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getFecha() {
        return fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isRealizada() {
        return realizada;
    }

    @Override
    public String toString() {
        return "Cita #" + id
                + " | Fecha: "       + fecha
                + " | Animal: "      + animal.getNombre() + " (" + animal.getTipoAnimal() + ")"
                + " | Vet: "         + veterinario
                + " | Motivo: "      + motivo
                + " | Precio: "      + precio + "€"
                + " | Estado: "      + (realizada ? "✔ Realizada" : "⏳ Pendiente");
    }
}

package com.clinica.modelo;

public abstract class Animal {

    private static int contador = 0;
    private int id;
    private String nombre;
    private int edad;
    private double peso;
    private Propietario propietario;

    public Animal(String nombre, int edad, double peso) {
        this.id = ++contador;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    public Animal(String nombre, int edad, double peso, Propietario propietario) {
        this(nombre, edad, peso);
        this.propietario = propietario;

    }

    public abstract String getTipoAnimal();
    public abstract String getSonido();

    @Override
    public String toString() {
        return "Animal: " + id + "\r\n->Raza: " + getTipoAnimal()
                + "\r\nNombre: " + nombre
                + "\r\nEdad: " + edad
                + "\r\nPeso: " + peso
                + "\r\nPropietario: " + (propietario != null ? propietario.getNombre() : "Sin dueño");
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
}

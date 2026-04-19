package com.clinica.modelo;

public class Perro extends Animal {

    private String raza;
    private boolean estaAdiestrado;

    public Perro(String nombre, int edad, double peso, String raza, boolean estaAdiestrado) {
        super(nombre, edad, peso);          // llama al constructor base de Animal
        this.raza           = raza;
        this.estaAdiestrado = estaAdiestrado;
    }

    public Perro(String nombre, int edad, double peso, String raza, boolean estaAdiestrado, Propietario propietario){
        super(nombre, edad, peso, propietario);
        this.raza = raza;
        this.estaAdiestrado = estaAdiestrado;
    }

    @Override
    public String getTipoAnimal() {
        return "Perro";
    }

    @Override
    public String getSonido() {
        return "Guau";
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public boolean isEstaAdiestrado() {
        return estaAdiestrado;
    }

    public void setEstaAdiestrado(boolean estaAdiestrado) {
        this.estaAdiestrado = estaAdiestrado;
    }
}

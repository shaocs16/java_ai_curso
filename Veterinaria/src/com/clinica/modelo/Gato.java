package com.clinica.modelo;

public class Gato extends Animal{

    private String colorPelaje ;
    private boolean esDeInterior;

    public Gato(String nombre, int edad, double peso, boolean esDeInterior, String colorPelaje) {
        super(nombre, edad, peso);
        this.esDeInterior = esDeInterior;
        this.colorPelaje  = colorPelaje;
    }

    public Gato(String nombre, int edad, double peso, String colorPelaje, boolean esDeInterior, Propietario propietario){
        super(nombre, edad, peso, propietario);
        this.colorPelaje = colorPelaje;
        this.esDeInterior = esDeInterior;
    }

    @Override
    public String getTipoAnimal() {
        return "Gato";
    }

    @Override
    public String getSonido() {
        return "Miau";
    }

    public String getColorPelaje() {
        return colorPelaje;
    }

    public void setColorPelaje(String colorPelaje) {
        this.colorPelaje = colorPelaje;
    }

    public boolean isEsDeInterior() {
        return esDeInterior;
    }

    public void setEsDeInterior(boolean esDeInterior) {
        this.esDeInterior = esDeInterior;
    }
}

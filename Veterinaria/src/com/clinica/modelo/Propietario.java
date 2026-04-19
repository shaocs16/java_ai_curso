package com.clinica.modelo;

import java.util.ArrayList;

public class Propietario {

    private static int contador = 0;
    private int id;
    private String nombre;
    private String telefono;
    private ArrayList<Animal> animales;

    public Propietario(String nombre, String telefono){
        this.id = ++contador;
        this.nombre = nombre;
        this.telefono = telefono;
        this.animales = new ArrayList<>();
    }

    public void addAnimales(Animal animal) {
        animales.add(animal);
    }

    public void eliminarAnimal(int idAnimal){
        Animal encontrado = null;
        for(Animal a: animales){
            if(a.getId() == idAnimal){
                encontrado = a;
                break;
            }
        }

        if(encontrado != null) {
            animales.remove(encontrado);
            System.out.println("Se ha eliminado el animal con ID " + idAnimal
                    + " del propietario " + nombre);
        }else{
            System.out.println("No se ha encontrado el animal con ID " + idAnimal);
        }
    }

    public void listarAnimales(){
        if(animales.isEmpty()){
            System.out.println("No hay animales registrados para el dueño " + nombre);
        }else{
            System.out.println("El dueño " + nombre + " tiene los siguientes animales registrados:");
            for(Animal a: animales) {
                System.out.println("\r\n" + a);
            }
        }
    }

    public int getTotalAnimales(){
        return animales.size();
    }

    public int getId(){
        return id;

    }
    public String getNombre() {
        return nombre;
    }
    public String getTelefono() {
        return telefono;
    }

    public ArrayList<Animal> getAnimales() {
        return animales;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Propietario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}

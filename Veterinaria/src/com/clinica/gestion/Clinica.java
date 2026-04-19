package com.clinica.gestion;

import com.clinica.modelo.Animal;
import com.clinica.modelo.Cita;
import com.clinica.modelo.Propietario;

import java.util.ArrayList;

public class Clinica {

    private String nombre;
    private ArrayList<Animal> animales;
    private ArrayList<Propietario> propietarios;
    private ArrayList<Cita> citas;

    public Clinica(String nombre){
        this.nombre = nombre;
        this.animales = new ArrayList<>();
        this.propietarios = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    public void registrarPropietario(Propietario propietario){
        for(Propietario p: propietarios){
            if(p.getId() == propietario.getId()){
                System.out.println("El propietario " + p.getNombre()
                        + " ya está registrado.");
                return;
            }
        }
        propietarios.add(propietario);
        System.out.println("Propietario registrado: " + propietario.getNombre());
    }

    public void registrarAnimal(Animal animal){
        animales.add(animal);
        System.out.println("Animal registrado: " + animal.getNombre()
                + " (" + animal.getTipoAnimal() + ")");
    }

    public void agendarCita(Cita cita){
        citas.add(cita);
        System.out.println("Cita agendada: " + cita.getFecha()
                + " — " + cita.getAnimal().getNombre()
                + " con Dr. " + cita.getVeterinario());
    }

    public Animal buscarAnimalPorId(int id){
        for(Animal a: animales){
            if(a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public Propietario buscarPropietarioPorId(int id){
        for(Propietario p: propietarios){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public Cita buscarCitaPorId(int id) {
        for (Cita c : citas) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public void listarAnimalesPorPropietario(int idPropietario) {
        Propietario p = buscarPropietarioPorId(idPropietario);
        if (p == null) {
            System.out.println("Propietario con ID " + idPropietario + " no encontrado.");
            return;
        }
        p.listarAnimales();   // Delegamos en el método de Propietario
    }

    public void listarAnimalesPorTipo(String tipo){
        System.out.println("── Animales de tipo: " + tipo + " ──");
        boolean encontrado = false;
        for(Animal a: animales){
            if(a.getTipoAnimal().equalsIgnoreCase(tipo)){
                System.out.println("  " + a);
                encontrado = true;
            }
        }
        if(!encontrado){
            System.out.println("  No hay animales de tipo " + tipo + ".");
        }
    }

    public void listarCitasPendientes(){
        System.out.println("── Citas pendientes ──");
        boolean encontrado = false;
        for(Cita c: citas){
            if(c.isRealizada() == false){
                System.out.println("  " + c);
                encontrado = true;
            }
        }
        if (!encontrado){
            System.out.println("  No hay citas pendientes.");
        }
    }
    
    public double calcularIngresosTotales(){
        double total = 0;
        for(Cita c: citas){
            if (c.isRealizada()) {
                total += c.getPrecio();
            }
        }
        System.out.println("Ingresos totales de " + nombre + ": " + total + "€");
        return total;
    }

    public String getNombre() {
        return nombre;
    }
}

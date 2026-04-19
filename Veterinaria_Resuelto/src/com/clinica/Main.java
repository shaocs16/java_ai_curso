package com.clinica;

import com.clinica.gestion.Clinica;
import com.clinica.modelo.Animal;
import com.clinica.modelo.Cita;
import com.clinica.modelo.Gato;
import com.clinica.modelo.Perro;
import com.clinica.modelo.Propietario;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Clinica clinica = new Clinica("Clínica Veterinaria El Pinar");

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = leerInt("Elige una opción: ");

            switch (opcion) {
                case 1: menuPropietarios(); break;
                case 2: menuAnimales();     break;
                case 3: menuCitas();        break;
                case 4: menuInformes();     break;
                case 0: System.out.println("¡Hasta luego!"); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    // ══════════════════════════════════════════════════════════════════════════
    // MENÚS
    // ══════════════════════════════════════════════════════════════════════════

    static void mostrarMenuPrincipal() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║  " + clinica.getNombre());
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  1. Gestión de propietarios      ║");
        System.out.println("║  2. Gestión de animales          ║");
        System.out.println("║  3. Gestión de citas             ║");
        System.out.println("║  4. Informes                     ║");
        System.out.println("║  0. Salir                        ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    // ── PROPIETARIOS ──────────────────────────────────────────────────────────

    static void menuPropietarios() {
        int op;
        do {
            System.out.println("\n── Propietarios ──");
            System.out.println("  1. Registrar propietario");
            System.out.println("  2. Buscar propietario por ID");
            System.out.println("  0. Volver");
            op = leerInt("Opción: ");

            switch (op) {
                case 1: registrarPropietario(); break;
                case 2: buscarPropietario();    break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }

    static void registrarPropietario() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        Propietario p = new Propietario(nombre, telefono);
        clinica.registrarPropietario(p);
    }

    static void buscarPropietario() {
        int id = leerInt("ID del propietario: ");
        Propietario p = clinica.buscarPropietarioPorId(id);
        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("No se encontró propietario con ID " + id + ".");
        }
    }

    // ── ANIMALES ──────────────────────────────────────────────────────────────

    static void menuAnimales() {
        int op;
        do {
            System.out.println("\n── Animales ──");
            System.out.println("  1. Registrar perro");
            System.out.println("  2. Registrar gato");
            System.out.println("  3. Buscar animal por ID");
            System.out.println("  0. Volver");
            op = leerInt("Opción: ");

            switch (op) {
                case 1: registrarPerro(); break;
                case 2: registrarGato();  break;
                case 3: buscarAnimal();   break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }

    static void registrarPerro() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        int edad      = leerInt("Edad (años): ");
        double peso   = leerDouble("Peso (kg): ");
        System.out.print("Raza: ");
        String raza   = scanner.nextLine();
        boolean adiestrado = leerBoolean("¿Está adiestrado? (s/n): ");

        int idProp = leerInt("ID del propietario (0 = sin dueño): ");
        Propietario p = clinica.buscarPropietarioPorId(idProp);

        Perro perro;
        if (p != null) {
            perro = new Perro(nombre, edad, peso, p, raza, adiestrado);
            p.registrarAnimal(perro);
        } else {
            perro = new Perro(nombre, edad, peso, raza, adiestrado);
            if (idProp != 0) System.out.println("Propietario no encontrado. Se registra sin dueño.");
        }

        clinica.registrarAnimal(perro);
    }

    static void registrarGato() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        int edad      = leerInt("Edad (años): ");
        double peso   = leerDouble("Peso (kg): ");
        boolean interior = leerBoolean("¿Es de interior? (s/n): ");
        System.out.print("Color de pelaje: ");
        String color  = scanner.nextLine();

        int idProp = leerInt("ID del propietario (0 = sin dueño): ");
        Propietario p = clinica.buscarPropietarioPorId(idProp);

        Gato gato;
        if (p != null) {
            gato = new Gato(nombre, edad, peso, p, interior, color);
            p.registrarAnimal(gato);
        } else {
            gato = new Gato(nombre, edad, peso, interior, color);
            if (idProp != 0) System.out.println("Propietario no encontrado. Se registra sin dueño.");
        }

        clinica.registrarAnimal(gato);
    }

    static void buscarAnimal() {
        int id = leerInt("ID del animal: ");
        Animal a = clinica.buscarAnimalPorId(id);
        if (a != null) {
            System.out.println(a);
        } else {
            System.out.println("No se encontró animal con ID " + id + ".");
        }
    }

    // ── CITAS ─────────────────────────────────────────────────────────────────

    static void menuCitas() {
        int op;
        do {
            System.out.println("\n── Citas ──");
            System.out.println("  1. Agendar cita");
            System.out.println("  2. Marcar cita como realizada");
            System.out.println("  0. Volver");
            op = leerInt("Opción: ");

            switch (op) {
                case 1: agendarCita();         break;
                case 2: marcarCitaRealizada(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }

    static void agendarCita() {
        int idAnimal = leerInt("ID del animal: ");
        Animal a = clinica.buscarAnimalPorId(idAnimal);
        if (a == null) {
            System.out.println("Animal no encontrado.");
            return;
        }

        System.out.print("Veterinario: ");
        String vet    = scanner.nextLine();
        System.out.print("Fecha (dd/MM/yyyy): ");
        String fecha  = scanner.nextLine();
        System.out.print("Motivo: ");
        String motivo = scanner.nextLine();
        double precio = leerDouble("Precio (€): ");

        Cita cita = new Cita(a, vet, fecha, motivo, precio);
        clinica.agendarCita(cita);
    }

    static void marcarCitaRealizada() {
        // Mostramos primero las pendientes para orientar al usuario
        clinica.listarCitasPendientes();
        int id = leerInt("ID de la cita a marcar como realizada: ");
        Cita c = clinica.buscarCitaPorId(id);
        if (c != null) {
            c.marcarRealizada();
        } else {
            System.out.println("No se encontró cita con ID " + id + ".");
        }
    }

    // ── INFORMES ──────────────────────────────────────────────────────────────

    static void menuInformes() {
        int op;
        do {
            System.out.println("\n── Informes ──");
            System.out.println("  1. Animales por tipo");
            System.out.println("  2. Citas pendientes");
            System.out.println("  3. Ingresos totales");
            System.out.println("  4. Animales de un propietario");
            System.out.println("  0. Volver");
            op = leerInt("Opción: ");

            switch (op) {
                case 1:
                    System.out.print("Tipo (Perro/Gato): ");
                    String tipo = scanner.nextLine();
                    clinica.listarAnimalesPorTipo(tipo);
                    break;
                case 2:
                    clinica.listarCitasPendientes();
                    break;
                case 3:
                    clinica.calcularIngresosTotales();
                    break;
                case 4:
                    int id = leerInt("ID del propietario: ");
                    clinica.listarAnimalesPorPropietario(id);
                    break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // UTILIDADES DE LECTURA
    // Centralizan la validación de entrada para no repetir try-catch en cada menú
    // ══════════════════════════════════════════════════════════════════════════

    static int leerInt(String mensaje) {
        int valor;
        while (true) {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Introduce un número entero válido.");
            }
        }
    }

    static double leerDouble(String mensaje) {
        double valor;
        while (true) {
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Introduce un número decimal válido (ej: 4.5).");
            }
        }
    }

    static boolean leerBoolean(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String resp = scanner.nextLine().trim().toLowerCase();
            if (resp.equals("s")) return true;
            if (resp.equals("n")) return false;
            System.out.println("  ⚠ Responde con 's' o 'n'.");
        }
    }
}

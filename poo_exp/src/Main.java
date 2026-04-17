import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal con menú interactivo por Scanner.
 * 
 * Aquí se demuestra cómo se COMUNICAN todas las clases:
 * 
 * 1. El usuario elige crear un Deportivo o Todoterreno.
 * 2. Para crearlo, primero se crean objetos de las clases componentes:
 *    - Se crea un Motor (Electrico o Gasolina) → POLIMORFISMO
 *    - Se crea una Tapiceria
 *    - Se crean las Ruedas
 * 3. Esos objetos se PASAN al constructor de Deportivo/Todoterreno → COMPOSICIÓN
 * 4. Deportivo/Todoterreno heredan de Vehiculo → HERENCIA
 * 5. Se guardan en una lista de tipo Vehiculo → POLIMORFISMO
 */
public class Main {

    private static ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n========================================");
            System.out.println("   GESTOR DE VEHICULOS - Menu Principal");
            System.out.println("========================================");
            System.out.println("1. Crear vehiculo");
            System.out.println("2. Listar vehiculos");
            System.out.println("3. Ver detalle de un vehiculo");
            System.out.println("0. Salir");
            System.out.print("Elige opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    crearVehiculo();
                    break;
                case 2:
                    listarVehiculos();
                    break;
                case 3:
                    verDetalle();
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    /**
     * PASO A PASO de cómo se comunican las clases al crear un vehículo:
     * 
     * 1) Se piden datos del Motor → se crea un objeto Electrico o Gasolina.
     *    (Electrico/Gasolina HEREDAN de Motor → relación IS-A)
     * 
     * 2) Se piden datos de Tapiceria → se crea un objeto Tapiceria.
     * 
     * 3) Se piden datos de Ruedas → se crea un array de objetos Rueda.
     * 
     * 4) Se pasan Motor, Tapiceria y Ruedas al constructor de Deportivo o Todoterreno.
     *    (Vehiculo CONTIENE Motor, Tapiceria y Ruedas → relación HAS-A / COMPOSICIÓN)
     * 
     * 5) Deportivo/Todoterreno HEREDAN de Vehiculo → relación IS-A.
     *    Al llamar super(...) en su constructor, inicializan la parte de Vehiculo.
     * 
     * 6) Se guarda en ArrayList<Vehiculo> → POLIMORFISMO.
     *    Un Deportivo o Todoterreno cabe en una variable de tipo Vehiculo.
     */
    private static void crearVehiculo() {
        System.out.println("\n--- CREAR VEHICULO ---");
        System.out.println("Tipo de vehiculo:");
        System.out.println("1. Deportivo");
        System.out.println("2. Todoterreno");
        System.out.print("Elige: ");
        int tipo = leerEntero();

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo no valido.");
            return;
        }

        // Datos comunes de Vehiculo
        System.out.print("Marca: ");
        String marca = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();

        // =============================================
        // PASO 1: Crear el MOTOR (Electrico o Gasolina)
        // =============================================
        // Aquí vemos POLIMORFISMO: la variable "motor" es de tipo Motor (padre),
        // pero el objeto real será Electrico o Gasolina (hijo).
        Motor motor = crearMotor();

        // =============================================
        // PASO 2: Crear la TAPICERIA
        // =============================================
        // Composición: creamos un objeto independiente que luego "vivirá"
        // dentro de Vehiculo.
        Tapiceria tapiceria = crearTapiceria();

        // =============================================
        // PASO 3: Crear las RUEDAS
        // =============================================
        Rueda[] ruedas = crearRuedas();

        // =============================================
        // PASO 4: Ensamblar todo en Deportivo o Todoterreno
        // =============================================
        // Motor, Tapiceria y Ruedas se PASAN al constructor.
        // Esto es COMPOSICIÓN: el vehículo "contiene" estos objetos.
        Vehiculo vehiculo;
        if (tipo == 1) {
            System.out.print("Velocidad maxima (km/h): ");
            int velMax = leerEntero();
            vehiculo = new Deportivo(marca, modelo, motor, tapiceria, ruedas, velMax);
        } else {
            System.out.print("Tiene traccion 4x4? (s/n): ");
            boolean t4x4 = sc.nextLine().equalsIgnoreCase("s");
            vehiculo = new Todoterreno(marca, modelo, motor, tapiceria, ruedas, t4x4);
        }

        // PASO 5: Se guarda como Vehiculo (POLIMORFISMO)
        vehiculos.add(vehiculo);
        System.out.println("\nVehiculo creado con exito!");
        System.out.println(vehiculo);
    }

    /**
     * Crea un Motor pidiendo datos por Scanner.
     * Retorna Motor (tipo padre) pero el objeto real es Electrico o Gasolina.
     * Esto es POLIMORFISMO.
     */
    private static Motor crearMotor() {
        System.out.println("\n  --- Configurar Motor ---");
        System.out.println("  Tipo de motor:");
        System.out.println("  1. Electrico");
        System.out.println("  2. Gasolina");
        System.out.print("  Elige: ");
        int tipoMotor = leerEntero();

        System.out.print("  Potencia (CV): ");
        int potencia = leerEntero();

        if (tipoMotor == 1) {
            System.out.print("  Autonomia (km): ");
            int autonomia = leerEntero();
            // Creamos un Electrico, pero lo devolvemos como Motor (polimorfismo)
            return new Electrico(potencia, autonomia);
        } else {
            System.out.print("  Cilindrada (cc): ");
            int cilindrada = leerEntero();
            // Creamos un Gasolina, pero lo devolvemos como Motor (polimorfismo)
            return new Gasolina(potencia, cilindrada);
        }
    }

    /**
     * Crea una Tapiceria pidiendo datos por Scanner.
     */
    private static Tapiceria crearTapiceria() {
        System.out.println("\n  --- Configurar Tapiceria ---");
        System.out.print("  Material (cuero/tela/alcantara): ");
        String material = sc.nextLine();
        System.out.print("  Color: ");
        String color = sc.nextLine();
        return new Tapiceria(material, color);
    }

    /**
     * Crea un array de Ruedas pidiendo datos por Scanner.
     */
    private static Rueda[] crearRuedas() {
        System.out.println("\n  --- Configurar Ruedas ---");
        System.out.print("  Numero de ruedas: ");
        int numRuedas = leerEntero();
        Rueda[] ruedas = new Rueda[numRuedas];

        System.out.print("  Mismas ruedas para todas? (s/n): ");
        boolean iguales = sc.nextLine().equalsIgnoreCase("s");

        if (iguales) {
            System.out.print("  Marca de la rueda: ");
            String marca = sc.nextLine();
            System.out.print("  Pulgadas: ");
            int pulgadas = leerEntero();
            for (int i = 0; i < numRuedas; i++) {
                ruedas[i] = new Rueda(marca, pulgadas);
            }
        } else {
            for (int i = 0; i < numRuedas; i++) {
                System.out.println("  Rueda " + (i + 1) + ":");
                System.out.print("    Marca: ");
                String marca = sc.nextLine();
                System.out.print("    Pulgadas: ");
                int pulgadas = leerEntero();
                ruedas[i] = new Rueda(marca, pulgadas);
            }
        }
        return ruedas;
    }

    /**
     * Lista todos los vehículos creados.
     * Aquí se ve POLIMORFISMO: recorremos una lista de Vehiculo,
     * pero cada elemento puede ser Deportivo o Todoterreno.
     * Al llamar toString(), Java ejecuta la versión correcta.
     */
    private static void listarVehiculos() {
        System.out.println("\n--- LISTA DE VEHICULOS ---");
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos creados.");
            return;
        }
        for (int i = 0; i < vehiculos.size(); i++) {
            // vehiculos.get(i) es de tipo Vehiculo, pero en realidad
            // es un Deportivo o Todoterreno → POLIMORFISMO
            System.out.println("\n[" + (i + 1) + "] " + vehiculos.get(i).getTipoVehiculo()
                    + " - " + vehiculos.get(i).getMarca() + " " + vehiculos.get(i).getModelo());
        }
    }

    /**
     * Muestra el detalle completo de un vehículo.
     * Demuestra cómo desde un Vehiculo accedemos a Motor, Tapiceria y Ruedas
     * a través de los getters (COMPOSICIÓN).
     */
    private static void verDetalle() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos creados.");
            return;
        }
        listarVehiculos();
        System.out.print("\nElige numero de vehiculo: ");
        int num = leerEntero();
        if (num < 1 || num > vehiculos.size()) {
            System.out.println("Numero no valido.");
            return;
        }

        Vehiculo v = vehiculos.get(num - 1);
        System.out.println("\n" + v); // Llama a toString() → POLIMORFISMO

        // Acceso por COMPOSICIÓN: desde el vehículo accedemos a sus componentes
        System.out.println("\n  >> Acceso por composicion:");
        System.out.println("     Motor tipo: " + v.getMotor().getTipoMotor()); // POLIMORFISMO
        System.out.println("     Motor potencia: " + v.getMotor().getPotenciaCV() + " CV");
        System.out.println("     Tapiceria: " + v.getTapiceria().getMaterial() + " " + v.getTapiceria().getColor());
        System.out.println("     Numero de ruedas: " + v.getRuedas().length);

        // Si el motor es eléctrico, podemos hacer CASTING para acceder a atributos propios
        if (v.getMotor() instanceof Electrico) {
            Electrico e = (Electrico) v.getMotor(); // CASTING (downcasting)
            System.out.println("     Autonomia: " + e.getAutonomiaKm() + " km");
        } else if (v.getMotor() instanceof Gasolina) {
            Gasolina g = (Gasolina) v.getMotor(); // CASTING (downcasting)
            System.out.println("     Cilindrada: " + g.getCilindrada() + " cc");
        }
    }

    /**
     * Utilidad para leer un entero de forma segura.
     */
    private static int leerEntero() {
        while (true) {
            try {
                String linea = sc.nextLine();
                return Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                System.out.print("Introduce un numero valido: ");
            }
        }
    }
}

# UniManager — Sistema de Gestión Universitaria

> **Proyecto educativo Java de nivel medio-avanzado**  
> Diseñado para que un estudiante universitario aprenda los fundamentos de la **Programación Orientada a Objetos (POO)** a través de un ejemplo real y funcional.

---

## 📚 ¿Qué aprenderás con este proyecto?

| Concepto POO           | Dónde se aplica en el proyecto                                  |
|------------------------|-----------------------------------------------------------------|
| **Clase Abstracta**    | `Persona` — no instanciable, define la plantilla común          |
| **Interface**          | `Evaluable`, `Reportable` — contratos de comportamiento         |
| **Herencia**           | `Estudiante extends Persona`, `Profesor extends Persona`        |
| **Polimorfismo**       | `List<Persona>` que contiene Estudiantes y Profesores           |
| **Encapsulamiento**    | Campos `private` + getters/setters en todas las clases          |
| **Sobrescritura**      | `@Override` de `mostrarRol()`, `toString()`, `generarReporte()` |
| **Sobrecarga**         | Dos constructores de `Curso` con diferente firma                |
| **Array fijo**         | `notasSemestre[]` en `Estudiante`, `materias[]` en `Profesor`   |
| **ArrayList dinámico** | Cursos inscritos, estudiantes del sistema, cursos disponibles   |
| **instanceof / Cast**  | Filtrado seguro de la lista polimórfica en `Universidad`        |
| **Manejo de Scanner**  | Lectura robusta con `try-catch` en `MenuService`                |

---

## 🏗️ Arquitectura del Proyecto

```
mid_level/
└── src/
    └── unimanager/
        ├── Main.java                      ← Punto de entrada (método main)
        ├── interfaces/
        │   ├── Evaluable.java             ← Interface: calcularPromedio(), estaAprobado()
        │   └── Reportable.java            ← Interface: generarReporte()
        ├── model/
        │   ├── Persona.java               ← Clase ABSTRACTA (base de la jerarquía)
        │   ├── Estudiante.java            ← extends Persona, implements Evaluable
        │   ├── Profesor.java              ← extends Persona, implements Reportable
        │   └── Curso.java                 ← POJO con sobrecarga de constructores
        └── service/
            ├── Universidad.java           ← Lógica de negocio central
            └── MenuService.java           ← Interacción con el usuario (Scanner)
```

### Diagrama de herencia e interfaces

```
             «interface»          «interface»
             Evaluable            Reportable
                 ▲                    ▲
                 │                    │
          ┌──────────────────────────────────────┐
          │         «abstract»                   │
          │           Persona                    │
          │  - id, nombre, edad                  │
          │  + mostrarRol() [abstract]            │
          │  + toString()                         │
          └──────────────┬───────────────────────┘
                         │
            ┌────────────┴─────────────┐
            │                          │
      ┌─────────────┐          ┌───────────────┐
      │ Estudiante  │          │    Profesor   │
      │ implements  │          │  implements   │
      │  Evaluable  │          │  Reportable   │
      │             │          │               │
      │ - notas[]   │          │ - materias[]  │
      │ - cursos    │          │ - estudiantes │
      │   (ArrayList│          │   (ArrayList) │
      └─────────────┘          └───────────────┘

      Curso (no hereda de Persona — relación "TIENE", no "ES")
```

---

## 🔑 Conceptos Clave Explicados

### 1. Clase Abstracta vs Interface

```
                  CLASE ABSTRACTA              INTERFACE
                  ───────────────              ─────────
Instanciar:       ❌ NO                        ❌ NO
Herencia:         1 sola (extends)             Múltiples (implements)
Estado (campos):  ✅ SÍ (con valores)          Solo constantes (static final)
Código:           ✅ Métodos concretos          Solo default methods (Java 8+)
Cuándo usarla:    Entidades relacionadas        Capacidades transversales
Ejemplo aquí:     Persona (todos son personas  Evaluable (ser calificable
                  y comparten id, nombre)       es una capacidad, no tipo)
```

### 2. Array vs ArrayList

```java
// ARRAY — tamaño FIJO, definido al crear
double[] notas = new double[10];   // Siempre 10 espacios, usa memoria fija
notas[0] = 8.5;                    // Acceso directo por índice O(1)
notas.length;                      // Tamaño total del array

// ARRAYLIST — tamaño DINÁMICO, crece según necesidad
ArrayList<String> cursos = new ArrayList<>();
cursos.add("Matemáticas");         // Agrega al final, crece si es necesario
cursos.size();                     // Cantidad de elementos actuales
cursos.contains("Física");         // Busca en la lista O(n)
cursos.remove("Matemáticas");      // Elimina la primera ocurrencia
```

| Aspecto         | Array `T[]`           | ArrayList `<T>`              |
|-----------------|-----------------------|------------------------------|
| Tamaño          | Fijo                  | Dinámico                     |
| Sintaxis tipo   | `int[]`, `String[]`   | `ArrayList<Integer>`, etc.   |
| Métodos útiles  | `.length`             | `.size()`, `.add()`, etc.    |
| Rendimiento     | Más rápido            | Ligeramente más lento        |
| Cuándo usarlo   | Tamaño conocido previamente | Tamaño desconocido o variable |

### 3. Polimorfismo con `instanceof` y Casting

```java
// Una sola lista guarda Estudiantes Y Profesores
List<Persona> personas = new ArrayList<>();
personas.add(new Estudiante("E001", "Ana", 20, "Informática"));
personas.add(new Profesor("P001", "Dr. Gómez", 45, "Ciencias", 15, materias));

// POLIMORFISMO: el mismo mensaje, diferente comportamiento
for (Persona p : personas) {
    p.mostrarRol();  // Cada tipo ejecuta SU versión del método
}

// INSTANCEOF + CAST: cuando necesitamos comportamiento específico
for (Persona p : personas) {
    if (p instanceof Estudiante) {       // ¿Es realmente un Estudiante?
        Estudiante est = (Estudiante) p; // Downcast seguro
        est.agregarNota(8.5);            // Método solo disponible en Estudiante
    }
}
```

### 4. Sobrecarga de Constructores (en `Curso`)

```java
// Constructor 1 — básico (sin descripción)
Curso c1 = new Curso("MAT101", "Cálculo I", 30);

// Constructor 2 — completo (con descripción)
Curso c2 = new Curso("MAT101", "Cálculo I", 30, "Fundamentos del cálculo");

// Java sabe cuál llamar por el NÚMERO DE PARÁMETROS.
// El constructor 1 internamente llama al 2 con descripción por defecto.
// Esto es "constructor chaining" usando this(...)
```

### 5. Scanner — Lectura segura del teclado

```java
Scanner scanner = new Scanner(System.in);

// ⚠️ TRAMPA COMÚN: nextInt() no consume el salto de línea
int numero = scanner.nextInt();
scanner.nextLine();  // ← OBLIGATORIO para limpiar el buffer

// Lectura segura con try-catch
try {
    int valor = scanner.nextInt();
    scanner.nextLine(); // limpiar buffer
} catch (InputMismatchException e) {
    scanner.nextLine(); // limpiar el valor incorrecto del buffer
    System.out.println("ERROR: Ingresa un número válido");
}
```

---

## 🚀 Cómo Compilar y Ejecutar

### Requisitos
- **JDK 11 o superior** instalado
- Terminal (CMD, PowerShell, o la terminal de tu IDE)

### Opción A — Desde la terminal (sin IDE)

```bash
# 1. Navega a la raíz del proyecto
cd e:\java\CursoIa\mid_level

# 2. Compila todos los archivos .java recursivamente
javac -cp src -d out -sourcepath src src/unimanager/Main.java

# 3. Ejecuta el programa
java -cp out unimanager.Main
```

### Opción B — Desde IntelliJ IDEA (recomendado)

1. Abre IntelliJ IDEA → `File → Open` → selecciona la carpeta `mid_level`
2. Click derecho en `src` → `Mark Directory as → Sources Root`
3. Abre `Main.java` y presiona el botón **▶ Run** (o `Shift+F10`)

### Opción C — Desde VS Code

1. Instala la extensión **Extension Pack for Java**
2. Abre la carpeta `mid_level`
3. Abre `Main.java` → aparecerá `Run | Debug` sobre el método main
4. Click en `Run`

---

## 🎮 Flujo de Uso del Sistema

```
╔══════════════════════════════════════════════════╗
║         UniManager — Sistema Universitario       ║
║              Proyecto Educativo Java OOP         ║
╚══════════════════════════════════════════════════╝

╔═══════════════════════════════════╗
║          MENÚ PRINCIPAL           ║
╠═══════════════════════════════════╣
║  1. Gestión de Estudiantes        ║
║  2. Gestión de Profesores         ║
║  3. Gestión de Cursos             ║
║  4. Reportes                      ║
║  0. Salir                         ║
╚═══════════════════════════════════╝
```

### Flujo de prueba recomendado

1. **Agregar 2-3 estudiantes** (opción 1 → 1)
2. **Agregar 1-2 profesores** (opción 2 → 1) con 2-3 materias cada uno
3. **Crear 2-3 cursos** (opción 3 → 1)
4. **Inscribir estudiantes en cursos** (opción 3 → 4)
5. **Agregar notas** a los estudiantes (opción 1 → 4)
6. **Ver el Top de estudiantes** (opción 4 → 2) — verás el ordenamiento burbuja
7. **Generar reporte de un profesor** (opción 2 → 3) — verás la interface Reportable

---

## 📁 Descripción de Archivos

| Archivo | Tipo | Propósito principal |
|---|---|---|
| `Main.java` | Clase concreta | Punto de entrada, método `main()` |
| `Evaluable.java` | Interface | Contrato: `calcularPromedio()`, `estaAprobado()` |
| `Reportable.java` | Interface | Contrato: `generarReporte()` |
| `Persona.java` | Clase abstracta | Plantilla base: `id`, `nombre`, `edad`, `mostrarRol()` abstracto |
| `Estudiante.java` | Clase concreta | Hereda Persona, implementa Evaluable, usa `array` y `ArrayList` |
| `Profesor.java` | Clase concreta | Hereda Persona, implementa Reportable, copia defensiva de array |
| `Curso.java` | Clase concreta | Sobrecarga de constructores, gestión de inscritos |
| `Universidad.java` | Servicio | Repositorio central, polimorfismo, instanceof, bubble sort |
| `MenuService.java` | Servicio | Scanner robusto con try-catch, submenús anidados |

---

## 🧠 Principios SOLID aplicados (nivel introductorio)

- **S** — *Single Responsibility*: cada clase tiene UN propósito claro
- **O** — *Open/Closed*: agregar un nuevo tipo de Persona (ej: Coordinador) no rompe el código existente
- **L** — *Liskov Substitution*: cualquier Estudiante o Profesor puede usarse donde se espera una Persona
- **I** — *Interface Segregation*: dos interfaces pequeñas (Evaluable, Reportable) en vez de una grande
- **D** — *Dependency Injection*: MenuService recibe Universidad en el constructor, no la crea internamente

---

## 💡 Ideas para extender el proyecto (ejercicios sugeridos)

1. **Agregar la clase `Coordinador`** que extienda `Persona` e implemente AMBAS interfaces (`Evaluable` y `Reportable`)
2. **Implementar un método `buscarPorNombre()`** que recorra el `ArrayList` y filtre por nombre parcial
3. **Agregar un `enum Turno`** con valores `MAÑANA`, `TARDE`, `NOCHE` y asignarlo a cada `Curso`
4. **Implementar `generarReporte()`** en `Estudiante` también (hacer que implemente ambas interfaces)
5. **Persistencia simple**: guardar y cargar los datos desde un archivo `.txt` con `FileWriter`/`BufferedReader`
6. **Ordenar la lista de estudiantes** usando `Collections.sort()` con un `Comparator` en vez del burbuja manual

---

## ✍️ Autor

Proyecto educativo diseñado para enseñar POO en Java a nivel universitario.  
Todos los comentarios en el código son intencionalmente detallados para facilitar el aprendizaje.

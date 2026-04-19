# 🏥 Sistema de Gestión de Clínica Veterinaria
### Ejercicio de Programación Orientada a Objetos en Java

---

## 📋 Descripción

Sistema de gestión para una clínica veterinaria que permite registrar propietarios, animales y citas médicas.  
Este ejercicio está diseñado para practicar los pilares fundamentales de la **Programación Orientada a Objetos** en Java a nivel universitario.

---

## 🎯 Objetivos de Aprendizaje

Al completar este ejercicio, el estudiante habrá practicado:

| Concepto POO      | Dónde se aplica                                        |
|-------------------|--------------------------------------------------------|
| **Herencia**      | `Perro` y `Gato` extienden la clase abstracta `Animal`|
| **Abstracción**   | Clase `Animal` con métodos abstractos                  |
| **Polimorfismo**  | `getTipoAnimal()` y `getSonido()` con distinto comportamiento |
| **Encapsulación** | Atributos `private` con getters/setters                |
| **Composición**   | `Propietario` dentro de `Animal`, `Animal` dentro de `Cita` |
| **Colecciones**   | Uso de `ArrayList` en `Clinica` y `Propietario`        |
| **ID Autoincremental** | Contador `static` en cada clase                  |

> ⚠️ **Restricciones del ejercicio:** No se permite el uso de `interfaces` ni `enum`.

---

## 🗂️ Estructura del Proyecto

```
ClinicaVeterinaria/
│
├── README.md
└── src/
    └── com/
        └── clinica/
            ├── modelo/
            │   ├── Animal.java          ← Clase abstracta base
            │   ├── Perro.java           ← Extiende Animal
            │   ├── Gato.java            ← Extiende Animal
            │   ├── Propietario.java     ← Gestiona sus animales
            │   └── Cita.java            ← Consulta médica
            ├── gestion/
            │   └── Clinica.java         ← Clase principal de gestión
            └── Main.java                ← Punto de entrada del programa
```

---

## 📦 Descripción de Clases

### `Animal` *(abstracta)*
Clase base que representa cualquier animal registrado en la clínica.

**Atributos:**
- `id` — identificador único (autoincremental con `static`)
- `nombre` — nombre del animal
- `edad` — edad en años
- `peso` — peso en kilogramos
- `propietario` — referencia al objeto `Propietario`

**Métodos abstractos:**
```java
public abstract String getTipoAnimal();  // "Perro" / "Gato"
public abstract String getSonido();      // "Guau" / "Miau"
```

---

### `Perro` *(extiende `Animal`)*
**Atributos propios:** `raza` (String), `estaAdiestrado` (boolean)

```java
getTipoAnimal() → "Perro"
getSonido()     → "Guau"
```

---

### `Gato` *(extiende `Animal`)*
**Atributos propios:** `esDeInterior` (boolean), `colorPelaje` (String)

```java
getTipoAnimal() → "Gato"
getSonido()     → "Miau"
```

---

### `Propietario`
Gestiona la lista de animales que le pertenecen.

**Atributos:** `id`, `nombre`, `telefono`, `animales (ArrayList<Animal>)`

**Métodos clave:**
```java
registrarAnimal(Animal a)
eliminarAnimal(int idAnimal)
listarAnimales()
getTotalAnimales() → int
```

---

### `Cita`
Representa una consulta médica en la clínica.

**Atributos:** `id`, `animal`, `veterinario`, `fecha`, `motivo`, `precio`, `realizada`

**Métodos clave:**
```java
marcarRealizada()   // cambia realizada a true
toString()          // info completa de la cita
```

---

### `Clinica` *(gestión central)*

**Atributos:** `nombre`, `animales (ArrayList)`, `propietarios (ArrayList)`, `citas (ArrayList)`

**Métodos obligatorios:**

| Método | Descripción |
|--------|-------------|
| `registrarPropietario(Propietario p)` | Añade si no existe ya |
| `registrarAnimal(Animal a)` | Añade animal a la clínica |
| `agendarCita(Cita c)` | Agenda una nueva cita |
| `buscarAnimalPorId(int id)` | Devuelve `Animal` o `null` |
| `buscarPropietarioPorId(int id)` | Devuelve `Propietario` o `null` |
| `listarAnimalesPorTipo(String tipo)` | Filtra por `"Perro"` o `"Gato"` |
| `listarCitasPendientes()` | Muestra citas con `realizada == false` |
| `calcularIngresosTotales()` | Suma precios de citas realizadas |
| `listarAnimalesPorPropietario(int id)` | Lista animales de un propietario |

---

## ▶️ Requisitos del `Main`

El método `main` debe demostrar el funcionamiento completo:

1. ✅ Crear al menos **2 propietarios**
2. ✅ Crear al menos **3 animales** (mezcla de perros y gatos) asignados a propietarios
3. ✅ Registrar todo en la clínica
4. ✅ Crear **4 citas** (2 para perros, 2 para gatos)
5. ✅ Marcar **2 citas** como realizadas
6. ✅ Mostrar por consola:
   - Todos los animales de tipo `"Perro"`
   - Citas pendientes
   - Ingresos totales de la clínica
   - Animales de un propietario específico

---

## 💡 Pistas de Implementación

### IDs autoincrementales
Cada clase que tenga `id` debe usar un contador estático:
```java
private static int contador = 0;

public Animal(String nombre, ...) {
    this.id = ++contador;
    ...
}
```

### Filtrar animales por tipo (polimorfismo)
En `Clinica`, aprovecha el método polimórfico `getTipoAnimal()`:
```java
public void listarAnimalesPorTipo(String tipo) {
    for (Animal a : animales) {
        if (a.getTipoAnimal().equalsIgnoreCase(tipo)) {
            System.out.println(a);
        }
    }
}
```

### Buscar por ID
Recorre la lista y compara con `getId()`:
```java
public Animal buscarAnimalPorId(int id) {
    for (Animal a : animales) {
        if (a.getId() == id) return a;
    }
    return null;
}
```

---

## 📊 Diagrama de Clases (simplificado)

```
          ┌──────────────────┐
          │   Animal (abs)   │
          │──────────────────│
          │ - id             │
          │ - nombre         │
          │ - edad           │
          │ - peso           │
          │ - propietario ───┼──────────────────────────┐
          │──────────────────│                          │
          │ + getTipoAnimal()│                    ┌─────▼──────────┐
          │ + getSonido()    │                    │   Propietario  │
          └────────┬─────────┘                    │────────────────│
                   │                              │ - id           │
          ┌────────┴────────┐                     │ - nombre       │
          │                 │                     │ - telefono     │
    ┌─────▼──────┐   ┌──────▼──────┐              │ - animales[]   │
    │   Perro    │   │    Gato     │              └────────────────┘
    │────────────│   │─────────────│
    │ - raza     │   │ - interior  │
    │ - adiestrado│  │ - colorPelaje│
    └────────────┘   └─────────────┘

          ┌──────────────────────────────────────────────┐
          │                  Clinica                     │
          │──────────────────────────────────────────────│
          │ - nombre                                     │
          │ - animales: ArrayList<Animal>                │
          │ - propietarios: ArrayList<Propietario>       │
          │ - citas: ArrayList<Cita>                     │
          └──────────────────────────────────────────────┘

          ┌──────────────────┐
          │      Cita        │
          │──────────────────│
          │ - id             │
          │ - animal ────────┼──► Animal
          │ - veterinario    │
          │ - fecha          │
          │ - motivo         │
          │ - precio         │
          │ - realizada      │
          └──────────────────┘
```

---

## 📝 Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| Clase abstracta `Animal` correctamente implementada | 20 |
| Herencia correcta en `Perro` y `Gato` | 15 |
| Polimorfismo funcional (`getTipoAnimal`, `getSonido`) | 15 |
| Composición `Propietario` ↔ `Animal` ↔ `Cita` | 15 |
| Métodos de `Clinica` correctamente implementados | 20 |
| `Main` que demuestra todos los puntos requeridos | 10 |
| Código limpio, comentado y bien estructurado | 5 |
| **Total** | **100** |

---

## 🛠️ Tecnologías

- **Lenguaje:** Java 11+
- **IDE recomendado:** IntelliJ IDEA / Eclipse / VS Code
- **Sin librerías externas** — solo Java estándar

---

*Ejercicio de nivel universitario — Programación Orientada a Objetos*

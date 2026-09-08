# Reto 1: Gestor de biblioteca

## Enunciado

Construir un sistema de gestión de biblioteca usando clases, en tres niveles progresivos.

### Nivel 1
`agregar_libro(titulo, autor)` y `listar_libros()` (ordenado alfabéticamente).

**Comprobación:** si alguien modifica lo que devuelve `listar_libros()`, el estado interno de la biblioteca no debe cambiar.

### Nivel 2
Añadir `Revista` y `DVD` — cada uno con sus propios campos y su propio plazo de préstamo (Libro=15 días, Revista=7, DVD=3). `prestar(item)` es un único método que sirve para los tres tipos y calcula la fecha de devolución según el tipo del item.

**Comprobación:** añadir un tipo nuevo de item con su propio plazo no debe requerir tocar ni una línea del método `prestar()`.

### Nivel 3
Un usuario puede pedir que le avisen cuando un item prestado quede libre.

**Comprobación:** la clase `Biblioteca` no puede tener ningún código que sepa cómo se le notifica a un usuario.

## Stack

- Java 17
- Maven

## Estructura del proyecto

- `src/main/java/org/example/`
    - `modelo/` → ItemBiblioteca (clase base), Libro, Revista, DVD
    - `notificaciones/` → IVigilar (interfaz), Usuario
    - `servicio/` → Biblioteca (lógica de negocio), Servicios (orquestación/menú)
    - `consola/` → InteraccionConsola (entrada/salida de consola)


## Decisiones de diseño

- **Encapsulación (nivel 1):** `listarItems()` devuelve una copia de la lista interna (`new ArrayList<>(items)`), nunca la referencia real, para que modificarla desde fuera no afecte al estado de `Biblioteca`.

- **Polimorfismo (nivel 2):** `ItemBiblioteca` es una clase abstracta con el método abstracto `plazoPrestamos()`. `Biblioteca.prestar(item)` llama a `item.plazoPrestamos()` sin ningún `instanceof`, así que añadir un tipo nuevo (por ejemplo `CD`) solo requiere crear la clase, heredando de `ItemBiblioteca` — sin tocar `prestar()`.

- **Observer (nivel 3):** cada `ItemBiblioteca` mantiene su propia lista de interesados (`IVigilar`). `Usuario implements IVigilar`, y sabe cómo reaccionar al aviso (`actualizar(item)`). `Biblioteca` solo dispara el proceso (`item.avisarItemDevuelto()`) sin saber cómo se notifica — ese conocimiento vive entre `ItemBiblioteca` y `Usuario`.

## Cómo ejecutar

```bash
cd RetoBiblioteca
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

(o ejecutar `Main.java` directamente desde IntelliJ)
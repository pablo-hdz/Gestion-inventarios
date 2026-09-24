# Gestión de Inventarios - SOFT-10

Proyecto grupal del curso SOFT-10 (Estructuras de Datos), CENFOTEC.
Primer avance: implementación de ListaProductos como lista enlazada simple.

## Estructura
- `Producto`: entidad con nombre, precio, categoria, fechaVencimiento, cantidad y listaImagenes.
- `Nodo`: nodo de la lista enlazada, contiene un Producto y referencia al siguiente nodo.
- `ListaProductos`: lista enlazada simple con inserción al inicio/final, modificación, eliminación y reporte de costos.
- `Menu`: clase funcional con el menú de consola.
- `Main`: contiene el método main() que invoca el menú.

## Cómo ejecutar
```
javac src/main/java/com/cenfotec/inventario/*.java -d bin
java -cp bin com.cenfotec.inventario.Main
```

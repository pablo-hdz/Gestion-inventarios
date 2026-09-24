package com.cenfotec.inventario;

/**
 * Lista enlazada simple de Productos. Implementa las operaciones habituales:
 * inserción al inicio y al final, modificación, eliminación y un reporte
 * de costos totales.
 */
public class ListaProductos {

    private Nodo cabeza;
    private int tamanio;

    public ListaProductos() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Inserta un producto al inicio de la lista.
     */
    public void insertarAlInicio(Producto producto) {
        Nodo nuevo = new Nodo(producto);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        tamanio++;
    }

    /**
     * Inserta un producto al final de la lista.
     */
    public void insertarAlFinal(Producto producto) {
        Nodo nuevo = new Nodo(producto);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamanio++;
    }

    /**
     * Busca un producto por nombre. Retorna null si no se encuentra.
     */
    public Producto buscarPorNombre(String nombre) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getProducto().getNombre().equalsIgnoreCase(nombre)) {
                return actual.getProducto();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    /**
     * Modifica los datos de un producto existente (identificado por nombre).
     * Retorna true si se encontró y modificó, false en caso contrario.
     */
    public boolean modificarProducto(String nombre, double nuevoPrecio, String nuevaCategoria,
                                      java.time.LocalDate nuevaFechaVencimiento, int nuevaCantidad) {
        Producto producto = buscarPorNombre(nombre);
        if (producto == null) {
            return false;
        }
        producto.setPrecio(nuevoPrecio);
        producto.setCategoria(nuevaCategoria);
        producto.setFechaVencimiento(nuevaFechaVencimiento);
        producto.setCantidad(nuevaCantidad);
        return true;
    }

    /**
     * Agrega una imagen a un producto existente (identificado por nombre).
     * Retorna true si se encontró el producto, false en caso contrario.
     */
    public boolean agregarImagenAProducto(String nombre, String rutaImagen) {
        Producto producto = buscarPorNombre(nombre);
        if (producto == null) {
            return false;
        }
        producto.agregarImagen(rutaImagen);
        return true;
    }

    /**
     * Elimina un producto de la lista por nombre.
     * Retorna true si se eliminó, false si no se encontró.
     */
    public boolean eliminarProducto(String nombre) {
        if (estaVacia()) {
            return false;
        }

        if (cabeza.getProducto().getNombre().equalsIgnoreCase(nombre)) {
            cabeza = cabeza.getSiguiente();
            tamanio--;
            return true;
        }

        Nodo anterior = cabeza;
        Nodo actual = cabeza.getSiguiente();
        while (actual != null) {
            if (actual.getProducto().getNombre().equalsIgnoreCase(nombre)) {
                anterior.setSiguiente(actual.getSiguiente());
                tamanio--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }

    /**
     * Recorre la lista e imprime un reporte de costos totales por producto
     * y el costo total acumulado de toda la lista.
     */
    public void imprimirReporteCostos() {
        if (estaVacia()) {
            System.out.println("La lista de productos está vacía.");
            return;
        }

        System.out.println("=== Reporte de costos ===");
        double costoAcumulado = 0.0;
        Nodo actual = cabeza;
        while (actual != null) {
            Producto p = actual.getProducto();
            double costoProducto = p.calcularCostoTotal();
            System.out.printf("Producto: %-20s Cantidad: %-5d Precio unitario: %10.2f Costo total: %10.2f%n",
                    p.getNombre(), p.getCantidad(), p.getPrecio(), costoProducto);
            costoAcumulado += costoProducto;
            actual = actual.getSiguiente();
        }
        System.out.printf("Costo total acumulado de la lista: %.2f%n", costoAcumulado);
    }

    /**
     * Imprime todos los productos de la lista (sin el detalle de costos).
     */
    public void imprimirProductos() {
        if (estaVacia()) {
            System.out.println("La lista de productos está vacía.");
            return;
        }
        Nodo actual = cabeza;
        int i = 1;
        while (actual != null) {
            System.out.println(i + ". " + actual.getProducto());
            actual = actual.getSiguiente();
            i++;
        }
    }
}

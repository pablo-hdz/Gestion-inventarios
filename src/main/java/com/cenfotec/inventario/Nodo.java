package com.cenfotec.inventario;

/**
 * Nodo de la lista enlazada simple. Almacena un Producto y la referencia
 * al siguiente nodo de la lista.
 */
public class Nodo {

    private Producto producto;
    private Nodo siguiente;

    public Nodo(Producto producto) {
        this.producto = producto;
        this.siguiente = null;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

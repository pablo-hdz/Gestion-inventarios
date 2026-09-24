package com.cenfotec.inventario;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representa un producto del sistema de ventas en línea.
 * La cantidad se interpreta según el contexto: unidades en el carrito del
 * cliente o unidades en el inventario de la tienda (según se use en avances
 * posteriores del proyecto).
 */
public class Producto {

    private String nombre;
    private double precio;
    private String categoria;
    private LocalDate fechaVencimiento; // puede ser null si no aplica
    private int cantidad;
    private ArrayList<String> listaImagenes;

    public Producto(String nombre, double precio, String categoria, LocalDate fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.listaImagenes = new ArrayList<>();
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    /**
     * Agrega una ruta de imagen a la lista de imágenes del producto.
     */
    public void agregarImagen(String rutaImagen) {
        this.listaImagenes.add(rutaImagen);
    }

    /**
     * Calcula el costo total de este producto según su cantidad (precio * cantidad).
     */
    public double calcularCostoTotal() {
        return precio * cantidad;
    }

    @Override
    public String toString() {
        String vencimiento = (fechaVencimiento != null) ? fechaVencimiento.toString() : "N/A";
        return String.format(
                "Nombre: %-20s | Precio: %10.2f | Categoria: %-15s | Vence: %-10s | Cantidad: %-5d | Imagenes: %d | Costo total: %.2f",
                nombre, precio, categoria, vencimiento, cantidad, listaImagenes.size(), calcularCostoTotal()
        );
    }
}

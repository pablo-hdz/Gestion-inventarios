package com.cenfotec.inventario;

/**
 * Punto de entrada de la aplicación. Invoca el menú de consola que
 * permite interactuar con la ListaProductos.
 */
public class Main {

    public static void main(String[] args) {
        ListaProductos listaProductos = new ListaProductos();
        Menu menu = new Menu(listaProductos);
        menu.mostrar();
    }
}

package com.cenfotec.inventario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Clase funcional con el menú de consola para interactuar con la ListaProductos.
 */
public class Menu {

    private final ListaProductos listaProductos;
    private final Scanner scanner;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Menu(ListaProductos listaProductos) {
        this.listaProductos = listaProductos;
        this.scanner = new Scanner(System.in);
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ - GESTIÓN DE INVENTARIOS =====");
            System.out.println("1. Insertar producto al inicio");
            System.out.println("2. Insertar producto al final");
            System.out.println("3. Modificar producto");
            System.out.println("4. Agregar imagen a un producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Listar productos");
            System.out.println("7. Reporte de costos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    insertarProducto(true);
                    break;
                case 2:
                    insertarProducto(false);
                    break;
                case 3:
                    modificarProducto();
                    break;
                case 4:
                    agregarImagen();
                    break;
                case 5:
                    eliminarProducto();
                    break;
                case 6:
                    listaProductos.imprimirProductos();
                    break;
                case 7:
                    listaProductos.imprimirReporteCostos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private void insertarProducto(boolean alInicio) {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = leerDouble();

        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();

        System.out.print("Fecha de vencimiento (dd/MM/yyyy, dejar vacío si no aplica): ");
        LocalDate fechaVencimiento = leerFecha();

        System.out.print("Cantidad: ");
        int cantidad = leerEntero();

        Producto producto = new Producto(nombre, precio, categoria, fechaVencimiento, cantidad);

        if (alInicio) {
            listaProductos.insertarAlInicio(producto);
        } else {
            listaProductos.insertarAlFinal(producto);
        }
        System.out.println("Producto agregado correctamente.");
    }

    private void modificarProducto() {
        System.out.print("Nombre del producto a modificar: ");
        String nombre = scanner.nextLine();

        if (listaProductos.buscarPorNombre(nombre) == null) {
            System.out.println("No se encontró un producto con ese nombre.");
            return;
        }

        System.out.print("Nuevo precio: ");
        double precio = leerDouble();

        System.out.print("Nueva categoría: ");
        String categoria = scanner.nextLine();

        System.out.print("Nueva fecha de vencimiento (dd/MM/yyyy, dejar vacío si no aplica): ");
        LocalDate fechaVencimiento = leerFecha();

        System.out.print("Nueva cantidad: ");
        int cantidad = leerEntero();

        listaProductos.modificarProducto(nombre, precio, categoria, fechaVencimiento, cantidad);
        System.out.println("Producto modificado correctamente.");
    }

    private void agregarImagen() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ruta de la imagen (relativa al proyecto): ");
        String ruta = scanner.nextLine();

        boolean encontrado = listaProductos.agregarImagenAProducto(nombre, ruta);
        if (encontrado) {
            System.out.println("Imagen agregada correctamente.");
        } else {
            System.out.println("No se encontró un producto con ese nombre.");
        }
    }

    private void eliminarProducto() {
        System.out.print("Nombre del producto a eliminar: ");
        String nombre = scanner.nextLine();

        boolean eliminado = listaProductos.eliminarProducto(nombre);
        if (eliminado) {
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("No se encontró un producto con ese nombre.");
        }
    }

    private int leerEntero() {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido, ingrese un número entero: ");
            }
        }
    }

    private double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido, ingrese un número (use punto decimal): ");
            }
        }
    }

    private LocalDate leerFecha() {
        String entrada = scanner.nextLine().trim();
        if (entrada.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(entrada, FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido, se dejará sin fecha de vencimiento.");
            return null;
        }
    }
}

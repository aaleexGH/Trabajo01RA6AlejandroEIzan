package main;

import java.util.List;
import java.util.Scanner;

import modelo.Libro;
import repositorios.FileLibroRepository;
import repositorios.LibroRepository;
import repositorios.MySQLLibroRepository;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        LibroRepository repoArchivo = new FileLibroRepository();
        LibroRepository repoMySQL = new MySQLLibroRepository();
        
        LibroRepository repoActivo = null;
        LibroRepository repoInactivo = null;
        
        System.out.println("GESTIÓN DE BIBLIOTECA");
        System.out.println("¿Con qué repositorio deseas trabajar?");
        System.out.println("1. Archivo de texto (.txt)");
        System.out.println("2. Base de datos (MySQL)");
        System.out.print("Elige una opción (1 o 2): ");
        
        String eleccionRepo = scanner.nextLine();
        if (eleccionRepo.equals("1")) {
            repoActivo = repoArchivo;
            repoInactivo = repoMySQL;
            System.out.println("-> Repositorio activo: Archivo de texto");
        } else {
            repoActivo = repoMySQL;
            repoInactivo = repoArchivo;
            System.out.println("-> Repositorio activo: MySQL");
        }

        boolean salir = false;
        while (!salir) {
            System.out.println("MENÚ PRINCIPAL");
            System.out.println("1. Mostrar todos los libros");
            System.out.println("2. Buscar libro por título");
            System.out.println("3. Buscar libros por autor");
            System.out.println("4. Buscar libros por rango de precios");
            System.out.println("5. Buscar libros por cantidad mínima en stock");
            System.out.println("6. Insertar nuevo libro");
            System.out.println("7. Eliminar libro por título");
            System.out.println("8. Hacer copia al otro repositorio");
            System.out.println("9. Salir");
            System.out.print("Selecciona una opción: ");
            
            String opcion = scanner.nextLine();
            System.out.println();
            
            switch (opcion) {
                case "1":
                    List<Libro> todos = repoActivo.findAll();
                    if (todos.isEmpty()) System.out.println("No hay libros en el sistema.");
                    else todos.forEach(System.out::println);
                    break;
                    
                case "2":
                    System.out.print("Introduce el título a buscar: ");
                    String titBusqueda = scanner.nextLine();
                    repoActivo.findByTitulo(titBusqueda).forEach(System.out::println);
                    break;
                    
                case "3":
                    System.out.print("Introduce el autor a buscar: ");
                    String autBusqueda = scanner.nextLine();
                    repoActivo.findByAutor(autBusqueda).forEach(System.out::println);
                    break;
                    
                case "4":
                    System.out.print("Precio mínimo: ");
                    double min = Double.parseDouble(scanner.nextLine());
                    System.out.print("Precio máximo: ");
                    double max = Double.parseDouble(scanner.nextLine());
                    repoActivo.findByRangoPrecio(min, max).forEach(System.out::println);
                    break;
                    
                case "5":
                    System.out.print("Stock mínimo: ");
                    int stock = Integer.parseInt(scanner.nextLine());
                    repoActivo.findByStockMinimo(stock).forEach(System.out::println);
                    break;
                    
                case "6":
                    System.out.print("ID del libro: ");
                    String id = scanner.nextLine();
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = Double.parseDouble(scanner.nextLine());
                    System.out.print("Stock inicial: ");
                    int stockInicial = Integer.parseInt(scanner.nextLine());
                    
                    Libro nuevo = new Libro(id, titulo, autor, precio, stockInicial);
                    repoActivo.insertar(nuevo);
                    System.out.println("Libro insertado correctamente.");
                    break;
                    
                case "7":
                    System.out.print("Introduce el título del libro a eliminar: ");
                    String titEliminar = scanner.nextLine();
                    List<Libro> encontrados = repoActivo.findByTitulo(titEliminar);
                    
                    if (encontrados.isEmpty()) {
                        System.out.println("No se encontró ningún libro con ese título.");
                    } else if (encontrados.size() == 1) {
                        repoActivo.eliminarPorId(encontrados.get(0).getId());
                        System.out.println("Libro eliminado.");
                    } else {
                        System.out.println("Hay varios libros con ese título. Por favor, especifica cuál borrar:");
                        encontrados.forEach(System.out::println);
                        System.out.print("Introduce el ID del libro que deseas eliminar: ");
                        String idEliminar = scanner.nextLine();
                        repoActivo.eliminarPorId(idEliminar);
                        System.out.println("Libro eliminado.");
                    }
                    break;
                    
                case "8":
                    List<Libro> origen = repoActivo.findAll();
                    repoInactivo.guardarTodos(origen);
                    System.out.println("Copia realizada con éxito. Se han copiado " + origen.size() + " libros.");
                    break;
                    
                case "9":
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                    
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
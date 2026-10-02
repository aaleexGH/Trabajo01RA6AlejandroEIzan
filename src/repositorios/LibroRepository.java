package repositorios;

import java.util.List;

import modelo.Libro;

/**
 * Define el contrato base para la persistencia y consulta de objetos {@link Libro}.
 * <p>
 * Las clases que implementen esta interfaz deben asegurar las operaciones 
 * elementales de lectura, escritura y borrado, independientemente de si el 
 * almacenamiento subyacente es un archivo de texto o una base de datos relacional.
 * 
 * @author Alejandro e Izan
 */

public interface LibroRepository {
	
	/**
     * Recupera el catálogo completo de libros almacenados en el sistema.
     * 
     * @return una lista que contiene todos los objetos {@link Libro} registrados, 
     *         o una lista vacía si no hay ninguno en el repositorio
     */
    List<Libro> findAll();
    
    /**
     * Localiza aquellos libros cuyo título coincida, total o parcialmente, 
     * con la cadena de texto indicada.
     * 
     * @param titulo el fragmento de texto a buscar dentro del título de las obras
     * @return la lista de libros que cumplen con el criterio de búsqueda
     */
    List<Libro> findByTitulo(String titulo);
    List<Libro> findByAutor(String autor);
    List<Libro> findByRangoPrecio(double min, double max);
    List<Libro> findByStockMinimo(int stockMinimo);
    void insertar(Libro libro);    
    void eliminarPorId(String id);
    void guardarTodos(List<Libro> libros);
}

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
    
    /**
     * Filtra el repositorio para encontrar las obras asociadas a un escritor en particular.
     * 
     * @param autor el nombre o parte del nombre del autor que se desea consultar
     * @return una colección de libros vinculados al autor introducido
     */
    List<Libro> findByAutor(String autor);
    
    /**
     * Busca los libros que tengan un precio de venta comprendido entre dos límites.
     * Se asume que ambos valores, mínimo y máximo, están incluidos en la comprobación.
     * 
     * @param min el importe mínimo aceptable
     * @param max el importe máximo aceptable
     * @return la lista de libros que encajan dentro del rango económico solicitado
     */
    List<Libro> findByRangoPrecio(double min, double max);
    
    /**
     * Obtiene los libros que cuentan con una cantidad de unidades disponibles en 
     * inventario igual o superior a la cifra solicitada.
     * 
     * @param stockMinimo la cantidad mínima de ejemplares requerida en el almacén
     * @return una lista con los libros que disponen del stock suficiente
     */
    List<Libro> findByStockMinimo(int stockMinimo);
    
    /**
     * Registra un nuevo libro en el sistema de almacenamiento.
     * 
     * @param libro el objeto de modelo con los datos del nuevo ejemplar a guardar
     */
    void insertar(Libro libro);   
    
    /**
     * Suprime de forma definitiva un libro del almacén basándose en su código identificador.
     * 
     * @param id el identificador alfanumérico único asociado al libro que se va a eliminar
     */
    void eliminarPorId(String id);
    
    /**
     * Vuelca una colección completa de libros al sistema de persistencia.
     * Se utiliza principalmente para reemplazar los datos actuales o para procesos 
     * de volcado masivo al copiar información entre diferentes tipos de repositorios.
     * 
     * @param libros la lista completa de objetos que formarán el nuevo estado de los datos
     */
    void guardarTodos(List<Libro> libros);
}

package repositorios;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelo.Libro;

/**
 * Implementación de la interfaz {@link LibroRepository} para el almacenamiento 
 * y recuperación de datos persistentes en un archivo de texto plano.
 * <p>
 * Utiliza las clases {@link BufferedReader} y {@link BufferedWriter} para 
 * gestionar la entrada y salida, apoyándose en la API de Streams de Java 
 * para realizar los filtrados de datos cargados en memoria.
 * 
 * @author Alejandro e Izan
 */

public class FileLibroRepository implements LibroRepository{
	
	    private final String archivo = "libros.txt";

	    /**
	     * Lee el archivo completo línea por línea utilizando un bloque try-with-resources.
	     * Transforma cada línea de texto leída en un objeto instanciado utilizando 
	     * el método {@code Libro.fromCSV()}.
	     * 
	     * @return una lista con todos los libros extraídos del archivo, o una lista 
	     *         vacía si el archivo no existe o no se pudo leer
	     */
	    
	    @Override
	    public List<Libro> findAll() {
	        List<Libro> libros = new ArrayList<>();
	        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                libros.add(Libro.fromCSV(linea));
	            }
	        } catch (FileNotFoundException e) {
	        } catch (IOException e) {
	            System.err.println("Error leyendo el archivo: " + e.getMessage());
	        }
	        return libros;
	    }

	    /**
	     * Carga todos los registros a memoria y emplea {@code stream().filter()} para 
	     * retener únicamente aquellos cuyo título contiene la cadena buscada, sin 
	     * distinguir entre letras mayúsculas o minúsculas.
	     * 
	     * @param titulo el texto que se desea buscar dentro de los títulos
	     * @return una lista de libros que coinciden parcialmente con la búsqueda
	     */
	    
	    @Override
	    public List<Libro> findByTitulo(String titulo) {
	        return findAll().stream()
	                .filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
	                .collect(Collectors.toList());
	    }

	    /**
	     * Obtiene el catálogo completo y filtra mediante un flujo de datos las obras 
	     * cuyo autor incluye la secuencia indicada, ignorando la capitalización del texto.
	     * 
	     * @param autor el nombre o fragmento del nombre del escritor a localizar
	     * @return una colección con los libros que corresponden a dicho autor
	     */
	    
	    @Override
	    public List<Libro> findByAutor(String autor) {
	        return findAll().stream()
	                .filter(l -> l.getAutor().toLowerCase().contains(autor.toLowerCase()))
	                .collect(Collectors.toList());
	    }

	    /**
	     * Recupera la información del archivo y filtra con {@code stream()} los resultados 
	     * comprobando que el precio se sitúe entre los límites definidos (ambos incluidos).
	     * 
	     * @param min el valor económico mínimo permitido
	     * @param max el valor económico máximo permitido
	     * @return la lista de libros encuadrados dentro del margen de precios solicitado
	     */
	    
	    @Override
	    public List<Libro> findByRangoPrecio(double min, double max) {
	        return findAll().stream()
	                .filter(l -> l.getPrecio() >= min && l.getPrecio() <= max)
	                .collect(Collectors.toList());
	    }

	    /**
	     * Filtra la colección de libros en memoria reteniendo solo los ejemplares 
	     * cuyo inventario disponible alcanza o supera el umbral establecido.
	     * 
	     * @param stockMinimo la cantidad mínima en almacén requerida
	     * @return una lista de libros con existencias suficientes
	     */
	    
	    @Override
	    public List<Libro> findByStockMinimo(int stockMinimo) {
	        return findAll().stream()
	                .filter(l -> l.getStock() >= stockMinimo)
	                .collect(Collectors.toList());
	    }

	    /**
	     * Abre el archivo en modo de adición (append) y escribe la representación 
	     * delimitada por separadores del nuevo registro al final del documento, 
	     * añadiendo un salto de línea tras la inserción.
	     * 
	     * @param libro el objeto que contiene la información a añadir al archivo
	     */
	    
	    @Override
	    public void insertar(Libro libro) {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
	            bw.write(libro.toCSV());
	            bw.newLine();
	        } catch (IOException e) {
	            System.err.println("Error escribiendo en el archivo: " + e.getMessage());
	        }
	    }

	    /**
	     * Realiza un borrado físico de un registro. Para ello, extrae todo el listado, 
	     * descarta mediante {@code filter()} el elemento que coincide con el parámetro 
	     * exacto y, posteriormente, sobrescribe todo el archivo con la lista resultante.
	     * 
	     * @param id el identificador alfanumérico exacto del libro que se desea eliminar
	     */
	    
	    @Override
	    public void eliminarPorId(String id) {
	        List<Libro> libros = findAll().stream()
	                .filter(l -> !l.getId().equals(id))
	                .collect(Collectors.toList());
	        guardarTodos(libros);
	    }

	    /**
	     * Reemplaza por completo el contenido del archivo de texto.
	     * Itera la colección proporcionada y vuelca, línea por línea, 
	     * los objetos de la memoria al formato delimitado del fichero subyacente.
	     * 
	     * @param libros la colección íntegra que conformará el nuevo estado del documento
	     */
	    
	    @Override
	    public void guardarTodos(List<Libro> libros) {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
	            for (Libro libro : libros) {
	                bw.write(libro.toCSV());
	                bw.newLine();
	            }
	        } catch (IOException e) {
	            System.err.println("Error sobrescribiendo el archivo: " + e.getMessage());
	        }
	    }
	}


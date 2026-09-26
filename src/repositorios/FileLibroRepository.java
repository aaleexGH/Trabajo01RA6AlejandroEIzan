package repositorios;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelo.Libro;
public class FileLibroRepository implements LibroRepository{
	
	    private final String archivo = "libros.txt";

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

	    @Override
	    public List<Libro> findByTitulo(String titulo) {
	        return findAll().stream()
	                .filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<Libro> findByAutor(String autor) {
	        return findAll().stream()
	                .filter(l -> l.getAutor().toLowerCase().contains(autor.toLowerCase()))
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<Libro> findByRangoPrecio(double min, double max) {
	        return findAll().stream()
	                .filter(l -> l.getPrecio() >= min && l.getPrecio() <= max)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<Libro> findByStockMinimo(int stockMinimo) {
	        return findAll().stream()
	                .filter(l -> l.getStock() >= stockMinimo)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public void insertar(Libro libro) {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
	            bw.write(libro.toCSV());
	            bw.newLine();
	        } catch (IOException e) {
	            System.err.println("Error escribiendo en el archivo: " + e.getMessage());
	        }
	    }

	    @Override
	    public void eliminarPorId(String id) {
	        List<Libro> libros = findAll().stream()
	                .filter(l -> !l.getId().equals(id))
	                .collect(Collectors.toList());
	        guardarTodos(libros);
	    }

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

